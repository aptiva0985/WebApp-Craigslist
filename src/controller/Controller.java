/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import databean.UserBean;


@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        Action.add(new ChangePwdAction(model));
        Action.add(new ImageAction(model));
        Action.add(new ManageAction(model));
        Action.add(new LoginAction(model));
        Action.add(new ShowItemAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new DeleteAction(model));
        Action.add(new UploadAction(model));
        Action.add(new SearchAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }

    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        UserBean    user        = (UserBean) session.getAttribute("user");
        String      action      = getActionName(servletPath);

        //System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        if (action.equals("register.do") || action.equals("login.do")
            || action.equals("image.do") || action.equals("search.do")) {
            // Allow these actions without logging in
            return Action.perform(action,request);
        }

        if (user == null) {
            // If the user hasn't logged in, direct him to the login page
            //return Action.perform("login.do",request);
            return Action.perform("showitem.do",request);
        }

          // Let the logged in user run his chosen action
        return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")) {
               RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
               d.forward(request,response);
               return;
        }

        if (nextPage.equals("image")) {
               RequestDispatcher d = request.getRequestDispatcher(nextPage);
               d.forward(request,response);
               return;
        }

        throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    /*
     * Returns the path component after the last slash removing any "extension"
     * if present.
     */
    private String getActionName(String path) {
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}

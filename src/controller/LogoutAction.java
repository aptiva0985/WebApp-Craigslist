/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;


/*
 * Logs out by setting the "user" session attribute to null.
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model) { }

    public String getName() { return "logout.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

        if((String)session.getAttribute("message") == null){
            session.setAttribute("message", "You are now logged out");
        }

        return "showitem.do";
    }
}

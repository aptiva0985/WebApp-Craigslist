/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.genericdao.RollbackException;

import databean.ItemBean;
import databean.UserBean;


/*
 * Sets up the request attributes for manage.jsp.
 * This is the way to enter "Manage Your Items"
 * from someplace else in the site.
 *
 *
 * Sets the "photoList" request attribute in order to display
 * the list of user's photos for management.
 *
 * Forwards to manage.jsp.
 */
public class ManageAction extends Action {

    private ItemDAO itemDAO;

    public ManageAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() { return "manage.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            //retrieval all items of certain user
            UserBean user = (UserBean) request.getSession(false).getAttribute("user");
            ItemBean[] usrItemList = itemDAO.getUseItems(user.getUserName());
            request.setAttribute("usrItemList",usrItemList);

            return "manage.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}

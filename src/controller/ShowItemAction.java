/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.ItemDAO;
import org.genericdao.RollbackException;
import databean.ItemBean;


/*
 * Looks all items and display them
 *
 */
public class ShowItemAction extends Action {
    private ItemDAO itemDAO;

    public ShowItemAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() { return "showitem.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar

            ItemBean[] itemList = itemDAO.getItems();
            request.setAttribute("itemList", itemList);

            return "showitem.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "showitem.jsp";
        }
    }
}

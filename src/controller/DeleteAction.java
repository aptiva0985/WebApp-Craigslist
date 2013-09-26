/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.ItemDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.ItemBean;
import databean.UserBean;
import formbean.IDForm;;

/*
 * Removes an item.  Given an "id" parameter.
 * Checks to see that id is valid number for a photo and that
 * the logged user is the owner.
 *
 * Sets up the "userList" and "photoList" request attributes
 * and if successful, forwards back to to "manage.jsp".
 */
public class DeleteAction extends Action {
    private FormBeanFactory<IDForm> formBeanFactory = FormBeanFactory.getInstance(IDForm.class);

    private ItemDAO itemDAO;

    public DeleteAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() { return "delete.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            IDForm form = formBeanFactory.create(request);

            UserBean user = (UserBean)request.getSession().getAttribute("user");
            //！！！！！需要检测当前用户是不是真的拥有这个物品 否则可能恶意删除他人
            itemDAO.delete(form.getIdAsInt());

            // Be sure to re-get the photoList after the delete
            ItemBean[] usrItemList = itemDAO.getUseItems(user.getUserName());
            request.setAttribute("usrItemList",usrItemList);

            HttpSession session = request.getSession(false);
            if((String)session.getAttribute("message") == null){
                session.setAttribute("message", "item " + form.getIdAsInt() + " has been deleted.");
            }

            return "manage.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "manage.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "manage.jsp";
        }
    }
}

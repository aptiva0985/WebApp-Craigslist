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
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.ItemBean;
import formbean.IDForm;

/**
 * This action looks up the item bean by "id" and then passes it
 * (via request attribute) to the ImageServlet.  See also the mapping
 * of /image in the web.xml file.
 *
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bytes and not HTML.
 */
public class ImageAction extends Action {
    private FormBeanFactory<IDForm> formBeanFactory = FormBeanFactory.getInstance(IDForm.class);

    private ItemDAO itemDAO;

    public ImageAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            IDForm form = formBeanFactory.create(request);

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }

            ItemBean p = itemDAO.getIDItem(form.getIdAsInt());
            if (p != null) request.setAttribute("photo",p);

            // Note: "/image" is mapped (in the web.xml file) to the ImageServlet
            // which looks at the "photo" request attribute and sends back the bytes.
            // If there is no "photo" attribute, it sends back HTTP Error 404 (resource not found).
            return "image";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}

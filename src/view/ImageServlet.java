/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databean.ItemBean;


/**
 * This servlet is the "view" for images.  It looks at the "item"
 * request attribute and sends it's image bytes to the client browser.
 *
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bits and not HTML.
 */
@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ItemBean cur = (ItemBean) request.getAttribute("photo");

        if (cur == null || cur.getPhoto() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(cur.getContentType());

        ServletOutputStream out = response.getOutputStream();
        out.write(cur.getPhoto());
    }
}

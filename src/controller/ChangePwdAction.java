/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.UserBean;
import formbean.ChangePwdForm;;

//Action for password change
public class ChangePwdAction extends Action {
    private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);

    private UserDAO userDAO;

    public ChangePwdAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "change-pwd.do"; }

    public String perform(HttpServletRequest request) {
        // Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            UserBean user = (UserBean) request.getSession(false).getAttribute("user");
            request.setAttribute("user", user);
            // Load the form parameters into a form bean
            ChangePwdForm form = formBeanFactory.create(request);

            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
                return "change-pwd.jsp";
            }

            // Check for any validation errors
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "change-pwd.jsp";
            }

            // Check the password
            Transaction.begin();
            if (!user.checkPassword(form.getOldPassword())) {
                errors.add("Incorrect old password");
                return "change-pwd.jsp";
            }
            Transaction.commit();

            // Change the password
            userDAO.setPassword(fixBadChars(user.getEmail()),
                                fixBadChars(form.getNewPassword()));

            HttpSession session = request.getSession(false);
            session.setAttribute("message","Password changed for user: "+user.getUserName()+
                    ".For securiy, please login again with new password.");

            return "logout.do";
        } catch (RollbackException e) {
            errors.add(e.toString());
            return "change-pwd.jsp";
        } catch (FormBeanException e) {
            errors.add(e.toString());
            return "change-pwd.jsp";
        }
    }

    private String fixBadChars(String s) {
        if (s == null || s.length() == 0) return s;

        Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                           break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }

        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}

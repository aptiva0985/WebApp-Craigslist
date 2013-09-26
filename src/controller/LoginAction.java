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
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.UserBean;
import formbean.LoginForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested item.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "login.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
                return "login.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "login.jsp";
            }

            // Look up the user
            UserBean user = userDAO.read(fixBadChars(form.getEmail()));

            if (user == null) {
                errors.add("Email Address not found");
                return "login.jsp";
            }

            // Check the password
            if (!user.checkPassword(fixBadChars(form.getPassword()))) {
                errors.add("Incorrect password");
                return "login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            HttpSession session = request.getSession(false);
            session.setAttribute("user",user);

            return "showitem.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "login.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "login.jsp";
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

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

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.UserBean;
import formbean.RegisterForm;

/*
 * Processes the parameters from the form in register.jsp.
 * If successful:
 *   (1) creates a new User bean
 *   (2) sets the "user" session attribute to the new User bean
 *   (3) redirects to view the originally requested photo.
 * If there was no photo originally requested to be viewed
 * (as specified by the "redirect" hidden form value),
 * just redirect to manage.do to allow the user to add some
 * photos.
 */
public class RegisterAction extends Action {
    private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            RegisterForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            if(userDAO.match(MatchArg.equals("email", fixBadChars(form.getNewEmail()))).length != 0 ||
               userDAO.match(MatchArg.equals("userName", fixBadChars(form.getNewUserName()))).length != 0){
                errors.add("Email is already used.");
            }

            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
                return "register.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "register.jsp";
            }

            // Create the user bean
            UserBean user = new UserBean();
            user.setUserName(fixBadChars(form.getNewUserName()));
            user.setEmail(fixBadChars(form.getNewEmail()));
            user.setFirstName(fixBadChars(form.getNewFirstName()));
            user.setLastName(fixBadChars(form.getNewLastName()));
            user.renewPassword(fixBadChars(form.getNewPass()));
            userDAO.create(user);

            // Attach (this copy of) the user bean to the session
            HttpSession session = request.getSession(false);
            session.setAttribute("user",user);

            return "showitem.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "register.jsp";
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

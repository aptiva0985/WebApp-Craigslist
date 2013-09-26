/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */


package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdForm extends FormBean {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword()     { return oldPassword;     }
    public String getNewPassword()     { return newPassword;     }
    public String getConfirmPassword() { return confirmPassword; }

    public void setOldPassword(String s)     { oldPassword     = s.trim(); }
    public void setNewPassword(String s)     { newPassword     = s.trim(); }
    public void setConfirmPassword(String s) { confirmPassword = s.trim(); }

    //validate the initial form
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (oldPassword == null || oldPassword.length() == 0) {
            errors.add("Old Password is required");
        }

        if (newPassword == null || newPassword.length() == 0) {
            errors.add("New Password is required");
        }

        if (confirmPassword == null || confirmPassword.length() == 0) {
            errors.add("Confirm Pwd is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (!newPassword.equals(confirmPassword)) {
            errors.add("Passwords do not match");
        }

        if (oldPassword.matches(".*[<>\"].*")) errors.add("Old Password may not contain angle brackets or quotes");
        if (newPassword.matches(".*[<>\"].*")) errors.add("New Password may not contain angle brackets or quotes");
        if (confirmPassword.matches(".*[<>\"].*")) errors.add("Confirm Pwd may not contain angle brackets or quotes");

        return errors;
    }
}

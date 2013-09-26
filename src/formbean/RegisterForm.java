/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;


public class RegisterForm extends FormBean {
    private String newUserName;
    private String newEmail;
    private String newFirstName;
    private String newLastName;
    private String newPass;
    private String newPassCon;

    public String getNewUserName()   { return newUserName;   }
    public String getNewEmail()      { return newEmail;      }
    public String getNewFirstName()  { return newFirstName;  }
    public String getNewLastName()   { return newLastName;   }
    public String getNewPass()       { return newPass;       }
    public String getNewPassCon()    { return newPassCon;    }

    public void setNewUserName(String s)   { newUserName = s.trim();  }
    public void setNewEmail(String s)      { newEmail = s.trim();     }
    public void setNewFirstName(String s)  { newFirstName = s.trim(); }
    public void setNewLastName(String s)   { newLastName = s.trim();  }
    public void setNewPass(String s)       { newPass = s.trim();      }
    public void setNewPassCon(String s)    { newPassCon = s.trim();   }

    //validate the register form
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (newUserName == null || newUserName.length() == 0) errors.add("User Name is required");
        if (newEmail == null || newEmail.length() == 0) errors.add("Email is required");
        if (newFirstName == null || newFirstName.length() == 0) errors.add("First Name is required");
        if (newLastName == null || newLastName.length() == 0) errors.add("Last Name is required");
        if (newPass == null || newPass.length() == 0) errors.add("Password is required");
        if (!newPass.equals(newPassCon)) errors.add("Different password, please confirm");
        if (!validateEmail(newEmail)) errors.add("Invalid Format for E-mail Address");
        if (newUserName.matches(".*[<>\"].*")) errors.add("User name may not contain angle brackets or quotes");
        if (newEmail.matches(".*[<>\"].*")) errors.add("Email address may not contain angle brackets or quotes");
        if (newFirstName.matches(".*[<>\"].*")) errors.add("First name may not contain angle brackets or quotes");
        if (newLastName.matches(".*[<>\"].*")) errors.add("Last name may not contain angle brackets or quotes");

        return errors;
    }

    //validate the Email address
    private static boolean validateEmail(String line){
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(line);
        return m.find();
    }

}
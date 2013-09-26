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


public class LoginForm extends FormBean {
    private String email;
    private String password;

    public String getEmail()         { return email;         }
    public String getPassword()      { return password;      }

    public void setEmail(String s)         { email = s.trim();        }
    public void setPassword(String s)      { password = s.trim();     }

    //validate the initial form
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();


        if (email == null || email.length() == 0) errors.add("Email Address is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (email.matches(".*[<>\"].*")) errors.add("Email address may not contain angle brackets or quotes");
        if (!validateEmail(email)) errors.add("Invalid Format for E-mail Address");

        return errors;
    }

    //validate the Email address
    private static boolean validateEmail(String line){
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(line);
        return m.find();
    }
}

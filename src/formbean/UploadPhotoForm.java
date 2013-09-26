/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package formbean;

import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class UploadPhotoForm extends FormBean {
    private String description= "";
    private String price      = "";
    private FileProperty file = null;

    public static int FILE_MAX_LENGTH = 1024 * 1024;

    public String       getDescription()    { return description;    }
    public String getPrice()                { return price;          }
    public FileProperty getFile()           { return file;           }

    public void setDescription(String s)    { description = trimAndConvert(s,"<>\""); }
    public void setPrice(String s)          { this.price = s;         }
    public void setFile(FileProperty file)  { this.file   = file;     }

    //validate the initial form
    public ArrayList<String> getValidationErrors() {
        ArrayList<String> errors = new ArrayList<String>();

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            errors.add("Price is not a number");
        }

        if(description == null || description == "") {
            errors.add("You must provide a valid item description");
            return errors;
        }

        if (file == null || file.getFileName().length() == 0) {
            errors.add("You must provide a file");
            return errors;
        }

        if (file.getBytes().length == 0) {
            errors.add("Zero length file");
        }

        if (description.matches(".*[<>\"].*")) errors.add("Last name may not contain angle brackets or quotes");
        if (price.matches(".*[<>\"].*")) errors.add("Last name may not contain angle brackets or quotes");

        return errors;
    }
}

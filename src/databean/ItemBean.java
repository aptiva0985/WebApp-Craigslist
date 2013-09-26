/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package databean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.genericdao.PrimaryKey;

////Java Bean for item records in the database
@PrimaryKey("itemID")
public class ItemBean {
    public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
            ".jpg", ".gif", ".JPG"
    } ));

    private int itemID         = 0;
    private String userName    = null;
    private String email       = null;
    private Date listDate      = null;
    private String description = null;
    private double price       = 0.0;
    private byte[] photo       = null;
    private String contentType = null;

    public ItemBean() {}

    public int getItemID()             { return itemID;     }
    public String getUserName()        { return userName;   }
    public String getEmail()           { return email;        }
    public Date getListDate()          { return listDate;   }
    public String getDescription()     { return description;}
    public double getPrice()           { return price;      }
    public byte[] getPhoto()           { return photo;      }
    public String getContentType()     { return contentType;}

    public void setItemID(int s)         { itemID = s;     }
    public void setUserName(String s)    { userName = s;   }
    public void setEmail(String s)       { email = s;      }
    public void setListDate(Date s)      { listDate = s;   }
    public void setDescription(String s) { description = s;}
    public void setPrice(double s)       { price = s;      }
    public void setPhoto(byte[] s)       { photo = s;      }
    public void setContentType(String s) { contentType = s;}
}

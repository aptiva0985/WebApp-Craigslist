/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.genericdao.PrimaryKey;

//Java Bean for user records in the database
@PrimaryKey("userID")
public class UserBean {
    private int userID         = 0;
    private String userName    = null;
    private String email       = null;
    private String firstName   = null;
    private String lastName    = null;
    private String password    = null;
    private int salt           = 0;

    public UserBean() {}

    public int getUserID()             { return userID;   }
    public String getUserName()        { return userName; }
    public String getEmail()           { return email;    }
    public String getFirstName()       { return firstName;}
    public String getLastName()        { return lastName; }
    public String getPassword()        { return password; }
    public int getSalt()               { return salt;     }


    public void setUserID(int s)       { userID = s;      }
    public void setUserName(String s)  { userName = s;    }
    public void setEmail(String s)     { email = s;       }
    public void setFirstName(String s) { firstName = s;   }
    public void setLastName(String s)  { lastName = s;    }
    public void setPassword(String s)  { password = s;    }
    public void setSalt(int s)         { salt = s;        }
    public void renewPassword(String s){salt = newSalt(); password = hash(s);}

    public boolean checkPassword(String inPassword) {
        return password.equals(hash(inPassword));
    }

    private String hash(String clearPassword) {
        if (salt == 0) return null;

        MessageDigest md = null;
        try {
          md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
          throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
        }

        String saltString = String.valueOf(salt);

        md.update(saltString.getBytes());
        md.update(clearPassword.getBytes());
        byte[] digestBytes = md.digest();

        // Format the digest as a String
        StringBuffer digestSB = new StringBuffer();
        for (int i = 0; i < digestBytes.length; i++) {
          int lowNibble = digestBytes[i] & 0x0f;
          int highNibble = (digestBytes[i]>>4) & 0x0f;
          digestSB.append(Integer.toHexString(highNibble));
          digestSB.append(Integer.toHexString(lowNibble));
        }
        String digestStr = digestSB.toString();

        return digestStr;
    }

    private int newSalt() {
        Random random = new Random();
        return random.nextInt(8192) + 1;  // salt cannot be zero
    }
}

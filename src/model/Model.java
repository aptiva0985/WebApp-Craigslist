/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.ItemBean;
import databean.UserBean;

public class Model {
    private ItemDAO itemDAO;
    private UserDAO userDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL    = config.getInitParameter("jdbcURL");
            String baseStr    = this.getClass().getClassLoader().getResource("/").getPath();
            File tmp = new File(baseStr);
            tmp = tmp.getParentFile();
            String base = tmp.toString().replace("%20", " ");

            ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);

            userDAO = new UserDAO(pool,  "ycui_user");
            itemDAO = new ItemDAO(pool,  "ycui_item");

            if(userDAO.getUsers().length == 0) {
                //create initial users
                UserBean usr = new UserBean();
                usr.setUserName("aaa");
                usr.renewPassword("111");
                usr.setFirstName("Adam");
                usr.setLastName("Bob");
                usr.setEmail("aaa@gmail.com");
                userDAO.create(usr);

                usr = new UserBean();
                usr.setUserName("bbb");
                usr.renewPassword("111");
                usr.setFirstName("Charlie");
                usr.setLastName("Davis");
                usr.setEmail("bbb@gmail.com");
                userDAO.create(usr);

                usr = new UserBean();
                usr.setUserName("ccc");
                usr.renewPassword("111");
                usr.setFirstName("Ellsworth");
                usr.setLastName("Frank");
                usr.setEmail("ccc@gmail.com");
                userDAO.create(usr);
            }

            if(itemDAO.getItems().length == 0) {
                //create initial items
                ItemBean item = new ItemBean();
                item.setUserName("aaa");
                item.setPrice(1.1);
                item.setListDate(new Date());
                item.setDescription("User aaa item 1");
                item.setEmail("aaa@gmail.com");
                tmp = new File(base + "/1.jpg");
                byte[] pic = new byte[(int) tmp.length()];
                FileInputStream stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("aaa");
                item.setPrice(2.2);
                item.setListDate(new Date());
                item.setDescription("User aaa item 2");
                item.setEmail("aaa@gmail.com");
                tmp = new File(base + "/2.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("aaa");
                item.setPrice(3.3);
                item.setListDate(new Date());
                item.setDescription("User aaa item 3");
                item.setEmail("aaa@gmail.com");
                tmp = new File(base + "/3.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("bbb");
                item.setPrice(1.1);
                item.setListDate(new Date());
                item.setDescription("User bbb item 1");
                item.setEmail("bbb@gmail.com");
                tmp = new File(base + "/4.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("bbb");
                item.setPrice(2.2);
                item.setListDate(new Date());
                item.setDescription("User bbb item 2");
                item.setEmail("bbb@gmail.com");
                tmp = new File(base + "/5.gif");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".gif");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("bbb");
                item.setPrice(3.3);
                item.setListDate(new Date());
                item.setDescription("User bbb item 3");
                item.setEmail("bbb@gmail.com");
                tmp = new File(base + "/1.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("ccc");
                item.setPrice(1.1);
                item.setListDate(new Date());
                item.setDescription("User ccc item 1");
                item.setEmail("ccc@gmail.com");
                tmp = new File(base + "/2.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("ccc");
                item.setPrice(2.2);
                item.setListDate(new Date());
                item.setDescription("User ccc item 2");
                item.setEmail("ccc@gmail.com");
                tmp = new File(base + "/3.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();

                item = new ItemBean();
                item.setUserName("ccc");
                item.setPrice(3.3);
                item.setListDate(new Date());
                item.setDescription("User ccc item 3");
                item.setEmail("ccc@gmail.com");
                tmp = new File(base + "/4.jpg");
                pic = new byte[(int) tmp.length()];
                stream = new FileInputStream(tmp);
                stream.read(pic);
                item.setPhoto(pic);
                item.setContentType(".jpg");
                itemDAO.create(item);
                stream.close();
            }

        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public ItemDAO getItemDAO()  { return itemDAO; }
    public UserDAO getUserDAO()  { return userDAO; }
}

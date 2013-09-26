/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBean.class, tableName, cp);
    }

    //method that takes a user's e-mail address
    //returns a UserBean containing information about that user
    public UserBean read(String email) throws RollbackException {
        try {
            Transaction.begin();

            UserBean tmp = null;

            //try to get the user's information
            if(match(MatchArg.equals("email", email)).length != 0) {
                tmp = match(MatchArg.equals("email", email))[0];
            }

            Transaction.commit();

            return tmp;

        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

    //creates a new user record in the user table
    //returns a UserBean containing all the fields that describe a user
    public void create(UserBean usr) throws RollbackException {
        try {
            Transaction.begin();
            //create a new UserBean in the database with the next id number
            createAutoIncrement(usr);
            Transaction.commit();

        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

    public void setPassword(String email, String password) throws RollbackException {

        UserBean dbUser = read(email);

        if (dbUser == null) {
            throw new RollbackException("User "+email+" no longer exists");
        }

        dbUser.renewPassword(password);

        update(dbUser);
    }

    public UserBean[] getUsers() throws RollbackException {
        UserBean[] users = match();
        return users;
    }
}

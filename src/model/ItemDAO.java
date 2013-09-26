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

import databean.ItemBean;

public class ItemDAO extends GenericDAO<ItemBean> {
    public ItemDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(ItemBean.class, tableName, cp);
    }

    //method that takes a user id parameter
    //returns array of ItemBeans about the items this user has listed for sale.
    public ItemBean[] getUseItems(String userName) throws RollbackException {
        // Calls GenericDAO's match() method.
        // This no match constraint arguments, match returns all the Item beans
        ItemBean[] items = match(MatchArg.equals("userName", userName));

        return items;
    }

    //method that takes a item id parameter
    //returns array of ItemBeans about the items this user has listed for sale.
    public ItemBean getIDItem(int itemID) throws RollbackException {
        // Calls GenericDAO's match() method.
        // This no match constraint arguments, match returns all the Item beans
        ItemBean item = match(MatchArg.equals("itemID", itemID))[0];

        return item;
    }

    //add a new item into the item table
    //returns an ItemBean that the new item being offered for sale
    public void create(ItemBean item) throws RollbackException {
        try {
            Transaction.begin();

            // Create a new ItemBean in the database with the next id number
            createAutoIncrement(item);

            Transaction.commit();

        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

    public ItemBean[] getItems() throws RollbackException {

        // Calls GenericDAO's match() method.
        // This no match constraint arguments, match returns all the Item beans
        ItemBean[] items = match();

        return items;
    }
}

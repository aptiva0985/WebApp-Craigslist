/* Name: Yuanshuo Cui
 * Date: 02/25/2013
 * Course Number: 08-764
 */

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.ItemBean;


/*
 * Search all matched items according to
 * user's input.
 *
 * Split keywords by space
 *
 * Search them one by one
 * and get all matched items with all keywords in their description
 *
 */
public class SearchAction extends Action {

    private ItemDAO itemDAO;

    public SearchAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() { return "search.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            String query = (String) request.getParameter("query");
            String[] queryItem = query.split(" ");
            ItemBean[] resultList = itemDAO.getItems();

            for(String curItem : queryItem){
                if(curItem == null || curItem == "") {
                    continue;
                }
                ItemBean[] tmpList = itemDAO.match(MatchArg.contains("description", curItem));
                resultList = intersect(tmpList, resultList);
            }

            request.setAttribute("queryInput",query);
            request.setAttribute("resultList",resultList);

            return "search.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "search.jsp";
        }
    }

    //get intersection of two result sets
    private ItemBean[] intersect(ItemBean[] arr1, ItemBean[] arr2) {
        HashMap<Integer, ItemBean> map1 = new HashMap<Integer, ItemBean>();
        HashMap<Integer, Boolean> map2 = new HashMap<Integer, Boolean>();

        for (ItemBean tmp : arr1) {
            if (!map1.containsKey(tmp.getItemID())) {
                map1.put(tmp.getItemID(), tmp);
                map2.put(tmp.getItemID(), Boolean.FALSE);
            }
        }

        for (ItemBean tmp : arr2) {
            if (map1.containsKey(tmp.getItemID())) {
                map2.put(tmp.getItemID(), Boolean.TRUE);
            }
        }

        for (Entry<Integer, Boolean> tmp : map2.entrySet()) {
            if (tmp.getValue() == Boolean.FALSE) {
                map1.remove(tmp.getKey());
            }
        }

        return map1.values().toArray(new ItemBean[0]);
    }
}

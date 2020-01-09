/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vu.cs506.assignment.two.shoppingcart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author CS506 Team
 */
public class DatabaseHelper {
    
    private static final String FILE = ".\\assets\\BS00000000.accdb";

    private Statement getConnectionStatement() throws ClassNotFoundException, SQLException {

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String url = "jdbc:ucanaccess://" + FILE;
        Connection con = DriverManager.getConnection(url);

        Statement statement = con.createStatement();

        return statement;
    }

    public int addOrder(String noOranges, String noGrapes, String noBananas, String noMangoes, String noItems, String subTotal, String shipping, String grandTotal) {

        int num = 0 ;
        
        try {

            Statement st = getConnectionStatement();

            String sql = "INSERT INTO Orders "
                    + "(Orange, Grapes, Banana, Mango, NoOfItems, SubTotal, Shipping, GrandTotal)"
                    + " Values ('" + noOranges + "', '" + noGrapes + "', '" + noBananas + "', '" + noMangoes + "', '"
                    + noItems + "', '" + subTotal + "', '" + shipping + "', '" + grandTotal + "')";

            num = st.executeUpdate(sql);

            st.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.toString());
        }
        
        return num ;
    }

    public ArrayList<Product> fetchProductsData() {
        
        ArrayList<Product> items = new ArrayList();

        try {

            Statement st = getConnectionStatement();

            String sql = "SELECT"
                    + " Id, "
                    + " Name, "
                    + " Price, "
                    + " Quantity"
                    + " FROM Products"
                    + " ORDER BY Id";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String strPrice = rs.getString("Price");
                String strQuantity = rs.getString("Quantity");

                float price = 0.0f;
                int quantity = 0;

                try {

                    price = Float.parseFloat(strPrice);
                    quantity = Integer.parseInt(strQuantity);

                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }

                if (id > 0 && !name.isEmpty() && price > 0.0f && quantity > 0) {

                    Product product = new Product(id, name, price, quantity);

//                    System.out.println(product.toString());

                    items.add(product);

                } else {

                    System.out.println("Invalid data");
                }
            }

            st.close();
            rs.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.toString());
        }
        
        return items ;
    }
}

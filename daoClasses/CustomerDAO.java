/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoClasses;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BlackMageMario
 */
public class CustomerDAO {
    private static final String DATABASE_URL = "jdbc:mysql://mysql1.it.nuigalway.ie:3306/mydb3409";
        
    private static final String USERNAME = "mydb3409he";
    private static final String PASSWORD = "fo3gip";
    private ArrayList<Customer> customers;
    public CustomerDAO()
    {
        customers = new ArrayList<Customer>();
        Connection con = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            con =  DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            System.out.println("Look a customer");
            while(rs.next())
            {
                
                System.out.println("Customer ID: " + rs.getInt(1));
                Customer customer = new Customer(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),
                rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                rs.getString(11), rs.getInt(12), rs.getInt(13));
                customers.add(customer);
            }
            
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        finally
        {
            try
            {
                if(stmt != null)
                {
                    stmt.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }
             catch(SQLException ex)
            {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());
            }
        }  
    }
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }
}

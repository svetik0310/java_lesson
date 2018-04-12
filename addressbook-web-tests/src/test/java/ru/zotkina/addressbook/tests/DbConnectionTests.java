package ru.zotkina.addressbook.tests;

import org.testng.annotations.Test;
import ru.zotkina.addressbook.model.GroupData;
import ru.zotkina.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTests {
    @Test
    public void DbConnectionTest() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password="+ "&serverTimezone=UTC");
            Statement st=conn.createStatement();
            ResultSet rs= st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups g= new Groups();
            while (rs.next())
            {
                g.add(new GroupData().withIdgroup(rs.getInt("group_id")).withGroupname(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(g);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}

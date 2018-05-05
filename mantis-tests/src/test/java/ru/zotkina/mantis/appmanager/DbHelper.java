package ru.zotkina.mantis.appmanager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.zotkina.mantis.model.UserData;
import ru.zotkina.mantis.model.Users;

import java.sql.*;
import java.util.List;


public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper()
    {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
       /* Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password="+ "&serverTimezone=UTC");
            Statement st=conn.createStatement();
            ResultSet rs= st.executeQuery("select username, password, email, id from mantis_user_table");
            Users g= new Users();
            while (rs.next())
            {
                g.add(new UserData().withUsername(rs.getString("username")).withPassword(rs.getString("password"))
                        .withEmail(rs.getString("email")).withId(rs.getInt("id")));
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
    }*/

    }

    public Users users()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData" ).list();
        for ( UserData group : result ) {
            System.out.println( group );
        }
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}

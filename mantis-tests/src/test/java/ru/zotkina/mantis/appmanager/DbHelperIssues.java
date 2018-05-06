package ru.zotkina.mantis.appmanager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.zotkina.mantis.model.Issue;
import ru.zotkina.mantis.model.Issues;

import java.sql.*;
import java.util.List;


public class DbHelperIssues {
    private final SessionFactory sessionFactory;

    public DbHelperIssues()
    {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password="+ "&serverTimezone=UTC");
            Statement st=conn.createStatement();
            ResultSet rs= st.executeQuery("select summary, status, id from mantis_bug_table");
            Issues g= new Issues();
            while (rs.next())
            {
                g.add(new Issue().withId(rs.getInt("id"))
                        .withStatus(rs.getShort("status"))
                        .withSummary(rs.getString("summary")));
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

    public Issues issues()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Issue> result = session.createQuery( "from Issue" ).list();
        for ( Issue group : result ) {
            System.out.println( group );
        }
        session.getTransaction().commit();
        session.close();
        return new Issues(result);
    }
}

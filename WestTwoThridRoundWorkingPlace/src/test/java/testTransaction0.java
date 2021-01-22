

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

public class testTransaction0 {

    public static void main(String[] args) {

        Connection conn=null;

        Statement stmt=null;



        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:jdbctest", "test", "test");



            conn.setAutoCommit(false);

            stmt = conn.createStatement();



            stmt.addBatch("insert into dept values (51, '500', 'ccc')");

            stmt.addBatch("insert into dept values (52, '600', 'ddd')");

            stmt.addBatch("insert into dept values (53, '700', 'eee')");

            stmt.executeBatch();



            conn.commit();

            conn.setAutoCommit(true);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch(SQLException e) {

            e.printStackTrace();

            try {

                if(conn != null)

                {



                    conn.rollback();



                    conn.setAutoCommit(true);

                }

            } catch (SQLException e1) {

                e1.printStackTrace();

            }

        }finally {

            if(conn != null)  {

                try {

                    conn.close();

                } catch (SQLException e) {

                    e.printStackTrace();

                }

            }

        }

    }

}

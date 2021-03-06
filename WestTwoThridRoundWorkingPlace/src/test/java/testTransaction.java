import java.sql.*;

public class testTransaction {


        /***
         本文使用的数据库名称为user,账号密码均为root,
         数据库含表userinfo,
         该表有三个字段，id(INT),userinfo(VARCHAR),password(VARCHAR)
         读者可根据数据库的实际情况对语句进行修改
         ***/
        public static void main(String[] args) {
            ResultSet rs = null;
            Connection connection = null;
            Statement statement = null;
            try {
                //1,加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
                Driver driver=new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                //2.创建连接
                //此处按照实际的数据库名称和账号密码进行修改
                //格式为jdbc:mysql://127.0.0.1:3306/数据库名称?useSSL=true&characterEncoding=utf-8&user=账号名&password=密码
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/try1?useSSL=true&characterEncoding=utf-8&user=root&password=honggetyf769");

                String url="jdbc:mysql://localhost:3306/try1?characterEncoding=utf-8&serverTimezone=UTC";//使用最新版本的mysql jdbc驱动需要写时间区；若jdbc驱动版本过低可能也会报错
                String user="root";
                String password="honggetyf769";
                connection =DriverManager.getConnection(url,user,password);
                System.out.println("创建连接成功");
                //设置非自动commit
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                //？？？？尝试创建表？？？？？？？？？？
                String sql="create table test(id int,password varchar(20))";
                //3.写sql
                //根据数据库实际的表名写SQL语句
//            String sql="select * from userinfo";
                //4.得到statement对象执行sql
                //statement = connection.prepareStatement(sql);
                sql="insert into student value('Tom',16)";
                statement.addBatch(sql);
                sql="insert into student value('Jim',17)";
                statement.addBatch(sql);
                statement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
                // int a = statement.executeUpdate();
                //5.得到结果集
//                rs = statement.executeQuery();
//                //6.处理结果集
//                while(rs.next()){
//                    System.out.println(rs.getInt(2));
//                    System.out.println(rs.getString(1));
////                System.out.println(rs.getString(3));
//                }

//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //7.关闭
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(statement!=null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("关闭成功");
            }
        }

}

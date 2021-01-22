import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class MainTest<Public> {
    public static void main(String[] args) {
//连接mysql数据库
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            //1,加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //2.创建连接
            //此处按照实际的数据库名称和账号密码进行修改
            //格式为jdbc:mysql://127.0.0.1:3306/数据库名称?useSSL=true&characterEncoding=utf-8&user=账号名&password=密码
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/try1?useSSL=true&characterEncoding=utf-8&user=root&password=honggetyf769");
            String url = "jdbc:mysql://localhost:3306/covid-19-form?characterEncoding=utf-8&serverTimezone=UTC";//使用最新版本的mysql jdbc驱动需要写时间区；若jdbc驱动版本过低可能也会报错
            String user = "root";
            String password = "honggetyf769";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("创建连接成功");
            String data = "";
            //将uri中内容转话为字符串
//        System.out.println(GetApiToString("https://covid-api.mmediagroup.fr/v1/cases"));//可，已存入项目目录下txt文件，先用text代替
            //将存在项目目录下的text文件转化为字符串
            //System.out.println(txt2String("Covid-19API.txt"));
            data = txt2String("Covid-19API.txt");
            //创建数组存中国（China）、美国(US)、英国(United Kingdom)、日本(Japan)
            String[] county={"China","US","United Kingdom","Japan"};
//将字符串用fastJson转换为Json对象
            JSONObject json = JSON.parseObject(data);//存为JSONObject而不是JSON
//找到不同国家的key
            for(int i=0;i<county.length;i++){
            JSONObject JsonCountry = json.getJSONObject(county[i]);
//为语句添加事务
                connection.setAutoCommit(false);
                statement = connection.createStatement();
            String CountryName=county[i];
            for (Iterator iter = JsonCountry.keySet().iterator(); iter.hasNext(); ) {//?????为何
                String key = (String) iter.next();
                //System.out.println(key); 可
                JSONObject x = JsonCountry.getJSONObject(key);
                if (key == "All") {
                    AllBean allBean = JSON.toJavaObject(x, AllBean.class);
                    long confirmed = allBean.getConfirmed();
                    long recovered = allBean.getRecovered();
                    long deaths = allBean.getDeaths();
                    String country = allBean.getCountry();
                    long population = allBean.getPopulation();
                    long sq_km_area = allBean.getSq_km_area();
                    double life_expectancy = allBean.getLife_expectancy();
                    String elevation_in_meters = allBean.getElevation_in_meters();
                    String continent = allBean.getContinent();
                    String Abbreviation = allBean.getAbbreviation();
                    String location = allBean.getLocation();
                    long iso = allBean.getIso();
                    String capital_city = allBean.getCapital_city();

                    String sql = "insert into countryall values ('" + CountryName + "&All'," + confirmed + "," + recovered + "," + deaths + ",'" + country + "'," + population + "," + sq_km_area + "," + life_expectancy + ",'" + elevation_in_meters + "','" + continent + "','" + Abbreviation + "','" + location + "'," + iso + ",'" + capital_city + "')";
                    System.out.println(sql);
                    statement.addBatch(sql);
                    statement.executeBatch();
//5.得到结果集（无需结果集？？？）


                } else {
                    ProvinceBean provinceBean = JSON.toJavaObject(x, ProvinceBean.class);
                    String lat = provinceBean.getLat();
                    String longitude = provinceBean.getLongitude();
                    long confirmed = provinceBean.getConfirmed();
                    long recovered = provinceBean.getRecovered();
                    long deaths = provinceBean.getDeaths();
                    String updated = provinceBean.getUpdated();
                    if(CountryName=="United Kingdom"){
                        CountryName="uk";
                    }
                    String sql = "insert into "+CountryName+" values('"+key+"','"+lat+"','"+longitude+"',"+confirmed+","+recovered+","+deaths+",'"+updated+"')";
                    System.out.println(sql);
                    statement.addBatch(sql);
                    statement.executeBatch();
//5.得到结果集

//                System.out.println(key+",Long : "+provinceBean.getLong());可
                }
            }
                connection.commit();
                connection.setAutoCommit(true);
            }
        }catch (SQLException e) {
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


    //将httpUrl中的数据转换为字符串   “用到了HttpComponent”
    public static String GetApiToString(String Uri){
        String data="";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(Uri);//"https://covid-api.mmediagroup.fr/v1/cases"
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity=response.getEntity();
//        try {
//            System.out.println("网页内容是："+ EntityUtils.toString(entity,"utf-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            data= EntityUtils.toString(entity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    //将项目下的txt文件读为字符串
        public static String txt2String(String PathName){
        File file =new File(PathName);
            StringBuilder result = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                String s = null;
                while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    result.append(System.lineSeparator()+s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return result.toString();
        }
}

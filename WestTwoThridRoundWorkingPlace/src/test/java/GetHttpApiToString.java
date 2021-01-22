import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetHttpApiToString {
    public static void main(String[] args) {//可作为一个方法类被引用导入txt文档
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://covid-api.mmediagroup.fr/v1/cases");
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity=response.getEntity();
        try {
            System.out.println("网页内容是："+EntityUtils.toString(entity,"utf-8"));
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
    }
}

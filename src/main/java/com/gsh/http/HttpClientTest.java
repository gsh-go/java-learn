package com.gsh.http;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import java.nio.charset.Charset;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/18 17:08
 * @Description:
 */
public class HttpClientTest {
    public static boolean httpPostWithXML(String xml, String url) {
        boolean isSuccess = false;

        HttpPost post = null;
        try {
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            post.setHeader("cookie", "ASP.NET_SessionId=ir2jhggkqjtrfzwq35p0vlty; bxmssmemberinfo=userinfo=006C00690064005000360050004300710030002B0070003500470047007900520033003200410044006200500079007800620069002F0072002F0063006500450044004E0066005000690070002B005900320057004900360049004A00560039006C006100700057004D0076006300620043002F006D004100530047006F0053; Hm_lvt_26fad0fa647fab8971d4c110d92f6535=1539853015; Hm_lpvt_26fad0fa647fab8971d4c110d92f6535=1539853029; Hm_lvt_3de671d246685f9f53214b84803799b6=1539853015; Hm_lpvt_3de671d246685f9f53214b84803799b6=1539853029; __cfduid=d7a62d64e63411f56c54fff79f9d80da71539853031; SERVERID=ef04198ebfec80cb463601da065c60a1|1539853074|1539853015");
            post.setHeader("Content-Type", "application/json");

            // 构建消息实体
            StringEntity entity = new StringEntity(xml.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                isSuccess = false;
            } else {
                int retCode = 0;
                String sessendId = "";
                // 返回码中包含retCode及会话Id
                for (Header header : response.getAllHeaders()) {
                    if (header.getName().equals("retcode")) {
                        retCode = Integer.parseInt(header.getValue());
                    }
                    if (header.getName().equals("SessionId")) {
                        sessendId = header.getValue();
                    }
                }


                isSuccess = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (post != null) {
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }
}

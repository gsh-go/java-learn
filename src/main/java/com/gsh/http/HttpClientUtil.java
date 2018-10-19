package com.gsh.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 8:29
 * @Description:
 */
public class HttpClientUtil {
    public String doPost(String url, String xml) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Connection", "Close");
            httpPost.setHeader("cookie", "ASP.NET_SessionId=ir2jhggkqjtrfzwq35p0vlty; bxmssmemberinfo=userinfo=006C00690064005000360050004300710030002B0070003500470047007900520033003200410044006200500079007800620069002F0072002F0063006500450044004E0066005000690070002B005900320057004900360049004A00560039006C006100700057004D0076006300620043002F006D004100530047006F0053; Hm_lvt_26fad0fa647fab8971d4c110d92f6535=1539853015; Hm_lpvt_26fad0fa647fab8971d4c110d92f6535=1539853029; Hm_lvt_3de671d246685f9f53214b84803799b6=1539853015; Hm_lpvt_3de671d246685f9f53214b84803799b6=1539853029; __cfduid=d7a62d64e63411f56c54fff79f9d80da71539853031; SERVERID=ef04198ebfec80cb463601da065c60a1|1539853074|1539853015");
          //  httpPost.setHeader("Content-Type", "application/json");
            //设置参数
            StringEntity entity = new StringEntity(xml.toString(), Charset.forName("UTF-8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

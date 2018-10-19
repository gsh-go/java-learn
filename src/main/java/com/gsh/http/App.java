package com.gsh.http;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


/**
 * @Author: gsh
 * @Date: Created in 2018/10/18 17:18
 * @Description:
 */
public class App {
    private static String URL = "https://www.runff.com/html/live/s1590.html?client=partner&isbxapimode=true&_xmltime=1539853073061.0.6301532038513451";
    private static String URI = "http://cdn.pic.runff.com";
    private static String FILEPATH = "E:\\image\\";


    public static void main(String[] args) throws DocumentException {
        String UserId ;
        for (int id = 104; id < 10000; id++) {
            UserId = "0000" + id;
            UserId = "A" + UserId.substring(UserId.length() - 5, UserId.length());
            System.out.println(UserId);

            String xml_cancel = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>follow</Action><Data>" +
                    "<action>cancel</action>" +
                    "<number>" + UserId + "</number><faceurl></faceurl>\n" +
                    "<name></name><id></id></Data></BxMessage>";

            /**根据号码查询fid*/
            String xml_number = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>follow</Action><Data>" +
                    "<action>add</action>" +
                    "<number>" + UserId + "</number>" +
                    "<faceurl></faceurl><name></name><id>0</id></Data></BxMessage>";
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String result_id = httpClientUtil.doPost(URL, xml_number);
            Document document = DocumentHelper.parseText(result_id.trim().substring(1));
            Element rootElt = document.getRootElement();
            String fid = rootElt.element("Data").elementTextTrim("id");

            if (fid != null) {
                String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>getPhotoList</Action><Data>" +
                        "<fid>" + fid + "</fid>" +
                        "<number>" + UserId + "</number>" +
                        "<minpid>0</minpid><time>Thu Oct 18 2018 16:57:09 GMT+0800 (中国标准时间)</time><sign>false</sign><pagesize>100</pagesize></Data></BxMessage>";
                String result = httpClientUtil.doPost(URL, xml);


                /**从document解析出照片地址*/
                document = DocumentHelper.parseText(result.trim().substring(1));
                rootElt = document.getRootElement(); // 获取根节点
                String list = rootElt.element("Data").elementTextTrim("list");
                JsonParser parse = new JsonParser();
                JsonArray jsonArray = ((JsonArray) parse.parse(list));
                String name;
                String path;
                ImageDownload download = new ImageDownload();
                for (int i = 0; i < jsonArray.size(); i++) {
                    name = jsonArray.get(i).getAsJsonObject().get("id").toString().replace("\"", "");
                    path = jsonArray.get(i).getAsJsonObject().get("big").toString().replace("\"", "");
                    download.downloadPicture(URI + path, FILEPATH + UserId + "\\" + name + ".jpg");
                }
                parse.parse(list);
            }

        }


    }
}

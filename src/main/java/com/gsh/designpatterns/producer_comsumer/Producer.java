package com.gsh.designpatterns.producer_comsumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.gsh.http.HttpClientUtil;
import com.gsh.http.ImageDownload;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 14:38
 * @Description:
 */
public class Producer {
    private static String URL = "https://www.runff.com/html/live/s1590.html?client=partner&isbxapimode=true&_xmltime=1539853073061.0.6301532038513451";

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private final ItemQueue queue;

    private final String name;

    private int begin;

    private int end;

    private String type;

    public Producer(String name, ItemQueue queue, String type, int begin, int end) {
        this.name = name;
        this.queue = queue;
        this.type = type;
        this.begin = begin;
        this.end = end;
    }

    /**
     * Put item in the queue
     */
    public void produce() throws InterruptedException, DocumentException {
        String UserId;
        for (int id = begin; id < end; id++) {
            UserId = "0000" + id;
            UserId = type + UserId.substring(UserId.length() - 5, UserId.length());


            /**关注号码并查询fid*/
            String xml_number = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>follow</Action><Data>" +
                    "<action>add</action>" +
                    "<number>" + UserId + "</number>" +
                    "<faceurl></faceurl><name></name><id>0</id></Data></BxMessage>";
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String result_id = httpClientUtil.doPost(URL, xml_number);
            Document document = DocumentHelper.parseText(result_id.trim().substring(1));
            Element rootElt = document.getRootElement();
            String fid = rootElt.element("Data").elementTextTrim("id");

            /*取消关注*/
            String xml_cancel = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>follow</Action><Data>" +
                    "<action>cancel</action>" +
                    "<number>" + UserId + "</number><faceurl></faceurl>\n" +
                    "<name></name><id>" + fid + "</id></Data></BxMessage>";
            httpClientUtil.doPost(URL, xml_cancel);

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
                String picId;
                String path;
                for (int i = 0; i < jsonArray.size(); i++) {
                    picId = jsonArray.get(i).getAsJsonObject().get("id").toString().replace("\"", "");
                    path = jsonArray.get(i).getAsJsonObject().get("big").toString().replace("\"", "");
                    Item item = new Item(UserId, picId, path);
                    queue.put(item);
                    /*Thread.sleep(random.nextInt(2000))*/
                    //   download.downloadPicture(URI + path, FILEPATH + UserId + "\\" + name + ".jpg");
                }
                LOGGER.info("生产者正在解析RUNNER [{}] 的照片 ", UserId);

                parse.parse(list);
            }

        }


    }
}

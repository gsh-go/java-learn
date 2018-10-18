package com.gsh.http;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/18 17:18
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        String url = "https://www.runff.com/html/live/s1590.html?client=partner&isbxapimode=true&_xmltime=1539853073061.0.6301532038513451";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BxMessage><AppId>BxAPI</AppId><Type>1</Type><Action>getPhotoList</Action><Data><fid>3044319</fid><number>A00002</number><minpid>0</minpid><time>Thu Oct 18 2018 16:57:09 GMT+0800 (中国标准时间)</time><sign>false</sign><pagesize>100</pagesize></Data></BxMessage>";
        HttpClientTest.httpPostWithXML(xml,url);
    }
}

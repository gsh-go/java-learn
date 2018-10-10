package com.gsh.service;


/**
 * 双公示
 */
public class ProxyService {
    private static final String TEST_KEY = "22FDAE6C0023481E71A49587FC23FFFC";
    private static final String USER_NAME = "zhqggsylhhxxzx";


    public static void main(String[] args) {

        try {
            String arguments;
            String WSDL_LOCATION;
            String md5;
            String reqMsg;
            String result;
            RPCInvoker invoker;


            /**调用双公示服务*/
        /*    WSDL_LOCATION = "http://59.255.188.78/service?wsdl";
            String id = "0001";
            arguments = "<arguments>" +
                    "<code_type>entname</code_type>" +
                    "<code>滁州天龙汽车运输有限公司</code>" +
                    "</arguments>";
            md5 = SicUtils.toMD5(arguments + TEST_KEY);
            reqMsg = "<request>" +
                    "<type>SIC_IF_" + id + "</type>" +
                    "<identity>" + USER_NAME + "</identity>" +
                    "<security>" + md5 + "</security>"
                    + arguments + "</request>";
            RPCInvoker invoker = new RPCInvoker(WSDL_LOCATION, "service", new Object[]{reqMsg});
            String result = invoker.invoke().toString();

            System.out.println(result);*/

            WSDL_LOCATION = "http://59.255.188.8/services/iacinfo?wsdl";


            arguments = "中国邮政广告有限责任公司100000000011916";
            md5 = SicUtils.toMD5(arguments + TEST_KEY);
            reqMsg = "<request>" +
                    "<identity>" + USER_NAME + "</identity>" +
                    "<security>" + md5 + "</security>" +
                    "<entname>中国邮政广告有限责任公司</entname>" +
                    "<regno>100000000011916</regno>" +
                    "<entstatus>1</entstatus><ver>0</ver></request>";
            invoker = new RPCInvoker(WSDL_LOCATION, "queryIACEntInfo", new Object[]{reqMsg});
            result = invoker.invoke().toString();
            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

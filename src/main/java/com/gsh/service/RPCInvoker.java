package com.gsh.service;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class RPCInvoker {

    private String wsdl;
    private String method;
    private Object[] params;

    public RPCInvoker(String wsdl, String method, Object[] params){
        this.wsdl = wsdl;
        this.method = method;
        this.params = params;
    }


    public Object invoke() throws Exception{
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient(wsdl);
        Object[] result = client.invoke(method, params);
        return result[0];
    }
}

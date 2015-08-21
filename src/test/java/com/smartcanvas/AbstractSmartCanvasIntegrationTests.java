package com.smartcanvas;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.jose4j.lang.JoseException;

import java.io.IOException;

public class AbstractSmartCanvasIntegrationTests {

    protected Smartcanvas smartcanvas;
    protected static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    protected static final JsonFactory JSON_FACTORY = new JacksonFactory();
    protected static final String CLIENT_ID = "kMbmHrwcr91OsJQN0nbX2TVRxOxo9AQH ";
    protected static final String CLIENT_SECRET = "ouGCDY37Z03ogMy3Q4LMIysbLHdvdKS0";
  //  protected HttpExecuteInterceptor executeInterceptor;


    public AbstractSmartCanvasIntegrationTests() throws JoseException {

        smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET);
    }


}
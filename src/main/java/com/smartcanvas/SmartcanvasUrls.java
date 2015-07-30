package com.smartcanvas;

import com.google.api.client.http.GenericUrl;

public class SmartcanvasUrls {

    private static final String DEFAULT_ROOT_URL = "https://api-sandbox.smartcanvas.com/";
    private static final String DEFAULT_SANDBOX_ROOT_URL = "https://api-sandbox.smartcanvas.com/";
    
	public static class CardApiUrl extends GenericUrl {
        
    	public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String url = "%s%s/cards";
        final static String urlUpdate = "%s%s/cards/%s";

        public CardApiUrl(boolean useSandbox) {
            super(String.format(url, useSandbox ? DEFAULT_SANDBOX_ROOT_URL : DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH));
        }
        
        public CardApiUrl(boolean useSandbox, String id) {
        	super(String.format(urlUpdate, useSandbox ? DEFAULT_SANDBOX_ROOT_URL : DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, id));
        }
	
    }	
	
}

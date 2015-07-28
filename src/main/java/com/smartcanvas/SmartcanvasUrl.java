package com.smartcanvas;

import com.google.api.client.http.GenericUrl;

public class SmartcanvasUrl extends GenericUrl {

    private static final String DEFAULT_ROOT_URL = "https://api.smartcanvas.com/";
    private static String rootUrl = DEFAULT_ROOT_URL;	

    
	public static class CardApiUrl extends GenericUrl {
        
    	public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String url = "%s%s/cards";
        final static String urlUpdate = "%s%s/cards/%s";
        
        public CardApiUrl() {
            super(String.format(url, rootUrl, DEFAULT_SERVICE_PATH));
        }    
        
        public CardApiUrl(String id) {
        	super(String.format(urlUpdate, rootUrl, DEFAULT_SERVICE_PATH,id));
        }
	
    }	
	
}

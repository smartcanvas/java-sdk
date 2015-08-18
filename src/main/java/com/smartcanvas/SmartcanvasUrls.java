package com.smartcanvas;

import com.google.api.client.http.GenericUrl;

public class SmartcanvasUrls {

    private static final String DEFAULT_ROOT_URL = "https://api.smartcanvas.com/";
    private static final String DEFAULT_SANDBOX_ROOT_URL = "https://api-sandbox.smartcanvas.com/";
    private static final String DIRECT_URL = "http://card-dot-d1-dev.appspot.com/api/";
    
	public static class CardApiUrl extends GenericUrl {
        
    	public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String url = "%s%s/cards";
        final static String urlUpdate = "%s%s/cards/%s";

        public CardApiUrl(boolean directUrl) {
            super(String.format(url, directUrl ? DIRECT_URL: DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH));
        }

        public CardApiUrl(boolean directUrl, String id) {
        	super(String.format(urlUpdate, directUrl  ?  DIRECT_URL : DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, id));
        }
    }

    public static class CardModerationUrl extends GenericUrl {
        
        public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String urlUpdate = "%s%s/cards/%s/moderations";
        
        public CardModerationUrl(boolean useSandbox, String cardId) {
            super(String.format(urlUpdate, useSandbox ? DEFAULT_SANDBOX_ROOT_URL : DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, cardId));
        }

        public static CardModerationUrl of(boolean useSandbox, Long cardId) {
            return new CardModerationUrl(useSandbox, String.valueOf(cardId));
        }

    }


}

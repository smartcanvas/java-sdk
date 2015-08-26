package com.smartcanvas;

import com.google.api.client.http.GenericUrl;

public class SmartcanvasUrls {

    private static final String DEFAULT_ROOT_URL = "https://api.smartcanvas.com/";

	public static class CardApiUrl extends GenericUrl {

    	public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String url = "%s%s/cards";
        final static String urlUpdate = "%s%s/cards/%s";

        public CardApiUrl() {
            super(String.format(url, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH));
        }

        public CardApiUrl(final String directUrl) {
            super(String.format(url, directUrl, DEFAULT_SERVICE_PATH));
         }

        public CardApiUrl(final String directUrl, final boolean useDirectUrl) {
            super(String.format(url, directUrl, DEFAULT_SERVICE_PATH));
        }

        public CardApiUrl(final String put, final String id) {
        	super(String.format(urlUpdate, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, id));
        }

        public CardApiUrl(final String put, final String id, final String directUrl) {
            super(String.format(urlUpdate, directUrl, DEFAULT_SERVICE_PATH, id));
        }
    }

    public static class CardModerationUrl extends GenericUrl {

        public static final String DEFAULT_SERVICE_PATH = "card/v1";
        final static String urlUpdate = "%s%s/cards/%s/moderations";

        public CardModerationUrl(final String cardId) {
            super(String.format(urlUpdate, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, cardId));
            System.out.println(DEFAULT_ROOT_URL);
        }

        public CardModerationUrl(final String directUrl, final String cardId) {
            super(String.format(urlUpdate, directUrl, DEFAULT_SERVICE_PATH, cardId));
            System.out.println(directUrl);
        }

        public static CardModerationUrl of(final String directUrl, final Long cardId) {
            return new CardModerationUrl(directUrl, String.valueOf(cardId));
        }

    }
}

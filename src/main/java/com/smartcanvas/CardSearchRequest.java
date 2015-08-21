package com.smartcanvas;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.*;
import com.smartcanvas.SmartcanvasUrls.CardApiUrl;
import com.smartcanvas.model.Card.CardStatus;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class CardSearchRequest extends CardApiUrl {

    @Key("q")
    private final String query;
    @Key
    private final CardStatus status;
    @Key
    private final String locale;
    @Key
    private final Integer limit;
    @Key
    private final Integer offset;
    @Key
    private final String initDate;
    @Key
    private final String endDate;
    @Key
    private final Integer maxAge;
    @Key
    private final String categories;
    @Key
    private final String metaTags;
    @Key
    private final String mnemonic;    
    @Key
    private final String authorIds;
    @Key
    private final String communityIds;
    @Key
    private final String providerIds;
    @Key
    private final Double decayment;
    @Key
    private final String fields;
    @Key
    private final String embed;
    @Key
    private final Object jsonExtendedData;

    private final static Joiner COMMA_JOINER = Joiner.on(',');


    private CardSearchRequest(String directUrl, String query, CardStatus status, String locale, Integer limit,
            Integer offset, String initDate, String endDate, Integer maxAge, Set<String> categories,
            Set<String> metaTags, Set<String> authorIds, Set<String> communityIds, Set<String> providerIds,
            Double decayment, Set<String> fields, Set<String> embed, Object jsonExtendedData, String mnemonic) {

        this.query = query;
        this.status = status;
        this.locale = locale;
        this.limit = limit;
        this.offset = offset;
        this.initDate = initDate;
        this.endDate = endDate;
        this.maxAge = maxAge;
        this.categories = join(categories);
        this.metaTags = join(metaTags);
        this.authorIds = join(authorIds);
        this.communityIds = join(communityIds);
        this.providerIds = join(providerIds);
        this.decayment = decayment;
        this.fields = join(fields);
        this.embed = join(embed);
        this.jsonExtendedData = jsonExtendedData;
        this.mnemonic = mnemonic;
    }

    private static String join(Iterable<String> joinable) {
        if (joinable == null)
            return null;
        return COMMA_JOINER.join(joinable);
    }

    public static CardSearchRequestBuilder builder() {
        return new CardSearchRequestBuilder();
    }

    public static CardSearchRequestBuilder builder(String directUrl) {
        return new CardSearchRequestBuilder(directUrl);
    }

    public static CardSearchRequestBuilder builder(String directUrl, JsonFactory jsonFactory) {
        return new CardSearchRequestBuilder(directUrl, jsonFactory);
    }

    public static class CardSearchRequestBuilder {

        private String query;

        private CardStatus status;

        private String locale;

        private Integer limit;

        private Integer offset;

        private String initDate;

        private String endDate;

        private Integer maxAge;

        private Set<String> categories;

        private Set<String> metaTags;

        private Set<String> authorIds;

        private Set<String> communityIds;

        private Set<String> providerIds;

        private Double decayment;

        private Set<String> fields;

        private Set<String> embed;

        private String jsonExtendedData;
        
        private String mnemonic;

        private String directUrl;

        private JsonFactory jsonFactory;

        private CardSearchRequestBuilder(String directUrl) {
            this.directUrl = directUrl;
            System.out.println("DirectURL: "+ this.directUrl);
        }

        private CardSearchRequestBuilder() {
            this.directUrl = new CardApiUrl().toString();
            System.out.println("this.directUrl sem parametro(default): "+this.directUrl);
        }

        public CardSearchRequestBuilder(String directUrl, JsonFactory jsonFactory) {
            this.jsonFactory = jsonFactory;
            this.directUrl = directUrl;
        }

        public CardSearchRequestBuilder query(String query) {
            this.query = query;
            return this;
        }

        public CardSearchRequestBuilder status(CardStatus status) {
            this.status = status;
            return this;
        }

        public CardSearchRequestBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public CardSearchRequestBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public CardSearchRequestBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public CardSearchRequestBuilder initDate(DateTime initDate) {
            String stringDate = initDate.toString();
            this.initDate = stringDate;
            return this;
        }

        public CardSearchRequestBuilder endDate(DateTime endDate) {
            String stringDate = endDate.toString();
            this.endDate = stringDate;
            return this;
        }

        public CardSearchRequestBuilder maxAge(Integer maxAgeString) {
            this.maxAge = maxAgeString;
            return this;
        }

        public CardSearchRequestBuilder categories(String... categoriesString) {
            if (this.categories == null)
                this.categories = Sets.newHashSet();
            this.categories.addAll(Arrays.asList(categoriesString));
            return this;
        }

        public CardSearchRequestBuilder metaTags(String... metaTagsString) {
            if (this.metaTags == null)
                this.metaTags = Sets.newHashSet();
            this.metaTags.addAll(Arrays.asList(metaTagsString));
            return this;
        }

        public CardSearchRequestBuilder authorIds(String... authors) {
            if (this.authorIds == null)
                this.authorIds = Sets.newHashSet();

            this.authorIds.addAll(Arrays.asList(authors));
            return this;
        }

        public CardSearchRequestBuilder communityIds(String... communityId) {
            if (this.communityIds == null)
                this.communityIds = Sets.newHashSet();
            this.communityIds.addAll(Arrays.asList(communityId));
            return this;
        }

        public CardSearchRequestBuilder providerIds(String... providerId) {
            if (this.providerIds == null)
                this.providerIds = Sets.newHashSet();
            this.providerIds.addAll(Arrays.asList(providerId));
            return this;
        }

        public CardSearchRequestBuilder decayment(Double decayment) {
            this.decayment = decayment;
            return this;
        }

        public CardSearchRequestBuilder mnemonic(String mnemonic) {
            this.mnemonic = mnemonic;
            return this;
        }
        
        public CardSearchRequestBuilder fields(String... fieldsString) {
            if (this.fields == null)
                this.fields = Sets.newHashSet();
            this.fields.addAll(Arrays.asList(fieldsString));
            return this;
        }

        public CardSearchRequestBuilder embed(String... embedString) {
            if (this.embed == null)
                this.embed = Sets.newHashSet();
            this.embed.addAll(Arrays.asList(embedString));
            return this;
        }

        public CardSearchRequestBuilder jsonExtendedData(Object jsonExtendedData) throws IOException {
            Preconditions.checkNotNull(jsonExtendedData);

            if (jsonExtendedData instanceof String ) {
                this.jsonExtendedData = (String) jsonExtendedData;
            } else {
                this.jsonExtendedData = jsonFactory.toString(jsonExtendedData);
            }

            return this;
        }

        private CardSearchRequestBuilder compareDate(String initDate, String endDate) {
            if (initDate == null || endDate == null || (initDate.compareTo(endDate) != 1)) {
                return this;
            } else
                throw new IllegalStateException("initDate must be equal or less than endDate");
        }

        public CardSearchRequest build() {

            compareDate(initDate, endDate);
            if ((initDate != null || endDate != null) && (maxAge != null)) {
                throw new IllegalStateException("maxAge can't be invoked with initDate or/and endDate");
            }

            return new CardSearchRequest(this.directUrl, this.query, this.status, this.locale, this.limit,
                    this.offset, this.initDate, this.endDate, this.maxAge, this.categories, this.metaTags,
                    this.authorIds, this.communityIds, this.providerIds, this.decayment, this.fields, this.embed,
                    this.jsonExtendedData, this.mnemonic);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("query", query).add("status", status).add("locale", locale)
                    .add("limit", limit).add("offset", offset).add("initDate", initDate).add("endDate", endDate)
                    .add("maxAge", maxAge).add("categories", categories).add("metaTags", metaTags)
                    .add("authorIds", authorIds).add("communityIds", communityIds).add("providerIds", providerIds)
                    .add("decayment", decayment).add("fields", fields).add("embed", embed)
                    .add("jsonExtendedData", jsonExtendedData)
                    .add("mnemonic", mnemonic).toString();
        }


    }
}

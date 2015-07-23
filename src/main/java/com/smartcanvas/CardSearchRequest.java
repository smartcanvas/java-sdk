package com.smartcanvas;

import java.util.List;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;

import com.google.api.client.util.Objects;
import com.smartcanvas.model.Card.CardStatus;

public class CardSearchRequest {


    private final String query;

    private final CardStatus status;

    private final String locale;

    private final Integer limit;

    private final Integer offset;

    private final LocalDate initDate;

    private final LocalDate endDate;

    private final Integer maxAge;

    private final List<String> categories;

    private final List<String> metaTags;
		
    private final List<String> authorIds;

    private final List<String> communityIds;

    private final List<String> providerIds;

    private final Double decayment;

    private final List<String> fields;

    private final List<String> embed;

    private final String jsonExtendedData;

    private CardSearchRequest(String query, CardStatus status, String locale,
                              Integer limit, Integer offset, LocalDate initDate,
                              LocalDate endDate, Integer maxAge,
                              List<String> categories, List<String> metaTags,
                              List<String> authorIds, List<String> communityIds,
                              List<String> providerIds,
                              Double decayment, List<String> fields,
                              List<String> embed, String jsonExtendedData) {

        this.query = query;
        this.status = status;
        this.locale = locale;
        this.limit = limit;
        this.offset = offset;
        this.initDate = initDate;
        this.endDate = endDate;
        this.maxAge = maxAge;
        this.categories = categories;
        this.metaTags = metaTags;
        this.authorIds = authorIds;
        this.communityIds = communityIds;
        this.providerIds = providerIds;
        this.decayment = decayment;
        this.fields = fields;
        this.embed = embed;
        this.jsonExtendedData = jsonExtendedData;
    }

    public static CardSearchRequestBuilder builder(String tenant) {
        return new CardSearchRequestBuilder();
    }

    public static class CardSearchRequestBuilder {

        private static final Pattern SPLITTER_PATTERN = Pattern.compile(",\\s*");


        private CardStatus status;

        private String locale;

        private Integer limit;

        private Integer offset;

        private LocalDate initDate;

        private LocalDate endDate;

        private Integer maxAge;

        private List<String> categories;

        private List<String> metaTags;

        private List<String> authorIds;

        private List<String> communityIds;

        private List<String> providerIds;

        private Double decayment;

        private List<String> fields;

        private List<String> embed;

        private String jsonExtendedData;

        private String query;

        private CardSearchRequestBuilder() {
            //checkArgument(!Strings.isNullOrEmpty(tenant));
        }

        public CardSearchRequestBuilder query(String query) {
            this.query = query;
            return this;
        }

        public CardSearchRequestBuilder status(String statusString) {
            //this.status = statusString.map(String::toUpperCase).map(CardStatus::valueOf);
            return this;
        }

        public CardSearchRequestBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public CardSearchRequestBuilder limit(String limitString) {
            //limit = limitString.map(Integer::parseInt);
            return this;
        }

        public CardSearchRequestBuilder offset(String offsetString) {
            //this.offset = offsetString.map(Integer::parseInt);
            return this;
        }

        public CardSearchRequestBuilder initDate(String initDateString) {
            //this.initDate = initDateString.map(LocalDate::parse);
            return this;
        }

        public CardSearchRequestBuilder endDate(String endDateString) {
            //this.endDate = endDateString.map(LocalDate::parse);
            return this;
        }

        public CardSearchRequestBuilder maxAge(String maxAgeString) {
            //this.maxAge = maxAgeString.map(Integer::parseInt);
            return this;
        }

        public CardSearchRequestBuilder categories(String categoriesString) {
            //this.categories = categoriesString.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder metaTags(String metaTagsString) {
            //this.metaTags = metaTagsString.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder authorIds(String authorId) {
            //this.authorIds = authorId.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder communityIds(String communityId) {
            //this.communityIds = communityId.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder providerIds(String providerId) {
            //this.providerIds = providerId.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder decayment(String decaymentString) {
            //this.decayment = decaymentString.map(Double::parseDouble);
            return this;
        }

        public CardSearchRequestBuilder fields(String fieldsString) {
            //this.fields = fieldsString.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder embed(String embedString) {
            //this.embed = embedString.map(STRING_LIST_MAPPER);
            return this;
        }

        public CardSearchRequestBuilder jsonExtendedData(String jsonExtendedData) {
            this.jsonExtendedData = jsonExtendedData;
            return this;
        }

        public CardSearchRequest build() {
           return new CardSearchRequest(this.query, this.status, this.locale, this.limit, this.offset, this.initDate,
               this.endDate, this.maxAge, this.categories, this.metaTags, this.authorIds, this.communityIds, this.providerIds,
               this.decayment, this.fields, this.embed, jsonExtendedData);
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("query", query)
                .add("status", status)
                .add("locale", locale)
                .add("limit", limit)
                .add("offset", offset)
                .add("initDate", initDate)
                .add("endDate", endDate)
                .add("maxAge", maxAge)
                .add("categories", categories)
                .add("metaTags", metaTags)
                .add("authorIds", authorIds)
                .add("communityIds", communityIds)
                .add("providerIds", providerIds)
                .add("decayment", decayment)
                .add("fields", fields)
                .add("embed", embed)
                .add("jsonExtendedData", jsonExtendedData)
                .toString();
    }
}

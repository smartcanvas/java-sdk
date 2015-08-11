package com.smartcanvas.model;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Value;
import com.smartcanvas.SmartcanvasUrls;

public class ModerationRequest {

    public enum CardModerationResolution {
        @Value("APPROVED")
        APPROVED,
        @Value("REJECTED")
        REJECTED,
        @Value("PENDING")
        PENDING
    }

    @Key
    private Long cardId;
    @Key
    private CardModerationResolution resolution;

    private ModerationRequest(Long cardId, CardModerationResolution resolution) {
        super();
        this.cardId = cardId;
        this.resolution = resolution;
    }

    public static ModerationRequest approvalOf(Long cardId) {
        return new ModerationRequest(cardId, CardModerationResolution.APPROVED);
    }

    public static ModerationRequest approvalOf(String cardId) {
        return approvalOf(Long.valueOf(cardId));
    }
    
    public static ModerationRequest rejectionOf(Long cardId) {
        return new ModerationRequest(cardId, CardModerationResolution.REJECTED);
    }

    public static ModerationRequest rejectionOf(String cardId) {
        return rejectionOf(Long.valueOf(cardId));
    }

    public static ModerationRequest pendingOf(Long cardId) {
        return new ModerationRequest(cardId, CardModerationResolution.PENDING);
    }

    public static ModerationRequest pendingOf(String cardId) {
        return pendingOf(Long.valueOf(cardId));
    }
    
    public GenericUrl url(boolean useSandbox) {
        return new SmartcanvasUrls.CardModerationUrl(useSandbox, String.valueOf(this.cardId));
    }

    @Override
    public String toString() {
        return Objects
            .toStringHelper(this)
            .add("cardId", cardId)
            .add("resolution", resolution)
            .toString();
    }
}
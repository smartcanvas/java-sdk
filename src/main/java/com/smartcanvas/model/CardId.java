package com.smartcanvas.model;

import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;

public class CardId {

    @Key
    private String id;

    @Key
    private String mnemonic;

    public String id() {
        return id;
    }

    public String mnemonic() {
        return mnemonic;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", this.id).add("mnemonic", this.mnemonic).toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mnemonic == null) ? 0 : mnemonic.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CardId other = (CardId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (mnemonic == null) {
            if (other.mnemonic != null)
                return false;
        } else if (!mnemonic.equals(other.mnemonic))
            return false;
        return true;
    }
}

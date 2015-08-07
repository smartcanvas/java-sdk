package com.smartcanvas;

import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;

/**
 * Created by gmoneda on 07/08/15.
 */
public class JsonExtendedData {

    @Key
    private String name = null;
    @Key
    private String address = null;
    @Key
    private String country = null;
    @Key
    private String website = null;
    @Key
    private String phone = null;
    @Key
    private String logoImgUrl = null;
    @Key
    private String contactName = null;
    @Key
    private String contactJobTitle = null;
    @Key
    private String businessDescription = null;
    @Key
    private String memberOf = null;
    @Key
    private String certifications = null;
    @Key
    private String yearOfEstablishment = null;
    @Key
    private Integer percBusinessOwnedByWomen = null;
    @Key
    private Boolean isManagedControledByWomen = null;
    @Key
    private Integer numPermEmployee = null;
    @Key
    private Integer numFemaleEmployee = null;


    public JsonExtendedData() {
        super();
    }

    public JsonExtendedData(String name,
                            String address, String country, String website, String phone, String logoImgUrl,
                            String contactName, String contactJobTitle, String businessDescription, String memberOf,
                            String certifications, String yearOfEstablishment, Integer percBusinessOwnedByWomen,
                            Boolean isManagedControledByWomen, Integer numPermEmployee, Integer numFemaleEmployee) {
        super();
        this.name = name;
        this.address = address;
        this.country = country;
        this.website = website;
        this.phone = phone;
        this.logoImgUrl = logoImgUrl;
        this.contactName = contactName;
        this.contactJobTitle = contactJobTitle;
        this.businessDescription = businessDescription;
        this.memberOf = memberOf;
        this.certifications = certifications;
        this.yearOfEstablishment = yearOfEstablishment;
        this.percBusinessOwnedByWomen = percBusinessOwnedByWomen;
        this.isManagedControledByWomen = isManagedControledByWomen;
        this.numPermEmployee = numPermEmployee;
        this.numFemaleEmployee = numFemaleEmployee;

    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("address", this.address)
                .add("country", this.country)
                .add("website", this.website)
                .add("phone", this.phone)
                .add("logoImgUrl", this.logoImgUrl)
                .add("contactName", this.contactName)
                .add("contactJobTitle", this.contactJobTitle)
                .add("businessDescription", this.businessDescription)
                .add("memberOf", this.memberOf)
                .add("certifications", this.certifications)
                .add("yearOfEstablishment", this.yearOfEstablishment)
                .add("percBusinessOwnedByWomen", this.percBusinessOwnedByWomen)
                .add("isManagedControledByWomen", this.isManagedControledByWomen)
                .add("numPermEmployee", this.numPermEmployee)
                .add("numFemaleEmployee", this.numFemaleEmployee)
                .toString();
    }
}


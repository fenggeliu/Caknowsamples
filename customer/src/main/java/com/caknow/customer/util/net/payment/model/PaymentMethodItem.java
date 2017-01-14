package com.caknow.customer.util.net.payment.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethodItem implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("address_city")
    @Expose
    private Object addressCity;
    @SerializedName("address_country")
    @Expose
    private Object addressCountry;
    @SerializedName("address_line1")
    @Expose
    private Object addressLine1;
    @SerializedName("address_line1_check")
    @Expose
    private Object addressLine1Check;
    @SerializedName("address_line2")
    @Expose
    private Object addressLine2;
    @SerializedName("address_state")
    @Expose
    private Object addressState;
    @SerializedName("address_zip")
    @Expose
    private Object addressZip;
    @SerializedName("address_zip_check")
    @Expose
    private Object addressZipCheck;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("cvc_check")
    @Expose
    private String cvcCheck;
    @SerializedName("dynamic_last4")
    @Expose
    private Object dynamicLast4;
    @SerializedName("exp_month")
    @Expose
    private Long expMonth;
    @SerializedName("exp_year")
    @Expose
    private Long expYear;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("funding")
    @Expose
    private String funding;
    @SerializedName("last4")
    @Expose
    private String last4;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("tokenization_method")
    @Expose
    private Object tokenizationMethod;
    public final static Parcelable.Creator<PaymentMethodItem> CREATOR = new Creator<PaymentMethodItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentMethodItem createFromParcel(Parcel in) {
            PaymentMethodItem instance = new PaymentMethodItem();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.object = ((String) in.readValue((String.class.getClassLoader())));
            instance.addressCity = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressCountry = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressLine1 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressLine1Check = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressLine2 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressState = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressZip = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressZipCheck = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.brand = ((String) in.readValue((String.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.customer = ((String) in.readValue((String.class.getClassLoader())));
            instance.cvcCheck = ((String) in.readValue((String.class.getClassLoader())));
            instance.dynamicLast4 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.expMonth = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.expYear = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.fingerprint = ((String) in.readValue((String.class.getClassLoader())));
            instance.funding = ((String) in.readValue((String.class.getClassLoader())));
            instance.last4 = ((String) in.readValue((String.class.getClassLoader())));
            instance.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
            instance.name = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.tokenizationMethod = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public PaymentMethodItem[] newArray(int size) {
            return (new PaymentMethodItem[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1186462814451139484L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Object getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(Object addressCity) {
        this.addressCity = addressCity;
    }

    public Object getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(Object addressCountry) {
        this.addressCountry = addressCountry;
    }

    public Object getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(Object addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Object getAddressLine1Check() {
        return addressLine1Check;
    }

    public void setAddressLine1Check(Object addressLine1Check) {
        this.addressLine1Check = addressLine1Check;
    }

    public Object getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(Object addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Object getAddressState() {
        return addressState;
    }

    public void setAddressState(Object addressState) {
        this.addressState = addressState;
    }

    public Object getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(Object addressZip) {
        this.addressZip = addressZip;
    }

    public Object getAddressZipCheck() {
        return addressZipCheck;
    }

    public void setAddressZipCheck(Object addressZipCheck) {
        this.addressZipCheck = addressZipCheck;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCvcCheck() {
        return cvcCheck;
    }

    public void setCvcCheck(String cvcCheck) {
        this.cvcCheck = cvcCheck;
    }

    public Object getDynamicLast4() {
        return dynamicLast4;
    }

    public void setDynamicLast4(Object dynamicLast4) {
        this.dynamicLast4 = dynamicLast4;
    }

    public Long getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Long expMonth) {
        this.expMonth = expMonth;
    }

    public Long getExpYear() {
        return expYear;
    }

    public void setExpYear(Long expYear) {
        this.expYear = expYear;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getTokenizationMethod() {
        return tokenizationMethod;
    }

    public void setTokenizationMethod(Object tokenizationMethod) {
        this.tokenizationMethod = tokenizationMethod;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(object);
        dest.writeValue(addressCity);
        dest.writeValue(addressCountry);
        dest.writeValue(addressLine1);
        dest.writeValue(addressLine1Check);
        dest.writeValue(addressLine2);
        dest.writeValue(addressState);
        dest.writeValue(addressZip);
        dest.writeValue(addressZipCheck);
        dest.writeValue(brand);
        dest.writeValue(country);
        dest.writeValue(customer);
        dest.writeValue(cvcCheck);
        dest.writeValue(dynamicLast4);
        dest.writeValue(expMonth);
        dest.writeValue(expYear);
        dest.writeValue(fingerprint);
        dest.writeValue(funding);
        dest.writeValue(last4);
        dest.writeValue(metadata);
        dest.writeValue(name);
        dest.writeValue(tokenizationMethod);
    }

    public int describeContents() {
        return 0;
    }

}
package com.caknow.customer.util.net.payment.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentsPayload implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("account_balance")
    @Expose
    private Long accountBalance;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("default_source")
    @Expose
    private String defaultSource;
    @SerializedName("delinquent")
    @Expose
    private Boolean delinquent;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("livemode")
    @Expose
    private Boolean livemode;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("shipping")
    @Expose
    private Object shipping;
    @SerializedName("sources")
    @Expose
    private PaymentSources paymentSources;
    @SerializedName("subscriptions")
    @Expose
    private Subscriptions subscriptions;
    public final static Parcelable.Creator<PaymentsPayload> CREATOR = new Creator<PaymentsPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentsPayload createFromParcel(Parcel in) {
            PaymentsPayload instance = new PaymentsPayload();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.object = ((String) in.readValue((String.class.getClassLoader())));
            instance.accountBalance = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.created = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.currency = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.defaultSource = ((String) in.readValue((String.class.getClassLoader())));
            instance.delinquent = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.description = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.discount = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.livemode = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
            instance.shipping = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.paymentSources = ((PaymentSources) in.readValue((PaymentSources.class.getClassLoader())));
            instance.subscriptions = ((Subscriptions) in.readValue((Subscriptions.class.getClassLoader())));
            return instance;
        }

        public PaymentsPayload[] newArray(int size) {
            return (new PaymentsPayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3024856318115747604L;

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

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public String getDefaultSource() {
        return defaultSource;
    }

    public void setDefaultSource(String defaultSource) {
        this.defaultSource = defaultSource;
    }

    public Boolean getDelinquent() {
        return delinquent;
    }

    public void setDelinquent(Boolean delinquent) {
        this.delinquent = delinquent;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Object getShipping() {
        return shipping;
    }

    public void setShipping(Object shipping) {
        this.shipping = shipping;
    }

    public PaymentSources getPaymentSources() {
        return paymentSources;
    }

    public void setPaymentSources(PaymentSources paymentSources) {
        this.paymentSources = paymentSources;
    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(object);
        dest.writeValue(accountBalance);
        dest.writeValue(created);
        dest.writeValue(currency);
        dest.writeValue(defaultSource);
        dest.writeValue(delinquent);
        dest.writeValue(description);
        dest.writeValue(discount);
        dest.writeValue(email);
        dest.writeValue(livemode);
        dest.writeValue(metadata);
        dest.writeValue(shipping);
        dest.writeValue(paymentSources);
        dest.writeValue(subscriptions);
    }

    public int describeContents() {
        return 0;
    }

}
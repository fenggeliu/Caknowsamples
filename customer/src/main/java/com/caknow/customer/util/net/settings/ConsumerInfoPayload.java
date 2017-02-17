package com.caknow.customer.util.net.settings;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.garage.Vehicle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumerInfoPayload implements Serializable, Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("lName")
    @Expose
    private String lName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = null;
    @SerializedName("_svcReqs")
    @Expose
    private List<Object> svcReqs = null;
    @SerializedName("profileImg")
    @Expose
    private String profileImg;
    @SerializedName("pubnubChnl")
    @Expose
    private String pubnubChnl;
    @SerializedName("pubnubAuthKey")
    @Expose
    private String pubnubAuthKey;
    @SerializedName("stripeCusToken")
    @Expose
    private String stripeCusToken;
    @SerializedName("ct")
    @Expose
    private int ct;
    @SerializedName("verified")
    @Expose
    private boolean verified;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    public final static Parcelable.Creator<ConsumerInfoPayload> CREATOR = new Creator<ConsumerInfoPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ConsumerInfoPayload createFromParcel(Parcel in) {
            ConsumerInfoPayload instance = new ConsumerInfoPayload();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.fName = ((String) in.readValue((String.class.getClassLoader())));
            instance.lName = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((String) in.readValue((String.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.vehicles, (com.caknow.customer.util.net.garage.Vehicle.class.getClassLoader()));
            in.readList(instance.svcReqs, (java.lang.Object.class.getClassLoader()));
            instance.profileImg = ((String) in.readValue((String.class.getClassLoader())));
            instance.pubnubChnl = ((String) in.readValue((String.class.getClassLoader())));
            instance.pubnubAuthKey = ((String) in.readValue((String.class.getClassLoader())));
            instance.stripeCusToken = ((String) in.readValue((String.class.getClassLoader())));
            instance.ct = ((int) in.readValue((int.class.getClassLoader())));
            instance.verified = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.referralCode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ConsumerInfoPayload[] newArray(int size) {
            return (new ConsumerInfoPayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6018992968010760257L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ConsumerInfoPayload() {
    }

    /**
     *
     * @param id
     * @param profileImg
     * @param phone
     * @param svcReqs
     * @param email
     * @param verified
     * @param referralCode
     * @param pubnubChnl
     * @param stripeCusToken
     * @param lName
     * @param vehicles
     * @param ct
     * @param pubnubAuthKey
     * @param fName
     */
    public ConsumerInfoPayload(String id, String fName, String lName, String phone, String email, List<Vehicle> vehicles, List<Object> svcReqs, String profileImg, String pubnubChnl, String pubnubAuthKey, String stripeCusToken, int ct, boolean verified, String referralCode) {
        super();
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.vehicles = vehicles;
        this.svcReqs = svcReqs;
        this.profileImg = profileImg;
        this.pubnubChnl = pubnubChnl;
        this.pubnubAuthKey = pubnubAuthKey;
        this.stripeCusToken = stripeCusToken;
        this.ct = ct;
        this.verified = verified;
        this.referralCode = referralCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConsumerInfoPayload withId(String id) {
        this.id = id;
        return this;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public ConsumerInfoPayload withFName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public ConsumerInfoPayload withLName(String lName) {
        this.lName = lName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ConsumerInfoPayload withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConsumerInfoPayload withEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ConsumerInfoPayload withVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

    public List<Object> getSvcReqs() {
        return svcReqs;
    }

    public void setSvcReqs(List<Object> svcReqs) {
        this.svcReqs = svcReqs;
    }

    public ConsumerInfoPayload withSvcReqs(List<Object> svcReqs) {
        this.svcReqs = svcReqs;
        return this;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public ConsumerInfoPayload withProfileImg(String profileImg) {
        this.profileImg = profileImg;
        return this;
    }

    public String getPubnubChnl() {
        return pubnubChnl;
    }

    public void setPubnubChnl(String pubnubChnl) {
        this.pubnubChnl = pubnubChnl;
    }

    public ConsumerInfoPayload withPubnubChnl(String pubnubChnl) {
        this.pubnubChnl = pubnubChnl;
        return this;
    }

    public String getPubnubAuthKey() {
        return pubnubAuthKey;
    }

    public void setPubnubAuthKey(String pubnubAuthKey) {
        this.pubnubAuthKey = pubnubAuthKey;
    }

    public ConsumerInfoPayload withPubnubAuthKey(String pubnubAuthKey) {
        this.pubnubAuthKey = pubnubAuthKey;
        return this;
    }

    public String getStripeCusToken() {
        return stripeCusToken;
    }

    public void setStripeCusToken(String stripeCusToken) {
        this.stripeCusToken = stripeCusToken;
    }

    public ConsumerInfoPayload withStripeCusToken(String stripeCusToken) {
        this.stripeCusToken = stripeCusToken;
        return this;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public ConsumerInfoPayload withCt(int ct) {
        this.ct = ct;
        return this;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public ConsumerInfoPayload withVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public ConsumerInfoPayload withReferralCode(String referralCode) {
        this.referralCode = referralCode;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(fName);
        dest.writeValue(lName);
        dest.writeValue(phone);
        dest.writeValue(email);
        dest.writeList(vehicles);
        dest.writeList(svcReqs);
        dest.writeValue(profileImg);
        dest.writeValue(pubnubChnl);
        dest.writeValue(pubnubAuthKey);
        dest.writeValue(stripeCusToken);
        dest.writeValue(ct);
        dest.writeValue(verified);
        dest.writeValue(referralCode);
    }

    public int describeContents() {
        return 0;
    }

}
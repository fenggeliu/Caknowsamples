package com.caknow.customer.util.net.service.quotes;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuoteList implements Serializable, Parcelable
{

    @SerializedName("affiliateId")
    @Expose
    private String affiliateId;
    @SerializedName("quoteId")
    @Expose
    private String quoteId;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("quoteTime")
    @Expose
    private Long quoteTime;
    @SerializedName("serviceFee")
    @Expose
    private String serviceFee;
    @SerializedName("priceDetails")
    @Expose
    private List<PriceDetail> priceDetails = null;
    @SerializedName("averageRating")
    @Expose
    private Long averageRating;
    @SerializedName("reviewCount")
    @Expose
    private Long reviewCount;
    @SerializedName("completedRate")
    @Expose
    private Long completedRate;
    private final static long serialVersionUID = 6970796698377753330L;

    /**
     * No args constructor for use in serialization
     *
     */
    public QuoteList() {
    }

    /**
     *
     * @param priceDetails
     * @param serviceFee
     * @param distance
     * @param averageRating
     * @param quoteId
     * @param quoteTime
     * @param verified
     * @param reviewCount
     * @param longitude
     * @param latitude
     * @param affiliateId
     * @param completedRate
     */
    public QuoteList(String affiliateId, String quoteId, Double longitude, Double latitude, Boolean verified, String distance, Long quoteTime, String serviceFee, List<PriceDetail> priceDetails, Long averageRating, Long reviewCount, Long completedRate) {
        super();
        this.affiliateId = affiliateId;
        this.quoteId = quoteId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.verified = verified;
        this.distance = distance;
        this.quoteTime = quoteTime;
        this.serviceFee = serviceFee;
        this.priceDetails = priceDetails;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.completedRate = completedRate;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public QuoteList withAffiliateId(String affiliateId) {
        this.affiliateId = affiliateId;
        return this;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public QuoteList withQuoteId(String quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public QuoteList withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public QuoteList withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public QuoteList withVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public QuoteList withDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public Long getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime(Long quoteTime) {
        this.quoteTime = quoteTime;
    }

    public QuoteList withQuoteTime(Long quoteTime) {
        this.quoteTime = quoteTime;
        return this;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public QuoteList withServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public List<PriceDetail> getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(List<PriceDetail> priceDetails) {
        this.priceDetails = priceDetails;
    }

    public QuoteList withPriceDetails(List<PriceDetail> priceDetails) {
        this.priceDetails = priceDetails;
        return this;
    }

    public Long getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Long averageRating) {
        this.averageRating = averageRating;
    }

    public QuoteList withAverageRating(Long averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public QuoteList withReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
        return this;
    }

    public Long getCompletedRate() {
        return completedRate;
    }

    public void setCompletedRate(Long completedRate) {
        this.completedRate = completedRate;
    }

    public QuoteList withCompletedRate(Long completedRate) {
        this.completedRate = completedRate;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.affiliateId);
        dest.writeString(this.quoteId);
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
        dest.writeValue(this.verified);
        dest.writeString(this.distance);
        dest.writeValue(this.quoteTime);
        dest.writeString(this.serviceFee);
        dest.writeTypedList(this.priceDetails);
        dest.writeValue(this.averageRating);
        dest.writeValue(this.reviewCount);
        dest.writeValue(this.completedRate);
    }

    protected QuoteList(Parcel in) {
        this.affiliateId = in.readString();
        this.quoteId = in.readString();
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.verified = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.distance = in.readString();
        this.quoteTime = (Long) in.readValue(Long.class.getClassLoader());
        this.serviceFee = in.readString();
        this.priceDetails = in.createTypedArrayList(PriceDetail.CREATOR);
        this.averageRating = (Long) in.readValue(Long.class.getClassLoader());
        this.reviewCount = (Long) in.readValue(Long.class.getClassLoader());
        this.completedRate = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<QuoteList> CREATOR = new Creator<QuoteList>() {
        @Override
        public QuoteList createFromParcel(Parcel source) {
            return new QuoteList(source);
        }

        @Override
        public QuoteList[] newArray(int size) {
            return new QuoteList[size];
        }
    };
}
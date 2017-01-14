package com.caknow.customer.util.net.service.quotes;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Affiliate implements Serializable, Parcelable
{

@SerializedName("affiliateId")
@Expose
private String affiliateId;
@SerializedName("shopName")
@Expose
private String shopName;
@SerializedName("shopImage")
@Expose
private String shopImage;
@SerializedName("address")
@Expose
private String address;
@SerializedName("telephoneNumber")
@Expose
private String telephoneNumber;
@SerializedName("longitude")
@Expose
private Double longitude;
@SerializedName("latitude")
@Expose
private Double latitude;
@SerializedName("verified")
@Expose
private Boolean verified;
@SerializedName("averageRating")
@Expose
private Long averageRating;
@SerializedName("reviewCount")
@Expose
private Long reviewCount;
@SerializedName("completedRate")
@Expose
private Long completedRate;
@SerializedName("quoteId")
@Expose
private String quoteId;
@SerializedName("quoteTime")
@Expose
private Long quoteTime;
@SerializedName("serviceFee")
@Expose
private String serviceFee;
@SerializedName("distance")
@Expose
private String distance;
public final static Parcelable.Creator<Affiliate> CREATOR = new Creator<Affiliate>() {


@SuppressWarnings({
"unchecked"
})
public Affiliate createFromParcel(Parcel in) {
Affiliate instance = new Affiliate();
instance.affiliateId = ((String) in.readValue((String.class.getClassLoader())));
instance.shopName = ((String) in.readValue((String.class.getClassLoader())));
instance.shopImage = ((String) in.readValue((String.class.getClassLoader())));
instance.address = ((String) in.readValue((String.class.getClassLoader())));
instance.telephoneNumber = ((String) in.readValue((String.class.getClassLoader())));
instance.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
instance.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
instance.verified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
instance.averageRating = ((Long) in.readValue((Long.class.getClassLoader())));
instance.reviewCount = ((Long) in.readValue((Long.class.getClassLoader())));
instance.completedRate = ((Long) in.readValue((Long.class.getClassLoader())));
instance.quoteId = ((String) in.readValue((String.class.getClassLoader())));
instance.quoteTime = ((Long) in.readValue((Long.class.getClassLoader())));
instance.serviceFee = ((String) in.readValue((String.class.getClassLoader())));
instance.distance = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Affiliate[] newArray(int size) {
return (new Affiliate[size]);
}

}
;
private final static long serialVersionUID = -8704712645885448192L;

public String getAffiliateId() {
return affiliateId;
}

public void setAffiliateId(String affiliateId) {
this.affiliateId = affiliateId;
}

public String getShopName() {
return shopName;
}

public void setShopName(String shopName) {
this.shopName = shopName;
}

public String getShopImage() {
return shopImage;
}

public void setShopImage(String shopImage) {
this.shopImage = shopImage;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getTelephoneNumber() {
return telephoneNumber;
}

public void setTelephoneNumber(String telephoneNumber) {
this.telephoneNumber = telephoneNumber;
}

public Double getLongitude() {
return longitude;
}

public void setLongitude(Double longitude) {
this.longitude = longitude;
}

public Double getLatitude() {
return latitude;
}

public void setLatitude(Double latitude) {
this.latitude = latitude;
}

public Boolean getVerified() {
return verified;
}

public void setVerified(Boolean verified) {
this.verified = verified;
}

public Long getAverageRating() {
return averageRating;
}

public void setAverageRating(Long averageRating) {
this.averageRating = averageRating;
}

public Long getReviewCount() {
return reviewCount;
}

public void setReviewCount(Long reviewCount) {
this.reviewCount = reviewCount;
}

public Long getCompletedRate() {
return completedRate;
}

public void setCompletedRate(Long completedRate) {
this.completedRate = completedRate;
}

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String quoteId) {
this.quoteId = quoteId;
}

public Long getQuoteTime() {
return quoteTime;
}

public void setQuoteTime(Long quoteTime) {
this.quoteTime = quoteTime;
}

public String getServiceFee() {
return serviceFee;
}

public void setServiceFee(String serviceFee) {
this.serviceFee = serviceFee;
}

public String getDistance() {
return distance;
}

public void setDistance(String distance) {
this.distance = distance;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(affiliateId);
dest.writeValue(shopName);
dest.writeValue(shopImage);
dest.writeValue(address);
dest.writeValue(telephoneNumber);
dest.writeValue(longitude);
dest.writeValue(latitude);
dest.writeValue(verified);
dest.writeValue(averageRating);
dest.writeValue(reviewCount);
dest.writeValue(completedRate);
dest.writeValue(quoteId);
dest.writeValue(quoteTime);
dest.writeValue(serviceFee);
dest.writeValue(distance);
}

public int describeContents() {
return 0;
}

}
package com.caknow.customer.util.net.garage;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle implements Serializable, Parcelable
{

@SerializedName("ut")
@Expose
private Long ut;
@SerializedName("ct")
@Expose
private Long ct;
@SerializedName("trim")
@Expose
private String trim;
@SerializedName("model")
@Expose
private String model;
@SerializedName("year")
@Expose
private Long year;
@SerializedName("make")
@Expose
private String make;
@SerializedName("logo")
@Expose
private String logo;
@SerializedName("_id")
@Expose
private String id;
@SerializedName("active")
@Expose
private Boolean active;
@SerializedName("mileage")
@Expose
private Long mileage;
@SerializedName("quoteCount")
@Expose
private Long quoteCount;
public final static Parcelable.Creator<Vehicle> CREATOR = new Creator<Vehicle>() {


@SuppressWarnings({
"unchecked"
})
public Vehicle createFromParcel(Parcel in) {
Vehicle instance = new Vehicle();
instance.ut = ((Long) in.readValue((Long.class.getClassLoader())));
instance.ct = ((Long) in.readValue((Long.class.getClassLoader())));
instance.trim = ((String) in.readValue((String.class.getClassLoader())));
instance.model = ((String) in.readValue((String.class.getClassLoader())));
instance.year = ((Long) in.readValue((Long.class.getClassLoader())));
instance.make = ((String) in.readValue((String.class.getClassLoader())));
instance.logo = ((String) in.readValue((String.class.getClassLoader())));
instance.id = ((String) in.readValue((String.class.getClassLoader())));
instance.active = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
instance.mileage = ((Long) in.readValue((Long.class.getClassLoader())));
instance.quoteCount = ((Long) in.readValue((Long.class.getClassLoader())));
return instance;
}

public Vehicle[] newArray(int size) {
return (new Vehicle[size]);
}

}
;
private final static long serialVersionUID = 916173834653574585L;

public Long getUt() {
return ut;
}

public void setUt(Long ut) {
this.ut = ut;
}

public Vehicle withUt(Long ut) {
this.ut = ut;
return this;
}

public Long getCt() {
return ct;
}

public void setCt(Long ct) {
this.ct = ct;
}

public Vehicle withCt(Long ct) {
this.ct = ct;
return this;
}

public String getTrim() {
return trim;
}

public void setTrim(String trim) {
this.trim = trim;
}

public Vehicle withTrim(String trim) {
this.trim = trim;
return this;
}

public String getModel() {
return model;
}

public void setModel(String model) {
this.model = model;
}

public Vehicle withModel(String model) {
this.model = model;
return this;
}

public Long getYear() {
return year;
}

public void setYear(Long year) {
this.year = year;
}

public Vehicle withYear(Long year) {
this.year = year;
return this;
}

public String getMake() {
return make;
}

public void setMake(String make) {
this.make = make;
}

public Vehicle withMake(String make) {
this.make = make;
return this;
}

public String getLogo() {
return logo;
}

public void setLogo(String logo) {
this.logo = logo;
}

public Vehicle withLogo(String logo) {
this.logo = logo;
return this;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Vehicle withId(String id) {
this.id = id;
return this;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}

public Vehicle withActive(Boolean active) {
this.active = active;
return this;
}

public Long getMileage() {
return mileage;
}

public void setMileage(Long mileage) {
this.mileage = mileage;
}

public Vehicle withMileage(Long mileage) {
this.mileage = mileage;
return this;
}

public Long getQuoteCount() {
return quoteCount;
}

public void setQuoteCount(Long quoteCount) {
this.quoteCount = quoteCount;
}

public Vehicle withQuoteCount(Long quoteCount) {
this.quoteCount = quoteCount;
return this;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(ut);
dest.writeValue(ct);
dest.writeValue(trim);
dest.writeValue(model);
dest.writeValue(year);
dest.writeValue(make);
dest.writeValue(logo);
dest.writeValue(id);
dest.writeValue(active);
dest.writeValue(mileage);
dest.writeValue(quoteCount);
}

public int describeContents() {
return 0;
}

}
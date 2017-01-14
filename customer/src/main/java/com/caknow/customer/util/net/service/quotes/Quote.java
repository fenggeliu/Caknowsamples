package com.caknow.customer.util.net.service.quotes;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote implements Serializable, Parcelable
{

@SerializedName("_id")
@Expose
private String id;
@SerializedName("isReQuote")
@Expose
private Boolean isReQuote;
@SerializedName("acceptTime")
@Expose
private Long acceptTime;
@SerializedName("itemizedAmounts")
@Expose
private List<ItemizedAmount> itemizedAmounts;
@SerializedName("status")
@Expose
private String status;
public final static Parcelable.Creator<Quote> CREATOR = new Creator<Quote>() {


@SuppressWarnings({
"unchecked"
})
public Quote createFromParcel(Parcel in) {
Quote instance = new Quote();
instance.id = ((String) in.readValue((String.class.getClassLoader())));
instance.isReQuote = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
instance.acceptTime = ((Long) in.readValue((Long.class.getClassLoader())));
in.readList(instance.itemizedAmounts, (com.caknow.customer.util.net.service.quotes.ItemizedAmount.class.getClassLoader()));
instance.status = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public Quote[] newArray(int size) {
return (new Quote[size]);
}

}
;
private final static long serialVersionUID = -6722088711181714501L;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Boolean getIsReQuote() {
return isReQuote;
}

public void setIsReQuote(Boolean isReQuote) {
this.isReQuote = isReQuote;
}

public Long getAcceptTime() {
return acceptTime;
}

public void setAcceptTime(Long acceptTime) {
this.acceptTime = acceptTime;
}

public List<ItemizedAmount> getItemizedAmounts() {
return itemizedAmounts;
}

public void setItemizedAmounts(List<ItemizedAmount> itemizedAmounts) {
this.itemizedAmounts = itemizedAmounts;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(id);
dest.writeValue(isReQuote);
dest.writeValue(acceptTime);
dest.writeList(itemizedAmounts);
dest.writeValue(status);
}

public int describeContents() {
return 0;
}

}

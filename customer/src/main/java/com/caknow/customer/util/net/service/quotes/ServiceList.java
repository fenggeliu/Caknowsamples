package com.caknow.customer.util.net.service.quotes;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceList implements Serializable, Parcelable
{

@SerializedName("icon")
@Expose
private String icon;
@SerializedName("category")
@Expose
private String category;
@SerializedName("field")
@Expose
private String field;
public final static Parcelable.Creator<ServiceList> CREATOR = new Creator<ServiceList>() {


@SuppressWarnings({
"unchecked"
})
public ServiceList createFromParcel(Parcel in) {
ServiceList instance = new ServiceList();
instance.icon = ((String) in.readValue((String.class.getClassLoader())));
instance.category = ((String) in.readValue((String.class.getClassLoader())));
instance.field = ((String) in.readValue((String.class.getClassLoader())));
return instance;
}

public ServiceList[] newArray(int size) {
return (new ServiceList[size]);
}

}
;
private final static long serialVersionUID = -4853725784895918918L;

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public String getField() {
return field;
}

public void setField(String field) {
this.field = field;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(icon);
dest.writeValue(category);
dest.writeValue(field);
}

public int describeContents() {
return 0;
}

}

package com.caknow.customer.util.net.quote;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.service.quotes.ItemizedAmount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopQuote implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isReQuote")
    @Expose
    private boolean isReQuote;
    @SerializedName("acceptTime")
    @Expose
    private int acceptTime;
    @SerializedName("itemizedAmounts")
    @Expose
    private List<ItemizedAmount> itemizedAmounts = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<TopQuote> CREATOR = new Creator<TopQuote>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TopQuote createFromParcel(Parcel in) {
            TopQuote instance = new TopQuote();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.isReQuote = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.acceptTime = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.itemizedAmounts, (com.caknow.customer.util.net.service.quotes.ItemizedAmount.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public TopQuote[] newArray(int size) {
            return (new TopQuote[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TopQuote() {
    }

    /**
     * 
     * @param id
     * @param isReQuote
     * @param status
     * @param acceptTime
     * @param itemizedAmounts
     */
    public TopQuote(String id, boolean isReQuote, int acceptTime, List<ItemizedAmount> itemizedAmounts, String status) {
        super();
        this.id = id;
        this.isReQuote = isReQuote;
        this.acceptTime = acceptTime;
        this.itemizedAmounts = itemizedAmounts;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsReQuote() {
        return isReQuote;
    }

    public void setIsReQuote(boolean isReQuote) {
        this.isReQuote = isReQuote;
    }

    public int getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(int acceptTime) {
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
        return  0;
    }

}

package com.caknow.customer.message;

/**
 * Created by jkang on 1/4/17.
 */
public class MessageItem {
    long id;
    String from;
    String content;
    String date;

    public MessageItem(final long id, final String from, final String content, final String date){
        this.id = id;
        this.from = from;
        this.content = content;
        this.date = date;
    }
    // ===========================================================
    // Constants
    // ===========================================================
    public long getMessageId(){
        return this.id;
    }
    public String getFrom(){
        return this.from;
    }
    public String getDate(){
        return this.date;
    }
    public String getContent(){
        return this.content;
    }
    // ===========================================================
    // Fields
    // ===========================================================
}

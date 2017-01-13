package com.caknow.customer.message;

import android.provider.BaseColumns;

/**
 * Defines the table niceName and column names for a single table.
 * <p>
 * https://developer.android.com/training/basics/data-storage/databases.html
 * Created by jkang on 1/5/17.
 */
public class MessageDataContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MessageDataContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}

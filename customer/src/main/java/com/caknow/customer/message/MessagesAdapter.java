package com.caknow.customer.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkang on 1/4/17.
 */
public class MessagesAdapter extends ArrayAdapter<MessageItem> {
    ListAdapter delegate = null;
    List<MessageItem> messageItems;
    Context viewContext;

    public MessagesAdapter(Context context, ArrayList<MessageItem> messageItemArrayList) {
        super(context, 0, messageItemArrayList);
        this.messageItems = messageItemArrayList;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public MessageItem getItem(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > messageItems.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return messageItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return messageItems.get(i).getMessageId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MessageItem messageItem = getItem(i);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.message_item_title);
        TextView tvDate = (TextView) convertView.findViewById(R.id.message_item_time);
        TextView tvInfo = (TextView) convertView.findViewById(R.id.message_item_info);
        // Populate the data into the template view using the data object
        tvName.setText(messageItem.getFrom());
        tvDate.setText(messageItem.getDate());
        tvInfo.setText(messageItem.getContent());
        // Return the completed view to render on screen
        return convertView;

    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return messageItems.size() == 0;
    }
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
}

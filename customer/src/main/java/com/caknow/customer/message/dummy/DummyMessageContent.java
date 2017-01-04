package com.caknow.customer.message.dummy;

import com.caknow.customer.message.MessageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyMessageContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final ArrayList<MessageItem> ITEMS = new ArrayList<MessageItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Long, MessageItem> ITEM_MAP = new HashMap<Long, MessageItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(MessageItem item) {
        for(int i = 0; i < 10; i++) {
            ITEMS.add(item);
            ITEM_MAP.put(item.getMessageId(), item);
        }
    }

    private static MessageItem createDummyItem(int position) {
        return new MessageItem(position, "from", "message", "date");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


}

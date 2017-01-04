package com.caknow.customer.service.model.dummy;

import com.caknow.customer.service.model.ServiceItem;

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
public class DummyServiceContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ServiceItem> ITEMS = new ArrayList<ServiceItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ServiceItem> ITEM_MAP = new HashMap<String, ServiceItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ServiceItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ServiceItem createDummyItem(int position) {
        return new ServiceItem(String.valueOf(position), "Item " + position, makeDetails(position));
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

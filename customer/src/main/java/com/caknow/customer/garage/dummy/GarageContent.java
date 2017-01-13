package com.caknow.customer.garage.dummy;

import com.caknow.customer.home.HomeCardItem;

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
public class GarageContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<HomeCardItem> ITEMS = new ArrayList<HomeCardItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, HomeCardItem> ITEM_MAP = new HashMap<String, HomeCardItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(HomeCardItem item) {
        for(int i = 0; i < 10; i++) {
            ITEMS.add(item);
            ITEM_MAP.put(item.getVehicleId(), item);
        }
    }

    private static HomeCardItem createDummyItem(int position) {
        return new HomeCardItem("status".concat(String.valueOf(position)), "vehicleId".concat(String.valueOf(position)),
                "vehicleType".concat(String.valueOf(position)),
                "date".concat(String.valueOf(position)),
                "detailInfo".concat(String.valueOf(position)),
                "action".concat(String.valueOf(position)));
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

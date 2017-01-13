package com.caknow.customer.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

public class IOUtils {
    private IOUtils() {
        throw new AssertionError();
    }

    /**
     * Close closeable object and wrap {@link java.io.IOException} with {@link RuntimeException}
     *
     * @param closeable closeable object
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred.", e);
            }
        }
    }

    /**
     * Close closeable and hide possible {@link IOException}
     *
     * @param closeable closeable object
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }
}

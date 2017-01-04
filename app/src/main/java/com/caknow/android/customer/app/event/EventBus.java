package com.caknow.android.customer.app.event;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by junu on 1/2/2017.
 */

public class EventBus {

    Bus bus = new Bus(ThreadEnforcer.MAIN);


}

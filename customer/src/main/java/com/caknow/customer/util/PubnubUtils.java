package com.caknow.customer.util;

/**
 * Created by wesson_wxy on 2016/12/20.
 */

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.util.Hashtable;

public class PubnubUtils {
    final static Pubnub pubnub = new Pubnub(
            "pub-c-b6de72fb-a8ec-4a89-adf4-86f0463e7d2a",    // PUBLISH_KEY (Optional, supply "" to disable)
            "sub-c-abf3508a-e33d-11e5-9dc0-0619f8945a4f",    // SUBSCRIBE_KEY (Required)
            "",                                              // SECRET_KEY (Optional, supply "" to disable)
            "",                                              // CIPHER_KEY (Optional, supply "" to disable)
            false                                            // SSL_ON?
    );

    public static void publish(final String chnl, final String msg) {
        try {
            pubnub.subscribe(chnl, new Callback() {
                @Override
                public void connectCallback(String channel, Object message) {
                    pubnub.publish(chnl, msg, new Callback() {
                    });
                }

                @Override
                public void disconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE: DISCONNECT on channel: "
                            + channel + " : " + message.getClass() + " : "
                            + message.toString());
                }

                public void reconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : RECONNECT on channel: "
                            + channel + " : " + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void successCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : " + channel + " : "
                            + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    System.out.println("SUBSCRIBE : ERROR on channel: "
                            + channel + " : " + error.toString());
                }
            });

            Hashtable args = new Hashtable(2);

            String message = "Hello PubNub!";
            String channel = "hello_world";

            args.put("message", message);
            args.put("channel", channel);
        } catch (PubnubException e) {
            System.out.println(e.toString());
        }

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }

            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };

        pubnub.time(callback);
    }

    public static void listen(String channel) {
        try {
            pubnub.subscribe(channel, new Callback() {
                @Override
                public void connectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : CONNECT on channel: "
                            + channel + " : " + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void disconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : DISCONNECT on channel: "
                            + channel + " : " + message.getClass() + " : "
                            + message.toString());
                }

                public void reconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : RECONNECT on channel: "
                            + channel + " : " + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void successCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : " + channel + " : "
                            + message.getClass() + " : " + message.toString());
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    System.out.println("SUBSCRIBE : ERROR on channel " + channel
                            + " : " + error.toString());
                }
            });

            Hashtable args = new Hashtable(1);
            args.put("channel", channel);
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    public static void listen(String channel, Callback callback) {
        try {
            pubnub.subscribe(channel, callback);
            //TODO what is this unresolved field 'openpath'
            //openPath.channelList.add(channel);
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    public static void history(String channel) {
        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }

            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };
        pubnub.history(channel, 100, true, callback);
    }

    public static void history(String channel, Callback callback) {
        pubnub.history(channel, 100, true, callback);
    }

    public static void presence(String channel) {
        try {
            pubnub.presence(channel, new Callback() {
                public void sucessCallback(String channel, Object message) {
                }

                public void errorCallback(String channel, Object message) {
                }
            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    public static void hereNow(String channel) {
        pubnub.hereNow(channel, new Callback() {
            public void successCallback(String channel, Object message) {
            }

            public void errorCallback(String channel, Object message) {
            }
        });
    }

    public static void unsubscribe(String channel) {
        pubnub.unsubscribe(channel);
    }

    public static void shutdown() {
        pubnub.shutdown();
    }
}


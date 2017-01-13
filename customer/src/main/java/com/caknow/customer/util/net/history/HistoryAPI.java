package com.caknow.customer.util.net.history;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by junu on 1/12/2017.
 */

public interface HistoryAPI {

    @GET("/consumer/history")
    Call<HistoryResponse> getHistory();
}

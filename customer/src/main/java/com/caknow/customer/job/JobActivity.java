package com.caknow.customer.job;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.report.ReportActivity;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseResponse;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.widget.BaseActivity;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/2/2017.
 */

public class JobActivity extends BaseActivity{

    @Inject
    Retrofit retrofit;

    Bundle extras;
    ArrayList<Parcelable> data;
    VehicleServiceInterface serviceItem;
    GetQuotesResponse responseBody;

    ServiceAPI serviceAPI;
    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_job);
        CAKNOWApplication.get().getNetComponent().inject(this);
        ButterKnife.bind(this);
        try{
            extras = getIntent().getExtras();

        } catch(Exception e){

        }
        if(extras!=null){
            try{
                serviceItem = extras.getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
            } catch(Exception e){
                serviceItem = extras.getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
                responseBody = extras.getParcelable("responseBody");
            }
        }


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        serviceAPI = retrofit.create(ServiceAPI.class);
        serviceAPI.getQuotes(serviceItem.getServiceRequestId()).enqueue(new Callback<GetQuotesResponse>() {
            @Override
            public void onResponse(Call<GetQuotesResponse> call, Response<GetQuotesResponse> response) {
                try {
                    hideProgress();
                    JobDetailsFragment fragment = new JobDetailsFragment();
                    final Bundle args = new Bundle();
                    args.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, serviceItem);
                    args.putParcelable("responseBody", response.body());
                    fragment.setArguments(args);
                    addFragment(R.id.job_fragment, fragment, JobDetailsFragment.FRAGMENT_TAG);
                } catch(Exception e){

                }

            }

            @Override
            public void onFailure(Call<GetQuotesResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void configView() {
    }


    public VehicleServiceInterface getServiceItem(){
        return this.serviceItem;
    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Service");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quote_details, menu);
//        MenuItem reinitiateItem = (MenuItem) findViewById(R.id.action_reinitiate);
//        reinitiateItem.setVisible(false);
//        MenuItem cancelItem = (MenuItem) findViewById(R.id.action_cancel_request);
//        if(serviceItem.getStatus() < 8){
//            cancelItem.setVisible(true);
//        }
        this.invalidateOptionsMenu();
        return true;
    }

    /**
     * Action Menu (3-dot) Items are here
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_cancel_request:
                cancelRequest();
                break;
            case R.id.action_order_number:
                showOrderNumber();
                break;
            case R.id.action_report:
                final Intent intent = new Intent(this, ReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.action_reinitiate:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showOrderNumber(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        String message = serviceItem.getOrderNo();
        alertDialogBuilder.setTitle(getString(R.string.dialog_order_number_title));
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
            try {
                dialogInterface.dismiss();
            } catch (Exception e) {
            }
        });

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

    private void cancelRequest(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        String message = getString(R.string.dialog_cancel_request_message_body);
        alertDialogBuilder.setTitle(getString(R.string.dialog_cancel_request_title));
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.dialog_positive_ok), (dialogInterface, i) -> {
                performCancellation();
        });
        alertDialogBuilder.setNegativeButton(getResources().getString(R.string.dialog_negative_cancel), (dialogInterface, i) -> dialogInterface.cancel());

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }


    public boolean checkCallPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            }
        }
        return true;
    }

    private void performCancellation(){
        showProgress();
        JsonObject requestId = new JsonObject();
        requestId.addProperty("serviceRequestId", serviceItem.getServiceRequestId());
        RequestBody cancelRequest = RequestBody.create(MediaType.parse("application/json"), requestId.toString());
        serviceAPI.cancelServiceByRequestId(cancelRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try{
                    hideProgress();
                    if(response.body().getSuccess()){
                        JobActivity.this.finish();
                    } else{
                        Toast.makeText(JobActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e){
                    // UI Events are not thread safe
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                try{
                    hideProgress();
                    Toast.makeText(JobActivity.this, "Network Error Occured:".concat(t.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    // UI Events are not thread safe
                }

            }
        });
    }
}

package com.caknow.customer.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.util.net.service.Services;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/11/2017.
 */

public class ServiceItemView extends View {

    private Services item;
    private Context context;
    private String id;

    @BindView(R.id.repair_item_imageview)
    ImageView repairItemImageView;

    @BindView(R.id.repair_item_name)
    TextView nameView;
    public ServiceItemView(final View view, @NonNull final Services item) {
        super(view.getContext());
        this.item = item;
        ButterKnife.bind(this, view);
        context = view.getContext();

    }


    public void setTitle(String name){
        nameView.setText(name);
    }

    public void setImage(String url){
        Glide.with(context).load(url).into(repairItemImageView);
    }

    public void setItem(Services item){
        this.item = item;
        id = item.getCatagoryId();
    }
}

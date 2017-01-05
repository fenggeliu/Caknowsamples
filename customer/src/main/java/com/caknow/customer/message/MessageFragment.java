package com.caknow.customer.message;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.BuildConfig;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.caknow.customer.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.caknow.app.R;
import com.caknow.customer.home.LoadMoreListView;
import com.caknow.customer.message.dummy.DummyMessageContent;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by junu on 1/1/17.
 */

public class MessageFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + MessageFragment.class.getName();

    @BindView(R.id.load_more_refresh_view) LoadMoreListView listView;
    @BindView(R.id.no_history_display) LinearLayout emptyMessagesView;
    private ListAdapter messageListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.message_layout, container, false);
        ButterKnife.bind(this, v);
        emptyMessagesView.setVisibility(View.GONE);
        setupListview();
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("Messages");
    }

    private void setupMenuCreator(){
        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                    0xCE)));
            // set item width
            openItem.setWidth(90);
            // set item title
            openItem.setTitle("Open");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(90);
            // set a icon
            deleteItem.setIcon(R.drawable.delete_icon_2x);
            // add to menu
            menu.addMenuItem(deleteItem);
        };

        // set creator
        listView.setMenuCreator(creator);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    private void setupListview(){
        listView.setOnMenuItemClickListener((position, menu, index) -> {
            switch (index) {
                case 0:
                    // open
                    break;
                case 1:
                    // delete
                    break;
            }
            // false : close the menu; true : not close the menu
            return false;
        });
        messageListAdapter = new MessagesAdapter(getActivity(), DummyMessageContent.ITEMS);
        SwipeMenuAdapter swipeMenuAdapter = new SwipeMenuAdapter(getActivity(), messageListAdapter);
        listView.setAdapter(swipeMenuAdapter);
    }
}

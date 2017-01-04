package com.caknow.customer.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.caknow.app.R;

/**
 * Created by wesson_wxy on 2016/12/9.
 */

public class LoadMoreListView extends SwipeMenuListView implements AbsListView.OnScrollListener {
    private boolean isScrollToBottom;
    private View footerView;
    private int footerViewHeight;
    private boolean isLoadingMore = false;

    private OnRefreshListener mOnRefreshListener;


    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView();
        this.setOnScrollListener(this);
    }

    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.pull_to_get_more, null);

        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footerView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onLoadingMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isScrollToBottom = getLastVisiblePosition() == (totalItemCount - 1);

    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    public void loadMoreComplete() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }

    public interface OnRefreshListener {
        void onLoadingMore();
    }
}

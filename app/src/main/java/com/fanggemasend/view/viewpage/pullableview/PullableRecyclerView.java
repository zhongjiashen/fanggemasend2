package com.fanggemasend.view.viewpage.pullableview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class PullableRecyclerView extends RecyclerView implements Pullable {
    private boolean top;
    private boolean down;

    public PullableRecyclerView(Context context) {
        super(context);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public PullableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public PullableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public boolean canPullDown() {
        if (canScrollVertically(-1))
            return false;
        else return true;
    }

    @Override
    public boolean canPullUp() {
        if (canScrollVertically(1))
            return false;
        else return true;
    }

}

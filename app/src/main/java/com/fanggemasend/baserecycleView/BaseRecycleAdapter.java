package com.fanggemasend.baserecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fanggemasend.model.Baseben;

import java.util.List;

/**
 * Created by 1363655717 on 2017/3/23.
 */

public abstract class BaseRecycleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private int layoutId;
    private List<? extends Baseben> data;
    private Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     *
     * @param context //上下文
     * @param layoutId  //布局id
     */
    public BaseRecycleAdapter(Context context, int layoutId) {
        this.layoutId = layoutId;

        this.context = context;
    }
    public void addList(List<? extends Baseben> data){
        this.data = data;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        final BaseViewHolder holder = new BaseViewHolder(v, context);
        //单击事件回调
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickFlag) {
                    if(onItemClickListner!=null)
                    onItemClickListner.onItemClickListner(v,holder.getLayoutPosition());
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListner!=null)
                onItemLongClickListner.onItemLongClickListner(v,holder.getLayoutPosition());
                clickFlag = false;
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    protected abstract <T extends Baseben> void convert(BaseViewHolder holder, T bean);

    @Override
    public int getItemCount() {
        if(data!=null)
        return data.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
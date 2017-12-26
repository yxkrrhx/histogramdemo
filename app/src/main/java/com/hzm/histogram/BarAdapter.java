package com.hzm.histogram;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


/**
 * hzm in CQ.
 */

public class BarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private List<ChartData> mDatas;
    private float mLowStandard;
    private float mHighStandard;
    private Context mContext;
    private int indexSelected = -1;

    public BarAdapter(Context context, float lowStandard, float highStandard,List<ChartData> mDatas) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mLowStandard = lowStandard;
        mHighStandard = highStandard;
        this.mDatas=mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(mLayoutInflater.inflate(R.layout.bar_chart_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChartData data = mDatas.get(position);
        TextView tv_name = ((ViewHolder) holder).getView(R.id.name);
        if(position == indexSelected){  //当用户选中时改变文字颜色
            tv_name.setTextColor(Color.RED);
        } else {
            tv_name.setTextColor(Color.GRAY);
        }
        ProgressBar progressBar = ((ViewHolder) holder).getView(R.id.pb_vertical);
        float pro = data.getProgress();
        progressBar.setVisibility(View.VISIBLE);
        if (pro > mHighStandard) {//大于“高”时使用一种颜色的Drawable
            progressBar.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.progress_high));
        } else if (pro < mLowStandard) {
            progressBar.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.progress_low));
        } else {//小于“低”时使用一种颜色的Drawable
            progressBar.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.progress_normal));
        }
        progressBar.setProgress(Math.round(pro));
        tv_name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    public void setSelected(int position) {
        if(indexSelected == -1){
            indexSelected = position;
            notifyItemChanged(indexSelected);
        } else {
            int a = indexSelected;
            indexSelected = position;
            notifyItemChanged(indexSelected);
            notifyItemChanged(a);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }

        public void setText(int viewId, String text){
            TextView tv = (TextView) itemView.findViewById(viewId);
            tv.setText(text);
        }

        public <T extends View> T getView(int viewId){
            return  (T) itemView.findViewById(viewId);
        }
    }
}

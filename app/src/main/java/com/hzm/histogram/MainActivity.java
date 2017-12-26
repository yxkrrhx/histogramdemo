package com.hzm.histogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图
 * hzm in CQ
 */

public class MainActivity extends AppCompatActivity {

    protected View viewY;
    protected View viewX;
    protected TextView textLow;
    protected TextView textMid;
    protected TextView textHi;
    protected RecyclerView rcv;
    protected RelativeLayout chart;
    BarAdapter mdapter;
    List<ChartData> mDatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        viewY = (View) findViewById(R.id.view_y);
        viewX = (View) findViewById(R.id.view_x);
        textLow = (TextView) findViewById(R.id.text_low);
        textMid = (TextView) findViewById(R.id.text_mid);
        textHi = (TextView) findViewById(R.id.text_hi);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(linearLayoutManager);
        chart = (RelativeLayout) findViewById(R.id.chart);
    }

    private void initData(){
        for (int i=1;i<=25;i++){
            ChartData item=new ChartData();
            item.setName("第"+i+"个");
            item.setProgress(i*4);
            mDatas.add(item);
        }
    }

    private void initAdapter(){
        mdapter = new BarAdapter(this, 25, 75,mDatas);
        rcv.setAdapter(mdapter);
    }
}

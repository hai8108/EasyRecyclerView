package com.jude.dome.header;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.dome.DataProvider;
import com.jude.dome.R;
import com.jude.dome.Utils;
import com.jude.dome.loadmore.PersonAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.Util;
import com.jude.rollviewpager.hintview.ColorPointHintView;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class HeaderFooterActivity extends Activity {
    private EasyRecyclerView recyclerView;
    private PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter = new PersonAdapter(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this,0.5f), 0,0);
        itemDecoration.setDrawLastItem(true);
        itemDecoration.setDrawHeaderFooter(true);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.addAll(DataProvider.getPersonList(0));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(HeaderFooterActivity.this);
                header.setHintView(new ColorPointHintView(HeaderFooterActivity.this, Color.YELLOW,Color.GRAY));
                header.setHintPadding(0, 0, 0, (int) Utils.convertDpToPixel(8, HeaderFooterActivity.this));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) Utils.convertDpToPixel(200, HeaderFooterActivity.this)));
                header.setAdapter(new BannerAdapter(HeaderFooterActivity.this));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                ImageView iv = new ImageView(HeaderFooterActivity.this);
                iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                iv.setBackgroundResource(R.drawable.header);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HeaderFooterActivity.this, "ImageView clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                return iv;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                TextView tv = new TextView(HeaderFooterActivity.this);
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) Utils.convertDpToPixel(56,HeaderFooterActivity.this)));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                tv.setText("(-_-)/~~~死宅真是恶心");
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HeaderFooterActivity.this, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                return tv;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }


}

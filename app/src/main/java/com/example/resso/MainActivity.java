package com.example.resso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.icu.number.IntegerWidth;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
      context=getApplicationContext();


        MyListData[] myListData = new MyListData[] {

                new MyListData(R.drawable.ww,R.raw.set,"Set Fire To The Rain","",context),
                new MyListData(R.drawable.khadahu,R.raw.khadahu,"Local train","",context),
                new MyListData(R.drawable.tt,R.raw.habibi,"Habibi by Ricky rich ","",context),
               new MyListData(R.drawable.qq,R.raw.nother,"another love ","",context),
                new MyListData(R.drawable.yy,R.raw.teri,"Yeri Yadome ","",context),


        };




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        MyListAdapter adapter = new MyListAdapter(myListData);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        // Flag to keep track of scrolling
        final boolean[] isScrolling = {false};
        adapter.setOnScrollListener(recyclerView);


// When the activity is destroyed or RecyclerView is no longer needed
        adapter.releaseMediaPlayer();



    }




}

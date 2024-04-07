package com.example.resso;

import android.content.Context;

public class MyListData{
    int song;
    String songname;
    String singer;
    private int imgId;
    Context context;
    public MyListData(int imgId,int song,String songname,String singer,Context context) {

        this.imgId = imgId;
        this.song=song;
        this.songname=songname;
        this.singer=singer;
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getSong() {
        return song;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
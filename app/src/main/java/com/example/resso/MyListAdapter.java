package com.example.resso;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private MyListData[] listdata;
    private int previousScrollPosition = 0;
    MediaPlayer mm;
    AudioManager audioManager;
    private SeekBar currentSeekBar;
    // RecyclerView recyclerView;
    public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.singlerow, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listdata[holder.getAdapterPosition()];
        holder.textView.setText(listdata[holder.getAdapterPosition()].getSinger());
        holder.textView1.setText(listdata[holder.getAdapterPosition()].getSongname());
        holder.imageView.setImageResource(listdata[holder.getAdapterPosition()].getImgId());

        // Release previous MediaPlayer instance if it's playing
        if (mm != null && mm.isPlaying()) {
            mm.stop();
            mm.release();
            mm = null;
        }

        mm = MediaPlayer.create(holder.itemView.getContext().getApplicationContext(), myListData.getSong());
        mm.start();
        mm.setOnCompletionListener(completed);
        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.heart.setImageResource(R.drawable.red_haert);
            }
        });
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                holder.seekBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });










        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
                holder.play.setImageResource(R.drawable.baseline_play_arrow_24);




            }
        });
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mm.isPlaying()) {
                    // Pause the song and update the play button icon
                    mm.pause();
                    holder.play.setImageResource(R.drawable.baseline_play_arrow_24);
                } else {
                    // Resume playback from the last position and update the play button icon
                    mm.start();
                    holder.play.setImageResource(R.drawable.baseline_pause_24);
                }
            }
        });

    }
    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        relse();
    }

    MediaPlayer.OnCompletionListener completed =new MediaPlayer.OnCompletionListener () {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            relse();
           // Toast.makeText(numberactivity.this, "i am done ", Toast.LENGTH_SHORT).show();


        }
    };

    void relse(){
        if(mm!=null){
            mm.release();
            mm=null;
        }
    }
    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView,heart;
        public TextView textView,textView1;
       public ImageView play;
       SeekBar seekBar;
       public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.heart=(ImageView)itemView.findViewById(R.id.heart);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageview);
            this.textView=(TextView) itemView.findViewById(R.id.singername);
            this.play=(ImageView)itemView.findViewById(R.id.play);
                 this.textView1=(TextView)itemView.findViewById(R.id.songname);
                 this.seekBar=(SeekBar)itemView.findViewById(R.id.seekBar);

           relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativelayout);
        }
    }
    public void setOnScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int currentScrollPosition = recyclerView.computeVerticalScrollOffset();
                if (currentScrollPosition < previousScrollPosition) {


                    // Scrolling upwards
                    if (mm.isPlaying()) {
                        mm.pause();
                        relse();
                        notifyDataSetChanged(); // Notify adapter to update UI
                    }
                }

                previousScrollPosition = currentScrollPosition;
            }
        });
    }
    public void releaseMediaPlayer() {
        if (mm != null) {
            mm.release();
            mm = null;
        }
    }



    public void pausePlaybackIfNeeded() {
        if (mm.isPlaying()) {
            mm.pause();
        }
    }
}
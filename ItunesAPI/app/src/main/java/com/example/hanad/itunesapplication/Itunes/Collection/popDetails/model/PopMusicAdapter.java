package com.example.hanad.itunesapplication.Itunes.Collection.popDetails.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanad.itunesapplication.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;


/**
 * Created by hanad on 09/02/2018.
 */

public class PopMusicAdapter extends RecyclerView.Adapter<PopMusicAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Result> results;
    private PopMusicFragment popMusicFragment;
    private PopMusicList popMusicList;


    public PopMusicAdapter(Context applicationContext, List<Result> results, PopMusicFragment popMusicFragment, PopMusicList popMusicList, int row) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.results = results;
        this.popMusicFragment = popMusicFragment;
        this.popMusicList = popMusicList;
        // RockMusicFragment.this.getActivity().getApplicationContext(),rockMusicLists.getResults(),RockMusicFragment.this,R.layout.row));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.artistName.setText(results.get(position).getArtistName());
        holder.trackprice.setText(results.get(position).getTrackPrice().toString());
        holder.collection.setText(results.get(position).getCollectionName());
//        holder.music.setText(results.get(position).getPreviewUrl());

        Picasso.with(applicationContext)
                .load(results.get(position).getArtworkUrl60())
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView artistName;
        private ImageView imageView;
        private TextView trackprice;
        private TextView collection;
        private Button button;

        public MyViewHolder(View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artistName);
            imageView = itemView.findViewById(R.id.artworkUrl60);
            trackprice = itemView.findViewById(R.id.trackPrice);
            collection = itemView.findViewById(R.id.collectionName);
            button = itemView.findViewById(R.id.button);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playMusic();
                    Toast.makeText(applicationContext, "music click", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(applicationContext, results.get(getPosition()).getPreviewUrl(), Toast.LENGTH_SHORT).show();

                }
            });

        }


        public void playMusic() {
            String url = results.get(getPosition()).getPreviewUrl().toString();
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }else{
                Toast.makeText(applicationContext, "Playing", Toast.LENGTH_SHORT).show();
            }
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    button.setEnabled(true);
                }
            });
            try {
                mediaPlayer.setDataSource(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                }
            });
             //mediaPlayer.stop();
        }

    }
}
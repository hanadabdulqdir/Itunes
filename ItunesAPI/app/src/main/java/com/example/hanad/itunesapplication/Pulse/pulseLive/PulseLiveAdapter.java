package com.example.hanad.itunesapplication.Pulse.pulseLive;
//package com.example.hanad.itunesapplication.Itunes.Collection.classicDetails.model;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanad.itunesapplication.R;

import java.util.List;

/**
 * Created by hanad on 25/02/2018.
 */

public class PulseLiveAdapter extends RecyclerView.Adapter<PulseLiveAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Item> items;
    private PulseLiveFragment pulseLiveFragment;
    private PulseLiveList pulseLiveList;

    public PulseLiveAdapter(Context applicationContext, int row, List<Item> items, PulseLiveFragment pulseLiveFragment, PulseLiveList pulseLiveList) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.items = items;
        this.pulseLiveFragment = pulseLiveFragment;
        this.pulseLiveList = pulseLiveList;
    }


    @Override
    public PulseLiveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row,parent,false));
    }

    @Override
    public void onBindViewHolder(PulseLiveAdapter.MyViewHolder holder, int position) {
        holder.id.setText(items.get(position).getId().toString());
        holder.title.setText(items.get(position).getTitle());
        holder.subtitle.setText(items.get(position).getSubtitle());
        holder.date.setText(items.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView title;
        private TextView subtitle;
        private TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.ID);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            date=itemView.findViewById(R.id.date);

        }
    }
}

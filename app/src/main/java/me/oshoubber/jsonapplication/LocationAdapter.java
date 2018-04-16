package me.oshoubber.jsonapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import android.support.v7.widget.RecyclerView;
import java.util.Collections;


public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<LocationData> data = Collections.emptyList();
    LocationData current;
    private Context context;
    private LayoutInflater inflater;
    int currentPos =0;


    public LocationAdapter (Context context, List<LocationData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.location_entry, parent,false);
        return new MyHolder(view);
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        myHolder.startDate.setText(current.startDate);
        myHolder.objType.setText(current.objType);
        myHolder.endDate.setText(current.endDate);
        myHolder.name.setText(current.name);
        myHolder.loginRequired.setText(current.loginRequired);
        myHolder.url.setText(current.url);
        myHolder.venue.setText(current.venue);
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView startDate;
        TextView objType;
        TextView endDate;
        TextView name;
        TextView loginRequired;
        TextView url;
        TextView venue;
        ImageView icon;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            
            TextView startDate = itemView.findViewById(R.id.startDate);
            TextView objType = itemView.findViewById(R.id.objType);
            TextView endDate = itemView.findViewById(R.id.endDate);
            TextView name =  itemView.findViewById(R.id.name);
            TextView loginRequired = itemView.findViewById(R.id.loginRequired);
            TextView url = itemView.findViewById(R.id.url);
            TextView venue = itemView.findViewById(R.id.venue);
            ImageView icon = itemView.findViewById(R.id.icon);
        }

    }

}

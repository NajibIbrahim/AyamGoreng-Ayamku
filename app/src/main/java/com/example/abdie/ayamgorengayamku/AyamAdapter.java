package com.example.abdie.ayamgorengayamku;

import android.app.Activity;
import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.List;


public class AyamAdapter extends RecyclerView.Adapter<AyamAdapter.AyamViewHolder> {


    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all superheroes
    List<Ayam> ayamList;

    //Constructor of this class
    public AyamAdapter(List<Ayam> ayamList, Context context){
        super();
        //Getting all superheroes
        this.ayamList = ayamList;
        this.context = context;
    }

    @Override
    public AyamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        AyamViewHolder viewHolder = new AyamViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AyamViewHolder holder, int position) {

        //Getting the particular item from the list
        Ayam ayam =  ayamList.get(position);

        //Loading image from url
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(ayam.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.drawable.image, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
        holder.imageView.setImageUrl(ayam.getImageUrl(), imageLoader);
        holder.textViewName.setText(ayam.getName());
        holder.textViewPublisher.setText(ayam.getPrice());

    }

    @Override
    public int getItemCount() {
        return ayamList.size();
    }

    class AyamViewHolder extends RecyclerView.ViewHolder{
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewPublisher;

        //Initializing Views
        public AyamViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.ayamImage);
            textViewName = (TextView) itemView.findViewById(R.id.ayamName);
            textViewPublisher = (TextView) itemView.findViewById(R.id.ayamPrice);
        }
    }
}


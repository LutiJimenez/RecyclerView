package com.luti.seccion_03_recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luti.seccion_03_recyclerview.R;
import com.luti.seccion_03_recyclerview.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luti on 4/12/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;


    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;

    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        holder.bind(movies.get(position), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageViewPoster;

        //Constructor
        public ViewHolder(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            //this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
        public void bind(final Movie movie, final OnItemClickListener listener){
            //Procesamos los datos a renderizar
            textViewName.setText(movie.getName());
            //Libreria Picasso para tratamiento de imagenes
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());
            //this.textViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }

    }



    public  interface OnItemClickListener{
        void onItemClick(Movie movies, int position);
    }
}

package com.luti.seccion_03_recyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.luti.seccion_03_recyclerview.adapters.MyAdapter;
import com.luti.seccion_03_recyclerview.R;
import com.luti.seccion_03_recyclerview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayaoutManager;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();
        mRecyclerView  = (RecyclerView) findViewById(R.id.recyclerView);

        // aqui vamos a cambiar la forma de ver el recycler view como List
        mLayaoutManager = new LinearLayoutManager(this);
        //este para verlo como un Grid!!!
        //mLayaoutManager = new GridLayoutManager(this, 2);
        //Este para verlo como un grid pero de diferente tamaño como por ejemplo fotos!!!, con esto no se podria utilizar el setHasFixedSize de mas abajo
        //mLayaoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movies, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                deleteMovie(position);
            }
        });



        //Si sabemos que el layout el recycler no  va a cambiar el tamaño no va a aumentar es mejor poner esto
        mRecyclerView.setHasFixedSize(true);
        //implementa una animacion por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayaoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Para añadir los options en el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(movies.size());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private List<Movie> getAllMovies(){
        return  new ArrayList<Movie>(){{
            add(new Movie("Spiderman", R.drawable.spiderman));
            add(new Movie("Ironman", R.drawable.ironman));
            add(new Movie("Thor", R.drawable.thor));
            add(new Movie("Batman vs Superman", R.drawable.bvss));

        }};
    }

   private void addMovie(int position){
        movies.add(position, new Movie("New Movie" + (++counter) ,R.drawable.newmovie ) );
        mAdapter.notifyItemInserted(position);
        //añadir un codigo
        mLayaoutManager.scrollToPosition(position);

    }

    private void deleteMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}

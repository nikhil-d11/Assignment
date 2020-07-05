package com.example.assignment.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.database.AppDatabase;

import com.example.assignment.network.INetworkCallback;
import com.example.assignment.adapter.MovieAdapter;
import com.example.assignment.models.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment.network.Api.search_api;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> results=new ArrayList<Movie>();
    ArrayList<Movie> bookmark_results=new ArrayList<Movie>();

    TextView bookmarktitle;
    ProgressBar progressBar;
    RecyclerView recyclerView_bookmark,recyclerView_search;
    RecyclerView.LayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    EditText searchbar;
    String query="marvel";
    Integer page_no=1;
    Integer max_page=Integer.MAX_VALUE;
    MovieAdapter bookmark_adapter,search_adapter;
    AppDatabase database;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean loading=false;

    //Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=AppDatabase.getInstance(this);
        bookmarktitle=findViewById(R.id.bookmark_title);
        progressBar=findViewById(R.id.progress_pagination);

        recyclerView_bookmark=findViewById(R.id.recycler_bookmark);
        recyclerView_search=findViewById(R.id.recycler_search);

        linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width=getWidth()/3;
                return true;
            }
        };


        gridLayoutManager=new GridLayoutManager(this,2);


        recyclerView_bookmark.setLayoutManager(linearLayoutManager);
        recyclerView_search.setLayoutManager(gridLayoutManager);

        bookmark_adapter=new MovieAdapter(this,bookmark_results);
        search_adapter=new MovieAdapter(this,results);


        recyclerView_bookmark.setAdapter(bookmark_adapter);
        recyclerView_search.setAdapter(search_adapter);

        BookMarksLoader task=new BookMarksLoader();
        task.execute();

        update_search_results();


        recyclerView_search.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount  && page_no<=max_page && loading==false) {
                    loading=true;
                    progressBar.setVisibility(View.VISIBLE);
                    update_search_results();
                }

            }
        });
    }



    class BookMarksLoader extends AsyncTask<Void,Void, List<Movie>> {
        @Override
        protected List<Movie> doInBackground(Void... voids) {
            return database.movieDao().get_bookmarks();
        }

        @Override
        protected void onPostExecute(List<Movie> models) {
            super.onPostExecute(models);
            bookmark_results.addAll(new ArrayList<Movie>(models));
            //Log.d("testing data",""+data.toString());
            bookmark_adapter.notifyDataSetChanged();

            if(bookmark_results.size()==0){
                bookmarktitle.setVisibility(View.GONE);
            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem
                = menu.findItem(R.id.search_bar);
        final SearchView searchView
                = (SearchView) MenuItemCompat
                .getActionView(searchViewItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String q)
                    {
                        page_no=1;
                        query=q;
                        results.clear();
                        progressBar.setVisibility(View.VISIBLE);
                        searchView.clearFocus();

                        update_search_results();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                          return false;
                      }
                });

        return super.onCreateOptionsMenu(menu);
    }





    private void update_search_results(){
        Log.d("testing query", query);
         search_api(query, String.valueOf(page_no), new INetworkCallback<ArrayList<Movie>>() {
            @Override
            public void OnSuccess(ArrayList<Movie> data,int max_size) {
                final int x=results.size();
                results.addAll(data);
               // Log.d("testing results", results.get(0).getTitle());
                //Log.d("testing count", ""+search_adapter.getItemCount());
                max_page=max_size;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        search_adapter.notifyItemRangeInserted(x,results.size()-x);
                        loading=false;
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
            @Override
            public void OnError(Exception e) {
                e.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
        page_no++;
    }

}
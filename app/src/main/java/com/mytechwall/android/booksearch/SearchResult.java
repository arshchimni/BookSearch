package com.mytechwall.android.booksearch;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.CubeGrid;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookData>> {

    ListView showresult;
    String finalUrl="";
    BookAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        CubeGrid cubeGrid=new CubeGrid();
        progressBar.setIndeterminateDrawable(cubeGrid);
        Intent intent=getIntent();
        String query=intent.getStringExtra("QUERY");

        String urlApi="https://www.googleapis.com/books/v1/volumes?q="+query.trim()+"&maxResults=10";
        finalUrl=urlApi;
        getLoaderManager().initLoader(0,null,this);
        showresult=(ListView)findViewById(R.id.displayResults);
         adapter=new BookAdapter(getApplicationContext(),new ArrayList<BookData>());
        showresult.setAdapter(adapter);




    }

    @Override
    public Loader<ArrayList<BookData>> onCreateLoader(int i, Bundle bundle) {
        return new LoaderBookData(getApplicationContext(),finalUrl);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookData>> loader, ArrayList<BookData> bookDatas) {
        adapter.clear();
        progressBar.setVisibility(View.INVISIBLE);

        if (bookDatas != null && !bookDatas.isEmpty()) {
            adapter.addAll(bookDatas);
        }



    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookData>> loader) {
        adapter.addAll(new ArrayList<BookData>());

    }
}

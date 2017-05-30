package com.mytechwall.android.booksearch;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookData>> {

    ListView showresult;
    String finalUrl="";
    BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent=getIntent();
        String query=intent.getStringExtra("QUERY");

        String urlApi="https://www.googleapis.com/books/v1/volumes?q="+query.trim()+"&maxResults=10";
        finalUrl=urlApi;
        System.out.println(finalUrl);
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
        System.out.println(bookDatas.size());
        if (bookDatas != null && !bookDatas.isEmpty()) {
            adapter.addAll(bookDatas);
        }



    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookData>> loader) {
        adapter.addAll(new ArrayList<BookData>());

    }
}

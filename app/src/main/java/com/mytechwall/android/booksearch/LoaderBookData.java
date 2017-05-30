package com.mytechwall.android.booksearch;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 30-05-2017.
 */

public class LoaderBookData extends AsyncTaskLoader<ArrayList<BookData>> {
    private String mUrl;
    public LoaderBookData(Context context,String mUrl) {
        super(context);
        this.mUrl=mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<BookData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        ArrayList<BookData> books;
        books = Utils.getBookData(mUrl);

        return books;



    }
}

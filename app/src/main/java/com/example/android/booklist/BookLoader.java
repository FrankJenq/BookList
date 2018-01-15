package com.example.android.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String[] mBookUrl;

    BookLoader(Context context, String... urls) {
        super(context);
        mBookUrl = urls;
    }

    @Override
    protected void onStartLoading() {
        Log.i("tag:", "This is onStartLoading");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {

        Log.i("tag:", "This is loadInBackground");
        // do not execute if there is no valid url, or the value of the first url is null
        if (mBookUrl.length < 1 || mBookUrl[0] == null) {
            return null;
        }
        List<Book> books = QueryUtils.fetchEarthquakeData(mBookUrl[0]);
        return books;
    }
}

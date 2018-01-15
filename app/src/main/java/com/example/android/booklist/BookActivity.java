
package com.example.android.booklist;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookActivity.class.getName();
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        String BOOK_LIST_JSON_RESPONSE = getString(R.string.book_link);
        return new BookLoader(this, BOOK_LIST_JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        // Make the ProgressBar invisible when onLoadFinished method is called
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.GONE);

        bookAdapter.clear();
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        TextView emptyTextView = (TextView) findViewById(R.id.empty_info);
        bookListView.setEmptyView(emptyTextView);
        if(isConnected){
            emptyTextView.setText(getString(R.string.empty_info));
        }
        else {
            emptyTextView.setText(getString(R.string.no_internet));
        }

        // return in advance if the object is null
        if (books == null) {
            return;
        }

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String url = bookAdapter.getItem(position).getInfoLink();
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(websiteIntent);
            }
        });

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(bookAdapter);

        // Add books into {@link ArrayAdapter}
        bookAdapter.addAll(books);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.clear();
    }
}

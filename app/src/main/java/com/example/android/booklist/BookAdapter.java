package com.example.android.booklist;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, List<Book> books) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Book currentBook = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.book_title);
        // Get the earthquakeLocation from the current Book object and
        // set this text on the earthquakeLocation TextView TextView
        titleTextView.setText(currentBook.getBookTitle());

        TextView authorsTextView = (TextView) listItemView.findViewById(R.id.authors);
        authorsTextView.setText(currentBook.getAuthors());
        Log.i("String", "getView: "+currentBook.getAuthors());

        TextView categoryTextView = (TextView) listItemView.findViewById(R.id.description);
        categoryTextView.setText(currentBook.getDescription());

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}

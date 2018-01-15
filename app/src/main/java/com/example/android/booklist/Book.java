package com.example.android.booklist;

import org.json.JSONArray;

public class Book {
    // String value
    private String mTitle;
    private String mAuthors;
    private String mDescription;
    private String mUrl;

    /**
     * Constructs a new book object with four Texts.
     */
    public Book(String bookTitle, JSONArray authors, String description, String url) {
        mTitle = bookTitle;
        mAuthors = authors.toString();
        mDescription = description;
        mUrl = url;
    }

    /**
     * Gets the string value of book title.
     */
    public String getBookTitle() {
        return mTitle;
    }

    /**
     * Gets the String value of authors.
     */
    public String getAuthors() {
        return mAuthors.substring(2, mAuthors.length() - 3).replace("\"", "");
    }

    /**
     * Gets the String value of book description.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Gets the String value of url of the book details.
     */
    public String getInfoLink() {
        return mUrl;
    }
}

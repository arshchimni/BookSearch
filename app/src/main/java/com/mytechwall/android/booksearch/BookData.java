package com.mytechwall.android.booksearch;

/**
 * Created by arshdeep chimni on 27-05-2017.
 */

public class BookData {

    private String author;
    private String title;
    private String description;
    private String imageUrl;
    private double rating;

    public BookData(String author, String title, String description, String imageUrl,double rating) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating=rating;
    }

    public double getRating() {
        return rating;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

package com.mytechwall.android.booksearch;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.R.attr.author;

/**
 * Created by arshdeep chimni on 27-05-2017.
 */

public final class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();


    private Utils(){

    }
    public static ArrayList<BookData> getBookData(String inputUrl) {
        ArrayList<BookData> books;

        URL url = createUrl(inputUrl);
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        System.out.println(jsonResponse);
        books = extractBookData(jsonResponse);
        return books;
    }

    private static ArrayList<BookData> extractBookData(String jsonResponse) {

        //TODO parse jason here
        String author="";
        ArrayList<BookData>bookData=new ArrayList<>();
        try {
        JSONObject fulldata = new JSONObject(jsonResponse);
         JSONArray items = fulldata.getJSONArray("items");
            for (int i = 0; i < items.length(); i++){
                System.out.println("ImageLINKS"+items.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks").toString());
                String url = items.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks").optString("thumbnail");
                String title=items.getJSONObject(i).getJSONObject("volumeInfo").optString("title");
                String description=items.getJSONObject(i).getJSONObject("volumeInfo").optString("description");
                double rating=items.getJSONObject(i).getJSONObject("volumeInfo").optDouble("averageRating");
                JSONArray authors=items.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors");
                for (int  j=0;j<authors.length();j++){
                     author +=authors.getString(j);
                }
                System.out.println(author);
                bookData.add(new BookData(author,title,description,url,rating));
                author="";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bookData;
    }


    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

    @NonNull
    private static String readFromStream(InputStream inputStream) {

        StringBuilder output=new StringBuilder();
        if (inputStream!=null){
            InputStreamReader isr=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(isr);
            String line=null;
            try {
                line=reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (line!=null){
                output.append(line);
                try {
                    line=reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output.toString();
    }


    private static URL createUrl(String inputUrl) {
        URL mUrl=null;
        if (inputUrl!=null){
            try {
                mUrl=new URL(inputUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return mUrl;
    }
}

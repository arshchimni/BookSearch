package com.mytechwall.android.booksearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    public void getSearchString(View view){
        EditText search=(EditText)findViewById(R.id.searchString);
        String query=search.getText().toString();
        System.out.println(query);
        if (query !=null) {
            Intent intent = new Intent(this, SearchResult.class);
            intent.putExtra("QUERY", query);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

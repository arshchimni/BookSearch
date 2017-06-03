package com.mytechwall.android.booksearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public void getSearchString(View view){
        EditText search=(EditText)findViewById(R.id.searchString);
        String query=search.getText().toString();
        StringBuilder output=new StringBuilder(query.trim());
        String modifirdQuery=output.toString().replaceAll(" ","+");

            if (!modifirdQuery.equals("")) {
                Intent intent = new Intent(this, SearchResult.class);
                intent.putExtra("QUERY", modifirdQuery);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Nothing To Search For", Toast.LENGTH_SHORT).show();

        }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

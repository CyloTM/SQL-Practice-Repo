package com.example.sql_first_try;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sql_first_try.models.Note;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        getSupportActionBar().hide();
        if(getIntent().hasExtra("Title")){
            Note note = getIntent().getParcelableExtra("Title");
            Log.d(TAG, "onCreate " + note.toString());
            TextView title = findViewById(R.id.text_view_toolbar_title);
            title.setText(note.getTitle());
        }
    }

    //View State
    //Edit State
}
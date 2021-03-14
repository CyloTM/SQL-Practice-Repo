package com.example.sql_first_try;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sql_first_try.models.Note;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if(getIntent().hasExtra("Title")){
            Note note = getIntent().getParcelableExtra("Title");
            Log.d(TAG, "onCreate " + note.toString());
            setTitle(note.getTitle());
        }
    }

    //View State
    //Edit State
}
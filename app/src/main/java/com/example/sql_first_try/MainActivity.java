package com.example.sql_first_try;

import android.os.Bundle;

import com.example.sql_first_try.adapters.NotesRecyclerAdapter;
import com.example.sql_first_try.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";


    // Ui components
    private EditText mEditTextName;
    private Button mButtonAdd;
    private Button mButtonViewData;
    private RecyclerView mRecyclerView;

    // Variables
    private ArrayList <Note> mNotes = new ArrayList();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);



        Note note = new Note("some title","some content","timestamp");

        Note note2 = new Note();
        note2.setContent("some other content");
        note2.setTitle("some other Title");
        note2.setTimestamp("some timestamp");


        Log.d(TAG, "onCreate my note" + note.toString());

        initRecyclerView();
        insertFakeNotes();


    }

    public void insertFakeNotes(){
        for(int i = 0;i<1000; i++){

            Note noteFake = new Note();
            noteFake.setTitle("Title #"+ i);
            noteFake.setContent("Content #" +i);
            noteFake.setTimestamp("Mar 2019");
            mNotes.add(noteFake);
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);

    }


}
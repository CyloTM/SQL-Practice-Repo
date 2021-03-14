package com.example.sql_first_try;

import android.os.Bundle;

import com.example.sql_first_try.adapters.NotesRecyclerAdapter;
import com.example.sql_first_try.models.Note;
import com.example.sql_first_try.util.VerticalSpacingItemDecorator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    public final static String TAG = "MainActivity";


    // Ui components
    private RecyclerView mRecyclerView;


    // Variables
    private ArrayList <Note> mNotes = new ArrayList();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        mRecyclerView = findViewById(R.id.recycler_view);



        Note note = new Note("some title","some content","timestamp");

        Note note2 = new Note();
        note2.setContent("some other content");
        note2.setTitle("some other Title");
        note2.setTimestamp("some timestamp");


        Log.d(TAG, "onCreate my note" + note.toString());

        initRecyclerView();
        insertFakeNotes();

        setSupportActionBar(findViewById(R.id.notes_toolbar));
        setTitle("Notes");
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
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);

    }


    @Override
    public void onNoteClicked(int position) {
        Log.d(TAG, "Note " + mNotes.get(position) + " has been clicked");

//        Intent intent = new Intent(this, NewActivity.java);
//        startActivity(intent);
    }
}
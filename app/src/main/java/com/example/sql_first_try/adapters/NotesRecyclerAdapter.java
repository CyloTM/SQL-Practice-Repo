package com.example.sql_first_try.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_first_try.R;
import com.example.sql_first_try.models.Note;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private ArrayList <Note> mNotes = new ArrayList();

    public NotesRecyclerAdapter(ArrayList<Note> notes) {
        mNotes = notes;
    }

    //Over ride methods for RecyclerView Adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.timestamp.setText(mNotes.get(i).getTimestamp());
        holder.title.setText(mNotes.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title , timestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Connect to (note list item) Layout
            title = itemView.findViewById(R.id.text_view_title);
            timestamp = itemView.findViewById(R.id.text_view_timestamp);
        }
    }
}

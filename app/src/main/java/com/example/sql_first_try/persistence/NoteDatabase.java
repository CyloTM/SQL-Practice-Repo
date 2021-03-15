package com.example.sql_first_try.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.sql_first_try.models.Note;

@Database(entities = {Note.class}, version=1)
public abstract class NoteDatabase extends RoomDatabase {
   public static final String DATABASE_NAME = "notes_db";

   private static NoteDatabase instance;

   static NoteDatabase getInstance(final Context context){
       if(instance == null)
           instance = Room.databaseBuilder(
                   context.getApplicationContext(),
                   NoteDatabase.class,
                   DATABASE_NAME
           ).build();
       return instance;
    }

    public abstract NoteDao getNoteDao();

}

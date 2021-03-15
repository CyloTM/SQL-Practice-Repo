package com.example.sql_first_try;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sql_first_try.models.Note;

public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener
{



    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;



    //Ui Components
    private LineEditText mLineEditText;
    private EditText mEditTextTitle;
    private TextView mTextViewTitle;
    private RelativeLayout mRelativeLayoutCheckContainer, mRelativeLayoutBackContainer;
    private ImageButton mToolbarCheck, mImageButtonBack;

    //Vars
    private boolean mIsNewNote;
    private Note mInitialNote;
    private GestureDetector mGestureDetector;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mLineEditText = findViewById(R.id.edit_text_note);
        mEditTextTitle = findViewById(R.id.edit_text_toolbar_title);
        mTextViewTitle = findViewById(R.id.text_view_toolbar_title);
        mRelativeLayoutBackContainer = findViewById(R.id.back_arrow_container);
        mRelativeLayoutCheckContainer = findViewById(R.id.check_container);
        mToolbarCheck = findViewById(R.id.toolbar_check);
        mImageButtonBack = findViewById(R.id.toolbar_back_arrow);


        getSupportActionBar().hide();
        if(getIncomingIntent()){
            //new note (EDIT MODE)
            setNewNoteProperties();
            enableEditMode();
            enableContentInteraction();

        }
        else{
            //NOT a new note (VIEW MODE)
            setNoteProperties();
            disableContentInteraction();

        }

        setListener();
    }

    private void enableEditMode(){
        mRelativeLayoutBackContainer.setVisibility(View.GONE);
        mRelativeLayoutCheckContainer.setVisibility(View.VISIBLE);

        mTextViewTitle.setVisibility(View.GONE);
        mEditTextTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;

        enableContentInteraction();
    }

    private void disableEditMode(){
        mRelativeLayoutBackContainer.setVisibility(View.VISIBLE);
        mRelativeLayoutCheckContainer.setVisibility(View.GONE);

        mTextViewTitle.setVisibility(View.VISIBLE);
        mEditTextTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;
        disableContentInteraction();

    }

    private void disableContentInteraction(){
        mLineEditText.setKeyListener(null);
        mLineEditText.setFocusable(false);
        mLineEditText.setFocusableInTouchMode(false);
        mLineEditText.setCursorVisible(false);
        mLineEditText.clearFocus();

    }

    private void enableContentInteraction(){
        mLineEditText.setKeyListener(new EditText (this).getKeyListener());
        mLineEditText.setFocusable(true);
        mLineEditText.setFocusableInTouchMode(true);
        mLineEditText.setCursorVisible(true);
        mLineEditText.requestFocus();

    }

    private void setListener(){
        mLineEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);
        mTextViewTitle.setOnClickListener(this);
        mToolbarCheck.setOnClickListener(this);
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "getIncomingIntent: " + mInitialNote.toString());
            mIsNewNote = false;
            mMode = EDIT_MODE_DISABLED;
            return false;
        }
        mMode = EDIT_MODE_ENABLED;
        mIsNewNote = true;
        return true;
    }

    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null){
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void setNoteProperties(){
        mEditTextTitle.setText(mInitialNote.getTitle());
        mTextViewTitle.setText(mInitialNote.getTitle());
        mLineEditText.setText(mInitialNote.getContent());
    }

    private void setNewNoteProperties(){
        mEditTextTitle.setText("Note Title");
        mTextViewTitle.setText("Note Title");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        enableEditMode();
        Log.d(TAG, "onDoubleTab: double tapped!");
        return false;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.toolbar_check:{
                disableEditMode();
                hideSoftKeyboard();
                break;
            }

            case R.id.text_view_toolbar_title:{
                enableEditMode();
                mEditTextTitle.requestFocus();
                //Sets cursor at end of string
                mEditTextTitle.setSelection(mEditTextTitle.length());
                break;
            }

        }

    }

    @Override
    public void onBackPressed() {
        if(mMode == EDIT_MODE_ENABLED){
            //Clicks check mark
            onClick(mToolbarCheck);
        }
        else{
            super.onBackPressed();
        }
    }
}
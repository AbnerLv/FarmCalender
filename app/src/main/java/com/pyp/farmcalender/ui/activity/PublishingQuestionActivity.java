package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import com.pyp.farmcalender.R;



public class PublishingQuestionActivity extends Activity {

    private static final String TAG = "PublishingQuestionActivity";
    private EditText mContentEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.comment_answer);
        init();

    }

    private void init(){

        Intent intent = getIntent();
        String messageId = intent.getStringExtra("messageId");
        mContentEditView = (EditText)findViewById(R.id.et_comment_answer_content);
        String content = mContentEditView.getText().toString();
        requestData(messageId,content);
    }

    private void requestData(String messageId,String content){

    }



}


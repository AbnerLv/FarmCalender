package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.CommentEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.GetCommentsByIdHandler;
import com.pyp.farmcalender.ui.adapter.CommentAdapter;

import java.util.List;


public class BoardDetailActivity extends Activity {

    private static final String TAG = "BoardDetailActivity";
    private CommentAdapter commentAdapter;

    private ListView mAnswerListView;
    private TextView mContentTextView;
    private ImageView ivReply;
    private EditText etReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.message_detail_layout);
        init();

    }

    private void init(){
        mAnswerListView = (ListView)findViewById(R.id.lv_message_detail_all_answer);
        mContentTextView = (TextView)findViewById(R.id.tv_message_detail_content);
        ivReply = (ImageView)findViewById(R.id.iv_reply);
        etReply = (EditText)findViewById(R.id.et_reply);
        Log.i(TAG,"BoardDetailActivity init------");
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String messageId = intent.getStringExtra("message_id");

        Log.i(TAG, "BoardDetailActivity messageId------" + messageId);

        mContentTextView.setText(content + "");
        requestData(messageId);
        ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replayContent = etReply.getText().toString();
                Intent intent = getIntent();
                String messageId = intent.getStringExtra("messageId");
                Intent intentA = new Intent(BoardDetailActivity.this, BoardDetailActivity.class);
                startActivity(intentA);
            }
        });




    }

    private void requestData(String messageId){
        MessageBoardService.getInstance().getCommentsById(getApplicationContext(), messageId, new GetCommentsByIdHandler() {
            @Override
            public void onSuccess(String commentEntitys) {
                Gson mGson = new Gson();
                List<CommentEntity> comments = mGson.fromJson(commentEntitys, new TypeToken<List<CommentEntity>>() {
                }.getType());
                commentAdapter = new CommentAdapter(getApplicationContext());
                commentAdapter.setCommentEntitys(comments);
                mAnswerListView.setAdapter(commentAdapter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}


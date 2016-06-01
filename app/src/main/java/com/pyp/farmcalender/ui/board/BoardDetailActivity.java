package com.pyp.farmcalender.ui.board;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_detail_layout);
        init();

    }

    private void init(){
        mAnswerListView = (ListView)findViewById(R.id.lv_message_detail_all_answer);
        mContentTextView = (TextView)findViewById(R.id.tv_message_detail_content);
        btnAnswer = (Button)findViewById(R.id.btn_answer_question);
        Log.i(TAG,"BoardDetailActivity init------");
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String messageId = intent.getStringExtra("message_id");

        Log.i(TAG,"BoardDetailActivity messageId------" + messageId);

        mContentTextView.setText(content + "");
        requestData(messageId);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



}


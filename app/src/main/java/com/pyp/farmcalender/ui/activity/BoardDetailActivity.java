package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.CommentEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.AddCommentHandler;
import com.pyp.farmcalender.service.handler.GetCommentsByIdHandler;
import com.pyp.farmcalender.ui.adapter.CommentAdapter;
import com.pyp.farmcalender.utils.calendar.DateUtil;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;


public class BoardDetailActivity extends Activity {

    private static final String TAG = "BoardDetailActivity";
    public static final int RESULT_CODE = 3;
    public static final int REQUEST_CODE = 3;
    private CommentAdapter commentAdapter;

    private ListView mAnswerListView;
    private TextView mContentTextView;
    private ImageView ivReply;
    private EditText etReply;
    private String messageId;
    private CommentEntity commentEntity;
    private List<CommentEntity> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.message_detail_layout);
        init();

    }

    private void init() {
        mAnswerListView = (ListView) findViewById(R.id.lv_message_detail_all_answer);
        mContentTextView = (TextView) findViewById(R.id.tv_message_detail_content);
        ivReply = (ImageView) findViewById(R.id.iv_reply);
        etReply = (EditText) findViewById(R.id.et_reply);
        Log.i(TAG, "BoardDetailActivity init------");
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        messageId = intent.getStringExtra("message_id");

        Log.i(TAG, "BoardDetailActivity messageId------" + messageId);

        mContentTextView.setText(content + "");
        requestData(messageId);

        ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageBoardService.getInstance().addComment(getApplicationContext(), getCommentJSON(messageId), new AddCommentHandler() {
                    @Override
                    public void addSuccess(int code) {
                        etReply.setText("");
                        if(code > 0){
                            commentAdapter = new CommentAdapter(getApplicationContext());
                            comments.add(commentEntity);
                            commentAdapter.setCommentEntitys(comments);
                            mAnswerListView.setAdapter(commentAdapter);
                        }else{
                            Toast.makeText(getApplicationContext(),"网络出现问题，添加失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }

    private JSONObject getCommentJSON(String messageId) {

        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        Integer userId = sp.getInt("userId", 0);

        String replayContent = etReply.getText().toString();

        commentEntity = new CommentEntity();
        commentEntity.setMessageId(messageId);
        commentEntity.setUserId(userId + "");
        commentEntity.setContent(replayContent);
        commentEntity.setTime(DateUtil.getCurrentTime());

        Gson gson = new Gson();
        String json = gson.toJson(commentEntity);
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void requestData(String messageId) {
        MessageBoardService.getInstance().getCommentsById(getApplicationContext(), messageId, new GetCommentsByIdHandler() {
            @Override
            public void onSuccess(String commentEntitys) {
                Gson mGson = new Gson();
                comments = mGson.fromJson(commentEntitys, new TypeToken<List<CommentEntity>>() {
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


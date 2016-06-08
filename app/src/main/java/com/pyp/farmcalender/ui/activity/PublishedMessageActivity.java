package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.GetPublishedMessagesHandler;
import com.pyp.farmcalender.ui.adapter.MessageBoardAdapter;

import java.util.List;


public class PublishedMessageActivity extends Activity {

    private static final String TAG = "PublishedMessageActivity";
    private ListView publishedMessageListView;
    private MessageBoardAdapter messageBoardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.published_message_list);
        init();

    }

    private void init(){
        publishedMessageListView = (ListView)findViewById(R.id.lv_published_message);
        requestData();
    }

    private void requestData(){
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        MessageBoardService.getInstance().getPublishedMessages(getApplicationContext(), userId, new GetPublishedMessagesHandler() {
            @Override
            public void onSuccess(List<MessageBoardEntity> entitys) {
                if(entitys != null && entitys.size() != 0){
                    messageBoardAdapter = new MessageBoardAdapter(getApplicationContext());
                    messageBoardAdapter.setMessageBoardEntities(entitys);
                    publishedMessageListView.setAdapter(messageBoardAdapter);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        MessageBoardService.getInstance().cancelPendingRequests();
        super.onStop();
    }
}


package com.pyp.farmcalender.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.GetMessageInfosHandler;
import com.pyp.farmcalender.ui.activity.AddMessagesActivity;
import com.pyp.farmcalender.ui.activity.BoardDetailActivity;
import com.pyp.farmcalender.ui.adapter.MessageBoardAdapter;

import java.util.List;


public class MessageBoardFragment extends Fragment {


    private static final String TAG = "MessageBoardFragment";
    public static final int REQUEST_CODE = 1;
    private ListView mBoardListView;
    private MessageBoardAdapter messageBoardAdapter;
    private Button btnRelease;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.message_board_list,
                container, false);
        return contactsLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    // 初始化控件
    private void init() {
        mBoardListView = (ListView)getActivity().findViewById(R.id.lv_message_board);
        btnRelease = (Button)getActivity().findViewById(R.id.btn_release);
        btnRelease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMessagesActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        requestData();

    }

    private void requestData(){
        MessageBoardService.getInstance().getMessageInfos(getActivity(), new GetMessageInfosHandler() {
            @Override
            public void onSuccess(String messageEntitys) {
                Log.d(TAG, messageEntitys + "");
                Gson mGson = new Gson();
                List<MessageBoardEntity> messages = mGson.fromJson(messageEntitys, new TypeToken<List<MessageBoardEntity>>() {
                }.getType());
                messageBoardAdapter = new MessageBoardAdapter(getActivity());
                messageBoardAdapter.setMessageBoardEntities(messages);
                mBoardListView.setAdapter(messageBoardAdapter);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case AddMessagesActivity.RESULT_CODE:  requestData();
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        MessageBoardService.getInstance().cancelPendingRequests();
        super.onStop();
    }
}

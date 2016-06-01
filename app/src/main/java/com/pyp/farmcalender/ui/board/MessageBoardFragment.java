package com.pyp.farmcalender.ui.board;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.GetMessageInfosHandler;
import com.pyp.farmcalender.ui.adapter.MessageBoardAdapter;

import java.util.List;


public class MessageBoardFragment extends Fragment implements OnClickListener {


    private static final String TAG = "MessageBoardFragment";
    private ListView mBoardListView;
    private MessageBoardAdapter messageBoardAdapter;
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
        requestData();

    }

    private void requestData(){
        MessageBoardService.getInstance().getMessageInfos(getActivity(), new GetMessageInfosHandler() {
            @Override
            public void onSuccess(String messageEntitys) {
                Log.d(TAG,messageEntitys+"");
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
    public void onClick(View v) {

    }
}

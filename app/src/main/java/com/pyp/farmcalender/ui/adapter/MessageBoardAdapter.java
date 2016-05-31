package com.pyp.farmcalender.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.ui.view.BoardItemView;

import java.util.List;

/**
 * Created by lzb on 2016/5/24.
 */


public class MessageBoardAdapter extends BaseAdapter {
    private static final String TAG = "NoticeAdapter";
    private List<MessageBoardEntity> messageBoardEntities;

    private Context mContext;

    public MessageBoardAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMessageBoardEntities(List<MessageBoardEntity> messageBoardEntities){
        this.messageBoardEntities = messageBoardEntities;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.e(TAG,"NoticeAdapter "+messageBoardEntities.size()+"");
        return messageBoardEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return messageBoardEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messageBoardEntities.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView,
            ViewGroup parent) {
       return BoardItemView.getInstance(mContext, convertView, messageBoardEntities.get(position));
    }
}

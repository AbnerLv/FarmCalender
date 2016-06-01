package com.pyp.farmcalender.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pyp.farmcalender.entity.CommentEntity;
import com.pyp.farmcalender.ui.view.CommentItemView;

import java.util.List;


public class CommentAdapter extends BaseAdapter {
    private static final String TAG = "CommentAdapter";
    private List<CommentEntity> commentEntitys;

    private Context mContext;

    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setCommentEntitys(List<CommentEntity> commentEntitys) {
        this.commentEntitys = commentEntitys;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.e(TAG, "CommentAdapter " + commentEntitys.size() + "");
        return commentEntitys.size();
    }

    @Override
    public Object getItem(int position) {
        return commentEntitys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commentEntitys.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        return CommentItemView.getInstance(mContext, convertView, commentEntitys.get(position));
    }
}

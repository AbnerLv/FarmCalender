package com.pyp.farmcalender.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.CommentEntity;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.ui.board.BoardDetailActivity;


public class CommentItemView {

    public static View getInstance(final Context context, View commentView,final CommentEntity commentEntity) {
        if (commentView == null) {
            commentView = View.inflate(context, R.layout.comment_list_item, null);
        }

        TextView commentTextView = (TextView)commentView.findViewById(R.id.tv_comment_content);
        commentTextView.setText(commentEntity.getContent()+"");
        return commentView;
    }
}

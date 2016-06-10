package com.pyp.farmcalender.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.ui.activity.BoardDetailActivity;



public class BoardItemView {

    public static View getInstance(final Context context, View boardView,final MessageBoardEntity mBoardEntity) {
        if (boardView == null) {
            boardView = View.inflate(context, R.layout.message_board_list_item, null);
        }

        TextView userTextView = (TextView)boardView.findViewById(R.id.tv_message_board_username);
        TextView timeTextView = (TextView)boardView.findViewById(R.id.tv_message_board_time);
        TextView contentTextView = (TextView)boardView.findViewById(R.id.tv_message_board_content);
        TextView numTextView = (TextView)boardView.findViewById(R.id.tv_message_board_num);
        userTextView.setText(mBoardEntity.getUsername());
        timeTextView.setText(mBoardEntity.getTime());
        contentTextView.setText(mBoardEntity.getContent());
        numTextView.setText("回答(" + mBoardEntity.getCommentNum() + ")");
        boardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BoardDetailActivity.class);
                intent.putExtra("message_id",mBoardEntity.getId());
                intent.putExtra("content", mBoardEntity.getContent());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return boardView;
    }
}

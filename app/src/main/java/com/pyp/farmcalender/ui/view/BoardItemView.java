package com.pyp.farmcalender.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.ui.board.BoardDetailActivity;


/**
 * Created by lzb on 2016/5/24.
 */
public class BoardItemView {

    public static View getInstance(final Context context, View noticeView,final MessageBoardEntity mBoardEntity) {
        if (noticeView == null) {
            noticeView = View.inflate(context, R.layout.message_board_list_item, null);
        }


        noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BoardDetailActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return noticeView;
    }
}

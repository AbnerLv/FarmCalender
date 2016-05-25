package com.pyp.farmcalender.ui.board;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.pyp.farmcalender.R;


public class MessageBoardFragment extends Fragment implements OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.message_board_layout,
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

    }


    @Override
    public void onClick(View v) {

    }
}

package com.pyp.farmcalender.ui.board;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pyp.farmcalender.R;


public class MessageBoardFragment extends Fragment implements OnClickListener {


    private ListView boardListView;
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
        boardListView = (ListView)getActivity().findViewById(R.id.lv_message_board);
    }


    @Override
    public void onClick(View v) {

    }
}

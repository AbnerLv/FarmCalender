package com.pyp.farmcalender.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.ui.activity.PublishedMessageActivity;


public class SettingFragment extends Fragment implements OnClickListener {



    private LinearLayout publishedMessageLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.setting_layout,
                container, false);
        return settingLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        publishedMessageLayout = (LinearLayout)getActivity().findViewById(R.id.layout_published_message);
        publishedMessageLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_published_message :
                Intent intent = new Intent(getActivity(),PublishedMessageActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

}

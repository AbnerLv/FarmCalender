package com.pyp.farmcalender.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.ui.activity.PublishedMessageActivity;
import com.pyp.farmcalender.ui.activity.StartActivity;


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
            case R.id.tv_exit :
                SharedPreferences sp = getActivity().getSharedPreferences(
                        "PersonInfo", getActivity().MODE_PRIVATE);
                sp.edit().clear().commit();
                Intent intent1 = new Intent(getActivity(), StartActivity.class);
                startActivity(intent1);
                break;
            default:
                break;

        }
    }

}

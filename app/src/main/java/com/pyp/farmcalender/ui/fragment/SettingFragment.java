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
import android.widget.TextView;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.ui.activity.LoginActivity;
import com.pyp.farmcalender.ui.activity.ModifyPasswordActivity;
import com.pyp.farmcalender.ui.activity.PublishedMessageActivity;
import com.pyp.farmcalender.ui.activity.ShowPerInfoActivity;
import com.pyp.farmcalender.ui.activity.StartActivity;


public class SettingFragment extends Fragment implements OnClickListener {



    private LinearLayout publishedMessageLayout;
    private LinearLayout personInfoLayout;
    private LinearLayout exitLayout;
    private TextView tvPersonName;
    private TextView tvPersonProfile;

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
        SharedPreferences sp = getActivity().getSharedPreferences("UserInfo", 0);
        tvPersonName = (TextView)getActivity().findViewById(R.id.tv_person_name);
        tvPersonProfile = (TextView)getActivity().findViewById(R.id.tv_person_profile);
        tvPersonName.setText(sp.getString("username",null)+"");
        tvPersonProfile.setText(sp.getString("profile",null)+"");

        publishedMessageLayout = (LinearLayout)getActivity().findViewById(R.id.layout_published_message);
        publishedMessageLayout.setOnClickListener(this);

        personInfoLayout = (LinearLayout)getActivity().findViewById(R.id.layout_person_info);
        personInfoLayout.setOnClickListener(this);

        exitLayout = (LinearLayout)getActivity().findViewById(R.id.layout_exit);
        exitLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_published_message :
                Intent intent = new Intent(getActivity(),PublishedMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_person_info :
                Intent personIntent = new Intent(getActivity(),ShowPerInfoActivity.class);
                startActivity(personIntent);
                break;

            case R.id.layout_modify_password :
                Intent modifyIntent = new Intent(getActivity(),ModifyPasswordActivity.class);
                startActivity(modifyIntent);
                break;

            case R.id.layout_exit :
                SharedPreferences sp= getActivity().getSharedPreferences(
                        "UserInfo", getActivity().MODE_PRIVATE);
                sp.edit().clear().commit();
                Intent exitIntent = new Intent(getActivity(),LoginActivity.class);
                startActivity(exitIntent);
                break;
            default:
                break;

        }
    }

}

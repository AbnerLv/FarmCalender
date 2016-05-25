package com.pyp.farmcalender.ui.other;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.pyp.farmcalender.R;


public class OtherFragment extends Fragment implements OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.other_layout,
                container, false);
        return settingLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {

    }

    @Override
    public void onClick(View v) {

    }

}

package com.pyp.farmcalender.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.pyp.farmcalender.R;

public class ShowPerInfoActivity extends Activity{


    private TextView tvPerInfoNickname;
    private TextView tvPerInfoSex;
    private TextView tvPerInfoAge;
    private TextView tvPerInfoPhoneNum;
    private TextView tvPerInfoEmail;
    private TextView tvPerInfoCity;
    private TextView tvPerInfoAddress;
    private TextView tvPerInfoProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.persion_info_show_layout);
        init();
    }


    private void init() {

        tvPerInfoNickname = (TextView) findViewById(R.id.tv_per_info_nickname);
        tvPerInfoSex = (TextView) findViewById(R.id.tv_per_info_sex);
        tvPerInfoAge = (TextView) findViewById(R.id.tv_per_info_age);
        tvPerInfoPhoneNum = (TextView) findViewById(R.id.tv_per_info_phone_num);
        tvPerInfoEmail = (TextView) findViewById(R.id.tv_per_info_email);
        tvPerInfoCity = (TextView) findViewById(R.id.tv_per_info_city);
        tvPerInfoAddress = (TextView) findViewById(R.id.tv_per_info_address);
        tvPerInfoProfile = (TextView) findViewById(R.id.tv_show_profile);

        setPerInfo();
    }

    private void setPerInfo() {
        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        tvPerInfoNickname.setText(sp.getString("username", null));
        tvPerInfoSex.setText(sp.getInt("sex", 0) == 1 ?"男":"女");
        tvPerInfoAge.setText(sp.getInt("age", 0)+"");
        tvPerInfoPhoneNum.setText(sp.getString("phone", null));
        tvPerInfoEmail.setText(sp.getString("email", null));
        tvPerInfoCity.setText(sp.getString("city", null));
        tvPerInfoAddress.setText(sp.getString("address", null));
        tvPerInfoProfile.setText(sp.getString("profile", null));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_per_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ActionBar点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.per_info_edit:
            Intent intent = new Intent(ShowPerInfoActivity.this,ModifyPerInfoActivity.class);
            startActivity(intent);
            break;
        case R.id.home:
            finish();
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}

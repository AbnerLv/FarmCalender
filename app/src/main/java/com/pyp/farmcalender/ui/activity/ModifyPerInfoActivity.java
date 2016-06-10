package com.pyp.farmcalender.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.service.UserService;
import com.pyp.farmcalender.service.handler.ModifyPerInfoHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ModifyPerInfoActivity extends Activity implements
        OnCheckedChangeListener {

    private EditText etPerInfoNickname;
    private RadioGroup radioPerInfoSex;
    private RadioButton radioPerInfoMale;
    private RadioButton radioPerInfoFemale;
    private EditText etPerInfoAge;
    private EditText etPerInfoPhoneNo;
    private EditText etPerInfoEmail;

    private EditText etPerInfoCity;
    private EditText etPerInfoAddress;

    private EditText etPerInfoProfile;
    private Button btnPerInfoSubmit;
    private int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.persion_info_edit_layout);
        init();
    }

    private void init() {
        etPerInfoNickname = (EditText) findViewById(R.id.et_per_info_nickname);
        radioPerInfoSex = (RadioGroup) findViewById(R.id.radio_per_info_sex);
        radioPerInfoMale = (RadioButton) findViewById(R.id.radio_per_info_male);
        radioPerInfoFemale = (RadioButton) findViewById(R.id.radio_per_info_female);
        etPerInfoAge = (EditText) findViewById(R.id.et_per_info_age);
        etPerInfoPhoneNo = (EditText) findViewById(R.id.et_per_info_phone_no);
        etPerInfoEmail = (EditText) findViewById(R.id.et_per_info_email);

        etPerInfoCity = (EditText) findViewById(R.id.et_per_info_city);
        etPerInfoAddress = (EditText) findViewById(R.id.et_per_info_address);
        btnPerInfoSubmit = (Button) findViewById(R.id.btn_edit_per_info_submit);

        etPerInfoProfile = (EditText) findViewById(R.id.et_modify_profile);

        setPerInfo();

        radioPerInfoSex.setOnCheckedChangeListener(this);

        btnPerInfoSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService.getInstance().modifyPerInfo(getApplicationContext(), getPersionJSON(), new ModifyPerInfoHandler() {
                    @Override
                    public void addSuccess(int code) {
                        if (code > 0) {
                            dialog();
                        } else {
                            Toast.makeText(ModifyPerInfoActivity.this,
                                    "修改个人信息失败，请耐心等待5秒后再次尝试",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void setPerInfo() {
        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        etPerInfoNickname.setText(sp.getString("username", null));
        if ("1".equals(sp.getInt("sex", 0) + "")) {
            radioPerInfoMale.setChecked(true);
        } else if ("0".equals(sp.getInt("sex", 0) + "")) {
            radioPerInfoFemale.setChecked(true);
        }
        etPerInfoAge.setText(sp.getInt("age", 0));
        etPerInfoPhoneNo.setText(sp.getString("phone", null));
        etPerInfoEmail.setText(sp.getString("email", null));
        etPerInfoCity.setText(sp.getString("city", null));
        etPerInfoAddress.setText(sp.getString("address", null));
        etPerInfoProfile.setText(sp.getString("profile", null));
    }



    private JSONObject getPersionJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("username", etPerInfoNickname.getText().toString());
            json.put("sex", sex);
            json.put("age", etPerInfoAge.getText().toString().trim());
            json.put("phone", etPerInfoPhoneNo.getText().toString());
            json.put("email", etPerInfoEmail.getText().toString().trim());

            json.put("city", etPerInfoCity.getText().toString().trim());
            json.put("address", etPerInfoAddress.getText().toString());

            json.put("profile", etPerInfoProfile.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radio_per_info_male) {
            sex = 1;
        } else if (checkedId == R.id.radio_per_info_female) {
            sex = 0;
        }
    }

    /**
     * 弹出对话框
     */
    protected void dialog() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setMessage("个人信息已成功修改,对话框2秒后自动关闭！");
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                builder.dismiss();
                t.cancel();
                ModifyPerInfoActivity.this.finish();
            }
        }, 2000);
    }
}

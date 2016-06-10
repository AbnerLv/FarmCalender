package com.pyp.farmcalender.ui.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.service.UserService;
import com.pyp.farmcalender.service.handler.ForgetPasswordHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends Activity implements OnClickListener {

    private final static String TAG = "ForgetPasswordActivity";
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnBackSubmit;
    private Button btnBackReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.password_forget_layout);
        init();
    }

    private void init() {

        etUsername = (EditText)findViewById(R.id.et_forget_username);
        etPhone = (EditText)findViewById(R.id.et_forget_phone);
        etEmail = (EditText)findViewById(R.id.et_forget_email);
        btnBackSubmit = (Button)findViewById(R.id.btn_forget_password_submit);
        btnBackReset = (Button)findViewById(R.id.btn_forget_password_reset);
        btnBackSubmit.setOnClickListener(this);
        btnBackReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.btn_forget_password_submit:
            if (chkEdit()) {
                JSONObject json = new JSONObject();
                try {
                    json.put("username", etUsername.getText().toString());
                    json.put("phone", etPhone.getText().toString());
                    json.put("email", etEmail.getText().toString());
                    UserService.getInstance().forgetPassword(getApplicationContext(), json, new ForgetPasswordHandler() {
                        @Override
                        public void success(String password) {
                            dialog(password);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            break;

        case R.id.btn_forget_password_reset:
            etUsername.getText().clear();
            etPhone.getText().clear();
            etEmail.getText().clear();
            break;

        default:
            break;
        }
    }

    private boolean chkEdit() {
        if (etUsername.getText().toString() == null
                || "".equals(etUsername.getText().toString())) {
            etUsername.requestFocus();
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etPhone.getText().toString() == null
                || "".equals(etPhone.getText().toString())) {
            etPhone.requestFocus();
            Toast.makeText(this, "手机号码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 弹出对话框
     * 用户名
     * @param password
     *            密码
     */
    protected void dialog(String password) {
        Builder builder = new Builder(this);
        builder.setMessage("您的登陆密码为：" + password);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(ForgetPasswordActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UserService.getInstance().cancelPendingRequests();
    }
}

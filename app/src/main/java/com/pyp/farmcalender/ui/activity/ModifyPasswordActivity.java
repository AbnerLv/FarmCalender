package com.pyp.farmcalender.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.service.UserService;
import com.pyp.farmcalender.service.handler.ModifyPasswordHandler;

public class ModifyPasswordActivity extends Activity implements OnClickListener {


    private EditText etUsername;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private Button btnModifyPasswordSubmit;
    private Button btnModifyPasswordReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.password_modify_layout);
        init();
    }

    private void init() {

        etUsername = (EditText)findViewById(R.id.et_modify_username);
        etOldPassword = (EditText)findViewById(R.id.et_modify_old_password);
        etNewPassword = (EditText)findViewById(R.id.et_modify_new_password);

        SharedPreferences sp = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String username = sp.getString("username", null);
        if (!(username == null || "".equals(username))) {
            etUsername.setText(username);
            etOldPassword.requestFocus();
        }
        btnModifyPasswordSubmit = (Button)findViewById(R.id.btn_modify_password_submit);
        btnModifyPasswordReset = (Button)findViewById(R.id.btn_modify_password_reset);
        btnModifyPasswordSubmit.setOnClickListener(this);
        btnModifyPasswordReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.btn_modify_password_submit:
            final String username = etUsername.getText().toString();
            final String oldPassword = etOldPassword.getText().toString();
            final String newPassword = etNewPassword.getText().toString();
            if (oldPassword.equals(newPassword)){
                Toast.makeText(ModifyPasswordActivity.this, "新密码与旧密码相同！", Toast.LENGTH_SHORT).show();
                etOldPassword.getText().clear();
                etNewPassword.getText().clear();
            }
            try {
                JSONObject json = new JSONObject();
                json.put("userName", username);
                json.put("oldPassword", etOldPassword.getText().toString()
                        .trim());
                json.put("newPassword", etNewPassword);
                UserService.getInstance().modifyPassword(
                        getApplicationContext(), json, new ModifyPasswordHandler() {
                            @Override
                            public void addSuccess(int code) {
                                if(code > 0){
                                    dialog();
                                }else{
                                    Toast.makeText(getApplicationContext(),"服务器端出问题，请等会再试...",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            break;

        case R.id.btn_modify_password_reset:
            etUsername.getText().clear();
            etOldPassword.getText().clear();
            etNewPassword.getText().clear();
            break;

        default:
            break;
        }
    }

    /**
     * 修改成功弹出对话框
     */
    private void dialog() {

        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("密码已修改成功，请重新登录！");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(ModifyPasswordActivity.this,LoginActivity.class);
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
}

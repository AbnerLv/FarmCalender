package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pyp.farmcalender.MainActivity;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.UserEntity;
import com.pyp.farmcalender.service.UserService;
import com.pyp.farmcalender.service.handler.LoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends Activity {


    private final static String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignOut;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    public void init(){
        etUsername = (EditText)findViewById(R.id.et_login_username);
        etPassword = (EditText)findViewById(R.id.et_login_password);
        btnSignIn = (Button)findViewById(R.id.btn_sign_in);
        btnSignOut = (Button)findViewById(R.id.btn_sign_out);




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                Log.i(TAG,"username = " + username);
                final JSONObject json = new JSONObject();
                try {
                    json.put("username", username);
                    json.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,json.toString());
                UserService.getInstance().checkLogin(json, getApplicationContext(), new LoginHandler() {
                    @Override
                    public void onSuccess(UserEntity userEntity) {
                            progressDialog();
                    }
                });
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 登陆进程框
     */
    private void progressDialog() {
        final ProgressDialog proDialog = new ProgressDialog(LoginActivity.this);
        proDialog.setTitle("验证中");
        proDialog.setMessage("正在登陆，请稍后…");
        proDialog.setIndeterminate(true);
        proDialog.setCancelable(false);
        proDialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                proDialog.dismiss();
                t.cancel();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }


}


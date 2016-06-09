package com.pyp.farmcalender.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pyp.farmcalender.R;
import com.pyp.farmcalender.service.UserService;
import com.pyp.farmcalender.service.handler.RegisterHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;

    private Button btnRegister;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        init();
    }

    private void init() {
        etUsername = (EditText) findViewById(R.id.et_register_username);
        etPassword = (EditText) findViewById(R.id.et_register_password);
        etEmail = (EditText) findViewById(R.id.et_register_email);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnBack = (Button)findViewById(R.id.btn_register_back);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                final JSONObject json = new JSONObject();
                try {
                    json.put("username", username);
                    json.put("password", password);
                    json.put("email", email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                UserService.getInstance().register(getApplicationContext(), json, new RegisterHandler() {
                    @Override
                    public void register(int success) {
                        if (success == 1) {
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG);
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}

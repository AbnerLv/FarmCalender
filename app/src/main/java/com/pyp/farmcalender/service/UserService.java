package com.pyp.farmcalender.service;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pyp.farmcalender.constant.Constant;
import com.pyp.farmcalender.service.handler.LoginHandler;
import com.pyp.farmcalender.service.handler.RegisterHandler;
import com.pyp.farmcalender.service.response.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/28.
 */
public class UserService {

    private static UserService instance;

    public static UserService  getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }


    /**
     * 登陆验证
     *
     * @param json
     *            username,password 封装json文件
     * @param context
     *            LoginActivity.this
     */
    public void checkLogin(JSONObject json, final Context context,final LoginHandler loginHandler) {
        String LOGIN_URL = Constant.URL + "login.json";
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, LOGIN_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        String password = null;
                        try {
                            password = response.getString("e  RequestQueue mQueue = Volley.newRequestQueue(context);mp_password").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (password == null || "0".equals(password)) {

                            Toast.makeText(context, "输入的用户名或密码有错",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            loginHandler.onSuccess(1);
                        }
                    }
                }, new ErrorResponse(context));

        mQueue.add(jsonObjectRequest);
    }

    public void register(final Context context, final JSONObject json, final RegisterHandler handler) {
        final String REGISTER_URL = Constant.URL + "register.json";
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                REGISTER_URL, json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int success = Integer.parseInt(response
                            .getString("success"));
                    if (success == 1) {
                        handler.register(success);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);

    }




}

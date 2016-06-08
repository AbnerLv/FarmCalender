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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.constant.Constant;
import com.pyp.farmcalender.entity.UserEntity;
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
    private final static String TAG = "UserService---";

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
                    public void onResponse(JSONObject userEntity) {
                        Log.d(TAG, userEntity.toString());
                        Gson gson = new Gson();
                        UserEntity entity =  gson.fromJson(userEntity.toString(), new TypeToken<UserEntity>() {
                        }.getType());
                        if (entity == null) {
                            Toast.makeText(context, "输入的用户名或密码有错",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            loginHandler.onSuccess(entity);
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
                        handler.register(success);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);

    }




}

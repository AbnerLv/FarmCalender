package com.pyp.farmcalender.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyp.farmcalender.constant.Constant;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.entity.UserEntity;
import com.pyp.farmcalender.service.handler.AddCommentHandler;
import com.pyp.farmcalender.service.handler.GetCommentsByIdHandler;
import com.pyp.farmcalender.service.handler.GetMessageInfosHandler;
import com.pyp.farmcalender.service.handler.GetPublishedMessagesHandler;
import com.pyp.farmcalender.service.response.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class MessageBoardService {

    private static final String TAG = "MessageBoardService";
    public static MessageBoardService instance;
    private RequestQueue mRequestQueue;


    public static MessageBoardService getInstance() {
        if (instance == null) {
            instance = new MessageBoardService();
        }
        return instance;
    }


    /**
     * 获取任务信息
     *
     * @param context
     */
    public void getMessageInfos(Context context, final GetMessageInfosHandler getMessageInfosHandler) {

        String GET_MESSAGE_INFOS_URL = Constant.URL + "get_message_infos.json";
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(GET_MESSAGE_INFOS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null || "".equals(response)) {

                        } else {
                            getMessageInfosHandler.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError + "");
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    /**
     * 获取任务信息
     *
     * @param context
     */
    public void getCommentsById(Context context, String messageId, final GetCommentsByIdHandler getCommentsByIdHandler) {

        String GET_COMMENTS_BY_ID_URL = Constant.URL + "getCommentById?messageId=" + messageId;
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(GET_COMMENTS_BY_ID_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null || "".equals(response)) {

                        } else {
                            getCommentsByIdHandler.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError + "");
            }
        });
        mRequestQueue.add(mStringRequest);
    }


    /**
     * 获取任务信息
     *
     * @param context
     */
    public void getPublishedMessages(Context context, Integer userId, final GetPublishedMessagesHandler getPublishedMessagesHandler) {

        String GET_PUBLISHED_MESSAGES_URL = Constant.URL + "getPublishedMessages.json?userId=" + userId;
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(GET_PUBLISHED_MESSAGES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<MessageBoardEntity> entitys = gson.fromJson(response, new TypeToken<List<MessageBoardEntity>>() {
                        }.getType());
                        getPublishedMessagesHandler.onSuccess(entitys);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError + "");
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    /**
     * 发布信息
     *
     * @param context
     */
    public void addComment(Context context, JSONObject jSONComment, final AddCommentHandler addCommentHandler) {

        String ADD_COMMENT_URL = Constant.URL + "addComment.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, ADD_COMMENT_URL, jSONComment,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            Integer code = response.getInt("code");
                            addCommentHandler.addSuccess(code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorResponse(context));

        mRequestQueue.add(jsonObjectRequest);
    }


    /**
     * 取消所有或部分未完成的网络请求
     */
    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new Object());
        }
    }

}

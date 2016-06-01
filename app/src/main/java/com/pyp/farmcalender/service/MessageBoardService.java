package com.pyp.farmcalender.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pyp.farmcalender.constant.Constant;
import com.pyp.farmcalender.service.handler.GetCommentsByIdHandler;
import com.pyp.farmcalender.service.handler.GetMessageInfosHandler;

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
     * @param context
     */
    public void getMessageInfos(Context context, final GetMessageInfosHandler getMessageInfosHandler) {

        String TASK_INFO_URL = Constant.URL + "get_message_infos.json";
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(TASK_INFO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response == null || "".equals(response)){

                        }else {
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
     * @param context
     */
    public void getCommentsById(Context context, String messageId,final GetCommentsByIdHandler getCommentsByIdHandler) {

        String TASK_INFO_URL = Constant.URL + "getCommentById?messageId="+messageId;
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(TASK_INFO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response == null || "".equals(response)){

                        }else {
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
     * 取消所有或部分未完成的网络请求
     */
    public void cancelPendingRequests(){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(new Object());
        }
    }

}

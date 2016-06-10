package com.pyp.farmcalender.ui.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pyp.farmcalender.R;
import com.pyp.farmcalender.entity.MessageBoardEntity;
import com.pyp.farmcalender.service.MessageBoardService;
import com.pyp.farmcalender.service.handler.AddMessagesHandler;
import com.pyp.farmcalender.utils.calendar.DateUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class AddMessagesActivity extends Activity {

    public static final int RESULT_CODE = 2;
    private static final String TAG = "AddMessagesActivity";
    private EditText mContentEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.message_add_layout);
        init();

    }

    private void init(){
        mContentEditView = (EditText)findViewById(R.id.et_message_content);

    }

    private void requestData(String content){

        MessageBoardService.getInstance().addMessages(getApplicationContext(), getMessagesJSON(content), new AddMessagesHandler() {
            @Override
            public void addSuccess(int code) {
                if(code > 0){
                    setResult(RESULT_CODE);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"服务器出问题，请等会再试...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private JSONObject getMessagesJSON(String content) {

        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        Integer userId = sp.getInt("userId", 0);
        MessageBoardEntity messageBoardEntity = new MessageBoardEntity();
        messageBoardEntity.setContent(content);
        messageBoardEntity.setUserId(userId + "");
        messageBoardEntity.setTime(DateUtil.getCurrentTime());
        Gson gson = new Gson();
        String json = gson.toJson(messageBoardEntity);
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0, 1, 0, "发布");
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1){
            String content = mContentEditView.getText().toString();
            if (content == null || "".equals(content)) {
                Toast.makeText(getApplicationContext(),"内容不能为空",Toast.LENGTH_LONG).show();
            }else{
                requestData(content);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        MessageBoardService.getInstance().cancelPendingRequests();
        super.onStop();
    }
}


package com.pyp.farmcalender.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pyp.farmcalender.entity.UserEntity;


/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class UserInfoCache {
    private static final String TAG = "UserInfoCache";

    public static void cacheUserInfo(Context context, UserEntity entity) {
        SharedPreferences sp = context.getSharedPreferences("UserInfo",
                context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putInt("userId", entity.getId());
            editor.putString("username", entity.getUsername());
            editor.putString("email", entity.getEmail());
            editor.putString("password", entity.getPassword());
            editor.putString("profile", entity.getProfile());
            editor.putString("phone", entity.getPhone());
            editor.putString("city", entity.getCity());
            editor.putInt("age", entity.getAge());
            editor.putInt("sex", entity.getSex());
            editor.putString("address", entity.getAddress());
            editor.commit();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            System.out.println("缓存用户信息失败");
        }
    }

}

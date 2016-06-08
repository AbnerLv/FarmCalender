package com.pyp.farmcalender.service.handler;

import com.pyp.farmcalender.entity.UserEntity;

/**
 * Created by Administrator on 2016/5/28.
 */
public abstract class LoginHandler {
    public abstract void onSuccess(UserEntity userEntity);
}

package com.pyp.farmcalender.service.handler;

import com.pyp.farmcalender.entity.MessageBoardEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public abstract class GetPublishedMessagesHandler {

    public abstract void onSuccess(List<MessageBoardEntity> entitys);
}

package com.jihuan.nettyrpc.client;
/**
*@Description:
*@Author: jihuan
*@date: 2019/8/20
*/
public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);

}

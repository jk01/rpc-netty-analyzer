package com.jihuan.nettyrpc.client.proxy;

import com.jihuan.nettyrpc.client.RPCFuture;

/**
 *@Description: rpc server
 *@Author: jihuan
 *@date: 2019/8/20
 */
public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}
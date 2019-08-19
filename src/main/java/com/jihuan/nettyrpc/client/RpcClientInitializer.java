package com.jihuan.nettyrpc.client;

import com.jihuan.nettyrpc.protocol.RpcDecoder;
import com.jihuan.nettyrpc.protocol.RpcEncoder;
import com.jihuan.nettyrpc.protocol.RpcRequest;
import com.jihuan.nettyrpc.protocol.RpcResponse;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
*@Description:
*@Author: jihuan
*@date: 2019/8/20
*/
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new RpcEncoder(RpcRequest.class));
        cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
        cp.addLast(new RpcDecoder(RpcResponse.class));
        cp.addLast(new RpcClientHandler());
    }
}

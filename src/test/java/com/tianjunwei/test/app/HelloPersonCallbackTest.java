package com.tianjunwei.test.app;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.tianjunwei.test.client.HelloPersonService;
import com.tianjunwei.test.client.Person;
import com.jihuan.nettyrpc.client.AsyncRPCCallback;
import com.jihuan.nettyrpc.client.RPCFuture;
import com.jihuan.nettyrpc.client.RpcClient;
import com.jihuan.nettyrpc.client.proxy.IAsyncObjectProxy;
import com.jihuan.nettyrpc.registry.ServiceDiscovery;

/**
*@Description:
*@Author: jihuan
*@date: 2019/8/20
*/
public class HelloPersonCallbackTest {
    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            IAsyncObjectProxy client = rpcClient.createAsync(HelloPersonService.class);
            int num = 5;
            RPCFuture helloPersonFuture = client.call("GetTestPerson", "xiaoming", num);
            helloPersonFuture.addCallback(new AsyncRPCCallback() {
                @Override
                public void success(Object result) {
                    List<Person> persons = (List<Person>) result;
                    for (int i = 0; i < persons.size(); ++i) {
                        System.out.println(persons.get(i));
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void fail(Exception e) {
                    System.out.println(e);
                    countDownLatch.countDown();
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rpcClient.stop();

        System.out.println("End");
    }
}

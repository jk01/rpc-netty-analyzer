package com.nettyrpc.test.app;

import com.jihuan.nettyrpc.client.RPCFuture;
import com.jihuan.nettyrpc.client.RpcClient;
import com.jihuan.nettyrpc.client.proxy.IAsyncObjectProxy;
import com.nettyrpc.test.client.HelloPersonService;
import com.nettyrpc.test.client.Person;
import com.nettyrpc.test.client.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client-spring.xml")
public class HelloServiceTest {

    @Autowired
    private RpcClient rpcClient;

    @Test
    public void helloTest1() {
        HelloService helloService = rpcClient.create(HelloService.class);
        String result = helloService.hello("World");
        Assert.assertEquals("Hello! World", result);
    }

    @Test
    public void helloTest2() {
        HelloService helloService = rpcClient.create(HelloService.class);
        Person person = new Person("Xiao", "Song");
        String result = helloService.hello(person);
        Assert.assertEquals("Hello! Xiao Song", result);
    }

    @Test
    public void helloPersonTest(){
        HelloPersonService helloPersonService = rpcClient.create(HelloPersonService.class);
        int num = 5;
        List<Person>  persons = helloPersonService.GetTestPerson("xiaosong",num);
        List<Person> expectedPersons = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            expectedPersons.add(new Person(Integer.toString(i), "xiaosong"));
        }
        assertThat(persons, equalTo(expectedPersons));

        for (int i = 0; i<persons.size(); ++i){
            System.out.println(persons.get(i));
        }
    }

    @Test
    public void helloFutureTest1() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloService = rpcClient.createAsync(HelloService.class);
        RPCFuture result = helloService.call("hello", "World");
        Assert.assertEquals("Hello! World", result.get());
    }

    @Test
    public void helloFutureTest2() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloService = rpcClient.createAsync(HelloService.class);
        Person person = new Person("Xiao", "Song");
        RPCFuture result = helloService.call("hello", person);
        Assert.assertEquals("Hello! Xiao Song", result.get());
    }

    @Test
    public void helloPersonFutureTest1() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloPersonService = rpcClient.createAsync(HelloPersonService.class);
        int num = 5;
        RPCFuture result = helloPersonService.call("GetTestPerson", "xiaosong", num);
        List<Person> persons = (List<Person>) result.get();
        List<Person> expectedPersons = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            expectedPersons.add(new Person(Integer.toString(i), "xiaosong"));
        }
        assertThat(persons, equalTo(expectedPersons));

        for (int i = 0; i < num; ++i) {
            System.out.println(persons.get(i));
        }
    }
}

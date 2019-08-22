package com.nettyrpc.test.server;

import java.util.ArrayList;
import java.util.List;

import com.nettyrpc.test.client.HelloPersonService;
import com.nettyrpc.test.client.Person;
import com.jihuan.nettyrpc.server.RpcService;

/**
*@Description:
*@Author: jihuan
*@date: 2019/8/20
*/
@RpcService(HelloPersonService.class)
public class HelloPersonServiceImpl implements HelloPersonService {

    @Override
    public List<Person> GetTestPerson(String name, int num) {
        List<Person> persons = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            persons.add(new Person(Integer.toString(i), name));
        }
        return persons;
    }
}

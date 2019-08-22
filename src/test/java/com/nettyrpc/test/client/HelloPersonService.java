package com.nettyrpc.test.client;

import java.util.List;

/**
*@Description:
*@Author: jihuan
*@date: 2019/8/20
*/
public interface HelloPersonService {
    List<Person> GetTestPerson(String name,int num);
}

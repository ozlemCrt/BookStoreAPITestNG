package com.bookstore.tests.apiTests;

import com.bookstore.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class SetApiData {
    Faker faker=new Faker();
    @Test
    public void setData(){
        ConfigurationReader.set("userName",faker.name().fullName());
        ConfigurationReader.set("password","I1!"+faker.internet().password());

    }
}

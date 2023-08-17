package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends TestBase {
    @Test
    public void registerUser(){
        Map<String,String> mapBody=new HashMap<>();
        mapBody.put("userName", ConfigurationReader.get("userName"));
        mapBody.put("password",ConfigurationReader.get("password"));
        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(mapBody)
                .when()
                .post("/Account/v1/User").prettyPeek();
    }

    @Test
    public void verifyNewUser(){
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertNotNull(response.path("userID"));
        Assert.assertEquals(response.path("username"),ConfigurationReader.get("userName"));
        ConfigurationReader.set("userID",response.path("userID"));
    }

}

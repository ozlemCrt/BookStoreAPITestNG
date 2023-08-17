package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetUserInfo extends TestBase {
    @Test
    public void getUserInfo(){
        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("UUID",ConfigurationReader.get("userID"))
                .header("Authorization","Bearer "+ConfigurationReader.get("token"))
                .when()
                .get("/Account/v1/User/{UUID}")
                .prettyPeek();
    }
    @Test
    public void verifyUserInfo(){
        List<String> addedBooks=response.path("books.isbn");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(addedBooks.contains(ConfigurationReader.get("isbn3")));
        Assert.assertTrue(addedBooks.contains(ConfigurationReader.get("isbn5")));
        Assert.assertEquals(response.path("userId"),ConfigurationReader.get("userID"));
        Assert.assertEquals(response.path("username"),ConfigurationReader.get("userName"));
    }



}

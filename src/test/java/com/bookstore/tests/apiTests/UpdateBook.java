package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateBook extends TestBase {
    @Test
    public void updateBook(){
        String body="{\n" +
                "  \"userId\": \""+ConfigurationReader.get("userID")+"\",\n" +
                "  \"isbn\": \""+ConfigurationReader.get("isbn8")+"\"\n" +
                "}\n";

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("isbn", ConfigurationReader.get("isbn5"))
                .body(body)
                .header("Authorization","Bearer "+ConfigurationReader.get("token"))
                .when()
                .put("/BookStore/v1/Books/{isbn}")
                .prettyPeek();
    }

    @Test
    public void verifyUpdateProcess() {
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.path("userId"),ConfigurationReader.get("userID"));
        Assert.assertTrue(response.path("books.isbn").toString().contains(ConfigurationReader.get("isbn8")));
    }
}

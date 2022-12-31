package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;


    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("ABC");
        userPojo.setEmail( getRandomString(3)+getRandomValue()+"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }
    @Test
    public void verifyUserGetSuccessfully(){
        Response response = given()
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .header("Connection", "keep-alive")
                .when()
                .get("/users");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("XYZ");
        userPojo.setEmail( getRandomString(3)+getRandomValue()+"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("inactive");
        Response response = given()
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/11762");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void VerifyUserDeleteSuccessfully(){
        Response response = given()
                .header("Authorization", "Bearer a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/11762");
        response.prettyPrint();
        response.then().statusCode(204);
    }
}

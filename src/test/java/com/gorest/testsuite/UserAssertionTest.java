package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;
    public UserAssertionTest(){
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size",equalTo(20));
    }

    // 2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        response.body("find{it.id == 5487}.name",equalTo("Hamsini Trivedi"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("[2].name",equalTo("Subhashini Talwar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("name",hasItems("Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV"));
    }

    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("find{it.id == 5471}.email",equalTo("adiga_aanjaneya_rep@jast.org"));
    }

    //6.Verify the status is “Active” of user name is “Ganak Jain”
    @Test
    public void test006() {
        response.body("find{it.name=='Ganak Jain'}.status",equalTo("active"));
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("find{it.name=='Niro Prajapat'}.gender",equalTo("male"));
    }
}

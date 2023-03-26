package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        //
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority=1)
    public void testPostUser(){
        logger.info("***********Creating User**************");
       Response response = UserEndPoints.createUser(userPayload);
       response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***********User is Created**************");
       // System.out.println(this.userPayload.getUsername());
    }

    @Test(priority=2)
    public void getUserByUserName(){
        logger.info("***********Reading User Info**************");
        Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***********User Info is displayed**************");
        logger.debug("testing debug.....");
    }

    @Test(priority=3)
    public void updateUserByUserName(){
       // UserEndPoints.getUser(this.userPayload.getUsername()).then().log().all();
        logger.info("***********Updating User**************");
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***********User is Updated**************");
       // UserEndPoints.getUser(this.userPayload.getUsername()).then().log().all();
    }
    @Test(priority=4)
    public void deleteUserByUserName(){
        logger.info("***********Deleting User**************");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Response res = UserEndPoints.getUser(this.userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),404);
        logger.info("***********User is deleted**************");
    }
}

package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {


    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class, priority=1)
    public void postUsers(String userID, String userName, String firstName, String lastName, String email, String pwd, String ph){
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(firstName);
        userPayload.setLastname(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class, priority=2)
    public void deleteUsersByName(String userName){
    Response response = UserEndPoints.deleteUser(userName);
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);
    }
}

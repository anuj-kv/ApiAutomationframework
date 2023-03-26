package api.endpoints;


//created to perform Crud operations

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints{
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");  //load the property file
        return routes;
    }

    public static Response createUser(User payload){
        String post_url = getURL().getString("post_url");
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .when()
                //.post(Routes.post_url);
                .post(post_url);

        return response;
    }

    public static Response getUser(String username){
        Response response = given().pathParam("username",username)
                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response updateUser(User payload, String username){
        Response response = given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return response;
    }

    public static Response deleteUser(String username){
        Response response = given().pathParam("username", username)
                .when()
                .delete(Routes.delete_url);

        return response;
    }

}

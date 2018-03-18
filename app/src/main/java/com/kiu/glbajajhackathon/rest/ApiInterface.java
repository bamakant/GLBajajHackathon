package com.kiu.glbajajhackathon.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bamakant on 20/1/18.
 */

public interface ApiInterface {

    /*@GET("/insertInOutTime/{id}/{outtime}/{intime}/")
    Call<JsonObject> insertInOutTime(@Path("id") int id,@Path("outtime") String outime,@Path("intime") String intime);

    @GET("/insertUserAttend/{inaddress}/{inlongitude}/{inlatitude}/{indatetime}/{userId}/{id}/")
    Call<JsonObject> insertUserAttend(@Path("inaddress") String inaddress, @Path("inlongitude") String inlongitude, @Path("inlatitude") String inlatitude, @Path("indatetime") String indatetime, @Path("userId") int userId, @Path("id") int id);

    ///updateUsrAttend/:outtime/:outlat/:outlon/:outaddress/

    @GET("/updateUsrAttend/{outtime}/{outlat}/{outlon}/{outaddress}/")
    Call<JsonObject> updateUsrAttend(@Path("outtime") String outtime, @Path("outlat") String outlat, @Path("outlon") String outlon, @Path("outaddress") String outaddress);

    @GET("/getAllStudentDetails/")
    Call<JsonArray> getAllStudents();*/
    @GET("/login/{username}/{password}/")
    Call<JsonObject> loginUser(@Path("username") String username, @Path("password") String password);

    @GET("/signup/{name}/{email}/{mobile}/{address}/{username}/{password}/{referral}/")
    Call<JsonObject> signup(@Path("name") String name,
                            @Path("email") String email,
                            @Path("mobile") String mobile,
                            @Path("address") String address,
                            @Path("username") String username,
                            @Path("password") String password,
                            @Path("referral") String referral);

    @GET("/item/insert/{itemName}/{itemQuantity}/{lng}/{lat}/{userid}/{address}/")
    Call<JsonObject> insertitem(@Path("itemName") String itemName,
                            @Path("itemQuantity") String itemQuantity,
                            @Path("lng") String lng,
                            @Path("lat") String lat,
                            @Path("userid") String userid,
                            @Path("address") String address);

    @GET("/getitem/")
    Call<JsonArray> getItems();

}

package com.example.ssith123.unoapps.Interface;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by SSITH123 on 3/2/2017.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("/Uno_registration/insert.php")
    public void insertUser(
            @Field("Firstname") String Firstname,
            @Field("Lastname") String Lastname,
            @Field("Mobilenum") String Mobilenum,
            @Field("Emailid") String Emailid,
            @Field("Password") String Password,
            Callback<Response> callback);

}

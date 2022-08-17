package com.nurdancaliskan.retrofittest.network;

import com.nurdancaliskan.retrofittest.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}

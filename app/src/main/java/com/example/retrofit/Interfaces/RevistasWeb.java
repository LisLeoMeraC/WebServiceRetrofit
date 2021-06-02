package com.example.retrofit.Interfaces;

import com.example.retrofit.modelos.Revistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RevistasWeb {

   @GET("ws/issues.php?j_id=3")
   Call<List<Revistas>> getPosts();

}


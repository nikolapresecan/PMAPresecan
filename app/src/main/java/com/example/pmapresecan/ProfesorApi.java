package com.example.pmapresecan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfesorApi {
    @GET("read_teachers.php")
    Call<List<Profesor>> getProfesori();
}

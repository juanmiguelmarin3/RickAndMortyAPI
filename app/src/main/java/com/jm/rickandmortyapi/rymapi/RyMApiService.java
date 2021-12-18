package com.jm.rickandmortyapi.rymapi;

import com.jm.rickandmortyapi.models.RyMRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RyMApiService {

    @GET("character")
    Call<RyMRespuesta> obtenerListaPersonajes();
}

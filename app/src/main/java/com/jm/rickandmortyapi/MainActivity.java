package com.jm.rickandmortyapi;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.jm.rickandmortyapi.models.RyM;
import com.jm.rickandmortyapi.models.RyMRespuesta;
import com.jm.rickandmortyapi.rymapi.RyMApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CHARACTER";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaPersonajesAdapter listaPersonajesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCharacter);
        listaPersonajesAdapter = new ListaPersonajesAdapter(this);
        recyclerView.setAdapter(listaPersonajesAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1   );
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();
    }

    private void obtenerDatos(){
        RyMApiService service = retrofit.create(RyMApiService.class);
        Call<RyMRespuesta> ryMRespuestaCall = service.obtenerListaPersonajes();

        ryMRespuestaCall.enqueue(new Callback<RyMRespuesta>() {
            @Override
            public void onResponse(Call<RyMRespuesta> call, Response<RyMRespuesta> response) {
                if(response.isSuccessful()){

                    RyMRespuesta rymRespuesta = response.body();
                    ArrayList<RyM> listaPersonajes = rymRespuesta.getResults();

                    listaPersonajesAdapter.adicionarListaPersonajes(listaPersonajes);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RyMRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
}
package com.example.proyectofinal.ui.home;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Activity.loandingCartelera;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.R;
import com.example.proyectofinal.RecyclerViewAdapter;
import com.example.proyectofinal.models.Directorio;
import com.example.proyectofinal.models.Evento;
import com.example.proyectofinal.models.IdSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    ImageView  imgPrincipal,imgFondo;



    private RecyclerViewAdapter carteleraAdapter;
    private ArrayList<Evento> carteleras = new ArrayList<>();

    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.listaEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



        obtenerDatos();

        return root;



    }






    private void obtenerDatos() {
        String url = Directorio.getInstance().getApiUrls("api1");



        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String imagen = jsonObject.getString("imagen");
                            String nombreEvento = jsonObject.getString("nombre_evento");
                            carteleras.add(new Evento(id, imagen, nombreEvento));
                        }

                            imgPrincipal = root.findViewById(R.id.imgPrincipal);
                           imgFondo= root.findViewById(R.id.imgFondo);
                            Glide.with(getContext())
                                    .load(carteleras.get(0).getFoto())
                                    .into(imgPrincipal);

                            Glide.with(getContext())
                                .load(carteleras.get(0).getFoto())
                                .into(imgFondo);


                            carteleraAdapter = new RecyclerViewAdapter(carteleras, getContext());
                            recyclerView.setAdapter(carteleraAdapter);
                            carteleraAdapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(),loandingCartelera.class );

                                //realizo el singlenton
                                IdSingleton.getInstance().setId(carteleras.get(recyclerView.getChildAdapterPosition(view)).getId());
                                startActivity(intent);



                            }
                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                        obtenerDatos();
                    }
                } else {
                    Toast.makeText(getContext(), "No hay eventos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.toString());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                obtenerDatos();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
    }


}
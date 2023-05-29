package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.window.OnBackInvokedCallback;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.proyectofinal.R;
import com.example.proyectofinal.models.CarteleraEvento;
import com.example.proyectofinal.models.Directorio;
import com.example.proyectofinal.models.IdSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleEvento extends AppCompatActivity {

    ImageView imgCartelera;
    TextView  txtTitulo_Evento,txtElenco,txtFecha,txtDescripcion,txtGenero,txtHorario,txtTurno,txtHora,txtMinuto;

    Button btnObtenerBoleto;



    ArrayList<CarteleraEvento> ModeloEvento= new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        Bundle ResponseBundle= this.getIntent().getExtras();
        imgCartelera =findViewById(R.id.imgCartelera);
        txtTitulo_Evento=findViewById(R.id.txtTitulo_Evento);
        txtElenco=findViewById(R.id.txtElenco);
        txtGenero=findViewById(R.id.txtGenero);

        txtFecha=findViewById(R.id.txtFecha);

        txtHorario=findViewById(R.id.txtHorario);
        txtTurno=findViewById(R.id.txtTurno);



        txtHora=findViewById(R.id.txtHora);
        txtMinuto=findViewById(R.id.txtMinuto);

        txtDescripcion=findViewById(R.id.txtDescripcion);


        btnObtenerBoleto =findViewById(R.id.btnOptenerBoleto);

        // Configurar la Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilitar el botón de retroceso
        }
        //int Id = ResponseBundle.getInt("id");
        int Id = IdSingleton.getInstance().getId();
        String url = Directorio.getInstance().getApiUrlsId("api2",Id);
        obtenerDatos(url);

        btnObtenerBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferenciasEMAIL= getSharedPreferences("email", Context.MODE_PRIVATE);
                String email = preferenciasEMAIL.getString("email","");

                if(email.isEmpty()){

                    //Creamos y mostramos los cuadros de dialogo para notificar sobre la inexistencia del correo
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetalleEvento.this);
                    builder.setTitle("Sesión no iniciada");
                    builder.setMessage("Para poder obtener boletos de algun evento es necesario iniciar sesión. \n Por favor, inicia sesión o regístrate para continuar.")
                            .setPositiveButton("Iniciar sesión", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(DetalleEvento.this, "Iniciar sesion", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(DetalleEvento.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }

                            })
                            .setNegativeButton("Registrarse", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(DetalleEvento.this, "Registrar", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(DetalleEvento.this,RegistroActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }else{

                    Intent intent= new Intent(DetalleEvento.this,SelecionBoletosActivity.class);
                    startActivity(intent);
                    //finish();
                }
            }
        });

    }
    public void obtenerDatos(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {


                   try {

                            JSONObject jsonObject = new JSONObject(response);

                            int id = jsonObject.getInt("id");
                            String imagen = jsonObject.getString("imagen");
                            String nombreEvento = jsonObject.getString("nombre_evento");
                            String artista = jsonObject.getString("artista");
                            String fecha= jsonObject.getString("fecha");
                            String hora= jsonObject.getString("hora");
                            String descripcion=jsonObject.getString("descripcion");
                            String duracion= jsonObject.getString("duracion_minutos");
                            String genero= jsonObject.getString("genero");

                           //ModeloEvento.add(new CarteleraEvento(id,imagen,nombreEvento,artista,fecha,hora,descripcion,duracion,genero));


                        llenarDatos(id,imagen,nombreEvento,artista,fecha,hora,descripcion,duracion,genero);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                } else {
                    Toast.makeText(DetalleEvento.this, "No hay eventos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.toString());
                Toast.makeText(DetalleEvento.this, error.toString(), Toast.LENGTH_SHORT).show();
                obtenerDatos(URL);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void llenarDatos(int id,String imagen,String nombreEvento,String artista,String fecha,String hora,String descripcion,String duracion,String genero){

        // asignacion de IDsingleton

         IdSingleton.getInstance().setNombre(nombreEvento);
         IdSingleton.getInstance().setFecha(fecha);
         IdSingleton.getInstance().setHora(hora);
         IdSingleton.getInstance().setURLImagen(imagen);


        String urlImagen=imagen;
        String[] horaDividida= hora.split(":");
        String[] horaDuracion= duracion.split(":");


        Glide.with(this)
                .load(urlImagen)
                .into(imgCartelera);

        txtTitulo_Evento.setText(nombreEvento);

        txtFecha.setText(fecha);

        txtHorario.setText(horaDividida[0]+":"+horaDividida[1]);
        txtTurno.setText(horaDividida[2]);

        txtHora.setText(horaDuracion[0]+"h");
        txtMinuto.setText(horaDuracion[1]+"m");


        txtElenco.setText(artista);
        txtGenero.setText(genero);


        txtDescripcion.setText(descripcion);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
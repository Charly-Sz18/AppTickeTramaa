package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Activity_menu;
import com.example.proyectofinal.R;
import com.example.proyectofinal.models.Directorio;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
            TextView  txtnombre,txtapellido,txtcorreo,txtcontrasena,txtconfirmcontra;
            ProgressBar progressBar;
            boolean camposIguales = false;
            Button  btnCrearCuenta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtnombre = (TextView) findViewById(R.id.txtnombreR);
        txtapellido = (TextView) findViewById(R.id.txtapellidoR);
        txtcorreo = (TextView) findViewById(R.id.txtcorreoR);
        txtcontrasena = (TextView) findViewById(R.id.txtcontrasenaR);
        txtconfirmcontra = (TextView) findViewById(R.id.txtconfirmarcontraR);
        btnCrearCuenta = (Button) findViewById(R.id.btncrearCuenta);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);


        txtconfirmcontra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String text1 = txtcontrasena.getText().toString();
                String text2 = charSequence.toString();

                if (text1.equals(text2)) {
                    camposIguales = true;
                    txtconfirmcontra.setError(null); // Limpiar el mensaje de error si los campos son iguales

                } else {
                    camposIguales = false;
                    txtconfirmcontra.setError("Los campos no coinciden"); // Mostrar mensaje de error en editText2 si los campos no son iguales
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String nombre= txtnombre.getText().toString() ;
              String apellido= txtapellido.getText().toString() ;
              String correo =txtcorreo.getText().toString() ;
              String contra= txtcontrasena.getText().toString();
              String contra2=txtconfirmcontra.getText().toString() ;




                if(correo.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    if(!correo.trim().isEmpty()){
                        txtcorreo.setError("Correo inválido");
                    }
                    txtcorreo.setError("Capture su correo electronico");
                    return;
                }else{
                    txtcorreo.setError(null);
                }

                if(nombre.trim().isEmpty()){
                    txtnombre.setError("capture su contraseña");
                    return;
                }else{
                    txtnombre.setError(null);
                }

                if(apellido.trim().isEmpty()){
                    txtapellido.setError("capture su contraseña");
                    return;
                }else{
                    txtapellido.setError(null);
                }

                if(contra.trim().isEmpty()){
                    txtcontrasena.setError("capture su contraseña");
                    return;
                }else{
                    txtcontrasena.setError(null);
                }
                if(contra2.trim().isEmpty()){
                    txtconfirmcontra.setError("capture su contraseña");
                    return;
                }else{
                    txtconfirmcontra.setError(null);
                }

                if (camposIguales) {

                    progressBar.setVisibility(View.VISIBLE);  // Mostrar la ProgressBar

                    //btnCrearCuenta.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);  // Ocultar la ProgressBar después de 2 segundos
                        }
                    }, 2000);  // Retrasar la ejecución del código dentro de run() por 2000 milisegundos (2 segundos)

                    crearCuenta();
                } else {

                }

            }
        });


        ActionBar actionBar= getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilitar el botón de retroceso
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearCuenta(){
        String URL = Directorio.getInstance().getApiUrls("api4");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){

                    try {


                        JSONObject jsonObject = new JSONObject(response);

                        int id = jsonObject.getInt("id");

                        String nombre = jsonObject.getString("nombre");
                        String email = jsonObject.getString("email");
                        String foto = jsonObject.getString("foto");


                        actualizacionShared(id,nombre,email,foto);

                        Intent intent= new Intent(RegistroActivity.this, Activity_menu.class);
                        Toast.makeText(RegistroActivity.this, "El usuario se registro correctamente ", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegistroActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }



                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                String name= txtnombre.getText().toString();
                String apellido= txtapellido.getText().toString();
                String email= txtcorreo.getText().toString();
                String pwss= txtcontrasena.getText().toString();


                parametros.put("nombre",name);
                parametros.put("apellido",apellido);
                parametros.put("email",email);
                parametros.put("pwss",pwss);
                parametros.put("foto","null");
                parametros.put("rol_id","1");
                parametros.put("status","1");


                return parametros;
            }
        };

        RequestQueue RequestQueue= Volley.newRequestQueue(this);
        RequestQueue.add(stringRequest);


    }


    public void actualizacionShared(int id,String nombre,String email,String foto){



        SharedPreferences preferencias = getSharedPreferences("id",Context.MODE_PRIVATE);
        SharedPreferences.Editor E_id=preferencias.edit();
        E_id.putInt("id", id);
        E_id.commit();
        finish();

        SharedPreferences editarnombre= getSharedPreferences("nombre",MODE_PRIVATE);
        SharedPreferences.Editor E_Nombre= editarnombre.edit();
        E_Nombre.putString("nombre",nombre);
        E_Nombre.commit();

        SharedPreferences editaremail= getSharedPreferences("email",MODE_PRIVATE);
        SharedPreferences.Editor E_email= editaremail.edit();
        E_email.putString("email",email);
        E_email.commit();

        SharedPreferences editarfoto= getSharedPreferences("foto",MODE_PRIVATE);
        SharedPreferences.Editor E_foto= editarfoto.edit();
        E_foto.putString("foto",foto);
        E_foto.commit();

    }

}
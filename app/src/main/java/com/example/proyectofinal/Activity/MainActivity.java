package com.example.proyectofinal.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {



    EditText txtEmail,txtPwd;
    Button btnInicio,btnRegistro;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPwd = (EditText)findViewById(R.id.txt_pwd);
        btnInicio = (Button)findViewById(R.id.btnInicio);
        btnRegistro = (Button)findViewById(R.id.btnRegistrar);


        // activacion de los botones de regreso
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilitar el botón de retroceso
        }

        // acciono botones para iniciar sesion o para realizar un registro

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //especificacion de los campos que se utilizan en la validación
                String mail = txtEmail.getText().toString();
                String pwds = txtPwd.getText().toString();

                if(mail.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    if(!mail.trim().isEmpty()){
                        txtEmail.setError("Correo inválido");
                    }
                    txtEmail.setError("Capture su correo electronico");
                    return;
                }else{
                    txtEmail.setError(null);
                }

                if(pwds.trim().isEmpty()){
                    txtPwd.setError("capture su contraseña");
                    return;
                }else{
                    txtPwd.setError(null);
                }

                     validacion();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Manejar el evento del botón de retroceso
            Intent intent= new Intent(MainActivity.this,Activity_menu.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void validacion(){
       String URL= Directorio.getInstance().getApiUrls("api3");

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


                        Toast.makeText(MainActivity.this, nombre.toString()+ email+foto, Toast.LENGTH_SHORT).show();
                        actualizacionShared(id,nombre,email,foto);
                        Toast.makeText(MainActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,Activity_menu.class);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();


                    }



                }else{

                    //Creamos y mostramos los cuadros de dialogo para notificar sobre la inexistencia del correo
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Credenciales incorrectas")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(MainActivity.this, "Inserte correo", Toast.LENGTH_SHORT).show();
                                    vaciarCampos();
                                }
                            }).show();




                    Toast.makeText(MainActivity.this, "El correo no  registrado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > parametros = new HashMap<String,String>();
                parametros.put("email",txtEmail.getText().toString());
                parametros.put("pwss",txtPwd.getText().toString());
                return parametros;
            }
        };

        RequestQueue RequestQueue= Volley.newRequestQueue(this);
        RequestQueue.add(stringRequest);

    }


    public void vaciarCampos(){
        txtEmail.setText("");
        txtPwd.setText("");
    }

    NavigationView navigationView;
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
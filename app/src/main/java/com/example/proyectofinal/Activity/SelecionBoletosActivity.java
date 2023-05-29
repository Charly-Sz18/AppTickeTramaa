package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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
import com.example.proyectofinal.models.ArraySingleton;
import com.example.proyectofinal.models.Directorio;
import com.example.proyectofinal.models.IdSingleton;
import com.example.proyectofinal.models.PreciosZona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelecionBoletosActivity extends AppCompatActivity {
    int delimitador=0;
    TextView  txtCantidadVIP,txtVIP,txtCostoVIP,txtCantidadORO,txtORO,txtCostoORO,txtCantidadPLATA,txtPLATA,txtCostoPLATA;
    Button   btnDecrementoVIP,btnIncrementoVIP,btnDecrementoORO,btnIncrementoORO,btnDecrementoPLATA,btnIncrementoPLATA,btnSeleccionAsiento;

    RadioButton radio1;
    int VIPcounter=0,OROcounter=0,PLATAcounter=0;

    ArrayList<PreciosZona> listapreciosZonas= new ArrayList<>();

    boolean camposMayoresa0 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion_boletos);
         radio1=findViewById(R.id.radioButton);

         radio1.setSelected(true);



        // DATOS VIP------------------------------------------------------------------------------

        btnDecrementoVIP=findViewById(R.id.btn_decrementarVIP);
        txtCantidadVIP= findViewById(R.id.txtCantidadVIP);
        txtVIP= findViewById(R.id.txtVIP);
        txtCostoVIP= findViewById(R.id.txtCostoVIP);
        btnIncrementoVIP=findViewById(R.id.btn_incrementarVIP);

        txtCantidadVIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               comprobar();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //DATOS ORO-------------------------------------------------------------------------

        btnDecrementoORO=findViewById(R.id.btn_decrementarORO);
        txtCantidadORO= findViewById(R.id.txtCantidadORO);
        txtORO= findViewById(R.id.txtORO);
        txtCostoORO= findViewById(R.id.txtCostoORO);
        btnIncrementoORO=findViewById(R.id.btn_incrementarORO);

        txtCantidadORO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                comprobar();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //DATOS PLATA  ------------------------------------------------------------------------------

        btnDecrementoPLATA=findViewById(R.id.btn_decrementarPLATA);
        txtCantidadPLATA= findViewById(R.id.txtCantidadPLATA);
        txtPLATA= findViewById(R.id.txtPLATA);
        txtCostoPLATA= findViewById(R.id.txtCostoPLATA);
        btnIncrementoPLATA=findViewById(R.id.btn_incrementarPLATA);

        txtCantidadPLATA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    comprobar();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        obtenerDatos();


        btnSeleccionAsiento=findViewById(R.id.btnContinuarCopra);
        txtCantidadVIP.setText(String.valueOf(VIPcounter));
        txtCantidadORO.setText(String.valueOf(OROcounter));
        txtCantidadPLATA.setText(String.valueOf(PLATAcounter));


        //BOTON DECREMENTO VIP
        btnDecrementoVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer cantidad= Integer.valueOf(txtCantidadVIP.getText().toString());
                if(cantidad>0){
                    VIPcounter=VIPcounter-1;
                    txtCantidadVIP.setText(String.valueOf(VIPcounter));
                }
            }
        });

        //BOTON INCREMENTO VIP
        btnIncrementoVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobar();
                if(delimitador < 8){
                    VIPcounter=VIPcounter+1;
                    txtCantidadVIP.setText(String.valueOf(VIPcounter));
                }

            }
        });



        //BOTON DECREMENTO ORO
        btnDecrementoORO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer cantidad= Integer.valueOf(txtCantidadORO.getText().toString());
                if(cantidad>0){
                    OROcounter=OROcounter-1;
                    txtCantidadORO.setText(String.valueOf(OROcounter));
                }

            }
        });

        //BOTON INCREMENTO ORO
        btnIncrementoORO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobar();
                if(delimitador<8){
                    OROcounter=OROcounter+1;
                    txtCantidadORO.setText(String.valueOf(OROcounter));
                }
            }
        });


        //BOTON DECREMENTO PLATA
        btnDecrementoPLATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer cantidad= Integer.valueOf(txtCantidadPLATA.getText().toString());
                if(cantidad>0){
                    PLATAcounter=PLATAcounter-1;
                    txtCantidadPLATA.setText(String.valueOf(PLATAcounter));
                }
            }
        });

        //BOTON INCREMENTO PLATA
        btnIncrementoPLATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobar();
                if(delimitador<8){
                    PLATAcounter=PLATAcounter+1;
                    txtCantidadPLATA.setText(String.valueOf(PLATAcounter));
                }

            }
        });

        btnSeleccionAsiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle misdatos= new Bundle();
                misdatos.putInt("VIPcounter",VIPcounter);
                misdatos.putInt("OROcounter",OROcounter);
                misdatos.putInt("PLATAcounter",PLATAcounter);

                Intent intent= new Intent(SelecionBoletosActivity.this,SelececionarAsientoActivity.class);
                intent.putExtras(misdatos);
                startActivity(intent);
                //finish();
            }
        });

        ActionBar actionBar= getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilitar el botón de retroceso
        }





    }

   public void comprobar(){

        delimitador = VIPcounter+OROcounter+PLATAcounter;


       if(VIPcounter ==0 && OROcounter ==0 && PLATAcounter==0){
           btnSeleccionAsiento.setEnabled(false);
       }else{
           btnSeleccionAsiento.setEnabled(true);
       }

   }



    public void obtenerDatos(){
        int Id = IdSingleton.getInstance().getId();
        String url = Directorio.getInstance().getApiUrls("api5");

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                    try {

                        JSONArray  response2= new JSONArray(response);
                        if(response2.length() !=0){
                            for (int i = 0; i < response2.length(); i++) {

                                JSONObject jsonObject = response2.getJSONObject(i);

                                int id = jsonObject.getInt("id");

                                int evento_id = Id;
                                int precio_id = jsonObject.getInt("id");
                                double precio = jsonObject.getDouble("precio");

                                JSONObject ZonaTeatroObject= jsonObject.getJSONObject("zona_teatro");

                                int zona_id = ZonaTeatroObject.getInt("id");
                                String zona = ZonaTeatroObject.getString("nombre_zona");

                                listapreciosZonas.add(new PreciosZona(evento_id,precio_id,precio,zona_id,zona));

                            }
                            asignarDatos();

                            ArraySingleton.getInstance().setListaPrecios(listapreciosZonas);

                        }else{
                            Toast.makeText(SelecionBoletosActivity.this, "No se ha determinado el precio ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SelecionBoletosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SelecionBoletosActivity.this, "Error consulta", Toast.LENGTH_SHORT).show();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("id", String.valueOf(Id));
                return  parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void asignarDatos(){

        for (PreciosZona precios: listapreciosZonas) {
            String zona= precios.getZona().toString();
            if(zona.equals("VIP")){
                txtVIP.setText(precios.getZona().toString());
                txtCostoVIP.setText("$"+String.valueOf(precios.getPrecio()));
            }
            if(zona.equals("ORO")){
                txtORO.setText(precios.getZona().toString());
                txtCostoORO.setText("$"+String.valueOf(precios.getPrecio()));
            }
            if(zona.equals("PLATA")){
                txtPLATA.setText(precios.getZona().toString());
                txtCostoPLATA.setText("$"+String.valueOf(precios.getPrecio()));
            }


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


}
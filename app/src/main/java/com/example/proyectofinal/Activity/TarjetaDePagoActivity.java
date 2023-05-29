package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.proyectofinal.R;
import com.example.proyectofinal.models.ArraySingleton;
import com.example.proyectofinal.models.Directorio;
import com.example.proyectofinal.models.IdSingleton;
import com.example.proyectofinal.models.asientoEstatico;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TarjetaDePagoActivity extends AppCompatActivity {

    ImageView imgFondo,imgPrincipal;

    TextInputEditText txtCorreoPago,txtnombretitular,numeroTarjeta,txtfecha,txtCVC;
    TextView txtResumen,txtPrecio;

    Button btnpago;

    CheckBox CBdatos;



    int boletos;
    Double total;
    String[]  horaTurno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta_de_pago);

        imgFondo     = findViewById(R.id.imgFondo);
        imgPrincipal = findViewById(R.id.imgPrincipal);

        txtResumen   = findViewById(R.id.txtResumen);
        txtPrecio  = findViewById(R.id.txtPrecio);
        txtCorreoPago= findViewById(R.id.txtCorreoPago);
        txtnombretitular= findViewById(R.id.txtnombretitular);
        numeroTarjeta = findViewById(R.id.numeroTarjeta);
        txtfecha = findViewById(R.id.txtfecha);
        txtCVC  = findViewById(R.id.txtCVC);

        btnpago = findViewById(R.id.btnPago);
        CBdatos = findViewById(R.id.CBdatos);




       String ImagenURL= IdSingleton.getInstance().getURLImagen();
       String nombre=IdSingleton.getInstance().getNombre();
       String fecha = IdSingleton.getInstance().getFecha();
       String hora= IdSingleton.getInstance().getHora();


       ///////////////////// DATOS PARA LA IMAGEN--------------------------

        Glide.with(this)
                .load(ImagenURL)
                .into(imgPrincipal);

        Glide.with(this)
                .load(ImagenURL)
                .into(imgFondo);



        ///////////////////// DATOS PARA LA EL TITULO--------------------------
        Bundle misDatos= getIntent().getExtras();

        boletos=  misDatos.getInt("boletos");
        total= misDatos.getDouble("total");
        horaTurno= hora.split(":");

        txtResumen.setText( String.valueOf(boletos)+" boletos para "+ nombre + " el "+ fecha + " a las " +horaTurno[0]+":"+horaTurno[1]+" "+horaTurno[2]);
        txtPrecio.setText("MXN  $"+String.valueOf(total));

        SharedPreferences preferenciasCORREO= getSharedPreferences("correoP", Context.MODE_PRIVATE);
        String correoElectronico = preferenciasCORREO.getString("correoP","");

        SharedPreferences preferenciasNOMBREt= getSharedPreferences("nombreT", Context.MODE_PRIVATE);
        String titular = preferenciasNOMBREt.getString("nombreT","");

        SharedPreferences preferenciasNUMERO= getSharedPreferences("numeroT", Context.MODE_PRIVATE);
        String numerotar = preferenciasNUMERO.getString("numeroT","");

        SharedPreferences preferenciasFECHA= getSharedPreferences("fecha", Context.MODE_PRIVATE);
        String fechatarjeta = preferenciasFECHA.getString("fecha","");



        if(!correoElectronico.isEmpty()){
            txtCorreoPago.setText(correoElectronico);
            txtnombretitular.setText(titular);
            numeroTarjeta.setText(numerotar);
            txtfecha.setText(fechatarjeta);
            CBdatos.setChecked(true);
        }








// Agregar un TextWatcher al TextView
        txtfecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Método antes de que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Método mientras el texto cambia
                formatText(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Método después de que el texto cambie
            }
        });

        txtCVC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               hacerCVC(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        numeroTarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    verificarTmanoTarjeta(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail= txtCorreoPago.getText().toString();
                String titular= txtnombretitular.getText().toString();
                String numero=numeroTarjeta.getText().toString();
                String fecha= txtfecha.getText().toString();
                String cvc= txtCVC.getText().toString();


                if(mail.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    if(!mail.trim().isEmpty()){
                        txtCorreoPago.setError("Correo inválido");
                    }
                    txtCorreoPago.setError("Capture su correo electrónico");
                    return;
                }else{
                    txtCorreoPago.setError(null);
                }

                if(titular.trim().isEmpty()){
                    txtnombretitular.setError("Capture su nombre");
                    return;
                }else{
                    txtnombretitular.setError(null);
                }


                if(numero.length()<16){
                    numeroTarjeta.setError("Número de tarjeta inválido");
                    return;
                }else{
                    numeroTarjeta.setError(null);
                }

                if(fecha.length()<5){
                    txtfecha.setError("Fecha inválido");
                    return;
                }else{
                    txtfecha.setError(null);
                }


                if(cvc.length()<3){
                    txtCVC.setError("CVC inválido");
                    return;
                }else{
                    txtCVC.setError(null);
                }


              if(CBdatos.isChecked()){
                  SharedPreferences preferencias = getSharedPreferences("correoP",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_correo =preferencias.edit();
                  E_correo.putString("correoP", mail);
                  E_correo.commit();


                  SharedPreferences preferenciasTitularr = getSharedPreferences("nombreT",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_titular=preferenciasTitularr.edit();
                  E_titular.putString("nombreT", titular);
                  E_titular.commit();


                  SharedPreferences preferenciasNUMERO = getSharedPreferences("numeroT",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_NUMERO=preferenciasNUMERO.edit();
                  E_NUMERO.putString("numeroT", numero);
                  E_NUMERO.commit();


                  SharedPreferences preferenciasFECha = getSharedPreferences("fecha",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_fecha=preferenciasFECha.edit();
                  E_fecha.putString("fecha", fecha);
                  E_fecha.commit();


                  enviarDatosVenta();



              }else{

                  SharedPreferences preferencias = getSharedPreferences("correoP",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_correo =preferencias.edit();
                  E_correo.clear();
                  E_correo.commit();


                  SharedPreferences preferenciasTitularr = getSharedPreferences("nombreT",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_titular=preferenciasTitularr.edit();
                  E_titular.clear();
                  E_titular.commit();


                  SharedPreferences preferenciasNUMERO = getSharedPreferences("numeroT",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_NUMERO=preferenciasNUMERO.edit();
                  E_NUMERO.clear();
                  E_NUMERO.commit();


                  SharedPreferences preferenciasFECha = getSharedPreferences("fecha",Context.MODE_PRIVATE);
                  SharedPreferences.Editor E_fecha=preferenciasFECha.edit();
                  E_fecha.clear();
                  E_fecha.commit();



                }


                enviarDatosVenta();


            }
        });







        ActionBar actionBar= getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
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

    public void verificarTmanoTarjeta(String text){
        if(text.length()>16){
            String textoFormado= text.substring(0,16);
            numeroTarjeta.setText(textoFormado);
            numeroTarjeta.setSelection(textoFormado.length());

        }
    }




    public void hacerCVC(String text){
        if (text.length() >3){
            String formattedText2 = text.substring(0, 3);
            txtCVC.setText(formattedText2);
            txtCVC.setSelection(formattedText2.length());
        }
    }


    private void formatText(String text) {

        if (text.length() == 2 && !text.endsWith("/")) {
            // Agregar "/" después de los primeros dos caracteres
            String formattedText = text.substring(0, 2) + "/";
            txtfecha.setText(formattedText);
            txtfecha.setSelection(formattedText.length()); // Mantener el cursor al final del texto
        } else if (text.length() > 5) {
            // Limitar la longitud del texto a "mm/aa"
            String formattedText = text.substring(0, 5);
            txtfecha.setText(formattedText);
            txtfecha.setSelection(formattedText.length()); // Mantener el cursor al final del texto
        }

    }



    public void enviarDatosVenta(){
        String URL = Directorio.getInstance().getApiUrls("api7");
        JSONArray jsonArray= new JSONArray();

        List<asientoEstatico> listaAsientosActualizar = ArraySingleton.getInstance().getAsientosModificados();

        for (asientoEstatico asiento : listaAsientosActualizar) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", asiento.getId());
                jsonObject.put("seleccionado", asiento.estaSeleccionado());

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, URL, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                                if(response.length()>0){
                                    realizarticket();

                                }

                            for (int i = 0; i < response.length(); i++) {

                                    Log.d("TAG", response.get(i) + "************************************************************************+");

                                }

                            }catch (JSONException e){

                                e.printStackTrace();
                             }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TarjetaDePagoActivity.this, "Erro en el servidor", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);





    }





    public void realizarticket(){

        int usuario_teatro_id,evento_teatro_id,zona_teatro_id,asiento_teatro_id;


        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = dateFormat.format(calendar.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat dateFormatTIME = new SimpleDateFormat("HH:mm:ss");
        String horaActual = dateFormatTIME.format(calendarTime.getTime());

        SharedPreferences preferenciasID= getSharedPreferences("id", Context.MODE_PRIVATE);
        usuario_teatro_id = preferenciasID.getInt("id",0);

        evento_teatro_id = IdSingleton.getInstance().getId();

        SharedPreferences preferenciasVIP= getSharedPreferences("idVIP", Context.MODE_PRIVATE);
        int zonaVIP_id = preferenciasVIP.getInt("id",0);

        SharedPreferences preferenciasORO= getSharedPreferences("idORO", Context.MODE_PRIVATE);
        int zonaORO_id = preferenciasORO.getInt("id",0);

        SharedPreferences preferenciasPLATA= getSharedPreferences("idPLATA", Context.MODE_PRIVATE);
        int zonaPLATA_id = preferenciasPLATA.getInt("id",0);

        String  zonas = zonaVIP_id+":"+zonaORO_id+":"+zonaPLATA_id;

        List<asientoEstatico> listaAsientosLocal = ArraySingleton.getInstance().getAsientosModificados();

        String asientos="";
        for (asientoEstatico asiento:listaAsientosLocal) {
            if(asiento.estaSeleccionado().equals("0")){

                asientos = asientos + asiento.getFila() +asiento.getColumna()+",";
            }

        }


        Log.d("TAG", boletos +" ,"+total+","+fechaActual+","+horaActual+","+usuario_teatro_id+","+evento_teatro_id+","+zonas+","+asientos);

        Intent inten= new Intent(TarjetaDePagoActivity.this, GraciasporComprarActivity.class);
        startActivity(inten);
        finish();

    }







}
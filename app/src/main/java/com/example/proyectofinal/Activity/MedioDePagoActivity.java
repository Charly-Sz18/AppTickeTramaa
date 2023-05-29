package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal.R;
import com.example.proyectofinal.models.ArraySingleton;
import com.example.proyectofinal.models.Asiento;
import com.example.proyectofinal.models.IdSingleton;
import com.example.proyectofinal.models.PreciosZona;
import com.example.proyectofinal.models.asientoEstatico;

import java.util.List;

public class MedioDePagoActivity extends AppCompatActivity {
    CheckBox  checkBox;
    ImageView  imgEvento;
    TextView  txtNombreEvento,txtFechaEvento,txtHoraEvento;
    TextView txtzonaVIP,txtboletosVIP,txtasientosVIP,txtcostosVIP;
    TextView txtzonaORO,txtboletosORO,txtasientosORO,txtcostosORO;
    TextView txtzonaPLATA,txtboletosPLATA,txtasientosPLATA,txtcostosPLATA, txtPago;

    LinearLayout zonaVIP,zonaORO, zonaPLATA;
   View tarjetaPago;

    int boletoVIP= 0;
    int boletoORO=0;
    int boletoPLATA= 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio_de_pago);

        tarjetaPago=findViewById(R.id.tarjetaPago);
        // informacion del evento_______-------------------

      txtNombreEvento  =findViewById(R.id.txtNombreEvento);
      txtFechaEvento   = findViewById(R.id.txtFechaEvento);
      txtHoraEvento    = findViewById(R.id.txtHoraEvento);
        imgEvento      =findViewById(R.id.imgEvento);
        checkBox       = findViewById(R.id.checkBox);


        //------------------------ZONA VIP------------------------
        txtzonaVIP      =findViewById(R.id.txtzonaVIP);
        txtboletosVIP   =findViewById(R.id.txtboletosVIP);
        txtasientosVIP  =findViewById(R.id.txtasientosVIP);
        txtcostosVIP    =findViewById(R.id.txtcostoVIP);

        //------------------------ZONA ORO------------------------

        txtzonaORO      =findViewById(R.id.txtzonaORO);
        txtboletosORO   =findViewById(R.id.txtboletosORO);
        txtasientosORO  =findViewById(R.id.txtasientosORO);
        txtcostosORO    =findViewById(R.id.txtcostoORO);
        //------------------------ZONA PLA------------------------

        txtzonaPLATA      =findViewById(R.id.txtzonaPLATA);
        txtboletosPLATA   =findViewById(R.id.txtboletosPLATA);
        txtasientosPLATA  =findViewById(R.id.txtasientosPLATA);
        txtcostosPLATA    =findViewById(R.id.txtcostoPLATA);

        zonaVIP=findViewById(R.id.containerZonaVIP);
        zonaORO=findViewById(R.id.containerZonaORO);
        zonaPLATA=findViewById(R.id.containerZonaPLATA);

        zonaVIP.setVisibility(View.GONE);
        zonaORO.setVisibility(View.GONE);
        zonaPLATA.setVisibility(View.GONE);


        txtPago=findViewById(R.id.txtPago);

        SharedPreferences preferenciasVIP= getSharedPreferences("idVIP", Context.MODE_PRIVATE);
        int zonaVIP_id = preferenciasVIP.getInt("id",0);

        SharedPreferences preferenciasORO= getSharedPreferences("idORO", Context.MODE_PRIVATE);
        int zonaORO_id = preferenciasORO.getInt("id",0);

        SharedPreferences preferenciasPLATA= getSharedPreferences("idPLATA", Context.MODE_PRIVATE);
        int zonaPLATA_id = preferenciasPLATA.getInt("id",0);



        /// Rellenamos la cartelera

        String nombre= IdSingleton.getInstance().getNombre();
        String fecha=IdSingleton.getInstance().getFecha();
        String hora= IdSingleton.getInstance().getHora();
        String imagenURL  = IdSingleton.getInstance().getURLImagen();

        String[] horaDividida=hora.split(":");
        txtNombreEvento.setText(nombre);
        txtFechaEvento.setText(fecha);
        txtHoraEvento.setText(horaDividida[0]+":"+horaDividida[1]+" "+horaDividida[2]);

        Glide.with(this)
                .load(imagenURL)
                .into(imgEvento);


        int contadorVIP=0,contadorORO=0,contadorPLATA=0;

        Bundle misboletos= getIntent().getExtras();

        boletoVIP= misboletos.getInt("VIP");
        boletoORO= misboletos.getInt("ORO");
        boletoPLATA= misboletos.getInt("PLATA");

        List<Asiento> listaAsientosServido = ArraySingleton.getInstance().getAsientosservidor();
        List<asientoEstatico> listaAsientosLocal = ArraySingleton.getInstance().getAsientosModificados();
        List<PreciosZona> listaprecios= ArraySingleton.getInstance().getListaPrecios();

        if(boletoVIP > 0){
            zonaVIP.setVisibility(View.VISIBLE);

        }if(boletoORO > 0){
            zonaORO.setVisibility(View.VISIBLE);
        }if(boletoPLATA > 0){
            zonaPLATA.setVisibility(View.VISIBLE);
        }

        String asientosVIP="";
        String asientosORO="";
        String asientosPLATA="";

        String sinComa = "";


        double precioVIP=0;
        double precioORO=0;
        double precioPLATA=0;


        for(asientoEstatico asientoLocal: listaAsientosLocal){

            // si hay eventos VIP
            if(asientoLocal.getId_zona()== 1){
                if(asientoLocal.estaSeleccionado().equals("0")){
                    contadorVIP ++;
                    asientosVIP = asientosVIP + asientoLocal.getFila() +asientoLocal.getColumna()+",";
                     sinComa = asientosVIP.substring(0,asientosVIP.length()-1);
                    for (PreciosZona elemento :listaprecios) {
                        if(elemento.getZona_id()==1){
                            precioVIP = elemento.getPrecio();
                        }
                    }

                }

                txtzonaVIP.setText("VIP");
                txtboletosVIP.setText(String.valueOf(boletoVIP));
                txtasientosVIP.setText(sinComa);
                txtcostosVIP.setText("$"+String.valueOf(precioVIP));

                SharedPreferences preferencesVIP = getSharedPreferences("idVIP",Context.MODE_PRIVATE);
                SharedPreferences.Editor E_id_VIP=preferencesVIP.edit();
                E_id_VIP.putInt("idVIP", 1);
                E_id_VIP.commit();


            }



            //si hay eventos ORO

            if(asientoLocal.getId_zona()==2){

                if(asientoLocal.estaSeleccionado().equals("0")){
                    contadorORO++;
                    asientosORO = asientosORO + asientoLocal.getFila() +asientoLocal.getColumna()+",";
                    sinComa = asientosORO.substring(0,asientosORO.length()-1);
                    for (PreciosZona elemento :listaprecios) {
                        if(elemento.getZona_id()==2){
                            precioORO = elemento.getPrecio();
                        }
                    }

                }

                txtzonaORO.setText("ORO");
                txtboletosORO.setText(String.valueOf(boletoORO));
                txtasientosORO.setText(sinComa);
                txtcostosORO.setText("$"+String.valueOf(precioORO));
                SharedPreferences preferencesORO = getSharedPreferences("idORO",Context.MODE_PRIVATE);
                SharedPreferences.Editor E_id_ORO=preferencesORO.edit();
                E_id_ORO.putInt("idORO", 2);
                E_id_ORO.commit();


            }


            // si hay eventos PLATA

            if(asientoLocal.getId_zona()==3){

                if(asientoLocal.estaSeleccionado().equals("0")){
                    contadorPLATA++;
                    asientosPLATA = asientosPLATA + asientoLocal.getFila() +asientoLocal.getColumna()+",";
                    sinComa = asientosPLATA.substring(0,asientosPLATA.length()-1);
                    for (PreciosZona elemento :listaprecios) {
                        if(elemento.getZona_id()==2){
                            precioPLATA = elemento.getPrecio();
                        }
                    }

                }

                txtzonaPLATA.setText("PLATA");
                txtboletosPLATA.setText(String.valueOf(boletoPLATA));
                txtasientosPLATA.setText(sinComa);
                txtcostosPLATA.setText("$"+String.valueOf(precioPLATA));
                SharedPreferences preferencesPLATA = getSharedPreferences("idPLATA",Context.MODE_PRIVATE);
                SharedPreferences.Editor E_id_PLATA=preferencesPLATA.edit();
                E_id_PLATA.putInt("idPLATA", 3);
                E_id_PLATA.commit();


            }

        }


        double suma= (precioVIP * boletoVIP) +(precioORO*boletoORO)+(precioPLATA*boletoPLATA);
        txtPago.setText("TOTAL A PAGAR:    $"+suma);


        tarjetaPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( checkBox.isChecked()){
                    int boletos = boletoVIP +boletoORO +boletoPLATA;

                    Bundle misDatos= new Bundle();
                    misboletos.putInt("boletos",boletos);
                    misboletos.putDouble("total",suma);


                    Intent intent= new Intent(MedioDePagoActivity.this,TarjetaDePagoActivity.class);
                    intent.putExtras(misboletos);
                    startActivity(intent);
                    finish();
                }else{
                    checkBox.setError("Acepta los términos y condiciones");
                }

            }
        });

        ActionBar Actionbar= getSupportActionBar();
        if(Actionbar != null){
            Actionbar.setDisplayHomeAsUpEnabled(true);
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
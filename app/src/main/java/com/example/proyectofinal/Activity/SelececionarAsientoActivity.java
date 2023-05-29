package com.example.proyectofinal.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.R;
import com.example.proyectofinal.models.ArraySingleton;
import com.example.proyectofinal.models.Asiento;
import com.example.proyectofinal.models.Directorio;
import com.example.proyectofinal.models.IdSingleton;
import com.example.proyectofinal.models.asientoEstatico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelececionarAsientoActivity extends AppCompatActivity {

    TextView    A1,A2,A3,A4,A5,B1,B2,B3,B4,B5,C1,C2,C3,C4,C5,C6,C7,D1,D2,D3,D4,D5,D6,D7,E1,E2,E3,E4,E5,E6,E7,F1,F2,F3,F4,F5,F6,F7,fila;
    HashMap<String, TextView> textViewMap = new HashMap<>();
    int VIPcounter,OROcounter,PLATAcounter;
    ArrayList<Asiento> ListaAsientoEvento= new ArrayList<>();
    Button btnContinuarcompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DECLARACION DE TODOS LOS TEXT QUE SON ASIENTOS----------------------------------------------------------------------------------
        setContentView(R.layout.activity_selececionar_asiento);
        btnContinuarcompra = findViewById(R.id.btnContinuarCopra);
        btnContinuarcompra.setEnabled(false);

        Bundle misdatos= getIntent().getExtras();
        VIPcounter = misdatos.getInt("VIPcounter");
        OROcounter = misdatos.getInt("OROcounter");
        PLATAcounter = misdatos.getInt("PLATAcounter");


        //fila A------------------------------------------------------------------------------------------
        A1=findViewById(R.id.A1);
        A2=findViewById(R.id.A2);
        A3=findViewById(R.id.A3);
        A4=findViewById(R.id.A4);
        A5=findViewById(R.id.A5);

        //fila B------------------------------------------------------------------------------------------
        B1=findViewById(R.id.B1);
        B2=findViewById(R.id.B2);
        B3=findViewById(R.id.B3);
        B4=findViewById(R.id.B4);
        B5=findViewById(R.id.B5);

        //fila C------------------------------------------------------------------------------------------
        C1=findViewById(R.id.C1);
        C2=findViewById(R.id.C2);
        C3=findViewById(R.id.C3);
        C4=findViewById(R.id.C4);
        C5=findViewById(R.id.C5);
        C6=findViewById(R.id.C6);
        C7=findViewById(R.id.C7);

        //fila D------------------------------------------------------------------------------------------
        D1=findViewById(R.id.D1);
        D2=findViewById(R.id.D2);
        D3=findViewById(R.id.D3);
        D4=findViewById(R.id.D4);
        D5=findViewById(R.id.D5);
        D6=findViewById(R.id.D6);
        D7=findViewById(R.id.D7);

        //fila E------------------------------------------------------------------------------------------
        E1=findViewById(R.id.E1);
        E2=findViewById(R.id.E2);
        E3=findViewById(R.id.E3);
        E4=findViewById(R.id.E4);
        E5=findViewById(R.id.E5);
        E6=findViewById(R.id.E6);
        E7=findViewById(R.id.E7);

        //fila F------------------------------------------------------------------------------------------
        F1=findViewById(R.id.F1);
        F2=findViewById(R.id.F2);
        F3=findViewById(R.id.F3);
        F4=findViewById(R.id.F4);
        F5=findViewById(R.id.F5);
        F6=findViewById(R.id.F6);
        F7=findViewById(R.id.F7);

        textViewMap.put("A1",A1);
        textViewMap.put("A2",A2);
        textViewMap.put("A3",A3);
        textViewMap.put("A4",A4);
        textViewMap.put("A5",A5);

        textViewMap.put("B1",B1);
        textViewMap.put("B2",B2);
        textViewMap.put("B3",B3);
        textViewMap.put("B4",B4);
        textViewMap.put("B5",B5);

        textViewMap.put("C1",C1);
        textViewMap.put("C2",C2);
        textViewMap.put("C3",C3);
        textViewMap.put("C4",C4);
        textViewMap.put("C5",C5);
        textViewMap.put("C6",C6);
        textViewMap.put("C7",C7);

        textViewMap.put("D1",D1);
        textViewMap.put("D2",D2);
        textViewMap.put("D3",D3);
        textViewMap.put("D4",D4);
        textViewMap.put("D5",D5);
        textViewMap.put("D6",D6);
        textViewMap.put("D7",D7);

        textViewMap.put("E1",E1);
        textViewMap.put("E2",E2);
        textViewMap.put("E3",E3);
        textViewMap.put("E4",E4);
        textViewMap.put("E5",E5);
        textViewMap.put("E6",E6);
        textViewMap.put("E7",E7);

        textViewMap.put("F1",F1);
        textViewMap.put("F2",F2);
        textViewMap.put("F3",F3);
        textViewMap.put("F4",F4);
        textViewMap.put("F5",F5);
        textViewMap.put("F6",F6);
        textViewMap.put("F7",F7);





       // AACTIVACION DE LA FLECHA DE REGRESOOOOO-----------------------------------------------------------------
        ActionBar actionBar= getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilitar el botón de retroceso
        }



        //METODO PARA LLAMAR A LA API Y OBTENER LOS DATOS DE LOS ASIENTOS_______-------------------------------------------------
        int respuesta = obtenerDatosAsientos();


    }


    public int obtenerDatosAsientos(){
            int id= IdSingleton.getInstance().getId();
           String URL = Directorio.getInstance().getApiUrls("api6");
            StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                        try {
                            JSONArray response2= new JSONArray(response);
                            if(response2.length() !=0){
                                for (int i = 0; i < response2.length(); i++) {

                                    JSONObject jsonObject = response2.getJSONObject(i);

                                    int id = jsonObject.getInt("id");
                                    String fila = jsonObject.getString("fila");
                                    int columna = jsonObject.getInt("columna");
                                    String  disponibilidad = jsonObject.getString("disponibilida");
                                    int zona_teatro_id= jsonObject.getInt("zona_teatro_id");
                                    int evento_teatro_id= jsonObject.getInt("evento_teatro_id");
                                    int status= jsonObject.getInt("status");

                                    Log.d("TAG",disponibilidad+"************************************************************************+");
                                    ListaAsientoEvento.add(new Asiento(id,fila,columna,disponibilidad,zona_teatro_id,evento_teatro_id,status));

                                }

                                List<asientoEstatico>   asientovista= new ArrayList<>();///////////--------------------


                                for(Asiento asiento: ListaAsientoEvento) {

                                    if(asiento.getDisponibilidad().equals("2")){
                                        asientovista.add(new asientoEstatico(asiento.getFila(), asiento.getColumna(),asiento.getZona_teatro_id(),"2",asiento.getId()));

                                    }else if(asiento.getDisponibilidad().equals("1")){
                                        asientovista.add(new asientoEstatico(asiento.getFila(), asiento.getColumna(),asiento.getZona_teatro_id(),asiento.getId()));

                                    }


                                }



                                for(asientoEstatico asiento: asientovista){
                                    String asientos= asiento.getFila() + String.valueOf(asiento.getColumna());
                                    TextView textView = textViewMap.get(asientos);

                                    if(asiento.estaSeleccionado().equals("1")){
                                        textView.setBackgroundResource(R.drawable.butaca_disponible);
                                    }else if(asiento.estaSeleccionado().equals("2")){
                                        textView.setBackgroundResource(R.drawable.butaca_ocupada);
                                        textView.setEnabled(false);
                                    }


                                }

                                final int[] cantidadDeBoletos = {0};
                                final int[] cantidadDeBoletosORO={0};
                                final int[] cantidadDeBoletosPLATA={0};
                                final int[] sumaboletos = new int[1];
                                final int[] sumaseleccion = new int[1];

                                // Crear un único OnClickListener para todos los TextView
                                View.OnClickListener textClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // Obtener el TextView que fue presionado
                                        TextView textView = (TextView) view;



                                        // Realizar la lógica correspondiente según el TextView presionado
                                        switch (textView.getId()) {
                                            case R.id.A1:

                                                    if (asientovista.get(0).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(0).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(0).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);

                                                break;

                                            case R.id.A2:

                                                if (asientovista.get(1).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(1).setSeleccionado("1");
                                                    cantidadDeBoletos[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletos[0] < VIPcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(1).setSeleccionado("0");
                                                        cantidadDeBoletos[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);

                                                break;



                                            case R.id.A3:
                                                    if (asientovista.get(2).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(2).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(2).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;



                                            case R.id.A4:
                                                    if (asientovista.get(3).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(3).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(3).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.A5:
                                                    if (asientovista.get(4).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(4).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(4).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;



                                            case R.id.B1:
                                                if (asientovista.get(5).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(5).setSeleccionado("1");
                                                    cantidadDeBoletos[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletos[0] < VIPcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(5).setSeleccionado("0");
                                                        cantidadDeBoletos[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;

                                            case R.id.B2:
                                                    if (asientovista.get(6).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(6).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(6).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.B3:
                                                    if (asientovista.get(7).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(7).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(7).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.B4:
                                                    if (asientovista.get(8).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(8).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(8).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.B5:
                                                    if (asientovista.get(9).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(9).setSeleccionado("1");
                                                        cantidadDeBoletos[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletos[0] < VIPcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(9).setSeleccionado("0");
                                                            cantidadDeBoletos[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.C1:
                                                    if (asientovista.get(10).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(10).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(10).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C2:
                                                    if (asientovista.get(11).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(11).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(11).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C3:
                                                    if (asientovista.get(12).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(12).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(12).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C4:
                                                    if (asientovista.get(13).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(13).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(13).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C5:
                                                    if (asientovista.get(14).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(14).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(14).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C6:

                                                if (asientovista.get(15).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(15).setSeleccionado("1");
                                                    cantidadDeBoletosORO[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletosORO[0] < OROcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(15).setSeleccionado("0");
                                                        cantidadDeBoletosORO[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.C7:
                                                    if (asientovista.get(16).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(16).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(16).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.D1:
                                                    if (asientovista.get(17).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(17).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(17).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.D2:
                                                    if (asientovista.get(18).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(18).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(18).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.D3:
                                                    if (asientovista.get(19).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(19).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(19).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.D4:
                                                    if (asientovista.get(20).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(20).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(20).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;

                                            case R.id.D5:
                                                    if (asientovista.get(21).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(21).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(21).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.D6:
                                                    if (asientovista.get(22).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(22).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(22).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.D7:
                                                    if (asientovista.get(23).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(23).setSeleccionado("1");
                                                        cantidadDeBoletosORO[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosORO[0] < OROcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(23).setSeleccionado("0");
                                                            cantidadDeBoletosORO[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;





                                            case R.id.E1:
                                                    if (asientovista.get(24).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(24).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(24).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.E2:
                                                if (asientovista.get(25).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(25).setSeleccionado("1");
                                                    cantidadDeBoletosPLATA[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(25).setSeleccionado("0");
                                                        cantidadDeBoletosPLATA[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.E3:
                                                    if (asientovista.get(26).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(26).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(26).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.E4:
                                                    if (asientovista.get(27).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(27).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(27).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.E5:
                                                    if (asientovista.get(28).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(28).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(28).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.E6:
                                                    if (asientovista.get(29).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(29).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(29).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.E7:
                                                    if (asientovista.get(30).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(30).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(30).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;





                                            case R.id.F1:
                                                    if (asientovista.get(31).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(31).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(31).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.F2:
                                                    if (asientovista.get(32).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(32).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(32).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.F3:
                                                    if (asientovista.get(33).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(33).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(33).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.F4:
                                                if (asientovista.get(34).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(34).setSeleccionado("1");
                                                    cantidadDeBoletosPLATA[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(34).setSeleccionado("0");
                                                        cantidadDeBoletosPLATA[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.F5:
                                                    if (asientovista.get(35).estaSeleccionado().equals("0")) {
                                                        // El asiento ya está seleccionado, deselecciónalo
                                                        asientovista.get(35).setSeleccionado("1");
                                                        cantidadDeBoletosPLATA[0]--;
                                                    }else{
                                                        // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                        if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                            // Selecciona el asiento
                                                            asientovista.get(35).setSeleccionado("0");
                                                            cantidadDeBoletosPLATA[0]++;
                                                            Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;


                                            case R.id.F6:
                                                if (asientovista.get(36).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(36).setSeleccionado("1");
                                                    cantidadDeBoletosPLATA[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(36).setSeleccionado("0");
                                                        cantidadDeBoletosPLATA[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);
                                                break;
                                            case R.id.F7:
                                                if (asientovista.get(37).estaSeleccionado().equals("0")) {
                                                    // El asiento ya está seleccionado, deselecciónalo
                                                    asientovista.get(37).setSeleccionado("1");
                                                    cantidadDeBoletosPLATA[0]--;
                                                }else{
                                                    // El asiento no está seleccionado, verifica si hay suficientes boletos disponibles
                                                    if (cantidadDeBoletosPLATA[0] < PLATAcounter) {
                                                        // Selecciona el asiento
                                                        asientovista.get(37).setSeleccionado("0");
                                                        cantidadDeBoletosPLATA[0]++;
                                                        Toast.makeText(SelececionarAsientoActivity.this, String.valueOf(cantidadDeBoletos[0]), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                sumaseleccion[0] = cantidadDeBoletos[0]+cantidadDeBoletosORO[0]+cantidadDeBoletosPLATA[0];
                                                sumaboletos[0] =VIPcounter+OROcounter+PLATAcounter;
                                                actualizar_asientos(asientovista,sumaseleccion[0],sumaboletos[0]);

                                                break;


                                        }
                                    }
                                };

                                //Asignamos el  textview al listener _________________________________________________________________


                                for (Asiento asiento: ListaAsientoEvento) {
                                    int idtxt= getResources().getIdentifier(asiento.getFila() + String.valueOf(asiento.getColumna()),"id", getPackageName());
                                    TextView textView = findViewById(idtxt);

                                    textView.setOnClickListener(textClickListener);
                                }




                                //INSTANCIA PARA EL EVENTO CLIC DEL BOTON-------------------------------------------------------------

                                btnContinuarcompra.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Bundle misBoletos= new Bundle();
                                        misBoletos.putInt("VIP",VIPcounter);
                                        misBoletos.putInt("ORO",OROcounter);
                                        misBoletos.putInt("PLATA",PLATAcounter);
                                        Intent intent= new Intent(SelececionarAsientoActivity.this,MedioDePagoActivity.class);
                                        intent.putExtras(misBoletos);
                                        startActivity(intent);
                                        //finish();
                                    }
                                });

                                Log.d("TAG", String.valueOf(ListaAsientoEvento.size()));
                            }else{

                                Toast.makeText(SelececionarAsientoActivity.this, "No se ha determinado el precio " + response, Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            Log.d("TAG",e.toString());
                            e.printStackTrace();
                            Toast.makeText(SelececionarAsientoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(SelececionarAsientoActivity.this, "error consulta", Toast.LENGTH_SHORT).show();

                }
            }){

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros= new HashMap<String,String>();
                    parametros.put("evento_teatro_id", String.valueOf(id));
                    return  parametros;
                }


            };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void actualizar_asientos(List<asientoEstatico> asientovista,int sumaseleccion,int sumaboletos){

        TextView textView = null;
        for(asientoEstatico asiento: asientovista){
            String asientos= asiento.getFila() + String.valueOf(asiento.getColumna());
             textView = textViewMap.get(asientos);

            if(asiento.estaSeleccionado().equals("1")){
                textView.setBackgroundResource(R.drawable.butaca_disponible);
            }else if(asiento.estaSeleccionado().equals("2")){
                textView.setBackgroundResource(R.drawable.butaca_ocupada);
            }else{
                textView.setBackgroundResource(R.drawable.butaca_selecionada);
            }



        }

        if(sumaseleccion==sumaboletos){
            btnContinuarcompra.setEnabled(true);
            ArraySingleton.getInstance().setAsientosservidor(ListaAsientoEvento);
            ArraySingleton.getInstance().setAsientosModificados(asientovista);

        }else{
            btnContinuarcompra.setEnabled(false);
        }





    }




}
package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Header;
import com.bumptech.glide.load.model.Headers;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.databinding.ActivityMenuBinding;

public class Activity_menu extends AppCompatActivity {

    public NavigationView  navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        navigationView=findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        TextView txtnombre= header.findViewById(R.id.nombre);
        TextView txtemail= header.findViewById(R.id.Email);

        SharedPreferences preferenciasID= getSharedPreferences("id", Context.MODE_PRIVATE);
        int id = preferenciasID.getInt("id",0);

        SharedPreferences preferenciasNAME= getSharedPreferences("nombre", Context.MODE_PRIVATE);
        String nombre = preferenciasNAME.getString("nombre","");

        SharedPreferences preferenciasEMAIL= getSharedPreferences("email", Context.MODE_PRIVATE);
        String email = preferenciasEMAIL.getString("email","");

        SharedPreferences preferenciasFOTO= getSharedPreferences("foto", Context.MODE_PRIVATE);
        String foto = preferenciasFOTO.getString("foto","");


        Menu menu = navigationView.getMenu();
        MenuItem menuISesion = menu.findItem(R.id.nav_iniciosesion);

        MenuItem Configuracion= menu.findItem(R.id.nav_configuracion);
        MenuItem contacto= menu.findItem(R.id.nav_contacto);

        Configuracion.setEnabled(false);
        contacto.setEnabled(false);


        MenuItem menuid = menu.findItem(R.id.nav_cerrarsesion);


        if(!nombre.isEmpty()){
            txtnombre.setText(nombre.toString());
            txtemail.setText(email.toString());
            menuISesion.setVisible(false);
            menuid.setVisible(true);

        }else{
            menuISesion.setVisible(true);
            menuid.setVisible(false);
        }




        menuid.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                    Toast.makeText(Activity_menu.this, "no pasa nada ", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferenciasID= getSharedPreferences("id", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferenciasID.edit();
                    editor.clear();
                    editor.commit();

                    SharedPreferences preferenciasNAME= getSharedPreferences("nombre", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorN= preferenciasNAME.edit();
                    editorN.clear();
                    editorN.commit();

                    SharedPreferences preferenciasEMAIL= getSharedPreferences("email", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorE= preferenciasEMAIL.edit();
                    editorE.clear();
                    editorE.commit();

                    SharedPreferences preferenciasFOTO= getSharedPreferences("foto", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorF= preferenciasFOTO.edit();
                    editorF.clear();
                    editorF.commit();

                    txtnombre.setText("Sin nombre");
                    txtemail.setText("*****************");



                    menuISesion.setVisible(true);
                    menuid.setVisible(false);
                    Intent inten= new Intent(Activity_menu.this, Activity_menu.class);
                    startActivity(inten);
                    finish();




                    return false;
                }
            });


        MenuItem menulogini= menu.findItem(R.id.nav_iniciosesion);
        menulogini.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                finish();
                return false;

            }
        });



        setSupportActionBar(binding.appBarActivityMenu.toolbar);
        binding.appBarActivityMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_configuracion, R.id.nav_contacto)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
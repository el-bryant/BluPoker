package com.poker;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.poker.adapters.ApuestasAdapter;
import com.poker.pojos.ClsApuesta;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.poker.Acceder.id_usuario;
import static com.poker.CrearMesa.id_mesa;
import static com.poker.Mesas.tvClub;

public class Juego9 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerApuestas;
    ArrayList<ClsApuesta> apuestas;
    ApuestasAdapter apuestasAdapter;
    AutoCompleteTextView txtMonto;
    int monto;
    TextInputEditText mesa;
    ImageView com1, com2, com3, com4, com5,jgc1,jgc2;
    int [] ImgNros = new int[53];
    LinearLayout usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7, usuario8, usuario9, mesa_fondo, cajaUser1, btnuser1,
        btnuser2, btnuser3, btnuser4, btnuser5, btnuser6, btnuser7, btnuser8, btnuser9, fondo;
    ConstraintLayout cajaPerfilUser1;
    TextView us1, us2 , us3 ,us4 , us5 , us6 ,us7, us8, us9, cron, tvId_mesa, tvCiegaMinima, tvCiegaMaxima;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    public static ProgressBar barra ;
    public static  int tipo_mesa=0;
    public static String[] IdJug = new String[9];
    public static int OrdenJug=0;
    public static String [] NomJug = new String [9];
    public static int [] PuestJug = new int [9];
    public static int [] jg1num = new int [4];
    public static String [] jg1palo = new String [4];
    public static int [] jg1id = new int[4];
    public static int [] jg2num = new int [4];
    public static String [] jg2palo = new String [4];
    public static int [] jg3num = new int [4];
    public static String [] jg3palo = new String [4];
    public static int [] jg4num = new int [4];
    public static String [] jg4palo = new String [4];
    public static int [] jg5num = new int [4];
    public static String [] jg5palo = new String [4];
    public static int [] jg6num = new int [4];
    public static String [] jg6palo = new String [4];
    public static int [] jg7num = new int [4];
    public static String [] jg7palo = new String [4];
    public static int [] jg8num = new int [4];
    public static String [] jg8palo = new String [4];
    public static int [] jg9num = new int [4];
    public static String [] jg9palo = new String [4];
    public static int [] idcomnum = new int [5];
    public static int [] comnum = new int [5];
    public static String [] compalo = new String [5];
    public static String [] rpts = new String [10];
    public static  ArrayList <String> idjug = new ArrayList <> ();
    public static int x = 0;
    public static int y =0;
    public static Timer reloj;
    public static String recup_ciega_minima, recup_ciega_maxima, recup_id_mesa, id_mano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juego9);

        mesa_fondo = (LinearLayout) findViewById(R.id.imvMesa);
        cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1Juego9);
        cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1Juego9);
        us1 = (TextView) findViewById(R.id.tvNombre1Juego9);
        us2 = (TextView) findViewById(R.id.tvNombre2Juego9);
        us3 = (TextView) findViewById(R.id.tvNombre3Juego9);
        us4 = (TextView) findViewById(R.id.tvNombre4Juego9);
        us5 = (TextView) findViewById(R.id.tvNombre5Juego9);
        us6 = (TextView) findViewById(R.id.tvNombre6Juego9);
        us7 = (TextView) findViewById(R.id.tvNombre7Juego9);
        us8 = (TextView) findViewById(R.id.tvNombre8Juego9);
        us9 = (TextView) findViewById(R.id.tvNombre9Juego9);
        btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego9);
        btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego9);
        btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego9);
        btnuser4 = (LinearLayout) findViewById(R.id.linPosicion4Juego9);
        btnuser5 = (LinearLayout) findViewById(R.id.linPosicion5Juego9);
        btnuser6 = (LinearLayout) findViewById(R.id.linPosicion6Juego9);
        btnuser7 = (LinearLayout) findViewById(R.id.linPosicion7Juego9);
        btnuser8 = (LinearLayout) findViewById(R.id.linPosicion8Juego9);
        btnuser9 = (LinearLayout) findViewById(R.id.linPosicion9Juego9);
        usuario1 = (LinearLayout) findViewById(R.id.lilUsuario1Juego9);
        usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2Juego9);
        usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3Juego9);
        usuario4 = (LinearLayout) findViewById(R.id.lilUsuario4Juego9);
        usuario5 = (LinearLayout) findViewById(R.id.lilUsuario5Juego9);
        usuario6 = (LinearLayout) findViewById(R.id.lilUsuario6Juego9);
        usuario7 = (LinearLayout) findViewById(R.id.lilUsuario7Juego9);
        usuario8 = (LinearLayout) findViewById(R.id.lilUsuario8Juego9);
        usuario9 = (LinearLayout) findViewById(R.id.lilUsuario9Juego9);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerApuestas = (RecyclerView) findViewById(R.id.recyclerapuestas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerApuestas.setLayoutManager(linearLayoutManager);

        apuestasAdapter = new ApuestasAdapter(this, apuestas);
        recyclerApuestas.setAdapter(apuestasAdapter);

        //cambia el fondo en cada rotación
        int orientation = getResources().getConfiguration().orientation;
        Log.d("Changescreen", "Orientation: " + orientation);
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                horizontal();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                vertical();
                break;
        }
        //pintar(nros);
        //PrimerProcesoxGanador();
        //PrimerProcesoxLlenar();

        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        tvId_mesa = (TextView) findViewById(R.id.numero_mesa);
        DecimalFormat df = new DecimalFormat("000000000");
        tvId_mesa.setText(df.format(Integer.parseInt(recup_id_mesa)));

        recup_ciega_minima = getIntent().getStringExtra("ciega_minima");
        tvCiegaMinima = (TextView) findViewById(R.id.ciega_menor);
        tvCiegaMinima.setText("$" + recup_ciega_minima);

        recup_ciega_maxima = getIntent().getStringExtra("ciega_maxima");
        tvCiegaMaxima = (TextView) findViewById(R.id.ciega_mayor);
        tvCiegaMaxima.setText("$" + recup_ciega_maxima);

        Verificar();
    }

    public void Verificar() {
        cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1Juego9);
        cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1Juego9);
        us1 = (TextView) findViewById(R.id.tvNombre1Juego9);
        us2 = (TextView) findViewById(R.id.tvNombre2Juego9);
        us3 = (TextView) findViewById(R.id.tvNombre3Juego9);
        us4 = (TextView) findViewById(R.id.tvNombre4Juego9);
        us5 = (TextView) findViewById(R.id.tvNombre5Juego9);
        us6 = (TextView) findViewById(R.id.tvNombre6Juego9);
        us7 = (TextView) findViewById(R.id.tvNombre7Juego9);
        us8 = (TextView) findViewById(R.id.tvNombre8Juego9);
        us9 = (TextView) findViewById(R.id.tvNombre9Juego9);
        btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego9);
        btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego9);
        btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego9);
        btnuser4 = (LinearLayout) findViewById(R.id.linPosicion4Juego9);
        btnuser5 = (LinearLayout) findViewById(R.id.linPosicion5Juego9);
        btnuser6 = (LinearLayout) findViewById(R.id.linPosicion6Juego9);
        btnuser7 = (LinearLayout) findViewById(R.id.linPosicion7Juego9);
        btnuser8 = (LinearLayout) findViewById(R.id.linPosicion8Juego9);
        btnuser9 = (LinearLayout) findViewById(R.id.linPosicion9Juego9);
        usuario1 = (LinearLayout) findViewById(R.id.lilUsuario1Juego9);
        usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2Juego9);
        usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3Juego9);
        usuario4 = (LinearLayout) findViewById(R.id.lilUsuario4Juego9);
        usuario5 = (LinearLayout) findViewById(R.id.lilUsuario5Juego9);
        usuario6 = (LinearLayout) findViewById(R.id.lilUsuario6Juego9);
        usuario7 = (LinearLayout) findViewById(R.id.lilUsuario7Juego9);
        usuario8 = (LinearLayout) findViewById(R.id.lilUsuario8Juego9);
        usuario9 = (LinearLayout) findViewById(R.id.lilUsuario9Juego9);
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        reloj = new Timer();
        x = 0;
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Thread tr1 = new Thread() {
                    @Override
                    public void run() {
                        final String result1 = PriProcVerificar("http://gosmart.pe/poker/Obtener_Jug_Mesa.php?id_mesa="
                                + recup_id_mesa);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r = SegProcVerificar(result1);
                                if (r > 0) {
                                    try {
                                        JSONArray json = new JSONArray(result1);
                                        if (json.length() > 0) {
                                            TercProcVerificar(result1);
                                            if (!us1.getText().toString().equals("")) {
                                                cajaUser1.setVisibility(View.VISIBLE);
                                                btnuser1.setVisibility(View.INVISIBLE);
                                                cajaPerfilUser1.setVisibility(View.VISIBLE);
                                                usuario1.setVisibility(View.VISIBLE);
                                            }
                                            if (!us2.getText().toString().equals("")) {
                                                btnuser2.setVisibility(View.INVISIBLE);
                                                usuario2.setVisibility(View.VISIBLE);
                                            }
                                            if (!us3.getText().toString().equals("")) {
                                                btnuser3.setVisibility(View.INVISIBLE);
                                                usuario3.setVisibility(View.VISIBLE);
                                            }
                                            if (!us4.getText().toString().equals("")) {
                                                btnuser4.setVisibility(View.INVISIBLE);
                                                usuario4.setVisibility(View.VISIBLE);
                                            }
                                            if (!us5.getText().toString().equals("")) {
                                                btnuser5.setVisibility(View.INVISIBLE);
                                                usuario5.setVisibility(View.VISIBLE);
                                            }
                                            if (!us6.getText().toString().equals("")) {
                                                btnuser6.setVisibility(View.INVISIBLE);
                                                usuario6.setVisibility(View.VISIBLE);
                                            }
                                            if (!us7.getText().toString().equals("")) {
                                                btnuser7.setVisibility(View.INVISIBLE);
                                                usuario7.setVisibility(View.VISIBLE);
                                            }
                                            if (!us8.getText().toString().equals("")) {
                                                btnuser8.setVisibility(View.INVISIBLE);
                                                usuario8.setVisibility(View.VISIBLE);
                                            }
                                            if (!us9.getText().toString().equals("")) {
                                                btnuser9.setVisibility(View.INVISIBLE);
                                                usuario9.setVisibility(View.VISIBLE);
                                            }
                                            //aquí ya iniciará el juego
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                };
                tr1.start();
            }
        };
        reloj.schedule(tarea,0,1000);
    }

    public String PriProcVerificar(String consulta) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(consulta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            Log.i("depurar",e.toString());
        }
        return result.toString();
    }

    public int SegProcVerificar (String responses) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(responses);
            if (json.length() > 0) {
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void TercProcVerificar (String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                IdJug[i] = json.getJSONObject(i).getString("id_usuario");
                NomJug[i] = json.getJSONObject(i).getString("usuario");
                PuestJug[i] = json.getJSONObject(i).getInt("orden");
                if (IdJug[i].equals(id_usuario)) {
                    btnuser1.setVisibility(View.INVISIBLE);
                    btnuser2.setVisibility(View.INVISIBLE);
                    btnuser3.setVisibility(View.INVISIBLE);
                    btnuser4.setVisibility(View.INVISIBLE);
                    btnuser5.setVisibility(View.INVISIBLE);
                    btnuser6.setVisibility(View.INVISIBLE);
                    btnuser7.setVisibility(View.INVISIBLE);
                    btnuser8.setVisibility(View.INVISIBLE);
                    btnuser9.setVisibility(View.INVISIBLE);
                } else {
                    for (int j = 0; j < PuestJug.length; j++) {
                        switch (PuestJug[i]) {
                            case 1:
                                us1.setText(NomJug[i]);
                                break;
                            case 2:
                                us2.setText(NomJug[i]);
                                break;
                            case 3:
                                us3.setText(NomJug[i]);
                                break;
                            case 4:
                                us4.setText(NomJug[i]);
                                break;
                            case 5:
                                us5.setText(NomJug[i]);
                                break;
                            case 6:
                                us6.setText(NomJug[i]);
                                break;
                            case 7:
                                us7.setText(NomJug[i]);
                                break;
                            case 8:
                                us8.setText(NomJug[i]);
                                break;
                            case 9:
                                us9.setText(NomJug[i]);
                                break;
                        }
                    }
                }
            }
            us1.setText(NomJug[0]);
            us2.setText(NomJug[1]);
            us3.setText(NomJug[2]);
            us4.setText(NomJug[3]);
            us5.setText(NomJug[4]);
            us6.setText(NomJug[5]);
            us7.setText(NomJug[6]);
            us8.setText(NomJug[7]);
            us9.setText(NomJug[8]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public void horizontal() {
        fondo = (LinearLayout) findViewById(R.id.fondo);
        Drawable d = getResources().getDrawable(R.drawable.fondo_h);
        //fondo.setBackground(d);
        mesa_fondo = (LinearLayout) findViewById(R.id.imvMesa);
        Drawable mf = getResources().getDrawable(R.drawable.mesa_h);
        mesa_fondo.setBackground(mf);
    }

    public void vertical() {
        fondo = (LinearLayout) findViewById(R.id.fondo);
        Drawable d = getResources().getDrawable(R.drawable.fondo_v);
        //fondo.setBackground(d);
        mesa_fondo = (LinearLayout) findViewById(R.id.imvMesa);
        Drawable mf = getResources().getDrawable(R.drawable.mesa_v);
        mesa_fondo.setBackground(mf);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PrimerProcesoxLlenar() {
        Thread tr1 = new Thread() {
            @Override
            public void run() {
                try {
                    final String result1 = SegundoProcesoCartComun("http://gosmart.pe/poker/obtener_tipo_moda_mesa.php?id_mesa="
                            + CrearMesa.id_mesa);
                    final String result2 = SegundoProcesoCartComun("http://gosmart.pe/poker/Consultar_Com.php?id_mesa="
                            + CrearMesa.id_mesa);
                    final String result3 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Cart_Jug.php?id_mesa="
                            + CrearMesa.id_mesa + "&id_usuario=" + Acceder.id_usuario);
                    final String result4 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Jug_Mesa.php?id_mesa="
                            + CrearMesa.id_mesa);
                    final String result5 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Orden.php?id_usuario="
                            + Acceder.id_usuario + "&id_mesa=" + CrearMesa.id_mesa);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //ID_JUG
                            Log.i("depurar","click");
                            EntAImg();
                            com1 = (ImageView) findViewById(R.id.imvComunitaria1);
                            com2 = (ImageView) findViewById(R.id.imvComunitaria2);
                            com3 = (ImageView) findViewById(R.id.imvComunitaria3);
                            com4 = (ImageView) findViewById(R.id.imvComunitaria4);
                            com5 = (ImageView) findViewById(R.id.imvComunitaria5);
                            jgc1 = (ImageView) findViewById(R.id.imvUsuario1C1);
                            jgc2 = (ImageView) findViewById(R.id.imvUsuario1C2);
                            us1 = (TextView) findViewById(R.id.tvNombre1Juego9);
                            us2 = (TextView) findViewById(R.id.tvNombre2Juego9);
                            us3 = (TextView) findViewById(R.id.tvNombre3Juego9);
                            us4 = (TextView) findViewById(R.id.tvNombre4Juego9);
                            us5 = (TextView) findViewById(R.id.tvNombre5Juego9);
                            us6 = (TextView) findViewById(R.id.tvNombre6Juego9);
                            us7 = (TextView) findViewById(R.id.tvNombre7Juego9);
                            us8 = (TextView) findViewById(R.id.tvNombre8Juego9);
                            us9 = (TextView) findViewById(R.id.tvNombre9Juego9);
                            int r = TercerProcesoCarCom(result1);
                            if (r > 0) {
                                CuartoProcesoTipo(result1);
                            }
                            r = TercerProcesoCarCom(result2);
                            if (r > 0) {
                                CuartoProcesoCartComun(result2);
                            }

                            r = TercerProcesoCarCom(result3);
                            if (r > 0) {
                                CuartoProcesoCartJug(result3);
                            }
                            com1.setImageResource(ImgNros[idcomnum[0]]);
                            com2.setImageResource(ImgNros[idcomnum[1]]);
                            com3.setImageResource(ImgNros[idcomnum[2]]);
                            com4.setImageResource(ImgNros[idcomnum[3]]);
                            com5.setImageResource(ImgNros[idcomnum[4]]);
                            jgc1.setImageResource(ImgNros[jg1id[0]]);
                            jgc2.setImageResource(ImgNros[jg1id[1]]);
                            r = TercerProcesoCarCom(result4);
                            if (r > 0) {
                                CuartoProcesoNombJug (result4);
                            }
                            r = TercerProcesoCarCom(result5);
                            if (r > 0) {
                                CuartoProcesoPuestoJug (result5);
                            }
                            //us1.setText("junior");
                            switch (OrdenJug) {
                                case 1:
                                    us9.setText(NomJug[1]);
                                    us8.setText(NomJug[2]);
                                    us7.setText(NomJug[3]);
                                    us6.setText(NomJug[4]);
                                    us5.setText(NomJug[5]);
                                    us4.setText(NomJug[6]);
                                    us3.setText(NomJug[7]);
                                    us2.setText(NomJug[8]);
                                    break;
                                case 2:
                                    us9.setText(NomJug[2]);
                                    us8.setText(NomJug[3]);
                                    us7.setText(NomJug[4]);
                                    us6.setText(NomJug[5]);
                                    us5.setText(NomJug[6]);
                                    us4.setText(NomJug[7]);
                                    us3.setText(NomJug[8]);
                                    us2.setText(NomJug[0]);
                                    break;
                                case 3:
                                    us9.setText(NomJug[3]);
                                    us8.setText(NomJug[4]);
                                    us7.setText(NomJug[5]);
                                    us6.setText(NomJug[6]);
                                    us5.setText(NomJug[7]);
                                    us4.setText(NomJug[8]);
                                    us3.setText(NomJug[0]);
                                    us2.setText(NomJug[1]);
                                    break;
                                case 4:
                                    us9.setText(NomJug[4]);
                                    us8.setText(NomJug[5]);
                                    us7.setText(NomJug[6]);
                                    us6.setText(NomJug[7]);
                                    us5.setText(NomJug[8]);
                                    us4.setText(NomJug[0]);
                                    us3.setText(NomJug[1]);
                                    us2.setText(NomJug[2]);
                                    break;
                                case 5:
                                    us9.setText(NomJug[5]);
                                    us8.setText(NomJug[6]);
                                    us7.setText(NomJug[7]);
                                    us6.setText(NomJug[8]);
                                    us5.setText(NomJug[0]);
                                    us4.setText(NomJug[1]);
                                    us3.setText(NomJug[2]);
                                    us2.setText(NomJug[3]);
                                    break;
                                case 6:
                                    us9.setText(NomJug[6]);
                                    us8.setText(NomJug[7]);
                                    us7.setText(NomJug[8]);
                                    us6.setText(NomJug[0]);
                                    us5.setText(NomJug[1]);
                                    us4.setText(NomJug[2]);
                                    us3.setText(NomJug[3]);
                                    us2.setText(NomJug[4]);
                                    break;
                                case 7:
                                    us9.setText(NomJug[7]);
                                    us8.setText(NomJug[8]);
                                    us7.setText(NomJug[0]);
                                    us6.setText(NomJug[1]);
                                    us5.setText(NomJug[2]);
                                    us4.setText(NomJug[3]);
                                    us3.setText(NomJug[4]);
                                    us2.setText(NomJug[5]);
                                    break;
                                case 8:
                                    us9.setText(NomJug[8]);
                                    us8.setText(NomJug[0]);
                                    us7.setText(NomJug[1]);
                                    us6.setText(NomJug[2]);
                                    us5.setText(NomJug[3]);
                                    us4.setText(NomJug[4]);
                                    us3.setText(NomJug[5]);
                                    us2.setText(NomJug[6]);
                                    break;
                                case 9:
                                    us9.setText(NomJug[0]);
                                    us8.setText(NomJug[1]);
                                    us7.setText(NomJug[2]);
                                    us6.setText(NomJug[3]);
                                    us5.setText(NomJug[4]);
                                    us4.setText(NomJug[5]);
                                    us3.setText(NomJug[6]);
                                    us2.setText(NomJug[7]);
                                    break;
                            }
                        }
                    });
                }
                catch (Exception e) {
                    Log.i("depurar",e.toString());
                }
            }
        };
        tr1.start();
    }

    public String SegundoProcesoCartComun (String consulta) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(consulta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            Log.i("depurar",e.toString());
        }
        return result.toString();
    }

    public int TercerProcesoCarCom (String responses) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(responses);
            if (json.length() > 0) {
                res = 1;
            }
        } catch (Exception e) {
            Log.i("depurar",e.toString());
        }
        return res;
    }

    public void CuartoProcesoCartComun(String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                idcomnum[i] = json.getJSONObject(i).getInt("id_carta");
            }
        } catch (JSONException e) {
            Log.i("depurar",e.toString());
        }
    }
    
    public void EntAImg() {
        ImgNros[0] = R.drawable.c101;
        ImgNros[1] = R.drawable.c102;
        ImgNros[2] = R.drawable.c103;
        ImgNros[3] = R.drawable.c104;
        ImgNros[4] = R.drawable.c105;
        ImgNros[5] = R.drawable.c106;
        ImgNros[6] = R.drawable.c107;
        ImgNros[7] = R.drawable.c108;
        ImgNros[8] = R.drawable.c109;
        ImgNros[9] = R.drawable.c110;
        ImgNros[10] = R.drawable.c111;
        ImgNros[11] = R.drawable.c112;
        ImgNros[12] = R.drawable.c113;
        ImgNros[13] = R.drawable.c201;
        ImgNros[14] = R.drawable.c202;
        ImgNros[15] = R.drawable.c203;
        ImgNros[16] = R.drawable.c204;
        ImgNros[17] = R.drawable.c205;
        ImgNros[18] = R.drawable.c206;
        ImgNros[19] = R.drawable.c207;
        ImgNros[20] = R.drawable.c208;
        ImgNros[21] = R.drawable.c209;
        ImgNros[22] = R.drawable.c210;
        ImgNros[23] = R.drawable.c211;
        ImgNros[24] = R.drawable.c212;
        ImgNros[25] = R.drawable.c213;
        ImgNros[26] = R.drawable.c301;
        ImgNros[27] = R.drawable.c302;
        ImgNros[28] = R.drawable.c303;
        ImgNros[29] = R.drawable.c304;
        ImgNros[30] = R.drawable.c305;
        ImgNros[31] = R.drawable.c306;
        ImgNros[32] = R.drawable.c307;
        ImgNros[33] = R.drawable.c308;
        ImgNros[34] = R.drawable.c309;
        ImgNros[35] = R.drawable.c310;
        ImgNros[36] = R.drawable.c311;
        ImgNros[37] = R.drawable.c312;
        ImgNros[38] = R.drawable.c313;
        ImgNros[39] = R.drawable.c401;
        ImgNros[40] = R.drawable.c402;
        ImgNros[41] = R.drawable.c403;
        ImgNros[42] = R.drawable.c404;
        ImgNros[43] = R.drawable.c405;
        ImgNros[44] = R.drawable.c406;
        ImgNros[45] = R.drawable.c407;
        ImgNros[46] = R.drawable.c408;
        ImgNros[47] = R.drawable.c409;
        ImgNros[48] = R.drawable.c410;
        ImgNros[49] = R.drawable.c411;
        ImgNros[50] = R.drawable.c412;
        ImgNros[51] = R.drawable.c413;
    }

    public void CuartoProcesoTipo (String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                tipo_mesa = json.getJSONObject(i).getInt("CANT_TIPO");
            }
        } catch (JSONException e) {
            Log.i("depurar",e.toString());
        }
    }

    public void CuartoProcesoCartJug(String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                jg1id[i] = json.getJSONObject(i).getInt("id_carta");
            }
        } catch (JSONException e) {
            Log.i("depurar",e.toString());
        }
    }

    public void CuartoProcesoNombJug (String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                NomJug[i] = json.getJSONObject(i).getString("usuario");
                PuestJug[i] = json.getJSONObject(i).getInt("orden");
            }
            Log.i("depurar", json.toString());
        } catch (JSONException e) {
            Log.i("depurar", e.toString());
        }
    }

    public void CuartoProcesoPuestoJug(String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                OrdenJug = json.getJSONObject(i).getInt("orden");
            }
        } catch (JSONException e) {
            Log.i("depurar", e.toString());
        }
    }

    public void Prueba (View view) {
        reloj = new Timer();
        x = 0;
        barra = (ProgressBar) findViewById(R.id.progressBar);
        cron = (TextView) findViewById(R.id.Cronometro);
        barra.setMax(15);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        x++;
                        Log.i("depurar",Integer.toString(x));
                        usuario1 = (LinearLayout) findViewById(R.id.cartas_1);
                        img2 = (ImageView) findViewById(R.id.imvUsuario2);
                        img3 = (ImageView) findViewById(R.id.imvUsuario3);
                        img4 = (ImageView) findViewById(R.id.imvUsuario4);
                        img5 = (ImageView) findViewById(R.id.imvUsuario5);
                        img6 = (ImageView) findViewById(R.id.imvUsuario6);
                        img7 = (ImageView) findViewById(R.id.imvUsuario7);
                        img8 = (ImageView) findViewById(R.id.imvUsuario8);
                        img9 = (ImageView) findViewById(R.id.imvUsuario9);
                        y++;
                        if (OrdenJug == 1) {
                            if (x > 1 && x < 16) {
                                barra.setProgress(y);
                                cron.setText(Integer.toString(y));
                            } else {
                                cron.setText("");
                                barra.setProgress(0);
                            }
                        }
                        switch (OrdenJug) {
                            case 1:
                                switch (x) {
                                    case 2 :
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 2:
                                switch (x) {
                                    case 2 :
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 3:
                                switch (x) {
                                    case 2:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 4:
                                switch (x) {
                                    case 2:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 5:
                                switch (x) {
                                    case 2 :
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 6:
                                switch (x) {
                                    case 2:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                                break;
                            case 7 :
                                switch (x) {
                                    case 2:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                            case 8:
                                switch (x) {
                                    case 2 :
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                            case 9:
                                switch (x) {
                                    case 2:
                                        usuario1.setBackgroundResource(R.color.transparente);
                                        img9.setBackgroundResource(R.color.verde);
                                        break;
                                    case 15:
                                        img9.setBackgroundResource(R.color.transparente);
                                        img8.setBackgroundResource(R.color.verde);
                                        break;
                                    case 30:
                                        img8.setBackgroundResource(R.color.transparente);
                                        img7.setBackgroundResource(R.color.verde);
                                        break;
                                    case 45:
                                        img7.setBackgroundResource(R.color.transparente);
                                        img6.setBackgroundResource(R.color.verde);
                                        break;
                                    case 60:
                                        img6.setBackgroundResource(R.color.transparente);
                                        img5.setBackgroundResource(R.color.verde);
                                        y = 0;
                                        break;
                                    case 75:
                                        img5.setBackgroundResource(R.color.transparente);
                                        img4.setBackgroundResource(R.color.verde);
                                        break;
                                    case 90:
                                        img4.setBackgroundResource(R.color.transparente);
                                        img3.setBackgroundResource(R.color.verde);
                                        break;
                                    case 105:
                                        img3.setBackgroundResource(R.color.transparente);
                                        img2.setBackgroundResource(R.color.verde);
                                        break;
                                    case 120:
                                        img2.setBackgroundResource(R.color.transparente);
                                        usuario1.setBackgroundResource(R.color.verde);
                                        break;
                                    case 135:
                                        x = 0;
                                        y = 0;
                                        break;
                                }
                        }
                    }
                });
            }
        };
        reloj.schedule(tarea,0,1000);
    }

    public void Straddle(View view) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarApuesta(Acceder.id_usuario, String.valueOf(Integer.parseInt(recup_ciega_maxima) * 2), "1");
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosApuesta(result);
                        if (r > 0) {
                            MostrarStraddle();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarApuesta(String usuario_mano, String cantidad_fichas, String ronda) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/apostar.php?usuario_mano=" + usuario_mano + "&cantidad_fichas=" +
                    cantidad_fichas + "&ronda=" + ronda);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int obtDatosApuesta(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void MostrarStraddle() {
        Button btnStraddle, btnMisisipi;
        btnStraddle = (Button) findViewById(R.id.btnStraddle);
        btnMisisipi = (Button) findViewById(R.id.btnMisisipi);
        btnStraddle.setVisibility(View.GONE);
        btnMisisipi.setVisibility(View.GONE);
    }

    public void Misisipi(View view) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarApuesta(Acceder.id_usuario, String.valueOf(Integer.parseInt(recup_ciega_maxima) * 3), "1");
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosApuesta(result);
                        if (r > 0) {
                            MostrarMisisipi();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void MostrarMisisipi() {
        Button btnStraddle, btnMisisipi;
        btnStraddle = (Button) findViewById(R.id.btnStraddle);
        btnMisisipi = (Button) findViewById(R.id.btnMisisipi);
        btnStraddle.setVisibility(View.GONE);
        btnMisisipi.setVisibility(View.GONE);
    }

    public String CrearMano(String id_mesa) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registrar_mano.php?id_mesa=" + id_mesa);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int ObtenerMano(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public String CrearUsuarioMano(String orden) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://gosmart.pe/poker/registrar_id_usuario_mano.php?id_usuario=" + id_usuario + "&id_mano=" + id_mano +
                    "&orden=" + orden);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result.toString();
    }

    public int ObtUsuarioMano (String response) {
        int res = 0;
        try {
            if (!response.equals("")) {
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void Posicion1(View view) {
        //PRIMER PASO CREAR MANO
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("1");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1Juego9);
                                                        btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego9);
                                                        cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1Juego9);
                                                        cajaUser1.setVisibility(View.VISIBLE);
                                                        btnuser1.setVisibility(View.INVISIBLE);
                                                        cajaPerfilUser1.setVisibility(View.VISIBLE);
                                                        us1.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion2(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego9);
        usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("2");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser2.setVisibility(View.INVISIBLE);
                                                        usuario2.setVisibility(View.VISIBLE);
                                                        us2.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion3(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego9);
        usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("3");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser3.setVisibility(View.INVISIBLE);
                                                        usuario3.setVisibility(View.VISIBLE);
                                                        us3.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion4(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser4 = (LinearLayout) findViewById(R.id.linPosicion4Juego9);
        usuario4 = (LinearLayout) findViewById(R.id.lilUsuario4Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("4");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser4.setVisibility(View.INVISIBLE);
                                                        usuario4.setVisibility(View.VISIBLE);
                                                        us4.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion5(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser5 = (LinearLayout) findViewById(R.id.linPosicion5Juego9);
        usuario5 = (LinearLayout) findViewById(R.id.lilUsuario5Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("5");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser5.setVisibility(View.INVISIBLE);
                                                        usuario5.setVisibility(View.VISIBLE);
                                                        us5.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion6(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser6 = (LinearLayout) findViewById(R.id.linPosicion6Juego9);
        usuario6 = (LinearLayout) findViewById(R.id.lilUsuario6Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("6");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser6.setVisibility(View.INVISIBLE);
                                                        usuario6.setVisibility(View.VISIBLE);
                                                        us6.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion7(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser7 = (LinearLayout) findViewById(R.id.linPosicion7Juego9);
        usuario7 = (LinearLayout) findViewById(R.id.lilUsuario7Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("7");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser7.setVisibility(View.INVISIBLE);
                                                        usuario7.setVisibility(View.VISIBLE);
                                                        us7.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion8(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser8 = (LinearLayout) findViewById(R.id.linPosicion8Juego9);
        usuario8 = (LinearLayout) findViewById(R.id.lilUsuario8Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("8");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser8.setVisibility(View.INVISIBLE);
                                                        usuario8.setVisibility(View.VISIBLE);
                                                        us8.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void Posicion9(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser9 = (LinearLayout) findViewById(R.id.linPosicion9Juego9);
        usuario9 = (LinearLayout) findViewById(R.id.lilUsuario9Juego9);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result1 = CrearMano(recup_id_mesa);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = ObtenerMano(result1);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result1);
                                if (json.length() > 0) {
                                    id_mano = json.getJSONObject(0).getString("id_mano");
                                    //SEGUNDO PASO CREAR USUARIO MANO
                                    Thread tr2 = new Thread() {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("9");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        btnuser9.setVisibility(View.INVISIBLE);
                                                        usuario9.setVisibility(View.VISIBLE);
                                                        us9.setText(Acceder.nom_usuario);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    tr2.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void cerrarMesa(View view) {
        Log.i("cerrarMesa", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = cerrarMesa2(tvId_mesa.getText().toString());
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = cerrarMesa3(result);
                        if (r > 0) {
                            cerrarMesa4();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String cerrarMesa2(String id_mesa) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/cerrar_mesa.php?id_mesa=" + id_mesa);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int cerrarMesa3(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void cerrarMesa4() {
        String id_club = tvClub.getText().toString();
        tvClub.setText("");
        tvClub.setText(id_club);
        finish();
    }
}

package com.poker;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.Http;
import com.poker.adapters.ApuestasAdapter;
import com.poker.adapters.MesasActivasAdapter;
import com.poker.adapters.MesasAdapter;
import com.poker.pojos.ClsApuesta;
import com.poker.pojos.ClsMesa;
import com.poker.pojos.ClsMesasActivas;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
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

import static com.poker.Acceder.fichas;
import static com.poker.Acceder.id_usuario;
import static com.poker.CrearMesa.id_mesa;
import static com.poker.Mesas.tvClub;
import static com.poker.Principal.codigo_de_usuario;

public class Juego3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerApuestas, recyclermesasactivas;
    ArrayList<ClsApuesta> apuestas;
    ArrayList<ClsMesasActivas> mesasActivas;
    ApuestasAdapter apuestasAdapter;
    MesasActivasAdapter mesasActivasAdapter;
    AutoCompleteTextView txtMonto;
    int monto;
    TextInputEditText mesa;
    ImageView com1, com2, com3, com4, com5, jgc1, jgc2;
    int[] ImgNros = new int[53];
    LinearLayout cajaUser1, usuario1, usuario2, usuario3, mesa_fondo,btnuser1, btnuser2, btnuser3, fondo, linStraddleMisisipi;
    TextView us1, us2, us3, cron, tvId_mesa, tvCiegaMinima, tvCiegaMaxima, minimo;
    ConstraintLayout cajaPerfilUser1;
    ImageView img1, img2, img3, img4, img5, img6, carta_animada;
    public static ProgressBar barra;
    public static int tipo_mesa = 0;
    public static String[] IdJug = new String[3];
    public static int OrdenJug = 0;
    public static String[] NomJug = new String[9];
    public static int[] PuestJug = new int[9];
    public static int[] jg1num = new int[4];
    public static String[] jg1palo = new String[4];
    public static int[] jg1id = new int[4];
    public static int[] jg2num = new int[4];
    public static String[] jg2palo = new String[4];
    public static int[] jg3num = new int[4];
    public static String[] jg3palo = new String[4];
    public static int[] jg4num = new int[4];
    public static String[] jg4palo = new String[4];
    public static int[] jg5num = new int[4];
    public static String[] jg5palo = new String[4];
    public static int[] jg6num = new int[4];
    public static String[] jg6palo = new String[4];
    public static int[] jg7num = new int[4];
    public static String[] jg7palo = new String[4];
    public static int[] jg8num = new int[4];
    public static String[] jg8palo = new String[4];
    public static int[] jg9num = new int[4];
    public static String[] jg9palo = new String[4];
    public static int[] idcomnum = new int[5];
    public static int[] comnum = new int[5];
    public static String[] compalo = new String[5];
    public static String[] rpts = new String[10];
    public static ArrayList<String> idjug = new ArrayList<>();
    static int x = 0;
    int y = 0;
    public static Timer reloj;
    public static String recup_ciega_minima, recup_ciega_maxima, recup_id_mesa, id_mano;
    int anchopantalla, altopantalla;
    SeekBar barApuesta;
    Button btnRaise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juego3);

        mesa_fondo = (LinearLayout) findViewById(R.id.imvMesa);
        carta_animada = (ImageView) findViewById(R.id.imvCartaAnimada);
        cron = (TextView) findViewById(R.id.Cronometro);

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
        recyclermesasactivas = (RecyclerView) findViewById(R.id.recycler_mesas_activas);
        LinearLayoutManager linearLayoutManagerMesasActivas = new LinearLayoutManager(this);
        linearLayoutManagerMesasActivas.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclermesasactivas.setLayoutManager(linearLayoutManagerMesasActivas);
        mesasActivasAdapter = new MesasActivasAdapter(this, mesasActivas);
        recyclermesasactivas.setAdapter(mesasActivasAdapter);

        cargarMesasActivas(id_usuario);

        initPantalla();

        cron = (TextView) findViewById(R.id.Cronometro);
        //Temporizar();

        Verificar();

        minimo = (TextView) findViewById(R.id.minimo);
        minimo.setText("2");

        barApuesta = (SeekBar) findViewById(R.id.barApuesta);
        btnRaise = (Button) findViewById(R.id.btnRaise);
        barApuesta.setProgress(0);
        barApuesta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    barApuesta.setMin(Integer.parseInt(minimo.getText().toString()));
                    barApuesta.setMax(Integer.parseInt(fichas));
                }
                String valor = String.valueOf(barApuesta.getProgress());
                btnRaise.setText(valor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void cargarMesasActivas(final String id_usuario) {
        Log.i("depurar", "click");
        mesasActivas = new ArrayList<>();
        mesasActivas.add(new ClsMesasActivas("", "", "", "", "", "", ""));
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarMesasActivas(id_usuario);
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtMesasActivas(result);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result);
                                for (int i = 0; i < json.length(); i++) {
                                    mesasActivas.add(new ClsMesasActivas(json.getJSONObject(i).getString("id_mesa"),
                                            json.getJSONObject(i).getString("entrada_minima"),
                                            json.getJSONObject(i).getString("modalidad"),
                                            json.getJSONObject(i).getString("ciega_minima"),
                                            json.getJSONObject(i).getString("ciega_maxima"),
                                            json.getJSONObject(i).getString("usuarios_activos"),
                                            json.getJSONObject(i).getString("cantidad_usuarios")));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mesasActivasAdapter = new MesasActivasAdapter(Juego3.this, mesasActivas);
                        recyclermesasactivas.setAdapter(mesasActivasAdapter);
                    }
                });
            }
        };
        tr.start();
    }

    public final String enviarMesasActivas(String id_usuario) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://gosmart.pe/poker/buscar_mesas_activas.php?id_usuario=" + id_usuario);
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

    public int obtMesasActivas(String response) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void initPantalla() {
        //Obtener el tamaño de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchopantalla = size.x;
        altopantalla = size.y;
    }

    public void Verificar() {
        us1 = (TextView) findViewById(R.id.tvNombre1);
        btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego3);
        usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2);
        us2 = (TextView) findViewById(R.id.tvNombre2);
        btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego3);
        usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3);
        us3 = (TextView) findViewById(R.id.tvNombre3);
        cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1);
        btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego3);
        cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1);
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        reloj = new Timer();
        x = 0;
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Thread tr1 = new Thread() {
                    @Override
                    public void run() {
                        final String result1=PriProcVerificar("http://gosmart.pe/poker/Obtener_Jug_Mesa.php?id_mesa="+recup_id_mesa);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r = SegProcVerificar(result1);
                                if (r > 0) {
                                    try {
                                        JSONArray json = new JSONArray(result1);
                                        if (json.length() > 0) {
                                            TercProcVerificar(result1);
                                            for (int i = 0; i < 2; i++) {
                                                if (IdJug[i].equals(id_usuario)) {
                                                    btnuser1.setVisibility(View.INVISIBLE);
                                                    btnuser2.setVisibility(View.INVISIBLE);
                                                    btnuser3.setVisibility(View.INVISIBLE);
                                                    switch (PuestJug[i]) {
                                                        case 1:
                                                            us1.setText(NomJug[i]);
                                                            us2.setText(NomJug[i + 1]);
                                                            us3.setText(NomJug[i + 2]);
                                                            break;
                                                        case 2:
                                                            us1.setText(NomJug[i]);
                                                            us2.setText(NomJug[i + 1]);
                                                            us3.setText(NomJug[i - 1]);
                                                            break;
                                                        case 3:
                                                            us1.setText(NomJug[i]);
                                                            us2.setText(NomJug[i - 1]);
                                                            us3.setText(NomJug[i + 1]);
                                                            break;
                                                    }
                                                }else {
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
                                                        }
                                                    }
                                                }
                                            }
                                            if (!us1.getText().toString().equals("")) {
                                                cajaUser1.setVisibility(View.VISIBLE);
                                                btnuser1.setVisibility(View.INVISIBLE);
                                                cajaPerfilUser1.setVisibility(View.VISIBLE);
                                            }
                                            if (!us2.getText().toString().equals("")) {
                                                btnuser2.setVisibility(View.INVISIBLE);
                                                usuario2.setVisibility(View.VISIBLE);
                                            }
                                            if (!us3.getText().toString().equals("")) {
                                                btnuser3.setVisibility(View.INVISIBLE);
                                                usuario3.setVisibility(View.VISIBLE);
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
            us1 = (TextView) findViewById(R.id.tvNombre1);
            btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego3);
            usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2);
            us2 = (TextView) findViewById(R.id.tvNombre2);
            btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego3);
            usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3);
            us3 = (TextView) findViewById(R.id.tvNombre3);
            cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1);
            btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego3);
            cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1);
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                IdJug[i] = json.getJSONObject(i).getString("id_usuario");
                NomJug[i] = json.getJSONObject(i).getString("usuario");
                PuestJug[i] = json.getJSONObject(i).getInt("orden");
            }
            //Log.i("depurar",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    private void PrimerProcesoxLlenar() {
        Thread tr1 = new Thread() {
            @Override
            public void run() {
                try {
                    final String result1 = SegundoProcesoCartComun("http://gosmart.pe/poker/obtener_tipo_moda_mesa.php?id_mesa="
                            + id_mesa);
                    final String result2 = SegundoProcesoCartComun("http://gosmart.pe/poker/Consultar_Com.php?id_mesa=" + id_mesa);
                    final String result3 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Cart_Jug.php?id_mesa=" + id_mesa
                            + "&id_usuario=" + id_usuario);
                    final String result4 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Jug_Mesa.php?id_mesa=" + id_mesa);
                    final String result5 = SegundoProcesoCartComun("http://gosmart.pe/poker/Obtener_Orden.php?id_usuario="
                            + id_usuario +"&id_mesa=" + id_mesa);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //ID_JUG
                            EntAImg();
                            com1 = (ImageView) findViewById(R.id.imvComunitaria1);
                            com2 = (ImageView) findViewById(R.id.imvComunitaria2);
                            com3 = (ImageView) findViewById(R.id.imvComunitaria3);
                            com4 = (ImageView) findViewById(R.id.imvComunitaria4);
                            com5 = (ImageView) findViewById(R.id.imvComunitaria5);
                            jgc1 = (ImageView) findViewById(R.id.imvUsuario1C1);
                            jgc2 = (ImageView) findViewById(R.id.imvUsuario1C2);
                            us1 = (TextView) findViewById(R.id.tvNombre1);
                            us2 = (TextView) findViewById(R.id.tvNombre2);
                            us3 = (TextView) findViewById(R.id.tvNombre3);
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
                            jgc1.setImageResource(ImgNros[jg1id[0]]);
                            jgc2.setImageResource(ImgNros[jg1id[1]]);
                            r = TercerProcesoCarCom(result4);
                            if (r > 0) {
                                CuartoProcesoNombJug(result4);
                            }
                            r = TercerProcesoCarCom(result5);
                            if (r > 0) {
                                CuartoProcesoPuestoJug(result5);
                            }
                            us1.setText(Acceder.nom_usuario);
                            switch (OrdenJug) {
                                case 1:
                                    us2.setText(NomJug[1]);
                                    us3.setText(NomJug[2]);
                                    break;
                                case 2:
                                    us2.setText(NomJug[3]);
                                    us3.setText(NomJug[1]);
                                    break;
                                case 3:
                                    us2.setText(NomJug[0]);
                                    us3.setText(NomJug[1]);
                                    break;
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.i("depurar", e.toString());
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
                idcomnum[i] =json.getJSONObject(i).getInt("id_carta");
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
                tipo_mesa=json.getJSONObject(i).getInt("CANT_TIPO");
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
            Log.i("depurar",json.toString());
        } catch (JSONException e) {
            Log.i("depurar",e.toString());
        }
    }

    public void CuartoProcesoPuestoJug(String responses) {
        try {
            JSONArray json = new JSONArray(responses);
            for (int i = 0; i < json.length(); i++) {
                OrdenJug = json.getJSONObject(i).getInt("orden");
            }
        } catch (JSONException e) {
            Log.i("depurar",e.toString());
        }
    }

    public void Straddle(View view) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarApuesta(id_usuario, String.valueOf(Integer.parseInt(recup_ciega_maxima) * 2), "1");
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
        btnStraddle.setVisibility(View.INVISIBLE);
        btnMisisipi.setVisibility(View.INVISIBLE);
    }

    public void Misisipi(View view) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarApuesta(id_usuario, String.valueOf(Integer.parseInt(recup_ciega_maxima) * 3), "1");
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
        btnStraddle.setVisibility(View.INVISIBLE);
        btnMisisipi.setVisibility(View.INVISIBLE);
    }

    public void Temporizar() {
        new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                long segundos_restantes = millisUntilFinished / 1000;
                cron.setText(segundos_restantes + "");
            }

            public void onFinish() {
                cron.setText("15");
            }
        }.start();
    }

    public void Posicion1(View view) {
        //PRIMER PASO CREAR MANO
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
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
                                    Thread tr2 = new Thread()
                                    {
                                        @Override
                                        public void run() {
                                            final String result2 = CrearUsuarioMano("1");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int r2 = ObtUsuarioMano(result2);
                                                    if (r2 > 0) {
                                                        cajaUser1 = (LinearLayout) findViewById(R.id.CajaUss1);
                                                        btnuser1 = (LinearLayout) findViewById(R.id.linPosicion1Juego3);
                                                        cajaPerfilUser1 = (ConstraintLayout) findViewById(R.id.CajaPerfil1);
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
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        tr.start();
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

    public void Posicion3(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser3 = (LinearLayout) findViewById(R.id.linPosicion3Juego3);
        usuario3 = (LinearLayout) findViewById(R.id.lilUsuario3);
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
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
                                    Thread tr2 = new Thread()
                                    {
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

    public void Posicion2(View view) {
        recup_id_mesa = getIntent().getStringExtra("id_mesa_card");
        btnuser2 = (LinearLayout) findViewById(R.id.linPosicion2Juego3);
        usuario2 = (LinearLayout) findViewById(R.id.lilUsuario2);
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

    public String  IngreCom (int [] nros) {
        StringBuilder result = null;
        for (int i = 1; i < 6; i++) {
            URL url = null;
            String linea = "";
            int respuesta = 0;
            try {
                url = new URL("http://gosmart.pe/poker/insertar_carta_comun.php?id_mesa=" + id_mesa + "&id_carta=" + nros[i]);
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
        }
        return result.toString();
    }

    public int[] NroRandom() {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT);
        int[] nrosrandom = new int [53];
        for (int i = 1; i < 53; i++) {
            nrosrandom[i]=rng.nextInt(52);
            if (i>=2) {
                for (int x =1 ; x<i ; x++) {
                    if (nrosrandom[x]==nrosrandom[i]) {
                        i--;
                    }
                }
            }

        }
        return nrosrandom;
    }

    public String IngresarCartxJug(int nros[],int pos) {
        StringBuilder result = null;
        URL url = null;
        String linea = "";
        int respuesta = 0;
        switch (pos) {
            case 1:
                for (int i =6 ; i<8 ; i++) {
                    try {
                        url = new URL("http://gosmart.pe/poker/insertar_carta_jug.php?id_usuario_mano="+id_mano + "&id_carta=" + nros[i]);
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
                }
                break;
            case 2:
                for (int i =8 ; i<10 ; i++)
                {

                    try {
                        url = new URL("http://gosmart.pe/poker/insertar_carta_jug.php?id_usuario_mano="+id_mano + "&id_carta=" + nros[i]);
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
                }
                break;
            case 3:
                for (int i =10 ; i<12 ; i++) {
                    try {
                        url = new URL("http://gosmart.pe/poker/insertar_carta_jug.php?id_usuario_mano="+id_mano + "&id_carta=" + nros[i]);
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
                }
                break;
        }
        return result.toString();
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

    public void Raise(View view) {
        btnRaise = (Button) findViewById(R.id.btnRaise);
        barApuesta = (SeekBar) findViewById(R.id.barApuesta);
        Log.i("depurar", "click");
        Thread tr2 = new Thread() {
            @Override
            public void run() {
                String fichas_cant = btnRaise.getText().toString();
                String codigo = id_usuario;
                Log.i("codigo", id_usuario);
                final String result2 = enviarFichas(codigo, fichas_cant);
                Log.i("enviarFichas", result2);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosFichas(result2);
                        if (r > 0) {
                            mostrarFichas();
                        }
                    }
                });
            }
        };
        tr2.start();
    }

    public void mostrarApuesta() {
        btnRaise = (Button) findViewById(R.id.btnRaise);
        barApuesta = (SeekBar) findViewById(R.id.barApuesta);
        cron = (TextView) findViewById(R.id.Cronometro);
        linStraddleMisisipi = (LinearLayout) findViewById(R.id.linStraddleMisisipi);
        btnRaise.setVisibility(View.INVISIBLE);
        barApuesta.setVisibility(View.INVISIBLE);
        cron.setVisibility(View.INVISIBLE);
        linStraddleMisisipi.setVisibility(View.INVISIBLE);
    }

    public String enviarFichas(String id_usuario, String  cantidad_de_fichas) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://gosmart.pe/poker/fichas_jugador.php?id_usuario=" + id_usuario + "&fichas=" + cantidad_de_fichas);
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

    public int obtDatosFichas(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void mostrarFichas() {
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarApuesta(id_usuario, btnRaise.getText().toString(), "1");
                Log.i("enviarApuesta", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosApuesta(result);
                        if (r > 0) {
                            mostrarApuesta();
                        }
                    }
                });
            }
        };
        tr.start();
    }
}

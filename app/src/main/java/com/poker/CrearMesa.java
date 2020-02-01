package com.poker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.poker.ui.main.SectionsPagerAdapter;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.poker.adapters.ClubesAdapter.id_clubes;

public class CrearMesa extends AppCompatActivity {
    private TextView tvCiegaMinima, tvCiegaMaxima, tvCiegasArriba, tvFichasIniciales, tvCiegasArriba2, tvFichasIniciales2, tvRegistroT, tvJugadasMin, tvJugadasMax;
    private RadioButton rbMesa3, rbMesa6, rbMesa9, rbAccion15s, rbAccion20s, rbAccion30s, rbComienzo2, rbComienzo3, rbComienzo4, rbComienzo5, rbCall15, rbCall20, rbCall30;
    private Switch sComiezoA, sExtensionA, sCallTime, sAutorizadoEntrar;
    private Spinner sp_veces, sp_rake, sp_tiempoMesa, spEntradaMinima, spEntradaMaxima;
    private CheckBox chkMultiveces;
    private EditText etNombreMesaCash, etEntradaMinima, etEntradaMaxima;

    public static String id_mesa;
    String ciegaMinima = "", ciegaMaxima = "", entradaMin = "", entradaMax = "", nombreM = "", mesa2 = "", mesa6 = "", mesa9 = "",
            accion15 = "", accion20 = "", accion30 = "", comienzoAutomatico = "", comienzo2 = "", comienzo3 = "", comienzo4 = "",
            comienzo5 = "", extensionAutomatica = "", veces = "", multiVeces = "", rake = "", callTime = "", call15 = "", call20 = "",
            call30 = "", autorizadoEntrar = "", tiempoMesa = "", id_club = "";
    String[] ciega_minima = new String[] {"0.10", "0.20", "0.30", "0.40", "0.50", "1", "2", "3", "4", "5", "10", "15", "25", "50", "100",
            "250", "500"};
    String[] ciega_maxima = new String[] {"0.20", "0.40", "0.60", "0.80", "1", "2", "4", "6", "8", "10", "20", "30", "50", "100", "200",
            "500", "1000"};
    String[] entradas01, entradas02, entradas03, entradas04, entradas05, entradas06, entradas07, entradas08, entradas09, entradas10,
            entradas11, entradas12, entradas13, entradas14, entradas15, entradas16, entradas17;
    List<String> lista;
    ArrayAdapter<String> adapter;
    String numero;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mesa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        tvCiegaMinima = (TextView) findViewById(R.id.tvCiegaMinima);
        tvCiegaMaxima = (TextView) findViewById(R.id.tvCiegaMaxima);
        etEntradaMinima = (EditText) findViewById(R.id.etEntradaMinima);
        etEntradaMaxima = (EditText) findViewById(R.id.etEntradaMaxima);
        tvCiegasArriba = (TextView) findViewById(R.id.tvCiegasA);
        tvFichasIniciales = (TextView) findViewById(R.id.tvFIniciales);
        tvCiegasArriba2 = (TextView) findViewById(R.id.tvCiegasA2);
        tvFichasIniciales2 = (TextView) findViewById(R.id.tvFIniciales2);
        tvRegistroT = (TextView) findViewById(R.id.tvRegistroT);
        tvJugadasMin = (TextView) findViewById(R.id.tvJugadasMin);
        tvJugadasMax = (TextView) findViewById(R.id.tvJugadasMax);
        rbMesa3 = (RadioButton) findViewById(R.id.rbMesa3);
        rbMesa6 = (RadioButton) findViewById(R.id.rbMesa6);
        rbMesa9 = (RadioButton) findViewById(R.id.rbMesa9);
        rbAccion15s = (RadioButton) findViewById(R.id.rbAccion15s);
        rbAccion20s = (RadioButton) findViewById(R.id.rbAccion20s);
        rbAccion30s = (RadioButton) findViewById(R.id.rbAccion30s);
        rbComienzo2 = (RadioButton) findViewById(R.id.rbComienzo2);
        rbComienzo3 = (RadioButton) findViewById(R.id.rbComienzo3);
        rbComienzo4 = (RadioButton) findViewById(R.id.rbComienzo4);
        rbComienzo5 = (RadioButton) findViewById(R.id.rbComienzo5);
        rbCall15 = (RadioButton) findViewById(R.id.rbCall15);
        rbCall20 = (RadioButton) findViewById(R.id.rbCall20);
        rbCall30 = (RadioButton) findViewById(R.id.rbCall30);
        sComiezoA = (Switch) findViewById(R.id.sComienzoA);
        sExtensionA = (Switch) findViewById(R.id.sExtensionA);
        sAutorizadoEntrar = (Switch)findViewById(R.id.sAutParaEntrar);
        sCallTime = (Switch) findViewById(R.id.sCallTime);
        sp_veces = (Spinner) findViewById(R.id.sp_veces);
        sp_rake = (Spinner) findViewById(R.id.sp_rake);
        sp_tiempoMesa = (Spinner) findViewById(R.id.sp_tiempoMesa);
        chkMultiveces = (CheckBox) findViewById(R.id.chkMultiveces);
        etNombreMesaCash = (EditText) findViewById(R.id.etNombreMesaCash);

        //id_club = getIntent().getStringExtra("id_club");

        lista = new ArrayList<>();

        /*entradas01 = new String[] {"4", "8", "10", "12", "16", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        entradas02 = new String[] {"8", "16", "20", "24", "32", "40", "60", "80", "100", "120", "140", "160", "180", "200"};
        entradas03 = new String[] {"12", "24", "30", "36", "48", "60", "90", "120", "150", "180", "210", "240", "270", "300"};
        entradas04 = new String[] {"16", "32", "40", "48", "64", "80", "120", "160", "200", "240", "280", "320", "360", "400"};
        entradas05 = new String[] {"20", "40", "50", "60", "80", "100", "150", "200", "250", "300", "350", "400", "450", "500"};
        entradas06 = new String[] {"40", "80", "100", "120", "160", "200", "300", "400", "500", "600", "700", "800", "900", "1000"};
        entradas07 = new String[] {"80", "160", "200", "240", "320", "400", "600", "800", "1000", "1200", "1400", "1600", "1800", "2000"};
        entradas08 = new String[] {"120", "240", "300", "360", "480", "600", "900", "1200", "1500", "1800", "2100", "2400", "2700", "3000"};
        entradas09 = new String[] {"160", "320", "400", "480", "640", "800", "1200", "1600", "2000", "2400", "2800", "3200", "3600", "4000"};
        entradas10 = new String[] {"200", "400", "500", "600", "800", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000"};
        entradas11 = new String[] {"400", "800", "1000", "1200", "1600", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"};
        entradas12 = new String[] {"600", "1200", "1500", "1800", "2400", "3000", "4500", "6000", "7500", "9000", "10500", "12000", "13500", "15000"};
        entradas13 = new String[] {"1000", "2000", "2500", "3000", "4000", "5000", "7500", "10000", "12500", "15000", "17500", "20000", "22500", "25000"};
        entradas14 = new String[] {"2000", "4000", "5000", "6000", "8000", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "50000"};
        entradas15 = new String[] {"4000", "8000", "10000", "12000", "16000", "20000", "30000", "40000", "50000", "60000", "70000", "80000", "90000", "100K"};
        entradas16 = new String[] {"10000", "20000", "25000", "30000", "40000", "50000", "75000", "100K", "125K", "150K", "175K", "200K", "225K", "250K"};
        entradas17 = new String[] {"20000", "40000", "50000", "60000", "80000", "100K", "150K", "200K", "250K", "300K", "350K", "400K", "450K", "500K"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, entradas01);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEntradaMinima = (Spinner) findViewById(R.id.spEntradaMinimaCash);
        spEntradaMinima.setAdapter(adapter);*/
    }

    //Aumentar y disminuir la ciega
    public void menosciegas(View view) {
        tvCiegaMinima = (TextView) findViewById(R.id.tvCiegaMinima);
        tvCiegaMaxima = (TextView) findViewById(R.id.tvCiegaMaxima);
        String ciegaMinima = tvCiegaMinima.getText().toString();
        String ciegaMaxima = tvCiegaMaxima.getText().toString();
        switch (ciegaMinima + " / " + ciegaMaxima) {
            case "0.10 / 0.20":
                tvCiegaMinima.setText("0.10");
                tvCiegaMaxima.setText("0.20");
                break;
            case "0.20 / 0.40":
                tvCiegaMinima.setText("0.10");
                tvCiegaMaxima.setText("0.20");
                break;
            case "0.30 / 0.60":
                tvCiegaMinima.setText("0.20");
                tvCiegaMaxima.setText("0.40");
                break;
            case "0.40 / 0.80":
                tvCiegaMinima.setText("0.30");
                tvCiegaMaxima.setText("0.60");
                break;
            case "0.50 / 1":
                tvCiegaMinima.setText("0.40");
                tvCiegaMaxima.setText("0.80");
                break;
            case "1 / 2":
                tvCiegaMinima.setText("0.50");
                tvCiegaMaxima.setText("1");
                break;
            case "2 / 4":
                tvCiegaMinima.setText("1");
                tvCiegaMaxima.setText("2");
                break;
            case "3 / 6":
                tvCiegaMinima.setText("2");
                tvCiegaMaxima.setText("4");
                break;
            case "4 / 8":
                tvCiegaMinima.setText("3");
                tvCiegaMaxima.setText("6");
                break;
            case "5 / 10":
                tvCiegaMinima.setText("4");
                tvCiegaMaxima.setText("8");
                break;
            case "10 / 20":
                tvCiegaMinima.setText("5");
                tvCiegaMaxima.setText("10");
                break;
            case "15 / 30":
                tvCiegaMinima.setText("10");
                tvCiegaMaxima.setText("20");
                break;
            case "25 / 50":
                tvCiegaMinima.setText("15");
                tvCiegaMaxima.setText("30");
                break;
            case "50 / 100":
                tvCiegaMinima.setText("25");
                tvCiegaMaxima.setText("50");
                break;
            case "100 / 200":
                tvCiegaMinima.setText("50");
                tvCiegaMaxima.setText("100");
                break;
            case "250 / 500":
                tvCiegaMinima.setText("100");
                tvCiegaMaxima.setText("200");
                break;
            case "500 / 1000":
                tvCiegaMinima.setText("250");
                tvCiegaMaxima.setText("500");
                break;
        }
    }

    public void masciegas(View view) {
        tvCiegaMinima = (TextView) findViewById(R.id.tvCiegaMinima);
        tvCiegaMaxima = (TextView) findViewById(R.id.tvCiegaMaxima);
        String ciegaMinima = tvCiegaMinima.getText().toString();
        String ciegaMaxima = tvCiegaMaxima.getText().toString();
        switch (ciegaMinima + " / " + ciegaMaxima) {
            case "0.10 / 0.20":
                tvCiegaMinima.setText("0.20");
                tvCiegaMaxima.setText("0.40");
                break;
            case "0.20 / 0.40":
                tvCiegaMinima.setText("0.30");
                tvCiegaMaxima.setText("0.60");
                break;
            case "0.30 / 0.60":
                tvCiegaMinima.setText("0.40");
                tvCiegaMaxima.setText("0.80");
                break;
            case "0.40 / 0.80":
                tvCiegaMinima.setText("0.50");
                tvCiegaMaxima.setText("1");
                break;
            case "0.50 / 1":
                tvCiegaMinima.setText("1");
                tvCiegaMaxima.setText("2");
                break;
            case "1 / 2":
                tvCiegaMinima.setText("2");
                tvCiegaMaxima.setText("4");
                break;
            case "2 / 4":
                tvCiegaMinima.setText("3");
                tvCiegaMaxima.setText("6");
                break;
            case "3 / 6":
                tvCiegaMinima.setText("4");
                tvCiegaMaxima.setText("8");
                break;
            case "4 / 8":
                tvCiegaMinima.setText("5");
                tvCiegaMaxima.setText("10");
                break;
            case "5 / 10":
                tvCiegaMinima.setText("10");
                tvCiegaMaxima.setText("20");
                break;
            case "10 / 20":
                tvCiegaMinima.setText("15");
                tvCiegaMaxima.setText("30");
                break;
            case "15 / 30":
                tvCiegaMinima.setText("25");
                tvCiegaMaxima.setText("50");
                break;
            case "25 / 50":
                tvCiegaMinima.setText("50");
                tvCiegaMaxima.setText("100");
                break;
            case "50 / 100":
                tvCiegaMinima.setText("100");
                tvCiegaMaxima.setText("200");
                break;
            case "100 / 200":
                tvCiegaMinima.setText("250");
                tvCiegaMaxima.setText("500");
                break;
            case "250 / 500":
                tvCiegaMinima.setText("500");
                tvCiegaMaxima.setText("1000");
                break;
            case "500 / 1000":
                tvCiegaMinima.setText("500");
                tvCiegaMaxima.setText("1000");
                break;
        }
    }

    public void menosFichasI(View view) {
        tvFichasIniciales = (TextView) findViewById(R.id.tvFIniciales);
        String fichasI = tvFichasIniciales.getText().toString();
        Log.i(fichasI, "fichasI");
        switch (fichasI) {
            case "1000":
                tvFichasIniciales.setText("1000");
                break;
            case "1500":
                tvFichasIniciales.setText("1000");
                break;
            case "2000":
                tvFichasIniciales.setText("1500");
                break;
            case "3000":
                tvFichasIniciales.setText("2000");
                break;
            case "5000":
                tvFichasIniciales.setText("3000");
                break;
            case "7500":
                tvFichasIniciales.setText("5000");
                break;
            case "10000":
                tvFichasIniciales.setText("7500");
                break;
            case "12000":
                tvFichasIniciales.setText("10000");
                break;
            case "15000":
                tvFichasIniciales.setText("12000");
                break;
            case "20000":
                tvFichasIniciales.setText("15000");
                break;
            case "30000":
                tvFichasIniciales.setText("20000");
                break;
            case "50000":
                tvFichasIniciales.setText("30000");
                break;
        }
    }

    public void masFichasI(View view) {
        tvFichasIniciales = (TextView) findViewById(R.id.tvFIniciales);
        String fichasI = tvFichasIniciales.getText().toString();
        Log.i(fichasI, "fichasI");
        switch (fichasI) {
            case "1000":
                tvFichasIniciales.setText("1500");
                break;
            case "1500":
                tvFichasIniciales.setText("2000");
                break;
            case "2000":
                tvFichasIniciales.setText("3000");
                break;
            case "3000":
                tvFichasIniciales.setText("5000");
                break;
            case "5000":
                tvFichasIniciales.setText("7500");
                break;
            case "7500":
                tvFichasIniciales.setText("10000");
                break;
            case "10000":
                tvFichasIniciales.setText("12000");
                break;
            case "12000":
                tvFichasIniciales.setText("15000");
                break;
            case "15000":
                tvFichasIniciales.setText("20000");
                break;
            case "20000":
                tvFichasIniciales.setText("30000");
                break;
            case "30000":
                tvFichasIniciales.setText("50000");
                break;
            case "50000":
                tvFichasIniciales.setText("50000");
                break;
        }
    }

    public void menosCiegasArriba(View view) {
        tvCiegasArriba = (TextView) findViewById(R.id.tvCiegasA);
        String ciegasA = tvCiegasArriba.getText().toString();
        Log.i(ciegasA, "CiegasArriba");
        switch (ciegasA) {
            case "2 min":
                tvCiegasArriba.setText("2 min");
                break;
            case "3 min":
                tvCiegasArriba.setText("2 min");
                break;
            case "4 min":
                tvCiegasArriba.setText("3 min");
                break;
            case "5 min":
                tvCiegasArriba.setText("4 min");
                break;
            case "6 min":
                tvCiegasArriba.setText("5 min");
                break;
            case "7 min":
                tvCiegasArriba.setText("6 min");
                break;
            case "8 min":
                tvCiegasArriba.setText("7 min");
                break;
            case "10 min":
                tvCiegasArriba.setText("8 min");
                break;
            case "12 min":
                tvCiegasArriba.setText("10 min");
                break;
            case "15 min":
                tvCiegasArriba.setText("12 min");
                break;
            case "20 min":
                tvCiegasArriba.setText("15 min");
                break;
            case "30 min":
                tvCiegasArriba.setText("20 min");
                break;
        }
    }

    public void masCiegasArriba(View view) {
        tvCiegasArriba = (TextView) findViewById(R.id.tvCiegasA);
        String ciegasA = tvCiegasArriba.getText().toString();
        Log.i(ciegasA, "CiegasArriba");
        switch (ciegasA) {
            case "2 min":
                tvCiegasArriba.setText("3 min");
                break;
            case "3 min":
                tvCiegasArriba.setText("4 min");
                break;
            case "4 min":
                tvCiegasArriba.setText("5 min");
                break;
            case "5 min":
                tvCiegasArriba.setText("6 min");
                break;
            case "6 min":
                tvCiegasArriba.setText("7 min");
                break;
            case "7 min":
                tvCiegasArriba.setText("8 min");
                break;
            case "8 min":
                tvCiegasArriba.setText("10 min");
                break;
            case "10 min":
                tvCiegasArriba.setText("12 min");
                break;
            case "12 min":
                tvCiegasArriba.setText("15 min");
                break;
            case "15 min":
                tvCiegasArriba.setText("20 min");
                break;
            case "20 min":
                tvCiegasArriba.setText("30 min");
                break;
            case "30 min":
                tvCiegasArriba.setText("30 min");
                break;
        }
    }

    public void menosFichasI2(View view) {
        tvFichasIniciales2 = (TextView) findViewById(R.id.tvFIniciales2);
        String fichasI2 = tvFichasIniciales2.getText().toString();
        Log.i(fichasI2, "fichasI2");
        switch (fichasI2) {
            case "1000":
                tvFichasIniciales2.setText("1000");
                break;
            case "1500":
                tvFichasIniciales2.setText("1000");
                break;
            case "2000":
                tvFichasIniciales2.setText("1500");
                break;
            case "3000":
                tvFichasIniciales2.setText("2000");
                break;
            case "5000":
                tvFichasIniciales2.setText("3000");
                break;
            case "7500":
                tvFichasIniciales2.setText("5000");
                break;
            case "10000":
                tvFichasIniciales2.setText("7500");
                break;
            case "12000":
                tvFichasIniciales2.setText("10000");
                break;
            case "15000":
                tvFichasIniciales2.setText("12000");
                break;
            case "20000":
                tvFichasIniciales2.setText("15000");
                break;
            case "30000":
                tvFichasIniciales2.setText("20000");
                break;
            case "50000":
                tvFichasIniciales2.setText("30000");
                break;
        }
    }

    public void masFichasI2(View view) {
        tvFichasIniciales2 = (TextView) findViewById(R.id.tvFIniciales2);
        String fichasI2 = tvFichasIniciales2.getText().toString();
        Log.i(fichasI2, "fichasI2");
        switch (fichasI2) {
            case "1000":
                tvFichasIniciales2.setText("1500");
                break;
            case "1500":
                tvFichasIniciales2.setText("2000");
                break;
            case "2000":
                tvFichasIniciales2.setText("3000");
                break;
            case "3000":
                tvFichasIniciales2.setText("5000");
                break;
            case "5000":
                tvFichasIniciales2.setText("7500");
                break;
            case "7500":
                tvFichasIniciales2.setText("10000");
                break;
            case "10000":
                tvFichasIniciales2.setText("12000");
                break;
            case "12000":
                tvFichasIniciales2.setText("15000");
                break;
            case "15000":
                tvFichasIniciales2.setText("20000");
                break;
            case "20000":
                tvFichasIniciales2.setText("30000");
                break;
            case "30000":
                tvFichasIniciales2.setText("50000");
                break;
            case "50000":
                tvFichasIniciales2.setText("50000");
                break;
        }
    }

    public void menosCiegasArriba2(View view) {
        tvCiegasArriba2 = (TextView) findViewById(R.id.tvCiegasA2);
        String ciegasA2 = tvCiegasArriba2.getText().toString();
        Log.i(ciegasA2, "CiegasArriba2");
        switch (ciegasA2) {
            case "2 min":
                tvCiegasArriba2.setText("2 min");
                break;
            case "3 min":
                tvCiegasArriba2.setText("2 min");
                break;
            case "4 min":
                tvCiegasArriba2.setText("3 min");
                break;
            case "5 min":
                tvCiegasArriba2.setText("4 min");
                break;
            case "6 min":
                tvCiegasArriba2.setText("5 min");
                break;
            case "7 min":
                tvCiegasArriba2.setText("6 min");
                break;
            case "8 min":
                tvCiegasArriba2.setText("7 min");
                break;
            case "10 min":
                tvCiegasArriba2.setText("8 min");
                break;
            case "12 min":
                tvCiegasArriba2.setText("10 min");
                break;
            case "15 min":
                tvCiegasArriba2.setText("12 min");
                break;
            case "20 min":
                tvCiegasArriba2.setText("15 min");
                break;
            case "30 min":
                tvCiegasArriba2.setText("20 min");
                break;
        }
    }

    public void masCiegasArriba2(View view) {
        tvCiegasArriba2 = (TextView) findViewById(R.id.tvCiegasA2);
        String ciegasA2 = tvCiegasArriba2.getText().toString();
        Log.i(ciegasA2, "CiegasArriba2");
        switch (ciegasA2) {
            case "2 min":
                tvCiegasArriba2.setText("3 min");
                break;
            case "3 min":
                tvCiegasArriba2.setText("4 min");
                break;
            case "4 min":
                tvCiegasArriba2.setText("5 min");
                break;
            case "5 min":
                tvCiegasArriba2.setText("6 min");
                break;
            case "6 min":
                tvCiegasArriba2.setText("7 min");
                break;
            case "7 min":
                tvCiegasArriba2.setText("8 min");
                break;
            case "8 min":
                tvCiegasArriba2.setText("10 min");
                break;
            case "10 min":
                tvCiegasArriba2.setText("12 min");
                break;
            case "12 min":
                tvCiegasArriba2.setText("15 min");
                break;
            case "15 min":
                tvCiegasArriba2.setText("20 min");
                break;
            case "20 min":
                tvCiegasArriba2.setText("30 min");
                break;
            case "30 min":
                tvCiegasArriba2.setText("30 min");
                break;
        }
    }

    public void menosJugadasMin(View view) {
        tvJugadasMin = (TextView) findViewById(R.id.tvJugadasMin);
        String jugadasMin = tvJugadasMin.getText().toString();
        Log.i(jugadasMin, "jugadasMin");
        switch (jugadasMin) {
            case "5":
                tvJugadasMin.setText("5");
                break;
            case "10":
                tvJugadasMin.setText("5");
                break;
            case "20":
                tvJugadasMin.setText("10");
                break;
            case "30":
                tvJugadasMin.setText("20");
                break;
            case "40":
                tvJugadasMin.setText("30");
                break;
            case "50":
                tvJugadasMin.setText("40");
                break;
            case "60":
                tvJugadasMin.setText("50");
                break;
            case "100":
                tvJugadasMin.setText("60");
                break;
            case "200":
                tvJugadasMin.setText("100");
                break;
            case "300":
                tvJugadasMin.setText("200");
                break;
            case "500":
                tvJugadasMin.setText("300");
                break;
            case "800":
                tvJugadasMin.setText("500");
                break;
            case "1000":
                tvJugadasMin.setText("800");
                break;
            case "2000":
                tvJugadasMin.setText("1000");
                break;
            case "3000":
                tvJugadasMin.setText("2000");
                break;
            case "4000":
                tvJugadasMin.setText("3000");
                break;
            case "5000":
                tvJugadasMin.setText("4000");
                break;
        }
    }

    public void masJugadasMin(View view) {
        tvJugadasMin = (TextView) findViewById(R.id.tvJugadasMin);
        String jugadasMin = tvJugadasMin.getText().toString();
        Log.i(jugadasMin, "jugadasMin");
        switch (jugadasMin) {
            case "5":
                tvJugadasMin.setText("10");
                break;
            case "10":
                tvJugadasMin.setText("20");
                break;
            case "20":
                tvJugadasMin.setText("30");
                break;
            case "30":
                tvJugadasMin.setText("40");
                break;
            case "40":
                tvJugadasMin.setText("50");
                break;
            case "50":
                tvJugadasMin.setText("60");
                break;
            case "60":
                tvJugadasMin.setText("100");
                break;
            case "100":
                tvJugadasMin.setText("200");
                break;
            case "200":
                tvJugadasMin.setText("300");
                break;
            case "300":
                tvJugadasMin.setText("500");
                break;
            case "500":
                tvJugadasMin.setText("800");
                break;
            case "800":
                tvJugadasMin.setText("1000");
                break;
            case "1000":
                tvJugadasMin.setText("2000");
                break;
            case "2000":
                tvJugadasMin.setText("3000");
                break;
            case "3000":
                tvJugadasMin.setText("4000");
                break;
            case "4000":
                tvJugadasMin.setText("5000");
                break;
            case "5000":
                tvJugadasMin.setText("5000");
                break;
        }
    }

    public void menosJugadasMax(View view) {
        tvJugadasMax = (TextView) findViewById(R.id.tvJugadasMax);
        String jugadasMax = tvJugadasMax.getText().toString();
        Log.i(jugadasMax, "jugadasMax");
        switch (jugadasMax) {
            case "5":
                tvJugadasMax.setText("5");
                break;
            case "10":
                tvJugadasMax.setText("5");
                break;
            case "20":
                tvJugadasMax.setText("10");
                break;
            case "30":
                tvJugadasMax.setText("20");
                break;
            case "40":
                tvJugadasMax.setText("30");
                break;
            case "50":
                tvJugadasMax.setText("40");
                break;
            case "60":
                tvJugadasMax.setText("50");
                break;
            case "100":
                tvJugadasMax.setText("60");
                break;
            case "200":
                tvJugadasMax.setText("100");
                break;
            case "300":
                tvJugadasMax.setText("200");
                break;
            case "500":
                tvJugadasMax.setText("300");
                break;
            case "800":
                tvJugadasMax.setText("500");
                break;
            case "1000":
                tvJugadasMax.setText("800");
                break;
            case "2000":
                tvJugadasMax.setText("1000");
                break;
            case "3000":
                tvJugadasMax.setText("2000");
                break;
            case "4000":
                tvJugadasMax.setText("3000");
                break;
            case "5000":
                tvJugadasMax.setText("4000");
                break;
        }
    }

    public void masJugadasMax(View view) {
        tvJugadasMax = (TextView) findViewById(R.id.tvJugadasMax);
        String jugadasMax = tvJugadasMax.getText().toString();
        Log.i(jugadasMax, "jugadasMax");
        switch (jugadasMax) {
            case "5":
                tvJugadasMax.setText("10");
                break;
            case "10":
                tvJugadasMax.setText("20");
                break;
            case "20":
                tvJugadasMax.setText("30");
                break;
            case "30":
                tvJugadasMax.setText("40");
                break;
            case "40":
                tvJugadasMax.setText("50");
                break;
            case "50":
                tvJugadasMax.setText("60");
                break;
            case "60":
                tvJugadasMax.setText("100");
                break;
            case "100":
                tvJugadasMax.setText("200");
                break;
            case "200":
                tvJugadasMax.setText("300");
                break;
            case "300":
                tvJugadasMax.setText("500");
                break;
            case "500":
                tvJugadasMax.setText("800");
                break;
            case "800":
                tvJugadasMax.setText("1000");
                break;
            case "1000":
                tvJugadasMax.setText("2000");
                break;
            case "2000":
                tvJugadasMax.setText("3000");
                break;
            case "3000":
                tvJugadasMax.setText("4000");
                break;
            case "4000":
                tvJugadasMax.setText("5000");
                break;
            case "5000":
                tvJugadasMax.setText("5000");
                break;
        }
    }

    public void menosRegistros(View view) {
        tvRegistroT = (TextView) findViewById(R.id.tvRegistroT);
        String registro = tvRegistroT.getText().toString();
        Log.i(registro, "registro");
        switch (registro) {
            case "level 1":
                tvRegistroT.setText("level 1");
                break;
            case "level 2":
                tvRegistroT.setText("level 1");
                break;
            case "level 3":
                tvRegistroT.setText("level 2");
                break;
            case "level 4":
                tvRegistroT.setText("level 3");
                break;
            case "level 5":
                tvRegistroT.setText("level 4");
                break;
            case "level 6":
                tvRegistroT.setText("level 5");
                break;
            case "level 7":
                tvRegistroT.setText("level 6");
                break;
            case "level 8":
                tvRegistroT.setText("level 7");
                break;
            case "level 9":
                tvRegistroT.setText("level 8");
                break;
            case "level 10":
                tvRegistroT.setText("level 9");
                break;
            case "level 11":
                tvRegistroT.setText("level 10");
                break;
            case "level 12":
                tvRegistroT.setText("level 11");
                break;
            case "level 13":
                tvRegistroT.setText("level 12");
                break;
            case "level 14":
                tvRegistroT.setText("level 13");
                break;
            case "level 15":
                tvRegistroT.setText("level 14");
                break;
        }
    }

    public void masRegistros(View view) {
        tvRegistroT = (TextView) findViewById(R.id.tvRegistroT);
        String registro = tvRegistroT.getText().toString();
        Log.i(registro, "registro");
        switch (registro) {
            case "level 1":
                tvRegistroT.setText("level 2");
                break;
            case "level 2":
                tvRegistroT.setText("level 3");
                break;
            case "level 3":
                tvRegistroT.setText("level 4");
                break;
            case "level 4":
                tvRegistroT.setText("level 5");
                break;
            case "level 5":
                tvRegistroT.setText("level 6");
                break;
            case "level 6":
                tvRegistroT.setText("level 7");
                break;
            case "level 7":
                tvRegistroT.setText("level 8");
                break;
            case "level 8":
                tvRegistroT.setText("level 9");
                break;
            case "level 9":
                tvRegistroT.setText("level 10");
                break;
            case "level 10":
                tvRegistroT.setText("level 11");
                break;
            case "level 11":
                tvRegistroT.setText("level 12");
                break;
            case "level 12":
                tvRegistroT.setText("level 13");
                break;
            case "level 13":
                tvRegistroT.setText("level 14");
                break;
            case "level 14":
                tvRegistroT.setText("level 15");
                break;
            case "level 15":
                tvRegistroT.setText("level 15");
                break;
        }
    }

    public void empezarCashGame(View view){
        Log.i("depurar","crear mesa");
        tvCiegaMinima = (TextView) findViewById(R.id.tvCiegaMinima);
        tvCiegaMaxima = (TextView) findViewById(R.id.tvCiegaMaxima);
        tvCiegasArriba = (TextView) findViewById(R.id.tvCiegasA);
        tvFichasIniciales = (TextView) findViewById(R.id.tvFIniciales);
        tvCiegasArriba2 = (TextView) findViewById(R.id.tvCiegasA2);
        tvFichasIniciales2 = (TextView) findViewById(R.id.tvFIniciales2);
        tvRegistroT = (TextView) findViewById(R.id.tvRegistroT);
        tvJugadasMin = (TextView) findViewById(R.id.tvJugadasMin);
        tvJugadasMax = (TextView) findViewById(R.id.tvJugadasMax);
        rbMesa3 = (RadioButton) findViewById(R.id.rbMesa3);
        rbMesa6 = (RadioButton) findViewById(R.id.rbMesa6);
        rbMesa9 = (RadioButton) findViewById(R.id.rbMesa9);
        rbAccion15s = (RadioButton) findViewById(R.id.rbAccion15s);
        rbAccion20s = (RadioButton) findViewById(R.id.rbAccion20s);
        rbAccion30s = (RadioButton) findViewById(R.id.rbAccion30s);
        rbComienzo2 = (RadioButton) findViewById(R.id.rbComienzo2);
        rbComienzo3 = (RadioButton) findViewById(R.id.rbComienzo3);
        rbComienzo4 = (RadioButton) findViewById(R.id.rbComienzo4);
        rbComienzo5 = (RadioButton) findViewById(R.id.rbComienzo5);
        rbCall15 = (RadioButton) findViewById(R.id.rbCall15);
        rbCall20 = (RadioButton) findViewById(R.id.rbCall20);
        rbCall30 = (RadioButton) findViewById(R.id.rbCall30);
        sComiezoA = (Switch) findViewById(R.id.sComienzoA);
        sExtensionA = (Switch) findViewById(R.id.sExtensionA);
        sAutorizadoEntrar = (Switch)findViewById(R.id.sAutParaEntrar);
        sCallTime = (Switch) findViewById(R.id.sCallTime);
        sp_veces = (Spinner) findViewById(R.id.sp_veces);
        sp_rake = (Spinner) findViewById(R.id.sp_rake);
        sp_tiempoMesa = (Spinner) findViewById(R.id.sp_tiempoMesa);
        chkMultiveces = (CheckBox) findViewById(R.id.chkMultiveces);
        etNombreMesaCash = (EditText) findViewById(R.id.etNombreMesaCash);
        etEntradaMinima = (EditText) findViewById(R.id.etEntradaMinima);
        etEntradaMaxima = (EditText) findViewById(R.id.etEntradaMaxima);
        Thread tr = new Thread() {
            @Override
            public void run() {
                nombreM = etNombreMesaCash.getText().toString();
                ciegaMinima = tvCiegaMinima.getText().toString();
                ciegaMaxima = tvCiegaMaxima.getText().toString();
                entradaMin = etEntradaMinima.getText().toString();
                entradaMax = etEntradaMaxima.getText().toString();
                mesa2 = String.valueOf(rbMesa3.isChecked());
                mesa6 = String.valueOf(rbMesa6.isChecked());
                mesa9 = String.valueOf(rbMesa9.isChecked());
                String id_tipo_mesa = "";
                if (mesa2 == "true") {
                    id_tipo_mesa = "1";
                } else {
                    if (mesa6 == "true") {
                        id_tipo_mesa = "2";
                    } else {
                        if (mesa9 == "true") {
                            id_tipo_mesa = "3";
                        }
                    }
                }
                String accion = "";
                accion15 = String.valueOf(rbAccion15s.isChecked());
                accion20 = String.valueOf(rbAccion20s.isChecked());
                accion30 = String.valueOf(rbAccion30s.isChecked());
                if (accion15 == "true") {
                    accion = "15";
                } else {
                    if (accion20 == "true") {
                        accion = "20";
                    } else {
                        if (accion30 == "true") {
                            accion = "30";
                        }
                    }
                }
                String jugadores_minimo = "";
                comienzo2 = String.valueOf(rbComienzo2.isChecked());
                comienzo3 = String.valueOf(rbComienzo3.isChecked());
                comienzo4 = String.valueOf(rbComienzo4.isChecked());
                comienzo5 = String.valueOf(rbComienzo5.isChecked());
                if (comienzo2 == "true") {
                    jugadores_minimo = "2";
                } else {
                    if (comienzo3 == "true") {
                        jugadores_minimo = "3";
                    } else {
                        if (comienzo4 == "true") {
                            jugadores_minimo = "4";
                        } else {
                            if (comienzo5 == "true") {
                                jugadores_minimo = "5";
                            }
                        }
                    }
                }
                veces = sp_veces.getSelectedItem().toString();
                String multiveces = "";
                multiVeces = String.valueOf(chkMultiveces.isChecked());
                if (multiVeces == "true") {
                    multiveces = "1";
                } else {
                    multiveces = "0";
                }
                rake = sp_rake.getSelectedItem().toString().substring(0, 1);
                String calltime = "";
                call15 = String.valueOf(rbCall15.isChecked());
                call20 = String.valueOf(rbCall20.isChecked());
                call30 = String.valueOf(rbCall30.isChecked());
                if (call15 == "true") {
                    calltime = "15";
                } else {
                    if (call20 == "true") {
                        calltime = "20";
                    } else {
                        if (call30 == "true") {
                            calltime = "30";
                        }
                    }
                }
                String autorizadoentrar = "";
                autorizadoEntrar = String.valueOf(sAutorizadoEntrar.isChecked());
                if (autorizadoEntrar == "true") {
                    autorizadoentrar = "1";
                } else {
                    autorizadoentrar = "0";
                }
                tiempoMesa = sp_tiempoMesa.getSelectedItem().toString().substring(0, (sp_tiempoMesa.getSelectedItem().toString().length() - 2));
                final String result = enviarDatosGet(ciegaMinima , ciegaMaxima, entradaMin , entradaMax , nombreM , id_tipo_mesa,
                        accion, jugadores_minimo, veces, multiveces, rake , calltime, autorizadoentrar , tiempoMesa, id_clubes, "1");
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson(result);
                        if (r > 0) {
                            MostrarData();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarDatosGet(String ciegaMinima, String ciegaMaxima ,String  entradaMin ,String  entradaMax ,String  nombreM ,String  tipo_mesa,String
            accion, String jugadores_minimo,String veces,String  multiVeces,String  rake ,String  callTime,String  autorizadoEntrar ,String  tiempoMesa, String id_club, String modalidad) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registrar_mesa.php?id_modalidad=" + modalidad + "&nombre=" + nombreM + "&id_tipo_mesa=" + tipo_mesa + "&accion=" + accion + "&ciega_minima=" + ciegaMinima
                    + "&ciega_maxima=" + ciegaMaxima + "&entrada_minima=" + entradaMin + "&entrada_maxima=" + entradaMax + "&jugadores_minimo=" + jugadores_minimo + "&veces=" + veces + "&multiveces=" + multiVeces
                    + "&rake=" + rake + "&calltime=" + callTime + "&autorizado_entrar=" + autorizadoEntrar + "&duracion_mesa=" + tiempoMesa + "&id_club=" + id_club);
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

    public int obtDatosJson(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void MostrarData() {
        try {
            Thread tr3 = new Thread() {
                @Override
                public void run() {
                    final String result2 = consultarId_mesa();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtId_mesa(result2);
                            if (r > 0) {
                                try {
                                    JSONArray json = new JSONArray(result2);
                                    if (json.length() > 0) {
                                        id_mesa = json.getJSONObject(0).getString("ID");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                RadioButton rbt3 = findViewById(R.id.rbMesa3), rbt6 = findViewById(R.id.rbMesa6), rbt9 =
                                        findViewById(R.id.rbMesa9);
                                if (rbt3.isChecked()) {
                                    Intent intent = new Intent(CrearMesa.this, Juego3.class);
                                    intent.putExtra("id_mesa_card", id_mesa);
                                    startActivity(intent);
                                    finish();
                                } else if (rbt6.isChecked()) {
                                    Intent intent = new Intent(CrearMesa.this, Juego6.class);
                                    intent.putExtra("id_mesa_card", id_mesa);
                                    startActivity(intent);
                                    finish();
                                } else if (rbt9.isChecked()) {
                                    Intent intent = new Intent(CrearMesa.this, Juego9.class);
                                    intent.putExtra("id_mesa_card", id_mesa);
                                    startActivity(intent);
                                    finish();
                                }
                                /*Thread tr2 = new Thread() {
                                    @Override
                                    public void run() {
                                        final String result3 = enviarMano(id_mesa);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                int r = obtMano(result3);
                                                if (r > 0) {
                                                        Thread tr4 = new Thread() {
                                                        @Override
                                                        public void run() {
                                                            int[] nros ;
                                                            nros = NroRandom();
                                                            final String result4 = enviarCom(id_mesa,nros);
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    int r = obtMano(result4);
                                                                    if (r > 0) {
                                                                        mostrarMano();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    };
                                                    tr4.start();
                                                }
                                            }
                                        });
                                    }
                                };
                                tr2.start();*/
                            }
                        }
                    });
                }
            };
            tr3.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] NroRandom() {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT);
        int[] nrosrandom = new int [53];
        for (int i = 1; i < 53; i++) {
            nrosrandom[i]=rng.nextInt(52);
            if (i>=2)
            {
                for (int x =1 ; x<i ; x++)
                {
                    if (nrosrandom[x]==nrosrandom[i])
                    {
                        i--;
                    }
                }
            }

        }
        return nrosrandom;
    }

    /*--------------------------------------*/
    /*--------------------------------------*/
    /*-------------------------------------*/
    public String enviarCom(String id, int [] rnd)
    {
        StringBuilder result = null;
        for (int i =1 ; i<6 ; i++)
        {
            URL url = null;
            String linea = "";
            int respuesta = 0;
            try {
                url = new URL("http://gosmart.pe/poker/insertar_carta_comun.php?id_mesa="+id_mesa + "&id_carta=" + rnd[i]);
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

    public String consultarId_mesa() {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/consultar_id_mesa.php");
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

    public int obtId_mesa(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public String enviarMano(String id_mesa) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registrar_mano.php?id_mesa=" + id_mesa);
            Log.i("depurar", id_mesa);
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

    public int obtMano(String response) {
        int res = 0;
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void mostrarMano() {
        RadioButton rbt1, rbt2, rbt3;
        rbt1 = (RadioButton) findViewById(R.id.rbMesa3);
        rbt2 = (RadioButton) findViewById(R.id.rbMesa6);
        rbt3 = (RadioButton) findViewById(R.id.rbMesa9);
        if (rbt1.isChecked()) {
            Intent intent = new Intent(this, Juego3.class);
            startActivity(intent);
        } else {
            if (rbt2.isChecked()) {
                Intent intent = new Intent(this, Juego6.class);
                startActivity(intent);
            } else {
                if (rbt3.isChecked()) {
                    Intent intent = new Intent(this, Juego9.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void ComienzoA(View view) {
        LinearLayout grupo = (LinearLayout) findViewById(R.id.ll_RadioGroup);
        sComiezoA = (Switch) findViewById(R.id.sComienzoA);
        if (sComiezoA.isChecked()) {
            grupo.setVisibility(View.VISIBLE);
        } else {
            grupo.setVisibility(View.GONE);
        }
    }

    public void extensionA(View view) {
        LinearLayout grupo = (LinearLayout) findViewById(R.id.llExtensionAutomaticaCash);
        sExtensionA = (Switch) findViewById(R.id.sExtensionA);
        if (sExtensionA.isChecked()) {
            grupo.setVisibility(View.VISIBLE);
        } else {
            grupo.setVisibility(View.GONE);
        }
    }

    public void CallTime(View view) {
        RadioGroup grupo = (RadioGroup) findViewById(R.id.rgCallTimeCash);
        sCallTime = (Switch) findViewById(R.id.sCallTime);
        if (sCallTime.isChecked()) {
            grupo.setVisibility(View.VISIBLE);
        } else {
            grupo.setVisibility(View.GONE);
        }
    }

    public void CantidadNo(View view) {
        RadioButton rbt2 = (RadioButton) findViewById(R.id.rbMesa3);
        RadioButton rbt3 = (RadioButton) findViewById(R.id.rbComienzo3);
        RadioButton rbt4 = (RadioButton) findViewById(R.id.rbComienzo4);
        RadioButton rbt5 = (RadioButton) findViewById(R.id.rbComienzo5);
        ImageView imv3 = (ImageView) findViewById(R.id.imvComienzo3);
        ImageView imv4 = (ImageView) findViewById(R.id.imvComienzo4);
        ImageView imv5 = (ImageView) findViewById(R.id.imvComienzo5);
        if (rbt2.isChecked()) {
            rbt3.setVisibility(View.GONE);
            rbt4.setVisibility(View.GONE);
            rbt5.setVisibility(View.GONE);
            imv3.setVisibility(View.GONE);
            imv4.setVisibility(View.GONE);
            imv5.setVisibility(View.GONE);
        } else {
            rbt3.setVisibility(View.VISIBLE);
            rbt4.setVisibility(View.VISIBLE);
            rbt5.setVisibility(View.VISIBLE);
            imv3.setVisibility(View.VISIBLE);
            imv4.setVisibility(View.VISIBLE);
            imv5.setVisibility(View.VISIBLE);
        }
    }

    public void CantidadSi(View view) {
        RadioButton rbt6 = (RadioButton) findViewById(R.id.rbMesa6);
        RadioButton rbt9 = (RadioButton) findViewById(R.id.rbMesa9);
        RadioButton rbt3 = (RadioButton) findViewById(R.id.rbComienzo3);
        RadioButton rbt4 = (RadioButton) findViewById(R.id.rbComienzo4);
        RadioButton rbt5 = (RadioButton) findViewById(R.id.rbComienzo5);
        ImageView imv3 = (ImageView) findViewById(R.id.imvComienzo3);
        ImageView imv4 = (ImageView) findViewById(R.id.imvComienzo4);
        ImageView imv5 = (ImageView) findViewById(R.id.imvComienzo5);
        if (rbt6.isChecked() || rbt9.isChecked()) {
            rbt3.setVisibility(View.VISIBLE);
            rbt4.setVisibility(View.VISIBLE);
            rbt5.setVisibility(View.VISIBLE);
            imv3.setVisibility(View.VISIBLE);
            imv4.setVisibility(View.VISIBLE);
            imv5.setVisibility(View.VISIBLE);
        } else {
            rbt3.setVisibility(View.GONE);
            rbt4.setVisibility(View.GONE);
            rbt5.setVisibility(View.GONE);
            imv3.setVisibility(View.GONE);
            imv4.setVisibility(View.GONE);
            imv5.setVisibility(View.GONE);
        }
    }

    public void empezarTorneoSimple(View view){

    }

    public void empezarTorneoEspecial(View view){

    }
}

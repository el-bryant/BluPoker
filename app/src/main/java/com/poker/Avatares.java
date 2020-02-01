package com.poker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import static com.poker.Registro.imbPerfil;
import static com.poker.Registro.perfil;

public class Avatares extends AppCompatActivity {
    ImageButton imbH01, imbH02, imbH03, imbH04, imbH05, imbH06, imbH07, imbH08, imbH09, imbH10, imbM01, imbM02, imbM03, imbM04, imbM05, imbM06;
    LinearLayout linVarones, linMujeres;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatares);

        imbH01 = (ImageButton) findViewById(R.id.imbH01);
        imbH02 = (ImageButton) findViewById(R.id.imbH02);
        imbH03 = (ImageButton) findViewById(R.id.imbH03);
        imbH04 = (ImageButton) findViewById(R.id.imbH04);
        imbH05 = (ImageButton) findViewById(R.id.imbH05);
        imbH06 = (ImageButton) findViewById(R.id.imbH06);
        imbH07 = (ImageButton) findViewById(R.id.imbH07);
        imbH08 = (ImageButton) findViewById(R.id.imbH08);
        imbH09 = (ImageButton) findViewById(R.id.imbH09);
        imbH10 = (ImageButton) findViewById(R.id.imbH10);
        imbM01 = (ImageButton) findViewById(R.id.imbM01);
        imbM02 = (ImageButton) findViewById(R.id.imbM02);
        imbM03 = (ImageButton) findViewById(R.id.imbM03);
        imbM04 = (ImageButton) findViewById(R.id.imbM04);
        imbM05 = (ImageButton) findViewById(R.id.imbM05);
        imbM06 = (ImageButton) findViewById(R.id.imbM06);

        linVarones = (LinearLayout) findViewById(R.id.linAvatarHombre);
        linMujeres = (LinearLayout) findViewById(R.id.linAvatarMujer);

        String recup_sexo = getIntent().getStringExtra("sexo");
        switch (recup_sexo) {
            case "M":
                linVarones.setVisibility(View.VISIBLE);
                linMujeres.setVisibility(View.GONE);
                break;
            case "F":
                linMujeres.setVisibility(View.VISIBLE);
                linVarones.setVisibility(View.GONE);
                break;
        }
    }

    public void ImagenM01(View view) {
        perfil = R.drawable.pjh01;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh01.png");
        finish();
    }

    public void ImagenM02(View view) {
        perfil = R.drawable.pjh02;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh02.png");
        finish();
    }

    public void ImagenM03(View view) {
        perfil = R.drawable.pjh03;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh03.png");
        finish();
    }

    public void ImagenM04(View view) {
        perfil = R.drawable.pjh04;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh04.png");
        finish();
    }

    public void ImagenM05(View view) {
        perfil = R.drawable.pjh05;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh05.png");
        finish();
    }

    public void ImagenM06(View view) {
        perfil = R.drawable.pjh06;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh06.png");
        finish();
    }

    public void ImagenM07(View view) {
        perfil = R.drawable.pjh07;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh07.png");
        finish();
    }

    public void ImagenM08(View view) {
        perfil = R.drawable.pjh08;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh08.png");
        finish();
    }

    public void ImagenM09(View view) {
        perfil = R.drawable.pjh09;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh09.png");
        finish();
    }

    public void ImagenM10(View view) {
        perfil = R.drawable.pjh10;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjh10.png");
        finish();
    }

    public void ImagenF01(View view) {
        perfil = R.drawable.pjm01;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm01.png");
        finish();
    }

    public void ImagenF02(View view) {
        perfil = R.drawable.pjm02;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm02.png");
        finish();
    }

    public void ImagenF03(View view) {
        perfil = R.drawable.pjm03;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm03.png");
        finish();
    }

    public void ImagenF04(View view) {
        perfil = R.drawable.pjm04;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm04.png");
        finish();
    }

    public void ImagenF05(View view) {
        perfil = R.drawable.pjm05;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm05.png");
        finish();
    }

    public void ImagenF06(View view) {
        perfil = R.drawable.pjm06;
        imbPerfil.setImageResource(perfil);
        imbPerfil.setTag("pjm06.png");
        finish();
    }
}

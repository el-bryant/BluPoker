package com.poker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Modalidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidad);
    }

    public void Texas(View view) {
        Intent intent = new Intent(this, CrearMesa.class);
        intent.putExtra("modalidad", "1");
        startActivity(intent);
        finish();
    }

    public void Omaha(View view) {
        Intent intent = new Intent(this, CrearMesa.class);
        intent.putExtra("modalidad", "2");
        startActivity(intent);
        finish();
    }
}

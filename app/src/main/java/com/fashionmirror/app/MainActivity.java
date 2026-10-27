package com.fashionmirror.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCatalog = findViewById(R.id.btnCatalog);
        Button btnSavedOutfits = findViewById(R.id.btnSavedOutfits);

        btnCatalog.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    CatalogActivity.class
            );
            startActivity(intent);
        });

        btnSavedOutfits.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Aici vei găsi ținutele salvate!",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}

package com.example.elperlanegra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class ContactenosActivity extends AppCompatActivity {

    ImageView whatsapp, instagram, facebook;

    //CROQUIS
    ImageView croquis;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);

        /////////////FLECHA HACIA ATRAS
        Toolbar toolbar = findViewById(R.id.tb_contactenos);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*/CROQUIS
        croquis = findViewById(R.id.croquis);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.croquis2);
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
        croquis.setImageBitmap(rotatedBitmap);
        croquis.setScaleType(ImageView.ScaleType.FIT_XY);
        /////////////*/

        whatsapp = findViewById(R.id.whatsapp);
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wa.me/593996483509"; // Reemplazar con tu número de teléfono de WhatsApp
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/elperlanegra.bar.restaurant/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/elperlanegra.bar.restaurant";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
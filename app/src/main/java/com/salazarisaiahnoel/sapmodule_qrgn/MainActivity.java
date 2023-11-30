package com.salazarisaiahnoel.sapmodule_qrgn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView i;
    EditText e;
    Button b;
    Bitmap bm;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        i = findViewById(R.id.img);
        e = findViewById(R.id.edit_text);
        b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(e.getText().toString())){
                    Toast.makeText(MainActivity.this, "Cannot generate empty QR.", Toast.LENGTH_SHORT).show();
                } else {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = Math.min(width, height);
                    dimen = dimen * 3 / 4;
                    qrgEncoder = new QRGEncoder(e.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    qrgEncoder.setColorBlack(Color.WHITE);
                    qrgEncoder.setColorWhite(Color.BLACK);
                    try {
                        bm = qrgEncoder.getBitmap();
                        i.setImageBitmap(bm);
                    } catch (Exception ignored) {
                    }
                }
            }
        });
    }
}
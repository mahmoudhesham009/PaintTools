package com.target.sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.target.edittool.EditTool;
import com.target.painttool.PaintTool;


public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button paintPhoto,editPhoto;
    Uri yourUri=Uri.parse("android.resource://com.target.sample/" +R.drawable.liverpool2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        paintPhoto=findViewById(R.id.paint_photo);
        editPhoto=findViewById(R.id.edit_photo);

        paintPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaintTool.with(MainActivity.this)
                        .setToolBarColor(Color.GRAY)
                        .setToolBarTitle("Hello from painter")
                        .setBackgroundColor(Color.BLACK)
                        .show();
            }
        });

        editPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTool.with(MainActivity.this, yourUri)
                        .setToolBarColor(Color.WHITE)
                        .setToolBarTitle("Hello from editor")
                        .show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case PaintTool.DRAW_RESULT:
                Bitmap bmp = PaintTool.getDrawResult(data);
                imageView.setImageBitmap(bmp);
                break;
        }
    }
}
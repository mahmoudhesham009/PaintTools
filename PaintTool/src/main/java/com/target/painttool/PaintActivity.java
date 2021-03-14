package com.target.painttool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;

public class PaintActivity extends AppCompatActivity implements View.OnClickListener {
    PaintView paintView;
    Button black, white, red, green, yellow, blue, violet, gray, undo;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        toolbar = findViewById(R.id.tool_bar);
        paintView = findViewById(R.id.paint_view);
        toolbar.setTitle(getIntent().getStringExtra("title"));
        toolbar.setBackgroundColor(getIntent().getIntExtra("TBC", Color.WHITE));
        paintView.setBackgroundColor(getIntent().getIntExtra("BGC", Color.WHITE));

        black = findViewById(R.id.black);
        white = findViewById(R.id.whith);
        red = findViewById(R.id.red);
        gray = findViewById(R.id.gray);
        green = findViewById(R.id.green);
        yellow = findViewById(R.id.yellow);
        blue = findViewById(R.id.blue);
        violet = findViewById(R.id.violet);
        undo = findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.deleteLastMove();
            }
        });
        black.setOnClickListener(this);
        white.setOnClickListener(this);
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        yellow.setOnClickListener(this);
        gray.setOnClickListener(this);
        violet.setOnClickListener(this);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.save)
                    saveMyWork();
                if (item.getItemId() == R.id.cancel)
                    cancel();
                return true;
            }
        });
    }

    private void saveMyWork() {
        Intent resultIntent = new Intent();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        paintView.getBitmapFromView().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        resultIntent.putExtra("result", byteArray);
        setResult(PaintTool.DRAW_RESULT, resultIntent);
        finish();
    }

    private void cancel() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.black) {
            paintView.setPaintColor(Color.BLACK);
        } else if (v.getId() == R.id.whith) {
            paintView.setPaintColor(Color.WHITE);
        } else if (v.getId() == R.id.red) {
            paintView.setPaintColor(getResources().getColor(R.color.red, null));
        } else if (v.getId() == R.id.green) {
            paintView.setPaintColor(getResources().getColor(R.color.green, null));
        } else if (v.getId() == R.id.yellow) {
            paintView.setPaintColor(getResources().getColor(R.color.yellow, null));
        } else if (v.getId() == R.id.blue) {
            paintView.setPaintColor(getResources().getColor(R.color.blue, null));
        } else if (v.getId() == R.id.gray) {
            paintView.setPaintColor(getResources().getColor(R.color.gray, null));
        } else if (v.getId() == R.id.violet) {
            paintView.setPaintColor(getResources().getColor(R.color.violet, null));
        }
    }


}

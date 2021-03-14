package com.target.edittool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EditView extends androidx.appcompat.widget.AppCompatImageView {
    ArrayList<Path> paths = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();
    Path currentPath = new Path();
    int currentColor;
    Paint tool = new Paint();
    float x;
    float y;
    Context mContext;

    public EditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackgroundColor(Color.WHITE);
        tool.setColor(Color.BLACK);
        tool.setStyle(Paint.Style.STROKE);
        tool.setStrokeWidth(8f);
        currentColor = Color.BLACK;
    }

    public void setPaintColor(int color) {
        tool.setColor(color);
        currentColor=color;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentPath = new Path();
            currentColor = tool.getColor();
            currentPath.moveTo(x, y);
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            currentPath.lineTo(x, y);
            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            paths.add(currentPath);
            colors.add(currentColor);
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            tool.setColor(colors.get(i));
            canvas.drawPath(paths.get(i), tool);
        }
        tool.setColor(currentColor);
        canvas.drawPath(currentPath, tool);
    }

    public Bitmap getBitmapFromView() {
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.draw(canvas);
        return bitmap;
    }

    public void deleteLastMove() {
        if (!paths.isEmpty()) {
            currentPath = new Path();
            paths.remove(paths.size() - 1);
            colors.remove(colors.size()-1);
            invalidate();
        }
    }
}

package com.example.crossandzero.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Zero extends AbstractClass {

    private float midX;
    private float midY;

    private boolean check = true;
    private int k;

    private float radius;
    private Paint paint = new Paint();

    public Zero(float x, float y, GameView view, Matrix matrix, Matrix matrixX, Matrix matrixY){
        super(view, matrix, matrixX, matrixY);
        midX = findMidX(x);
        midY = findMidY(y);

        check = toMatrix(-1);
        k = winCheck();
        radius = view.getWidth()/12 - 25;
    }


    @Override
    public boolean getCheck() {
        return check;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        if ((midX != 0) && (midY != 0)&&check){
            canvas.drawCircle(midX,midY,radius,paint);
        }
        ifWin(canvas,k);
    }


}

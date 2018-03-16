package com.example.crossandzero.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Cross extends AbstractClass {

    private float midX;
    private float midY;

    private boolean check = true;

    private float k;
    private int z;

    private Paint paint = new Paint();

    public Cross(float x, float y, GameView view, Matrix matrix, Matrix matrixX, Matrix matrixY) {
        super(view,matrix, matrixX, matrixY);
        midX = findMidX(x);
        midY = findMidY(y);

        check = toMatrix(1);
        z = winCheck();

        float width = view.getWidth();
        k = width /12 - 30;
    }

    @Override
    public boolean getCheck() {
        return check;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);
        if ((midX != 0) && (midY != 0)&&check){
            canvas.drawLine(midX,midY,midX + k,midY - k,paint);
            canvas.drawLine(midX,midY,midX - k,midY - k,paint);
            canvas.drawLine(midX,midY,midX - k,midY + k,paint);
            canvas.drawLine(midX,midY,midX + k, midY + k,paint);
        }
        ifWin(canvas,z);
    }
}

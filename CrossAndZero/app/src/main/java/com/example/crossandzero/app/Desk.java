package com.example.crossandzero.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by женя on 11.07.2015.
 */
public class Desk {

    private float height;
    private float width;

    private Paint paint = new Paint();

    public Desk(GameView view){
        height = view.getHeight();
        width = view.getWidth();
    }

    public void onDraw(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(width/4, height/2 - width/4, width*3/4, height/2 + width/4, paint);
        paint.setStrokeWidth(10);
        canvas.drawLine(width*5/12, height/2 - width/4, width*5/12, height/2 + width/4, paint);
        canvas.drawLine(width*7/12, height/2 - width/4, width*7/12, height/2 + width/4, paint);
        canvas.drawLine(width/4, height/2 - width/12, width*3/4, height/2 - width/12, paint);
        canvas.drawLine(width/4, height/2 + width/12, width*3/4, height/2 + width/12, paint);
    }
}

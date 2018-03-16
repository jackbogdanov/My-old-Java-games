package com.example.crossandzero.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameManager gameManager;

    private Desk desk;
    private int priority = 1;

    private Matrix matrix;
    private Matrix matrixOfCenterX;
    private Matrix matrixOfCenterY;

    private ArrayList<Zero> zeros = new ArrayList<Zero>();
    private ArrayList<Cross> crosses = new ArrayList<Cross>();

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        matrix = new Matrix();
        matrixOfCenterX = new Matrix();
        matrixOfCenterY = new Matrix();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (priority % 2 == 0){
            Zero zero = new Zero(event.getX(), event.getY(), this, matrix, matrixOfCenterX, matrixOfCenterY);
            if (zero.getCheck()){
                zeros.add(zero);
                priority++;
            }

        } else {
            Cross cross = new Cross(event.getX(), event.getY(), this, matrix, matrixOfCenterX, matrixOfCenterY);

            if (cross.getCheck()){
                crosses.add(cross);
                priority++;
            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw (Canvas canvas){
        canvas.drawColor(Color.BLACK);
        desk.onDraw(canvas);
        for (int i = 0; i < zeros.size(); i++) {
                zeros.get(i).onDraw(canvas);

        }
        for (int i = 0; i < crosses.size(); i++) {
            crosses.get(i).onDraw(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameManager = new GameManager(getHolder(), this);
        desk = new Desk(this);
        gameManager.setRunning(true);
        gameManager.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameManager.setRunning(false);
        while (retry) {
            try {
                gameManager.join();
                retry = false;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

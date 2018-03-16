package com.example.crossandzero.app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameManager extends  Thread {
    static  final  long FPS = 13;

    private boolean running;

    private GameView view;

    private SurfaceHolder holder;

    public GameManager(SurfaceHolder holder, GameView view ){
        this.holder = holder;
        this.view = view;
    }

    public void setRunning(boolean run){
        running = run;
    }

    @Override
    public void run() {
        Canvas canvas;

        long ticksPS = 1000/FPS;
        long startTime;
        long sleepTime;

        while (running){
            canvas = null;
            startTime = System.currentTimeMillis();
            try {
                canvas = holder.lockCanvas(null);
                if (canvas == null) continue;
                view.onDraw(canvas);
            } finally {
                if (canvas != null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try {
                if (sleepTime > 0) sleep(sleepTime);
                else sleep(10);
            } catch (Exception e){}
        }
    }
}

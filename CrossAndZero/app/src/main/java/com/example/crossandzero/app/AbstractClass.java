package com.example.crossandzero.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class AbstractClass {

    private float height;
    private float width;

    private Paint paint;
    private Matrix matrix;

    private Matrix matrixOfCenterX;
    private Matrix matrixOfCenterY;

    private int column = -1;
    private int line = -1;

    private float centerX = -1;
    private float centerY = -1;

    public AbstractClass(GameView view, Matrix matrix, Matrix matrixX, Matrix matrixY){
        height = view.getHeight();
        width = view.getWidth();

        paint = new Paint();

        matrixOfCenterX = matrixX;
        matrixOfCenterY = matrixY;
        this.matrix = matrix;
    }

    public float findMidX(float x){
        if ((x > width/4) && (x < width*5/12)){
            column = 0;
            centerX = (width/4 + width*5/12)/2;
            return centerX;
        }

        if ((x > width*5/12) && (x < width*7/12)){
            column = 1;
            centerX = (width*5/12 + width*7/12)/2;
            return centerX;
        }

        if ((x > width*7/12) && (x < width*3/4)){
            column = 2;
            centerX = (width*7/12 + width*3/4)/2;
            return centerX;
        }

        column = -1;
        centerX = -1;
        return 0;
    }

    public float findMidY(float y){
        if ((y > height/2-width/4) && (y < height/2 - width/12)){
            line = 0;
            centerY = (height/2-width/4 + height/2-width/12)/2;
            return centerY;
        }

        if ((y > height/2 - width/12) && (y < height/2 + width/12)){
            line = 1;
            centerY = (height/2 - width/12 + height/2 + width/12)/2;
            return centerY;
        }

        if ((y > height/2 + width/12) && (y < height/2 + width/4)){
            line = 2;
            centerY = (height/2 + width/12 + height/2 + width/4)/2;
            return centerY;
        }

        line = -1;
        centerY = -1;
        return 0;
    }

    public boolean toMatrix(int z){
        if ((column != -1) && (line != -1) && (centerX != 0) && (centerY != 0)){
            if (matrix.getMatrix(column, line) == 0) {
                matrix.setMatrix(column, line,z);
                matrixOfCenterX.setMatrix(column,line,centerX);
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <=2 ; j++) {
                        System.out.println(matrixOfCenterX.getMatrix(i,j));
                    }
                }
                matrixOfCenterY.setMatrix(column,line,centerY);
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    public  int winCheck(){
        int k = 0;
        for (int i = 0; i <= 2; i++) {
               switch (i){
                   case 0:
                       for (int j = 0; j <= 2; j++) {
                           if (matrix.getMatrix(i,j) != 0){
                               k+=matrix.getMatrix(i,j);
                           }
                           if ((k == 3)||(k == -3)) return 113*3/k;
                       }
                       k = 0;
                   break;
                   case 1:
                       for (int j = 0; j <= 2; j++) {
                           if (matrix.getMatrix(i,j) != 0){
                               k+= matrix.getMatrix(i,j);
                           }
                           if ((k == 3)||(k == -3)) return 213*3/k;
                       }
                       k = 0;
                   break;

                   case 2:
                       for (int j = 0; j <= 2; j++) {
                           if (matrix.getMatrix(i,j) != 0){
                               k+=matrix.getMatrix(i,j);
                           }
                           if ((k == 3)||(k == -3)) return 313*3/k;
                       }
                       k = 0;
                   break;
               }
        }

//        for (int j = 0; j <= 2; j++) {
//            switch (j){
//                case 0:
//                    for (int i = 0; i < 2; i++) {
//                        if (matrix.getMatrix(i,j) == 1){
//                            k++;
//                        }
//                        if ((k == 3)||(k == -3)) return 31*3/k;
//                    }
//                    k = 0;
//                break;
//                case 1:
//                    for (int i = 0; i < 2; i++) {
//                        if (matrix.getMatrix(j,i) == 1){
//                            k++;
//                        }
//                        if ((k == 3)||(k == -3)) return 32*3/k;
//                    }
//                    k = 0;
//                break;
//
//                case 2:
//                    for (int i = 0; i < 2; i++) {
//                        if (matrix.getMatrix(j,i) == 1){
//                            k++;
//                        }
//                        if ((k == 3)||(k == -3)) return 33*3/k;
//                    }
//                    k = 0;
//                break;
//            }
//        }
        return 0;
    }

    public void ifWin(Canvas canvas, int k){
        if (k != 0){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(4);
            if (k < 0){
                canvas.drawText("Zero win", height/6, width/2, paint);
            } else {
                canvas.drawText("Cross win", height/6, width/2, paint);
            }

            k = Math.abs(k);
            int column = k/100 - 1;
            int lineEnd = k%10-1;
            k = k/10;
            int lineBegin = k%10-1;
            canvas.drawLine(matrixOfCenterX.getMatrix(column,lineBegin),matrixOfCenterY.getMatrix(column,lineBegin), matrixOfCenterX.getMatrix(column,lineEnd), matrixOfCenterY.getMatrix(column,lineEnd),paint);
        }
    }

    public abstract boolean getCheck();
    public abstract void onDraw(Canvas canvas);
}

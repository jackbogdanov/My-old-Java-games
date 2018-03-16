package com.example.crossandzero.app;

public class Matrix {
    public float[][] matrix = new float[3][3];

    public Matrix(){
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                matrix[i][j]=0;
            }
        }
    }

    public void setMatrix(int x, int y, float value){
        matrix[x][y]=value;
    }

    public float getMatrix(int x, int y){
        return matrix[x][y];
    }
}

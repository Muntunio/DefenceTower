package com.mygdx.tower_defence.bullet.model;

import java.awt.*;


public class CoefficientQuadratic {
    public static double [] threePoint(Point start,Point between,Point end){

        double [] y = createYMatrix(start.y,between.y,end.y);
        double [][] x = createXMatrix(start.x,between.x,end.x);
        x = invert(x);

        return multiplyMatrix(x,y);
    }

    private static double[] multiplyMatrix(double[][] x, double[] y) {
        double [] coefficient = new double[3];

        double sum=0;
        for(int i=0; i<x.length; ++i){
            sum = 0;
            for(int j=0; j<y.length; ++j){
                sum += x[i][j]*y[j];
            }
            coefficient[i] = sum;
        }

        return coefficient;
    }


    private static double[] createYMatrix(double y0, double y1, double y2) {
        double [] y = new double[3];
        y[0] = y0;
        y[1] = y1;
        y[2] = y2;
        return y;
    }

    private static double [][] createXMatrix(double x0, double x1, double x2) {
        double [][] xx = new double[3][3];
        xx[0][0] = x0*x0;
        xx[0][1] = x0;
        xx[0][2] = 1;

        xx[1][0] = x1*x1;
        xx[1][1] = x1;
        xx[1][2] = 1;

        xx[2][0] = x2*x2;
        xx[2][1] = x2;
        xx[2][2] = 1;

        return xx;
    }

    private static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];

        for (int i=0; i<n; ++i)
            b[i][i] = 1;


        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    private static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];
        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row

        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;

        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)

            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;

            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }

    }



}

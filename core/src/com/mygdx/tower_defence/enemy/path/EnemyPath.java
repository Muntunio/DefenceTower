package com.mygdx.tower_defence.enemy.path;

import java.awt.*;

public class EnemyPath {

    private Point[] path_in_step;
    private double interpolation_step;
    private int steps;

    public EnemyPath(double [] map_point_x, double [] map_point_y) {
        interpolation_step = 2.0;
        double [] space = calculateSpaceBetweenMapPoint(map_point_x, map_point_y);
        double [] space_sum = calculateSpaceSum(space);
        steps = (int) (space_sum[space_sum.length-1]/2)+1;
        path_in_step = new Point [steps];
        calculatePathStep(map_point_x, map_point_y,space_sum);
    }


    private double[] calculateSpaceBetweenMapPoint(double[] map_point_x, double[] map_point_y) {
        double [] space = new double[map_point_x.length];

        space[0] = 0.0;
        for(int i=1; i<map_point_x.length; ++i){
            space[i] = Math.sqrt(Math.pow(map_point_x[i]-map_point_x[i-1],2)+
                    Math.pow(map_point_y[i]-map_point_y[i-1],2));
        }
        return space;
    }

    private double[] calculateSpaceSum(double[] space) {
        double [] space_sum = new double[space.length];
        space_sum [0] = space[0];
        for(int i=1; i<space.length; ++i)
            space_sum[i] = space[i] + space_sum[i-1];

        return space_sum;
    }

    private void calculatePathStep(double[] map_point_x, double[] map_point_y, double[] space_sum) {
        CubicSplineFast interpolationX = new CubicSplineFast(space_sum,map_point_x);
        CubicSplineFast interpolationY = new CubicSplineFast(space_sum,map_point_y);

        int x,y;

        for(int i=0; i<steps; ++i){
            x = (int) Math.floor(interpolationX.interpolate(i*interpolation_step)+0.5);
            y = (int) Math.floor(interpolationY.interpolate(i*interpolation_step)+0.5);
            path_in_step[i] = new Point(x,y);
        }
    }

    public Point[] getPathInStep() { return path_in_step; }
    public Point getPointStep(int i) { return path_in_step[i]; }
    public int getCountOfSteps() {return path_in_step.length; }
}

package com.mygdx.tower_defence.map.model;

import com.mygdx.tower_defence.enemy.EnemyType;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ReadFileMapSetting {

    private Point[] path_points;
    private Queue<EnemyWave> enemy_wave_queue;
    private Point[] tower_pivot;

    public ReadFileMapSetting(String file_name) { readFromFile(file_name); }

    private void readFromFile(String file_name) {
        int type_information = 0;
        int i = 0;


        File file_map = new File(file_name);
        try {
            Scanner scanner = new Scanner(file_map);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.charAt(0) == '#'){
                    ++type_information;
                    init_type(type_information,Integer.parseInt(line.substring(1)));
                    i=0;
                    continue;
                }
                convertInformation(i,line,type_information);
                ++i;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init_type(int type_information, int line_in_collection) {
        switch (type_information){
            case 1: //path coords
                path_points = new Point[line_in_collection];
                break;
            case 2: //queue wave mob
                enemy_wave_queue = new LinkedList<>();
                break;
            case 3: //position pivot tower
                tower_pivot = new Point[line_in_collection];

        }
    }

    private void convertInformation(int i, String line, int type_information) {
        String [] values;
        switch (type_information){
            case 1: //path coords
                values = line.split(",");
                path_points[i] = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
                break;
            case 2: //queue wave mob
                values = line.split(",");
                addWaveToQueue(values);
                break;
            case 3:
                values = line.split(",");
                tower_pivot[i] = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
                break;
        }
    }

    private void addWaveToQueue(String[] values) {
        int count_mob = Integer.parseInt(values[1]);
        int delay_mob = Integer.parseInt(values[2]);
        int delay_wave = Integer.parseInt(values[3]);

        switch (values[0]){
            case "RED":
                enemy_wave_queue.add(new EnemyWave(EnemyType.RED,count_mob,delay_mob,delay_wave));
                break;
            case "GREEN":
                enemy_wave_queue.add(new EnemyWave(EnemyType.GREEN,count_mob,delay_mob,delay_wave));
                break;
            case "MESH":
                enemy_wave_queue.add(new EnemyWave(EnemyType.MESH,count_mob,delay_mob,delay_wave));
                break;
            default:
                throw new IllegalArgumentException("Unknown enemy type in file "+values[0]);

        }
    }

    public Point[] getPathPoints() { return path_points; }

    public Queue<EnemyWave> getEnemyWaveQueue() { return enemy_wave_queue; }

    public Point[] getTowerPivot() { return tower_pivot; }
}

package com.mygdx.tower_defence.map.model;

import com.mygdx.tower_defence.enemy.EnemyType;

public class EnemyWave {
    private final EnemyType enemy_type;
    private final int count_of_mob;
    private final int delay_between_mobs;
    private final int delay_between_pre_wave;

    public EnemyWave(EnemyType enemy_type,int count_of_mob, int delay_between_mobs, int delay_between_pre_wave) {
        this.enemy_type = enemy_type;
        this.count_of_mob = count_of_mob;
        this.delay_between_mobs = delay_between_mobs;
        this.delay_between_pre_wave = delay_between_pre_wave;
    }


    public EnemyType getEnemyType() { return enemy_type; }

    public int getCountOfMob() { return count_of_mob; }

    public int getDelayBetweenMobs() { return delay_between_mobs; }

    public int getDelayBetweenPreWave() { return delay_between_pre_wave; }
}

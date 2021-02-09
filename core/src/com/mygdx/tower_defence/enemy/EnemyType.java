package com.mygdx.tower_defence.enemy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnemyType {
    GREEN,
    MESH,
    RED;

    private static final List<EnemyType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EnemyType randomEnemyType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

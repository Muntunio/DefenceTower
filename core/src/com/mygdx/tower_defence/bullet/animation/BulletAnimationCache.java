package com.mygdx.tower_defence.bullet.animation;

import java.util.HashMap;
import java.util.Map;

public class BulletAnimationCache {
    /*
     * Implementation cache/flyweight design pattern
     * Store a SINGLE instance of BulletAnimation
     */

    private static Map<BulletAnimationType, BulletAnimation> bulletAnimations = new HashMap<>();

    public static BulletAnimation getAnimation(BulletAnimationType type){
        BulletAnimation result = bulletAnimations.get(type);

        if(result == null){
            result = BulletAnimationFactory.createBulletAnimation(type);
            bulletAnimations.put(type,result);
        }
        return result;
    }
}

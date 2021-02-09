package com.mygdx.tower_defence.bullet.animation;

import com.mygdx.tower_defence.bullet.animation.type.DirtBulletAnimation;
import com.mygdx.tower_defence.bullet.animation.type.FireBulletAnimation;
import com.mygdx.tower_defence.bullet.animation.type.IronBulletAnimation;
import com.mygdx.tower_defence.bullet.animation.type.StoneBulletAnimation;

public class BulletAnimationFactory {
    public static BulletAnimation createBulletAnimation(BulletAnimationType type){

        switch (type){
            case DIRT:
                return new DirtBulletAnimation();
            case FIRE:
                return new FireBulletAnimation();
            case IRON:
                return new IronBulletAnimation();
            case STONE:
                return new StoneBulletAnimation();
            default:
                throw new IllegalArgumentException("Unknown bullet animation type"+type);
        }
    }
}

package com.m3z0id.gunGame.config.subclasses.config;

import org.bukkit.potion.PotionEffectType;

public class Effect {
    PotionEffectType effect;
    Integer level;
    Integer duration;

    public Effect() {

    }

    public PotionEffectType getEffect() {
        return effect;
    }
    public int getLevel() {
        if(level == null) return 1;
        return Math.abs(level);
    }
    public int getDuration() {
        if(duration == null) return 60;
        return Math.abs(duration);
    }
}

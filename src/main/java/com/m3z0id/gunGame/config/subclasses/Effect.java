package com.m3z0id.gunGame.config.subclasses;

import org.bukkit.potion.PotionEffectType;

public class Effect {
    PotionEffectType effect;
    int level;
    int duration;

    public Effect() {

    }

    public PotionEffectType getEffect() {
        return effect;
    }
    public int getLevel() {
        return level;
    }
    public int getDuration() {
        return duration;
    }
}

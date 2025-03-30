package com.m3z0id.gunGame.config;

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
    public void setEffect(PotionEffectType effect) {
        this.effect = effect;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}

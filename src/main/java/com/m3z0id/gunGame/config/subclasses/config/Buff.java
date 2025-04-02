package com.m3z0id.gunGame.config.subclasses.config;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Buff {
    int fromLevel;
    Integer toLevel;
    PotionEffectType effect;
    Integer amplifier;
    Boolean showParticles;

    public int getFromLevel() {
        return fromLevel;
    }
    public int getToLevel() {
        if(toLevel == null) return Integer.MAX_VALUE;
        return toLevel;
    }
    public PotionEffectType getEffect() {
        return effect;
    }
    public int getAmplifier() {
        if(amplifier == null) return 1;
        return amplifier;
    }
    public Boolean getShowParticles() {
        if(showParticles == null) return false;
        return showParticles;
    }
    public PotionEffect getPotionEffect() {
        return effect.createEffect(Integer.MAX_VALUE, Math.abs(getAmplifier()-1));
    }
}

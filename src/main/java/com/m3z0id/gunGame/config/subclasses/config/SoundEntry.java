package com.m3z0id.gunGame.config.subclasses.config;

import org.bukkit.Sound;

public class SoundEntry {
    private Sound sound;
    private Float volume;
    private Float pitch;

    public Sound getSound() {
        return sound;
    }
    public float getVolume() {
        if (volume == null || volume == 0) return 1.0F;
        return Math.abs(volume);
    }
    public float getPitch() {
        if (pitch == null || pitch == 0) return 1.0F;
        return Math.abs(pitch);
    }
}

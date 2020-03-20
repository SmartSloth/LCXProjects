package ProgrammingAssignment9;

import sound.Playback;
import sound.SoundSample;
import sound.SimpleSound;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//
// Decompiled by Procyon v0.5.36
//

public class Sound
{
    public int[] sound;
    // 这个是我加的，我需要一个采样率参数
    public int sampleRate;

    /**
     * sound是一组长度为采样点个数，每个元素大小为采样点大小的整型数组
     * @param path
     */
    public Sound(String path){
        SimpleSound sound = new SimpleSound(path);
        // getSamples()返回的是数组，数组类型是SoundSample，声音的一个个样点
        SoundSample[] ss = sound.getSamples();

        // 查看采样率
        // 采样率默认是44100，这个是为了PA9中的验证有的是sample直接数组写出来的，没有采样率
        // 如果用wav格式的音乐就有采样率了，走的就是else中的赋值
        if((int)sound.getSamplingRate() == 0){
            this.sampleRate = 44100;
        }
        else {
            this.sampleRate = (int)sound.getSamplingRate();
        }
        int rate = (int)sound.getSamplingRate();
        if(rate == 22050) {
            int[] samples = new int[ss.length];
            for(int i = 0; i < ss.length; i += 1) {
                // 将每个样点的样本大小赋值
                samples[i] = ss[i].getValue();
            }
            this.sound = samples;
        }
        else if(rate == 44100) {
            int[] samples = new int[ss.length / 2];
            for(int i = 0; i < samples.length - 1; i += 2) {
                samples[i] = ss[i * 2].getValue();
            }
            this.sound = samples;
        }
        else {
            throw new RuntimeException("Unknown sampling rate from audio file: " + rate);
        }

    }

    public Sound(int[] soundIn){
        this.sound = soundIn;
    }

    void saveSound(int[] sound, String destinationPath) {
        SimpleSound s = new SimpleSound(sound);
        s.write(destinationPath);
    }

    int[] readSoundAtNaturalRate(String path) {
        SimpleSound sound = new SimpleSound(path);
        SoundSample[] ss = sound.getSamples();
        int rate = (int)sound.getSamplingRate();
        int[] samples = new int[ss.length];
        for(int i = 0; i < ss.length; i += 1) {
            samples[i] = ss[i].getValue();
        }
        return samples;
    }

    boolean play() {
        new SimpleSound(sound).play();
        return true;
    }


    boolean blockingPlay() {
        new SimpleSound(sound).blockingPlay();
        return true;
    }


    boolean stopMusic() {
        for(Playback p: SimpleSound.globalPlaybacks) {
            p.stopPlaying();
        }
        return true;
    }

    public boolean explore() {
        new SimpleSound(sound).explore();
        return true;
    }

    public int[] getSamples() {
        return this.sound;
    }

    public boolean equals(Object other) {
        if(!(other instanceof Sound)) { return false; }
        Sound s = (Sound) other;
        return Arrays.equals(this.sound, s.sound);
    }
}


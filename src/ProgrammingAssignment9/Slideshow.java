package ProgrammingAssignment9;

import java.awt.Color;
import java.util.Arrays;

class Slideshow{
    public Image[] SlideImages;
    public Sound[] SlideSounds;

    public Slideshow(Image[] SlideImages, Sound[] SlideSounds){
        this.SlideImages = SlideImages;
        this.SlideSounds = SlideSounds;
    }

    public int duration(){
        int dur = 0;
        for(int i = 0; i < SlideSounds.length; i++){
            // 声音长度 = 采样点数 / 采样率
            System.out.println("采样数：" + SlideSounds[i].getSamples().length + "   采样率：" + SlideSounds[i].sampleRate);
            dur = dur + (SlideSounds[i].getSamples().length / (int)(SlideSounds[i].sampleRate));
        }
        return dur;
    }

    public Slideshow concat(Slideshow other){
        // 复制加缀连声音数组
        Sound newSlideSounds[] = new Sound[this.SlideSounds.length + other.SlideSounds.length];
        for(int i = 0; i < SlideSounds.length; i++){
            newSlideSounds[i] = this.SlideSounds[i];
        }
        for(int i = SlideSounds.length; i < this.SlideSounds.length + other.SlideSounds.length; i++){
            newSlideSounds[i] = other.SlideSounds[i - SlideSounds.length];
        }
        // 复制加缀连图像数组
        Image newSlideImages[] = new Image[this.SlideImages.length + other.SlideImages.length];
        for(int i = 0; i < SlideImages.length; i++){
            newSlideImages[i] = this.SlideImages[i];
        }
        for(int i = SlideImages.length; i < this.SlideImages.length + other.SlideImages.length; i++){
            newSlideImages[i] = other.SlideImages[i - SlideSounds.length];
        }
        return new Slideshow(newSlideImages, newSlideSounds);
    }

    public void play(){
        for(int i = 0; i < this.SlideSounds.length; i++){
            // 当前系统时间以毫秒为单位
            // 其实感觉这个计时方法很蠢，有高级的尽可以替换
            long startTime =  System.currentTimeMillis();
            int duringTime = (SlideSounds[i].sound.length / (int)(SlideSounds[i].sampleRate));
            long endTime = startTime + duringTime * 1000;
            System.out.println((i + 1) + "slide, startTime is " + startTime + ", " +
                    "duringTime is " + duringTime + ", endTime is " + endTime);
            if(System.currentTimeMillis() <= endTime){
                System.out.println("Image" + i + " height is " + SlideImages[i].getHeight());
                SlideImages[i].show();
                SlideSounds[i].play();
            }
            System.out.println((i + 1) + "slide, endTime is " + endTime);
            SlideSounds[i].blockingPlay();
            continue;
        }
    }
}
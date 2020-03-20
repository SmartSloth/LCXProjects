package ProgrammingAssignment9;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

class PA9 {

    static void testDuration(){
        // You can copy an example from the PA description to here when ready
        Color[][] pix = {{new Color(0, 0, 0)}};

        Image[] imgs = {new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image1.jpg"),
                new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image2.jpg")};

        int[] samples1 = new int[100000];
        int[] samples2 = new int[25000];
        Sound[] sounds = {new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound1.wav"),
                new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound2.wav")};

        Slideshow s = new Slideshow(imgs, sounds);
        System.out.println("Expect 5 total seconds: " + s.duration());
    }

    static void testConcat() {
        // You can copy an example from the PA description to here when ready
        Color[][] pix = {{new Color(0, 0, 0)}};
//        Image blackDot = new Image(pix);
        Image image1 = new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image1.jpg");
        Color[][] pixBlue = {{new Color(0, 0, 255)}};
//        Image blueDot = new Image(pixBlue);
        Image image2 = new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image2.jpg");

        Image[] imgs1 = {image1, image1, image2};
        Image[] imgs2 = {image2, image1};

        int[] samples1 = new int[1000000];
        int[] samples2 = new int[250000];
        Sound s1 = new Sound(samples1);
//        Sound s1 = new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound1.wav");
        Sound s2 = new Sound(samples2);
//        Sound s2 = new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound2.wav");

        Sound[] sounds1 = {s1, s2, s1};
        Sound[] sounds2 = {s2, s2};

        Slideshow ss1 = new Slideshow(imgs1, sounds1);
        Slideshow ss2 = new Slideshow(imgs2, sounds2);

        Slideshow ss3 = ss1.concat(ss2);

        Sound[] expectSounds = {s1, s2, s1, s2, s2};
//        Image[] expectImages = {blackDot, blackDot, blueDot, blueDot, blackDot};
        Image[] expectImages = {image1, image1, image2, image2, image1};

        // 参考答案和运行答案的对比
        System.out.println("Should be true: " + Arrays.equals(expectSounds, ss3.SlideSounds));
        System.out.println("Should be true: " + Arrays.equals(expectImages, ss3.SlideImages));

    }

    static void demoSlideshow() {
        // Here you will create a slide show from files and demo it
        Image image1 = new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image1.jpg");
        Image image2 = new Image("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\image2.jpg");
        Image[] imgs1 = {image1, image1, image2};

        // 说明一下，用地址找音乐播放的话时间太长了，因为wav无损格式的短铃声太难找，我随便用了几个
        // 如果只是看效果的话用samples生成的就行（其实就是一段生成的时间）可以用来看效果
        int[] samples1 = new int[1000000];
        int[] samples2 = new int[250000];
        Sound s1 = new Sound(samples1);
//        Sound s1 = new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound1.wav");
        Sound s2 = new Sound(samples2);
//        Sound s2 = new Sound("D:\\Documents\\IdeaProjects\\LCXProjects\\src\\ProgrammingAssignment9\\sound2.wav");
        Sound[] sounds1 = {s1, s2, s1};

        Slideshow ss1 = new Slideshow(imgs1, sounds1);
        // 幻灯片显示实验，其实稍微慢了点不知道是代码问题还是运行问题
        ss1.play();
    }

    public static void main(String[] args){
        testDuration();
        testConcat();
        demoSlideshow();
    }

}

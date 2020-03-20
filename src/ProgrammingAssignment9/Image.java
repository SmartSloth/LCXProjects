package ProgrammingAssignment9;

import image.Pixel;
import image.SimplePicture;
import java.awt.Color;
import image.PictureFrame;
import java.lang.Thread;
import java.util.Arrays;
/**
 * Simple class used to represent an image.
 *
 * Internally, it stores the image data as a 2D array of Color objects where
 * each element in the array represents one pixel in the given image object.
 */
public class Image {

    // Member variable used to store the pixels of the Image object
    private Color[][] pixels;
    static PictureFrame pf = null;
    // Member variables used to store the width and height of the Image object
    private int width;
    private int height;

    /**
     * Constructor that creates a new Image object from the image file specified by
     * the given path.
     * 从给定路径指定的图像文件中创建新Image对象的构造方法。
     */
    public Image(String path) {
        // Use SimplePicture to parse file and convert Pixel object
        // to Color object for this Image object
        // 使用SimplePicture解析文件并将该Image对象的Pixel对象转换为Color对象
        SimplePicture pic = new SimplePicture(path);
        Pixel[] pixels = pic.getPixels();
        this.width = pic.getWidth();
        this.height = pic.getHeight();
        this.pixels = new Color[this.height][this.width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel p = pixels[i * width + j];
                this.pixels[i][j] = new Color(p.getRed(), p.getGreen(), p.getBlue());
            }
        }
    }

    /**
     * Constructor that creates a new Image object from the given pixels.
     * 从给定的像素创建一个新的Image对象的构造方法。
     */
    public Image(Color[][] pixels) {
        // Make a copy of the pixels array to avoid mutating this Image object
        // 复制像素数组，以避免使此Image对象发生变异
        this.width = pixels[0].length;
        this.height = pixels.length;
        this.pixels = new Color[this.height][this.width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.pixels[i][j] = pixels[i][j];
            }
        }
    }



    /**
     * Gets the distance between two colors
     * 获取两种颜色之间的距离
     */
    public double colorDistance(Color color1, Color color2) {
        int redDistance = color1.getRed()-color2.getRed();
        int greenDistance = color1.getGreen()-color2.getGreen();
        int blueDistance = color1.getBlue()-color2.getBlue();
        int totalDistance = redDistance * redDistance +
                greenDistance * greenDistance +
                blueDistance * blueDistance;
        return totalDistance / 1000;
    }

    /**
     * Visualizes this Image object in an interactive window.
     * 在交互式窗口中可视化此Image对象。
     */
    public void explore() {

        SimplePicture picToExplore = new SimplePicture(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                picToExplore.setBasicPixel(j, i, pixels[i][j].getRGB());
            }
        }
        picToExplore.explore();

    }

    public void show(){
        if(Image.pf == null) { Image.pf = new PictureFrame(); }

        SimplePicture picToExplore = new SimplePicture(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                picToExplore.setBasicPixel(j, i, pixels[i][j].getRGB());
            }
        }
        pf.setPicture(picToExplore);
        pf.displayImage();
    }


    /**
     * Returns the width of this Image object.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this Image object.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns a copy of the pixels of this Image object.
     * 返回此Image对象的像素的副本。
     */
    public Color[][] getPixels2D() {
        Color[][] copy = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = pixels[i][j];
            }
        }
        return copy;
    }

    /**
     * Returns a String representation of this Image object
     * 返回此Image对象的String表示形式
     */
    @Override
    public String toString() {
        String pixelRef = pixels.toString();
        String p = pixelRef.substring(pixelRef.indexOf("@"));
        return "Image[width=" + width + ", height=" + height + ", pixels=" + p + "]";
    }

    public boolean equals(Object other) {
        if(!(other instanceof Image)) { return false; }
        Image i = (Image) other;
        return Arrays.deepEquals(i.pixels, i.pixels);
    }

}

package PackWork;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Image implements BaseImage{
    protected BufferedImage img;
    protected int width;
    protected int height;
    protected int[][][] rgbBuf;

    public Image(BufferedImage img, int width, int height) {
        this.img = img;
        this.width = this.img.getWidth();
        this.height = this.img.getHeight();
    }

    public Image(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override

    public void GetBuffer() {
        rgbBuf = new int[3][img.getHeight()][img.getWidth()]; //se creeaza un bufer tridimensional in functie de dimensiunea imaginii
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < img.getWidth(); col++) {
                Color c = new Color(img.getRGB(col, row)); //se extrag culorile rgb din fiecare bit al imaginii
                rgbBuf[0][row][col] = c.getRed();
                rgbBuf[1][row][col] = c.getGreen();
                rgbBuf[2][row][col] = c.getBlue();
            }
        }
    }
}

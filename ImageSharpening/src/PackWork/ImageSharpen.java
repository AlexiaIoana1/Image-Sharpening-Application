package PackWork;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSharpen extends Image {
    private int r = 0;
    private int g = 0;
    private int b = 0;
    float[] kernelelem = { 0.0f, -1.0f, 0.0f,
				          -1.0f,  5.f, -1.0f,
				           0.0f, -1.0f,  0.0f};

    public ImageSharpen(BufferedImage img, int width, int height, int r, int g, int b) {
        super(img, width, height);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public ImageSharpen(BufferedImage img, int r, int g, int b) {
        super(img);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public ImageSharpen(BufferedImage img) {
        super(img);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    //functie cu numar variabil de parametrii
    //se calculeaza noua valoare a culorii rosu din fiecare bit
    public int getRed(int a, int ... b){
        int red = 0;
        for (int i: b) {
            red += (int) (a + 0.5 * (a - i));
            if (red > 255) red = 255;
            if (red < 0) red = 0;
        }
        return red;
    }

    //functie cu numar variabil de parametrii
    //se calculeaza noua valoare a culorii verde din fiecare bit
    public int getGreen(int a, int ... b){
        int green = 0;
        for (int i: b) {
            green += (int) (a + 0.5 * (a - i));
            if (green > 255) green = 255;
            if (green < 0) green = 0;
        }
        return green;
    }

    //functie cu numar variabil de parametrii
    //se calculeaza noua valoare a culorii albastru din fiecare bit
    public int getBlue(int a, int ... b){
        int blue = 0;
        for (int i: b) {
            blue += (int) (a + 0.5 * (a - i));
            if (blue > 255) blue = 255;
            if (blue < 0) blue = 0;
        }
        return blue;
    }



    public BufferedImage Sharpening(){
        super.GetBuffer();
        for(int row = 1; row < img.getHeight() - 2; row++){
            for(int col = 1; col < img.getWidth() - 2; col++) {
                //se extrag noile valori rgb pentru fiecare bit
                r = getRed(rgbBuf[0][row][col], rgbBuf[0][row - 1][col - 1]);
                g = getGreen(rgbBuf[1][row][col], rgbBuf[1][row - 1][col - 1]);
                b = getBlue(rgbBuf[2][row][col], rgbBuf[2][row - 1][col - 1]);

                Color c = new Color(r, g, b);
                img.setRGB(col, row, c.getRGB()); //se seteaza noii parametrii in fiecare bit al imaginii
            }
        }
        return this.img;
    }


}

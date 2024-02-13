package PackWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//clasa ce mosteneste o clasa abstracta
public class WriteImage extends WriteFile{
    //se scrie imaginea la adresa data ca parametru
    public void Write(String name_fdest, BufferedImage i) {
        BufferedImage img = i;
        // File f=null;
        try {
            System.out.println("Writing");
            File f = new File(name_fdest);
            ImageIO.write(img, "bmp", f);
            System.out.println("Writing complete");
        } catch (IOException e) {
            System.out.println("WritingError: "+e);
        }

    }
}


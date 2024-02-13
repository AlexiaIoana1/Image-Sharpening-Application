package PackWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.BlockingQueue;

public class ReadImage extends Thread {
    private final BlockingQueue<BufferedImage> buffer;
    private final String source;

    public ReadImage(BlockingQueue<BufferedImage> buffer, String source) {
        this.buffer = buffer;
        this.source = source;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = readImageFromFile(source);
            synchronized (buffer) {
                buffer.put(image); // Adaugă imaginea citită în buffer
                buffer.notifyAll(); // Notifică ImageProcessor că o imagine este disponibilă
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, failed to complete operation");
        } catch (IOException e) {
            System.out.println("Failed to read the image: " + e.getMessage());
        }
    }

    private BufferedImage readImageFromFile(String source) throws IOException {
        File file = new File(source);
        return ImageIO.read(file);
    }
}
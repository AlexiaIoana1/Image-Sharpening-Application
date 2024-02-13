package PackWork;

import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;

public class ImageProcessor implements Runnable {
    private final BlockingQueue<BufferedImage> inputBuffer;
    private final BlockingQueue<BufferedImage> outputBuffer;

    public ImageProcessor(BlockingQueue<BufferedImage> inputBuffer, BlockingQueue<BufferedImage> outputBuffer) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = inputBuffer.take();
            BufferedImage processedImage = processImage(image);
            outputBuffer.put(processedImage);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        	System.out.println("Thread was interrupted, failed to complete operation");
        		}
        	}
    private BufferedImage processImage(BufferedImage originalImage) {
        // Implementează logica de procesare a imaginii aici
        ImageSharpen sharpenFilter = new ImageSharpen(originalImage);
        return sharpenFilter.Sharpening();
    	}
    }
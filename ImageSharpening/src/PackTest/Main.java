package PackTest;

import PackWork.ReadImage;
import PackWork.ImageProcessor;
import PackWork.ImageSharpen;
import PackWork.WriteImage;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BufferedImage> readBuffer = new ArrayBlockingQueue<>(1);
        BlockingQueue<BufferedImage> processedBuffer = new ArrayBlockingQueue<>(1);
        String source, dest;

        if (args.length != 0) {
            source = args[0];
        } else {
            System.out.print("Dati adresa fisierului sursa: ");
            Scanner input = new Scanner(System.in);
            source = input.nextLine();
        }

     // Inițializeaza și pornește ReadImage
        ReadImage myImage = new ReadImage(readBuffer, source);
        myImage.start();

        // Inițializeaza și pornește ImageProcessor
        ImageProcessor processor = new ImageProcessor(readBuffer, processedBuffer);
        Thread processorThread = new Thread(processor);
        processorThread.start();

        // Așteapta finalizarea citirii și procesării
        myImage.join();
        processorThread.join();

        // Preia imaginea procesată
        BufferedImage imagine = processedBuffer.take();
        
        // Proceseaza imaginea
        long timpProcesare1 = System.nanoTime();
        ImageSharpen mySharpenedImage = new ImageSharpen(imagine);
        imagine = mySharpenedImage.Sharpening();
        long timpProcesare2 = System.nanoTime();
        System.out.print("Timpul necesar procesarii imaginii este: " + (double)(timpProcesare2 - timpProcesare1) / 1.0E9D + " secunde\n");

        // Determină destinația imaginii
        if (args.length == 2) {
            dest = args[1];
        } else {
            System.out.print("Dati adresa fisierului destinatie: ");
            Scanner output = new Scanner(System.in);
            dest = output.nextLine();
        }

        // Scrie imaginea procesată
        long timpScriere1 = System.nanoTime();
        WriteImage myWriteImage = new WriteImage();
        myWriteImage.Write(dest, imagine);
        long timpScriere2 = System.nanoTime();
        System.out.print("Timpul necesar scrierii imaginii este: " + (double)(timpScriere2 - timpScriere1) / 1.0E9D + " secunde\n");
    }
}
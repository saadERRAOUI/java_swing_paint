import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class editingWindow {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("mouse clicked");

        //creating a main frame as a root element
        JFrame mainFrame = new JFrame("Editing Window");
        //creating a panel to add to the frame
        ImagePanel panel = new ImagePanel("EditingWindow/media/example.jpg");
        //setting the panel layout
        panel.setLayout(new FlowLayout());
        //adding the panel to the frame
        mainFrame.add(panel);

        //setting default frame size and location,
        mainFrame.setSize(900, 900);
        mainFrame.setLocationRelativeTo(null);
        //setting jframe color
        panel.setBackground(new Color(153, 153, 153));
        //setting default close operation X mark
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //showing the frame
        //read image from folder
        BufferedImage mainImage = null;
        try {
            mainImage = ImageIO.read(new File("EditingWindow/media/example.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        mainFrame.setVisible(true);
        Pixel pixelPointer = new Pixel(new Color(0, 0, 0));

        //get RGB, the values in p are stored in bytes and are hard to easily manipulate
        // except if we used Color class


//        //test pixle class , turn everything in an image to black
//        TimeUnit.SECONDS.sleep(5);
//        Pixel pixelPointer = new Pixel(new Color(0, 0, 0));
//        for (int i = 0; i < mainImage.getWidth(); i++) {
//            for (int j = 0; j < mainImage.getHeight(); j++) {
//                pixelPointer.setCoordinates(i, j);
//                panel.updateImage(pixelPointer);
//            }
//            //panel.repaint();

    }

}



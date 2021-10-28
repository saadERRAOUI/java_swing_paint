import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class editingWindow {
    public static void main(String[] args) {
        //creating a main frame as a root element
        JFrame mainFrame = new JFrame("Editing Window");
        //creating a panel to add to the frame
        JPanel panel = new JPanel();
        //setting the panel layout
        panel.setLayout(new FlowLayout());
        //adding the panel to the frame
        mainFrame.add(panel);
        //setting default frame size an location,
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        //setting default close operation X mark
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //showing the frame
        mainFrame.setVisible(true);
        //read image from folder
        BufferedImage mainImage = null;
        try {
            mainImage = ImageIO.read(new File("EditingWindow/media/example.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //get RGB, the values in p are stored in bytes and are hard to easily manipulate
        // except if we used Color class
        //test pixle class , turn everything in an image to black
        int p = mainImage.getRGB(1, 1);
        Pixel pixelPointer = new Pixel(new Color(0, 0, 0));
        for (int i = 0; i < mainImage.getWidth(); i++) {
            for (int j = 0; j < mainImage.getHeight(); j++) {
                pixelPointer.setCoordinates(i, j);
                pixelPointer.updateImage(mainImage);
            }
        }
    }


}
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
        mainFrame.setSize(panel.getSize());
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        //setting jframe color
        panel.setBackground(new Color(153, 153, 153));
        //setting default close operation X mark
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //showing the frame
        //read image from folder
        BufferedImage mainImage = null;


        mainFrame.setVisible(true);


    }

}



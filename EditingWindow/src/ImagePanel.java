import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    ContinuousPaintingThread t;
    ConcurrentLinkedQueue<Point> queue = new ConcurrentLinkedQueue<>();

    public ImagePanel(String imagePath) {
        //Constructor , create an image panel from an image path
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //a listener that add a point o the queue if a mouse press is detected
                capturePoint(mouseEvent);
                //then we launch a continiousPainting thread
                t = new ContinuousPaintingThread((ImagePanel) mouseEvent.getSource());
                t.start();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                //when the mouse is released, we paint the queue last time and close the continiousPainting thread
                paintQueue();
                t.setExit(true);
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                //while the mouse is being dragged, new points get added to the queue
                capturePoint(mouseEvent);
            }
        });
    }

    public void capturePoint(MouseEvent mouseEvent) {
        // a function that capture a point from a mouse event
        //it creates a point from the mouse event X and Y
        Point tempPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
        queue.add(tempPoint);
    }

    public void setImage(BufferedImage image) {
        // a setter for the current image
        this.image = image;
        this.repaint();
    }

    public void paintCercle(int x, int y, int r, Color CercleColor) {
        // a function that paints a filled cercle using the params x, y , rayon and a color
        double i, angle, x1, y1;
        for (i = 0; i < 360; i += 5) {
            angle = i;
            x1 = r * Math.cos(angle * Math.PI / 180);
            y1 = r * Math.sin(angle * Math.PI / 180);
            //right low quarter j>=x & K>=y
            for (int j = x; j < (int) (x + x1); j++) {
                for (int k = y; k < (int) (y + y1); k++) {
                    this.updateImage(new Pixel(CercleColor, j, k));
                }
            }
            //left low quarter j<=x & K>=y
            for (int j = x; j > (int) (x + x1); j--) {
                for (int k = y; k < (int) (y + y1); k++) {
                    this.updateImage(new Pixel(CercleColor, j, k));
                }
            }
            //left top quarter j<=x & K<=y
            for (int j = x; j > (int) (x + x1); j--) {
                for (int k = y; k > (int) (y + y1); k--) {
                    this.updateImage(new Pixel(CercleColor, j, k));
                }
            }
            //right top quarter j<=x & K>=y
            for (int j = x; j < (int) (x + x1); j++) {
                for (int k = y; k > (int) (y + y1); k--) {
                    this.updateImage(new Pixel(CercleColor, j, k));
                }
            }
        }
    }

    public void updateImage(int x, int y, Color c) {
        // update change the color of an exact image pixle (x,y) to color C
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            //if x and y out of bonds don't do return
            //System.out.println("Point are out of bond")
            return;
        }
        image.setRGB(x, y, c.getRGB());
        //this.repaint repaints the current Jpanel on screen, ensuring that you have the latest version of the image and the component overall
        this.repaint();
    }

    public void updateImage(Point p, Color c) {
        //an overload of update image, takes Point instead of int x and int y
        updateImage(p.x, p.y, c);
    }

    public void updateImage(Pixel p) {
        //an overload of update image, takes a Pixel instead of the coordinates and the color
        //replace the image pixel with a pixel passed in params
        updateImage(p.getX(), p.getY(), p.getCurrentColor());
    }

    public BufferedImage getImage() {
        //getter of the buffered image
        return image;
    }

    public boolean isPointInQueue(Point P) {
        //test if the queue contains a point
        return queue.contains(P);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //override of the paintComponent of the JPanel, it draws the image on top of the Image panel
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); //
    }

    public void paintQueue() {
        // TODO: 30/10/2021 change the temp params here to real params (r and color)
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            paintCercle(p.x, p.y, 40, Color.BLUE);
        }
    }
}



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImagePanel extends JPanel {
    //a Jpanel responsible of controling panels
    private BufferedImage image;
    ContinuousPaintingThread t;
    PaintConcurrentLinkedQueue queue = new PaintConcurrentLinkedQueue();

    public ImagePanel(String imagePath) {
        //Constructor , create an image panel from an image path
        openImage(imagePath);
        this.setSize(new Dimension(image.getWidth(), image.getHeight()));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //a listener that add a point o the queue if a mouse press is detected
                capturePoint(mouseEvent);
                capturePoint(mouseEvent);
                //then we launch a continiousPainting thread
                t = new ContinuousPaintingThread((ImagePanel) mouseEvent.getSource());
                t.start();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                //when the mouse is released, we paint the queue last time and close the continiousPainting thread
                paintQueue(true);
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
        queue.addPoint(tempPoint);
    }

    public void openImage(String s) {
        //open & set image from string
        try {
            setImage(ImageIO.read(new File(s)));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setImage(BufferedImage image) {
        // a setter for the current image
        this.image = image;
        this.setSize(new Dimension(image.getWidth(), image.getHeight()));
        this.repaint();
    }

    public void paintLine(Point p1, Point p2, Color lineColor) {
        paintLine(p1.x, p1.y, p2.x, p2.y, lineColor);
    }

    public void paintLine(int x1, int y1, int x2, int y2, Color lineColor) {
        //paint line between two points (p1,p2) with the color LineColor
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(lineColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // TODO: 01/11/2021 change params from real variables

        BasicStroke b = new BasicStroke(50, BasicStroke.CAP_ROUND, 1);
        g2d.setStroke(b);
        g2d.drawLine(x1, y1, x2, y2);

    }


    public void paintCercle(int x, int y, int r, Color CercleColor) {
        //a function that paints a Cercle defined by it's center (x,y) and rayon, as well as it's color;
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(CercleColor);
        g2d.fillOval(x, y, r, r);

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

    public void paintQueue(boolean isPaintAll) {
        // TODO: 30/10/2021 change the temp params here to real params (r and color)
        // a function responsible for painting all elements in the Queue,
        if (isPaintAll) {
            while (!queue.isEmpty()) {
                {
                    Point p1 = queue.poll();
                    Point p2 = queue.poll();
                    paintLine(p1, p2, Color.BLUE);
                }
            }
        } else {
            while (queue.size() > 2) {
                {
                    Point p1 = queue.poll();
                    Point p2 = queue.peek();
                    paintLine(p1, p2, Color.BLUE);
                }
            }
        }

    }
}




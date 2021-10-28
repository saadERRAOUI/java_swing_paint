import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel {
    /// a class to hold the values of a specific pixel and modify it accordingly
    int x = 0;//width
    int y = 0;//height
    Color currentColor;

    Pixel(Color color) {
        //constructor that inisialise a pixle, from it's color,
        currentColor = color;
    }

    Pixel(BufferedImage sourceImage, int x, int y) {
        //constructor that inisialise a pixle, from it's source image, x and y coardinates
        this.x = x;
        this.y = y;
        currentColor = new Color(sourceImage.getRGB(x, y));
    }

    Pixel(Color color, int x, int y) {
        //constructor that inisialise a pixle, from it's color, x and y coardinates
        this.x = x;
        this.y = y;
        currentColor = color;
    }

    public void setCurrentColor(int r, int g, int b) {
        this.currentColor = new Color(r, g, b);
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void updateImage(BufferedImage sourceImage) {
        sourceImage.setRGB(x, y, currentColor.getRGB());
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PaintConcurrentLinkedQueue extends ConcurrentLinkedQueue<Point> {
    //a class that represents a ConcurrentLinked queue of type point
    public boolean addPoint(Point p) {
        add(p);
        return true;
    }


}

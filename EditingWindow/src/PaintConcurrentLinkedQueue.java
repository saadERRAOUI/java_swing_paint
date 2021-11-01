import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PaintConcurrentLinkedQueue extends ConcurrentLinkedQueue<Point> {
    public boolean writing = false;
    public boolean closedFlag = false;

    public boolean addPoint(Point p) {
        add(p);
        return true;

        /*System.out.println("----Point adding : " + p);

        Point lastpoint = this.peek();
        int step = 0;
        if (lastpoint == null) {
            System.out.println("---- null");

            add(p);
            return true;
        } else if (lastpoint.equals(p)) {
            System.out.println("---- equals");
            return false;
        }
//        else if (Math.abs(lastpoint.x - p.x) == 1 || Math.abs(lastpoint.y - p.y) == 1) {
//            super.add(p);
//            return true;
//        }
        else if (lastpoint.x == p.x) {
            System.out.println("---- X equals to X");

            step = (lastpoint.y > p.y) ? -1 : 1;
            for (int yPointer = lastpoint.y + step; yPointer != p.y + step; yPointer += step) {
                add(new Point(lastpoint.x, yPointer));
            }
            return true;

        } else if (lastpoint.y == p.y) {
            System.out.println("---- Y equals to Y");

            step = (lastpoint.x > p.x) ? -1 : 1;
            for (int xPointer = lastpoint.x + step; xPointer != p.x + step; xPointer += step) {
                add(new Point(xPointer, lastpoint.y));
            }
            return true;
        } else {
            writing = true;
            System.out.println("----Calculations ---");
            System.out.println("input point " + p);
            System.out.println("last point " + lastpoint);
            double slope = (double) (lastpoint.y - p.y) / (lastpoint.x - p.x);
            double b = lastpoint.y - (slope * lastpoint.x);
            step = (lastpoint.x > p.x) ? -1 : 1;
            for (int xPointer = lastpoint.x + step; xPointer != p.x + step; xPointer += step) {
                add(new Point(xPointer, (int) ((slope * xPointer) + b)));
                System.out.println("(" + xPointer + " ," + slope + " , " + b + " )");
                System.out.println("point added " + new Point(xPointer, (int) ((slope * xPointer) + b)));
            }
            System.out.println("----End Calculations ---");
            writing = false;
            return true;
        }

*/
    }


}

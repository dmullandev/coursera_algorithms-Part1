
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point point1, Point point2) {
                double slopeDiff = slopeTo(point1) - slopeTo(point2);
                if (slopeDiff > 0) {
                    return 1;
                } else if (slopeDiff < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }
    
    public void draw() {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (that == null) {
            throw new java.lang.NullPointerException("Null point to compareTo");
        }
	    
	    if (that.y > this.y) {
	        return -1; // if this is smaller than that
	    } else if (that.y < this.y) {
	        return +1; // if this is greater than that
	    } else if (that.y == this.y) {
	        if (that.x > this.x) {
	            return -1;
	        } else if (that.x < this.x) {
	            return +1;
	        }
	    }
	    return 0;
    }
    
    public double slopeTo(Point that) {
    	if (that.y == this.y && that.x == this.x) return Double.NEGATIVE_INFINITY; // point to itself
    	if (that.y == this.y) return +0.0; // vertical line
    	if (that.x == this.x) return Double.POSITIVE_INFINITY; // horizontal line positive zero
        return ((double) (that.y - this.y)) / ((that.x - this.x));
    }
}

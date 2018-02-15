package assignment3;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>{
	private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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
        if (that.y < this.y || (that.y == this.y && that.x < this.x) ) return -1; // if that is less
        if (that.y > this.y || (that.y == this.y && that.x > this.x) ) return +1; // if that is greater
        return 0;   // if that is equal
    }
    
    public double slopeTo(Point that) {
    	if(that.y == this.y && that.x == this.x) return Double.NEGATIVE_INFINITY; // point to itself
    	if(that.x == this.x) return +0.0; // vertical line positive zero
    	if(that.y == that.y) return -0.0; // horizontal negative zero
        return ((that.y - this.y) / (that.x - this.x));
    }
    
    public Comparator<Point> slopeOrder() {		
		return new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return Double.compare(slopeTo(p1), slopeTo(p2));
			}
		};
    }
}

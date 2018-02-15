package assignment3;

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        if(points==null) {
        	throw new java.lang.IllegalArgumentException("Null array to constructor");
        }
        for(int i = 0; i < points.length; i++) {
        	if(points[i] == null) {
        		throw new java.lang.IllegalArgumentException("Null element found in Array");
        	}
        }
    }
    
    public int numberOfSegments() {
        
    }
    
    public LineSegment[] segments() {
        
    }
}


public class BruteCollinearPoints {
    private int segment = 0;
    private LineSegment[] segs;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Null array to constructor");
        }
        for (int i = 0; i < points.length; i++) {
        	if (points[i] == null) {
        		throw new java.lang.IllegalArgumentException("Null element found in Array");
        	}
        }
        for (int i = 0; i < points.length; i++) {
            if (i+3 > points.length) {
                break;
            }
            Point p = points[i];
            Point q = points[i+1];
            Point r = points[i+2];
            Point s = points[i+3];
            double pToq = p.slopeTo(q);
            double qTor = q.slopeTo(r);
            if (pToq == qTor) {
                if (q.compareTo(s) == qTor) {
                    LineSegment ls = new LineSegment(p, s);
                    segs[segment++] = ls;
                    
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return segs.length * 4;
    }
    
    public LineSegment[] segments() {
        return segs;
    }
}

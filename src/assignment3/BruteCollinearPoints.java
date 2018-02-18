
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segs;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Null array to constructor");
        }
        for (Point point : points) {
            if (point == null) {
                throw new java.lang.IllegalArgumentException("Null array to constructor");
            }
        }
        
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        
        Point p;
        Point q;
        Point r;
        Point s;
        double rToq, sTor;
        
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        
        checkDuplicates(pointsCopy);
        for (int i = 0; i < pointsCopy.length -3; i++) {
            p = pointsCopy[i];
            for (int k = i + 1; k < pointsCopy.length -2; k++) {
                q = pointsCopy[k];
                for (int b = k + 1; b < pointsCopy.length -1; b++) {
                    r = pointsCopy[b];
                    int slopeOrder = p.slopeOrder().compare(q, r);
                    if (slopeOrder == 0) { // first three points equal slope, search for fourth
                        for (int j = b + 1; j < pointsCopy.length; j++) {
                            s = pointsCopy[j];
                            sTor = s.slopeTo(r);
                            rToq = r.slopeTo(q);
                            if (sTor == rToq) {
                                LineSegment ls = new LineSegment(p, s);
                                foundSegments.add(ls);
                            }
                        }
                    }
                }
            }
        }
        segs = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }
    private void checkDuplicates(Point[] p) {
        for (int i = 0; i < p.length-1; i++) {
            for (int k = i + 1; k < p.length; k++) {
                if (p[i].compareTo(p[k]) == 0) {
                    throw new java.lang.IllegalArgumentException("Duplicate Found");
                }
            }
        }
    }
    public int numberOfSegments() {
        return segs.length;
    }
    
    public LineSegment[] segments() {
        return Arrays.copyOf(segs, numberOfSegments());
    }
}

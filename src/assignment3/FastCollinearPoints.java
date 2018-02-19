
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segs;
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Null array to constructor");
        }
        checkNull(points);
        checkDuplicates(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        int j;
        for (int i = 0; i < pointsCopy.length -1; i++) {
            Point p = pointsCopy[i];
            Arrays.sort(pointsCopy , p.slopeOrder());            
            for (int k = i + 1; k < pointsCopy.length; k++) {
                if (pointsCopy[i].compareTo(pointsCopy[k]) == 0) {
                    j = k;
                    while (pointsCopy[i].compareTo(pointsCopy[j]) == 0) {
                        j++;
                    }
                    LineSegment ls = new LineSegment(pointsCopy[i], pointsCopy[j]);
                    foundSegments.add(ls);
                }
            }
        }
        segs = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }
    
    public int numberOfSegments() {
        return segs.length;
    }
    
    public LineSegment[] segments() {
        return Arrays.copyOf(segs, numberOfSegments());
    }

    private void checkNull(Point[] p) {
        for (Point point : p) {
            if (point == null) {
                throw new java.lang.IllegalArgumentException("Null Point object in given array");
            }
        }
    }
    
    private void checkDuplicates(Point[] p) {
        for (int i = 0; i < p.length-1; i++) {
            for (int k = i + 1; k < p.length; k++) {
                if (p[i].compareTo(p[k]) == 0) {
                    throw new java.lang.IllegalArgumentException("Duplicate Point object in given array");
                }
            }
        }
    }
}

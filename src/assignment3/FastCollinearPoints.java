
public class FastCollinearPoints {
    private LineSegment[] segs;
    public FastCollinearPoints(Point[] points) {
        
    }
    
    public int numberOfSegments() {
        return 1;
    }
    
    public LineSegment[] segments() {
        return segs;
    }
}

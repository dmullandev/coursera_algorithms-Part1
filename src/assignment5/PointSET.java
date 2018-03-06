

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
    
    private final SET<Point2D> set;
    public PointSET() { // construct an empty set of points
        set = new SET<Point2D>();
    }
    
    public boolean isEmpty() { // is the set empty? 
        return set.isEmpty();
    }
     
    public int size() { // number of points in the set 
        return set.size();
    }
    
    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new java.lang.IllegalArgumentException();
        set.add(p);
    }
    
    public boolean contains(Point2D p) { // does the set contain point p? 
        if (p == null) throw new java.lang.IllegalArgumentException();
        return set.contains(p);
    }
    
    public void draw() { // draw all points to standard draw 
        for (Point2D pt : set) {
            pt.draw();
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary) 
        Stack<Point2D> points = new Stack<Point2D>();
        if (rect == null) throw new java.lang.IllegalArgumentException();
        
        for (Point2D pt : set) {
            if (rect.contains(pt)) {
                points.push(pt);
            }
        }
        return points;
    }
    
    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty 
        if (p == null) throw new java.lang.IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D champion = null;
        for (Point2D pt : set) {
            if (champion == null) champion = pt;
            if (pt.distanceSquaredTo(p) < champion.distanceSquaredTo(p)) {
                champion = pt;
            }
        }
        return champion;
    }
}

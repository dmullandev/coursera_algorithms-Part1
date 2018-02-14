package assignment3;
public class Point implements Comparable<Point>{
    private int x;
    private int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw() {
        
    }
    
    public void drawTo(Point that) {
        
    }
    
    public String toString() {
        
    }
    
    public int compareTo(Point that) {
        if (that.y < this.y || (that.y == this.y && that.x < this.x) ) return -1; // if that is less
        if (that.y > this.y || (that.y == this.y && that.x > this.x) ) return +1; // if that is greater
        return 0;   // if that is equal
    }
    
    public double slopeTo(Point that) {
        
    }
    
    public Comparator<Point> slopeOrder(){
        
    }
}

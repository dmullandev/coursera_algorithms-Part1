
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private final SET<Point2D> set;
    private Node root;
    
    public KdTree() {
        root = null;
        set = new SET<Point2D>();
    }
    
    private static class Node {
        private final Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;
        private final int level;
        private int s;

        public Node(Point2D p, int level, int size, RectHV rect) {
            this.p = p;
            this.s = size;
            this.rect = rect;
            this.level = level;
        }
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return sizeCheck(root);
    }
    
    private int sizeCheck(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.s;
        }
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.s;
    }
    
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("calls put() with a null point");

        root = insert(root, p, 0, new RectHV(0, 0, 1, 1));
    }
    
    private Node insert(Node x, Point2D p, int level, RectHV rect) {
        if (x == null) return new Node(p, level, 1, rect);
        double cmp;
        if (level % 2 == 0) {
            cmp = p.x() - x.p.x();
        } else {
            cmp = p.y() - x.p.y();
        }
        
        RectHV rectLeft = null;
        RectHV rectRight = null;
        
        if (cmp < 0 && x.lb == null) {
            if (level % 2 == 0) {
                rectLeft = new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax());
            } else {
                rectLeft = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y());
            }
        } else if (cmp >= 0 && x.rt == null) {
            if (level % 2 == 0) {
                rectRight = new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax());
            } else {
                rectRight = new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax());
            }
        }

        if (cmp < 0) {
            x.lb = insert(x.lb,  p, level + 1, rectLeft);
        } else if (cmp > 0) {
            x.rt = insert(x.rt, p, level + 1, rectRight);
        } else if (!p.equals(x.p)) {
            x.rt = insert(x.rt, p, level +1, rectRight);
        }
        
        x.s = 1 + size(x.lb) + size(x.rt);
        
        return x;
    }
    
    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("argument to contains() is null");
        return get(root, p) != null;
    }
    
    private Point2D get(Node x, Point2D p) {
        if (p == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        
        double cmp;
        if (x.level % 2 == 0) {
            cmp = p.x() - x.p.x();
        } else {
            cmp = p.y() - x.p.y();
        }
        
        if (cmp < 0) {
            return get(x.lb, p);
        } else if (cmp > 0) {
            return get(x.rt, p);
        } else if (!p.equals(x.p)) {
            return get(x.rt, p);
        } else {
            return x.p;
        }        
    }
    
    public void draw() {
        if (root == null) throw new java.lang.IllegalArgumentException();
        StdDraw.setScale();
        
        Stack<Node> st = new Stack<Node>();        
        st.push(root);
        
        while (st.isEmpty()) {
            Node current = st.pop();
            
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(current.p.x(), current.p.y());
            
            boolean even = (current.level % 2 == 0);
            
            StdDraw.setPenRadius();
            if (even) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(current.p.x(), current.p.y(), current.rect.xmax(), current.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(current.rect.xmax(), current.rect.ymax(), current.p.x(), current.p.y());
            }
            if (current.rt != null) st.push(current.rt);
            if (current.lb != null) st.push(current.lb);
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException("null argument to range() is given");
        if (root == null) throw new java.lang.IllegalArgumentException("root node is null at call to range()");
        
        Stack<Node> st = new Stack<Node>();
        st.push(root);
        
        while (st.isEmpty()) {
            Node current = st.pop();            
            
            if (rect.xmin() <= current.p.x() && current.p.x() <= rect.xmax() || rect.ymin() <= current.p.y() && current.p.y() <= rect.ymax()) {
                if (rect.contains(current.p)) {
                    set.add(current.p);
                }
                st.push(current.lb);
                st.push(current.rt);
            } else {
                if (rect.xmin() < current.p.x() && rect.xmax() < current.p.x() || rect.ymin() < current.p.y() && rect.ymax() < current.p.y()){
                    st.push(current.lb);
                } else {
                    st.push(current.rt);
                }
            }
        }
        return set;
    }
    
    public Point2D nearest(Point2D p) {
        return p;
    }
}

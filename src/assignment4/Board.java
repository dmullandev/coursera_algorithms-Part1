package assignment4;

public class Board {
    
    public Board(int[][] blocks) {}
    
    public int dimension() {return 1;}
    public int hamming() {return 1;}
    public int manhattan() {return 1;}
    
    public boolean isGoal() {return true;}
    
    public Board twin() {}
    
    public boolean equals(Object y) {return true;}
    
    public Iterable<Board> neighbors() {}
    
    public String toString() {return "toString";}
    
    public static void main(String[] args) {}
}

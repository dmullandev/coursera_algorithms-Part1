package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
	private int gridLength;
	private int arraySize;
	private int virtualTopIndex;
	private int virtualBottomIndex;
	private boolean[] isOpen;	
	 private WeightedQuickUnionUF percolation;
	
	
	
	private void checkBounds(int row, int col) {
		if(row < 1 || row > gridLength){
			throw new IndexOutOfBoundsException();
		}
		
		if(col < 1 || col > gridLength){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private int siteIndex(int row, int col) {
		checkBounds(row,col);
		int x = row;
		int y = col;
		return (y-1)*gridLength+(x);
		
	}	
	
	public Percolation(int n) {
		if(n < 1){
			throw new IllegalArgumentException();
		}
		gridLength = n;
		arraySize = (n*n)+2;
		
		isOpen = new boolean[arraySize];
		
		virtualTopIndex = 0;
		virtualBottomIndex = (n*n)+1;
        
        isOpen[virtualTopIndex] = true;
        isOpen[virtualBottomIndex] = true;
        percolation = new WeightedQuickUnionUF(arraySize);
        
        for (int j = 1; j<=n; j++) {
            /// connect all top row sites to virtual top site
            int i = 1;
            int topSiteIndex = siteIndex(i,j);
            percolation.union(virtualTopIndex, topSiteIndex);
            
            i = n;
            int bottomSiteIndex = siteIndex(i,j);
            percolation.union(virtualBottomIndex, bottomSiteIndex);
        }
		
	}

	public void open(int row, int col) {
		int siteIndex = siteIndex(row,col);
        if (!isOpen[siteIndex]) {
            isOpen[siteIndex] = true;
            
            if (col>1 && isOpen(row,col-1)) {
                int indexToLeft = siteIndex(row,col-1);
                percolation.union(siteIndex, indexToLeft);
            }
            
            if (col<gridLength && isOpen(row,col+1)) {
                int indexToRight = siteIndex(row,col+1);
                percolation.union(siteIndex, indexToRight);
                
            }
            
            if (row>1 && isOpen(row-1,col)) {
                int indexToTop = siteIndex(row-1,col);
                percolation.union(siteIndex, indexToTop);
                
            }
            
            if (row<gridLength && isOpen(row+1,col)) {
                int indexToBottom = siteIndex(row+1,col);
                percolation.union(siteIndex, indexToBottom);
                
            }
        }
	}
	
	public boolean isOpen(int row, int col) {
		int siteIndex = siteIndex(row,col);
		return isOpen[siteIndex];
	}
	
	public boolean isFull(int row, int col) {
		int siteIndex = siteIndex(row, col);
		return (percolation.connected(virtualTopIndex,siteIndex) && isOpen[siteIndex]);
	}
	
	public boolean percolates() {
		if(gridLength>1) {
			return percolation.connected(virtualTopIndex, virtualBottomIndex);
		}else {
			return isOpen[siteIndex(1,1)];
		}
	}
	
	

	public static void main(String[] args) {

		Percolation p = new Percolation(1);
		StdOut.println(p.percolates());		
		p.open(1, 1);
		StdOut.println(p.percolates());
		
		Percolation p2 = new Percolation(2);
		StdOut.println(p2.percolates());		
		p2.open(1, 1);
		p2.open(2, 1);
		StdOut.println(p2.percolates());
	}
}

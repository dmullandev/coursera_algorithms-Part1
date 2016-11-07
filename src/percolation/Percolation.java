package percolation;

import edu.princeton.cs.algs4.*;

public class Percolation {
	int range;
	public Percolation(int n) {
		range = n;
		if(n < 0) {
			throw new IllegalArgumentException();
		}
	}
	public void open(int row, int col) {
		if ((row < 1 || col < 1) || (row > range || col > range)){		//throw if row is less than 1 or greater than n-by-n grid
			throw new IndexOutOfBoundsException();
		}
	}	
	public boolean isOpen(int row, int col) {
		if ((row < 1 || col < 1) || (row > range || col > range)){		//throw if row is less than 1 or greater than n-by-n grid
			throw new IndexOutOfBoundsException();
		}
		return true;
	}
	
	public boolean isFull(int row, int col) {
		if ((row < 1 || col < 1) || (row > range || col > range)){		//throw if row is less than 1 or greater than n-by-n grid
			throw new IndexOutOfBoundsException();
		}
		return true;
	}
	
	public boolean percolates() {
		return true;
	}

	public static void main(String[] args) {
	}
}

package percolation;

public class PercolationStats {
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0){
			throw new IllegalArgumentException();
		}
	}

	public double mean() {
		return 0;
	}
		
	public double stddev() {
		return 0;
	}
		
	public double confidenceLo() {
		return 0;
	}
		
	public double confidenceHi() {
		return 0;
	}
	
	
}



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board {
    private int[][] boardTiles;
    private Board[] neighbours;
    
    public Board(int[][] blocks) { // construct a board from an n-by-n array of blocks
                                   // (where blocks[i][j] = block in row i, column j)
        this.boardTiles = copy(blocks);
    }
    
    private static int[][] copy(int[][] copyOfblocks) {
        int[][] theCopy = new int[copyOfblocks.length][];
        for (int i = 0; i < copyOfblocks.length; i++) {
            theCopy[i] = Arrays.copyOf(copyOfblocks[i], copyOfblocks[i].length);
        }
        return theCopy;
    }
    
    public int dimension() { // board dimension n
        return boardTiles.length;
    }
    public int hamming() { // number of blocks out of place
        int value = -1;
        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[i].length; j++) {
                if (boardTiles[i][j] != (i * boardTiles.length + j + 1)) value++;
            }
        }
        return value;
    }
    public int manhattan() { // sum of Manhattan distances between blocks and goal
        int manhattan = 0;
        for (int row = 0; row < boardTiles.length; row++) {
            for (int column = 0; column < boardTiles[row].length; column++) {
                int expectedValue = (row * boardTiles.length + column + 1);
                if (boardTiles[row][column] != 0 && boardTiles[row][column] != expectedValue) {
                    int actualValue = boardTiles[row][column];
                    actualValue--;
                    int goalI = actualValue / dimension();
                    int goalJ = actualValue % dimension();
                    manhattan += Math.abs(goalI - row) + Math.abs(goalJ - column);
                }                
            }
        }
        return manhattan;
    }
    
    public boolean isGoal() { // is this board the goal board?
        return (hamming() == 0);
    }
    
    public Board twin() { // a board that is obtained by exchanging any pair of blocks
        int[][] twinBlocks = copy(boardTiles);

        int i = 0;
        int j = 0;
        while (twinBlocks[i][j] == 0 || twinBlocks[i][j + 1] == 0) {
            j++;
            if (j >= twinBlocks.length - 1) {
                i++;
                j = 0;
            }
        }

        exchangeBlocks(twinBlocks, i, j, i, j + 1);
        return new Board(twinBlocks);
    }
    
    public boolean equals(Object y) { // does this board equal y?
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        
        Board that = (Board) y;

        if (this.boardTiles.length != that.boardTiles.length) return false;
        
        for (int row = 0; row < boardTiles.length; row++) {
            if (this.boardTiles[row].length != that.boardTiles[row].length) return false;
            for(int column = 0; column < boardTiles[row].length; column++) {
                if (this.boardTiles[row][column] != that.boardTiles[row][column]) return false;
            }
        }
        return true;
    }
    
    private void exchangeBlocks(int[][] blocks, int iFirstBlock, int jFirstBlock, int iSecondsBlock, int jSecondBlock) {
        int firstValue = blocks[iFirstBlock][jFirstBlock];
        blocks[iFirstBlock][jFirstBlock] = blocks[iSecondsBlock][jSecondBlock];
        blocks[iSecondsBlock][jSecondBlock] = firstValue;
    }
    
    public Iterable<Board> neighbors() { // all neighboring boards
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                if (neighbours == null) {
                    findNeighbours();
                }
                return new NeighbourIterator();
            }
        };
    }
    
    private void findNeighbours() {
        List<Board> foundNeighbors = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        while (boardTiles[i][j] != 0) {
            j++;
            if (j > dimension()) {
                i++;
                j = 0;
            }
        }
        
        if (i > 0) {
            int[][] neighborTiles = copy(boardTiles);
            exchangeBlocks(neighborTiles, i - 1, j, i, j);
            foundNeighbors.add(new Board(neighborTiles));
        }
        if (i < dimension() - 1) {
            int[][] neighborTiles = copy(boardTiles);
            exchangeBlocks(neighborTiles, i, j, i + 1, j);
            foundNeighbors.add(new Board(neighborTiles));
        }
        if (j > 0) {
            int[][] neighborTiles = copy(boardTiles);
            exchangeBlocks(neighborTiles, i, j - 1, i, j);
            foundNeighbors.add(new Board(neighborTiles));
        }
        if (j < dimension() - 1) {
            int[][] neighborTiles = copy(boardTiles);
            exchangeBlocks(neighborTiles, i, j, i, j + 1);
            foundNeighbors.add(new Board(neighborTiles));
        }
        neighbours = foundNeighbors.toArray(new Board[foundNeighbors.size()]);  
    }
    
    private class NeighbourIterator implements Iterator<Board> {
        private int index = 0;
        
        public boolean hasNext() {
            return index < neighbours.length;
        }
        
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Not a supported implementation");
        }
        
        
        public Board next() {
            if(!hasNext()) {
                throw new java.util.NoSuchElementException("No next neighbour");
            }
            return neighbours[index++];
        }
    }
    public String toString() { // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int[] row : boardTiles) {
            for (int tile : row) {
                s.append(tile);
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    
    public static void main(String[] args) { // unit tests (not graded)
        
    } 
}

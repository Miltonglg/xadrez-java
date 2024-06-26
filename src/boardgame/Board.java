package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error Creating Board: there must be at least 1 row and 1 colum");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece (int row, int colum) {
		if (!positionExists(row, colum)) {
			throw new BoardException ("position not on the board");
		}
		return pieces [row][colum];
	}
	
	public Piece piece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException ("position not on the board");
		}
		return pieces [position.getRow()][position.getColum()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException ("there is a piece on position" + position);
		}
		pieces[position.getRow()][position.getColum()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException ("position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColum()] = null;
		return aux;
	}
	
	private boolean positionExists (int row, int colum) {
		return row >= 0 && row < rows && colum >= 0 && colum < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColum());
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException ("position not on the board");
		}
		return piece(position) != null;
	}
}

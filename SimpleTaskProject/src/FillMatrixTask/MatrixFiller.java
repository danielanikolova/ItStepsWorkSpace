package FillMatrixTask;

public class MatrixFiller {

    private int[][] matrix;
    private int n;
    private int value;
    private int minCol;
    private int maxCol;
    private int minRow;
    private int maxRow;
    private int pointRowPosition;
    private int pointColPositin;
    private boolean isBounderyLoop;

    public MatrixFiller(int[][] matrix, int pointRowPosition, int pointColPositin) {

	this.matrix = matrix;
	n = matrix.length;
	this.pointColPositin = pointColPositin;
	this.pointRowPosition = pointRowPosition;
    }

    public void fillSpiralMatrixClockwise() throws IncorrectCoordinatesException {

	value = 1;
	int steps = 0;
	minCol = 0;
	maxCol = n - 1;
	minRow = 0;
	maxRow = n - 1;
	isBounderyLoop = true;

	if (pointRowPosition == minRow && pointColPositin < maxCol && pointColPositin >= minCol) {
	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillRowRight(minCol, maxCol, minRow);
		fillColDown(minRow, maxRow, maxCol);
		fillRowLeft(maxCol, minCol, maxRow);
		fillCollUp(maxRow, minRow, minCol);
		changeBoundaryValues();
	    }
	    checkCenter();
	    
	} else if (pointRowPosition < maxRow && pointColPositin == maxCol && pointRowPosition >= minRow) {

	    steps = n * n - pointRowPosition;
	    while (value < steps) {

		fillColDown(minRow, maxRow, maxCol);
		fillRowLeft(maxCol, minCol, maxRow);
		fillCollUp(maxRow, minRow, minCol);
		fillRowRight(minCol, maxCol, minRow);
		changeBoundaryValues();
	    }
	    
	    checkCenter();

	} else if (pointRowPosition == maxRow && pointColPositin > minCol && pointColPositin <= maxCol) {

	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillRowLeft(maxCol, minCol, maxRow);
		fillCollUp(maxRow, minRow, minCol);
		fillRowRight(minCol, maxCol, minRow);
		fillColDown(minRow, maxRow, maxCol);
		changeBoundaryValues();
	    }
	    
	    checkCenter();
	    
	} else if (pointRowPosition <= maxRow && pointRowPosition > minRow && pointColPositin == minCol) {
	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillCollUp(maxRow, minRow, minCol);
		fillRowRight(minCol, maxCol, minRow);
		fillColDown(minRow, maxRow, maxCol);
		fillRowLeft(maxCol, minCol, maxRow);
		changeBoundaryValues();
	    }
	    
	    checkCenter();
	    
	} else
	    throw new IncorrectCoordinatesException();

    }

    public void fillSpiralMatrixCounterClockwise() throws IncorrectCoordinatesException {

	value = 1;
	int steps = 0;
	minCol = 0;
	maxCol = n - 1;
	minRow = 0;
	maxRow = n - 1;
	isBounderyLoop = true;

	if (pointRowPosition == minRow && pointColPositin <= maxCol && pointColPositin > minCol) {
	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillRowLeft(maxCol, minCol, minRow);
		fillColDown(minRow, maxRow, minCol);
		fillRowRight(minCol, maxCol, maxRow);
		fillCollUp(maxRow, minRow, maxCol);
		changeBoundaryValues();
	    }
	    
	    checkCenter();
	    
	} else if (pointRowPosition <= maxRow && pointColPositin == maxCol && pointRowPosition > minRow) {

	    steps = n * n - pointRowPosition;
	    while (value < steps) {
		fillCollUp(maxRow, minRow, maxCol);
		fillRowLeft(maxCol, minCol, minRow);
		fillColDown(minRow, maxRow, minCol);
		fillRowRight(minCol, maxCol, maxRow);
		changeBoundaryValues();
	    }

	   
	    checkCenter();

	} else if (pointRowPosition == maxRow && pointColPositin >= minCol && pointColPositin < maxCol) {

	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillRowRight(minCol, maxCol, maxRow);
		fillCollUp(maxRow, minRow, maxCol);
		fillRowLeft(maxCol, minCol, minRow);
		fillColDown(minRow, maxRow, minCol);
		changeBoundaryValues();
	    }
	    
	    checkCenter();

	} else if (pointRowPosition < maxRow && pointRowPosition >= minRow && pointColPositin == minCol) {
	    steps = n * n - pointColPositin;
	    while (value < steps) {
		fillColDown(minRow, maxRow, minCol);
		fillRowRight(minCol, maxCol, maxRow);
		fillCollUp(maxRow, minRow, maxCol);
		fillRowLeft(maxCol, minCol, minRow);

		changeBoundaryValues();
	    }
	    
	    checkCenter();
	    
	} else
	    throw new IncorrectCoordinatesException();
    }

    //This method checks if the matrix has center point and fill it
    private void checkCenter() {
	
	 if (minCol == maxCol && minRow == maxRow) {
		matrix[minRow][minCol] = value;
	    }
	
    }

    private void fillColDown(int minRow, int maxRow, int maxCol) {

	// fillDown
	for (int i = minRow; i < maxRow; i++) {
	    if (i < pointRowPosition && isBounderyLoop == true) {
		matrix[i][maxCol] = 0;
		continue;
	    }

	    matrix[i][maxCol] = value;
	    value++;
	}

	isBounderyLoop = false;
    }

    private void fillRowRight(int minCol, int maxCol, int minRow) {

	// fillRight
	for (int i = minCol; i < maxCol; i++) {
	    if (i < pointColPositin && isBounderyLoop == true) {
		matrix[minRow][i] = 0;
		continue;
	    }
	    matrix[minRow][i] = value;
	    value++;
	}

	isBounderyLoop = false;
    }

    private void fillCollUp(int maxRow, int minRow, int minCol) {

	// fillUp
	for (int i = maxRow; i > minRow; i--) {
	    if (i > pointRowPosition && isBounderyLoop == true) {
		matrix[i][minCol] = 0;
		continue;
	    }

	    matrix[i][minCol] = value;
	    value++;
	}

	isBounderyLoop = false;
    }

    private void fillRowLeft(int maxCol, int minCol, int maxRow) {

	// fillLeft
	for (int i = maxCol; i > minCol; i--) {

	    if (i > pointColPositin && isBounderyLoop == true) {
		matrix[maxRow][i] = 0;
		continue;
	    }
	    matrix[maxRow][i] = value;
	    value++;
	}

	isBounderyLoop = false;
    }

    private void changeBoundaryValues() {

	minCol++;
	minRow++;
	maxCol--;
	maxRow--;

    }

    public void printMatrix() {
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix.length; j++) {

		if (matrix[i][j] == 0) {
		    System.out.print(" " + "\t");
		} else {
		    System.out.print(matrix[i][j] + "\t");
		}

	    }

	    System.out.println();
	}
    }

}

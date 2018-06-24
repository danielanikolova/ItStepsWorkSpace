/**
 * Task description: We create matrix with dimension entered from the console. Then we fill the
 * matrix clockwise and counter clockwise from concrete point. The point coordinates are entered from the console.
 * 
 * created at 2018-06-07 by d.nikolova <d.nikolova@seeburger.com>
 * 
 *  Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 * 
 * <p>
 * Assigned by Todor Manahov
 * <p>
 */

package FillMatrixTask;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);
	
	int n = Integer.parseInt(scanner.nextLine());
	
	int [][] matrix = new int[n][n];
        
        int rowIndex = Integer.parseInt(scanner.nextLine());
        int colIndex = Integer.parseInt(scanner.nextLine());       
        
        MatrixFiller mf = new MatrixFiller(matrix, rowIndex, colIndex);
        
        System.out.printf("Dimensions of the matrix below are %d × %d.\n", n, n);
        
        System.out.printf("The enter point is with coordinates [%d][%d].\n", 
    		rowIndex, colIndex);
        
        try {
           
            mf.fillSpiralMatrixClockwise();
            System.out.println("Clockwise print:");
            mf.printMatrix();
            System.out.println();
            
            mf.fillSpiralMatrixCounterClockwise();
            System.out.println("Counterclockwise print:");
            mf.printMatrix();
            
            
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

}

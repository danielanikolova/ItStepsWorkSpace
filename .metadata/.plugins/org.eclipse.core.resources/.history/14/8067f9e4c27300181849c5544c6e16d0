/**
 * Task description: 
 * This program implements the fastest way to transfer directory content to another directory.  
 * The task is performed until the program stops. If the directory is empty the program waits for new files. 
 * When they arrive they are immediately picked up and  moved.
 * 
 * created at 2018-06-07 by d.nikolova <d.nikolova@seeburger.com>
 * 
 *  Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 * 
 * <p>
 * Assigned by Todor Manahov
 * <p>
 */

package copyFilesTask;

import java.io.IOException;
import java.util.Scanner;

public class Run {

    private static final String sourceFolder = "C:\\Users\\danie\\Desktop\\github\\ItSteps\\SimpleTasks\\com.seeburger.simpleTasks\\FromDirectory";
    private static final String destination = "D:\\ToDirectory";
//    private static final String destination = "C:\\Users\\danie\\Desktop\\github\\ItSteps\\SimpleTasks\\com.seeburger.simpleTasks\\ToDirectory";
      
    
    public static void main(String[] args) throws IOException {
	
	CopyProcessor copyThread = new CopyProcessor(sourceFolder, destination);
	
	copyThread.start();
	
	System.out.println("Press any key to stop ...");
	Scanner scanner = new Scanner(System.in);
	
	scanner.nextLine();
	
	copyThread.shutdown();
	
	scanner.close();
	

    }

}

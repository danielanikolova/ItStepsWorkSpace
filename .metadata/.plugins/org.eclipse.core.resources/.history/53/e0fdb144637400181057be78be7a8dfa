package copyFilesTask;

import java.io.IOException;


/*
 * CopyProcessor class check the directory for files and transfer them in another directory.
 * If new files arrive the CopyProcessor class transfer them too.
 */
public class CopyProcessor extends Thread {

    private volatile boolean running = true;
    private CopyManager copyManager;

    public CopyProcessor(String source, String destination) {
	this.copyManager = new CopyManager(source, destination);;
    }

    public void run() {	while (running) {
	    if (!copyManager.checkEmptyDirectory()) 
	    {
		try {
		    copyManager.copyFiles();
		    
		} catch (FileLockedException e) {
		    System.out.println(e.getMessage());
		    return;
		} catch (IOException e) {
		    e.printStackTrace();
		    return;
		}
		
//		try {
//		    copyManager.removeFiles();
//		} catch (IOException e) {		    
//		    e.printStackTrace();
//		}
	    }

	    try {
		Thread.sleep(300);		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

    public void shutdown() {
	this.running = false;
    }

}

/**
 * 
 */
package Logistics;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @author danie
 *
 */
public class Producer implements Runnable{

	private volatile Factory factory;
	private Lock lock;
	
	public Producer(Factory factory, Lock lock) {
		this.factory = factory;
		this.lock = lock;
	}

	@Override
	public void run() {
		
		while (!Thread.interrupted()) {
			
			synchronized (lock) {
				
				try {
					while (factory.producing) {
						lock.wait();
						System.out.println("The factory is producing new packages...");
						
						factory.producePackages();
						
						factory.producing = false;
						
						notifyAll();
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			}
			
		}
		
		Random random = new Random();
		
	}
	
}

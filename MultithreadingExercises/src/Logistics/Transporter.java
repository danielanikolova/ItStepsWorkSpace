/**
 * 
 */
package Logistics;

import java.util.concurrent.locks.Lock;

/**
 * @author danie
 *
 */
public class Transporter implements Runnable{

	private Factory factory;
	private int trucksNumber;
	private Lock lock;
	
	public Transporter(Factory factory, int truckesNumber,  Lock lock) {
		this.factory = factory;
		this.trucksNumber = truckesNumber;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				while (!factory.producing) {
					lock.wait();
					
					factory.shipPacks(trucksNumber);
					factory.producing = true;
					
					lock.notifyAll();
				}
				
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}
	
//	public void removeTruckes(int truckesToRemove)
//	{
//		if (truckesToRemove>=trucksNumber) {
//			trucksNumber = 0;
//		}else {
//			trucksNumber-=truckesToRemove;
//		}
//	}
//	
//	public void addTrucks(int truckesToAdd) 
//	{
//		trucksNumber+=truckesToAdd;
//	}
//	
//	public void setTransporting(boolean transporting) 
//	{
//		this.transporting = transporting;
//	}
}

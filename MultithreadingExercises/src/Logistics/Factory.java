package Logistics;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Factory {

	Queue<Pack> packegesToTransport = new ArrayDeque<>();
	List<Worker> workers;
	int capacity;
	volatile boolean producing;
	
	public Factory(List<Worker> workers, int capacity) {
		this.workers = workers;
		this.capacity = capacity;
	}
	
	
	public void addWorker(Worker worker)
	{
		workers.add(worker);
	}
	
	public void removerWorker(Worker worker)
	{
		workers.remove(worker);
	}
	
	public void producePackages()
	{
		for (int i = 0; i < workers.size(); i++) 
		{
			if (packegesToTransport.size() >= capacity) {
				break;
			}
			packegesToTransport.add(new Pack());
		}
	}
	
	public void shipPacks(int trucksNumber)
	{
		while (trucksNumber>0 && !packegesToTransport.isEmpty()) 
		{
			packegesToTransport.peek();
			trucksNumber--;
		}
		
		
	}
}

package Logistics;

public class RunLogistics {

	public static void main(String[] args) {
		
		Factory factory = new Factory();
		
		Producer producerThread = new Producer(factory);
		Transporter transporterThread = new Transporter(factory);

	}

}

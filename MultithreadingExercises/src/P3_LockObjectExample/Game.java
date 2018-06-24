package P3_LockObjectExample;

public class Game {
	
	public static void main(String[] args) {
		
		Object lock = new Object();
		
		Player player1 = new Player("ping", lock);
		Player player2 = new Player("pong", lock);
		
		player1.setNextPlayer(player2);
		player2.setNextPlayer(player1);
		
		 System.out.println("Game starting...!");

	     player1.setMustPlay(true);
	     
	     Thread thread1 = new Thread(player1);
	     Thread thread2 = new Thread(player2);
	     
	     thread2.start();
	     thread1.start();
	     
	     try {
			Thread.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	     
	     thread1.interrupt();
	     thread2.interrupt();
	     
	     try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	     

	     System.out.println("Game finished!");
		
	}

}

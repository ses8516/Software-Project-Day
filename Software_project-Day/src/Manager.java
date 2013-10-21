import java.util.Random;


public class Manager extends Thread {
	
	private Thread manager;
	public Manager(Thread manager){
		this.manager = manager;
	}
	
	/**
	 * Manager has a standup meeting for 15 minutes
	 */
	public void standupMeeting(){
		System.out.println("Manager is having a standup meeting.");
		try {
			this.wait(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The manager takes a 1 hour our lunch break 
	 */
	public void lunchBreak(){
		System.out.println("Manager is on lunch break");
		try {
			this.wait(600);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * The manager holds a 1 hour meeting
	 */
	public void executiveMeeting(){
		System.out.println("Manager is in an executive meeting");
		try {
			this.wait(600);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * The manager holds a project status meeting for 15 minutes
	 */
	public void statusMeeting(){
		System.out.println("Manager is conducting a Project Status meeting");
		try {
			this.wait(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

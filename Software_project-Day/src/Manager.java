import java.util.Random;


public class Manager extends Thread {
	
	private final Clock clock;
	private boolean busy = false;
	private int numLeads = 0; // # of team leads in the office. Needed so Manager can know when to start
				// the meeting.
	
	public Manager(Clock clock, int numLeads){
		this.clock = clock;
		this.numLeads = numLeads;
	}
	
	/**
	 * A team lead knocks on the Manager's door for the morning meeting. 
	 */
	 //Synchronized in case not allowed that multiple team leads can access at the same time.
	public synchronized void knockOnDoor(int teamNumber){
		System.out.println(clock.getTime() + "Team Lead " + teamNumber 
							+ " has arrived at the morning meeting");
		numLeads -= 1; //Subtract 1 for number of leads waiting for.
	}
	
	/**
	 * Manager has a standup meeting for 15 minutes
	 */
	public void standupMeeting(){
		System.out.println(clock.getTime()+": Manager is having a standup meeting.");
		try {
			this.wait(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The manager takes a 1 hour on lunch break 
	 */
	public void lunchBreak(){
		System.out.println(clock.getTime()+": Manager is on lunch break.");
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
		System.out.println(clock.getTime()+": Manager is in an executive meeting.");
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
		System.out.println(clock.getTime()+": Manager is conducting a Project Status meeting.");
		
		try {
			this.wait(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The manager answers a question for 10 minutes
	 */
	public void answerQuestion(){
		System.out.println(clock.getTime()+": Manager is answering the team lead's question.");
		
		try {
			this.wait(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * The random stuff the manager does during the day when not answering questions, having lunch, or doing any other activity
	 */
	public void randomStuff(){
		Random generator = new Random();
		int n = 4;
		int selector = generator.nextInt(n);
		
		if (selector == 0){
			System.out.println(clock.getTime()+": The manager is browsing reddit.");
		}
		else if (selector == 1){
			System.out.println(clock.getTime()+": The manager is doing actual work, looking at administrative data.");
		}
		else if (selector == 2){
			System.out.println(clock.getTime()+": Manager is twirling his pen.");
		}
		else if (selector == 3){
			System.out.println(clock.getTime()+": Manager is finishing up some work, while redditing.");
		}
		
	}
	
	/**
	 * The run method goes through a day of the manager
	 */
	public void run(){
		busy = true;
		//Manager arrives at 8:00 each day
		System.out.println(clock.getTime()+": Manager arrives.");
		//Manager does planning and waits until all team leads arrive in his office
		System.out.println(clock.getTime()+": Manager is doing some planning work as he waits "
						+ "for the team leads to arrive.");
		while( numLeads != 0 )
			this.wait(10);
			
		//manager has 15 minute meeting here. standupMeeting()
		this.standUpMeeting();

		//manager then goes back to doing random stuff
		this.randomStuff();
		
		//manager can be asked questions that take 10 minutes
		
		//at 10:00-11:00 a meeting. executiveMeeting()
		//while( clock.getTimeMillis < 1200 ){} //Don't think this is right
		
		//this.executiveMeeting();
		//if answering question, finish question then have meeting
		
		//lunch at 12:00(or closest time to it) to 1:00, lunchBreak()
		//while( clock.getTimeMillis < 2400 ){} //Don't think this is right
		//this.lunchBreak();
		
		//meeting from 2:00-3:00. executiveMeeting()
		//while( clock.getTimeMillis < 3600 ){} //Don't think this is right
		//this.executiveMeeting();
		
		//4:15, status meeting starts, statusMeeting()
		//while( clock.getTimeMillis < 4950 ){} //Don't think this is right
		//this.statusMeeting();
		
		//manager leaves at 5:00
	}


}

import java.util.Random;


public class Manager extends Thread {
	
	private final Clock clock;
	private boolean busy = false;
	
	public Manager(Clock clock){
		this.clock = clock;
	}
	
	/**
	 * Manager has a standup meeting for 15 minutes
	 */
	public void standupMeeting(){
		System.out.println(clock.getTime()+": Manager is having a standup meeting.");
		try {
			this.sleep(150);
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
			this.sleep(600);
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
			this.sleep(600);
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
			this.sleep(150);
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
			this.sleep(100);
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
		System.out.println(clock.getTime()+": Manager arrives.");	//Manager arrives at 8:00 each day
		randomStuff(); 												//Manager does planning and waits until all team leads arrive in his office
																	//when they arrive, they knock on manger door
		standupMeeting();											//manager has 15 minute meeting here. standupMeeting()
		//randomStuff();												//manager then goes back to doing random stuff
		busy = false;
																	//manager can be asked questions that take 10 minutes
																	//if answering question, finish question then have meeting
		if (clock.getTimeMillis() >= 1200 && busy == false){
			busy = true;
			executiveMeeting();      								//at 10:00-11:00 a meeting. executiveMeeting()
			busy = false;
		}
		
												
		else if (clock.getTimeMillis() >= 3600 && busy == false){
			busy = true;
			executiveMeeting();										//meeting from 2:00-3:00. exectuiveMeeting()
			busy = false;
		}
		else if (clock.getTimeMillis() >= 2400 && busy == false ){
			busy = true;
			lunchBreak();											//lunch at 12:00(or closest time to it) to 1:00, lunchBreak()
			busy = false;
		}
		else if (clock.getTimeMillis() == 4950)
		{
			statusMeeting();										//4:15, status meeting starts, statusMeeting()
			busy = false;
		}
		else if (clock.getTimeMillis() >= 5400){
			System.out.println(clock.getTime() + ": Manager leaves"); //manager leaves at 5:00
		}		
		else{
			randomStuff();
			busy = false;
		}
	}


}

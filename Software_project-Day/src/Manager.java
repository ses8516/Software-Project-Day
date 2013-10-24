import java.util.Random;


public class Manager extends Thread {
	
	private final Clock clock;
	private boolean busy = false;
	private int numLeads = 0; // # of team leads in the office. Needed so Manager can know when to start
				// the meeting.
	private Thread employeeWithQuestion = null;
	
	public Manager(Clock clock, int numLeads){
		this.clock = clock;
		this.numLeads = numLeads;
	}
	
	/**
	 * Registers that a employee has a question to ask the manager
	 * @return true if manager is available to answer a question
	 * false if manager is busy or answering another employee's question
	 */
	public synchronized boolean askQuestion(){
		if( employeeWithQuestion != null || busy){
			return false;
		}
		try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		employeeWithQuestion = Thread.currentThread();
		return true;
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
	public void standUpMeeting(){
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
	private void lunchBreak(){
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
	private void executiveMeeting(){
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
	private void statusMeeting(){
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
	private void answerQuestion(){
		System.out.println(clock.getTime()+": Manager is answering the team lead's question.");
		
		try {
			this.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		employeeWithQuestion.notify();
		employeeWithQuestion = null;
	}
	/**
	 * The random stuff the manager does during the day when not answering questions, having lunch, or doing any other activity
	 */
	private void randomStuff(){
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
	 * Check to see if any employee is asking a question and if so answer it
	 */
	private void answerQuestions(){
		if(employeeWithQuestion == null){
			return;
		}
		answerQuestion();
	}
	
	/**
	 * The run method goes through a day of the manager
	 */
	public void run(){
		busy = true;
		//Manager arrives at 8:00 each day
		System.out.println(clock.getTime()+": Manager arrives.");
		//Manager does planning and sleeps until all team leads arrive in his office
		System.out.println(clock.getTime()+": Manager is doing some planning work as he waits "
						+ "for the team leads to arrive.");
		while( numLeads != 0 ){
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//manager has 15 minute meeting here. standupMeeting()
		this.standUpMeeting();
		busy = false;		
		
		//at 10:00 a meeting. executiveMeeting()
		while( clock.getTimeMillis() < 1200 ){
			answerQuestions();
		} //Don't think this is right
		
		busy = true;
		this.executiveMeeting();
		busy = false;
		
		//answer questions until lunch at 12:00
		while( clock.getTimeMillis() < 2400 ){
			answerQuestions();
		}
		
		busy = true;
		//lunch at 12:00(or closest time to it) to 1:00, lunchBreak()
		this.lunchBreak();
		busy = false;
		
		//meeting from 2:00-3:00. executiveMeeting()
		while( clock.getTimeMillis() < 3600 ){
			answerQuestions();
		}
		busy = true;
		this.executiveMeeting();
		busy = false;
		
		//4:15, status meeting starts, statusMeeting()
		while( clock.getTimeMillis() < 4950 ){
			answerQuestions();
		}
		busy = true;
		this.statusMeeting();
		busy = false;
		
		//manager leaves at 5:00
		while( clock.getTimeMillis() < 5400){
			answerQuestions();
		}
		System.out.println(clock.getTime()+": Manager has left.");
	}


}

import java.util.Random;

public class Employee extends Thread {
	
	private static volatile boolean conferenceRoomAvailable = true;
	
	private final int teamNumber;
	// Employee number of 1 means team lead
	private final int employeeNumber;
	private final Manager manager;
	private final Clock clock;
	private Employee teamLead;
	private boolean busy = false;
	private volatile int numDevelopers = 3;
	
	private boolean teamQuestion = false;
	
	/**
	 * Constructor for team leads as they will be their own team lead
	 */
	public Employee(int team, int num, Manager manager, Clock clock){
		super(""+team+num);
		this.teamNumber = team;
		this.employeeNumber = num;
		this.manager = manager;
		this.clock = clock;
		this.teamLead = this;
	}
	
	/**
	 * Constructor for regular employees.
	 */
	public Employee(int team, int num, Employee teamLead, Manager manager, Clock clock){
		this(team,num,manager,clock);
		this.teamLead = teamLead;
	}
	
	/**
	 * Represents the Employee being asked a question. Has a 50%
	 * chance of being able to answer the question.
	 * @return True if the question was answered and false otherwise.
	 */
	public boolean answerQuestion(){
		Random r = new Random(2);
		if(r.nextInt() == 1){
			return true;			
		}
		else{
			teamQuestion = true;
			return false;
		}
	}
	
	/**
	 * Represents the Employee arriving at the office 
	 */
	 private void arrive(){
	 	//Employee can arrive at any time between 8:00 and 8:30		
		try {
			sleep( (long) (Math.random() * 300) );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(clock.getTime()+" : Developer "+getName()+" has arrived at work.");
	 } 
	
	
	private void lunchBreak(){
		Random r = new Random(300);
		System.out.println(clock.getTime()+" : Developer "+getName()+" goes out for lunch.");
		try {
			sleep(300 + r.nextInt());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void knockOnDoor(){
		System.out.println(clock.getTime() + " : Developer " + getName()+ " has arrived at the team stand up meeting");
		numDevelopers -= 1; //Subtract 1 for number of developers waiting for.
		try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void teamStandUpMeeting(){
		conferenceRoomAvailable = false;
		
		System.out.println(clock.getTime()+" : Team"+teamNumber+" is having a standup meeting.");
		try {
			this.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyAll();
		
		conferenceRoomAvailable = true;
	}
	
	
	public void run(){
		if(employeeNumber == 1){
			runAsTeamLead();
		}else{
			runAsEmployee();
		}
	}
	
	private boolean askQuestion(){
		if(teamQuestion || Math.random() < .05){
			teamQuestion = false;
			return true;
		}
		return false;
	}
	
	private void runAsTeamLead(){
		//Arrive
		arrive();
		int startTime = clock.getTimeMillis();
		
		busy = true;
		//Stand-up-meeting
		manager.knockOnDoor(teamNumber);
		
		//Team based stand-up-meeting
		while(numDevelopers > 0){
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		teamStandUpMeeting();
		busy = false;

		while(clock.getTimeMillis() < 2400){
			if(askQuestion()){
				manager.askQuestion();
			}else{
				// Do actual work
			}
		}
		
		busy = true;
		//Lunch
		lunchBreak();
		busy = false;
		
		while(clock.getTimeMillis() < 4800){
			if(askQuestion()){
				manager.askQuestion();
			}else{
				// Do actual work
			}
		}
		
		busy = true;
		//Status meeting
		busy = false;
		
		while(clock.getTimeMillis() < startTime+4800){
			if(askQuestion()){
				manager.askQuestion();
			}else{
				// Do actual work
			}
		}
		
		System.out.println(clock.getTime()+" : Developer "+getName()+" has left work.");
	}
	
	private void runAsEmployee(){
		//Arrive
		arrive();
		int startTime = clock.getTimeMillis();

		knockOnDoor();

		while(clock.getTimeMillis() < 2400){
			if(askQuestion()){
				if(teamLead.answerQuestion()){
					System.out.println(clock.getTime()+" : Team Lead "+teamLead.getName()+" has answered Developer "+getName()+"'s question.");
				}else{
					
				}
			}else{
				// Do actual work
			}
		}
		
		//Lunch
		lunchBreak();
		
		while(clock.getTimeMillis() < 4800){
			if(askQuestion()){
				manager.askQuestion();
			}else{
				// Do actual work
			}
		}
		
		//Status meeting
		while(clock.getTimeMillis() < startTime+4800){
			if(askQuestion()){
				manager.askQuestion();
			}else{
				// Do actual work
			}
		}
		
		System.out.println(clock.getTime()+" : Developer "+getName()+" has left work.");
	}
}


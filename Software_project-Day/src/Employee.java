import java.util.Random;

public class Employee extends Thread {
	
	private final int teamNumber;
	// Employee number of 1 means team lead
	private final int employeeNumber;
	
	private final Clock clock;
	
	public Employee(int team, int num, Clock clock){
		super(""+team+num);
		teamNumber = team;
		employeeNumber = num;
		this.clock = clock;
	}
	
	/**
	 * Represents the Employee being asked a question. Has a 50%
	 * chance of being able to answer the question.
	 * @return True if the question was answered and false otherwise.
	 */
	public boolean answerQuestion(){
		Random r = new Random(2);
		if(r.nextInt() == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Represents the Employee arriving at the office or a meeting.
	 * @param range - Range of minutes Employee can arrive at (e.g Employee can arrive at anytime within 30min)
	 */
	 public void arrive(int range){
	 	//Employee can arrive at any time between 8:00 and 8:30
		Random r = new Random(range);
		
		this.wait( (r.nextInt() + 1)*10 ); // +1 to account for 0
	 } 
	
	/** 
	 * Represents a team lead knocking on the Manager's door at the start of the day and entering the room. 
	 * Will check if the Employee is a team lead by checking their employeeNumber.
	 */
	public void knockOnDoor(int employeeNumber){
		if( employeeNumber == 1 )
			//manager.knockOnDoor(teamNumber);
	}	
	
	public int lunchBreak(){
		Random r = new Random(3000);
		return (3000 + r.nextInt());
	}
	
	/**
	 * Represents the Employee asking the Manager a question when they
	 * unable to successfully answer a question. Enters their question into a queue.
	 * */
	public void requestAnswer(){
		//Employee e = new Employee
		
	}
	
	
	public void run(){
		if(employeeNumber == 1){
			runAsTeamLead();
		}else{
			runAsEmployee();
		}
	}
	
	private void runAsTeamLead(){
		
	}
	
	private void runAsEmployee(){
		
	}
}


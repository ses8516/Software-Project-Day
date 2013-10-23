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
	 * Represents the (class name here) being asked a question. Has a 50%
	 * chance of being able to answer the question.
	 * @return True if the question was answered and false otherwise.
	 */
	//NOTE: Check if Random goes to 0!
	public boolean answerQuestion(){
		Random r = new Random(2);
		if(r.nextInt() == 1)
			return true;
		else
			return false;
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
	
}


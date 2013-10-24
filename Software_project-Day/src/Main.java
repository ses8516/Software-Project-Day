import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args){
		Clock clock = new Clock();
		Manager manager = new Manager(clock, 3);
		ArrayList<Employee> employees = new ArrayList<Employee>(12);
		
		// Create 3 Teams
		for(int i = 1; i < 4; i++){
			Employee teamLead = new Employee(i,1,manager,clock);
			employees.add(teamLead);
			System.out.println("Team Lead "+i);
			for(int j = 2; j < 5; j++){
				// i = team number
				// j = employee number
				employees.add(new Employee(i,j,teamLead,manager,clock));
				
				System.out.println("Developer "+i+j);
			}
		}
		
		clock.start();
//		manager.start();
		for(Employee e : employees){
			e.start();
		}
		
	}
}

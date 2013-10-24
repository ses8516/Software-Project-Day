import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args){
		Clock clock = new Clock();
		
		Manager manager = new Manager(clock);
		manager.start();
		
		ArrayList<Employee> employees = new ArrayList<Employee>(12);
		// Create Teams
		for(int i = 1; i < 4; i++){
			for(int j = 1; j < 5; j++){
				// i = team number
				// j = employee number
				employees.add(new Employee(i,j,clock));
				
//				System.out.println(i+""+j);
			}
		}
		
		clock.start();
//		manager.start();
//		for(Employee e : employees){
//			e.start();
//		}
	}

}

public class Clock extends Thread{
		
	volatile int time = 0;
		
	public Clock(){}
		
	public String getTime(){
		// Dividing by 600 gets the hour and the +8 adjusts it correctly
		// e.g 1 hr from the start of the day would be 9.
		String hour = "" + ( (time/600) + 8 );
		String minutes = "" + (time%600);
			
		return hour + ":" + minutes;
	}
		
	public void run(){
		for( int hr = 1; hr <= 9; hr++){
			for( int min = 0; min < 60; min++){
				try{
					time += min;
					//send out this hr and min to all interested parties.
				} catch (InterruptedException e) {
			      		e.printStackTrace();
		      		}
			}
		}
	}
}

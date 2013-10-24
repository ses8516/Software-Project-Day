/**
 * Class to keep track of time
 * 
 * 10ms represents 1 min of actual time
 * 
 * The time attribute represents the amount of milliseconds that
 * have passed since 8:00am
 * 
 * Employee and Manager classes can ask the clock what time it is
 */
public class Clock extends Thread{
	
	//Current time as millis past 8:00 am
	private volatile int time = 0;
		
	/**
	 * @return Current time in the format HH:mm
	 */
	public String getTime(){
		// Dividing by 60 gets the hour and the +8 adjusts it correctly
		// e.g 1 hr from the start of the day would be 9.
		String hour = "" + ( (time/10/60) + 8 );
		String minutes = "" + (time/10%60);
		if ((time/10%60) < 10){
			minutes = "0"+minutes;
		}
			
		return hour + ":" + minutes;
	}
	
	/**
	 * @return Current time as millis past 8:00 am
	 */
	public int getTimeMillis(){
		return time;
	}
	
	/**
	 * Every run through the inner loop represents the passing of 1 min
	 * The clock thread will sleep for 10ms to allow other threads to do
	 * stuff in the passing minute
	 */
	public void run(){
		for( int hr = 1; hr <= 9; hr++){
			for( int min = 0; min < 60; min++){
				time += 10;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			System.out.println("Time is : " + getTime());
//				System.out.println("Millis are : " + time);
			}
		}
	}
}

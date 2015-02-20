package com.github.willpascucci.tasklist.model;

//some edits made by Sahil on 2/19
//edits are marked by comments

/**
 * Created by willpascucci on 2/1/15.
 */

public class Event extends Task {

    private boolean skippable;

    public Event(int importance, String name, String description, String deadline,
                 String location, String category, double completed, boolean skippable) {
        super(importance, name, description, deadline, location, category, completed);
        this.skippable = skippable;
    }

    public boolean isSkippable() {
        return skippable;
    }

    public void setSkippable(boolean skippable) {
        this.skippable = skippable;
    }
    
    //edits
    public Calendar startTime= Calendar.getInstance();
	public Calendar endTime= Calendar.getInstance();
	public int intensity;
	public float interval;
	public float workRequired;
	
	
	Scanner scan = new Scanner(System.in);
	
	public void enterNameImportance(){
		System.out.println("Enter title, importance(1-10) of the event");
		this.name= scan.nextLine();
		this.importance= scan.nextInt();
				
	}
	
	public void setStartTime(){
		System.out.println("enter start time as yyyy, mm(0-11), dd(0-23), hh, mm");
		this.startTime.set(Calendar.YEAR, scan.nextInt());
		this.startTime.set(Calendar.MONTH, scan.nextInt());
		this.startTime.set(Calendar.DAY_OF_MONTH, scan.nextInt());
		this.startTime.set(Calendar.HOUR_OF_DAY, scan.nextInt());
		this.startTime.set(Calendar.MINUTE, scan.nextInt());
		
	}
	
	public void setEndTime(){
		System.out.println("enter end time as hh, mm (the date part is assumed to be the same as the start time, in case its not, plz create another event for the next day with start time as midnight)");
		this.endTime.set(Calendar.YEAR, this.startTime.get(Calendar.YEAR));
		this.endTime.set(Calendar.MONTH, this.startTime.get(Calendar.MONTH));
		this.endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
		this.endTime.set(Calendar.HOUR_OF_DAY, scan.nextInt());
		this.endTime.set(Calendar.MINUTE, scan.nextInt());
		
	}
	
	public void setIntensity(){
		System.out.println("How intense is this task on a scale of 1 to 3 where 1 is light "
				+ "and 3 is intense");
		this.intensity= scan.nextInt();
				
	}
	
	public void setInterval(){
		this.interval= this.endTime.get(Calendar.HOUR_OF_DAY) - this.startTime.get(Calendar.HOUR_OF_DAY) - ((this.endTime.get(Calendar.MINUTE) + (60 - this.endTime.get(Calendar.MINUTE)))/60);
	}
	
	public void setWorkRequired(){
		this.workRequired= this.intensity * this.interval;
	}
}

package com.github.willpascucci.tasklist.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

//some edits mady by Sahil, 2/19
//edits are marked by comments

import java.util.Date;
import java.util.List;
import java.util.Calendar;    //edit
import java.util.Scanner;    //edit

/**
 * Created by Nathan Walker on 2/4/15.
 */
@Table(name = "Tasks")
public class Task extends Model {
    @Column(name="Time")        public Date time;
    @Column(name="Text")        public String text;
    @Column(name="Importance")  public int importance;
    @Column(name="Description") public String description;
    @Column(name="Deadline")    public Calendar deadline= Calendar.getInstance();   //edit
    @Column(name="Location")    public String location;
    @Column(name="Category")    public String category;
    @Column(name="% Completed") public double completed;
    @Column(name="Started")     public boolean started;
    @Column(name="Finished")    public boolean finished;
    @Column(name="Working?")    public boolean working;
    @Column(name="Paused Time") public Date pauseTime;
                                public long elapsedTime;
                                public Date startTime;
                                public float timeRequired;          //edit
                                public int percentCompleted= 0;     //edit
                                public int intensity;               //edit

    public Task() {
        super();
        time = new Date();
        pauseTime=null;
        startTime=null;
    }

    public Task(String text) {
        super();
        this.text = text;
        this.importance = 0;
        this.deadline = null;
        this.location = null;
        this.category = null;
        this.completed = 0;
        this.started = false;
        this.finished = false;
        this.working = false;
    }

    public Task(int importance, String text, String description, String deadline,
                String location, String category, double completed, boolean started) {
        this.importance = importance;
        this.text = text;
        this.description = description;
        this.deadline = deadline;
        this.location = location;
        this.category = category;
        this.completed = completed;
        this.started = started;
        this.working = false;
        this.finished = false;
    }

    public void startTask() {
        //assert not finished?
        //error catching assertions handled?
        if(!isStarted()) {
            setStartTime(new Date());
            setWorking(true);
            setStarted(true);
        }
        else if (!isWorking()) {
            setWorking(true);
            setStartTime(new Date());
        }
    }

    public void pauseTask() {
        //assert started and not finished?
        if(isWorking()) {
            //prompt for amount completed
            setWorking(false);
            setPauseTime(new Date());
            setElapsedTime(getElapsedTime()+
                    (getPauseTime().getTime()-getStartTime().getTime()) );
        }
    }

    public Date getStartTime() {return startTime;}

    public void setStartTime(Date startTime) {this.startTime = startTime;}

    public long getElapsedTime() {return elapsedTime;}

    public void setElapsedTime(long elapsedTime) {this.elapsedTime = elapsedTime;}

    public Date getPauseTime() {return pauseTime;}

    public void setPauseTime(Date pauseTime) {this.pauseTime = pauseTime;}

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCompleted() {return completed;}

    public void setCompleted(double completed) {this.completed = completed;}

    public boolean isStarted() {return started;}

    public void setStarted(boolean started) {this.started = started;}

    public boolean isFinished() {return finished;}

    public void setFinished(boolean finished) {this.finished = finished;}

    public boolean isWorking() {return working;}

    public void setWorking(boolean working) {this.working = working;}

    public static List<Task> getAll() {
        return new Select()
                .from(Task.class)
                .orderBy("Time desc")
                .execute();
    }
    
    //edits
    Scanner scan= new Scanner(System.in);
    public void enterNameImportance(){
		System.out.println("Enter title, importance(1-10) of the task");
		this.name= scan.nextLine();
		this.importance= scan.nextInt();
			
	}
	
	public void estimateTimeReq(){
		System.out.println("How much time do you think this task will require? Enter hours and min"); 
		int hour= scan.nextInt();
		int min= scan.nextInt();
		this.timeRequired= hour + min/60;
			
	}
	
	public void setDeadline(){
		System.out.println("enter deadline as yyyy, mm(0-11), dd, hh(0-23), mm");
		this.deadline.set(Calendar.YEAR, scan.nextInt());
		this.deadline.set(Calendar.MONTH, scan.nextInt());
		this.deadline.set(Calendar.DAY_OF_MONTH, scan.nextInt());
		this.deadline.set(Calendar.HOUR_OF_DAY, scan.nextInt());
		this.deadline.set(Calendar.MINUTE, scan.nextInt());
	
	}
	
	public void setIntensity(){
		System.out.println("How intense is this task on a scale of 1 to 3 where 1 is light "
				+ "and 3 is intense");
		this.intensity= scan.nextInt();
			
	}
}

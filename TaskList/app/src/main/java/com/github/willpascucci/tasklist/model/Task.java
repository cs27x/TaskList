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
    // User variables
    @Column(name="Text")        public String text;
    @Column(name="Importance")  public int importance;
    @Column(name="Description") public String description;
    @Column(name="Deadline")    public Date deadline; //edit
    @Column(name="Location")    public String location;
    @Column(name="Category")    public String category;
    @Column(name="Required")    public float timeRequired;          //edit
    @Column(name="Intensity")   public int intensity;               //edit

    // Program variables
    @Column(name="Time")        public Date time;
    @Column(name="% Completed") public double completed;
    @Column(name="Started")     public boolean started;
    @Column(name="Finished")    public boolean finished;
    @Column(name="Working?")    public boolean working;
    @Column(name="Paused Time") public Date pauseTime;
    @Column(name="ElapsedTime") public long elapsedTime;
    @Column(name="StartTime")   public Date startTime;

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

    public Task(int importance, String text, String description, Date deadline,
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
    
	public void setTimeReq(float e){
		this.timeRequired = e;
	}

    public float getTimeReq() {
        return this.timeRequired;
    }

    public int getIntensity() {
        return this.intensity;
    }

	public void setIntensity(int i) {
        this.intensity = i;
	}
}

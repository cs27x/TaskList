/* compiler errors
package com.github.willpascucci.tasklist.api;

// Created by Sahil, 2/19

import java.util.Calendar;
import java.util.Scanner;

public class TimeScheduling {
  public static void main(String [ ] args){
		
		//arrays for tasks and events		
		task[] taskArray = new task[100];
		event[] eventArray = new event[100];
		
		int i= 1, j = 1;
		int countT = 0;
		int countE = 0;
		
		Scanner scan = new Scanner(System.in);
		
		//input tasks and events
		
		while(i == 1){
			System.out.println("task created. enter the details asked");
			task t = new task();
			t.enterNameImportance();
			t.estimateTimeReq();
			t.setDeadline();
			t.setIntensity();
			taskArray[countT] = t;
			countT++;
			System.out.println("\nwant to enter more (1/0)?");
			i = scan.nextInt();
			
		}
		
		while(j == 1){
			System.out.println("event created. enter the details asked");
			event e = new event();
			e.enterNameImportance();
			e.setEndTime();
			e.setStartTime();
			e.setIntensity();
			e.setInterval();
			e.setWorkRequired();
			eventArray[countE]= e;
			countE++;
			System.out.println("\nwant to enter more (1/0)?");
			j = scan.nextInt();
			
		}
		
		// capacity of the user is intensity*time
		//capacity will used for estimating how much work the user can get done in a day
		
		int capacity;
		
		
		//estimate the capacity of the user
		
		
		System.out.println("To begin with, an estimate of your capacity is required. On an average day, "
				+ "how many hours can you work really intensely, how many moderately "
				+ "and how many lightly (enter the three integers respectively)");
		int h, m, l;
		h= scan.nextInt();
		m= scan.nextInt();
		l= scan.nextInt();
		capacity= h*3 + m*2 + l*1;
		
		//sort the task and event array according to importance
				
		
		task tempT;
		event tempE;
		int max;
		
		for(int p=0;p<countT;p++){
			max= taskArray[p].importance;
			for(int q=p+1;q<countT;q++){
				if(taskArray[q].importance>max){
					tempT= taskArray[q];
					taskArray[q]= taskArray[p];
					taskArray[p]= tempT;
				}
			}
		}
		
		
		
		for(int p=0; p<countE;p++){
			max= eventArray[p].importance;
			for(int q=p+1;q<countE;q++){
				if(eventArray[q].importance>max){
					tempE= eventArray[q];
					eventArray[q]= eventArray[p];
					eventArray[p]= tempE;
				}
			}
		}
		
		
		//the following code gets the optimum schedule and stores it in the dayObject class objects stored in an array
		
		int xE=0, xT=0;
		dayObject[] dayObjectArray= new dayObject[100];
		int countD=0;
		
		while(xE<countE && xT<countT){
			
			//when the top element in the event array has higher importance than the one in the task array
			
			if(eventArray[xE].importance >= taskArray[xT].importance){
				int dayExists=0, dayObjectNum=0, timeExists=1;
				
				//to find if the date of the event is already present in the day object array
				
				for(int k=0;k<countD;k++){
					if(eventArray[xE].startTime.get(Calendar.DAY_OF_MONTH) == dayObjectArray[k].day.get(Calendar.DAY_OF_MONTH)){
						dayExists=1;
						dayObjectNum=k;
					}
				}
				
				//if the date exists
				
				if(dayExists==1){
					for(int k=0;k<dayObjectArray[dayObjectNum].countBlocked;k++){
						if(!(eventArray[xE].startTime.after(dayObjectArray[dayObjectNum].blockedEnd[k]) || eventArray[xE].endTime.before(dayObjectArray[dayObjectNum].blockedStart[k]))){
							timeExists=0;
						}
					}
					
					//if the time slot is available
					
					if(timeExists==1){
						dayObjectArray[dayObjectNum].blockedStart[dayObjectArray[dayObjectNum].countBlocked]=eventArray[xE].startTime;
						dayObjectArray[dayObjectNum].blockedEnd[dayObjectArray[dayObjectNum].countBlocked]=eventArray[xE].endTime;
						dayObjectArray[dayObjectNum].dayEvents[dayObjectArray[dayObjectNum].countBlocked]= eventArray[xE];
						dayObjectArray[dayObjectNum].countBlocked++;
						dayObjectArray[dayObjectNum].workDone += eventArray[xE].workRequired;
					}
					
					else System.out.println("there is already an event during this time");
				}
				
				//if the date doesn't exist
				
				else{
					dayObjectArray[countD]= new dayObject();
					dayObjectArray[countD].blockedStart[dayObjectArray[countD].countBlocked]=eventArray[xE].startTime;
					dayObjectArray[countD].blockedEnd[dayObjectArray[countD].countBlocked]=eventArray[xE].endTime;
					dayObjectArray[countD].dayEvents[dayObjectArray[countD].countBlocked]= eventArray[xE];
					dayObjectArray[countD].countBlocked++;
					dayObjectArray[countD].workDone +=eventArray[xE].workRequired;
					countD++;
				}
				
				xE++;
								
			}
			
			//case when the top element in the task array has a higher importance than the one in the event array
			
			else{
				Calendar startDay= Calendar.getInstance();
				int daysLeft= taskArray[xT].deadline.get(Calendar.DAY_OF_MONTH)- startDay.get(Calendar.DAY_OF_MONTH) + 1;
				float dailyPercentage = 100*(1/daysLeft);
				float dailyTime = (taskArray[xT].timeRequired)/daysLeft;
				int countPercent=0, dayObjectNum=0;
				float timeSpent=0;
				
				//until the current task hasn't been divided 
				
				while(countPercent <=99 && startDay.get(Calendar.DAY_OF_MONTH)<= taskArray[xT].deadline.get(Calendar.DAY_OF_MONTH)){
					int dayExists=0;
					for(int k=0;k<countD;k++){
						if(startDay.get(Calendar.DAY_OF_MONTH)==dayObjectArray[k].day.get(Calendar.DAY_OF_MONTH)){
							dayExists=1;
							dayObjectNum=k;
						}
					}
					
					if(dayExists == 1){
						if(dayObjectArray[dayObjectNum].workDone<capacity){
							dayObjectArray[dayObjectNum].dayTasks[dayObjectArray[dayObjectNum].countTask]= taskArray[xT];
							dayObjectArray[dayObjectNum].timeOnTask[dayObjectArray[dayObjectNum].countTask]= dailyTime;
							dayObjectArray[dayObjectNum].percentOfTask[dayObjectArray[dayObjectNum].countTask]= dailyPercentage;
							dayObjectArray[dayObjectNum].countTask++;
							dayObjectArray[dayObjectNum].workDone += dailyTime*taskArray[xT].intensity;
							countPercent += dailyPercentage;
							timeSpent += dailyTime;
							daysLeft--;
						}
						
						else{
							daysLeft--;
							dailyPercentage= (100-countPercent)/daysLeft;
							dailyTime= (taskArray[xT].timeRequired - timeSpent)/daysLeft;
						}
						
						
					}
					else{
						dayObjectArray[countD]= new dayObject();
						dayObjectArray[countD].dayTasks[dayObjectArray[countD].countTask]= taskArray[xT];
						dayObjectArray[countD].timeOnTask[dayObjectArray[countD].countTask]= dailyTime;
						dayObjectArray[countD].percentOfTask[dayObjectArray[countD].countTask]= dailyPercentage;
						dayObjectArray[countD].countTask++;
						dayObjectArray[countD].workDone += dailyTime*taskArray[xT].intensity;
						countD++;
						countPercent += dailyPercentage;
						timeSpent += dailyTime;
						daysLeft--;
					}
					
					startDay.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				if(countPercent < 99){
					System.out.println("the task "+ taskArray[xT].name+" cant be completed in the given time. Please increase capacity");
				}
				
				xT++;
			}
		}
		
		
	}
		
		
}
*/
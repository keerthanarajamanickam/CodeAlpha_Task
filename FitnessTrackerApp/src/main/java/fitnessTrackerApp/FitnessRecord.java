package fitnessTrackerApp;

import java.sql.Date;

public class FitnessRecord {
	
	private int id;
    private Date date;
    private int steps;
    private int workoutTime;
    private int calories;

    public FitnessRecord(Date date, int steps, int workoutTime, int calories) {
        this.date = date;
        this.steps = steps;
        this.workoutTime = workoutTime;
        this.calories = calories;
    }

    // Getters
    public Date getDate() { 
    	return date; 
    }
    public int getSteps() { 
    	return steps; 
    }
    public int getWorkoutTime() { 
    	return workoutTime; 
    }
    public int getCalories() { 
    	return calories; 
    }

}

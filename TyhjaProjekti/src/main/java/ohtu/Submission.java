package ohtu;

import java.util.ArrayList;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;


    public int getDoneCount() {
        Boolean[] exercises = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};

        int doneCount = 0;
        for (int i = 0; i < exercises.length; i++){
            if (exercises[i]){
                doneCount++;
            }
        }
        return doneCount;
    }

    public String getDoneExercises() {
        Boolean[] exercises = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};

        String done = "";
        for (int i = 0; i < exercises.length; i++) {
            if(exercises[i]){
                done += " " + (i + 1);
            }
        }
        return done;
    }

    public String getStudent_number() {
        return student_number;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }
}
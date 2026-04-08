package math;
public class GWACalculator {

    public static void main(String[] args){
        //ask grades from the user
        //  each grade has a grade and no. of units
        //  maintain a running some of grades multiplied with units
        //  totalGrade = totalGrade + grade * units;
        //  totalUnits = totalUnits + units;

        // totalGrade = totalGrade + (grade * units/totalUnits)
        // if done 
        //  display the GWA 
        //   GWA = totalGrade / totalUnits

        float grade = 1.75f;
        double gradeD = 1.75;

        float[] grades = {1.75f, 1.0f, 2.0f, 2.25f};
        int[] units = {5, 5, 3, 3};

        double totalGrades = 0;
        int totalUnits = 0;

        for( int i = 0; i < 4 ; i++)
        {
            totalGrades = totalGrades + grades[i] * units[i];
            totalUnits = totalUnits + units[i];
        }

        //int   unit = 3;

        System.out.println("Your GWA is " + totalGrades/totalUnits);
        if(totalGrades/totalUnits <= 1.75
             && totalGrades/totalUnits > 1.4)
             {
                System.out.println("Congratulations, you graduated Cumlaude!");
             }

    }
    
}

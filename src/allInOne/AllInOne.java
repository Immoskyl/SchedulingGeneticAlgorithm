package allInOne;

/**
 * Created by immoskyl on 25/02/17.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllInOne {

    //attributes
    private int nbGenerations = 0;
    private int popSize = 0;
    private int nbStudents = 0;
    private int nbModules = 0;
    private int nbModulesInCourse = 0;

    private List<List<Integer>> studentsModules;
    private List<List<Integer>> examSchedule;

    private List<List<List<Integer>>> population = new ArrayList<>();
    private List<Integer> populationFitnessCost = new ArrayList<>();

    //getter and setter methods


    public void addToPopulationFitnessCost (int fitnessCost) {
        this.populationFitnessCost.add(fitnessCost);
    }

    public void removeFromPopulationFitnessCost (int index) {
        this.populationFitnessCost.remove(index);
    }

    public int getFitnessCost (int index) {
        return populationFitnessCost.get(index);
    }

    public List<List<Integer>> getOrderingFromPopulation(int index) {
        return population.get(index);
    }

    public void addToPopulation (List<List<Integer>> ordering) {
        this.population.add(ordering);
    }

    public void removeFromPopulation (List<List<Integer>> ordering) {
        this.population.remove(ordering);
    }

    public void removeFromPopulation (int index) {
        this.population.remove(index);
    }

    public int getCurrentPopulationSize() {
        return population.size();
    }

    public List<List<Integer>> getExamSchedule() {
        return examSchedule;
    }

    public void setExamSchedule(List<List<Integer>> examSchedule) {
        this.examSchedule = examSchedule;
    }

    public List<List<Integer>> getStudentsModules() {
        return studentsModules;
    }

    public void setStudentsModules(List<List<Integer>> studentsModules) {
        this.studentsModules = studentsModules;
    }

    public int getNbExamDays() {
        return nbExamDays;
    }

    public void setNbExamDays(int nbExamDays) {
        this.nbExamDays = nbExamDays;
    }

    private int nbExamDays = 0;

    public int getNbGenerations() {
        return nbGenerations;
    }

    public void setNbGenerations(int nbGenerations) {
        this.nbGenerations = nbGenerations;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public int getNbStudents() {
        return nbStudents;
    }

    public void setNbStudents(int nbStudents) {
        this.nbStudents = nbStudents;
    }

    public int getNbModules() {
        return nbModules;
    }

    public void setNbModules(int nbModules) {
        this.nbModules = nbModules;
    }

    public int getNbModulesInCourse() {
        return nbModulesInCourse;
    }

    public void setNbModulesInCourse(int nbModulesInCourse) {
        this.nbModulesInCourse = nbModulesInCourse;
    }

    //methods and procedures

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    } //randomWithRange


    private void studentsModulesArrayCreation () {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> list = new ArrayList<>();

        System.out.println("How do you want to attribute the modules to each student?");
        System.out.println("1 : Random attribution");
        System.out.println("2 : Manual attribution");
        switch (scanner.nextInt()) {
            default:
                System.out.println("Is typing 1 or 2 that difficult?");
                System.out.println("Considering your issues concerning keyboard inputs, we will limit them as much as possible, and for now use the random attribution.");
            case 1: //random attribution
                for (int i = 0; i != getNbStudents(); ++i) {
                    int chosenModule;
                    list.add(new ArrayList<Integer>());

                    //available modules creation and populating
                    ArrayList<Integer> availableModules = new ArrayList<>();
                    for (int k = 0; k != getNbModules(); ++k) {
                        availableModules.add(k + 1);
                    }

                    for (int j = 0; j != getNbModulesInCourse(); ++j) {

                        //selects a random module still available...
                        do {
                            chosenModule = availableModules.get(randomWithRange(0, availableModules.size() - 1));
                        } while (chosenModule == 0);                   //integrity rule: there must be as many or more modules
                        availableModules.set(chosenModule - 1, 0);     //than modules used by a student

                        // ...and adds it
                        list.get(i).add(chosenModule);
                    }
                }
                break;
            case 2: //manual attribution
                for (int i = 0; i != getNbStudents(); ++i) {
                    list.add(new ArrayList<Integer>());
                    System.out.println("Student #" + i + ":");
                    for (int j = 0; j != getNbModulesInCourse(); ++j) {
                        System.out.println("Enter module #" + (j + 1) + " (between 1 and " + getNbModules() + ")");
                        list.get(i).add(scanner.nextInt());
                    }
                }
                break;
        }
        setStudentsModules(list);
    }

    private void basicParametersCreation () {
        String strInput;
        int intInput;
        Scanner scanner = new Scanner(System.in);
        while (true){
            //nb of generetions
            System.out.println("Enter the number of generations to run the program through");
            intInput = scanner.nextInt();
            setNbGenerations(intInput);

            //population size
            System.out.println("Enter the population size (number of randomly generated orderings)");
            intInput = scanner.nextInt();
            setPopSize(intInput);

            //nb of students
            System.out.println("Enter the number of students");
            intInput = scanner.nextInt();
            setNbStudents(intInput);

            //nb of total modules
            System.out.println("Enter the number of total modules available");
            intInput = scanner.nextInt();
            setNbModules(intInput);

            //nb of modules in a course
            System.out.println("Enter the number of modules in a course");
            while (true) {
                intInput = scanner.nextInt();
                if (intInput > getNbModules()) {
                    System.out.println("The number of module in a course must not be greater than the total number of modules!");
                } else {
                    break;
                }
            }
            setNbModulesInCourse(intInput);

            //confirmation
            System.out.println("Are you happy with these values? (y/n)");
            strInput = scanner.nextLine(); //otherwise the required input is somehow skipped...
            strInput = scanner.nextLine(); //
            System.out.println(strInput);
            if (strInput.equals("yes") || strInput.equals("y")) {
                break;
            }
        }

        //nb of exam days = nb of modules / 2
        setNbExamDays(getNbModules()/2);
    } //basicParameterCreation()

    private List<List<Integer>> generateOrdering() {
        List<List<Integer>> list = new ArrayList<>();
        int chosenModule;

        //available modules creation and populating
        ArrayList<Integer> availableModules = new ArrayList<>();
        for (int k = 0; k != getNbModules(); ++k) {
            availableModules.add(k + 1);
        }

        for (int i = 0; i != getNbExamDays(); ++i) { //for every exam day
            list.add(new ArrayList<Integer>());
            for (int j = 0; j != 2; ++j) { //for 2 modules per day

                //selects a random module still available...
                do {
                    chosenModule = availableModules.get(randomWithRange(0, availableModules.size() - 1));
                } while (chosenModule == 0);               //integrity rule: availableModules indexes will all be 0
                availableModules.set(chosenModule - 1, 0);     //when the for loop finishes, so no need to check it

                // ...and adds it
                list.get(i).add(chosenModule);
            }
        }
        return list;
    }


    public void setParameters() {
        basicParametersCreation();
        studentsModulesArrayCreation();
    } //setParameters()

    private boolean areOrderingsDiff() { //only checks if the last ordering is different from the others
        if (getCurrentPopulationSize() == 1) {
            return true;
        }
        for (int i = 0; i != getCurrentPopulationSize() - 1; ++i) {
            if (getOrderingFromPopulation(i) == getOrderingFromPopulation(getCurrentPopulationSize() -1)) {
                return false;
            }
        }
        return true;
    }

    public void generatePopulation () {
        for (int i = 0; i !=getPopSize(); ++i) {
            addToPopulation(generateOrdering());
            while (! areOrderingsDiff()) { //assure different orderings
                removeFromPopulation(getCurrentPopulationSize() - 1);
                addToPopulation(generateOrdering());
            }
        }
    }

    public void generatePopulationFitnessCost () {
        System.out.println("Fitness cost calculating...");

        for (int i = 0; i != getPopSize(); ++i) {
            addToPopulationFitnessCost(calculateFitnessCost(getOrderingFromPopulation(i)));
        }
    }

    public int calculateFitnessCost (List<List<Integer>> ordering) {
        int subSum = 0;
        for (int i = 0; i != getStudentsModules().size(); ++i) {
            if (hasStudentExamOverlapping(getStudentsModules().get(i), ordering)) {
                ++subSum;
            }
        }
        return subSum;
    }

    private boolean hasStudentExamOverlapping(List<Integer> studentModules, List<List<Integer>> ordering) {
        int matchingModules = 0;
        for (List<Integer> pairOfModule : ordering) {
            matchingModules = 0;
            for (Integer j : pairOfModule) {
                for (Integer i : studentModules) {
                   if (i.equals(j)) {
                       ++matchingModules;
                   }
                }
            }
            if (matchingModules == 2) {
                return true;
            }
        }
        return false;
    }


    //read/write files


    private void writeToFile(String address, String text) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(address);
            bw = new BufferedWriter(fw);
            bw.write(text);
        } catch (IOException e) {
            System.out.println("Sorry it seems impossible to write the file");
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                System.out.println("Sorry it seems impossible to write the file");
            }
        }
    }

    public void writeResults() {
        String address = "AI17.txt";
        String newline = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        System.out.println("Results writing...");

        //write students schedules
        for (int i = 0; i != getStudentsModules().size(); ++i) {
            builder.append("Student ");
            builder.append(i);
            builder.append(": ");
            for (Integer module : getStudentsModules().get(i)) {
                builder.append("M");
                builder.append(module);
                builder.append(" ");
            }
            builder.append(newline);
        }

        builder.append(newline);

        //write orderings
        for (int i = 0; i != getCurrentPopulationSize(); ++i) {
            builder.append("Order ");
            builder.append(i);
            builder.append(" (Cost ");
            builder.append(getFitnessCost(i));
            builder.append("):");
            builder.append(newline);
            for (List<Integer> pairOfModule : getOrderingFromPopulation(i)) {
                builder.append("M");
                builder.append(pairOfModule.get(0));
                builder.append(" ");
            }
            builder.append(newline);
            for (List<Integer> pairOfModule : getOrderingFromPopulation(i)) {
                builder.append("M");
                builder.append(pairOfModule.get(1));
                builder.append(" ");
            }
            builder.append(newline);
            builder.append(newline);
        }

        writeToFile(address, builder.toString());
    }

    public static void main(String[] args) {
        AllInOne main = new AllInOne();
        main.setParameters();
        main.generatePopulation();
        main.generatePopulationFitnessCost();
        main.writeResults();
    }
}

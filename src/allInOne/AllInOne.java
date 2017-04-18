package allInOne; //this we are not allowed to deliver more than one class, I called the package and the class
                  //AllInOne, because everything should be separated, but anyway...

/**
 * Intelligent Systems CS4006 Assignement Spring Semester 2016-17
 * University of Limerick
 *
 * Created by Romain ROUX
 * Last modified on 28/02/2017
 * my UL ID: 16083733
 *
 * I assure all work done throuhough this project is done all my myself.
 * Do not hesitate to mail me if any question.
 *
 *
 * file should be named is16083733.java
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

    /**
     * returns a random number including max and excluding min
     * @param min int minimum
     * @param max int maximum
     * @return
     */
    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    } //randomWithRange


    /**
     * generates, rather randomly or specified, the studentModules,
     * considering the parameters already registered in the program.
     * Each student is allocated the number of module in a course among all the module available
     */
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
    } //studentModuleArrayCreation()


    /**
     * asks the user to register the number of generation, the population size, the number of students,
     * the number of total modules, and the number of modules in a course, checking integrity for each parameter and
     * asking confirmation. Then deduces the number of exam days.
     */
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
                    System.out.println("Type again");
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


    /**
     * generates a random ordering of an exam schedule, assuring that every module picked in the ordering is different
     * @return ordering
     */
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
                } while (chosenModule == 0);                   //integrity rule: availableModules indexes will all be 0
                availableModules.set(chosenModule - 1, 0);     //when the for loop finishes, so no need to check it

                // ...and adds it
                list.get(i).add(chosenModule);
            }
        }
        return list;
    } //generatesOrdering()


    /**
     * factorisation procedure
     */
    public void setParameters() {
        basicParametersCreation();
        studentsModulesArrayCreation();
    } //setParameters()


    /**
     * Checks for atomicity of the last ordering in the population
     * Checking only the last ordering is fine because the check is made for every ordering added to the population
     * so every ordering in the population is checked (besides the first, but the first is alone so the check would
     * be pointless)
     * @return true if the last ordering is different from any other in the population
     */
    private boolean areOrderingsDiff() { //
        if (getCurrentPopulationSize() == 1) {
            return true;
        }
        for (int i = 0; i != getCurrentPopulationSize() - 1; ++i) {
            if (getOrderingFromPopulation(i) == getOrderingFromPopulation(getCurrentPopulationSize() -1)) {
                return false;
            }
        }
        return true;
    } //areOrderingDiff()


    /**
     * fill the stored attribute population (its size being already defined) with randomly generated orderings.
     * For each ordering added, it checks if it is different from every other. If not so, removes it, and creates
     * another randomly generated ordering, and tests it again, until a different one is created.
     */
    public void generatePopulation () {
        for (int i = 0; i !=getPopSize(); ++i) {
            addToPopulation(generateOrdering());
            while (! areOrderingsDiff()) { //assure different orderings
                removeFromPopulation(getCurrentPopulationSize() - 1);
                addToPopulation(generateOrdering());
            }
        }
    } //generateInitialPopulation()


    /**
     * Calculates the fitness cost of each ordering in the population, and stores them in the PopulationFitnessCost
     * list, that shares the same index than the Population list
     */
    public void generatePopulationFitnessCost () {
        System.out.println("Fitness cost calculating...");

        for (int i = 0; i != getPopSize(); ++i) {
            addToPopulationFitnessCost(calculateFitnessCost(getOrderingFromPopulation(i)));
        }
    } //generateInitialPopulationFitnessCost()


    /**
     * Calculates the fitness cost of a given ordering, and returns the fitness cost as an int
     * @param ordering List<List<Integer>>
     * @return fitness cost int
     */
    public int calculateFitnessCost (List<List<Integer>> ordering) {
        int subSum = 0;
        for (int i = 0; i != getStudentsModules().size(); ++i) {
            if (hasStudentExamOverlapping(getStudentsModules().get(i), ordering)) {
                ++subSum;
            }
        }
        return subSum;
    } //calculateFitnessCost()


    /**
     * Checks overlapping modules of each student for a given ordering.
     * @param studentModules List<integer> eg. the list of students, and their respective modules
     * @param ordering List<List<Integer>>
     * @return true if at least one of the students has two modules taking place the same day eg. in the same PairOfModules
     */
    private boolean hasStudentExamOverlapping(List<Integer> studentModules, List<List<Integer>> ordering) {
        int matchingModules;
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
    } //hasStudentExamOverlapping()


    //write files

    /**
     * generic file writing procedure, handling i/o 0exceptions
     * @param address String address of the file to write (creates it if it does not already exists)
     * @param text String text to write in the file
     */
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
    } //writeToFile()


    /**
     * writes the wanted output ofr the interim submission on the right file
     */
    public void writeResults() {
        String address = "AI17.txt";
        String newline = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        System.out.println("Results writing...");

        //write students schedules
        for (int i = 0; i != getStudentsModules().size(); ++i) {
            builder.append("Student ");
            builder.append(i + 1);
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
            builder.append(i + 1);
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
    } //writeBestResults()


    public void iterate () {
        for (int i = 0; i != nbGenerations; ++i) {
            //selection
            //random
            //reproduction
            //mutation
            //crossover
        }
    }

    /**
     * pretty straight forward main. Just read the methods' names adn it should be fine to understand what's going on
     * Everything else is detailed in the other functions, and the handout given for the project.
     * @param args we don't need that
     */
    public static void main(String[] args) {
        AllInOne main = new AllInOne();
        main.setParameters();
        main.generatePopulation();
        main.generatePopulationFitnessCost();
        main.writeResults();
        main.iterate();
        main.writeResults(); //basic output
    } //psvm()
}

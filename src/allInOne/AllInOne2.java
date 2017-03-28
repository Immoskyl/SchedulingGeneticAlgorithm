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
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllInOne2 {

    //attributes

    private int chancesOfMutation = 0;
    private int chancesOfCrossover = 0;

    private int nbGenerations = 0;
    private int popSize = 0;
    private int nbStudents = 0;
    private int nbModules = 0;
    private int nbModulesInCourse = 0;

    private List<List<Integer>> studentsModules;
    private List<List<Integer>> crossoverOrdering = null;
    private int crossoverOrderingIndex = 0;

    private List<List<List<List<Integer>>>> populations = new ArrayList<>();
    private List<List<Integer>> populationFitnessCosts = new ArrayList<>();


    //getter and setter methods

    public void overwriteOrderingInPopulation(int index, List<List<Integer>> ordering, int generation) {
        populations.get(generation).set(index, ordering);
    }

    public void overwritePopulationFitnessCost(int index, int fitness, int generation) {
        populationFitnessCosts.get(generation).set(index, fitness);
    }

    public void addToPopulationFitnessCost (int generation, int fitnessCost) {
        this.populationFitnessCosts.get(generation).add(fitnessCost);
    }

    public int getCrossoverOrderingIndex() {
        return crossoverOrderingIndex;
    }

    public void setCrossoverOrderingIndex(int crossoverOrderingIndex) {
        this.crossoverOrderingIndex = crossoverOrderingIndex;
    }

    public void removeFromPopulationFitnessCost (int generation, int index) {
        this.populationFitnessCosts.get(generation).remove(index);
    }

    public int getFitnessCost (int generation, int index) {
        return populationFitnessCosts.get(generation).get(index);
    }

    public List<List<Integer>> getOrderingFromPopulation(int generation, int index) {
        return populations.get(generation).get(index);
    }

    public void addToPopulation (int generation, List<List<Integer>> ordering) {
        this.populations.get(generation).add(ordering);
    }

    public void iterateGeneration() {
        populations.add(populations.get(getGeneration())); //verrrrrrrrrrrrry cheesy line (as it is base on populations size it increments)
    }

    public int getGeneration() {
        return populations.size();
    }

    public void removeFromPopulation (int generation, List<List<Integer>> ordering) {
        this.populations.get(generation).remove(ordering);
    }

    public void removeFromPopulation (int generation, int index) {
        this.populations.get(generation).remove(index);
    }

    public int getCurrentPopulationSize(int generation) {
        return populations.get(generation).size();
    }

    public List<List<Integer>> getCrossoverOrdering() {
        return crossoverOrdering;
    }

    public void setCrossoverOrdering(List<List<Integer>> crossoverOrdering) {
        this.crossoverOrdering = crossoverOrdering;
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

    public int getChancesOfMutation() {
        return chancesOfMutation;
    }

    public void setChancesOfMutation(int chancesOfMutation) {
        this.chancesOfMutation = chancesOfMutation;
    }

    public int getChancesOfCrossover() {
        return chancesOfCrossover;
    }

    public void setChancesOfCrossover(int chancesOfCrossover) {
        this.chancesOfCrossover = chancesOfCrossover;
    }

    public List<List<List<Integer>>> getPopulation(int generation) {
        return populations.get(generation);
    }

    public void setPopulation(int generation, List<List<List<Integer>>> population) {
        this.populations.set(generation, population);
    }

    public List<Integer> getPopulationFitnessCost(int generation) {
        return populationFitnessCosts.get(generation);
    }

    public void setPopulationFitnessCost(int generation, List<Integer> populationFitnessCosts) {
        this.populationFitnessCosts.set(generation, populationFitnessCosts);
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


    private void sortPopulation () {
        List<List<List<Integer>>> pop = getPopulation(getGeneration());
        List<Integer> fitness = getPopulationFitnessCost(getGeneration());


        //bubble sort implementation
        for (int i = (fitness.size() - 1); i >= 0; i--)
        {
            for (int j = 1; j <= i; j++) //quadratic time but it does the job
            {
                if (fitness.get(j - 1) > fitness.get(j)) {
                    //fitness
                    int fitnessTemp = fitness.get(j - 1);
                    fitness.set(j - 1, fitness.get(j));
                    fitness.set(j, fitnessTemp);

                    //population
                    List<List<Integer>> popTemp = pop.get(j - 1);
                    pop.set(j - 1, pop.get(j));
                    pop.set(j, popTemp);
                }
            }
        }

        setPopulation(getGeneration(), pop);
        setPopulationFitnessCost(getGeneration(), fitness);
    }


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
     * asks the user to register the number of generation, the populations size, the number of students,
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

            //populations size
            System.out.println("Enter the populations size (number of randomly generated orderings)");
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
     * Checks for atomicity of the last ordering in the populations
     * Checking only the last ordering is fine because the check is made for every ordering added to the populations
     * so every ordering in the populations is checked (besides the first, but the first is alone so the check would
     * be pointless)
     * @return true if the last ordering is different from any other in the populations
     */
    private boolean areOrderingsDiff() { //
        if (getCurrentPopulationSize(getGeneration()) == 1) {
            return true;
        }
        for (int i = 0; i != getCurrentPopulationSize(getGeneration()) - 1; ++i) {
            if (getOrderingFromPopulation(getGeneration(), i) == getOrderingFromPopulation(getGeneration(), getCurrentPopulationSize(getGeneration()) -1)) {
                return false;
            }
        }
        return true;
    } //areOrderingDiff()


    /**
     * fill the stored attribute populations (its size being already defined) with randomly generated orderings.
     * For each ordering added, it checks if it is different from every other. If not so, removes it, and creates
     * another randomly generated ordering, and tests it again, until a different one is created.
     */
    public void generatePopulation () {
        for (int i = 0; i !=getPopSize(); ++i) {
            addToPopulation(getGeneration(), generateOrdering());
            while (! areOrderingsDiff()) { //assure different orderings
                removeFromPopulation(getGeneration(), getCurrentPopulationSize(getGeneration()) - 1);
                addToPopulation(getGeneration(), generateOrdering());
            }
        }
    } //generatePopulation()


    /**
     * Calculates the fitness cost of each ordering in the populations, and stores them in the PopulationFitnessCost
     * list, that shares the same index than the Population list
     */
    public void generatePopulationFitnessCost () {
        System.out.println("Fitness cost calculating...");

        for (int i = 0; i != getPopSize(); ++i) {
            addToPopulationFitnessCost(getGeneration(), calculateFitnessCost(getOrderingFromPopulation(getGeneration(), i)));
        }
    } //generatePopulationFitnessCost()


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
        for (int i = 0; i != getCurrentPopulationSize(getGeneration()); ++i) {
            builder.append("Order ");
            builder.append(i + 1);
            builder.append(" (Cost ");
            builder.append(getFitnessCost(getGeneration(), i));
            builder.append("):");
            builder.append(newline);
            for (List<Integer> pairOfModule : getOrderingFromPopulation(getGeneration(), i)) {
                builder.append("M");
                builder.append(pairOfModule.get(0));
                builder.append(" ");
            }
            builder.append(newline);
            for (List<Integer> pairOfModule : getOrderingFromPopulation(getGeneration(), i)) {
                builder.append("M");
                builder.append(pairOfModule.get(1));
                builder.append(" ");
            }
            builder.append(newline);
            builder.append(newline);
        }

        writeToFile(address, builder.toString());
    } //writeResults()


    public void iterate () {
        do {
            iterateGeneration();
            selection();

            //modify orderings
            for (int i = 0; i != getCurrentPopulationSize(getGeneration()); ++i) {
                selectGATechniqueOnOrdering(i);
            }

            //calculate new fitness costs
            for (int i = 0; i != getCurrentPopulationSize(getGeneration()); ++i) { //double loop to consider all the crossover orderings
                overwritePopulationFitnessCost(i, calculateFitnessCost(getOrderingFromPopulation(getGeneration(), i)), getGeneration()); //calculate new fitness cost
            }
        } while (getGeneration() != getNbGenerations());
    }

    public void selectGATechniqueOnOrdering(int i) {
        int randomNumber;
        randomNumber = randomWithRange(0, 100);
        if (randomNumber <= getChancesOfMutation()) { //mutation
            mutation(getOrderingFromPopulation(getGeneration(), i), i);
        } else if (randomNumber <= (getChancesOfMutation() + getChancesOfCrossover())) { //crossover
            if (getCrossoverOrdering() != null) {
                crossover(getOrderingFromPopulation(getGeneration(), i), i , getCrossoverOrdering(), getCrossoverOrderingIndex());
                setCrossoverOrdering(null);
            } else if (i < (getCurrentPopulationSize(getGeneration()) - 1)) { //needs at least 2 orderings
                setCrossoverOrdering(getOrderingFromPopulation(getGeneration(), i));
                setCrossoverOrderingIndex(i);
            } else {
                selectGATechniqueOnOrdering(i); //reroll for the ordering
            }
        } //else reproduction so nothing happens
    }

    public void selection() {
        sortPopulation();

        //replace the third sub group by the first one
        int part = getCurrentPopulationSize(getGeneration()) / 3; //can "miss" possibly 2 orderings at the beginning of the array
        for (int i = getCurrentPopulationSize(getGeneration()); i != part * 2; --i) { //divide in 3 equal sub groups
            overwriteOrderingInPopulation(i, getOrderingFromPopulation(getGeneration(), i - (part * 2)), getGeneration());
            overwritePopulationFitnessCost(i, getFitnessCost(getGeneration(), i - (part * 2)), getGeneration());
        }
    }

    public void mutation(List<List<Integer>> ordering, int index) {
        int rand1= randomWithRange(0, nbModules);
        int rand2;
        do {
            rand2 = randomWithRange(0, nbModules);
        } while (rand1 == rand2);
        List<Integer> temp = ordering.get(rand1);
        ordering.set(rand1, ordering.get(rand2));
        ordering.set(rand2, temp);
        overwriteOrderingInPopulation(index, ordering, getGeneration());
        //swap randomly 2 modules
    }

    public void crossover(List<List<Integer>> ordering1, int index1, List<List<Integer>> ordering2, int index2) {
        //crossover stuff


        overwriteOrderingInPopulation(index1, ordering1, getGeneration());
        overwriteOrderingInPopulation(index2, ordering2, getGeneration());
    }

    /**
     * pretty straight forward main. Just read the methods' names adn it should be fine to understand what's going on
     * Everything else is detailed in the other functions, and the handout given for the project.
     * @param args we don't need that
     */
    public static void main(String[] args) {
        AllInOne2 main = new AllInOne2();
        main.setParameters();
        main.generatePopulation();
        main.generatePopulationFitnessCost();
        main.writeResults();
        main.iterate();
        main.writeResults(); //basic output
    } //psvm()
}

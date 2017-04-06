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

    /*-****************************************************************************************************************/
    /*                                          class related                                                         */
    /*-****************************************************************************************************************/

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
    //
    //overload of methods with the generation parameter to facilitate the comprehension of the business logic part
    //every method without the generation attribute assume the requested population is the last generation one

    public void overwriteOrderingInPopulation(int index, List<List<Integer>> ordering, int generation) {
        populations.get(generation).set(index, ordering);
    }


    public void overwriteOrderingInPopulation(int index, List<List<Integer>> ordering) {
        overwriteOrderingInPopulation(index, ordering, getGeneration());
    }

    public void overwritePopulationFitnessCost(int index, int fitness, int generation) {
        populationFitnessCosts.get(generation).set(index, fitness);
    }

    public void overwritePopulationFitnessCost(int index, int fitness) {
        overwritePopulationFitnessCost(index, fitness, getGeneration());
    }

    public void addToPopulationFitnessCost (int generation, int fitnessCost) {
        this.populationFitnessCosts.get(generation).add(fitnessCost);
    }

    public void addToPopulationFitnessCost (int fitnessCost) {
        addToPopulationFitnessCost(getGeneration(), fitnessCost);
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

    public void removeFromPopulationFitnessCost (int index) {
        removeFromPopulationFitnessCost(getGeneration(), index);
    }

    public int getFitnessCost (int generation, int index) {
        return populationFitnessCosts.get(generation).get(index);
    }

    public int getFitnessCost (int index) {
        return getFitnessCost(getGeneration(), index);
    }

    public List<List<Integer>> getOrderingFromPopulation(int generation, int index) {
        return populations.get(generation).get(index);
    }

    public List<List<Integer>> getOrderingFromPopulation(int index) {
        return getOrderingFromPopulation(getGeneration(), index);
    }

    public void addToPopulation (int generation, List<List<Integer>> ordering) {
        this.populations.get(generation).add(ordering);
    }

    public void addToPopulation (List<List<Integer>> ordering) {
        addToPopulation(getGeneration(), ordering);
    }

    public void initializePop(List<List<List<Integer>>> firstGeneration) {
        populations.add(firstGeneration);
    }

    public void initializeFitnessCost(List<Integer> firstGeneration) {
        populationFitnessCosts.add(firstGeneration);
    }

    public void iterateGeneration() {
        populations.add(populations.get(getGeneration())); //verrrrrrrrrrrrry cheesy line (as it is base on populations size it increments)
        populationFitnessCosts.add(populationFitnessCosts.get(getGeneration() - 1)); //get egneration will return the size +1
    }

    public int getGeneration() {
        return (populations.size() - 1);
    }

    public void removeFromPopulation (int generation, List<List<Integer>> ordering) {
        this.populations.get(generation).remove(ordering);
    }

    public void removeFromPopulation (List<List<Integer>> ordering) {
        removeFromPopulation(getGeneration(), ordering);
    }

    public void removeFromPopulation (int generation, int index) {
        this.populations.get(generation).remove(index);
    }

    public void removeFromPopulation (int index) {
        removeFromPopulation(getGeneration(), index);
    }

    public int getCurrentPopulationSize(int generation) {
        return populations.get(generation).size();
    }

    public int getCurrentPopulationSize() {
        return getCurrentPopulationSize(getGeneration());
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

    public List<List<List<Integer>>> getPopulation() {
        return getPopulation(getGeneration());
    }

    public void setPopulation(int generation, List<List<List<Integer>>> population) {
        this.populations.set(generation, population);
    }

    public void setPopulation(List<List<List<Integer>>> population) {
        setPopulation(getGeneration(), population);
    }

    public List<Integer> getPopulationFitnessCost(int generation) {
        return populationFitnessCosts.get(generation);
    }


    public List<Integer> getPopulationFitnessCost() {
        return getPopulationFitnessCost(getGeneration());
    }

    public void setPopulationFitnessCost(int generation, List<Integer> populationFitnessCosts) {
        this.populationFitnessCosts.set(generation, populationFitnessCosts);
    }

    public void setPopulationFitnessCost(List<Integer> populationFitnessCosts) {
        setPopulationFitnessCost(getGeneration(), populationFitnessCosts);
    }

    //methods and procedures


    /*-****************************************************************************************************************/
    /*                                          initialization                                                        */
    /*-****************************************************************************************************************/

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
                System.out.println("Considering your issues concerning keyboard inputs, we will limit them as much as possible, and for this time use the random attribution.");
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

            //percentage of mutation
            System.out.println("Enter the probability over 100 of mutation of the genetic algorithm");
            while (true) {
                intInput = scanner.nextInt();
                if (intInput > 100) {
                    System.out.println("The probability must not be greater than 100 (100 / 100)");
                    System.out.println("Type again");
                } else {
                    break;
                }
            }
            setChancesOfMutation(intInput);

            //percentage of crossover
            System.out.println("Enter the probability over 100 of crossover of the genetic algorithm");
            while (true) {
                intInput = scanner.nextInt();
                if (intInput > 100 - getChancesOfMutation()) {
                    System.out.println("The probability must not be greater than " + (100 - getChancesOfMutation()) + " (probability of crossover plus mutation / 100)");
                    System.out.println("Type again");
                } else {
                    break;
                }
            }
            setChancesOfCrossover(intInput);

            System.out.println("the probability of Reproduction is then " + (100 - (getChancesOfCrossover() + getChancesOfMutation())) + " / 100");

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
        if (getCurrentPopulationSize() == 1) {
            return true;
        }
        for (int i = 0; i != getCurrentPopulationSize() - 1; ++i) {
            if (getOrderingFromPopulation(i) == getOrderingFromPopulation(getCurrentPopulationSize() - 1)) {
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
    public void generateInitialPopulation() {
        List<List<List<Integer>>> firstGen = new ArrayList<>();
        initializePop(firstGen);
        for (int i = 0; i !=getPopSize(); ++i) {
            addToPopulation(generateOrdering());
            while (! areOrderingsDiff()) { //assure different orderings
                removeFromPopulation(getCurrentPopulationSize());
                addToPopulation(generateOrdering());
            }
        }
    } //generateInitialPopulation()


    /**
     * Calculates the fitness cost of each ordering in the populations, and stores them in the PopulationFitnessCost
     * list, that shares the same index than the Population list
     */
    public void generateInitialPopulationFitnessCost() {
        System.out.println("Fitness cost calculating...");
        List<Integer> firstGen = new ArrayList<>();
        initializeFitnessCost(firstGen);
        for (int i = 0; i != getPopSize(); ++i) {
            addToPopulationFitnessCost((calculateFitnessCost(getOrderingFromPopulation(i))));
        }
    } //generateInitialPopulationFitnessCost()



    /*-****************************************************************************************************************/
    /*                                          business logic                                                        */
    /*-****************************************************************************************************************/

    /**
     * returns a random number including max and excluding min
     * @param min int minimum
     * @param max int maximum
     * @return randomNumber
     */
    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    } //randomWithRange


    private void sortPopulation () {
        List<List<List<Integer>>> pop = getPopulation();
        List<Integer> fitness = getPopulationFitnessCost();


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

        setPopulation(pop);
        setPopulationFitnessCost(fitness);
    }


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


    /*-****************************************************************************************************************/
    /*                                          iterate generations                                                   */
    /*-****************************************************************************************************************/

    public void iterate () {
        //temporary
        int gen = 0;
        do {
            System.out.println("iterate");
            System.out.println("generation " + gen);
            iterateGeneration();
            selection();

            //modify orderings in the new generation
            for (int i = 0; i != getCurrentPopulationSize(); ++i) {
                System.out.println("on ordering " + i + " on " + getCurrentPopulationSize() + ":");
                selectGATechniqueOnOrdering(i);
            }
            if (getCrossoverOrdering() != null) { //security if a single ordering has been chosen for crossover
                mutation(getCrossoverOrdering(), getCrossoverOrderingIndex());
            }

            //calculate new fitness costs
            for (int i = 0; i != getCurrentPopulationSize(); ++i) { //2 different loops to consider all the crossover orderings
                overwritePopulationFitnessCost(i, calculateFitnessCost(getOrderingFromPopulation(i))); //calculate new fitness cost
            }

            //temporary
            ++gen;
            writeResults("generation" + gen); //basic output
        } while (getGeneration() != getNbGenerations());
    }

    public void selectGATechniqueOnOrdering(int i) {
        int randomNumber;
        randomNumber = randomWithRange(0, 100);
        if (randomNumber <= getChancesOfMutation()) { //mutation
            mutation(getOrderingFromPopulation(i), i);
        } else if (randomNumber <= (getChancesOfMutation() + getChancesOfCrossover())) { //crossover
            if (getCrossoverOrdering() != null) {
                crossover(getOrderingFromPopulation(i), i , getCrossoverOrdering(), getCrossoverOrderingIndex());
                setCrossoverOrdering(null);
            } else if (i < (getCurrentPopulationSize())) { //needs at least 2 orderings
                setCrossoverOrdering(getOrderingFromPopulation(i));
                setCrossoverOrderingIndex(i);
            } else {
                selectGATechniqueOnOrdering(i); //reroll for the ordering
            }
        } //else reproduction so nothing happens
        else {System.out.println("reproduction");}
    }

    public void selection() {
        sortPopulation();

       //               /!\ not sure about the corectness of the algo
        //replace the third sub group by the first one
        int part = getCurrentPopulationSize() / 3; //can "add" possibly 2 orderings to the copying due to the division rest
        for (int i = getCurrentPopulationSize() - 1; i < part * 2; --i) { //divide in 3 equal sub groups
            System.out.println("selection: index " + i + " - part " + part + " *2");
            overwriteOrderingInPopulation(i, getOrderingFromPopulation(i - (part * 2)));
            overwritePopulationFitnessCost(i, getFitnessCost(i - (part * 2)));
        }
    }

    public void mutation(List<List<Integer>> ordering, int index) {
        System.out.println("mutation");
        int rand1= randomWithRange(0, getNbModules() - 1);
        int rand2;
        do {
            rand2 = randomWithRange(0, getNbModules() - 1);
        } while (rand1 == rand2);

        //col = rand / 2 and row = rand % 2
        int temp = ordering.get(rand1 / 2).get(rand1 % 2);
        ordering.get(rand1 / 2).set(rand1 % 2, ordering.get(rand2 / 2).get(rand2 % 2));
        ordering.get(rand2 / 2).set(rand2 % 2, temp);
        overwriteOrderingInPopulation(index, ordering);
        //swap randomly 2 modules
    }

    public void crossover(List<List<Integer>> ordering1, int index1, List<List<Integer>> ordering2, int index2) {
        System.out.println("crossover");

        System.out.println("ordering1 before crossover" + ordering1);
        System.out.println("ordering2 before crossover" + ordering2);

        int cutPoint = randomWithRange(1, getNbModules() - 2); //must at least cut one module

        System.out.println("cutpoint " + cutPoint);

        List<Integer> temp = new ArrayList<>();


        for (int i = 0; i != cutPoint; ++i) {
            temp.add(ordering1.get(i / 2).get(i % 2));
            ordering1.get(i / 2).set(i % 2, ordering2.get(i / 2).get(i % 2));
            ordering2.get(i / 2).set(i % 2, temp.get(i));
        }

        /*
        old stuff


        //checking for duplicates


        int moduleCheck;
        List<Integer> test1 = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();
        List<Integer> duplicate1 = new ArrayList<>();
        List<Integer> duplicate2 = new ArrayList<>();
        int crossedPointer1to2 = 0;
        int crossedPointer2to1 = 0;

        for (int i = 0; i != getNbModules(); ++i) {
            //ordering1
            moduleCheck = ordering1.get(i / 2).get(i % 2);
            if(! test1.contains(moduleCheck)) {
                test1.add(moduleCheck);
            } else { duplicate1.add(moduleCheck);}

            //ordering2
            moduleCheck = ordering2.get(i / 2).get(i % 2);
            if(! test2.contains(moduleCheck)) {
                test2.add(moduleCheck);
            } else {
                duplicate2.add(moduleCheck);
            }
        }

        //replacing duplicated values by lost values

        System.out.println("duplicate size:" + duplicate1.size() + " " + duplicate2.size());
        System.out.println("duplicate1" + duplicate1);
        System.out.println("duplicate1" + duplicate2);
        System.out.println("ordering size:" + ordering1.size() + " " + ordering2.size());
        System.out.println("ordering1" + ordering1);
        System.out.println("ordering2" + ordering2);
        System.out.println("nb of modules:" + getNbModules());
        for (int i = 0; i != getNbModules(); ++i) {
            for (int j = 0; j != duplicate1.size(); ++j) {
               if (ordering1.get(i / 2).get(i % 2).equals(duplicate1.get(j))) {
                   System.out.println("i == " + i);
                   System.out.println("duplicate crossed pointer 1 to 2 " + crossedPointer1to2);
                   ordering1.get(i / 2).set(i % 2, duplicate2.get(crossedPointer1to2));
                   ++crossedPointer1to2;
               }
               if (ordering2.get(i / 2).get(i % 2).equals(duplicate2.get(j))) {
                   System.out.println("i == " + i);
                   System.out.println("duplicate crossed pointer 2 to 1 " + crossedPointer2to1);
                   ordering2.get(i / 2).set(i % 2, duplicate1.get(crossedPointer2to1));
                   ++crossedPointer2to1;
               }
            }
        }

         */

        System.out.println("ordering1" + ordering1);
        System.out.println("ordering2" + ordering2);

        //checking for duplicates


        List<List<Integer>> test = new ArrayList<>();       //index 0 is for ordering1, 1 is for ordering2
        List<List<Integer>> duplicates = new ArrayList<>();
        List<List<Integer>> dupPos = new ArrayList<>();

        test.add(new ArrayList<Integer>());
        test.add(new ArrayList<Integer>());
        duplicates.add(new ArrayList<Integer>());
        duplicates.add(new ArrayList<Integer>());
        dupPos.add(new ArrayList<Integer>());
        dupPos.add(new ArrayList<Integer>());

        for (int i = 0; i != getNbModules(); ++i) {
            //ordering1
            if (! test.get(0).contains(ordering1.get(i/2).get(i%2))) {
               test.get(0).add(ordering1.get(i/2).get(i%2));
            } else {
                duplicates.get(0).add(ordering1.get(i/2).get(i%2));
                dupPos.get(0).add(i);
            }
            //ordering2
            if (! test.get(1).contains(ordering2.get(i/2).get(i%2))) {
                test.get(1).add(ordering2.get(i/2).get(i%2));
            } else {
                duplicates.get(1).add(ordering2.get(i/2).get(i%2));
                dupPos.get(1).add(i);
            }
        }

        //replacing duplicated values by lost values


        Integer pos;
        Integer val;
        for (int i = 0; i != duplicates.get(0).size(); ++i) { //the duplicates have the same size
            //ordering1
            pos = dupPos.get(0).get(i);
            val = duplicates.get(1).get(i);
            ordering1.get(pos / 2).set(pos % 2, val);
            //ordering2
            pos = dupPos.get(1).get(i);
            val = duplicates.get(0).get(i);
            ordering1.get(pos / 2).set(pos % 2, val);
        }

        overwriteOrderingInPopulation(index1, ordering1);
        overwriteOrderingInPopulation(index2, ordering2);
    }

    /*-****************************************************************************************************************/
    /*                                          output                                                                */
    /*-****************************************************************************************************************/

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
    public void writeResults(String address) {
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
    } //writeResults()


    /**
     * pretty straight forward main. Just read the methods' names adn it should be fine to understand what's going on
     * Everything else is detailed in the other functions, and the handout given for the project.
     * @param args we don't need that
     */
    public static void main(String[] args) {
        AllInOne2 main = new AllInOne2();
        main.setParameters();
        main.generateInitialPopulation();
        main.generateInitialPopulationFitnessCost();
        main.writeResults("AI17-base.txt");
        main.iterate();
        main.writeResults("AI17-final.txt"); //basic output
    } //psvm()
}

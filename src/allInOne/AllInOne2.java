package allInOne; //this we are not allowed to deliver more than one class, I called the package
                  //AllInOne, because everything should be separated, but anyway...

/**
 * Intelligent Systems CS4006 Assignement Spring Semester 2016-17
 * University of Limerick
 *
 * Created by Romain ROUX
 * Last modified on 20/04/2017
 * my UL ID: 16083733
 *
 * I assure all work done throuhough this project is done all my myself.
 * Do not hesitate to mail me if any question.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    private String bestOutputPerGen;

    private boolean printAllGens = false;
    private boolean graphRep = false;

    //getter and setter methods
    //
    //overload of methods with the generation parameter to facilitate the comprehension of the business logic part
    //every method without the generation attribute assume the requested population is the last generation one

    public void overwriteOrderingInPopulation(int index, List<List<Integer>> ordering, int generation) {
        populations.get(generation).set(index, copyOrdering(ordering)); //copy by value of ordering and all its content
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
        populations.add(populations.get(getGeneration()));
        populationFitnessCosts.add(populationFitnessCosts.get(getGeneration() - 1)); //getGeneration() will return the size +1
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

    public String getBestOutputPerGen() {
        return bestOutputPerGen;
    }

    public void setBestOutputPerGen(String bestOutputPerGen) {
        this.bestOutputPerGen = bestOutputPerGen;
    }

    public boolean isPrintAllGens() {
        return printAllGens;
    }

    public void setPrintAllGens(boolean printAllGens) {
        this.printAllGens = printAllGens;
    }

    public boolean isGraphRep() {
        return graphRep;
    }

    public void setGraphRep(boolean graphRep) {
        this.graphRep = graphRep;
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
                    for (int k = 0; k != getNbModules(); ++k)
                        availableModules.add(k + 1);

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
                } else
                    break;
            }
            setNbModulesInCourse(intInput);

            //percentage of mutation
            System.out.println("Enter the probability over 100 of mutation of the genetic algorithm");
            while (true) {
                intInput = scanner.nextInt();
                if (intInput > 100) {
                    System.out.println("The probability must not be greater than 100 (100 / 100)");
                    System.out.println("Type again");
                } else
                    break;
            }
            setChancesOfMutation(intInput);

            //percentage of crossover
            System.out.println("Enter the probability over 100 of crossover of the genetic algorithm");
            while (true) {
                intInput = scanner.nextInt();
                if (intInput > 100 - getChancesOfMutation()) {
                    System.out.println("The probability must not be greater than " + (100 - getChancesOfMutation()) + " (probability of crossover plus mutation / 100)");
                    System.out.println("Type again");
                } else
                    break;
            }
            setChancesOfCrossover(intInput);

            System.out.println("the probability of Reproduction is then " + (100 - (getChancesOfCrossover() + getChancesOfMutation())) + " / 100");

            //confirmation
            System.out.println("Are you happy with these values? (y/n)");
            strInput = scanner.nextLine(); //otherwise the required input is somehow skipped...
            strInput = scanner.nextLine(); //
            System.out.println(strInput);
            if (strInput.equals("yes") || strInput.equals("y") || strInput.equals("Y") || strInput.equals("YES"))
                break;
        }

        System.out.println("There are two exams per exam day, and one per module, so there will be " + getNbModules()/2 + " exam days");
        setNbExamDays(getNbModules()/2);
    } //basicParameterCreation()

    /**
     * ask the user for the setting oof two optionnal outputs:
     * -a set of files, each containing the states of the orderings of one generation
     * -a graph representation of the best fitness cost per generation
     * with the fitness cost on the abscissa and the generation on the ordinate
     * Keep in mind the graph rep is really simple and just there to give the user a better idea of the efficiency
     * of the algo. The results in this representation are still white-space parsable for further interpretation
     */
    private void setOptions() {
        String strInput;
        Scanner scanner = new Scanner(System.in);

        //print of every generation
        System.out.println("OPTIONAL: Do you want to keep track of details of every step? (y/n)");
        System.out.println("(A file describing each ordering will be created for every generation)");
        strInput = scanner.nextLine();
        if (strInput.equals("yes") || strInput.equals("y") || strInput.equals("Y") || strInput.equals("YES"))
            setPrintAllGens(true);

        //print a simple ASCII graph
        System.out.println("OPTIONAL: Do you want to print the result as a graph (y/n)");
        System.out.println("(Very simple ASCII graph representation to help to visualize the effectiveness of the algorithm)");
        strInput = scanner.nextLine();
        if (strInput.equals("yes") || strInput.equals("y") || strInput.equals("Y") || strInput.equals("YES"))
            setGraphRep(true);
    } //setOptions()

    /**
     * generates a random ordering of an exam schedule, assuring that every module picked in the ordering is different
     * @return ordering
     */
    private List<List<Integer>> generateOrdering() {
        List<List<Integer>> list = new ArrayList<>();
        int chosenModule;

        //available modules creation and populating
        ArrayList<Integer> availableModules = new ArrayList<>();
        for (int k = 0; k != getNbModules(); ++k)
            availableModules.add(k + 1);

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
        setOptions();
    } //setParameters()

    /**
     * Checks for atomicity of the last ordering in the populations
     * Checking only the last ordering is fine because the check is made for every ordering added to the populations
     * so every ordering in the populations is checked (besides the first, but the first is alone so the check would
     * be pointless)
     * @return true if the last ordering is different from any other in the populations
     */
    private boolean areOrderingsDiff() { //
        if (getCurrentPopulationSize() == 1)
            return true;
        for (int i = 0; i != getCurrentPopulationSize() - 1; ++i) {
            if (getOrderingFromPopulation(i) == getOrderingFromPopulation(getCurrentPopulationSize() - 1))
                return false;
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
        List<Integer> firstGen = new ArrayList<>();
        initializeFitnessCost(firstGen);
        for (int i = 0; i != getPopSize(); ++i)
            addToPopulationFitnessCost((calculateFitnessCost(getOrderingFromPopulation(i))));
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


    /**
     * sort the stored population by fitness cost from the lowest to the biggest.
     * The algorithm used is bubble sort
     */
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
    } //sortPopulation()


    /**
     * Calculates the fitness cost of a given ordering, and returns the fitness cost as an int
     * @param ordering List<List<Integer>>
     * @return fitness cost int
     */
    public int calculateFitnessCost (List<List<Integer>> ordering) {
        int subSum = 0;
        for (int i = 0; i != getStudentsModules().size(); ++i) {
            if (hasStudentExamOverlapping(getStudentsModules().get(i), ordering))
                ++subSum;
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
                   if (i.equals(j))
                       ++matchingModules;
                }
            }
            if (matchingModules == 2) {
                return true;
            }
        }
        return false;
    } //hasStudentExamOverlapping()

    /**
     * copy by value the ordering and all its content
     * I had to implement this method (having to go through every elem of the list) because the ordering are just copied
     * by reference otherwise, and produce a huge mess during the selection phase of each iteration
     * @param orderingToCopy List<List<Integer>>
     * @return copied ordering List<List<Integer>
     */
    public List<List<Integer>> copyOrdering (List<List<Integer>> orderingToCopy) {
    List<List<Integer>> newOrdering = new ArrayList<>();

        for (int i = 0; i!= orderingToCopy.size(); ++i) {
            newOrdering.add(new ArrayList<Integer>());
            for (int j = 0; j != orderingToCopy.get(0).size(); ++j)   // j!=2
                newOrdering.get(i).add(orderingToCopy.get(i).get(j));
        }
        return newOrdering;
    } //copyOrdering()


    /*-****************************************************************************************************************/
    /*                                          iterate generations                                                   */
    /*-****************************************************************************************************************/

    /**
     * main function of the iteration process
     * copy the population to a new generation, apply a random Genetic Algorithm technique to every ordering of the new
     * generation, calculate their new respective fitness cost, and stores the best fitness cost for later
     */
    public void iterate () {
        //temporary
        do {
            iterateGeneration();
            selection();

            //modify orderings in the new generation
            for (int i = 0; i != getCurrentPopulationSize(); ++i)
                selectGATechniqueOnOrdering(i);
            if (getCrossoverOrdering() != null) //security if a single ordering has been chosen for crossover
                mutation(getCrossoverOrdering(), getCrossoverOrderingIndex()); //totally arbitrary mutation

            //calculate new fitness costs
            for (int i = 0; i != getCurrentPopulationSize(); ++i) //2 different loops to consider all the crossover orderings
                overwritePopulationFitnessCost(i, calculateFitnessCost(getOrderingFromPopulation(i))); //calculate new fitness cost

            //for keeping track of every step:
            if (isPrintAllGens())
                writeResults("gen" + getGeneration());

            Integer minFitCost = Collections.min(getPopulationFitnessCost());

           //for a graph reprensentation of the results
            StringBuilder sb = new StringBuilder();
            if (isGraphRep()) {
                for (int i = 0; i != minFitCost; ++i)
                    sb.append(" ");
            }
            sb.append(minFitCost);
            addToBestResults(sb.toString()); //return the best fitness cost of the generation
        } while (getGeneration() != getNbGenerations());
    } //iterate()

    /**
     * select randomly a Genetic Algorithm technique for an ordering
     * The ordering chosen to crossover have to be paired it stores one until a second is chosen
     * If the ordering is the last in his population and is single for a crossover, chooses again the technique,
     * leading eventually to another result
     * @param i int eg. the index in the stored attribute population, leading to an ordering
     */
    public void selectGATechniqueOnOrdering(int i) {
        int randomNumber;
        randomNumber = randomWithRange(0, 100);
        if (randomNumber < getChancesOfMutation()) //mutation
            mutation(getOrderingFromPopulation(i), i);
        else if (randomNumber < (getChancesOfMutation() + getChancesOfCrossover())) { //crossover
            if (getCrossoverOrdering() != null) {
                crossover(getOrderingFromPopulation(i), i , getCrossoverOrdering(), getCrossoverOrderingIndex());
                setCrossoverOrdering(null);
            } else if (i < (getCurrentPopulationSize())) { //needs at least 2 orderings
                setCrossoverOrdering(getOrderingFromPopulation(i));
                setCrossoverOrderingIndex(i);
            } else
                selectGATechniqueOnOrdering(i); //reroll for the ordering
        } //else reproduction so nothing happens
    } //selectGATechniqueOrdering()

    /**
     * implementation of the Selection Genetic Algorithm technique
     * first sort the population by increasing fitness cost, and then copy the best third of the population to the
     * wost third
     */
    public void selection() {

        sortPopulation();

        //replace the third sub group by the first one
        for (int i = 0; i != getCurrentPopulationSize() / 3; ++i)
            overwriteOrderingInPopulation(getCurrentPopulationSize() - 1 - i, getOrderingFromPopulation(i));
            //does not duplicate the fitness cost as well because they will be overwritten in the next step right away
    } //selection()

    /**
     * implementation of the Mutation Genetic Algorithm technique
     * swap two modules randomly, and put back the ordering in the population
     * @param ordering List<List<Integer>>
     * @param index int e.g. the place of the ordering in the population
     */
    public void mutation(List<List<Integer>> ordering, int index) {
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
    } //mutation()

    /**
     * implementation of the Crossover Genetic Algorithm technique
     * Swap a set of modules of each orderings according to a random cutpoint
     * Keeps the orderings clean by checking for duplicate modules in the orderings and removing them
     * @param ordering1 List<List<Integer>>
     * @param index1 int e.g. the place of the ordering in the population
     * @param ordering2 List<List<Integer>>
     * @param index2 int e.g. the place of the ordering in the population
     */
    public void crossover(List<List<Integer>> ordering1, int index1, List<List<Integer>> ordering2, int index2) {
        int cutPoint = randomWithRange(1, getNbModules() - 2); //must at least cut one module
        List<Integer> temp = new ArrayList<>();

        //swapping

        for (int i = 0; i != cutPoint; ++i) {
            temp.add(ordering1.get(i / 2).get(i % 2));
            ordering1.get(i / 2).set(i % 2, ordering2.get(i / 2).get(i % 2));
            ordering2.get(i / 2).set(i % 2, temp.get(i));
        }

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
            if (! test.get(0).contains(ordering1.get(i/2).get(i%2)))
               test.get(0).add(ordering1.get(i/2).get(i%2));
            else {
                duplicates.get(0).add(ordering1.get(i/2).get(i%2));
                dupPos.get(0).add(i);
            }
            //ordering2
            if (! test.get(1).contains(ordering2.get(i/2).get(i%2)))
                test.get(1).add(ordering2.get(i/2).get(i%2));
            else {
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
            ordering2.get(pos / 2).set(pos % 2, val);
        }

        overwriteOrderingInPopulation(index1, ordering1);
        overwriteOrderingInPopulation(index2, ordering2);
    } //crossover

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
     * adds the argument String as a new line in the BestOutputPerGeneration stored value
     * Pre-condition: It semantically assumes the the argument String is the best fitnessCost of one generation
     * of orderings
     * @param fitnessCost String
     */
    public void addToBestResults (String fitnessCost) {
        if (getBestOutputPerGen() == null)
            setBestOutputPerGen(fitnessCost);
        else {
            String newline = System.getProperty("line.separator");
            StringBuilder builder = new StringBuilder(getBestOutputPerGen());
            builder.append(newline);
            builder.append(fitnessCost);
            setBestOutputPerGen(builder.toString());
        }
    } //addToBestResults()

    /**
     * writes the wanted output for the final submission on the given file
     * @param address String eg. address of the file to write
     */
    public void writeBestResults(String address) {
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

        builder.append("Best fitness cost per generation:");
        builder.append(newline);
        builder.append(getBestOutputPerGen());

        builder.append(newline);
        builder.append(newline);
        builder.append("The best ordering in the last generation is:");
        builder.append(newline);
        List<List<Integer>> ordering = getOrderingFromPopulation(getPopulationFitnessCost().indexOf(Collections.min(getPopulationFitnessCost())));
        for (List<Integer> pairOfModule : ordering) {
            builder.append("M");
            builder.append(pairOfModule.get(0));
            builder.append(" ");
        }
        builder.append(newline);
        for (List<Integer> pairOfModule : ordering) {
            builder.append("M");
            builder.append(pairOfModule.get(1));
            builder.append(" ");
        }

        writeToFile(address, builder.toString());
    } //writeBestResults()

    /**
     * write the results of one generation of the orderings population for the optional output
     */
    public void writeResults(String address) {
        String newline = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

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
        main.iterate();
        main.writeBestResults("AI17-RomainRoux-FinalSubmission.txt");
    } //psvm()
}

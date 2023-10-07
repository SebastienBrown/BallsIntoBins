import java.util.Random;

public class BallsIntoBins{

    //collects data for the balls into bins thought experiment using the traditional method
    public static void collisionData(){

        //step increase in number of bins
        int STEP=10;

        //number of repetitions for some number of bins
        int repeats=2;

        //max number of bins
        int MAX=2000;

        //maximum number of balls thrown (max java int)
        int balls = 2147483647;

        int value=0;

        Random rand = new Random();

        //initialize csv file for saving results (average of results)
        CSVWriter csv = new CSVWriter("BallStatsAvg.csv");

        csv.addEntry("Number of bins");
        csv.addEntry("First Collision Throw");
        csv.addEntry("Max Occupancy after n trials");
        csv.addEntry("Number of trials to fill all buckets");
        csv.endLine();

        //initialize csv file for saving results (maximum values)
        CSVWriter csv2 = new CSVWriter("BallStatsMax.csv");

        csv2.addEntry("Number of bins");
        csv2.addEntry("First Collision Throw");
        csv2.addEntry("Max Occupancy after n trials");
        csv2.addEntry("Number of trials to fill all buckets");
        csv2.endLine();

        //initialize csv file for saving results (minimum values)
        CSVWriter csv3 = new CSVWriter("BallStatsMin.csv");

        csv3.addEntry("Number of bins");
        csv3.addEntry("First Collision Throw");
        csv3.addEntry("Max Occupancy after n trials");
        csv3.addEntry("Number of trials to fill all buckets");
        csv3.endLine();

    //gradually increases the number of bins into which balls are thrown
    for(int numBins=STEP; numBins<=MAX; numBins +=STEP){

        //initializes variables used to measure averages
        int maxOccupancySum=0;

        int firstCollisionValSum=0;

        int everyBinFullSum=0;

        //intializes variables used to measure maxima

        int maxOccupancyMAX=0;

        int firstCollisionValMAX=0;

        int everyBinFullMAX=0;

        //intializes variables used to measure minima, the largest possible int value 
        //is chosen since initializing with 0 would prevent the min value from updating
        //(since 0 would always be smaller than the values studied)

        int maxOccupancyMIN=2147483647;

        int firstCollisionValMIN=2147483647;

        int everyBinFullMIN=2147483647;

        //repeats balls-into-bins simulation multiple times and takes max,min,average 
        //of relevant values
        for(int h=0;h<repeats;h++){
        
        //creates a new array of bins of length numBins
        int[] bins=new int[numBins];
        //System.out.println("New step");

        //initializes relevant local variables describing values we want to count
        int maxOccupancy=0;

        int numFullBins =0;

        int firstCollisionLogic=0;

        int firstCollisionVal=0;

        int everyBinFull=0;

        for(int i=0; i<=balls; i++){
            //System.out.println("ran once");


            //defines a random variable value
            value=rand.nextInt(numBins);

            //increases the number stored at bins[value] by 1
            //increases the counter for the number of full bins
            if (bins[value]==0){
                bins[value]+=1;
                numFullBins+=1;
            }
            else if(bins[value]!=0){
                bins[value]++;
            }

            //if a bin receievs a 2nd ball, there is a collision
            //stores number of balls thrown at that point and sets the firstcollisionLogic
            //variable to 1, so this statement is no longer called
            if(bins[value]==2 && firstCollisionLogic==0){
                firstCollisionLogic=1;
                firstCollisionVal=i;
            }

            //triggers once n trials have been completed
            if(i==numBins){

                //assigns the number of balls in the first bin to be the max
                maxOccupancy=bins[0];

                //iterates through the array to search for the bin with maximal occupancy
                for(int j=0;j<=numBins-1;j++){
                    
                    //if a bin with higher occupancy is found, change maxOccupancy
                    if(bins[j]>maxOccupancy){
                        maxOccupancy=bins[j];
                    }
            }
        }

            //if every bin is full, print the number of balls thrown so far to the csv and break the loop
            if(numFullBins==numBins){

                //updates sum variables used for calculating averages
                everyBinFull=i;
                maxOccupancySum+=maxOccupancy;
                firstCollisionValSum+=firstCollisionVal;
                everyBinFullSum+=everyBinFull;


                //updates variables tracking maxima
                if(maxOccupancy>maxOccupancyMAX){
                    maxOccupancyMAX=maxOccupancy;
                }

                if(firstCollisionVal>firstCollisionValMAX){
                    firstCollisionValMAX=firstCollisionVal;
                }

                if(everyBinFull>everyBinFullMAX){
                    everyBinFullMAX=everyBinFull;
                }

                //updates variables tracking minima
                if(maxOccupancy<maxOccupancyMIN){
                    maxOccupancyMIN=maxOccupancy;
                }

                if(firstCollisionVal<firstCollisionValMIN){
                    firstCollisionValMIN=firstCollisionVal;
                }

                if(everyBinFull<everyBinFullMIN){
                    everyBinFullMIN=everyBinFull;
                }

                break;
            }
        }
    }

    //prints all average values for the repeated runs to the csv
    csv.addEntry(numBins);
    csv.addEntry(firstCollisionValSum/repeats);
    csv.addEntry(maxOccupancySum/repeats);
    csv.addEntry(everyBinFullSum/repeats);
    csv.endLine();

    //prints the maximum values for the repeated runs
    csv2.addEntry(numBins);
    csv2.addEntry(firstCollisionValMAX);
    csv2.addEntry(maxOccupancyMAX);
    csv2.addEntry(everyBinFullMAX);
    csv2.endLine();

    //prints the minimum values for the repeated runs
    csv3.addEntry(numBins);
    csv3.addEntry(firstCollisionValMIN);
    csv3.addEntry(maxOccupancyMIN);
    csv3.addEntry(everyBinFullMIN);
    csv3.endLine();

        }  
        csv.close();     
        csv2.close();
        csv3.close();
    }


     //collects data for the balls into bins thought experiment using 
     //the power of two choices method
    public static void collisionDataTwoChoices(){

        //step increase in number of bins
        int STEP=10;

        //number of repetitions for some number of bins
        int repeats=2;

        //max number of bins
        int MAX=2000;

        //maximum number of balls thrown (max java int)
        int balls = 2147483647;

        int value=0;

        int value2=0;

        int heads_or_tails=0;

        Random rand = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        //initialize csv file for saving results (average of results)
        CSVWriter csv = new CSVWriter("BallStatsAvg2Choice.csv");

        csv.addEntry("Number of bins");
        csv.addEntry("First Collision Throw");
        csv.addEntry("Max Occupancy after n trials");
        csv.addEntry("Number of trials to fill all buckets");
        csv.endLine();

        //initialize csv file for saving results (maximum values)
        CSVWriter csv2 = new CSVWriter("BallStatsMax2Choice.csv");

        csv2.addEntry("Number of bins");
        csv2.addEntry("First Collision Throw");
        csv2.addEntry("Max Occupancy after n trials");
        csv2.addEntry("Number of trials to fill all buckets");
        csv2.endLine();

        //initialize csv file for saving reseults (minimum values)
        CSVWriter csv3 = new CSVWriter("BallStatsMin2Choice.csv");

        csv3.addEntry("Number of bins");
        csv3.addEntry("First Collision Throw");
        csv3.addEntry("Max Occupancy after n trials");
        csv3.addEntry("Number of trials to fill all buckets");
        csv3.endLine();

    //gradually increases the number of bins into which balls are thrown
    for(int numBins=STEP; numBins<=MAX; numBins +=STEP){

        //initializes variables used to measure averages
        int maxOccupancySum=0;

        int firstCollisionValSum=0;

        int everyBinFullSum=0;

        //intializes variables used to measure maxima

        int maxOccupancyMAX=0;

        int firstCollisionValMAX=0;

        int everyBinFullMAX=0;

        //intializes variables used to measure minima, the largest possible int value 
        //is chosen since initializing with 0 would prevent the min value from updating
        //(since 0 would always be smaller than the values studied)

        int maxOccupancyMIN=2147483647;

        int firstCollisionValMIN=2147483647;

        int everyBinFullMIN=2147483647;

        //repeats balls-into-bins simulation multiple times and takes max,min,average 
        //of relevant values
        for(int h=0;h<repeats;h++){
        
        //creates a new array of bins of length numBins
        int[] bins=new int[numBins];
        //System.out.println("New step");

        //initializes relevant local variables describing values we want to count
        int maxOccupancy=0;

        int numFullBins =0;
        
        //is changed to 1 when the first collision occurs
        int firstCollisionLogic=0;

        int firstCollisionVal=0;

        //has value 0 at start, is changed to 1 when all bins are filled
        int everyBinFull=0;

        for(int i=0; i<=balls; i++){

            //defines random variable values
            value=rand.nextInt(numBins);
            value2=rand2.nextInt(numBins);
            heads_or_tails=rand3.nextInt(2);

            //case where both both randomly chosen bins have same occupancy
            if(bins[value]==bins[value2]){

                //checks a random variable with value 1 or 0
                //if heads_or_tails has value 1, increases the occupancy of the bin with index value
                if(heads_or_tails==1){

                    if(bins[value]==0){
                        bins[value]+=1;
                        numFullBins+=1;
                    }
                    else if(bins[value]!=0){
                        bins[value]++;
                    }
                }

                //if heads_or_tails has value 0, increases the occupancy of the bin with index value2
                else if(heads_or_tails==0){

                    if(bins[value2]==0){
                        bins[value2]+=1;
                        numFullBins+=1;
                    }
                else if(bins[value2]!=0){
                    bins[value2]++;
                }
                }
            }

            //case where both randomly chosen bins have different occupancies
            else{

                //if the occupancy of the bin with index value is smaller, add to that bin
                if(bins[value]<bins[value2]){

                    if(bins[value]==0){
                        bins[value]+=1;
                        numFullBins+=1;
                    }
                    else if(bins[value]!=0){
                        bins[value]++;
                    }
                }

                //if the occupancy of the bin with index value2 is smaller, add to that bin
                else if(bins[value]>bins[value2]){ 

                    if(bins[value2]==0){
                    bins[value2]+=1;
                    numFullBins+=1;
                }
                else if(bins[value2]!=0){
                    bins[value2]++;
                }
                }
            }


            //if a bin receievs a 2nd ball, there is a collision
            //stores number of balls thrown at that point and sets the firstcollisionLogic
            //variable to 1, so this statement is no longer called
            if(bins[value]==2 && firstCollisionLogic==0){
                firstCollisionLogic=1;
                firstCollisionVal=i;
            }


            //triggers once n trials have been completed
            if(i==numBins){

                //assigns the number of balls in the first bin to be the max
                maxOccupancy=bins[0];

                //iterates through the array to search for the bin with maximal occupancy
                for(int j=0;j<=numBins-1;j++){
                    
                    //if a bin with higher occupancy is found, change maxOccupancy
                    if(bins[j]>maxOccupancy){
                        maxOccupancy=bins[j];
                    }
                }  
            }

            //if every bin is full, print the number of balls thrown so far to the csv and break the loop
            if(numFullBins==numBins){

                //updates sum variables used for calculating averages
                everyBinFull=i;
                maxOccupancySum+=maxOccupancy;
                firstCollisionValSum+=firstCollisionVal;
                everyBinFullSum+=everyBinFull;


                //updates variables tracking maxima
                if(maxOccupancy>maxOccupancyMAX){
                    maxOccupancyMAX=maxOccupancy;
                }

                if(firstCollisionVal>firstCollisionValMAX){
                    firstCollisionValMAX=firstCollisionVal;
                }

                if(everyBinFull>everyBinFullMAX){
                    everyBinFullMAX=everyBinFull;
                }

                //updates variables tracking minima
                if(maxOccupancy<maxOccupancyMIN){
                    maxOccupancyMIN=maxOccupancy;
                }

                if(firstCollisionVal<firstCollisionValMIN){
                    firstCollisionValMIN=firstCollisionVal;
                }

                if(everyBinFull<everyBinFullMIN){
                    everyBinFullMIN=everyBinFull;
                }

                break;
            }
        }
    }

    //prints all average values for the repeated runs to the csv
    csv.addEntry(numBins);
    csv.addEntry(firstCollisionValSum/repeats);
    csv.addEntry(maxOccupancySum/repeats);
    csv.addEntry(everyBinFullSum/repeats);
    csv.endLine();

    //prints the maximum values for the repeated runs
    csv2.addEntry(numBins);
    csv2.addEntry(firstCollisionValMAX);
    csv2.addEntry(maxOccupancyMAX);
    csv2.addEntry(everyBinFullMAX);
    csv2.endLine();

    //prints the minimum values for the repeated runs
    csv3.addEntry(numBins);
    csv3.addEntry(firstCollisionValMIN);
    csv3.addEntry(maxOccupancyMIN);
    csv3.addEntry(everyBinFullMIN);
    csv3.endLine();

        }  
        csv.close();     
        csv2.close();
        csv3.close();
    }
    
    public static void main(String[] args){
          collisionData();
          collisionDataTwoChoices();
    }
}
import java.util.Scanner;
/**
 * BillManagement
 * @author Gazi MD Wasi-UL-hoque Eusha
 * @version 3 June 2022
 */
public class BillManagement {

    public static int applianceUnits(Scanner in) {

        int applianceUnit;

        System.out.print("Enter number of unit: ");
        applianceUnit = in.nextInt();
        return applianceUnit;
    }

    public static int applianceMenu(Scanner in) {

        // variable declaration
        int userChoice; // to know which number the user selected

        // menu displayed for the user to interact 
        System.out.println("++++This is the appliance selection menu++++");
        System.out.println("Press 1 for Fridge");
        System.out.println("Press 2 for Air Conditioner");
        System.out.println("Press 3 for Television");
        System.out.println("Press 4 for Light");
        System.out.println("Press 5 for Fan");
        System.out.println("Press 6 for Washing Machine");
        System.out.println();
        System.out.print("Enter your choice: ");
        userChoice = in.nextInt();
        
        return userChoice;
    }

    public static int applianceInfo(Scanner in) {

        // constants declared, the values are the power required by the appliances
        final int FRIDGE = 250;
        final int AC = 1200;
        final int TV = 130;
        final int LIGHT = 10;
        final int FAN = 65;
        final int WASHINGMACHINE = 900;

        // variable declaration
        int userSelection; // to know the user choice of appliance
        int applianceUnit; // to know the number of units operated
        String userChString = "y";

        System.out.println("Add appliance to the system!");
        System.out.println();
        userSelection = applianceMenu(in);
           
        switch(userSelection){

            case 1:
                System.out.println("Select the number of units of FRIDGE");
                return FRIDGE;                
            case 2:
                System.out.println("Select the number of units of AC");
                return AC;
            case 3:
                System.out.println("Select the number of units of TV");
                return TV;
            case 4: 
                System.out.println("Select the number of units of LIGHT ");
                return LIGHT;
            case 5:
                System.out.println("Select the number of units of FAN");
                return FAN;
            case 6:
                System.out.println("Select the number of units of WASHING MACHINE ");
                return WASHINGMACHINE;
            default:
                return 0;
            }  

    }

    public static double timeCalculation(Scanner in) {
        
        // variable declaration
        double timeHour;    // time in hours
        double timeMin;     // time in minutes 

        // input prompt
        System.out.print("Enter time for hours: ");
        timeHour = in.nextDouble();
        System.out.print("Enter time in minutes: ");
        timeMin = in.nextDouble();

        // minutes to hour conversion
        timeMin = timeMin / 60; 

        // total time calculation
        timeHour = timeHour + timeMin;

        return timeHour;
    }

    public static double energyConsumption(int power, int totalUnits, double time) {

        // variable declaration
        double energyConsumption;

        energyConsumption = power * totalUnits * time;
        
        return energyConsumption;
    }

    public static void amountRemaining(Scanner in) {
        
        // variable declaration
        String userName = knowUserName(in);
        double userAmount = prepaidAmount(in);          // to store the prepaid value of the user
        double electricityCost = billingCalculation(in);    // the electricity cost after all the calculation
        double amountRemaining;                         // the amount remaining after subrtacting the prepaid value and all expenses
        double taxCalculation = 0;                      // to calculate the tax

        // amount remaining calculation
        taxCalculation = taxCalculation + electricityCost * 0.05;
        electricityCost = electricityCost + taxCalculation;
        amountRemaining = userAmount - electricityCost;

        // output message of the final amount remaining        
        System.out.println(userName + " your amount Remaining is " + amountRemaining);
        
    }
    /**
     * to calculate the total electricity cost after using different appliances
     * @param in for taking user input 
     * @return the electricity cost that has been calculated for further operations
     */
    public static double billingCalculation(Scanner in) {
        
        // variable declaration
        int days;                   // to know how many days the device was used
        int power;                  // power required to operate the device 
        int totalUnits;             // total number of electronic devices(of a specific type, eg: TV)
        double totalTime;           // time the device has operated
        double energyConsumption;   // to know how much enegy the device used 
        double electricityCost = 0; // total electricity bill
        String userChString = "y";  // for user choice
        
        // repeat until the user says no
        do {
            
            // input prompt and value assignment
            power = applianceInfo(in);
            totalUnits = applianceUnits(in);
            System.out.print("Enter how many days operated: ");
            days = in.nextInt();
            totalTime = timeCalculation(in);       
            energyConsumption = energyConsumption(power, totalUnits, totalTime);

            // electricity cost calculation
            electricityCost = electricityCost + ((energyConsumption * 2) / 1000);
      
            System.out.print("Add another appliance?(Y/N) ");
            in.nextLine(); // to know whether the user wants to add another device
            userChString = in.nextLine().toLowerCase(); 
            System.out.println();

        } while (!userChString.equals("n"));
        
        return electricityCost;
    }

    /**
     * user account management to enter name and prepaid amount
     * @param in to take in user input
     */
    public static double prepaidAmount(Scanner in) {

        // variable declaration        
        double amountPrepaid;   // to hold the prepaid amount

        // input prompts       
        System.out.print("Enter amount prepaid: ");
        amountPrepaid = in.nextDouble();

        return amountPrepaid; // amount that the user has paid before
    }

    public static String knowUserName(Scanner in) {
        String userName;        //to store the user name
        System.out.print("Enter Your name: ");
        userName = in.nextLine();
        return userName;
    }

    /* program display */
    public static void displayOutput() {

        System.out.println("**********************");
        System.out.println("Bill Management System");
        System.out.println("_______________________");
        
    }

    /* method to start the code */
    public static void starterFuntion(Scanner in) {
        displayOutput();
        amountRemaining(in);

    }

    public static void main(String[] args) {

        // variable to take in user input
        Scanner in = new Scanner(System.in);

        starterFuntion(in); // to start the code
    }
}
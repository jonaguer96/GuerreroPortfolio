import java.util.Scanner;
import java.io.File; 
import java.io.IOException;

public class CL1_Guerrero{
	public static void main(String [] args) throws IOException {

        //Instantiate a File Object.
        File file = new File("starbucks_menu.txt");

        //Instantiate a scanner to read the file. 
        Scanner fileReader = new Scanner( file );
        //Instantiate a scanner that reads keyboard.
        Scanner keyboard = new Scanner(System.in);


        //Declare variable for userInput for main menu. 
        int userInput = 0;

        //Declare variables to read the file. 
        String itemName;
        boolean isBeverage;
        double itemCost; 

        //Declare variables to store file information
        String cart = "";
        double subTotal = 0;


        //Create boolean value that keeps track of existing order item. 


        while( userInput != 5 ){

            //Initialize boolean inside the while loop so it resets every time an item is addeed to cart. 
            boolean itemNameIsInFile = false;
            System.out.println("Welcome to Guerrero Coffee! Please select an option below to continue:");
            System.out.println("1. Display Menu\n2. Add Item to order\n3. View Cart\n4. Checkout\n5. Exit Guerrero Coffe.");
            userInput = keyboard.nextInt();

            if( userInput == 1 ){

                //Skip first line of the file. 
                String headerline = fileReader.nextLine();
                //Display menu to user by reading the file
                System.out.format("+-------------------------------------------------------+%n");
                System.out.format("|                 GUERRERO COFFEE MENU                  |%n");
                System.out.format("+---------------------------------+-------------+-------+%n");
                System.out.format("|                        Item Name|  isBeverage?|   Cost|%n");
                System.out.format("+---------------------------------+-------------+-------+%n");

                while( fileReader.hasNext() ){
                
                    //Read the first column of the file.
                    itemName = fileReader.next();
                    //Read the second column of the file. This column is a boolean (true or false) column.
                    isBeverage = fileReader.nextBoolean();
                    //Read the third column of the file. 
                    itemCost = fileReader.nextDouble();
                    
                    System.out.printf( "| %31s | %11b | $%1.2f |\n", itemName, isBeverage, itemCost );
                    System.out.format("+---------------------------------+-------------+-------+%n");

                }//ends while loop nested on if UserInput == 1 loop.

                //Reset the scanner
                System.out.print("\n");
                fileReader = new Scanner( file );
            }//end the userInput == 1 loop. 
            else if ( userInput == 2 ){


                System.out.println("Please enter the name of the item as shown on the menu: ");
                String itemInputName = keyboard.next();

                while( fileReader.hasNext() ){
                    itemName = fileReader.next();
                    if( itemInputName.equalsIgnoreCase(itemName) ){
                        itemNameIsInFile = true; 
                        isBeverage = fileReader.nextBoolean();
                        itemCost = fileReader.nextDouble();
                        subTotal = subTotal + itemCost;


                        if(isBeverage == true){
                            System.out.println("\nWhat size would you like for your  drink? ");
                            System.out.println("1. Tall    (+$0.00) ");
                            System.out.println("2. Grande  (+$1.50) ");
                            System.out.println("3. Venti   (+$2.75) ");
                            int drinkChoice = keyboard.nextInt();
                            if(drinkChoice == 1){
                                System.out.println("Item added to cart.");
                                cart = cart+ itemName + " | Size: Tall | Price: " + itemCost + "\n";
                            }else if(drinkChoice == 2){
                                subTotal = subTotal + 1.50;
                                System.out.println("Item added to cart.");
                                double costofGrande = itemCost + 1.50;
                                cart = cart+ itemName + " | Size: Grande | Price: " + costofGrande + "\n";
                            }else if(drinkChoice == 3){
                                subTotal = subTotal + 2.75;
                                System.out.println("Item added to cart.");
                                double costofVenti = itemCost + 2.75;
                                cart = cart+ itemName + " | Size: Venti | Price: " + costofVenti + "\n";
                            }else{
                                System.out.println("Please try again!");
                            }

                        }else{
                            System.out.println("How many would you like? ");
                            int itemQty = keyboard.nextInt();
                            //Since we already assumed the user is taking at least one, at the beginning of the outer loop,
                            //we will ask how many they want, then multiply item cost * number they want minus 
                            //item cost, to account for the first one.
                            double extraOnes = (itemCost * itemQty) - itemCost;
                            subTotal = subTotal + extraOnes;
                            double costofQty = itemCost * itemQty;
                            cart = cart + itemName + " | Qty: " + itemQty + " | Price: " + costofQty + "\n";
                            System.out.println("Item added to cart!");
                        }
                    }//end outer if loop.
                }//end while loop.
                if( !itemNameIsInFile ){
                    System.out.println("I don't carry " + itemInputName);
                }

            }//end of userInput == 2 loop.
            else if( userInput == 3){
                System.out.println("---------------- YOUR CART ----------------");
                System.out.println(cart);

            }//end of userInput == 3 loop.
            else if( userInput == 4){
                double tax = subTotal * .0675;
                double finaltotal = subTotal + tax; 
                System.out.println("---------------- YOUR CART ----------------");
                System.out.println(cart);
                System.out.println("\nSubtotal: $" + subTotal);
                System.out.printf("Tax: $" + "%,.2f", tax);
                System.out.println();
                System.out.printf("Total after tax: $" + "%,.2f", finaltotal);
                System.out.println("\n");
                System.out.println("Pease enter CHECKOUT to complete purchase");
                
                String checkOut = "CHECKOUT";
                boolean done = false;

                while(done == false){
                    String checkingOut = keyboard.next();
                    if( checkingOut.equalsIgnoreCase(checkOut) ){
                        System.out.println("Alright! Here's your receipt!");
                        done = true;
                    }else{
                        System.out.println("Oops! Try it with the chip reader instead.");
                    }
                }

            }else{
                itemName = fileReader.next();

            }

            System.out.print("\n");
            fileReader = new Scanner( file );

        }//end the while loop which holds the entire program.

        System.out.println("Thank you for shopping at Guerrero Coffee.");


    }
}
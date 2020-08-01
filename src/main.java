import javafx.scene.chart.ScatterChart;
/*
Author: Nicolas Martalog
Class:   ICS4U

Program: Inventory Management
Input: The user inputs a number based on what he/she wants to do with the program
Processing: the program reads different different names, quantities, and prices of a product and adds them to specified arrays and can preform many function with said arrays
Output: the Program outputs the arraylists to the txt file associated with the program and each different input that is associated with a function of the program.

*/

import java.io.File;
import java.util.ArrayList;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Math;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        File file = new File ("Inventory.txt");
        Scanner fileInput = new Scanner(file);
        int descision = 0;

        String line;
        int records = 0;
        // takes in how many records there are in the file
        try{

            while(true){
                line = fileInput.nextLine();
                records++;
            }
        }

        catch (NoSuchElementException ex)
        {

        }

        // setting up the array list for each data field
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> quantities = new ArrayList<Integer>();
        ArrayList<Double> unitPrices = new ArrayList<Double>();

        try{
            Scanner fileInput2 = new Scanner(file);
            fileInput2.useDelimiter(",");

            for (int i = 0; i < records; i++)
            {
                // reading each data field and putting it in its own arraylist
                String name = fileInput2.next();
                int quantity = fileInput2.nextInt();
                String price = fileInput2.nextLine();
                String newPrice = price.substring(1);
                double parsedPrice = Double.parseDouble(newPrice);

                names.add(name);
                quantities.add(quantity);
                unitPrices.add(parsedPrice);

            }
            // closing the scanner for the file
            fileInput2.close();

        }
// catching if the file is not found
        catch (FileNotFoundException ex)
        {
            System.out.printf("ERROR %s\n", ex);
        }
// main loop for the inventory program
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("\nMenu – Type the number of the desired option\t\t\n" + "1 Search Product        \n" + "2 Reduce Inventory  \n" + "3 Add New Product\n" + "4 Bulk Price Change\n" + "5 Exit\n");
            descision = input.nextInt();
// search product function
            if (descision == 1)
            {
                System.out.println("what Item do you want to search for? ");
                String prodName = in.nextLine();
                // checking to see if the input is in the array list names
                boolean found = names.contains(prodName);

// printing out the other data fields associated with the product if the product is found
                if (found == true )
                {
                    int index = names.indexOf(prodName);
                    System.out.println("\nItem found: " + prodName + "   Quantity: " + quantities.get(index) + "   Unit Price: $" + unitPrices.get(index) + "\n");
                }
                // printing out no product if not found in arraylist
                else
                {
                    System.out.println("\nSorry, there is not item named " + prodName + "\n");
                }
            }
            // reducing product function
            if (descision == 2)
            {
                // taking input for product and quantity to reduce
                System.out.println("What product do you want to be reduced? ");
                String removeProduct = in.nextLine();
                boolean found2 = names.contains(removeProduct);
                int productIndex = names.indexOf(removeProduct);
                System.out.println("What quantity do you want to remove? ");
                int remQuantity = in.nextInt();

                // if quantity to remove is less than overall quantity of product this prints
                if (found2 == true) {
                    if (quantities.get(productIndex) > remQuantity) {
                        quantities.set(productIndex, quantities.get(productIndex) - remQuantity);
                        System.out.println("you requested " + remQuantity + " " + removeProduct + "'s" + ". You now have " + quantities.get(productIndex));
                    }
                    // runs if quantity to remove is greater than overall quantity of product
                    else {
                        System.out.print("you requested " + remQuantity + " " + removeProduct + "'s" + ". but there are only " + quantities.get(productIndex) + " in stock. ");
                        quantities.set(productIndex, 0);
                        System.out.print("You now have " + quantities.get(productIndex) + ". Please reorder.\n\n");
                    }
                }

                // prints if product is not found
                else
                {
                    System.out.println("\nSorry, there is not item named " + removeProduct + "\n");
                }
            }
            // adds new product, quantity, and price to associated arraylist
            if (descision == 3)
            {
                        System.out.println("Product Name: ");
                        String productName = in.nextLine();
                        names.add(productName);
                        System.out.println("Quantity: ");
                        quantities.add( in.nextInt());
                        System.out.println("Unit Price: ");
                        unitPrices.add(in.nextDouble());
            }
// bulk price change function
            if (descision == 4)
            {
                System.out.println("enter the percentage increase or decrease");
                double percentage = in.nextDouble();
                percentage = percentage / 100 + 1;

// if percentage is positive
                if (percentage > 1)
                {
                    for (int i = 0; i < names.size(); i++)
                    {
                        double newPercentage = Math.round(unitPrices.get(i) * percentage * 100) / 100.0;
                        // sets unit price to new unit price
                        unitPrices.set(i, newPercentage);
                        // formatting for different length names in arraylist
                        if (names.get(i).length() < 8)
                        {
                            System.out.println("Product Name:\t" + names.get(i) + "\t\t\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));

                        }
                        if (names.get(i).length() > 11 && names.get(i).length() < 16)
                        {
                            System.out.println("Product Name:\t" + names.get(i) + "\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));
                        }
                        else if (names.get(i).length() > 7 && names.get(i).length() < 12)
                        {System.out.println("Product Name:\t" + names.get(i) + "\t\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));}

                    }
                    System.out.println("\n");

                }
// if percentage is negative this runs
                if (percentage < 1)
                {
                    for (int i = 0; i < names.size(); i++)
                    {
                        double negativeNewNum = Math.round( unitPrices.get(i) * percentage * 100) / 100.0;
                        // sets unit price to new percentage
                        unitPrices.set(i, negativeNewNum);
                        // formatting for string lengths in names array list
                        if (names.get(i).length() < 8)
                        {
                            System.out.println("Product Name:\t" + names.get(i) + "\t\t\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));

                        }
                        if (names.get(i).length() > 11 && names.get(i).length() < 16)
                        {
                            System.out.println("Product Name:\t" + names.get(i) + "\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));
                        }
                        else if (names.get(i).length() > 7 && names.get(i).length() < 12)
                        {System.out.println("Product Name:\t" + names.get(i) + "\t\t\tQuantity:\t\t" + quantities.get(i) + "\t\tUnit Price:\t\t$" + unitPrices.get(i));
                            }

                    }
                    System.out.println("\n");
                }
            }
// stops the program and writes each arraylist to the file
        }while (descision != 5);

        try{
            PrintWriter output = new PrintWriter(file);

            for (int i = 0; i < names.size(); i++)
            {
                
                String recWrite = names.get(i) + "," + quantities.get(i) + "," + unitPrices.get(i);
                output.println(recWrite);
            }
            output.close();

        } catch (IOException ex)
        {
            System.out.printf("ERROR: %s\n", ex);
        }
    }

}

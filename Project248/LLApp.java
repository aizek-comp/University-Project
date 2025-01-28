import java.io.*;
import java.util.*;
import java.text.*;

public class LLApp {
    public static void main(String[] args) throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            in.useDelimiter("\n");

            DecimalFormat df = new DecimalFormat("0.00");

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            int itemId;
            String itemName;
            char itemCategory;
            int purchaseQuantity;
            String expiryDate;
            int customerId;
            String customerName;
            String customerPhoneNumber;
            int transactionId;
            String paymentStatus;

            String inputData = null;
            LinkedList<FoodInventory> purchaseList = new LinkedList<>();

            while ((inputData = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inputData, ";");

                itemId = Integer.parseInt(st.nextToken());
                itemName = st.nextToken();
                itemCategory = st.nextToken().charAt(0);
                purchaseQuantity = Integer.parseInt(st.nextToken());
                expiryDate = st.nextToken();
                customerId = Integer.parseInt(st.nextToken());
                customerName = st.nextToken();
                customerPhoneNumber = st.nextToken();
                transactionId = Integer.parseInt(st.nextToken());
                paymentStatus = st.nextToken();

                FoodInventory inv = new FoodInventory(itemId, itemName, itemCategory, purchaseQuantity,
                        expiryDate, customerId, customerName, customerPhoneNumber,
                        transactionId, paymentStatus);

                purchaseList.addLast(inv);
            }

            //1. store all the product information
            System.out.println("\nList of all Food Inventory details\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-12s|%-11s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-10s|%-15s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            FoodInventory obj = purchaseList.getFirst();
            while (obj != null) {
                System.out.println(obj.toString());
                obj = purchaseList.getNext();
            }

            //2.count the size of the list
            obj = purchaseList.getFirst();
            int count = 0;
            while (obj != null) {
                count++;
                obj = purchaseList.getNext();
            }
            System.out.println("\n\n----------SIZE OF THE LIST----------");
            System.out.println("The size of the purchaseList: " + count);
            System.out.println();
            //3.search and display items by their category
            System.out.println("========== Search by Item Category ==========");
            System.out.println("Available Categories:");
            System.out.println("  F - Fresh Produce");
            System.out.println("  G - Frozen Goods");
            System.out.println("  D - Dairy");
            System.out.println("  S - Snacks");
            System.out.println("---------------------------------------------");
            boolean found = false;
            String output="";

            do{
                System.out.print("\nEnter the item category you want to search for: ");
                char searchCategory = in.next().charAt(0);

                obj = purchaseList.getFirst();

                while (obj != null) {

                    if (obj.getItemCategory() == searchCategory) {
                        found = true;
                        output+=obj.toString();
                    }

                    obj = purchaseList.getNext();
                }

                if(found){
                    System.out.println("\nSearched item(s) details:\n");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print(output);
                    System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                }

                if (!found) {
                    System.out.println("\nNo items found in the specified category.");
                    System.out.println("\nPLEASE TRY AGAIN.");
                    //Ask user to enter again if there's an error
                }

            } while (!found) ;

            //4.Split the records into customers who have paid and those with pending payments
            LinkedList<FoodInventory> paidList = new LinkedList();
            LinkedList<FoodInventory> pendingList = new LinkedList();
            obj = purchaseList.getFirst();

            while (obj != null) {

                if (obj.getPaymentStatus().equalsIgnoreCase("Paid")) {
                    paidList.addLast(obj);
                } 

                else {
                    pendingList.addLast(obj);
                }
                obj = purchaseList.getNext();
            }

            //display
            System.out.println("\nPayment status-->(PAID) item(s) details:\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-12s|%-11s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-10s|%-15s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            FoodInventory obj1 = paidList.getFirst();

            while (obj1 != null) {
                System.out.println(obj1.toString());
                obj1 = paidList.getNext();
            }

            //display
            System.out.println("\nPayment status-->(PENDING) item(s) details:\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-12s|%-11s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-10s|%-15s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            FoodInventory obj2 = pendingList.getFirst();

            while (obj2 != null) {
                System.out.println(obj2.toString());
                obj2 = pendingList.getNext();
            }

            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //5.Count the number of customers for each category of items.
            int countP = 0;  
            int countG = 0;  
            int countD = 0;  
            int countS = 0;  

            obj = purchaseList.getFirst();
            while (obj != null) {

                if (obj.getItemCategory() == 'P') {
                    countP++;
                } 
                else if (obj.getItemCategory() == 'G') {
                    countG++;
                } 
                else if (obj.getItemCategory() == 'D') {
                    countD++;
                }    
                else if (obj.getItemCategory() == 'S') {
                    countS++;
                }

                obj = purchaseList.getNext();  
            }

            
            // Display total customers for each category
            System.out.println();
            System.out.println("\nTotal Customer(s) For Each Category: \n");
            System.out.println("--------------------------");
            System.out.println(String.format("|%-10s|%-10s|", "CATEGORY", "TOTAL CUSTOMER"));
            System.out.println("--------------------------");
            System.out.println(String.format("|%-10s|%-14d|", "F", countP));
            System.out.println(String.format("|%-10s|%-14d|", "G", countG));
            System.out.println(String.format("|%-10s|%-14d|", "D", countD));
            System.out.println(String.format("|%-10s|%-14d|", "S", countS));
            System.out.println("--------------------------");

            //6.Find and display highest and lowest details of the food inventory purchased amount by the customer
            obj = purchaseList.getFirst();
            FoodInventory highest = null ;
            FoodInventory lowest = null;

            double highestAmount=obj.Amount();
            double lowestAmount=obj.Amount();

            while(obj != null){

                if(obj.Amount() > highestAmount){
                    highestAmount=obj.Amount();
                    highest = obj;
                }
                if(obj.getPaymentStatus().equalsIgnoreCase("Paid")){
                    if(obj.Amount() < lowestAmount){
                        lowestAmount=obj.Amount();
                        lowest = obj;
                    }
                }
                obj = purchaseList.getNext();
            }

            System.out.println("\nCustomer details for highest purchase: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (highest !=null){
                System.out.print(highest.toString());
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("\nCustomer details for lowest purchase: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (lowest !=null){
                System.out.print(lowest.toString());
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //7    Calculate total sales for all orders
            double totSales=0;
            obj = purchaseList.getFirst();
            while (obj!=null) {

                totSales+=obj.Amount();
                obj = purchaseList.getNext();
            }

            System.out.println();
            System.out.println("\nTotal Sales For All Orders: RM"+df.format(totSales));

            //8.Calculate total sales received for each category
            double totalP = 0.00;
            double totalG = 0.00;
            double totalD = 0.00;
            double totalS = 0.00;

            obj = purchaseList.getFirst();
            while (obj != null) {

                if (obj.getItemCategory() == 'P') {
                    totalP += obj.Amount();
                } 
                else if (obj.getItemCategory() == 'G') {
                    totalG += obj.Amount();
                } 
                else if (obj.getItemCategory() == 'D') {
                    totalD += obj.Amount();
                } 
                else if (obj.getItemCategory() == 'S') {
                    totalS += obj.Amount();
                }

                obj = purchaseList.getNext();  
            }

            // Display total sales for each category
            System.out.println();
            System.out.println("\nTotal Sales For Each Category: \n");
            System.out.println("-----------------------");
            System.out.println(String.format("|%-10s|%-10s|", "CATEGORY", "TOTAL (RM)"));
            System.out.println("-----------------------");
            System.out.println(String.format("|%-10s|%-10.2f|", "P", totalP));
            System.out.println(String.format("|%-10s|%-10.2f|", "G", totalG));
            System.out.println(String.format("|%-10s|%-10.2f|", "D", totalD));
            System.out.println(String.format("|%-10s|%-10.2f|", "S", totalS));
            System.out.println("-----------------------");

            //9.update order information if customer wants to change the purchase details
            obj = purchaseList.getFirst();

            char updateAns;
            do {
                System.out.println("\nDo you want to change the purchase details? [Y-YES | N-NO]: ");
                updateAns = in.next().charAt(0);

                if (updateAns != 'Y' && updateAns != 'y' && updateAns != 'N' && updateAns != 'n'){
                    System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                }

            }while (updateAns != 'Y' && updateAns != 'y' && updateAns != 'N' && updateAns != 'n');

            boolean modifyStatus = (updateAns == 'Y' || updateAns == 'y');
            boolean updated = false;
            boolean choiceErr = false;

            do{

                while(modifyStatus){

                    if(updateAns=='Y' || updateAns =='y'){
                        System.out.print("Enter the Customer ID to modify : ");
                        int modifyId = in.nextInt();

                        while(obj!= null){
                            if(obj.getCustomerId() == modifyId && !updated){
                                System.out.println("Which detail do you want to update?");
                                System.out.println("1. Item ID");
                                System.out.println("2. Item Name");
                                System.out.println("3. Item Category");
                                System.out.println("4. Item Quantity");
                                System.out.println("5. Item Expiry Date");
                                System.out.println("6. Customer Name");
                                System.out.println("7. Customer Phone Number");

                                System.out.print("Enter choice: ");
                                int choice = in.nextInt();
                                in.nextLine();

                                if (choice == 1) {
                                    System.out.print("Enter new item ID: ");
                                    int newID = in.nextInt();
                                    obj.setItemId(newID);
                                    choiceErr = false;
                                    updated = true;
                                } 
                                else if(choice ==2){
                                    System.out.print("Enter new item Name: ");
                                    String newName = in.nextLine();
                                    obj.setItemName(newName);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if (choice == 3) {
                                    System.out.print("Enter new item category: ");
                                    char newCategory = in.next().charAt(0);
                                    obj.setItemCategory(newCategory);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==4){
                                    System.out.print("Enter new purchase quantity: ");
                                    int newQuantity = in.nextInt();
                                    obj.setPurchaseQuantity(newQuantity);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==5){
                                    System.out.print("Enter new expiry date: ");
                                    String newExpDate = in.next();
                                    obj.setExpiryDate(newExpDate);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==6){
                                    System.out.print("Enter new Customer Name: ");
                                    String newName = in.next();
                                    obj.setCustomerName(newName);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==7){
                                    System.out.print("Enter new Customer Phone Number: ");
                                    String newPhoneNo = in.next();
                                    obj.setCustomerPhoneNumber(newPhoneNo);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else{
                                    System.out.println("\nWrong choice!");
                                    choiceErr = true;
                                    updated =false; }

                            }

                            obj = purchaseList.getNext();
                        }

                        //rewrite the input file to the latest information
                        obj=purchaseList.getFirst();
                        BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"));

                        while (obj != null) {

                            bw.write(obj.getItemId() + ";" + obj.getItemName() + ";" + obj.getItemCategory() + ";"+ obj.getPurchaseQuantity() + ";" + obj.getExpiryDate() + ";" + obj.getCustomerId() + ";"+ obj.getCustomerName() + ";" + obj.getCustomerPhoneNumber() + ";" + obj.getTransactionId() + ";"+ obj.getPaymentStatus());
                            bw.newLine();
                            obj = purchaseList.getNext();
                        }

                        bw.close();

                        if(updated && !choiceErr){
                            System.out.println("Order has been updated successfully!");
                        }

                        if(!updated && !choiceErr){
                            System.out.println("\nCUSTOMER ID NOT FOUND!!!");
                            System.out.println("Please Enter The Customer ID Again.");
                        }

                        if(choiceErr){
                            System.out.println("\nCHOICE NOT VALID!!!");
                            System.out.println("Please Re-enter Customer ID.");

                        }

                        if(updated){
                            char ans;
                            do {
                                System.out.println("Do you want to update the details again [Y-YES|N-NO]: ");
                                ans = in.next().charAt(0);

                                if(ans == 'N' || ans == 'n'){
                                    modifyStatus = false;
                                }
                                else if (ans == 'Y' || ans == 'y') {
                                    updated = false; // reset update if the user want to update again
                                } 

                                if (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
                                    System.out.println("Invalid input! Please enter 'Y' or 'N'.");

                                }

                            } while(ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') ;

                        }

                        obj = purchaseList.getFirst();
                    }

                }

            }
            while(modifyStatus);

            //10. Remove records of customers who have completed their purchase.
            obj = purchaseList.getFirst();

            while (obj != null){

                if(obj.getPaymentStatus().equalsIgnoreCase("Paid")){
                    obj= purchaseList.removeFirst();
                }

                obj = purchaseList.getNext();
            }

            System.out.println("\nCustomer Records After Removing Completed Orders: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            obj = purchaseList.getFirst();
            while(obj != null ){

                System.out.print(obj.toString());
                obj = purchaseList.getNext();

            }

            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found");
        } catch (IOException io) {
            System.out.println("IO Exception");
        } catch (Exception e) {
            System.out.println("Exception handler error");
            e.printStackTrace();
        }
    }
}
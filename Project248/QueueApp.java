import java.io.*;
import java.util.*;
import java.text.*;

public class QueueApp
{
    public static void main (String[]args)throws Exception{
        try{
            Scanner in = new Scanner (System.in);
            in.useDelimiter("\n");

            DecimalFormat df=new DecimalFormat ("0.00");

            FileReader fr = new FileReader ("input.txt");
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

            int j=0;
            String inputData=null;
            Queue<FoodInventory> purchaseList = new Queue();

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

                purchaseList.enqueue(inv);

            }

            //1. store all the product information
            System.out.println("\nList of all Food Inventory details\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            FoodInventory temp =null;
            Queue <FoodInventory> tempQ = new Queue();

            while (!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                System.out.print(temp.toString());

                tempQ.enqueue(temp);
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (!tempQ.isEmpty()){
                temp = tempQ.dequeue();

                purchaseList.enqueue(temp);
            }

            //2.count the size of the list
            int count =0;
            while (!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                count++;

                tempQ.enqueue(temp);
            }
            System.out.println("\n\n----------SIZE OF THE LIST----------");
            System.out.println("\nthe size of the list: "+count);
            System.out.println();
            while (!tempQ.isEmpty()){
                temp = tempQ.dequeue();

                purchaseList.enqueue(temp);
            }

            //3.search and display items by their category

            System.out.println("========== Search by Item Category ==========");
            System.out.println("Available Categories:");
            System.out.println("  P - Fresh Produce");
            System.out.println("  G - Frozen Goods");
            System.out.println("  D - Dairy");
            System.out.println("  S - Snacks");
            System.out.println("---------------------------------------------");
            boolean found = false;
            String output="";
            do{
                System.out.println("\nEnter the item category you want to search for: ");
                char searchCategory = in.next().charAt(0);

                while (!purchaseList.isEmpty()){
                    temp = purchaseList.dequeue();

                    if(temp.getItemCategory() == searchCategory){
                        found=true;
                        output+=temp.toString();
                    }

                    tempQ.enqueue(temp);
                }
                if(found){
                    System.out.println("\nSearched item(s) details:\n");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print(output);
                    System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                }
                while (!tempQ.isEmpty()){
                    temp = tempQ.dequeue();

                    purchaseList.enqueue(temp);
                }
                if (!found) {
                    System.out.println("\nNO ITEM(S) FOUND IN THE SPECIFIED CATEGORY.");
                    System.out.println("\nPLEASE TRY AGAIN.");
                    //Ask user to enter again if there's an error

                }

            }while(!found);

            //4.Split the records into customers who have paid and those with pending payments
            Queue <FoodInventory> paidList = new Queue();  
            Queue <FoodInventory> pendingList= new Queue();
            Queue <FoodInventory> tempPaid = new Queue();
            Queue <FoodInventory> tempPending = new Queue();
            while (!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(temp.getPaymentStatus().equalsIgnoreCase("Paid")){
                    paidList.enqueue(temp);
                }
                else
                    pendingList.enqueue(temp);

                //tempQ.enqueue(temp);
            }

            //display
            System.out.println("\nPayment status-->(PAID) item(s) details:\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!paidList.isEmpty()){
                temp = paidList.dequeue();

                System.out.print(temp.toString());

                tempPaid.enqueue(temp);
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //display

            System.out.println("\nPayment status-->(PENDING) item(s) details:\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!pendingList.isEmpty()){
                temp = pendingList.dequeue();

                System.out.print(temp.toString());

                tempPending.enqueue(temp);
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //enqueue back to purchaseList queue
            while(!tempPaid.isEmpty()){
                temp = tempPaid.dequeue();

                purchaseList.enqueue(temp);
            }

            while(!tempPending.isEmpty()){
                temp = tempPending.dequeue();

                purchaseList.enqueue(temp);
            }
            Queue<FoodInventory> tempp = new Queue();

            //5.Count the number of customers for each category of items.

            int countP=0;
            int countG = 0;
            int countD =0;
            int countS =0;
            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(temp.getItemCategory() =='P'){
                    countP++;
                }
                else if(temp.getItemCategory() =='G'){
                    countG++;
                }
                else if(temp.getItemCategory() =='D'){
                    countD++;
                }
                else if(temp.getItemCategory() =='S'){
                    countS++;
                }

                tempp.enqueue(temp);
            }

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
            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }

            //6.Find and display highest and lowest details of the food inventory purchased amount by the customer
            FoodInventory highest = null ;
            FoodInventory lowest = null;
            double highestAmount=0;
            double lowestAmount = 99999;

            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(temp.Amount() > highestAmount){
                    highestAmount = temp.Amount();
                    highest = temp;
                }
                if(temp.getPaymentStatus().equalsIgnoreCase("Paid")){
                    if(temp.Amount() < lowestAmount){
                        lowestAmount = temp.Amount();
                        lowest = temp;
                    }
                }
                tempp.enqueue(temp);
            }

            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }

            System.out.println("\nCustomer details for highest purchase amount: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (highest !=null){
                System.out.print(highest.toString());
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("\nCustomer details for lowest purchase amount: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (lowest !=null){
                System.out.print(lowest.toString());
            }
            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //7    Calculate total sales for all orders
            double totSales =0;
            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(temp.getPaymentStatus().equalsIgnoreCase("Paid")){
                    totSales += temp.Amount();
                }
                tempp.enqueue(temp);
            }
            System.out.println();
            System.out.println("\nTotal Sales For All Orders: RM"+df.format(totSales));

            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }

            //8.Calculate total sales received for each category
            double totalP=0;
            double totalG = 0;
            double totalD =0;
            double totalS =0;
            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(temp.getItemCategory() =='P'){
                    totalP += temp.Amount();
                }
                else if(temp.getItemCategory() =='G'){
                    totalG += temp.Amount();
                }
                else if(temp.getItemCategory() =='D'){
                    totalD += temp.Amount();
                }
                else if(temp.getItemCategory() =='S'){
                    totalS += temp.Amount();
                }

                tempp.enqueue(temp);
            }

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
            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }
            //9.update order information if customer wants to change the purchase details
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
            boolean errAns = false;
            do{ 
                while(modifyStatus){

                    if(updateAns=='Y' || updateAns =='y'){
                        System.out.print("Enter the Customer ID to modify : ");
                        int modifyId = in.nextInt();
                        in.nextLine();

                        while(!purchaseList.isEmpty()){
                            temp = purchaseList.dequeue();

                            if(temp.getCustomerId() == modifyId && !updated){
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
                                    temp.setItemId(newID);
                                    choiceErr = false;
                                    updated = true;
                                } 
                                else if(choice ==2){
                                    System.out.print("Enter new item Name: ");
                                    String newName = in.nextLine();
                                    temp.setItemName(newName);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if (choice == 3) {
                                    System.out.print("Enter new item category: ");
                                    char newCategory = in.next().charAt(0);
                                    temp.setItemCategory(newCategory);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==4){
                                    System.out.print("Enter new purchase quantity: ");
                                    int newQuantity = in.nextInt();
                                    temp.setPurchaseQuantity(newQuantity);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==5){
                                    System.out.print("Enter new expiry date: ");
                                    String newExpDate = in.next();
                                    temp.setExpiryDate(newExpDate);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==6){
                                    System.out.print("Enter new Customer Name: ");
                                    String newName = in.next();
                                    temp.setCustomerName(newName);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else if(choice ==7){
                                    System.out.print("Enter new Customer Phone Number: ");
                                    String newPhoneNo = in.next();
                                    temp.setCustomerPhoneNumber(newPhoneNo);
                                    choiceErr = false;
                                    updated = true;
                                }
                                else{
                                    System.out.println("\nWrong choice!");
                                    choiceErr = true;
                                    updated =false;}
                            }

                            tempQ.enqueue(temp);
                        }

                        //rewrite the input file to the latest information

                        BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"));
                        while (!tempQ.isEmpty()) {
                            temp = tempQ.dequeue();
                            bw.write(temp.getItemId() + ";" + temp.getItemName() + ";" + temp.getItemCategory() + ";"+ temp.getPurchaseQuantity() + ";" + temp.getExpiryDate() + ";" + temp.getCustomerId() + ";"+ temp.getCustomerName() + ";" + temp.getCustomerPhoneNumber() + ";" + temp.getTransactionId() + ";"+ temp.getPaymentStatus());
                            bw.newLine();
                            purchaseList.enqueue(temp);
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
                            do{
                                System.out.println("Do you want to update the details again [Y-YES|N-NO]: ");
                                ans = in.next().charAt(0);
                                if(ans == 'N' || ans == 'n'){
                                    modifyStatus = false;
                                }
                                else if (ans == 'Y' || ans == 'y') {
                                    updated = false; // reset update if the user want to update again
                                } else {
                                    System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                                }
                            }while(ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') ;

                        }

                    }
                }
            }while(modifyStatus);

            //10.Remove certain records of the customer after the order is completed
            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(!(temp.getPaymentStatus().equalsIgnoreCase("Paid"))){
                    tempp.enqueue(temp);
                }
            }
            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }

            System.out.println("\nCustomer Records After Removing Completed Orders: \n");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("|%-8s|%-15s|%-10s|%-21s|%-11s|%-15s|%-15s|%-19s|%-15s|%-14s|%-19s|","ITEM ID", "ITEM NAME", "CATEGORY", "PURCHASE QUANTITY", "EXP. DATE", "CUSTOMER ID", "CUSTOMER NAME", "CUSTOMER PHONE NO", "TRANSACTION ID", "PAYMENT STATUS","PAYMENT AMOUNT (RM)"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(!purchaseList.isEmpty()){
                temp = purchaseList.dequeue();

                if(!(temp.getPaymentStatus().equalsIgnoreCase("Paid"))){
                    System.out.print(temp.toString());
                }

                tempp.enqueue(temp);
            }

            System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!tempp.isEmpty()){
                purchaseList.enqueue(tempp.dequeue());
            }

        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
        catch (IOException io){
            System.out.println("IO Exception");
        }
        catch (Exception e){
            System.out.println ("Exception handler error");
            e.printStackTrace();
        }
    }
}
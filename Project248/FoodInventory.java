import java.text.DecimalFormat;

public class FoodInventory {
    private int itemId;
    private String itemName;
    private char itemCategory;
    private int purchaseQuantity;
    private String expiryDate; 
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private int transactionId;
    private String paymentStatus;

    public FoodInventory(int itemId, String itemName, char itemCategory, int purchaseQuantity, 
    String expiryDate, int customerId, String customerName, String customerPhoneNumber, 
    int transactionId, String paymentStatus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.purchaseQuantity = purchaseQuantity;
        this.expiryDate = expiryDate; 
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public char getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(char itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
    
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Method to calculate the amount to be paid
    public double Amount() {
        double paymentAmount = 0;
        double taxRate = 0.05; // 5% tax
        double discount = 0.1; // 10% discount

        if (paymentStatus.equalsIgnoreCase("Paid")){

            if ((itemCategory == 'P') || (itemCategory == 'p')) { // Fresh Produce
                if (itemName.equalsIgnoreCase("Apples")) {
                    paymentAmount = 4 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Carrots")) {
                    paymentAmount = 3 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Lettuce")) {
                    paymentAmount = 2 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Bananas")) {
                    paymentAmount = 2 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Tomatoes")) {
                    paymentAmount = 3 * purchaseQuantity;
                }
            } 
            else if ((itemCategory == 'G') || (itemCategory == 'g')) { // Frozen Goods
                if (itemName.equalsIgnoreCase("Frozen Nugget")) {
                    paymentAmount = 15 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Frozen Fries")) {
                    paymentAmount = 8 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Ice Cream")) {
                    paymentAmount = 10 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Frozen Pizza")) {
                    paymentAmount = 18 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Frozen Burgers")) {
                    paymentAmount = 12 * purchaseQuantity;
                }
            } 
            else if ((itemCategory == 'D') || (itemCategory == 'd')) { // Dairy
                if (itemName.equalsIgnoreCase("Milk")) {
                    paymentAmount = 4 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Cheese")) {
                    paymentAmount = 10 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Butter")) {
                    paymentAmount = 7 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Yogurt")) {
                    paymentAmount = 5 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Cream")) {
                    paymentAmount = 6 * purchaseQuantity;
                }
            } 
            else if ((itemCategory == 'S') || (itemCategory == 's')) { // Snacks
                if (itemName.equalsIgnoreCase("Potato Chips")) {
                    paymentAmount = 3 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Chocolate Bars")) {
                    paymentAmount = 2 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Cookies")) {
                    paymentAmount = 5 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Popcorn")) {
                    paymentAmount = 4 * purchaseQuantity;
                } else if (itemName.equalsIgnoreCase("Gummy Bears")) {
                    paymentAmount = 6 * purchaseQuantity;
                }
            }

            if (paymentAmount > 100) {
                paymentAmount = paymentAmount - (paymentAmount * discount);
            }

            double tax = paymentAmount * taxRate;
            paymentAmount += tax;

        }

        return paymentAmount;
    }

    public String toString() {
        String output;

        output = String.format("%-10d%-19s%-15c%-14d%-16s%-16d%-16s%-21s%-17d%-14s",itemId,itemName,itemCategory,purchaseQuantity,expiryDate, customerId,customerName,customerPhoneNumber,transactionId,paymentStatus);

        if (paymentStatus.equalsIgnoreCase("Paid")) {
            output += String.format("%-16.2f\n", Amount());
        } else {
            output += String.format("%-15s\n", "Pending");
        }
        return output;
    }

}


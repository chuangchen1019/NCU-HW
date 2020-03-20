public class Invoice 
{
   private final int partNumber; 
   private final String partDescription;
   private int quantity;
   private double price;

   public Invoice(int partNumber, String partDescription, int quantity,
      double price)
   {
      if (quantity < 0) // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");

      if (price < 0.0) // validate price
         throw new IllegalArgumentException(
            "Price per item must be >= 0");

      this.partNumber = partNumber;
      this.partDescription = partDescription;
      this.quantity = quantity;
      this.price = price;
   }

   public int getPartNumber()
   {
      return partNumber; // should validate
   } 

   public String getPartDescription()
   {
      return partDescription;
   } 

   public void setQuantity(int quantity)
   {
      if (quantity < 0) // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");

      this.quantity = quantity;
   } 

   public int getQuantity()
   {
      return quantity;
   }

   public void setPrice(double price)
   {
      if (price < 0.0) // validate price
         throw new IllegalArgumentException(
            "Price per item must be >= 0");

      this.price = price;
   } 

   public double getPrice()
   {
      return price;
   } 

   public double getBalance()
   {
      return price * quantity;
   }

   @Override
   public String toString()
   {
      return String.format(
         "Id #: %-2d  Description: %-15s  Quantity: %-4d  Price: $%,10.2f", 
         getPartNumber(), getPartDescription(), 
         getQuantity(), getPrice());
   } 
}
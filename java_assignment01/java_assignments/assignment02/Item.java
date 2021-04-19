package java_assignments.assignment02;

public class Item
{
    public String itemName;             // unique model name with type
    public double itemRate;
    public String itemType;
    public int itemQuantity;
    public String itemCode;             /* fixed length string first 3 chars are the first 3 letters of
                                           the item common name(type) + "0"'s + "chrono count*/
    Item()
    {
        itemName = "";
        itemCode = "";
        itemQuantity = 0;
        itemRate = 0.0;
    }
}

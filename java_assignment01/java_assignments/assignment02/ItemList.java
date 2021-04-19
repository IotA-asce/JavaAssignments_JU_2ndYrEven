package java_assignments.assignment02;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ItemList
{
    private ArrayList<Item> itemList;
    private Tally tally;
    public String ShopType;
    private Scanner scan = new Scanner(System.in);

    ItemList()                                  // initialization done inside constr
    {
        ShopType = "";
        itemList = new ArrayList<>();
        tally = new Tally();
    }

    // operation methods below

    public void AddNewItem()                    // operation 1 accessible only to SEO   /* error with the count tally section */
    {
        String itemType = "";
        String iName = "";
        Item tempItem = new Item();
        double iRate = 0;
        String buff = "";
        int index = -1;
        int ind = 0;
        boolean found = false;
        String buffer = "";

        System.out.print("\n\t\tItem type : ");
        itemType = scan.nextLine();

        if (tally.productName.size() == 0)
        {
            tally.productName.add(itemType.toUpperCase(Locale.ROOT));
            tally.productCount.add(1);
            tally.productChronology.add(1);
            ind = 0;
        }else {
            for (int i = 0; i < tally.productName.size(); i++)
            {
                if (itemType.equalsIgnoreCase(tally.productName.get(i)))
                {
                    index = i;
                    ind = i;
                    break;
                }
            }

            if (index == -1)
            {
                tally.productName.add(itemType);
                tally.productCount.add(1);
                tally.productChronology.add(1);
                ind = tally.productName.size() - 1;
            }
            else
            {
                int tct = tally.productCount.get(index);
                int tcr = tally.productChronology.get(index);

                tally.productCount.remove(index);
                tally.productChronology.remove(index);

                tally.productCount.add(index, ++tct);
                tally.productChronology.add(index, ++tcr);
            }
        }

        System.out.print("\n\t\tItem name : ");
        iName = scan.nextLine();
        if (itemList.size() == 0)
        {
            tempItem.itemName = String.valueOf(iName.toUpperCase(Locale.ROOT).toCharArray(),0,iName.length());
            tempItem.itemType = String.valueOf(itemType.toUpperCase(Locale.ROOT).toCharArray(),0,itemType.length());
            tempItem.itemCode = String.valueOf(itemType.toUpperCase(Locale.ROOT).toCharArray(),0,3) + "00" + 1;
            tempItem.itemQuantity = 1;
            System.out.print("\n\t\tItem rate : $");
            tempItem.itemRate = scan.nextDouble();
            buff = scan.nextLine();

            itemList.add(tempItem);

        }else if (itemList.size() > 0){
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).itemName.equals(iName)) {
                    itemList.get(i).itemQuantity += 1;
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                tempItem.itemName = String.valueOf(iName.toCharArray(),0,iName.length());
                tempItem.itemQuantity = 1;

                if (tally.productChronology.get(ind) < 10)
                {
                    buffer = "00";
                }else if (tally.productChronology.get(ind) >= 10 && tally.productChronology.get(ind) < 100)
                {
                    buffer = "0";
                }else{
                    buffer = "";
                }
                tempItem.itemType = String.valueOf(itemType.toUpperCase(Locale.ROOT).toCharArray(),0,itemType.length());
                tempItem.itemCode = String.valueOf((itemType.toUpperCase(Locale.ROOT).toCharArray()),0,3) + buffer + tally.productChronology.get(ind);

                System.out.print("\n\t\tItem rate : $");
                tempItem.itemRate = scan.nextDouble();
                buff = scan.nextLine();

                itemList.add(tempItem);
            }
        }
        tempItem = null;
        System.gc();

        // constructing the item code next

    }

    public void UpdataRateOfAnItem()            // operation 2 accessible only to SEO
    {

        String itemType = "";
        boolean found = false;
        String itemCode = "";
        String buff = "";
        int index = -1;

        // search the Item type and display the items with matching item type
        System.out.println("________________________________________________________________________");
        System.out.println("::::::::::::::::::::::::::::Update item rate::::::::::::::::::::::::::::");
        System.out.println("________________________________________________________________________");

        System.out.print("\n\t\tItem type : ");
        itemType = scan.nextLine();

        System.out.println("\n              Items with matching item type listed below");
        System.out.println("\n\tItem Code\t\tItem name\t\t\t\t\t\t\t\tItem Rate\t\tquantity");
        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemList.get(i).itemType.equalsIgnoreCase(itemType))
            {
                System.out.println("__________________________________________________________________________________");
                System.out.print("\t" + itemList.get(i).itemCode + "\t\t\t" + itemList.get(i).itemName);
                if (itemList.get(i).itemName.length() < 25)
                {
                    for (int j = 0; j < (25-itemList.get(i).itemName.length()); j++)
                    {
                        System.out.print(" ");
                    }
                }else{
                    System.out.print("");
                }
                System.out.println("\t\t\t\t $" + itemList.get(i).itemRate + "\t\t\t" + itemList.get(i).itemQuantity);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("\n\t\t\tERROR : Items with matching item type not found");
            return;
        }

        System.out.println("__________________________________________________________________________________");
        System.out.print("\n\t\tSelect the item code to update rate : ");
        itemCode = scan.nextLine();

        found = false;
        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemType.equalsIgnoreCase(itemList.get(i).itemType) && itemCode.equalsIgnoreCase(itemList.get(i).itemCode))
            {
                System.out.println("\n\t\t\tCurrent rate : $" + itemList.get(i).itemRate);
                System.out.print    ("\t\t\tUpdated rate : $");
                itemList.get(i).itemRate = scan.nextDouble();
                buff = scan.nextLine();
                found = true;
                System.out.println("::::::::::::::::::::::::::::Updated details:::::::::::::::::::::::::::::");
                System.out.println("\n\tItem Code\t\t\tItem name\t\t\t\t\t\t\tItem Rate\t\tquantity");
                System.out.println("__________________________________________________________________________________");
                System.out.print("\n\t" + itemCode + "\t\t\t" + itemList.get(i).itemName);
                if (itemList.get(i).itemName.length() < 25)
                {
                    for (int j = 0; j < (25-itemList.get(i).itemName.length()); j++)
                    {
                        System.out.print(" ");
                    }
                }else{
                    System.out.print("");
                }
                System.out.println("\t\t\t\t $" + itemList.get(i).itemRate + "\t\t\t" + itemList.get(i).itemQuantity);
            }
        }
        if (!found)
        {
            System.out.println("\n\t\t\tERROR : wrong item code entered");
        }

    }

    public void IssueItem()                     // operation 3 accessible only to SEO
    {
        String itemType = "";
        boolean found = false;
        String itemCode = "";
        double qty = 0;
        String buff = "";
        int index = -1;

        // check for availability
        // if tally.productName isEmpty --> exit
        if (tally.productName.isEmpty())
        {
            System.out.println("\n\t\t\tERROR : No items registered");
        }else{

            System.out.println("\n::::::::::::::::::::::::::Available items:::::::::::::::::::::::::::::::\n");
            System.out.println("");
            System.out.println("\t\tItem type\t\t\t|||\t\t\tQuantity\n");
            for (int i = 0; i < tally.productName.size(); i++)
            {
                System.out.print("\t\t" + tally.productName.get(i));
                for (int k = 0; k < (20 - tally.productName.get(i).length()); k++ )
                {
                    System.out.print(" ");
                }
                System.out.println(" :\t\t\t\t" + tally.productCount.get(i));
            }
            System.out.println("\n\t\tChoose from the above available item types");
            System.out.println("________________________________________________");
            System.out.print("\t\tItem type : ");
            itemType = scan.nextLine();

            for (int i = 0; i < tally.productName.size(); i++)
            {
                if (itemType.equalsIgnoreCase(tally.productName.get(i)))
                {
                    if (tally.productCount.get(i) == 0)
                    {
                        System.out.println("\t\tNot enough items of this type are available");
                        return;
                    }
                    index = i;
                    found = true;
                }
            }
            if (!found)
            {
                System.out.println("\t\tERROR : Item type not available");
            }

            System.out.println("::::::::::::::::::::::::Options available::::::::::::::::::::::::::\n");
            System.out.println("\t\tItem code\t\tItem name\t\t\t\t\tRate\t\tQuantity\n\n");
            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemType.equalsIgnoreCase(itemList.get(i).itemType))
                {
                    System.out.print("\t\t" + itemList.get(i).itemCode + "\t\t\t" + itemList.get(i).itemName);
                    for (int k = 0; k < (22 - itemList.get(i).itemName.length()); k++)
                    {
                        System.out.print(" ");
                    }
                    System.out.println("\t" + itemList.get(i).itemRate + "\t\t\t\t" + itemList.get(i).itemQuantity);
                }
            }
            System.out.print("\n\n\n\t\tEnter item code for issue : ");
            itemCode = scan.nextLine();

            found = false;
            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemCode.equals(itemList.get(i).itemCode))
                {
                    System.out.println("Quantity available : " + itemList.get(i).itemQuantity);
                    System.out.print("\n\t\tQuantity to issue : ");
                    qty = scan.nextDouble();
                    buff = scan.nextLine();
                    if(qty > itemList.get(i).itemQuantity)
                    {
                        System.out.println("\n\t\tERROR : Not enough items available");
                    }
                    else
                    {
                        itemList.get(i).itemQuantity -= qty;
                        int temp = tally.productCount.get(index);
                        tally.productCount.remove(index);
                        temp -= qty;
                        tally.productCount.add(index,temp);
                    }
                }
            }
        }
    }


    public void CheckAvailabilityOfAnItem()     // operation 4 accessible to both SEO and SK
    {
        String itemType = "";
        int index = -1;

        System.out.println("_____________________________________________________");
        System.out.println("\n\n\t\t\tcheck availability of an item");
        System.out.println("_____________________________________________________");
        System.out.print("\n\nItem type : ");
        itemType = scan.nextLine();

        for (int i = 0; i < tally.productName.size(); i++)
        {
            if (itemType.equals(tally.productName.get(i)))
            {
                index = i;
            }
        }
        if (index == -1)
        {
            System.out.println("\n\t\tERROR : Items with matching item type not found");
            return;
        }else{
            System.out.println("\n\t\tQuantity available : " + tally.productCount.get(index));

            System.out.println("\n");
            System.out.println("________________________________________________________________________________________");
            System.out.println("\t\tItem code\t\tItem name\t\t\t\t\t\t\t Rate\t\t\t\tQuantity");
            System.out.println("\t\t_________\t\t_________\t\t\t\t\t\t\t ____\t\t\t\t________\n");

            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemType.equalsIgnoreCase(itemList.get(i).itemType))
                {
                    System.out.print("\t\t  " + itemList.get(i).itemCode + "\t\t" + itemList.get(i).itemName);
                    for (int k = 0; k < 25 - itemList.get(i).itemName.length(); k++) {
                        System.out.print(" ");
                    }
                    System.out.println("\t\t\t" + itemList.get(i).itemRate + "\t\t\t\t\t" + itemList.get(i).itemQuantity);
                }
            }
            System.out.println("\n\n");
            System.out.println("________________________________________________________________________________________");

        }
    }

    public void CheckQuantity_or_Price()        // operation 5 accessible to both SEO and SK
    {
        String itemType = "";
        int index = -1;

        System.out.println("_____________________________________________________");
        System.out.println("\n\n\t\t\tcheck availability of an item");
        System.out.println("_____________________________________________________");
        System.out.print("\n\nItem type : ");
        itemType = scan.nextLine();

        for (int i = 0; i < tally.productName.size(); i++)
        {
            if (itemType.equals(tally.productName.get(i)))
            {
                index = i;
            }
        }
        if (index == -1)
        {
            System.out.println("\n\t\tERROR : Items with matching item type not found");
            return;
        }else{
            System.out.println("\n\t\tQuantity available : " + tally.productCount.get(index));

            System.out.println("\n");
            System.out.println("________________________________________________________________________________________");
            System.out.println("\t\tItem code\t\tItem name\t\t\t\t\t\t\t Rate\t\t\t\tQuantity");
            System.out.println("\t\t_________\t\t_________\t\t\t\t\t\t\t ____\t\t\t\t________\n");

            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemType.equalsIgnoreCase(itemList.get(i).itemType))
                {
                    System.out.print("\t\t  " + itemList.get(i).itemCode + "\t\t" + itemList.get(i).itemName);
                    for (int k = 0; k < 25 - itemList.get(i).itemName.length(); k++) {
                        System.out.print(" ");
                    }
                    System.out.println("\t\t\t" + itemList.get(i).itemRate + "\t\t\t\t\t" + itemList.get(i).itemQuantity);
                }
            }
            System.out.println("\n\n");
            System.out.println("________________________________________________________________________________________");

        }
    }

    public void ItemRateAboveValue(double limitRate)    // operation 6 accessible to both SEO and SK
    {
        System.out.println("\n");
        System.out.println("________________________________________________________________________________________");
        System.out.println("\t\tItem code\t\tItem name\t\t\t\t\t\t\t Rate\t\t\t\tQuantity");
        System.out.println("\t\t_________\t\t_________\t\t\t\t\t\t\t ____\t\t\t\t________\n");

        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemList.get(i).itemRate >= limitRate)
            {
                System.out.print("\t\t  " + itemList.get(i).itemCode + "\t\t" + itemList.get(i).itemName);
                for (int k = 0; k < 25 - itemList.get(i).itemName.length(); k++) {
                    System.out.print(" ");
                }
                System.out.println("\t\t\t" + itemList.get(i).itemRate + "\t\t\t\t\t" + itemList.get(i).itemQuantity);
            }
        }
        System.out.println("\n\n");
        System.out.println("________________________________________________________________________________________");
    }

    public void ShowAllItems()          // this is for debugging purpose only
    {
        System.out.println("________________________________________________________________________________________");
        System.out.println("\t\tItem code\t\tItem name\t\t\t\t\t\t\t Rate\t\t\t\tQuantity");
        System.out.println("\t\t_________\t\t_________\t\t\t\t\t\t\t ____\t\t\t\t________\n");
        for (int i = 0; i < itemList.size(); i++)
        {
            System.out.print("\t\t  " + itemList.get(i).itemCode + "\t\t" + itemList.get(i).itemName );
            for (int k = 0; k < 25 - itemList.get(i).itemName.length(); k++)
            {
                System.out.print(" ");
            }
            System.out.println("\t\t\t" + itemList.get(i).itemRate + "\t\t\t\t\t" + itemList.get(i).itemQuantity);
        }
        System.out.println("\n\n");
        System.out.println("________________________________________________________________________________________");
    }

}

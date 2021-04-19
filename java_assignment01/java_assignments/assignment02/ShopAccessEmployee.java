package java_assignments.assignment02;

import java.util.Scanner;

public class ShopAccessEmployee
{
    ItemList itemList;
    Scanner scan = new Scanner(System.in);
    private final String[] userType;
    private String[] password;

    ShopAccessEmployee()
    {
        userType = new String[2];
        password = new String[2];
        itemList = new ItemList();

        // initialize the password with "admin" and userType with SEO and SK
        userType[0] = "SEO";
        userType[1] = "SK";

        password[0] = "admin";
        password[1] = "admin";

        // the following thought unnecessary is to Set the type of shop
        System.out.println("______________________________________________________\n");
        System.out.print("\tSet shop type : ");
        itemList.ShopType = scan.nextLine();
        System.out.println("______________________________________________________\n");
    }

    public void Operations()
    {
        int exitOuter = 0;
        int exitInner = 0;
        String choiceOuter = "";
        String choiceInner = "";
        boolean loginStatus = false;

        while (exitOuter == 0)
        {
            System.out.println("_________________________________________________________");
            System.out.println(":::::::::::::::::::Options available:::::::::::::::::::::\n");
            System.out.println("\tChoice 1\t:\t\tSign in");
            System.out.println("\tChoice 2\t:\t\tReset password");
            System.out.println("\tChoice 3\t:\t\texit");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("__________________________________________________________\n");
            System.out.print("\t\tChoice\t:\t");
            choiceOuter = scan.nextLine();

            // switch case is a lot better choice of driving the user but if and else is used here

            if (choiceOuter.equals("1"))
            {
                String tempUsername = "";
                String tempPassword = "";
                int index = -1;

                System.out.print("\n\t\tUsername : ");
                tempUsername = scan.nextLine();
                System.out.print("\t\tpassword : ");
                tempPassword = scan.nextLine();

                // compare the password with the existing password
                for (int i = 0; i < 2; i++)
                {
                    if (tempUsername.equals(userType[i]))
                    {
                        index = i;
                        break;
                    }
                }
                if (index != -1)
                {
                    if (tempPassword.equals(password[index]))
                    {
                        loginStatus = true;
                    }else {
                        loginStatus = false;
                    }
                }else{
                    System.out.println("\n\tERROR : Username not valid");
                    continue;
                }

                if (loginStatus)
                {
                    if (tempUsername.equals("SEO"))                 // provide access to all operations // can also use the index as a valid choice system
                    {
                        // lots of switch case here
                        System.out.println("\n\t\t\tSuccessfully logged in as SEO\n\n");
                        while(exitInner == 0)
                        {
                            System.out.println("\n\n:::::::::::::::::::Options available:::::::::::::::::::::\n");
                            System.out.println("\tChoice 1\t:\tRegister new item");
                            System.out.println("\tChoice 2\t:\tUpdate item rate");
                            System.out.println("\tChoice 3\t:\tIssue item");
                            System.out.println("\tChoice 4\t:\tCheck availability of an item");
                            System.out.println("\tChoice 5\t:\tCheck quantity or price");
                            System.out.println("\tChoice 6\t:\tItems above a given price");
                            System.out.println("\tChoice 7\t:\tLogout");
                            System.out.println("__________________________________________________________\n");

                            System.out.print("\n\t\tEnter choice : ");
                            choiceInner = scan.nextLine();

                            if (choiceInner.equals("1"))
                            {
                                itemList.AddNewItem();
                            }
                            else if (choiceInner.equals("2"))
                            {
                                itemList.UpdataRateOfAnItem();
                            }
                            else if (choiceInner.equals("3"))
                            {
                                itemList.IssueItem();
                            }
                            else if (choiceInner.equals("4"))
                            {
                                itemList.CheckAvailabilityOfAnItem();
                            }
                            else if (choiceInner.equals("5"))
                            {
                                itemList.CheckQuantity_or_Price();
                            }
                            else if (choiceInner.equals("6"))
                            {
                                double limitRate;
                                String buff = "";

                                System.out.print("\n\t\tView items above rate : ");
                                limitRate = scan.nextDouble();
                                buff = scan.nextLine();


                                itemList.ItemRateAboveValue(limitRate);
                            }
                            else if (choiceInner.equals("7"))
                            {
                                loginStatus = false;
                                exitInner++;
                            }
                            else if (choiceInner.equals("13"))
                            {
                                itemList.ShowAllItems();
                            }
                            else{
                                System.out.println("\n\t\tERROR : INVALID CHOICE");
                            }
                        }
                    }
                    exitInner = 0;

                    if (tempUsername.equals("SK"))             // provaide access to the respective operations
                    {
                        // switch case here
                        System.out.println("");
                        System.out.println("\n\t\t\tSuccessfully logged in as SK\n\n");
                        while(exitInner == 0)
                        {
                            System.out.println(":::::::::::::::::::Options available:::::::::::::::::::::\n");
                            System.out.println("\tChoice 1\t:\tCheck availability of an item");
                            System.out.println("\tChoice 2\t:\tCheck quantity or price");
                            System.out.println("\tChoice 3\t:\tItems above a given price");
                            System.out.println("\tChoice 4\t:\tLogout");
                            System.out.println("__________________________________________________________\n");

                            System.out.print("\n\t\tEnter choice : ");
                            choiceInner = scan.nextLine();

                            if (choiceInner.equals("1"))
                            {
                                itemList.CheckAvailabilityOfAnItem();
                            }
                            else if (choiceInner.equals("2"))
                            {
                                itemList.CheckQuantity_or_Price();
                            }
                            else if (choiceInner.equals("3"))
                            {
                                double limitRate;
                                String buff = "";

                                System.out.print("\n\t\tView items above rate : ");
                                limitRate = scan.nextDouble();
                                buff = scan.nextLine();


                                itemList.ItemRateAboveValue(limitRate);
                            }
                            else if (choiceInner.equals("4"))
                            {
                                loginStatus = false;
                                exitInner++;
                            }
                            else{
                                System.out.println("\n\t\t\tERROR : INVALID CHOICE");
                            }
                        }
                    }
                }else{
                    System.out.println("\n\tERROR : Wrong password entered contact admin");
                }

                // reset the strings to null
            }

            else if (choiceOuter.equals("2"))
            {
                // check for eisting password
                String tempUserName = "";
                String tempUserPass = "";
                String newpass1 = "";
                String newpass2 = "";
                int index = -1;

                System.out.println("\n\t\t\tEnter Details\n\n");
                System.out.print("\n\t\tUsername : ");
                tempUserName = scan.nextLine();
                for (int i = 0; i < 2; i++)
                {
                    if (tempUserName.equals(userType[i]))
                    {
                        index = i;
                        break;
                    }
                }
                if (index == -1)
                {
                    System.out.println("\n\t\tInvalid username");
                    continue;
                }
                else{
                    System.out.print("\n\t\tCurrent Password : ");
                    tempUserPass = scan.nextLine();

                    if (!tempUserPass.equals(password[index]))
                    {
                        System.out.println("\n\t\tERROR : Password does not match");
                        continue;
                    }
                    else
                    {
                        System.out.print("\n\t\tNew password : ");
                        newpass1 = scan.nextLine();
                        System.out.print("\n\t\tConfirm password : ");
                        newpass2 = scan.nextLine();

                        if (!newpass1.equals(newpass2))
                        {
                            System.out.println("\n\t\tERROR : passwords dont match");
                            continue;
                        }
                        else
                        {
                            password[index] = String.valueOf(newpass1.toCharArray(),0,newpass1.length());
                            System.out.println("\n\t\tPassword updated successfuly for " + userType[index]);
                            continue;
                        }
                    }

                }
            }
            else if (choiceOuter.equals("3"))
            {
                System.out.println("\n\n\t\tExit initiated");
                exitOuter++;
            }
            else
            {
                System.out.println("\n\t\tEnter valid choice");
            }

        }

    }

}


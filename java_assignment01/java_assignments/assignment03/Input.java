package java_assignments.assignment03;

import java.util.Scanner;

public class Input
{
    Scanner scan = new Scanner(System.in);
    String brackets;
    char[] opening = new char[3];
    char[] closing = new char[3];
    char[] buff;
    Stack stk = new Stack();

    Input()
    {
        opening[0] = '(';
        opening[1] = '{';
        opening[2] = '[';

        closing[0] = ')';
        closing[1] = '}';
        closing[2] = ']';
    }

    void TakeInput()
    {
        System.out.println("Enter the bracketing sequence");
        System.out.print("Sequence : ");
        brackets = scan.nextLine();
        buff = new char[brackets.length()];

        for (int i = 0; i < brackets.length(); i++)
        {
            buff[i] = brackets.charAt(i);
        }
    }
//  [()]{}{[()()]()}
    void Compare()
    {
        boolean possible = true;
        for (char c : buff)
        {
            for (int i = 0; i < 3; i++)
            {
                if (c == opening[i])
                {
                    stk.push(c);
                }
            }
            for (int i = 0; i < 3; i++)
            {
                if (c == closing[i])
                {
                    char temp = stk.pop();
                    if (temp == '\0')
                    {
                        possible = false;
                        break;
                    }
                    for (int j = 0; j < 3; j++)
                    {
                        if (temp == opening[j])
                        {
                            if (i == j)
                            {
                                possible = true;
                            }
                            else
                            {
                                possible = false;
                                break;
                            }
                        }
                    }

                }
            }
            if (!possible)
            {
                break;
            }
        }
        if (possible)
        {
            if (stk.stack.isEmpty())
            {
                System.out.println("True");
            }
            else{
                System.out.println("false");
            }
        }
        else{
            System.out.println("False");
        }
    }

}

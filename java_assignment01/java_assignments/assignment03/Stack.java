package java_assignments.assignment03;

import java.util.ArrayList;

public class Stack
{
    ArrayList<Character> stack = new ArrayList<Character>();

    public void push(char c)
    {
        stack.add(c);
    }
    public char pop()
    {
        int length = stack.size();
        if(length == 0)
        {
            return '\0';
        }
        char c = stack.get(length-1);
        stack.remove(length-1);
        return c;
    }
}

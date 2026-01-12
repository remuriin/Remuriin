import java.util.*;
class Stack
{
    int stacks[] = new int[5];
    int top = -1;
            
    void push(int data)
    {
        if (top == stacks.length - 1)
        {
            System.out.println("Stack is full");
        }
        else
        {
            top++;
            stacks[top] = data;
        }
    }

    int pop()
    {
        int popped = 0;
        if (top == -1)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            popped = stacks[top];
            stacks[top] = 0;
            top--;
        }
        return popped;
    }

    void display()
    {
        if (top == -1)
        {
            System.out.println("Stack is empty");
            return;
        }

        for (int i = top; i > -1; i--)
        {
            System.out.println(stacks[i]);
        }
    }

    int peek()
    {
        if (top == -1)
        {
            System.out.println("Stack is empty");
            return -1;
        }
        return stacks[top];
    }

    void remove(int data)
    {
        if (top == -1)
        {
            System.out.println("Stack is empty");
            return;
        }
        
        boolean found = false;
        Stack holder = new Stack();

        while (top != -1)
        {
            int pointer = stacks[top];
            if (data == pointer)
            {
                found = true;
                System.out.println("Removed: " + pointer);
                break;
            }
            else
            {
                holder.push(pointer);
            }
            top--;
        }

        while (holder.top != -1)
        {
            push(holder.stacks[holder.top--]);
        }

        if (!found)
        {
            System.out.println("not found");
        }
    }
}

public class stacksArray
{
    public static void main(String[] args)
    {
        Stack s = new Stack();
        Scanner in = new Scanner(System.in);
        
        int choice = 0;
        
        do
        {
            System.out.println("[0] exit");
            System.out.println("[1] push");
            System.out.println("[2] pop");
            System.out.println("[3] display");
            System.out.println("[4] peek");
            System.out.println("[5] remove");
            
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            
            if (choice == 1)
            {
                System.out.print("Enter data : ");
                int data = in.nextInt();
                s.push(data);
            }
            else if (choice == 2)
            {
                System.out.println("Removed value: " + s.pop());
            }
            else if (choice == 3)
            {
                s.display();
            }
            else if (choice == 4)
            {
                System.out.println("Peek value: " + s.peek());
            }
            else if (choice == 5)
            {
                System.out.print("Enter value to remove: ");
                int rem = in.nextInt();
                s.remove(rem);
            }
            else if (choice == 0)
            {
                System.out.println("Exiting...");
                break;
            }
            else
            {
                System.out.println("Invalid choice\n");
            }
        }
        while (choice != 0);
    }
}

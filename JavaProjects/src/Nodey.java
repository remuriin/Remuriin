import java.util.Scanner;

class Node
{
    int data;
    Node next;
}

class LinkedList
{
    Node head;

    int count()
    {
        Node pointer = head;
        int ctr = 0;
        while (pointer != null)
        {
            ctr++;
            pointer = pointer.next;    
        }
        return ctr;
    }

    void addStart(int data)
    {
        Node node = new Node();
        node.data = data;
        node.next = head;
        head = node;
    }

    void addEnd(int data)
    {
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (head == null)
        {
            head = node;
        } else
        {
            Node pointer = head;
            while (pointer.next != null)
            {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    void addAtIndex(int index, int data)
    {
        if (index < 0 || index > count())
        {
            System.out.println("Invalid index");
            return;
        }

        if (index == 0)
        {
            addStart(data);
        } else
        {
            Node node = new Node();
            node.data = data;
            node.next = null;

            Node pointer = head;
            Node prev = null;
            for (int i = 0; i < index; i++)
            {
                prev = pointer;
                pointer = pointer.next;
            }
            prev.next = node;
            node.next = pointer;
        }
    }

    void deleteStart()
    {
        if (head != null)
        {
            head = head.next;
        } else
        {
            System.out.println("List is empty.");
        }
    }

    void deleteEnd()
    {
        if (head == null)
        {
            System.out.println("List is empty");
            return;
        }

        if (head.next == null)
        {
            head = null;
        } else
        {
            Node pointer = head;
            Node prev = null;
            while (pointer.next != null)
            {
                prev = pointer;
                pointer = pointer.next;    
            }
            prev.next = null;
        }
    }

    void deleteAtIndex(int index)
    {
        if (head == null)
        {
            System.out.println("List is empty.");
            return;
        }

        if (index < 0 || index > count() - 1)
        {
            System.out.println("Invalid index.");
            return;
        }

        if (index == 0)
        {
            deleteStart();
        } else
        {
            Node pointer = head;
            Node prev = null;
            for (int i = 0; i < index; i++)
            {
                prev = pointer;
                pointer = pointer.next;
            }
            prev.next = pointer.next;
        }
    }

    void display()
    {
        Node pointer = head;
        while (pointer != null)
        {
            System.out.println(pointer.data);
            pointer = pointer.next;
        }
    }

    void reverseList()
    {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
}

public class Nodey
{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        LinkedList link = new LinkedList();

        while (true)
        {
            System.out.println("""
                    [1] - add at start
                    [2] - add at end
                    [3] - add at index
                    [4] - delete at start
                    [5] - delete at end
                    [6] - delete at index
                    [7] - list count
                    [8] - display list
                    [9] - reverse list
                    """);
            System.out.print("choice: ");
            int choice = input.nextInt();

            int data = 0;
            int index = 0;
            switch (choice)
            {
                case 1: {
                    System.out.print("Data: ");
                    data = input.nextInt();
                    link.addStart(data);
                    break;
                }
                case 2: {
                    System.out.print("Data: ");
                    data = input.nextInt();
                    link.addEnd(data);
                    break;
                }
                case 3: {
                    System.out.print("Index: ");
                    index = input.nextInt();
                    System.out.print("Data: ");
                    data = input.nextInt();
                    link.addAtIndex(index, data);
                    break;
                }
                case 4: {
                    link.deleteStart();
                    break;
                }
                case 5: {
                    link.deleteEnd();
                    break;
                }
                case 6: {
                    System.out.print("Index: ");
                    index = input.nextInt();
                    link.deleteAtIndex(index);
                    break;
                }
                case 7: {
                    System.out.println("List count: " + link.count());
                    break;
                }
                case 8: {
                    link.display();
                    break;
                }
                case 9: {
                    link.reverseList();
                    break;
                }
            }
        }
    }
}
import java.util.Scanner;

class QArray
{
    int[] queue = new int[5];
    int front = -1;
    int rear = -1;
    boolean emptied = true;

    int count()
    {
        if (front == -1)
        {
            return 0;
        }
        return rear - front + 1;
    }

    void enqueue(int data)
    {
        if (!emptied)
        {
            System.out.println("Dequeue all elements first.");
            return;
        }

        if (rear == queue.length - 1)
        {
            System.out.println("Queue is full.");
            return;
        }

        if (count() == 0)
        {
            front = 0;
        }

        rear++;
        queue[rear] = data;
        System.out.println("Enqueued: " + data);
    }

    void dequeue()
    {
        if (front == -1)
        {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Removed: " + queue[front]);
        front++;
        emptied = false;

        if (front > rear)
        {
            front = -1;
            rear = -1;
            emptied = true;
            System.out.println("Queue is empty.");
        }
    }

    int peek()
    {
        if (front == -1)
        {
            System.out.println("Queue is empty.");
            return -1;
        }
        return queue[front];
    }

    void display()
    {
        if (front == -1)
        {
            System.out.println("Queue is empty.");
            return;
        }

        for (int i = front; i <= rear; i++)
        {
            System.out.print(queue[i] + "\t");
        }
        System.out.println();
    }
}

public class QueueArray
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        QArray qArray = new QArray();
        int choice;

        do
        {
            System.out.println("""
                    [1] enqueue
                    [2] dequeue
                    [3] peek
                    [4] display
                    [5] count
                    [0] exit
                    """);
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice)
            {
                case 1 ->
                {
                    if (qArray.emptied && qArray.count() != qArray.queue.length)
                    {
                        System.out.print("Enter value: ");
                        int val = input.nextInt();
                        qArray.enqueue(val);
                    }
                    else if (!qArray.emptied)
                    {
                        System.out.println("Dequeue all elements first.");
                    }
                    else
                    {
                        System.out.println("Queue is full.");
                    }
                }

                case 2 -> qArray.dequeue();
                case 3 -> System.out.println("Front value: " + qArray.peek());
                case 4 -> qArray.display();
                case 5 -> System.out.println("Number of elements: " + qArray.count());
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

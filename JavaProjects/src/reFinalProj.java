import java.util.*;
import java.io.*;
import java.time.Year;
import java.text.DecimalFormat;

public class reFinalProj
{
    static Scanner input = new Scanner(System.in);
    static DecimalFormat twodec = new DecimalFormat("0.00");

    static ArrayList<ArrayList<String>> title = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> stocks = new ArrayList<>();

    static ArrayList<Boolean> select = new ArrayList<>();

    static ArrayList<ArrayList<String>> titleBin = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> stocksBin = new ArrayList<>();
    static int binPageLimit = 1;

    static int totalBookCount = 0;

    static ArrayList<String> adminName = new ArrayList<String>();
    static ArrayList<String> adminPass = new ArrayList<String>();

    static String loggedAcc = "";
    static String loggedPass = "";
    static int loggedAccNum = 0;
    static boolean editSaved = false;
    static Boolean selecting = false;

    static ArrayList<String> customerName = new ArrayList<String>();
    static ArrayList<String> customerBday = new ArrayList<String>();
    static ArrayList<String> customerHistory = new ArrayList<String>();
    static ArrayList<String> customerNameBin = new ArrayList<String>();
    static ArrayList<String> customerBdayBin = new ArrayList<String>();
    static ArrayList<Boolean> pwd = new ArrayList<Boolean>();
    static ArrayList<Boolean> pwdBin = new ArrayList<Boolean>();

    static boolean buying = false;
    static boolean buyAddCustomer = false;
    static boolean buyAddCustomerSaved = true;
    static boolean buyselecting = false;
    static int yearForReceipt = 0;
    static int selectedQuantity[] = { 1, 1, 1, 1, 1 };

    public static void main(String[] args)
    {
        // BOOK TITLES
        ArrayList<String> yr1Titles = new ArrayList<>();
        yr1Titles.add("Introduction to Computing");
        yr1Titles.add("Computer Programming 1");
        yr1Titles.add("Computer Programming 2");
        yr1Titles.add("Platform Technologies");
        ArrayList<String> yr2Titles = new ArrayList<>();
        yr2Titles.add("Data Structures and Algorithms");
        yr2Titles.add("Information Management");
        yr2Titles.add("Object-oriented Programming");
        ArrayList<String> yr3Titles = new ArrayList<>();
        yr3Titles.add("Networking 1");
        yr3Titles.add("Quantitative Methods");
        yr3Titles.add("Event-Driven programming");
        ArrayList<String> yr4Titles = new ArrayList<>();
        yr4Titles.add("Advanced Database Systems");
        yr4Titles.add("Introduction to Data Analytics");
        yr4Titles.add("Web Systems and Technologies");

        // STOCKS
        ArrayList<Integer> yr1BookStocks = new ArrayList<>();
        yr1BookStocks.add(3);
        yr1BookStocks.add(3);
        yr1BookStocks.add(3);
        yr1BookStocks.add(3);
        ArrayList<Integer> yr2BookStocks = new ArrayList<>();
        yr2BookStocks.add(3);
        yr2BookStocks.add(3);
        yr2BookStocks.add(3);
        ArrayList<Integer> yr3BookStocks = new ArrayList<>();
        yr3BookStocks.add(3);
        yr3BookStocks.add(3);
        yr3BookStocks.add(3);
        ArrayList<Integer> yr4BookStocks = new ArrayList<>();
        yr4BookStocks.add(3);
        yr4BookStocks.add(3);
        yr4BookStocks.add(3);

        // MULTI-SELECT
        select.add(false);
        select.add(false);
        select.add(false);
        select.add(false);
        select.add(false);

        // TITLE BIN
        ArrayList<String> yr1TitleBin = new ArrayList<>();
        ArrayList<String> yr2TitleBin = new ArrayList<>();
        ArrayList<String> yr3TitleBin = new ArrayList<>();
        ArrayList<String> yr4TitleBin = new ArrayList<>();

        // STOCKS BIN
        ArrayList<Integer> yr1StocksBin = new ArrayList<>();
        ArrayList<Integer> yr2StocksBin = new ArrayList<>();
        ArrayList<Integer> yr3StocksBin = new ArrayList<>();
        ArrayList<Integer> yr4StocksBin = new ArrayList<>();

        title.add(yr1Titles);
        title.add(yr2Titles);
        title.add(yr3Titles);
        title.add(yr4Titles);

        stocks.add(yr1BookStocks);
        stocks.add(yr2BookStocks);
        stocks.add(yr3BookStocks);
        stocks.add(yr4BookStocks);

        titleBin.add(yr1TitleBin);
        titleBin.add(yr2TitleBin);
        titleBin.add(yr3TitleBin);
        titleBin.add(yr4TitleBin);

        stocksBin.add(yr1StocksBin);
        stocksBin.add(yr2StocksBin);
        stocksBin.add(yr3StocksBin);
        stocksBin.add(yr4StocksBin);

        startUp();
    }

    public static void startUp()
    {
        String action = "x";
        while (true)
        {
            editSaved = false;
            if (!action.equals("a") && !action.equals("b"))
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |   ___  __                            _________         _   |
                        |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                        |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                        |  |___|__ (___ \\|_|_| (___ \\|_| \\//____/|__|  (___ \\/\\_| |  |
                        |         \\/   \\/     \\/   \\/                      \\/\\____|  |
                        '------------------------------------------------------------'
                         |                                                          |
                         |                                                          |
                         |                                                          |
                         |                       [A] LOG-IN                         |
                         |                                                          |
                         |                       [B] SIGN-UP                        |
                         |                                                          |
                         |                                                          |
                         |                                                          |
                        .------------------------------------------------------------.
                        |                 Select action by letters.                  |
                        '------------------------------------------------------------'""");
                if (!action.equals("a") && !action.equals("b") && !action.equals("c") && !action.equals("x")
                        && !action.equals("o"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
            }
            switch (action)
            {
                case "a": {
                    action = logIn();
                    break;
                }
                case "b": {
                    action = signUp();
                    break;
                }
            }
        }
    }

    public static String signUp()
    {
        String action = "x";
        String name = " ";
        String password = " ";
        boolean nameTooShort = false;
        boolean passTooShort = false;
        boolean accDupe = false;

        do
        {
            name = " ";
            password = " ";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |       _________ __                     ___ ___             |
                        |      /   _____/|__| ____   ___        |   |   \\_____       |
                        |      \\_____  \\ |  |/ __ \\ /   \\  _____|   |   /\\___ \\      |
                        |      /_____  / |__|\\__  /|__|  //____/|______/ |  __/      |
                        |            \\/      /___/     \\/                |_|         |
                        '------------------------------------------------------------'""");

                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 43 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (nameTooShort || name.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 43 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (passTooShort || password.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                 Enter Username and Password                |
                        '------------------------------------------------------------'""");
                if (name.equals("") || password.equals(""))
                {
                    System.out.println(
                            " |               Fill in username and password.             |\n" +
                                    " '----------------------------------------------------------'");
                } else
                {
                    if (accDupe)
                    {
                        System.out.println(
                                " |                  Username already taken.                 |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    if (nameTooShort)
                    {
                        System.out.println(
                                " |       Username must be at least 6 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    if (passTooShort)
                    {
                        System.out.println(
                                " |       Password must be at least 8 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                }
                System.out.print("\nUsername: ");
                name = input.nextLine().trim();
            } while (name.equals(""));

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |       _________ __                     ___ ___             |
                        |      /   _____/|__| ____   ___        |   |   \\_____       |
                        |      \\_____  \\ |  |/ __ \\ /   \\  _____|   |   /\\___ \\      |
                        |      /_____  / |__|\\__  /|__|  //____/|______/ |  __/      |
                        |            \\/      /___/     \\/                |_|         |
                        '------------------------------------------------------------'""");

                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 43 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (nameTooShort || name.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 43 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (passTooShort || password.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                 Enter Username and Password                |
                        '------------------------------------------------------------'""");
                if (name.equals("") || password.equals(""))
                {
                    System.out.println(
                            " |               Fill in username and password.             |\n" +
                                    " '----------------------------------------------------------'");
                } else
                {
                    if (accDupe)
                    {
                        System.out.println(
                                " |                  Username already taken.                 |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    if (nameTooShort)
                    {
                        System.out.println(
                                " |       Username must be at least 6 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    if (passTooShort)
                    {
                        System.out.println(
                                " |       Password must be at least 8 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                }
                System.out.print("\nPassword: ");
                password = input.nextLine().trim();
            } while (password.equals(""));

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |       _________ __                     ___ ___             |
                        |      /   _____/|__| ____   ___        |   |   \\_____       |
                        |      \\_____  \\ |  |/ __ \\ /   \\  _____|   |   /\\___ \\      |
                        |      /_____  / |__|\\__  /|__|  //____/|______/ |  __/      |
                        |            \\/      /___/     \\/                |_|         |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 45 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 45 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        | [X] BACK                                       SIGN-UP [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine();
            } while (!action.equals("o") && !action.equals("x"));

            if (action.equals("o"))
            {
                if (adminName.size() > 0)
                {
                    for (int i = 0; i < adminName.size(); i++)
                    {
                        if (name.equals(adminName.get(i)))
                        {
                            accDupe = true;
                            break;
                        } else
                        {
                            accDupe = false;
                        }
                    }
                }
                if (!accDupe)
                {
                    if (name.length() < 6)
                    {
                        nameTooShort = true;
                    } else
                    {
                        nameTooShort = false;
                    }
                    if (password.length() < 8)
                    {
                        passTooShort = true;
                    } else
                    {
                        passTooShort = false;
                    }
                }
                if (adminName.size() > 0)
                {
                    for (int i = 0; i < adminName.size(); i++)
                    {
                        if (name.equals(adminName.get(i)))
                        {
                            accDupe = true;
                            break;
                        }
                    }
                }
                if (!accDupe)
                {
                    if (name.length() < 6)
                    {
                        nameTooShort = true;
                    } else
                    {
                        nameTooShort = false;
                    }
                    if (password.length() < 8)
                    {
                        passTooShort = true;
                    } else
                    {
                        passTooShort = false;
                    }
                }
            }
        } while (action.equals("x") || nameTooShort || passTooShort || accDupe);

        adminName.add(name);
        adminPass.add(password);

        action = "x";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    .------------------------------------------------------------.
                    |       _________ __                     ___ ___             |
                    |      /   _____/|__| ____   ___        |   |   \\_____       |
                    |      \\_____  \\ |  |/ __ \\ /   \\  _____|   |   /\\___ \\      |
                    |      /_____  / |__|\\__  /|__|  //____/|______/ |  __/      |
                    |            \\/      /___/     \\/                |_|         |
                    '------------------------------------------------------------'
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |          Sign-up complete. You can now log-in.           |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                    .------------------------------------------------------------.
                    | [X] BACK                                        LOG-IN [O] |
                    '------------------------------------------------------------'""");
            if (!action.equals("o") && !action.equals("x"))
            {
                System.out.println(
                        " |             Invalid action. Please try again.            |\n" +
                                " '----------------------------------------------------------'");
            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("o") && !action.equals("x"));
        if (action.equals("o"))
        {
            return action = "a";
        } else
        {
            return action;
        }
    }

    public static String logIn()
    {
        String action = "x";
        String name = " ";
        String password = " ";
        boolean accFound = true;
        boolean passFound = true;

        int logFail = 0;
        int attempts = 3;

        if (adminName.size() < 1)
        {
            while (passFound)
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |   ___  __                            _________         _   |
                        |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                        |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                        |  |___|__ (___ \\|_|_| (___ \\|_| \\//____/|__|  (___ \\/\\_| |  |
                        |         \\/   \\/     \\/   \\/                      \\/\\____|  |
                        '------------------------------------------------------------'
                         |                                                          |
                         |                                                          |
                         |                                                          |
                         |         Log-in feature is currently unavailable.         |
                         |                   Please sign-up first.                  |
                         |                                                          |
                         |                                                          |
                         |                                                          |
                         |                                                          |
                        .------------------------------------------------------------.
                        | [X] BACK                                       SIGN-UP [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("o"))
                {
                    return action = "b";
                } else if (action.equals("x"))
                {
                    return action;
                }
            }
        }
        do
        {
            name = " ";
            password = " ";

            if (editSaved)
            {
                while (true)
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println("""
                            .------------------------------------------------------------.
                            |              ___                     ___                   |
                            |             |   |   ___  ____       |   | ___              |
                            |             |   |__/ _ \\/ __ \\ _____|   |/   \\             |
                            |             |_____ \\___/\\__  //____/|___|__|  /            |
                            |                   \\/    /___/               \\/             |
                            '------------------------------------------------------------'
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |   Your account details have been updated successfully.   |
                             |  For security purposes, please log in again to continue. |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                            .------------------------------------------------------------.
                            |                                               CONTINUE [O] |
                            '------------------------------------------------------------'""");
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("o"))
                    {
                        break;
                    }
                }
            }

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |              ___                     ___                   |
                        |             |   |   ___  ____       |   | ___              |
                        |             |   |__/ _ \\/ __ \\ _____|   |/   \\             |
                        |             |_____ \\___/\\__  //____/|___|__|  /            |
                        |                   \\/    /___/               \\/             |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 43 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (!accFound || name.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 43 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (!passFound || password.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                 Enter Username and Password                |
                        '------------------------------------------------------------'""");
                if (name.equals("") || password.equals(""))
                {
                    System.out.println(
                            " |               Fill in username and password.             |\n" +
                                    " '----------------------------------------------------------'");
                } else if (!accFound || !passFound && logFail > 0)
                {
                    System.out.println(
                            " |  Incorrect username or password. " + (attempts - logFail)
                                    + " attempt(s) remaining. |\n"
                                    +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\nUsername: ");
                name = input.nextLine().trim();
            } while (name.equals(""));

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |              ___                     ___                   |
                        |             |   |   ___  ____       |   | ___              |
                        |             |   |__/ _ \\/ __ \\ _____|   |/   \\             |
                        |             |_____ \\___/\\__  //____/|___|__|  /            |
                        |                   \\/    /___/               \\/             |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 43 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (!accFound || name.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 43 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (!passFound || password.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                 Enter Username and Password                |
                        '------------------------------------------------------------'""");
                if (name.equals("") || password.equals(""))
                {
                    System.out.println(
                            " |               Fill in username and password.             |\n" +
                                    " '----------------------------------------------------------'");
                } else if (!accFound || !passFound && logFail > 0)
                {
                    System.out.println(
                            " |  Incorrect username or password. " + (attempts - logFail)
                                    + " attempt(s) remaining. |\n"
                                    +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\nPassword: ");
                password = input.nextLine().trim();
            } while (password.equals(""));

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        |              ___                     ___                   |
                        |             |   |   ___  ____       |   | ___              |
                        |             |   |__/ _ \\/ __ \\ _____|   |/   \\             |
                        |             |_____ \\___/\\__  //____/|___|__|  /            |
                        |                   \\/    /___/               \\/             |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 45 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 45 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        | [X] BACK                                        LOG-IN [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine();

            } while (!action.equals("o") && !action.equals("x"));

            if (action.equals("o"))
            {
                for (int a = 0; a < adminName.size(); a++)
                {
                    if (name.equals(adminName.get(a)))
                    {
                        accFound = true;
                        loggedAcc = name;
                        loggedAccNum = a;
                        break;
                    } else
                    {
                        accFound = false;
                    }
                }
                for (int b = 0; b < adminName.size(); b++)
                {
                    if (password.equals(adminPass.get(b)))
                    {
                        passFound = true;
                        loggedPass = password;
                        break;
                    } else
                    {
                        passFound = false;
                    }
                }
                if (!accFound || !passFound)
                {

                    logFail++;
                }
                if (logFail == 3)
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println("Too many failed login attempts. The system will now close.");
                    System.exit(0);
                }
            }
        } while (action.equals("x") || !passFound || !accFound);
        if (!editSaved)
        {
            dashBoard();
        }
        return "x";
    }

    public static void aboutDevelopers()
    {

    }

    public static void dashBoard()
    {
        String action = "a";
        String tab = "a";

        while (true)
        {
            totalBookCount = title.get(0).size() + title.get(1).size() + title.get(2).size() + title.get(3).size();

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                dashBoardDisplay();
                switch (tab)
                {
                    case "a": {
                        System.out.print("'----------------..=============.-------------.--------------'\n" +
                                " | W E L C O M E ||  BUY BOOKS  |  INVENTORY     CUSTOMERS  |\n" +
                                " | ");
                        break;
                    }
                    case "b": {
                        System.out.print("'----------------..-------------.=============.--------------'\n" +
                                " | W E L C O M E ||  BUY BOOKS  |  INVENTORY  |  CUSTOMERS  |\n" +
                                " | ");
                        break;
                    }
                    case "c": {
                        System.out.print("'----------------..-------------.-------------.=============.'\n" +
                                " | W E L C O M E ||  BUY BOOKS     INVENTORY  |  CUSTOMERS  |\n" +
                                " | ");
                        break;
                    }
                }
                dashBoardTabs(tab);
                System.out.println("""
                        .------------------------------------------------------------.
                        | [V] SETTINGS                                    SELECT [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("a") && !action.equals("b") && !action.equals("c") && !action.equals("v")
                        && !action.equals("o"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("a") || action.equals("b") || action.equals("c"))
                {
                    tab = action;
                }
                if (action.equals("v"))
                {
                    tab = action;
                    action = "x";

                    while (true)
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        dashBoardDisplay();
                        System.out.print("'----------------..------------------------------------------'\n" +
                                " | W E L C O M E ||               [ SETTINGS ]              |\n" +
                                " | ");
                        dashBoardTabs(tab);
                        System.out.println("""
                                .------------------------------------------------------------.
                                | [X] BACK                                                   |
                                '------------------------------------------------------------'""");
                        if (!action.equals("x") && !action.equals("a") && !action.equals("b") && !action.equals("c"))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\naction: ");
                        action = input.nextLine().toLowerCase();
                        if (action.equals("x"))
                        {
                            tab = "a";
                            action = "v";
                            break;
                        } else if (action.equals("a") || action.equals("b") || action.equals("c"))
                        {
                            tab = action;
                        }
                        switch (action)
                        {
                            case "a": {
                                tab = editAcc();
                                break;
                            }
                            case "b": {
                                aboutDevelopers();
                                break;
                            }
                            case "c": {
                                action = "x";

                                do
                                {
                                    for (int i = 0; i < 100; i++)
                                    {
                                        System.out.println();
                                    }
                                    System.out.println("""
                                            .------------------------------------------------------------.
                                            |   ___  __                            _________         _   |
                                            |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                                            |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                                            |  |___|__ (___ \\|_|_| (___ \\|_| \\//____/|__|  (___ \\/\\_| |  |
                                            |         \\/   \\/     \\/   \\/                      \\/\\____|  |
                                            '------------------------------------------------------------'
                                             |                                                          |
                                             |                                                          |
                                             |                                                          |
                                             |                                                          |
                                             |             Are you sure you want to log out?            |
                                             |                                                          |
                                             |                                                          |
                                             |                                                          |
                                             |                                                          |
                                            .------------------------------------------------------------.
                                            | [X] BACK                                           YES [O] |
                                            '------------------------------------------------------------'""");
                                    if (!action.equals("o") && !action.equals("x"))
                                    {
                                        System.out.println(
                                                " |             Invalid action. Please try again.            |\n" +
                                                        " '----------------------------------------------------------'");
                                    }
                                    System.out.print("\naction: ");
                                    action = input.nextLine();
                                    if (action.equals("o"))
                                    {
                                        return;
                                    }
                                } while (!action.equals("o") && !action.equals("x"));

                                action = "a";
                                tab = "v";
                                break;
                            }
                        }
                    } // while true
                }
            } while (!action.equals("o"));

            switch (tab)
            {
                case "a": {
                    buyBooks();
                    break;
                }
                case "b": {
                    inventory();
                    break;
                }
                case "c": {
                    customerCenter();
                    break;
                }
            }
        }
    }

    public static String editAcc()
    {
        String action = "x";
        String name = "";
        String password = "";
        boolean nameTooShort = false;
        boolean passTooShort = false;
        boolean accDupe = false;
        boolean passFound = false;
        String passwordCheck = "";

        int logFail = 0;
        int attempts = 3;

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    .------------------------------------------------------------.
                    |   ___  __                            _________         _   |
                    |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                    |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                    |  |___|__ (___ \\|_|_| (___ \\|_| \\//____/|__|  (___ \\/\\_| |  |
                    |         \\/   \\/     \\/   \\/                      \\/\\____|  |
                    '------------------------------------------------------------'
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |  Are you sure you want to make changes to your account?  |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                    .------------------------------------------------------------.
                    | [X] BACK                                           YES [O] |
                    '------------------------------------------------------------'""");
            if (!action.equals("o") && !action.equals("x"))
            {
                System.out.println(
                        " |             Invalid action. Please try again.            |\n" +
                                " '----------------------------------------------------------'");

            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
            if (action.equals("x"))
            {
                return action = "v";
            }
        } while (!action.equals("o") && !action.equals("x"));
        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.print(
                    """
                            .------------------------------------------------------------.
                            |  _______    __ _  _      __                           _    |
                            |  \\_ ___/ __| /|_|/ |_   /  \\   ___  ___  ___  _ _  __/ |_  |
                            |   | _)_ / _ | | \\   _\\ / /\\ \\_/ __\\/ __\\/ _ \\| | \\/  \\  _\\ |
                            |  /___  /\\__ | |_||_|  \\__/\\  /\\__  >__  >___/|__/|_| /_|   |
                            |      \\/    \\/              \\/    \\/   \\/           \\/      |
                            '------------------------------------------------------------'
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |            Are you sure you want to log out?             |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                            .------------------------------------------------------------.
                            | [X] BACK                                           YES [O] |
                            '------------------------------------------------------------'""");
            System.out.println("""
                    .------------------------------------------------------------.
                    | S E T T I N G S - EDIT ACC                                 |
                    |------------------------------------------------------------|
                    |      Verify your identity before editing your account.     |
                    |                 Please enter your password.                |
                    '------------------------------------------------------------'""");
            if (!passFound && logFail > 0)
            {
                System.out.println(
                        " |  Incorrect username or password. " + (attempts - logFail)
                                + " attempt(s) remaining. |\n"
                                +
                                " '----------------------------------------------------------'");
            }
            System.out.print("\nPassword: ");
            passwordCheck = input.nextLine();
            if (!passwordCheck.equals(loggedPass))
            {
                logFail++;
            } else
            {
                passFound = true;
            }
            if (logFail == 3)
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("Too many failed attempts. The system will now close.");
                System.exit(0);
            }
        } while (!passwordCheck.equals(loggedPass));

        action = "x";

        do
        {

            name = " ";
            password = " ";
            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | S E T T I N G S - EDIT ACC                                 |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 43 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (nameTooShort || name.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 43 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                if (passTooShort || password.equals(""))
                {
                    System.out.print("*");
                } else
                {
                    System.out.print(" ");
                }
                System.out.println(" |     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                 Enter Username and Password                |
                        '------------------------------------------------------------'""");
                if (name.equals("") || password.equals(""))
                {
                    System.out.println(
                            " |               Fill in username and password.             |\n" +
                                    " '----------------------------------------------------------'");
                } else
                {
                    if (nameTooShort)
                    {
                        System.out.println(
                                " |       Username must be at least 6 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    if (passTooShort)
                    {
                        System.out.println(
                                " |       Password must be at least 8 characters long.       |\n" +
                                        " '----------------------------------------------------------'");
                    }
                }
                System.out.println();
                System.out.print("Username: ");
                name = input.nextLine().trim();
                System.out.print("Password: ");
                password = input.nextLine().trim();
            } while (name.equals("") || password.equals(""));

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | S E T T I N G S - EDIT ACC                        BACK [X] |
                        '------------------------------------------------------------'""");
                System.out.print(
                        " |                                                          |\n" +
                                " |     .-[Username]-----------------------------------.     |\n" +
                                " |     | " + name);
                int gap = 45 - name.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.print(" |     '----------------------------------------------'     |\n" +
                        " |     .-[Password]-----------------------------------.     |\n" +
                        " |     | " + password);
                gap = 45 - password.length();
                for (int i = 0; i < gap; i++)
                {
                    System.out.print(" ");
                }
                System.out.println("|     |");
                System.out.println(" |     '----------------------------------------------'     |\n" +
                        " |                                                          |");
                System.out.println("""
                        .------------------------------------------------------------.
                        |                                                PROCEED [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine();
            } while (!action.equals("o") && !action.equals("x"));

            if (action.equals("o"))
            {
                if (adminName.size() > 0)
                {
                    for (int i = 0; i < adminName.size(); i++)
                    {
                        if (name.equals(adminName.get(i)))
                        {
                            accDupe = true;
                            break;
                        } else
                        {
                            accDupe = false;
                        }
                    }
                }
                if (!accDupe)
                {
                    if (name.length() < 6)
                    {
                        nameTooShort = true;
                    } else
                    {
                        nameTooShort = false;
                    }
                    if (password.length() < 8)
                    {
                        passTooShort = true;
                    } else
                    {
                        passTooShort = false;
                    }
                }
                if (adminName.size() > 0)
                {
                    for (int i = 0; i < adminName.size(); i++)
                    {
                        if (name.equals(adminName.get(i)))
                        {
                            accDupe = true;
                            break;
                        } else
                        {
                            accDupe = false;
                        }
                    }
                }
                if (!accDupe)
                {
                    if (name.length() < 6)
                    {
                        nameTooShort = true;
                    } else
                    {
                        nameTooShort = false;
                    }
                    if (password.length() < 8)
                    {
                        passTooShort = true;
                    } else
                    {
                        passTooShort = false;
                    }
                }
            }
            if (accDupe)
            {
                action = "x";

                do
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println("""
                            .------------------------------------------------------------.
                            |   ___  __                            _________         _   |
                            |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                            |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                            |  |___|__ (___ \\|_|_| (___ \\|_| \\//____/|__|  (___ \\/\\_| |  |
                            |         \\/   \\/     \\/   \\/                      \\/\\____|  |
                            '------------------------------------------------------------'
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                   Account already exist                  |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                             |                                                          |
                            .------------------------------------------------------------.
                            | [X] BACK                                     TRY AGAIN [O] |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x") && !action.equals("o"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine();
                    if (action.equals("x"))
                    {
                        return action = "v";
                    }
                } while (!action.equals("x") && !action.equals("o"));
            }
        } while (action.equals("x") || nameTooShort || passTooShort || accDupe);

        action = "o";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    .------------------------------------------------------------.
                    | S E T T I N G S - EDIT ACC                                 |
                    |------------------------------------------------------------|
                    |   Do you wish to continue saving changes to your account?  |
                    '------------------------------------------------------------'""");
            if (!action.equals("o"))
            {
                System.out.println(
                        " |             Invalid action. Please try again.            |\n" +
                                " '----------------------------------------------------------'");
            }
            System.out.print("\n[O] YES    [X] CANCEL: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("o") && !action.equals("x"));

        if (action.equals("x"))
        {
            return action = "v";
        }

        adminName.set(loggedAccNum, name);
        adminPass.set(loggedAccNum, password);
        editSaved = true;
        logIn();
        return action = "v";
    }

    public static void buyBooks()
    {
        String action = "x";
        int num = 1;
        int num2 = 1;
        String name = "";
        String bday = "";

        boolean selected = false;
        boolean pwdCheck = false;
        buying = true;

        do
        {
            num = 1;

            do
            {
                do
                {
                    do
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        System.out.println("""
                                .------------------------------------------------------------.
                                | B U Y   B O O K S                                 BACK [X] |
                                |------------------------------------------------------------'
                                |               [ SELECT CUSTOMER ]             |           |
                                '-----------------------------------------------'           |""");
                        generateCustomerList();
                        System.out.println("""
                                .------------------------------------------------------------.
                                |                                           NEW CUSTOMER [O] |
                                '------------------------------------------------------------'""");
                        if ((!action.equals("x") && !action.equals("o") && !action.equals("1") && !action.equals("2")
                                && !action.equals("3")
                                && !action.equals("4") && !action.equals("5"))
                                || (num < 1 || (num > customerName.size() && customerName.size() != 0)))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\naction: ");
                        action = input.nextLine().toLowerCase();
                        if (action.equals("x"))
                        {
                            buying = false;
                            return;
                        } else if (action.equals("o"))
                        {
                            buyAddCustomer = true;
                            addCustomer();
                            if (buyAddCustomer)
                            {
                                num = customerName.size();
                            }
                        } else if (action.equals("1") || action.equals("2") || action.equals("3")
                                || action.equals("4") || action.equals("5"))
                        {
                            num = Integer.parseInt(action);
                        }
                    } while (!action.equals("o") && !action.equals("x") && !action.equals("1") && !action.equals("2")
                            && !action.equals("3")
                            && !action.equals("4") && !action.equals("5"));
                } while (!buyAddCustomerSaved);

                if (!buyAddCustomer)
                {
                    pwdCheck = pwd.get(num - 1);
                } else
                {
                    pwdCheck = pwd.get(customerName.size() - 1);
                }

                if (!buyAddCustomer)
                {
                    do
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        System.out.println("""
                                .------------------------------------------------------------.
                                | B U Y   B O O K S                                 BACK [X] |
                                |------------------------------------------------------------|
                                |                     You have selected...                   |
                                '------------------------------------------------------------'""");
                        System.out.println(
                                " |                                                          |\n" +
                                        " |                                                          |\n" +
                                        " |                                                          |\n" +
                                        " |                                                          |\n" +
                                        " |    [NAME]                             [DATE OF BIRTH]    |\n" +
                                        " |                                                          |");
                        System.out.print(" |     ");
                        System.out.print(customerName.get(num - 1));
                        int gap = 37 - customerName.get(num - 1).length();
                        for (int b = 0; b < gap; b++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(customerBday.get(num - 1));
                        System.out.println("      |");
                        System.out.println(
                                " |                                                          |\n" +
                                        " |                                                          |\n" +
                                        " |                                                          |\n" +
                                        " |                                                          |");
                        System.out.println("""
                                .------------------------------------------------------------.
                                |      Do you wish to continue buying for this customer?     |
                                '------------------------------------------------------------'""");
                        if (!action.equals("x") && !action.equals("o") && !action.equals("1") && !action.equals("2")
                                && !action.equals("3")
                                && !action.equals("4") && !action.equals("5"))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\n[O] YES    [X] NO:  ");
                        action = input.nextLine().toLowerCase();
                    } while (!action.equals("x") && !action.equals("o"));
                }
            } while (action.equals("x"));

            name = customerName.get(num - 1);
            bday = customerBday.get(num - 1);

            num = 1;

            buyAddCustomer = false;
            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | B U Y   B O O K S                                 BACK [X] |""");
                switch (num)
                {
                    case 1: {
                        System.out.println("""
                                |===========.-----------.-----------.-----------.------------'
                                |   YEAR 1  |   YEAR 2      YEAR 3      YEAR 4  |           |
                                '====[1]===='----[2]----'----[3]----'----[4]----'           |""");
                        break;
                    }
                    case 2: {
                        System.out.println("""
                                |-----------.===========.-----------.-----------.------------'
                                |   YEAR 1  |   YEAR 2  |   YEAR 3      YEAR 4  |           |
                                '----[1]----'====[2]===='----[3]----'----[4]----'           |""");
                        break;
                    }
                    case 3: {
                        System.out.println("""
                                |-----------.-----------.===========.-----------.------------'
                                |   YEAR 1      YEAR 2  |   YEAR 3  |   YEAR 4  |           |
                                '----[1]----'----[2]----'====[3]===='----[4]----'           |""");
                        break;
                    }
                    case 4: {
                        System.out.println("""
                                |-----------.-----------.-----------.===========.------------'
                                |   YEAR 1      YEAR 2      YEAR 3  |   YEAR 4  |           |
                                '----[1]----'----[2]----'----[3]----'====[4]===='           |""");
                        break;
                    }
                }
                generateBookList(num);
                System.out.println("""
                        .------------------------------------------------------------.
                        | Select year level                               SELECT [O] |
                        '------------------------------------------------------------'""");
                if ((!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                        && !action.equals("4")) || (num < 1 || num > 4))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("x"))
                {
                    return;
                } else if (action.equals("1") || action.equals("2") || action.equals("3")
                        || action.equals("4"))
                {
                    num = Integer.parseInt(action);
                }
            } while (!action.equals("x") && !action.equals("o"));

            action = "x";
            int count = 0;
            int amount = 0;

            do
            {
                selecting = true;

                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println(".------------------------------------------------------------.\n" +
                        "| B U Y   B O O K S - [ YEAR " + num + " ]                    BACK [X] |");
                System.out.println("""
                        |------------------------------------------------------------'
                        |                [ SELECT BOOKS ]               |           |
                        '-----------------------------------------------'           |""");
                generateBookList(num);
                System.out.println("""
                        .------------------------------------------------------------.
                        | Select book title by numbers.                  PROCEED [O] |
                        '------------------------------------------------------------'""");
                if (!action.equals("x") && !action.equals("o") && !action.equals("1") && !action.equals("2")
                        && !action.equals("3")
                        && !action.equals("4") && !action.equals("5") || num2 > title.get(num - 1).size())
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("x"))
                {
                    for (int i = 0; i < select.size(); i++)
                    {
                        for (int j = 0; j < select.size(); j++)
                        {
                            select.set(j, false);
                        }
                    }
                    break;
                }
                if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                        || action.equals("5"))
                {
                    num2 = Integer.parseInt(action);
                    if (num2 > 0 && num2 <= title.get(num - 1).size())
                    {
                        select.set(num2 - 1, !select.get(num2 - 1));
                    }
                    // book buy amount
                    action = "x";
                    buyselecting = true;

                    do
                    {
                        count = 0;
                        for (int i = 0; i < select.size(); i++)
                        {
                            if (select.get(i))
                            {
                                count++;
                            }
                        }
                        for (int index = 0; index < 100; index++)
                        {
                            System.out.println();
                        }
                        System.out.println("""
                                .------------------------------------------------------------.
                                | B U Y   B O O K S                                 BACK [X] |
                                |------------------------------------------------------------|
                                |                     You have selected...                   |
                                '------------------------------------------------------------'""");
                        displaySelected(num, count);
                        System.out.println("""
                                .------------------------------------------------------------.
                                |                       Enter quantity                       |
                                '------------------------------------------------------------'""");
                        if (amount > stocks.get(num - 1).get(num2 - 1))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\naction: ");
                        action = input.nextLine().toLowerCase();
                        if (action.equals("1") || action.equals("2") || action.equals("3"))
                        {
                            amount = Integer.parseInt(action);
                            selectedQuantity[num2 - 1] = amount;
                        }
                    } while (amount > stocks.get(num - 1).get(num2 - 1));
                }
                for (int i = 0; i < title.get(num - 1).size(); i++)
                {
                    if (select.get(i))
                    {
                        selected = true;
                        break;
                    } else
                    {
                        selected = false;
                    }
                }
                if (action.equals("o"))
                {
                    selecting = false;
                    if (buyAddCustomer)
                    {
                        name = customerName.get(customerName.size() - 1);
                        bday = customerBday.get(customerName.size() - 1);
                    }
                    action = checkOut(num, count, name, bday, pwdCheck);
                }
            } while (!action.equals("o")/* || (num2 < 1 && num2 > titleBin.get(num - 1).size()) || !selected */);
        } while (action.equals("x"));
    }

    public static String checkOut(int yearCheckout, int count, String name, String bday, boolean pwdCheck)
    {
        String action = "x";
        String paymentMethod = "";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println(".------------------------------------------------------------.\n" +
                    "| B U Y   B O O K S - [ YEAR " + yearCheckout + " ]                    BACK [X] |");
            System.out.println("""
                    |------------------------------------------------------------'
                    |                  [ CHECKOUT ]                 |           |
                    '-----------------------------------------------'           |""");

            displaySelected(yearCheckout, count);

            System.out.println("""
                    .------------------------------------------------------------.
                    | Do you wish to continue buying?               CHECKOUT [O] |
                    '------------------------------------------------------------'""");
            if (!action.equals("o") && !action.equals("x"))
            {
                System.out.println(
                        " |             Invalid action. Please try again.            |\n" +
                                " '----------------------------------------------------------'");
            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("o") && !action.equals("x"));

        buyselecting = false;
        action = "a";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println(".------------------------------------------------------------.\n" +
                    "| B U Y   B O O K S - [ YEAR " + yearCheckout + " ]                    BACK [X] |");
            System.out.println("""
                    |------------------------------------------------------------'
                    |                  [ CHECKOUT ]                 |           |
                    '-----------------------------------------------'           |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                        [A] CASH                          |
                     |                                                          |
                     |                     [B] INSTALLMENT                      |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                     |                                                          |
                    .------------------------------------------------------------.
                    |                   Select payment method.                   |
                    '------------------------------------------------------------'""");
            if (!action.equals("a") && !action.equals("b"))
            {
                System.out.println(
                        " |             Invalid action. Please try again.            |\n" +
                                " '----------------------------------------------------------'");
            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
            if (action.equals("a"))
            {
                paymentMethod = "Cash";
            } else if (action.equals("b"))
            {
                paymentMethod = "Installment";
            }
        } while (!action.equals("a") && !action.equals("b"));

        if (action.equals("a") || action.equals("b"))
        {
            reciept(name, bday, yearCheckout, pwdCheck, paymentMethod);
        }
        buying = false;
        return action;
    }

    public static void reciept(String name, String bday, int yearDisplay, boolean pwdCheck, String paymentMethod)
    {
        String action = "x";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            String receipt = "|\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|\n" +
                    "|               K A M A R - T A J              |\n" +
                    "|                    RECEIPT                   |\n" +
                    "| -------------------------------------------- |\n" +
                    "| Customer Name         : ";
            for (int i = 0; i < 20; i++)
            {
                if (i >= name.length())
                {
                    receipt += " ";
                } else
                {
                    receipt += name.charAt(i);
                }
            }
            receipt += " |\n" +
                    "| Date of Birth         : ";
            for (int i = 0; i < 20; i++)
            {
                if (i >= bday.length())
                {
                    receipt += " ";
                } else
                {
                    receipt += bday.charAt(i);
                }
            }
            receipt += " |\n" +
                    "| -------------------------------------------- |\n" +
                    "| BOOK TITLE(S)           QTY    SRP    PRICE  |\n";
            for (int a = 0; a < title.get(yearDisplay - 1).size(); a++)
            {
                if (select.get(a))
                {
                    receipt += "| ";
                    for (int i = 0; i < 21; i++)
                    {
                        if (i >= title.get(yearDisplay - 1).get(a).length())
                        {
                            receipt += " ";
                        } else
                        {
                            receipt += title.get(yearDisplay - 1).get(a).charAt(i);
                        }
                    }
                    receipt += "    " + selectedQuantity[a] + "    P 220   P " + (220 * selectedQuantity[a]) + "  |\n";
                }
            }
            int subtotal = 0;
            for (int i = 0; i < title.get(yearDisplay - 1).size(); i++)
            {
                if (select.get(i))
                {
                    subtotal += 220 * selectedQuantity[i];
                }
            }
            receipt += "| -------------------------------------------- |\n" +
                    "| Subtotal              : P ";
            if (subtotal < 1000)
            {
                receipt += twodec.format(subtotal) + "              |\n";
            } else
            {
                receipt += twodec.format(subtotal) + "            |\n";

            }
            int totalBooksBought = 0;
            for (int j = 0; j < title.get(yearDisplay - 1).size(); j++)
            {
                if (select.get(j))
                {
                    totalBooksBought += selectedQuantity[j];
                }
            }
            String type = "N/A";
            double discount = 0;
            if (yearForReceipt < 1965)
            {
                type = "Senior Citizen - 20% |\n";
                discount = 0.20;
            } else if (pwdCheck)
            {
                type = "PWD Discount   - 20% |\n";
                discount = 0.20;
            } else if (totalBooksBought > 2)
            {
                type = "Bulk Discount  - 5%  |\n";
                discount = 0.05;
            }
            if (totalBooksBought > 2 && (yearForReceipt < 1965 || pwdCheck))
            {
                type += "|                         Bulk Discount  - 5%  |\n";
                discount = 0.25;
            }
            receipt += "|                                              |\n" +
                    "| Discount Applied                             |\n" +
                    "|    - Type             : " + type;
            double amount = subtotal * discount;
            double vat = 0.12;
            double total = (subtotal - amount) + ((subtotal - amount) * vat);
            receipt += "|    - Amount           : P " + twodec.format(amount);
            if (amount < 100)
            {
                receipt += "              |\n";
            }
            if (amount < 1000)
            {
                receipt += "             |\n";
            } else
            {
                receipt += "            |\n";
            }

            receipt += "|                                              |\n" +
                    "| VAT (12%)             : " + vat + "                 |\n" +
                    "| -------------------------------------------- |\n" +
                    "| TOTAL                 : P ";
            if (total < 1000)
            {
                receipt += twodec.format(total) + "             |\n";
            } else
            {
                receipt += twodec.format(total) + "            |\n";
            }
            receipt += "|                                              |\n" +
                    "| Payment method        : " + paymentMethod;
            if (paymentMethod.equals("Cash"))
            {
                receipt += "                 |\n";
            } else
            {
                receipt += "          |\n";
                double firstInstallment = total * 0.60;
                double secondInstallment = total * 0.40;

                receipt += "|    - First Payment    : P " + twodec.format(firstInstallment);
                if (firstInstallment < 1000)
                {
                    receipt += "             |\n";
                } else
                {
                    receipt += "            |\n";
                }
                receipt += "|    - Second Payment   : P " + twodec.format(secondInstallment);
                if (secondInstallment < 1000)
                {
                    receipt += "             |\n";
                } else
                {
                    receipt += "            |\n";
                }
            }
            receipt += "|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\|\n";

            System.out.println(receipt);
            System.out.print("\n[X] EXIT: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("x"));
        for (int i = 0; i < select.size(); i++)
        {
            for (int j = 0; j < select.size(); j++)
            {
                select.set(j, false);
            }
        }
    }

    public static void inventory()
    {
        String action = "x";
        int num = 1;
        int num2 = 1;

        do
        {
            num = 1;
            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | I N V E N T O R Y                                 BACK [X] |""");
                switch (num)
                {
                    case 1: {
                        System.out.println("""
                                |===========.-----------.-----------.-----------.------------'
                                |   YEAR 1  |   YEAR 2      YEAR 3      YEAR 4  |           |
                                '====[1]===='----[2]----'----[3]----'----[4]----'           |""");
                        break;
                    }
                    case 2: {
                        System.out.println("""
                                |-----------.===========.-----------.-----------.------------'
                                |   YEAR 1  |   YEAR 2  |   YEAR 3      YEAR 4  |           |
                                '----[1]----'====[2]===='----[3]----'----[4]----'           |""");
                        break;
                    }
                    case 3: {
                        System.out.println("""
                                |-----------.-----------.===========.-----------.------------'
                                |   YEAR 1      YEAR 2  |   YEAR 3  |   YEAR 4  |           |
                                '----[1]----'----[2]----'====[3]===='----[4]----'           |""");
                        break;
                    }
                    case 4: {
                        System.out.println("""
                                |-----------.-----------.-----------.===========.------------'
                                |   YEAR 1      YEAR 2      YEAR 3  |   YEAR 4  |           |
                                '----[1]----'----[2]----'----[3]----'====[4]===='           |""");
                        break;
                    }
                }
                generateBookList(num);
                System.out.println("""
                        .------------------------------------------------------------.
                        | Select year level                               SELECT [O] |
                        '------------------------------------------------------------'""");
                if ((!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                        && !action.equals("4")) || (num < 1 || num > 4))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("x"))
                {
                    return;
                } else if (action.equals("1") || action.equals("2") || action.equals("3")
                        || action.equals("4"))
                {
                    num = Integer.parseInt(action);
                }
            } while (!action.equals("x") && !action.equals("o"));

            action = "x";

            do
            {
                do
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println("""
                            .------------------------------------------------------------.""");
                    System.out.println("| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                    switch (num2)
                    {
                        case 1: {
                            System.out.println("""
                                    |===========.-----------.-----------.-----------.------------'
                                    |  -CREATE  |   -EDIT      -DELETE    -RETRIEVE |           |
                                    '====[1]===='----[2]----'----[3]----'----[4]----'           |""");
                            break;
                        }
                        case 2: {
                            System.out.println("""
                                    |-----------.===========.-----------.-----------.------------'
                                    |  -CREATE  |   -EDIT   |  -DELETE    -RETRIEVE |           |
                                    '----[1]----'====[2]===='----[3]----'----[4]----'           |""");
                            break;
                        }
                        case 3: {
                            System.out.println("""
                                    |-----------.-----------.===========.-----------.------------'
                                    |  -CREATE      -EDIT   |  -DELETE  | -RETRIEVE |           |
                                    '----[1]----'----[2]----'====[3]===='----[4]----'           |""");
                            break;
                        }
                        case 4: {
                            System.out.println("""
                                    |-----------.-----------.-----------.===========.------------'
                                    |  -CREATE      -EDIT      -DELETE  | -RETRIEVE |           |
                                    '----[1]----'----[2]----'----[3]----'====[4]===='           |""");
                            break;
                        }
                    }
                    generateBookList(num);
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Select operation                                SELECT [O] |
                            '------------------------------------------------------------'""");
                    if ((!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                            && !action.equals("4")) || (num2 < 1 || num2 > 4))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        break;
                    } else if (action.equals("1") || action.equals("2") || action.equals("3")
                            || action.equals("4"))
                    {
                        num2 = Integer.parseInt(action);
                    }
                } while (!action.equals("x") && !action.equals("o"));

                if (action.equals("o"))
                {
                    switch (num2)
                    {
                        case 1: {
                            action = createBook(num);
                            num2 = 1;
                            break;
                        }
                        case 2: {
                            action = editBook(num);
                            num2 = 1;
                            break;
                        }
                        case 3: {
                            action = deleteBook(num);
                            num2 = 1;
                            break;
                        }
                        case 4: {
                            action = retrieveBook(num);
                            num2 = 1;
                            break;
                        }
                    }
                } else
                {
                    break;
                }
            } while (action.equals("x"));
        } while (action.equals("x"));
    }

    public static void customerCenter()
    {
        String action = "x";
        int num = 1;

        while (true)
        {
            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                if (buying)
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            | B U Y   B O O K S                                 BACK [X] |
                            |------------------------------------------------------------'
                            |               [ SELECT CUSTOMER ]             |           |
                            '-----------------------------------------------'           |""");
                    generateCustomerList();
                    System.out.println("""
                            .------------------------------------------------------------.
                            |                                           NEW CUSTOMER [O] |
                            '------------------------------------------------------------'""");
                } else
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            | C U S T O M E R   C E N T E R                     BACK [X] |""");
                    switch (num)
                    {
                        case 1: {
                            System.out.println("""
                                    |===========.-----------.-----------.-----------.------------'
                                    |  -CREATE  |   -EDIT      -DELETE    -RETRIEVE |           |
                                    '====[1]===='----[2]----'----[3]----'----[4]----'           |""");
                            break;
                        }
                        case 2: {
                            System.out.println("""
                                    |-----------.===========.-----------.-----------.------------'
                                    |  -CREATE  |   -EDIT   |  -DELETE    -RETRIEVE |           |
                                    '----[1]----'====[2]===='----[3]----'----[4]----'           |""");
                            break;
                        }
                        case 3: {
                            System.out.println("""
                                    |-----------.-----------.===========.-----------.------------'
                                    |  -CREATE      -EDIT   |  -DELETE  | -RETRIEVE |           |
                                    '----[1]----'----[2]----'====[3]===='----[4]----'           |""");
                            break;
                        }
                        case 4: {
                            System.out.println("""
                                    |-----------.-----------.-----------.===========.------------'
                                    |  -CREATE      -EDIT      -DELETE  | -RETRIEVE |           |
                                    '----[1]----'----[2]----'----[3]----'====[4]===='           |""");
                            break;
                        }
                    }
                    generateCustomerList();
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Select operation                                SELECT [O] |
                            '------------------------------------------------------------'""");
                }

                if ((!action.equals("x") && !action.equals("o") && !action.equals("1") && !action.equals("2")
                        && !action.equals("3")
                        && !action.equals("4")) || (num < 1 || num > 4))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\naction: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("x"))
                {
                    return;
                } else if (action.equals("1") || action.equals("2") || action.equals("3")
                        || action.equals("4"))
                {
                    num = Integer.parseInt(action);
                }
            } while (!action.equals("x") && !action.equals("o"));

            switch (num)
            {
                case 1: {
                    addCustomer();
                    break;
                }
                case 2: {
                    editCustomer();
                    break;
                }
                case 3: {
                    deleteCustomer();
                    break;
                }
                case 4: {
                    retrieveCustomer();
                    break;
                }
            }
        }
    }

    public static void addCustomer()
    {
        String action = "x";
        String name = " ";
        String bday = "";
        String seniorDisplay = "";
        String pwdDisplay = "";

        boolean validBday = true;
        boolean dupeName = false;
        boolean dupeBday = false;
        boolean pwdCheck = false;

        int month = 0;
        int day = 0;
        int year = 0;

        do
        {
            if (customerName.size() == 5)
            {
                while (true)
                {
                    if (buying)
                    {
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| B U Y   B O O K S                                 BACK [X] |");
                        System.out.println("""
                                |------------------------------------------------------------'
                                |                [ ADD CUSTOMER ]               |           |
                                '-----------------------------------------------'           |""");
                    } else
                    {
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| C U S T O M E R   C E N T E R                     BACK [X] |");
                        System.out.println("""
                                |------------------------------------------------------------'
                                |                [ ADD CUSTOMER ]               |           |
                                '-----------------------------------------------'           |""");
                    }
                    generateCustomerList();
                    System.out.println("""
                            .------------------------------------------------------------.
                            | This feature is currently unavailable. Customer list full. |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("action: ");
                    action = input.nextLine();
                    if (action.equals("x"))
                    {
                        return;
                    }
                }
            }
            do
            {
                do
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    if (buying)
                    {
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| B U Y   B O O K S                                 BACK [X] |");
                        System.out.println("""
                                |------------------------------------------------------------'
                                |                [ ADD CUSTOMER ]               |           |
                                '-----------------------------------------------'           |""");
                    } else
                    {
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| C U S T O M E R   C E N T E R                     BACK [X] |");
                        System.out.println("""
                                |------------------------------------------------------------'
                                |                [ ADD CUSTOMER ]               |           |
                                '-----------------------------------------------'           |""");
                    }
                    generateCustomerList();
                    System.out.println("""
                            .------------------------------------------------------------.
                            |      Do you wish to continue adding customer records?      |
                            '------------------------------------------------------------'""");
                    if (!action.equals("o") && !action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\n[O] YES    [X] NO: ");
                    action = input.nextLine();
                    if (action.equals("x"))
                    {
                        buyAddCustomerSaved = false;
                        buyAddCustomer = false;
                        return;
                    }
                } while (!action.equals("o") && !action.equals("x"));

                do
                {
                    do
                    {
                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            if (buying)
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| B U Y   B O O K S                                 BACK [X] |");
                                System.out.println("""
                                        |------------------------------------------------------------'
                                        |                [ ADD CUSTOMER ]               |           |
                                        '-----------------------------------------------'           |""");
                            } else
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| C U S T O M E R   C E N T E R                     BACK [X] |");
                                System.out.println("""
                                        |------------------------------------------------------------'
                                        |                [ ADD CUSTOMER ]               |           |
                                        '-----------------------------------------------'           |""");
                            }
                            System.out.println("""
                                     |                                                          |
                                     |   .-[Name]-------------------------------------------.   |
                                     |   |                                                  |   |
                                     |   '--------------------------------------------------'   |
                                     |   .-[Bday]-----.                                         |
                                     |   | --/--/---- |                                         |
                                     |   '------------'                                         |
                                     |                                                          |
                                     |   Senior Citizen : yes / no                              |
                                     |   PWD            : yes / no                              |
                                     |                                                          |
                                    .------------------------------------------------------------.
                                    |                     Enter customer name                    |
                                    '------------------------------------------------------------'""");
                            if (name.equals(""))
                            {
                                System.out.println(
                                        " |       Invalid action. Please fill in customer name.      |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            if (!validBday)
                            {
                                System.out.println(
                                        " |          Invalid date of birth. Please try again.        |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\nName: ");
                            name = input.nextLine().trim();
                        } while (name.equals(""));

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            if (buying)
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| B U Y   B O O K S                                 BACK [X] |");
                            } else
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| C U S T O M E R   C E N T E R                     BACK [X] |");
                            }
                            System.out.print("""
                                    |------------------------------------------------------------'
                                    |                [ ADD CUSTOMER ]               |           |
                                    '-----------------------------------------------'           |
                                     |                                                          |
                                     |   .-[Name]-------------------------------------------.   |
                                     |   |""");
                            System.out.print(" ");
                            for (int i = 0; i < 48; i++)
                            {
                                if (i >= name.length())
                                {
                                    System.out.print(" ");
                                } else
                                {
                                    System.out.print(name.charAt(i));
                                }
                            }
                            if (name.length() > 48)
                            {
                                System.out.print("-");
                            } else
                            {
                                System.out.print(" ");
                            }
                            System.out.println("|   |");
                            System.out.println(
                                    " |   '--------------------------------------------------'   |\n" +
                                            " |   .-[Bday]-----.                                         |\n" +
                                            " |   | --/--/---- |                                         |\n" +
                                            " |   '------------'                                         |\n" +
                                            " |                                                          |\n" +
                                            " |   Senior Citizen : yes / no                              |\n" +
                                            " |   PWD            : yes / no                              |\n" +
                                            " |                                                          |\n" +
                                            ".------------------------------------------------------------.\n" +
                                            "|                     Enter customer name                    |\n" +
                                            "'------------------------------------------------------------'");
                            if (!validBday)
                            {
                                System.out.println(
                                        " |          Invalid date of birth. Please try again.        |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\nDate of birth: ");
                            bday = input.nextLine();

                            if (bday.length() != 10)
                            {
                                validBday = false;
                            } else
                            {
                                month = Integer.parseInt(bday.substring(0, 2));
                                day = Integer.parseInt(bday.substring(3, 5));
                                year = Integer.parseInt(bday.substring(6, 10));
                                if (bday.charAt(2) != '/' || bday.charAt(5) != '/')
                                {
                                    validBday = false;
                                } else if (month < 1 || month > 12)
                                {
                                    validBday = false;
                                } else if (day < 1 || day > 31)
                                {
                                    validBday = false;
                                } else if (year > 2025)
                                {
                                    validBday = false;
                                } else if (month == 4 && day > 30 || month == 6 && day > 30 || month == 9 && day > 30
                                        || month == 11 && day > 30)
                                {
                                    validBday = false;
                                } else if (year == 2025 && month > 5)
                                {
                                    validBday = false;
                                } else
                                {
                                    validBday = true;
                                }
                            }
                        } while (!validBday);

                        if (year < 1965)
                        {
                            seniorDisplay = "[yes]/ no ";
                        } else
                        {
                            seniorDisplay = " yes /[no]";
                        }

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            if (buying)
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| B U Y   B O O K S                                 BACK [X] |");
                            } else
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| C U S T O M E R   C E N T E R                     BACK [X] |");
                            }
                            System.out.print("""
                                    |------------------------------------------------------------'
                                    |                [ ADD CUSTOMER ]               |           |
                                    '-----------------------------------------------'           |
                                     |                                                          |
                                     |   .-[Name]-------------------------------------------.   |
                                     |   |""");
                            System.out.print(" ");
                            for (int i = 0; i < 48; i++)
                            {
                                if (i >= name.length())
                                {
                                    System.out.print(" ");
                                } else
                                {
                                    System.out.print(name.charAt(i));
                                }
                            }
                            if (name.length() > 48)
                            {
                                System.out.print("-");
                            } else
                            {
                                System.out.print(" ");
                            }
                            System.out.println("|   |");
                            System.out.println(
                                    " |   '--------------------------------------------------'   |\n" +
                                            " |   .-[Bday]-----.                                         |\n" +
                                            " |   | " + bday + " |                                         |\n" +
                                            " |   '------------'                                         |\n" +
                                            " |                                                          |\n" +
                                            " |   Senior Citizen :" + seniorDisplay + "                             |\n"
                                            +
                                            " |   PWD            : yes / no                              |\n" +
                                            " |                                                          |\n" +
                                            ".------------------------------------------------------------.\n" +
                                            "|                             PWD?                           |\n" +
                                            "'------------------------------------------------------------'");
                            System.out.print("\n[O] YES   [X] NO: ");
                            action = input.nextLine().toLowerCase();
                        } while (!action.equals("o") && !action.equals("x"));

                        if (action.equals("o"))
                        {
                            pwdDisplay += "[yes]/ no ";
                            pwdCheck = true;
                        } else
                        {
                            pwdDisplay += " yes /[no]";
                            pwdCheck = false;
                        }

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            if (buying)
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| B U Y   B O O K S                                 BACK [X] |");
                            } else
                            {
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| C U S T O M E R   C E N T E R                     BACK [X] |");
                            }
                            System.out.print("""
                                    |------------------------------------------------------------'
                                    |                [ ADD CUSTOMER ]               |           |
                                    '-----------------------------------------------'           |
                                     |                                                          |
                                     |   .-[Name]-------------------------------------------.   |
                                     |   |""");
                            System.out.print(" ");
                            for (int i = 0; i < 48; i++)
                            {
                                if (i >= name.length())
                                {
                                    System.out.print(" ");
                                } else
                                {
                                    System.out.print(name.charAt(i));
                                }
                            }
                            if (name.length() > 48)
                            {
                                System.out.print("-");
                            } else
                            {
                                System.out.print(" ");
                            }
                            System.out.println("|   |");
                            System.out.println(
                                    " |   '--------------------------------------------------'   |\n" +
                                            " |   .-[Bday]-----.                                         |\n" +
                                            " |   | " + bday + " |                                         |\n" +
                                            " |   '------------'                                         |\n" +
                                            " |                                                          |\n" +
                                            " |   Senior Citizen :" + seniorDisplay + "                             |\n"
                                            +
                                            " |   PWD            :" + pwdDisplay + "                             |\n" +
                                            " |                                                          |\n" +
                                            ".------------------------------------------------------------.\n" +
                                            "|                                                PROCEED [O] |\n" +
                                            "'------------------------------------------------------------'");
                            System.out.print("\naction: ");
                            action = input.nextLine().toLowerCase();
                            if (action.equals("x"))
                            {
                                break;
                            }
                        } while (!action.equals("o"));
                    } while (action.equals("x"));
                    for (int a = 0; a < customerName.size(); a++)
                    {
                        if (name.equals(customerName.get(a)))
                        {
                            dupeName = true;
                            break;
                        } else
                        {
                            dupeName = false;
                        }
                    }
                    for (int b = 0; b < customerBday.size(); b++)
                    {
                        if (bday.equals(customerBday.get(b)))
                        {
                            dupeBday = true;
                            break;
                        } else
                        {
                            dupeBday = false;
                        }
                    }
                    if (dupeName && dupeBday)
                    {
                        action = "x";

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            // if (buying)
                            // {
                            // System.out.println(".------------------------------------------------------------.\n"
                            // +
                            // "| B U Y B O O K S BACK [X] |");
                            // System.out.println("""
                            // |------------------------------------------------------------'
                            // | [ ADD CUSTOMER ] | |
                            // '-----------------------------------------------' |""");
                            // } else
                            // {
                            // System.out.println(".------------------------------------------------------------.\n"
                            // +
                            // "| C U S T O M E R C E N T E R BACK [X] |");
                            // System.out.println("""
                            // |------------------------------------------------------------'
                            // | [ ADD CUSTOMER ] | |
                            // '-----------------------------------------------' |""");
                            // }
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | C U S T O M E R   C E N T E R - [ CREATE ]                 |
                                    |------------------------------------------------------------|
                                    |                   Customer already exist.                  |
                                    '------------------------------------------------------------'""");
                            if (!action.equals("o") && !action.equals("x"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\n[O] TRY AGAIN    [X] EXIT: ");
                            action = input.nextLine().toLowerCase();
                            if (action.equals("x"))
                            {
                                return;
                            }
                        } while (!action.equals("o") && !action.equals("x"));
                    } else
                    {
                        customerName.add(name);
                        customerBday.add(bday);
                        buyAddCustomerSaved = true;
                    }
                } while (dupeName && dupeBday);
            } while (action.equals("x"));

            action = "o";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                if (buying)
                {
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| B U Y   B O O K S                                          |");
                } else
                {
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| C U S T O M E R   C E N T E R                     BACK [X] |");
                }
                System.out.println("""
                        |------------------------------------------------------------'
                        |                [ ADD CUSTOMER ]               |           |
                        '-----------------------------------------------'           |
                         |                                                          |
                         |                                         .:'              |
                         |                                      .::'                |
                         |                                    .::'                  |
                         |                                  .::'                    |
                         |                                .::'                      |
                         |                    ':.       .::'                        |
                         |                      '::.  .::'                          |
                         |                        ':::::                            |
                         |                          '''                             |
                         |                                                          |""");
                if (buying)
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Customer has been added successfully.   PROCEED BUYING [O] |
                            '------------------------------------------------------------'""");
                } else
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            |            Customer has been added successfully.           |
                            '------------------------------------------------------------'""");
                }

                if (!action.equals("o"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                if (buying)
                {
                    System.out.print("\naction: ");
                } else
                {
                    System.out.print("\n[O] AGAIN    [X] BACK: ");
                }
                action = input.nextLine().toLowerCase();
                if (buying)
                {
                    if (action.equals("o"))
                    {
                        pwd.add(pwdCheck);
                        yearForReceipt = year;
                        return;
                    }
                } else if (action.equals("x"))
                {
                    break;
                }
            } while (!action.equals("o"));
        } while (action.equals("o"));
    }

    public static void editCustomer()
    {
        int num = -1;
        String action = "x";
        String name = "";
        String bday = "";
        String tryAgain = "o";

        boolean validBday = true;
        boolean dupeName = false;
        boolean dupeBday = false;

        int month = 0;
        int day = 0;
        int year = 0;

        do
        {
            do
            {
                do
                {
                    do
                    {
                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println(".------------------------------------------------------------.\n" +
                                    "| C U S T O M E R   C E N T E T                     BACK [X] |");
                            System.out.println("""
                                    |------------------------------------------------------------'
                                    |                [ EDIT CUSTOMER ]              |           |
                                    '-----------------------------------------------'           |""");
                            generateCustomerList();
                            if (customerName.size() < 1)
                            {
                                System.out.println("""
                                        .------------------------------------------------------------.
                                        | This feature is currently unavailable. Customer list empty |
                                        '------------------------------------------------------------'""");
                            } else
                            {
                                System.out.println("""
                                        .------------------------------------------------------------.
                                        | Select year level by numbers                               |
                                        '------------------------------------------------------------'""");
                            }
                            if (!action.equals("x") && action.equals("1") && action.equals("2") && action.equals("3")
                                    && action.equals("4")
                                    && action.equals("5"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            if (num == 0 || num > customerName.size())
                            {
                                System.out.println(
                                        " |             Invalid number. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            if (customerName.size() < 1)
                            {
                                System.out.print("\naction: ");
                                action = input.nextLine().toLowerCase();
                                if (action.equals("x"))
                                {
                                    return;
                                }
                            } else
                            {
                                System.out.print("\naction: ");
                                action = input.nextLine().toLowerCase();
                                if (action.equals("x"))
                                {
                                    return;
                                } else if (action.equals("1") || action.equals("2") || action.equals("3")
                                        || action.equals("4")
                                        || action.equals("5"))
                                {
                                    num = Integer.parseInt(action);
                                }
                            }
                        } while (num == 0 || num > customerName.size());

                        action = "x";

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | C U S T O M E R   C E N T E R - [ EDIT ]          BACK [X] |
                                    |------------------------------------------------------------|
                                    |                     You have selected...                   |
                                    '------------------------------------------------------------'""");
                            System.out.println(
                                    " |                                                          |\n" +
                                            " |    [NAME]                             [DATE OF BIRTH]    |\n" +
                                            " |                                                          |");
                            System.out.print(" |     ");
                            System.out.print(customerName.get(num - 1));
                            int gap = 37 - customerName.get(num - 1).length();
                            for (int b = 0; b < gap; b++)
                            {
                                System.out.print(" ");
                            }
                            System.out.print(customerBday.get(num - 1));
                            System.out.println("      |");
                            System.out.println(
                                    " |                                                          |");
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    |       Do you wish to continue editing this customer?       |
                                    '------------------------------------------------------------'""");
                            if (!action.equals("x") && !action.equals("o"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\n[O] YES    [X] NO:  ");
                            action = input.nextLine().toLowerCase();
                        } while (!action.equals("x") && !action.equals("o"));
                    } while (num < 1 || num > customerName.size() && !action.equals("x") || action.equals("x"));

                    action = "x";

                    do
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        System.out.println("""
                                .------------------------------------------------------------.
                                | C U S T O M E R   C E N T E R - [ EDIT ]                   |
                                |------------------------------------------------------------|
                                |                   Enter customer details                   |
                                '------------------------------------------------------------'""");
                        if (!validBday)
                        {
                            System.out.println(
                                    " |          Invalid date of birth. Please try again.        |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\nName: ");
                        name = input.nextLine();
                        System.out.print("Date of birth: ");
                        bday = input.nextLine();

                        if (bday.length() != 10)
                        {
                            validBday = false;
                        } else
                        {
                            month = Integer.parseInt(bday.substring(0, 2));
                            day = Integer.parseInt(bday.substring(3, 5));
                            year = Integer.parseInt(bday.substring(6, 10));
                            if (bday.charAt(2) != '/' || bday.charAt(5) != '/')
                            {
                                validBday = false;
                            } else if (month < 1 || month > 12)
                            {
                                validBday = false;
                            } else if (day < 1 || day > 31)
                            {
                                validBday = false;
                            } else if (year > 2025)
                            {
                                validBday = false;
                            } else if (month == 4 && day > 30 || month == 6 && day > 30 || month == 9 && day > 30
                                    || month == 11 && day > 30)
                            {
                                validBday = false;
                            } else if (year == 2025 && month > 5)
                            {
                                validBday = false;
                            } else
                            {
                                validBday = true;
                            }
                        }
                    } while (!validBday);
                    for (int a = 0; a < customerName.size(); a++)
                    {
                        if (name.equals(customerName.get(a)))
                        {
                            dupeName = true;
                            break;
                        } else
                        {
                            dupeName = false;
                        }
                    }
                    for (int b = 0; b < customerBday.size(); b++)
                    {
                        if (bday.equals(customerBday.get(b)))
                        {
                            dupeBday = true;
                            break;
                        } else
                        {
                            dupeBday = false;
                        }
                    }
                    if (dupeName && dupeBday)
                    {
                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | C U S T O M E R   C E N T E R - [ EDIT ]                   |
                                    |------------------------------------------------------------|
                                    |                   Customer already exist.                  |
                                    '------------------------------------------------------------'""");
                            if (!tryAgain.equals("o") && !tryAgain.equals("x"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\n[O] TRY AGAIN    [X] EXIT: ");
                            tryAgain = input.nextLine().toLowerCase();
                            if (tryAgain.equals("x"))
                            {
                                return;
                            }
                        } while (!tryAgain.equals("o") && !tryAgain.equals("x"));
                    } else
                    {
                        action = "x";

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | C U S T O M E R   C E N T E R - [ EDIT ]          BACK [X] |
                                    '------------------------------------------------------------'""");
                            System.out.println(
                                    " |                                                          |\n" +
                                            " |    [NAME]                             [DATE OF BIRTH]    |\n" +
                                            " |                                                          |");
                            System.out.print(" |     ");
                            System.out.print("Old : " + customerName.get(num - 1));
                            int gap = 31 - customerName.get(num - 1).length();
                            for (int b = 0; b < gap; b++)
                            {
                                System.out.print(" ");
                            }
                            System.out.print(customerBday.get(num - 1));
                            System.out.println("      |");
                            System.out.print(" |     ");
                            System.out.print("New : " + name);
                            gap = 31 - name.length();
                            for (int b = 0; b < gap; b++)
                            {
                                System.out.print(" ");
                            }
                            System.out.print(bday);
                            System.out.println("      |");
                            System.out.println(
                                    " |                                                          |");
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    |           Are you sure you want to save changes?           |
                                    '------------------------------------------------------------'""");
                            if (!action.equals("x") && !action.equals("o"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\n[O] YES    [X] NO: ");
                            action = input.nextLine().toLowerCase();
                            if (action.equals("o"))
                            {
                                customerName.set((num - 1), name);
                                customerBday.set((num - 1), bday);

                            }
                        } while (!action.equals("x") && !action.equals("o"));
                    }
                } while (dupeName && dupeBday);
            } while (action.equals("x"));

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | C U S T O M E R   C E N T E R - [ EDIT ]                   |
                        |------------------------------------------------------------|
                        |             Changes has been saved successfully.           |
                        '------------------------------------------------------------'""");
                if (!action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\n[O] AGAIN    [X] BACK: ");
                action = input.nextLine();
            } while (!action.equals("x") && !action.equals("o"));
        } while (action.equals("o"));
    }

    public static void deleteCustomer()
    {
        int num = -1;
        String action = "x";

        do
        {
            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println(".------------------------------------------------------------.\n" +
                        "| C U S T O M E R   C E N T E T                     BACK [X] |");
                System.out.println("""
                        |------------------------------------------------------------'
                        |               [ DELETE CUSTOMER ]             |           |
                        '-----------------------------------------------'           |""");
                generateCustomerList();
                if (customerName.size() < 1)
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            | This feature is currently unavailable. Customer list empty |
                            '------------------------------------------------------------'""");
                } else
                {
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Select year level by numbers                               |
                            '------------------------------------------------------------'""");
                }
                if (!action.equals("x") && action.equals("1") && action.equals("2") && action.equals("3")
                        && action.equals("4")
                        && action.equals("5"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                if (num == 0 || num > customerName.size())
                {
                    System.out.println(
                            " |             Invalid number. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                if (customerName.size() < 1)
                {
                    System.out.print("action: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return;
                    }
                } else
                {
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return;
                    } else if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                            || action.equals("5"))
                    {
                        num = Integer.parseInt(action);
                    }
                }
            } while (num < 1 || num > customerName.size() && !action.equals("x"));

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }

                System.out.println("[NAME]             [DATE OF BIRTH]");
                System.out.print(customerName.get(num - 1));
                int gap = 19 - customerName.get(num - 1).length();
                for (int b = 0; b < gap; b++)
                {
                    System.out.print(" ");
                }
                System.out.println(customerBday.get(num - 1));
                System.out.println(
                        "--------------------------------------------------------------------------------------");

                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println("""
                            Invalid action. Please try again.
                            --------------------------------------------------------------------------------------""");
                }
                System.out.println("Are you sure you want to delete customer #" + num + "?");
                System.out.print("[O] YES    [X] NO: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("o"))
                {
                    customerNameBin.add(customerName.get(num - 1));
                    customerBdayBin.add(customerName.get(num - 1));
                    pwdBin.add(pwd.get(num - 1));

                    customerName.remove(num - 1);
                    customerBday.remove(num - 1);
                    pwd.remove(num - 1);
                }
            } while (!action.equals("o") && !action.equals("x"));
        } while (action.equals("x"));

        action = "x";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    Record deleted successfully.
                    --------------------------------------------------------------------------------------""");
            if (!action.equals("x"))
            {
                System.out.println("""
                        Invalid action. Please try again.
                        --------------------------------------------------------------------------------------""");
            }
            System.out.print("[X] BACK: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("x"));
    }

    public static void retrieveCustomer()
    {
        String action = "x";
        int num = -1;

        do
        {
            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        --------------------------------------------------------------------------------------
                        C U S T O M E R    M A N A G E M E N T - [ RETRIEVE ]                         BACK [X]
                        --------------------------------------------------------------------------------------""");
                generateCustomerBinList();
                if (!action.equals("x"))
                {
                    System.out.println("""
                            Invalid action. Please try again.
                            --------------------------------------------------------------------------------------""");
                }
                if (num == 0 || num > customerNameBin.size())
                {
                    System.out.println("""
                            Invalid number. Please try again.
                            --------------------------------------------------------------------------------------""");
                }
                if (customerNameBin.size() < 1)
                {
                    System.out.println("""
                            The retrieve feature is currently unavailable. The bin is empty.
                            --------------------------------------------------------------------------------------""");
                    System.out.print("action: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return;
                    }
                } else
                {
                    System.out.println("Select a # to delete: ");
                    System.out.print("action: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return;
                    } else if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                            || action.equals("5"))
                    {
                        num = Integer.parseInt(action);
                    }
                }
            } while (num < 1 || num > customerNameBin.size() && !action.equals("x"));

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("[NAME]             [DATE OF BIRTH]");
                System.out.print(customerNameBin.get(num - 1));
                int gap = 19 - customerNameBin.get(num - 1).length();
                for (int b = 0; b < gap; b++)
                {
                    System.out.print(" ");
                }
                System.out.println(customerBdayBin.get(num - 1));
                System.out.println(
                        "--------------------------------------------------------------------------------------");
                if (!action.equals("o") && !action.equals("x"))
                {
                    System.out.println("""
                            Invalid action. Please try again.
                            --------------------------------------------------------------------------------------""");
                }
                System.out.println("Are you sure you want to retrieve customer #" + num + "?");
                System.out.print("[O] YES    [X] NO: ");
                action = input.nextLine().toLowerCase();
                if (action.equals("o"))
                {
                    customerName.add(customerNameBin.get(num - 1));
                    customerBday.add(customerNameBin.get(num - 1));
                    pwd.add(pwdBin.get(num - 1));

                    customerNameBin.remove(num - 1);
                    customerBdayBin.remove(num - 1);
                    pwdBin.remove(num - 1);
                }
            } while (!action.equals("o") && !action.equals("x"));
        } while (action.equals("x"));

        action = "x";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    Record retrieved successfully.
                    --------------------------------------------------------------------------------------""");
            if (!action.equals("x"))
            {
                System.out.println("""
                        Invalid action. Please try again.
                        --------------------------------------------------------------------------------------""");
            }
            System.out.print("[X] BACK: ");
            action = input.nextLine().toLowerCase();
        } while (!action.equals("x"));
    }

    public static String createBook(int num)
    {
        String action = "x";
        String bookTitle = "";
        int bookStock = 1;
        String again = "";

        boolean bookDupe = false;

        action = "1";

        do
        {
            again = "";

            if (title.get(num - 1).size() == 5)
            {
                while (true)
                {
                    for (int index = 0; index < 100; index++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                [ CREATE BOOK ]                |           |
                            '-----------------------------------------------'           |""");

                    generateBookList(num);
                    System.out.println("""
                            .------------------------------------------------------------.
                            |   This feature is currently unavailable. Section is full.  |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return action;
                    }
                }
            }
            do
            {
                do
                {
                    do
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }

                        action = "x";

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println(".------------------------------------------------------------.\n" +
                                    "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                            System.out.println("""
                                    |------------------------------------------------------------'
                                    |                [ CREATE BOOK ]                |           |
                                    '-----------------------------------------------'           |""");
                            generateBookList(num);
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    |                                                PROCEED [O] |
                                    '------------------------------------------------------------'""");
                            if (!action.equals("o") && !action.equals("x"))
                            {
                                System.out.println(
                                        " |             Invalid action. Please try again.            |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\naction: ");
                            action = input.nextLine();
                            if (action.equals("x"))
                            {
                                return action;
                            }
                        } while (!action.equals("x") && !action.equals("o"));

                        action = "x";

                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println(".------------------------------------------------------------.\n" +
                                    "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                            System.out.println("""
                                    |------------------------------------------------------------'
                                    |                [ CREATE BOOK ]                |           |
                                    '-----------------------------------------------'           |""");
                            System.out.println(
                                    " |                                                          |\n" +
                                            " |                                                          |\n" +
                                            " |                                                          |\n" +
                                            " |    [BOOK TITLE]                             [STOCKS]     |\n" +
                                            " |   .-------------------------------------.    .-.         |\n" +
                                            " |   |                                     |    | |/3       |\n" +
                                            " |   '-------------------------------------'    '-'         |\n" +
                                            " |                                                          |\n" +
                                            " |                                                          |\n" +
                                            " |                                                          |\n" +
                                            " |                                                          |");
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    |                    Enter book details.                     |
                                    '------------------------------------------------------------'""");

                            if (bookStock < 1 || bookStock > 3)
                            {
                                System.out.println(
                                        " |          Invalid stocks amount. Please try again.        |\n" +
                                                " '----------------------------------------------------------'");
                            }
                            System.out.print("\nTitle: ");
                            bookTitle = input.nextLine();
                            System.out.print("Stock amount: ");
                            bookStock = input.nextInt();
                            input.nextLine();
                        } while (bookStock < 1 || bookStock > 3);
                        for (int i = 0; i < title.get(num - 1).size(); i++)
                        {
                            if (bookTitle.equals(title.get(num - 1).get(i)))
                            {
                                bookDupe = true;
                                break;
                            } else
                            {
                                bookDupe = false;
                            }
                        }
                        if (bookDupe)
                        {
                            action = "x";

                            do
                            {
                                for (int i = 0; i < 100; i++)
                                {
                                    System.out.println();
                                }
                                System.out.println("""
                                        .------------------------------------------------------------.
                                        | I N V E N T O R Y - CREATE BOOK                            |
                                        |------------------------------------------------------------|
                                        |                  Book title already exist.                 |
                                        '------------------------------------------------------------'""");

                                if (!action.equals("o") && !action.equals("x"))
                                {
                                    System.out.println(
                                            " |             Invalid action. Please try again.            |\n" +
                                                    " '----------------------------------------------------------'");
                                }
                                System.out.print("\n[O] TRY AGAIN    [X] EXIT: ");
                                action = input.nextLine().toLowerCase();
                                if (action.equals("x"))
                                {
                                    return action;
                                }
                            } while (!action.equals("o") && !action.equals("x"));
                        } else
                        {
                            action = "x";

                            do
                            {
                                for (int i = 0; i < 100; i++)
                                {
                                    System.out.println();
                                }
                                System.out.println(".------------------------------------------------------------.\n" +
                                        "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                                System.out.println("""
                                        |------------------------------------------------------------'
                                        |                [ CREATE BOOK ]                |           |
                                        '-----------------------------------------------'           |""");
                                System.out.print(
                                        " |                                                          |\n" +
                                                " |                                                          |\n" +
                                                " |                                                          |\n" +
                                                " |    [BOOK TITLE]                             [STOCKS]     |\n" +
                                                " |   .-------------------------------------.    .-.         |\n" +
                                                " |   | ");
                                for (int i = 0; i < 35; i++)
                                {
                                    if (i >= bookTitle.length())
                                    {
                                        System.out.print(" ");
                                    } else
                                    {
                                        System.out.print(bookTitle.charAt(i));
                                    }
                                }
                                if (bookTitle.length() > 35)
                                {
                                    System.out.print("-");
                                } else
                                {
                                    System.out.print(" ");
                                }
                                System.out.println("|    |" + bookStock + "|/3       |");
                                System.out.println(
                                        " |   '-------------------------------------'    '-'         |\n" +
                                                " |                                                          |\n" +
                                                " |                                                          |\n" +
                                                " |                                                          |\n" +
                                                " |                                                          |");

                                System.out.println("""
                                        .------------------------------------------------------------.
                                        |        Do you wish to continue creating this book?         |
                                        '------------------------------------------------------------'""");
                                if (!action.equals("o") && !action.equals("x"))
                                {
                                    System.out.println(
                                            " |             Invalid action. Please try again.            |\n" +
                                                    " '----------------------------------------------------------'");
                                }
                                System.out.print("\n[O] YES    [X] NO: ");
                                action = input.nextLine().toLowerCase();
                            } while (!action.equals("o") && !action.equals("x"));
                        }
                    } while (bookDupe || bookStock < 1 || bookStock > 3);
                } while (!action.equals("x") && !action.equals("o"));
            } while (action.equals("x"));

            title.get(num - 1).add(bookTitle);
            stocks.get(num - 1).add(bookStock);

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println(".------------------------------------------------------------.\n" +
                        "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                System.out.println("""
                        |------------------------------------------------------------'
                        |                [ CREATE BOOK ]                |           |
                        '-----------------------------------------------'           |
                         |                                                          |
                         |                                         .:'              |
                         |                                      .::'                |
                         |                                    .::'                  |
                         |                                  .::'                    |
                         |                                .::'                      |
                         |                    ':.       .::'                        |
                         |                      '::.  .::'                          |
                         |                        ':::::                            |
                         |                          '''                             |
                         |                                                          |
                        .------------------------------------------------------------.
                        |             Book has been created successfully.            |
                        '------------------------------------------------------------'""");
                if (!action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\n[O] AGAIN    [X] BACK: ");
                action = input.nextLine();
            } while (!action.equals("x") && !action.equals("o"));
            if (action.equals("o"))
            {
                again = action;
            }
            action = "x";
        } while (again.equals("o"));
        return action;
    }

    public static String editBook(int num)
    {
        String action = "x";
        int num2 = 1;
        String bookTitle = "";
        String again = "";

        boolean bookDupe = false;

        do
        {
            again = "";

            if (title.get(num - 1).size() < 1)
            {
                while (true)
                {
                    for (int index = 0; index < 100; index++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                     BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                 [ EDIT BOOK ]                 |           |
                            '-----------------------------------------------'           |""");
                    generateBookList(num);
                    System.out.println("""
                            .------------------------------------------------------------.
                            |  This feature is currently unavailable. Section is empty.  |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return action;
                    }
                }
            }
            do
            {
                do
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                 [ EDIT BOOK ]                 |           |
                            '-----------------------------------------------'           |""");
                    generateBookList(num);
                    System.out.println("""
                            .------------------------------------------------------------.
                            |                Select book title by numbers.               |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                            && !action.equals("4") && !action.equals("5") || num2 > title.get(num - 1).size())
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return action;
                    }
                    if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                            || action.equals("5"))
                    {
                        num2 = Integer.parseInt(action);
                    }
                } while (!action.equals("1") && !action.equals("2") && !action.equals("3")
                        && !action.equals("4") && !action.equals("5") || num2 > title.get(num - 1).size());

                action = "x";

                do
                {
                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                 [ EDIT BOOK ]                 |           |
                            '-----------------------------------------------'           |""");
                    System.out.print(
                            " |                                                          |\n" +
                                    " |    You have selected...                                  |\n" +
                                    " |                                                          |\n" +
                                    " |    [BOOK TITLE]                                          |\n" +
                                    " |                                                          |\n" +
                                    " |    - " + title.get(num - 1).get(num2 - 1));
                    int gap = 52 - title.get(num - 1).get(num2 - 1).length();
                    for (int i = 0; i < gap; i++)
                    {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    System.out.println(
                            " |                                                          |\n" +
                                    " |                                                          |\n" +
                                    " |                                                          |\n" +
                                    " |                                                          |\n" +
                                    " |                                                          |");
                    System.out.println("""
                            .------------------------------------------------------------.
                            |         Do you wish to continue editing this title?        |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\n[O] YES    [X] NO: ");
                    action = input.nextLine().toLowerCase();

                    if (action.equals("o"))
                    {
                        do
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                System.out.println();
                            }
                            System.out.println(".------------------------------------------------------------.\n" +
                                    "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                            System.out.println("""
                                    |------------------------------------------------------------'
                                    |                 [ EDIT BOOK ]                 |           |
                                    '-----------------------------------------------'           |""");
                            System.out.print(
                                    " |                                                          |\n" +
                                            " |    [BOOK TITLE]                                          |\n" +
                                            " |                                                          |\n" +
                                            " |   .-[Old]--------------------------------------------.   |\n" +
                                            " |   | ");
                            for (int i = 0; i < 48; i++)
                            {
                                if (i >= title.get(num - 1).get(num2 - 1).length())
                                {
                                    System.out.print(" ");
                                } else
                                {
                                    System.out.print(title.get(num - 1).get(num2 - 1).charAt(i));
                                }
                            }
                            if (title.get(num - 1).get(num2 - 1).length() > 48)
                            {
                                System.out.print("-");
                            } else
                            {
                                System.out.print(" ");
                            }
                            System.out.println("|   |");
                            System.out.println(
                                    " |   '--------------------------------------------------'   |\n" +
                                            " |                                                          |\n" +
                                            " |   .-[New]--------------------------------------------.   |\n" +
                                            " |   |                                                  |   |\n" +
                                            " |   '--------------------------------------------------'   |\n" +
                                            " |                                                          |");
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    |                   Enter new book title.                    |
                                    '------------------------------------------------------------'""");
                            System.out.print("\nTitle: ");
                            bookTitle = input.nextLine();
                            for (int i = 0; i < title.get(num - 1).size(); i++)
                            {
                                if (bookTitle.equals(title.get(num - 1).get(i)))
                                {
                                    bookDupe = true;
                                    break;
                                } else
                                {
                                    bookDupe = false;
                                }
                            }
                            if (bookDupe)
                            {
                                action = "x";

                                do
                                {
                                    for (int i = 0; i < 100; i++)
                                    {
                                        System.out.println();
                                    }
                                    System.out.println("""
                                            .------------------------------------------------------------.
                                            | D A T A B A S E - EDIT BOOK                                |
                                            |------------------------------------------------------------|
                                            |                  Book title already exist.                 |
                                            '------------------------------------------------------------'""");
                                    if (!action.equals("o") && !action.equals("x"))
                                    {
                                        System.out.println(
                                                " |             Invalid action. Please try again.           |\n" +
                                                        " '---------------------------------------------------------'");
                                    }
                                    System.out.print("\n[O] TRY AGAIN    [X] EXIT: ");
                                    action = input.nextLine().toLowerCase();
                                    if (action.equals("x"))
                                    {
                                        return action;
                                    }
                                } while (!action.equals("o") && !action.equals("x"));
                            } else
                            {
                                action = "x";

                                do
                                {
                                    for (int i = 0; i < 100; i++)
                                    {
                                        System.out.println();
                                    }
                                    System.out.println(
                                            ".------------------------------------------------------------.\n" +
                                                    "| I N V E N T O R Y - [ YEAR " + num
                                                    + " ]                    BACK [X] |");
                                    System.out.println("""
                                            |------------------------------------------------------------'
                                            |                 [ EDIT BOOK ]                 |           |
                                            '-----------------------------------------------'           |""");
                                    System.out.print(
                                            " |                                                          |\n" +
                                                    " |    [BOOK TITLE]                                          |\n" +
                                                    " |                                                          |\n" +
                                                    " |   .-[Old]--------------------------------------------.   |\n" +
                                                    " |   | ");
                                    for (int i = 0; i < 48; i++)
                                    {
                                        if (i >= title.get(num - 1).get(num2 - 1).length())
                                        {
                                            System.out.print(" ");
                                        } else
                                        {
                                            System.out.print(title.get(num - 1).get(num2 - 1).charAt(i));
                                        }
                                    }
                                    if (title.get(num - 1).get(num2 - 1).length() > 48)
                                    {
                                        System.out.print("-");
                                    } else
                                    {
                                        System.out.print(" ");
                                    }
                                    System.out.println("|   |");
                                    System.out.println(
                                            " |   '--------------------------------------------------'   |");
                                    System.out.print(
                                            " |                                                          |\n" +
                                                    " |   .-[New]--------------------------------------------.   |\n" +
                                                    " |   | ");
                                    for (int i = 0; i < 48; i++)
                                    {
                                        if (i >= bookTitle.length())
                                        {
                                            System.out.print(" ");
                                        } else
                                        {
                                            System.out.print(bookTitle.charAt(i));
                                        }
                                    }
                                    if (bookTitle.length() > 48)
                                    {
                                        System.out.print("-");
                                    } else
                                    {
                                        System.out.print(" ");
                                    }
                                    System.out.println("|   |");
                                    System.out.println(
                                            " |   '--------------------------------------------------'   |");
                                    System.out.println(
                                            " |                                                          |");
                                    System.out.println("""
                                            .------------------------------------------------------------.
                                            |         Do you wish to save changes to this book?          |
                                            '------------------------------------------------------------'""");
                                    if (!action.equals("o") && !action.equals("x"))
                                    {
                                        System.out.println(
                                                " |             Invalid action. Please try again.            |\n" +
                                                        " '----------------------------------------------------------'");
                                    }
                                    System.out.print("\n[O] YES    [X] NO: ");
                                    action = input.nextLine().toLowerCase();
                                } while (!action.equals("o") && !action.equals("x"));
                            }
                        } while (bookDupe);
                    }
                } while (!action.equals("x") && !action.equals("o"));
            } while (action.equals("x"));

            title.get(num - 1).set((num2 - 1), bookTitle);
            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | D A T A B A S E - EDIT BOOK                                |
                        |------------------------------------------------------------|
                        |          Your changes have been saved successfully         |
                        '------------------------------------------------------------'""");
                if (!action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\n[O] AGAIN    [X] BACK: ");
                action = input.nextLine();
            } while (!action.equals("x") && !action.equals("o"));
            if (action.equals("o"))
            {
                again = action;
            }
            action = "x";
        } while (again.equals("o"));
        return action;
    }

    public static String deleteBook(int num)
    {
        String action = "x";
        int num2 = 1;
        int count = 0;
        String again = "";

        boolean selected = false;

        do
        {
            again = "";

            if (title.get(num - 1).size() < 1)
            {
                while (true)
                {
                    for (int index = 0; index < 100; index++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                     BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                [ DELETE BOOK ]                |           |
                            '-----------------------------------------------'           |""");
                    generateBookList(num);
                    if (title.get(num - 1).size() < 1)
                    {
                        System.out.println("""
                                .------------------------------------------------------------.
                                |  This feature is currently unavailable. Section is empty.  |
                                '------------------------------------------------------------'""");
                    }
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return action;
                    }
                }
            }
            do
            {
                for (int i = 0; i < select.size(); i++)
                {
                    for (int j = 0; j < select.size(); j++)
                    {
                        select.set(j, false);
                    }
                }

                do
                {
                    selecting = true;

                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println(".------------------------------------------------------------.\n" +
                            "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                    System.out.println("""
                            |------------------------------------------------------------'
                            |                [ DELETE BOOK ]                |           |
                            '-----------------------------------------------'           |""");
                    generateBookList(num);
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Select book title by numbers.                  PROCEED [O] |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                            && !action.equals("4") && !action.equals("5") || num2 > title.get(num - 1).size())
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        for (int i = 0; i < select.size(); i++)
                        {
                            for (int j = 0; j < select.size(); j++)
                            {
                                select.set(j, false);
                            }
                        }
                        return action;
                    }
                    if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                            || action.equals("5"))
                    {
                        num2 = Integer.parseInt(action);
                        if (num2 > 0 && num2 <= title.get(num - 1).size())
                        {
                            select.set(num2 - 1, !select.get(num2 - 1));
                        }
                    }
                    for (int i = 0; i < title.get(num - 1).size(); i++)
                    {
                        if (select.get(i))
                        {
                            selected = true;
                            break;
                        } else
                        {
                            selected = false;
                        }
                    }
                } while (!action.equals("o") || (num2 < 1 && num2 > titleBin.get(num - 1).size()) || !selected);

                selecting = false;

                if (action.equals("o"))
                {
                    action = "x";

                    do
                    {
                        count = 0;
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        for (int i = 0; i < select.size(); i++)
                        {
                            if (select.get(i))
                            {
                                count++;
                            }
                        }
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| I N V E N T O R Y - [ YEAR " + num + " ]                             |");
                        System.out.println("""
                                |------------------------------------------------------------'
                                |                [ DELETE BOOK ]                |           |
                                '-----------------------------------------------'           |""");

                        displaySelected(num, count);

                        System.out.println("""
                                .------------------------------------------------------------.
                                |              Do you wish to continue deleting?             |
                                '------------------------------------------------------------'""");
                        if (!action.equals("x"))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\n[O] YES    [X] NO: ");
                        action = input.nextLine().toLowerCase();
                    } while (!action.equals("x") && !action.equals("o"));
                }
            } while (action.equals("x"));

            selecting = false;

            for (int i = title.get(num - 1).size() - 1; i >= 0; i--)
            {
                if (select.get(i))
                {
                    titleBin.get(num - 1).add(title.get(num - 1).get(i));
                    stocksBin.get(num - 1).add(stocks.get(num - 1).get(i));
                    title.get(num - 1).remove(i);
                    stocks.get(num - 1).remove(i);
                    if (titleBin.get(num - 1).size() > 5)
                    {
                        select.add(false);
                    }
                }
            }

            for (int i = 0; i < select.size(); i++)
            {
                for (int j = 0; j < select.size(); j++)
                {
                    select.set(j, false);
                }
            }

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | D A T A B A S E - DELETE BOOK                              |
                        |------------------------------------------------------------|
                        |             Book has been deleted successfully.            |
                        '------------------------------------------------------------'""");
                if (!action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\n[O] AGAIN    [X] BACK: ");
                action = input.nextLine();
            } while (!action.equals("x") && !action.equals("o"));
            if (action.equals("o"))
            {
                again = action;
            }
            action = "x";
        } while (again.equals("o"));
        return action;
    }

    public static String retrieveBook(int num)
    {
        String action = "x";
        int num2 = 1;
        int startingIndex = 0;
        int endingIndex = 5;
        int count = 0;
        String again = "";

        boolean selected = false;

        do
        {
            again = "";

            if (titleBin.get(num - 1).size() < 1 || title.get(num - 1).size() == 5)
            {
                endingIndex = titleBin.get(num - 1).size();
                while (true)
                {
                    for (int index = 0; index < 100; index++)
                    {
                        System.out.println();
                    }
                    System.out.println("""
                            .------------------------------------------------------------.
                            | I N V E N T O R Y - RETRIEVE BOOK                          |
                            '------------------------------------------------------------'""");
                    generateBinList(num, startingIndex, endingIndex, num2);
                    if (titleBin.get(num - 1).size() < 1)
                    {
                        System.out.println("""
                                .------------------------------------------------------------.
                                |    This feature is currently unavailable. Bin is empty.    |
                                '------------------------------------------------------------'""");
                    } else
                    {
                        System.out.println("""
                                .------------------------------------------------------------.
                                |  This feature is currently unavailable. Inventory is full. |
                                '------------------------------------------------------------'""");
                    }
                    if (!action.equals("x"))
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        return action;
                    }
                }
            }
            do
            {
                for (int i = 0; i < select.size(); i++)
                {
                    for (int j = 0; j < select.size(); j++)
                    {
                        select.set(j, false);
                    }
                }

                if (titleBin.get(num - 1).size() > 5)
                {
                    do
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        binPageLimit = (titleBin.get(num - 1).size() + 4) / 5;
                        System.out.println(".------------------------------------------------------------.\n" +
                                "| I N V E N T O R Y - [ YEAR " + num + " ]                    BACK [X] |");
                        System.out.println("|------------------------------------------------------------'\n" +
                                "|     [ RETRIEVE BOOK ]       [ PAGE: " + num2 + "/" + binPageLimit
                                + " ]     |           |\n" +
                                "'-----------------------------------------------'           |");
                        if (num2 <= binPageLimit)
                        {
                            generateBinList(num, startingIndex, endingIndex, num2);
                        }
                        if (num2 > 1 && num2 == binPageLimit)
                        {
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | [O] SELECT                                             | < |
                                    '------------------------------------------------------------'""");
                        } else if (num2 > 1 && num2 < binPageLimit)
                        {
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | [O] SELECT                                         | < | > |
                                    '------------------------------------------------------------'""");
                        } else
                        {
                            System.out.println("""
                                    .------------------------------------------------------------.
                                    | [O] SELECT                                             | > |
                                    '------------------------------------------------------------'""");
                        }

                        if ((!action.equals("o") && !action.equals("x") && !action.equals("<") && !action.equals(">"))
                                || (action.equals(">") && num2 == binPageLimit) || (action.equals("<") && num2 == 1))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\naction: ");
                        action = input.nextLine().toLowerCase();
                        if (action.equals("x"))
                        {
                            return action;
                        } else if (action.equals(">") && num2 < binPageLimit)
                        {
                            num2++;
                            startingIndex += 5;
                            if (num2 == binPageLimit)
                            {
                                endingIndex = titleBin.get(num - 1).size();
                            } else
                            {
                                endingIndex = startingIndex + 5;
                            }
                        } else if (action.equals("<") && num2 > 1)
                        {
                            num2--;
                            startingIndex -= 5;
                            endingIndex = startingIndex + 5;
                        }
                    } while (!action.equals("o"));
                }

                endingIndex = titleBin.get(num - 1).size();
                action = "x";
                num2 = 1;

                do
                {
                    selecting = true;

                    for (int i = 0; i < 100; i++)
                    {
                        System.out.println();
                    }
                    System.out.println("""
                            .------------------------------------------------------------.
                            | D A T A B A S E - RETRIEVE BOOK                   BACK [X] |
                            |------------------------------------------------------------|""");
                    System.out.println(
                            "|                         YEAR " + num + "                             |\n" +
                                    "'------------------------------------------------------------'");
                    generateBinList(num, startingIndex, endingIndex, num2);
                    System.out.println("""
                            .------------------------------------------------------------.
                            | Select book title by numbers.                  PROCEED [O] |
                            '------------------------------------------------------------'""");
                    if (!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                            && !action.equals("4") && !action.equals("5") || num2 > titleBin.get(num - 1).size())
                    {
                        System.out.println(
                                " |             Invalid action. Please try again.            |\n" +
                                        " '----------------------------------------------------------'");
                    }
                    System.out.print("\naction: ");
                    action = input.nextLine().toLowerCase();
                    if (action.equals("x"))
                    {
                        for (int i = 0; i < select.size(); i++)
                        {
                            for (int j = 0; j < select.size(); j++)
                            {
                                select.set(j, false);
                            }
                        }
                        return action;
                    }
                    if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")
                            || action.equals("5"))
                    {
                        num2 = Integer.parseInt(action);
                        if (num2 > 0 && num2 <= titleBin.get(num - 1).size())
                        {
                            select.set(num2 - 1, !select.get(num2 - 1));
                        }
                    }
                    for (int i = 0; i < titleBin.get(num - 1).size(); i++)
                    {
                        if (select.get(i))
                        {
                            selected = true;
                            break;
                        } else
                        {
                            selected = false;
                        }
                    }
                } while (!action.equals("o") || (num2 < 1 && num2 > titleBin.get(num - 1).size()) || !selected);

                selecting = false;

                if (action.equals("o"))
                {
                    action = "x";

                    do
                    {
                        count = 0;
                        for (int i = 0; i < 100; i++)
                        {
                            System.out.println();
                        }
                        for (int i = 0; i < select.size(); i++)
                        {
                            if (select.get(i))
                            {
                                count++;
                            }
                        }
                        System.out.println("""
                                .------------------------------------------------------------.
                                | D A T A B A S E - RETRIEVE BOOK                            |
                                |------------------------------------------------------------|
                                |                     You have selected...                   |
                                '------------------------------------------------------------'""");
                        System.out.println(
                                " |                                                          |\n" +
                                        " |       [BOOK TITLE] - [" + count + "/5]                 [STOCKS]      |");
                        System.out.println(
                                " |                                                          |");
                        for (int a = 0; a < titleBin.get(num - 1).size(); a++)
                        {
                            if (select.get(a))
                            {
                                System.out.print(" |      - " + titleBin.get(num - 1).get(a));
                                int gap = 38 - titleBin.get(num - 1).get(a).length();
                                for (int i = 0; i < gap; i++)
                                {
                                    System.out.print(" ");
                                }
                                System.out.print(stocksBin.get(num - 1).get(a) + "/3");
                                System.out.println("         |");
                            }
                        }
                        System.out.println(
                                " |                                                          |");
                        System.out.println("""
                                .------------------------------------------------------------.
                                |       Do you wish to continue retrieving this title?       |
                                '------------------------------------------------------------'""");
                        if (!action.equals("x"))
                        {
                            System.out.println(
                                    " |             Invalid action. Please try again.            |\n" +
                                            " '----------------------------------------------------------'");
                        }
                        System.out.print("\n[O] YES    [X] NO: ");
                        action = input.nextLine().toLowerCase();
                    } while (!action.equals("x") && !action.equals("o"));
                }
            } while (action.equals("x"));
            selecting = false;

            for (int i = titleBin.get(num - 1).size() - 1; i >= 0; i--)
            {
                if (select.get(i))
                {
                    title.get(num - 1).add(titleBin.get(num - 1).get(i));
                    stocks.get(num - 1).add(stocksBin.get(num - 1).get(i));
                    titleBin.get(num - 1).remove(i);
                    stocksBin.get(num - 1).remove(i);
                }
            }

            for (int i = 0; i < select.size(); i++)
            {
                for (int j = 0; j < select.size(); j++)
                {
                    select.set(j, false);
                }
            }

            action = "x";

            do
            {
                for (int i = 0; i < 100; i++)
                {
                    System.out.println();
                }
                System.out.println("""
                        .------------------------------------------------------------.
                        | D A T A B A S E - RETRIEVE BOOK                            |
                        |------------------------------------------------------------|
                        |             Book has been deleted successfully.            |
                        '------------------------------------------------------------'""");
                if (!action.equals("x"))
                {
                    System.out.println(
                            " |             Invalid action. Please try again.            |\n" +
                                    " '----------------------------------------------------------'");
                }
                System.out.print("\n[O] AGAIN    [X] BACK: ");
                action = input.nextLine();
            } while (!action.equals("x") && !action.equals("o"));
            if (action.equals("o"))
            {
                again = action;
            }
            action = "x";
        } while (again.equals("o"));
        return action;
    }

    public static void database()
    {
        String action = "a";

        while (true)
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            dashBoardDisplay();
            selectDatabase();
            System.out.println(
                    "            |                                                              |\n" +
                            "            |       BOOK INVENTORY [A]          [B] CUSTOMER LIST          |\n"
                            +
                            "            |                                                              |\n"
                            +
                            "            |--------------------------------------------------------------|\n"
                            +
                            "            |                        [X] MINIMIZE                          |\n"
                            +
                            "            '--------------------------------------------------------------'");
            if (!action.equals("a") && !action.equals("b") && !action.equals("c") && !action.equals("d")
                    && !action.equals("x"))
            {
                System.out.println("                       |   Invalid action. Please try again.   |\n" +
                        "                       '---------------------------------------'");
            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
            switch (action)
            {
                case "a": {
                    bookInventory();
                    break;
                }
                case "b": {
                    customerList();
                    break;
                }
                case "x": {
                    return;
                }
            }
        }
    }

    public static void bookInventory()
    {
        String action = "1";
        int num = 1;

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    .------------------------------------------------------------.
                    | D A T A B A S E - BOOK INVENTORY                  BACK [X] |""");
            switch (num)
            {
                case 1: {
                    System.out.println("""
                            |========.--------.--------.--------.------------------------'
                            | YEAR 1 | YEAR 2   YEAR 3   YEAR 4 |                       |
                            '========'--------'--------'--------'                       |""");
                    break;
                }
                case 2: {
                    System.out.println("""
                            |--------.========.--------.--------.------------------------'
                            | YEAR 1 | YEAR 2 | YEAR 3   YEAR 4 |                       |
                            '--------'========'--------'--------'                       |""");
                    break;
                }
                case 3: {
                    System.out.println("""
                            |--------.--------.========.--------.------------------------'
                            | YEAR 1   YEAR 2 | YEAR 3 | YEAR 4 |                       |
                            '--------'--------'========'--------'                       |""");
                    break;
                }
                case 4: {
                    System.out.println("""
                            |--------.--------.--------.========.------------------------'
                            | YEAR 1   YEAR 2   YEAR 3 | YEAR 4 |                       |
                            '--------'--------'--------'========'                       |""");
                    break;
                }
            }
            System.out.println(
                    " |                                                          |\n" +
                            " |       [BOOK TITLE] - [" + title.get(0).size()
                            + "/5]                 [STOCKS]      |");
            generateBookList(num);
            System.out.println("""
                    .------------------------------------------------------------.
                    |               Select year level by numbers.                |
                    '------------------------------------------------------------'""");
            if (!action.equals("x") && !action.equals("1") && !action.equals("2") && !action.equals("3")
                    && !action.equals("4"))
            {
                System.out.println("  |           Invalid action. Please try again.            |\n" +
                        "  '--------------------------------------------------------'");
            }
            System.out.print("\naction: ");
            action = input.nextLine().toLowerCase();
            if (action.equals("x"))
            {
                return;
            } else if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4"))
            {
                num = Integer.parseInt(action);
            }
        } while (!action.equals("x"));
    }

    public static void customerList()
    {
        String action = "x";

        do
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println();
            }
            System.out.println("""
                    --------------------------------------------------------------------------------------
                    D A T A B A S E - CUSTOMER LIST                                               BACK [X]
                    --------------------------------------------------------------------------------------""");
            if (customerName.size() < 1)
            {
                System.out.println("No customers.");
            } else
            {
                System.out.println("  [NAME]             [DATE OF BIRTH]");
                for (int a = 0; a < customerName.size(); a++)
                {
                    System.out.print((a + 1) + ". " + customerName.get(a));
                    int gap = 19 - customerName.get(a).length();
                    for (int b = 0; b < gap; b++)
                    {
                        System.out.print(" ");
                    }
                    System.out.println(customerBday.get(a));
                }
            }
            System.out.println(
                    "--------------------------------------------------------------------------------------");
            if (!action.equals("o") && !action.equals("x"))
            {
                System.out.println("""
                        Invalid action. Please try again.
                        --------------------------------------------------------------------------------------""");
            }
            System.out.print("action: ");
            action = input.nextLine();
            if (action.equals("x"))
            {
                return;
            }
        } while (!action.equals("o") && !action.equals("x"));
    }

    public static void dashBoardDisplay()
    {
        System.out.println("""
                .------------------------------------------------------------.
                |   ___  __                            _________         _   |
                |  |   |/ _|___    _ _ ____ ______     \\__  ___/___     |_|  |
                |  |     < \\__ \\  / V \\\\__ \\\\_ __ \\ _____|  |  \\__ \\    | |  |
                |  |___|__ (___ \\|_|_| (___ \\/_| \\//____/|__|  (___ \\/\\_| |  |
                |         \\/   \\/     \\/   \\/                      \\/\\____|  |""");

    }

    public static void generateCustomerList()
    {
        System.out.println(
                " |                                                          |");
        System.out.println(
                " |   .--------------------------------------------------.   |\n" +
                        " |   | # | [NAME] - [" + customerName.size()
                        + "/5]             | [DATE OF BIRTH] |   |");
        System.out.println(
                " |   |---|----------------------------|-----------------|   |");
        for (int i = 0; i < customerName.size(); i++)
        {
            if (selecting)
            {
                if (select.get(i))
                {
                    System.out.print(" |   |[" + (i + 1) + "]| - ");
                } else
                {
                    System.out.print(" |   | " + (i + 1) + " | - ");
                }
            } else
            {
                System.out.print(" |   | " + (i + 1) + " | - ");
            }
            for (int b = 0; b < 24; b++)
            {
                if (b >= customerName.get(i).length())
                {
                    System.out.print(" ");
                } else
                {
                    System.out.print(customerName.get(i).charAt(b));
                }
            }
            if (customerName.get(i).length() > 24)
            {
                System.out.print("-");
            } else
            {
                System.out.print(" ");
            }
            System.out.println("|    " + customerBday.get(i) + "   |   |");
        }
        for (int b = customerName.size(); b < 5; b++)
        {
            System.out.println(" |   | " + (b + 1) + " | --- Empty ---              |    --/--/----   |   |");
        }
        System.out.println(
                " |   '--------------------------------------------------'   |");
        System.out.println(
                " |                                                          |");
    }

    public static void generateCustomerBinList()
    {
        System.out.println("CUSTOMER LIST:\n");
        if (customerNameBin.size() < 1)
        {
            System.out.println("No delete history yet.");
        } else
        {
            System.out.println("  [NAME]             [DATE OF BIRTH]");
            for (int a = 0; a < customerNameBin.size(); a++)
            {
                System.out.print((a + 1) + ". " + customerNameBin.get(a));
                int gap = 19 - customerNameBin.get(a).length();
                for (int b = 0; b < gap; b++)
                {
                    System.out.print(" ");
                }
                System.out.println(customerBdayBin.get(a));
            }
        }
        System.out.println(
                "--------------------------------------------------------------------------------------");
    }

    public static void generateBookList(int num)
    {
        num -= 1;

        System.out.println(
                " |                                                          |");
        System.out.println(
                " |   .--------------------------------------------------.   |\n" +
                        " |   | # | [BOOK TITLE] - [" + title.get(num).size()
                        + "/5]              | [STOCKS] |   |");
        System.out.println(
                " |   |---|-----------------------------------|----------|   |");
        for (int i = 0; i < title.get(num).size(); i++)
        {
            if (selecting)
            {
                if (select.get(i))
                {
                    System.out.print(" |   |[" + (i + 1) + "]| - ");
                } else
                {
                    System.out.print(" |   | " + (i + 1) + " | - ");
                }
            } else
            {
                System.out.print(" |   | " + (i + 1) + " | - ");
            }
            for (int b = 0; b < 31; b++)
            {
                if (b >= title.get(num).get(i).length())
                {
                    System.out.print(" ");
                } else
                {
                    System.out.print(title.get(num).get(i).charAt(b));
                }
            }
            if (title.get(num).get(i).length() > 31)
            {
                System.out.print("-");
            } else
            {
                System.out.print(" ");
            }
            System.out.println("|   " + stocks.get(num).get(i) + "/3    |   |");
        }
        int empty = 5 - title.get(num).size();
        for (int b = (5 - empty); b < 5; b++)
        {
            System.out.println(" |   | " + (b + 1) + " | --- Empty ---                     |   -/-    |   |");
        }
        System.out.println(
                " |   '--------------------------------------------------'   |");
        System.out.println(
                " |                                                          |");
    }

    public static void generateBinList(int num, int startingIndex, int endingIndex, int num2)
    {
        num -= 1;

        System.out.println(
                " |                                                          |");
        System.out.println(
                " |   .--------------------------------------------------.   |\n" +
                        " |   | # | [BOOK TITLE] - [" + titleBin.get(num).size() + "]                | [STOCKS] |   |");
        System.out.println(
                " |   |---|-----------------------------------|----------|   |");
        for (int i = startingIndex; i < endingIndex; i++)
        {
            if (selecting)
            {
                if (select.get(i))
                {
                    System.out.print(" |   |[" + (i + 1) + "]| - ");
                } else
                {
                    System.out.print(" |   | " + (i + 1) + " | - ");
                }
            } else
            {
                System.out.print(" |   | " + (i + 1) + " | - ");
            }
            int gap = 31;
            for (int b = 0; b < gap; b++)
            {
                if (b >= titleBin.get(num).get(i).length())
                {
                    System.out.print(" ");
                } else
                {
                    System.out.print(titleBin.get(num).get(i).charAt(b));
                }
            }
            if (titleBin.get(num).get(i).length() > 31)
            {
                System.out.print("-");
            } else
            {
                System.out.print(" ");
            }
            System.out.println("|   " + stocksBin.get(num).get(i) + "/3    |   |");
        }
        if (num2 == binPageLimit)
        {
            for (int b = titleBin.get(num).size(); b < (binPageLimit * 5); b++)
            {
                if ((b + 1) >= 10)
                {
                    System.out.println(" |   | " + (b + 1) + " | --- Empty ---                    |   -/-    |   |");
                } else
                {
                    System.out.println(" |   | " + (b + 1) + " | --- Empty ---                     |   -/-    |   |");
                }
            }
        }
        System.out.println(
                " |   '--------------------------------------------------'   |");
        System.out.println(
                " |                                                          |");
    }

    public static void displaySelected(int num, int count)
    {
        num -= 1;

        if (buyselecting)
        {
            System.out.println(
                    " |                                                          |\n" +
                            " |   .--------------------------------------------------.   |\n" +
                            " |   | [BOOK TITLE] - [" + count + "]            | [QTY] | [STOCKS] |   |\n" +
                            " |   |-------------------------------|-------|----------|   |");
            for (int i = 0; i < title.get(num).size(); i++)
            {
                if (select.get(i))
                {
                    System.out.print(" |   | - ");
                    for (int b = 0; b < 27; b++)
                    {
                        if (b >= title.get(num).get(i).length())
                        {
                            System.out.print(" ");
                        } else
                        {
                            System.out.print(title.get(num).get(i).charAt(b));
                        }
                    }
                    if (title.get(num).get(i).length() > 31)
                    {
                        System.out.print("-");
                    } else
                    {
                        System.out.print(" ");
                    }
                    System.out
                            .println("|   " + selectedQuantity[i] + "   |   " + stocks.get(num).get(i) + "/3    |   |");
                }
            }
        } else
        {
            System.out.println(
                    " |                                                          |\n" +
                            " |   .--------------------------------------------------.   |\n" +
                            " |   | # | [BOOK TITLE] - [" + count + "/5]              | [STOCKS] |   |\n" +
                            " |   |---|-----------------------------------|----------|   |");
            for (int i = 0; i < select.size(); i++)
            {
                if (select.get(i))
                {
                    System.out.print(" |   | " + (i + 1) + " | - ");
                    for (int b = 0; b < 31; b++)
                    {
                        if (b >= title.get(num).get(i).length())
                        {
                            System.out.print(" ");
                        } else
                        {
                            System.out.print(title.get(num).get(i).charAt(b));
                        }
                    }
                    if (title.get(num).get(i).length() > 31)
                    {
                        System.out.print("-");
                    } else
                    {
                        System.out.print(" ");
                    }
                    System.out.println("|   " + stocks.get(num).get(i) + "/3    |   |");
                }
            }
        }
        System.out.println(
                " |   '--------------------------------------------------'   |");
        for (int i = count - 1; i <= 4; i++)
        {
            System.out.println(
                    " |                                                          |");
        }

    }

    public static void displayCheckOut()
    {

    }

    public static void dashBoardTabs(String tab)
    {
        for (int i = 0; i < 13; i++)
        {
            if (i >= loggedAcc.length())
            {
                System.out.print(" ");
            } else
            {
                System.out.print(loggedAcc.charAt(i));
            }
        }
        if (loggedAcc.length() > 13)
        {
            System.out.print("-");
        } else
        {
            System.out.print(" ");
        }
        switch (tab)
        {
            case "a": {
                System.out.println("||=====[A]====='-----[B]-----'-----[C]-----|");
                buyBooksDisplay();
                break;
            }
            case "b": {
                System.out.println("||-----[A]-----'=====[B]====='-----[C]-----|");
                inventoryDisplay();
                break;
            }
            case "c": {
                System.out.println("||-----[A]-----'-----[B]-----'=====[C]=====|");
                customersDisplay();
                break;
            }
            case "v": {
                System.out.println("||-----------------------------------------|");
                settingsDisplay();
                break;
            }
        }
    }

    public static void buyBooksDisplay()
    {
        System.out.print(" |---------------||         .--___           ___--.	    |\n" +
                " | Books:        ||        /  -___---_   _---___-  \\ 	    |\n" +
                " | - " + totalBookCount);
        if (totalBookCount > 9)
        {
            System.out.print("/20       ||       /  --___--  -.-  --___--  \\	    |\n");
        } else
        {
            System.out.print("/20        ||       /  --___--  -.-  --___--  \\	    |\n");
        }
        System.out.print(" | Customers:    ||      /-----___--   |   --___-----\\	    |\n" +
                " | - " + customerName.size()
                + "/5         ||      '-----___---_ | _---___-----'      |\n" +
                " | Orders today: ||               ---_-|-_---   	    |\n" +
                " | - 8           ||	              -:-		    |\n");
    }

    public static void inventoryDisplay()
    {
        System.out.print(" |---------------||          __.--.__  ...                  |\n" +
                " | Books:        ||         | .____  |'.. ''.. 	            |\n" +
                " | - " + totalBookCount);
        if (totalBookCount > 9)
        {
            System.out.print("/20       ||         | .____  |'..''...|	            |\n");
        } else
        {
            System.out.print("/20        ||         | .____  |'..''...|	            |\n");
        }
        System.out.print(" | Customers:    ||         | .____  |'..'''  |	            |\n" +
                " | - " + customerName.size() + "/5         ||         |________|  |     |             |\n" +
                " | Orders today: ||               '..   |   ..'	            |\n" +
                " | - 8           ||	             ''.|.''                |\n");
    }

    public static void customersDisplay()
    {
        System.out.print(" |---------------||                .........                |\n" +
                " | Books:        ||             .''  .....  ''.	            |\n" +
                " | - " + totalBookCount);
        if (totalBookCount > 9)
        {
            System.out.print("/20       ||            .'   :::::::   '.            |\n");

        } else
        {
            System.out.print("/20        ||            .'   :::::::   '.	          |\n");
        }
        System.out.print(" | Customers:    ||            :    :::::::    :            |\n" +
                " | - " + customerName.size() + "/5         ||            :     :::::     :            |\n" +
                " | Orders today: ||             : .:::::::::. :	            |\n" +
                " | - 8           ||	         '':::::::::''              |\n");
    }

    public static void settingsDisplay()
    {
        System.out.print(" |---------------||                                         |\n" +
                " | Books:        ||    [A] EDIT ACCOUNT                     |\n" +
                " | - " + totalBookCount);
        if (totalBookCount > 9)
        {
            System.out.print("/20       ||    [B] ABOUT DEVELOPERS                 |\n");

        } else
        {
            System.out.print("/20       ||    [B] ABOUT DEVELOPERS                 |\n");
        }
        System.out.print(" | Customers:    ||    [C] LOG OUT                          |\n" +
                " | - " + customerName.size() + "/5         ||                                         |\n" +
                " | Orders today: ||                                         |\n" +
                " | - 8           ||	                          	    |\n");
    }
}
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------WELCOME TO 8 PUZZLE------------");
        char x = 'y';

        // Loop for repeat operation if user want
        while (x == 'y' || x == 'Y')
        {
            System.out.print("Please Enter Initial state : ");
            String InitialState = sc.next();
            int check = 1;

            // Loop for repeat operation with same Initial State if user want

            char xx = 'y';
            while (xx == 'y' || xx == 'Y')
            {

                // Check if length of Initial State is 9

                if (InitialState.length() == 9)
                {

                    // Check that Initial State doesn't have duplicated numbers

                    char[] carry = InitialState.toCharArray();
                    System.out.println("The Initial State is :" + InitialState);
                    System.out.print("Duplicate Characters in above string are : ");
                    for (int i = 0; i < InitialState.length(); i++) {
                        for (int j = i + 1; j < InitialState.length(); j++)
                        {
                            if (carry[i] == carry[j])
                            {
                                System.out.print(carry[j] + " ");
                                x = 'y';
                                check = 0;
                                break;
                            }
                        }
                    }
                    System.out.println();

                    // Check that Initial State is only from 0 to 8 and doesn't have strange letters

                    if (check == 1) {
                        for (char c : InitialState.toCharArray()) {
                            if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '0') {
                                System.out.println(c + " is TRUE");
                            }
                            // access each character
                            else {
                                System.out.println(c + " is ERROR");
                                x = 'y';
                                check = 0;
                            }
                        }
                    }
                } else {
                    System.out.println("ERROR");
                    x = 'y';
                    check = 0;
                }


                // After checking Initial State is qualified

                if (InitialState.length() == 9 && check == 1) {

                    // Main Interface

                    System.out.println("Choose : \n1. DFS\n2. BFS\n3. A*");
                    System.out.print("Please Choose one type of search algorithms : ");
                    int choose = sc.nextInt();

                    // Algorithms

                    if (choose == 1) {
                        System.out.println("Depth First Searching (DFS)");
                        DFS.dfs(InitialState);
                    } else if (choose == 2) {
                        System.out.println("Breadth first searching (BFS)");
                        BFS.bfs(InitialState);
                    } else if (choose == 3) {
                        System.out.println("Choose : \n1. Euclidean\n2. Manhattan");
                        System.out.print("Please Enter your Response : ");
                        int chooseA = sc.nextInt();
                        if (chooseA == 1) {
                            EuclideanAStar.AStarEuclidean(InitialState);
                        } else if (chooseA == 2) {
                            ManhattanAStar.AStarManhattan(InitialState);
                        }
                    }

                    // Ask for Another Operations

                    System.out.println("Do you want another operation with same Initial State (y/n)");
                    xx = sc.next().charAt(0);
                    if (xx !='y')
                    {
                        System.out.println("Do you want another operation (y/n)");
                        x = sc.next().charAt(0);
                    }
                }

                // If any requirement is not qualified

                else
                {
                    System.out.println("Initial State must be 9 numbers from 0 to 8 and not duplicated \nPlease Try again ");
                    System.out.print("Please Enter Initial state : ");
                    InitialState = sc.next();
                    check = 1;
                }
            }

            // THE END

            System.out.println("------------GOOD BYE------------");
        }
    }
}

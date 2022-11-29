import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Work_Space
{
    //"printBoard" method to print the puzzle in the console

    public static void printBoard(char[][] board)
    {
    System.out.println("|"+board[0][0] + "|" + board[0][1] + "|" + board[0][2]+"|");
    System.out.println("|"+board[1][0] + "|" + board[1][1] + "|" + board[1][2]+"|");
    System.out.println("|"+board[2][0] + "|" + board[2][1] + "|" + board[2][2]+"|");
    }

    //"toBoardArray" method to return the string puzzle to 2d array

    public static char[][] toBoardArray(String stateToExplore)
    {
        char[][] board = new char[3][3];
        board[0][0] = stateToExplore.charAt(0);
        board[0][1] = stateToExplore.charAt(1);
        board[0][2] = stateToExplore.charAt(2);
        board[1][0] = stateToExplore.charAt(3);
        board[1][1] = stateToExplore.charAt(4);
        board[1][2] = stateToExplore.charAt(5);
        board[2][0] = stateToExplore.charAt(6);
        board[2][1] = stateToExplore.charAt(7);
        board[2][2] = stateToExplore.charAt(8);
        return board;
    }

    //"toBoardString" method to return the 2d array puzzle to string

    public static String toBoardString(char[][] board)
    {
        return "" + board[0][0] + board[0][1] + board[0][2]
                + board[1][0] + board[1][1] + board[1][2]
                + board[2][0] + board[2][1] + board[2][2];
    }

    //Print the path to goal and all wanted information
    public static void print (Stack<char[][]> printParent, int count, Stack<String> printMoves, Queue<String> printMoves1,
                              ArrayList<char[][]> pathList, ArrayList<String> moveList, int Depth, double start, Queue<Node>NodesNum)
    {
        while (!printParent.isEmpty())
        {
            count++;
            System.out.print("Move "+count+" to Goal: ");
            printMoves1.add(printMoves.peek());
            System.out.println(printMoves.peek());
            moveList.add(printMoves.peek());
            System.out.println(printMoves.pop());
            pathList.add(printParent.peek());
            Work_Space.printBoard(printParent.pop());
        }
        System.out.println("Path To Goal:"+ printMoves1 +"\nCost of path= "+count);
        System.out.println("Depth= "+Depth);
        System.out.println("Nodes Expanded= "+NodesNum.size());
        double end = System.nanoTime();
        double execution = end - start;
        System.out.println("Execution time of Euclidean distance: " + execution/100000000 + " seconds");
    }

    //"GoalX" method to get the index of x for the chosen element in the goal

    public static int GoalX( char Num1)
    {
        String goal="012345678";
        char [][] Goal=toBoardArray(goal);
        int len = Goal.length;
        for ( int i=0;i<len;i++)
        {
            for (int j = 0; j < len; j++)
            {
                if (Goal[i][j] == Num1)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    //"GoalY" method to get the index of Y for the chosen element in the goal

    public static int GoalY( char Num2)
    {
        String goal="012345678";
        char [][] Goal=toBoardArray(goal);
        int len = Goal.length;
        for (char[] chars : Goal)
        {
            for (int j = 0; j < len; j++)
            {
                if (chars[j] == Num2)
                {
                    return j;
                }
            }
        }
        return -1;
    }
}
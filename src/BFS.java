import java.util.*;

public class BFS {

    // Path to goal and move list
    static ArrayList<char[][]> pathList = new ArrayList<>();
    static ArrayList<String> moveList= new ArrayList<>();

    // BFS algorithm
    public static void bfs(String InitialState)
    {
        // Start time
        double start = System.nanoTime();

        // Initialize Goal

        String goal = "012345678";
        Node goal1 = new Node(goal,null,null);
        boolean Solution = false;

        //Queue to work with BFS

        Queue<Node> Frontier = new LinkedList();

        //Declaring all data structure we will use in the search

        Node InitialStateNode=new Node(InitialState,null,null);
        Frontier.add(InitialStateNode);
        HashSet<String> Explored = new HashSet();
        Queue<Node>NodesNum=new LinkedList<>();
        Stack<char[][]> printParent =new Stack<>();
        Stack<String> printMoves =new Stack<>();
        Queue<String> printMoves1 =new LinkedList<>();

        //Start searching

        while (!Frontier.isEmpty())
        {
            int Depth=0;
            Node nodeToExplore =Frontier.poll() ;
            Explored.add(nodeToExplore.value);
            char[][] board = Work_Space.toBoardArray(nodeToExplore.value);
            System.out.println("Exploring: ");
            Work_Space.printBoard(board);

            //Check if we reached the goal

            if (nodeToExplore.value.equals(goal1.value))
            {
                int count=0;
                System.out.println("arrived!");
                Solution = true;
                System.out.println("_______________\nPath To Goal:\nInitial State");
                Work_Space.printBoard(Work_Space.toBoardArray(InitialState));

                //Get nodes parents

                while (nodeToExplore.parent!=null)
                {
                    char [][] o=Work_Space.toBoardArray(nodeToExplore.value);
                    printParent.add(o);
                    printMoves.add(nodeToExplore.Move);
                    nodeToExplore=nodeToExplore.parent;
                    Depth= printParent.size();
                }

                //Print the path to goal and all wanted information

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
                System.out.println("Execution time of Euclidean distance: " + execution/100000000 + " seconds");                break;
            }

            // Getting the index of 0 in the puzzle

            int zeroIndex = nodeToExplore.value.indexOf('0');
            int zeroY = zeroIndex / 3;
            int zeroX = zeroIndex % 3;

            // Getting 1st neighbour

            if (zeroX != 0)
            {
                board[zeroY][zeroX] = board[zeroY][zeroX - 1];
                board[zeroY][zeroX - 1] = '0';
                String boardString = Work_Space.toBoardString(board);

                //Adding the move in the tree

                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,1,nodeToExplore,"Left");
                    Frontier.add(nodeToExplore.node1);
                    Explored.add(nodeToExplore.node1.value);
                }

                // Undo the last move to reuse it

                board[zeroY][zeroX - 1] = board[zeroY][zeroX];
                board[zeroY][zeroX] = '0';
            }

            // Getting 2nd neighbour

            if (zeroY != 0)
            {
                board[zeroY][zeroX] = board[zeroY - 1][zeroX];
                board[zeroY - 1][zeroX] = '0';

                //Adding the move in the tree

                String boardString = Work_Space.toBoardString(board);
                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,2,nodeToExplore,"Up");
                    Frontier.add(nodeToExplore.node2);
                    Explored.add(nodeToExplore.node2.value);
                }

                // Undo the last move to reuse it

                board[zeroY - 1][zeroX] = board[zeroY][zeroX];
                board[zeroY][zeroX] = '0';
            }

            // Getting 3rd neighbour

            if (zeroY != 2)
            {
                board[zeroY][zeroX] = board[zeroY + 1][zeroX];
                board[zeroY + 1][zeroX] = '0';

                // Adding the move in the tree

                String boardString = Work_Space.toBoardString(board);
                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,3,nodeToExplore,"Down");
                    Frontier.add(nodeToExplore.node3);
                    Explored.add(nodeToExplore.node3.value);
                }

                // Undo the last move to reuse it

                board[zeroY + 1][zeroX] = board[zeroY][zeroX];
                board[zeroY][zeroX] = '0';
            }

            // Getting 4th neighbour

            if (zeroX != 2)
            {
                board[zeroY][zeroX] = board[zeroY][zeroX + 1];
                board[zeroY][zeroX + 1] = '0';

                // Adding the move in the tree

                String boardString = Work_Space.toBoardString(board);
                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,4,nodeToExplore,"Right");
                    Frontier.add(nodeToExplore.node4);
                    Explored.add(nodeToExplore.node4.value);
                }

                // Undo the last move to reuse it

                board[zeroY][zeroX + 1] = board[zeroY][zeroX];
                board[zeroY][zeroX] = '0';
            }
            NodesNum.add(nodeToExplore.parent);
        }

        //Check if we didn't find a solution

        if (!Solution)
        {
            System.out.println("Not solvable");
        }
    }
}
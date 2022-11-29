import java.util.*;
public class ManhattanAStar
{

    //"Manhattan" calculate Manhattan Distance

    public static int Manhattan(int currentX,int currentY,int goalX,int goalY)
    {
        return Math.abs(currentX-goalX)+Math.abs(currentY-goalY);
    }

    //"GetHManhattan" method to get the heuristic with Manhattan Distance

    public static int GetHManhattan(char[][] State)
    {
        int H=0;
        for (int i=0;i<State.length;i++)
        {
            for (int j=0;j<State.length;j++)
            {
                char current=State[i][j];
                int goalX=Work_Space.GoalX(current);
                int goalY=Work_Space.GoalY(current);
                H+=Manhattan(i, j,goalX,goalY);
            }
        }return H;
    }

    // Path to goal and move list
    static ArrayList<char[][]> pathList = new ArrayList<>();
    static ArrayList<String> moveList= new ArrayList<>();

    public static void AStarManhattan(String InitialState)
    {

        // Start time
        double start = System.nanoTime();

        // Initialize Goal

        String goal = "012345678";
        Node goal1 = new Node(goal,null,null);
        boolean Solution = false;

        //Use the Mao.entry to work with A*

        PriorityQueue<Map.Entry<Node, Integer>> Nodeq= new PriorityQueue<>(Map.Entry.comparingByValue());

        //Declaring all data structure we will use in the search

        Node InitialStateNode=new Node(InitialState,null,null);
        int currentH = GetHManhattan(Work_Space.toBoardArray(InitialState));
        Nodeq.add(new AbstractMap.SimpleEntry<>(InitialStateNode, currentH));
        HashSet<String> Explored = new HashSet();
        Stack<char[][]> printParent =new Stack<>();
        Stack<String>printMoves=new Stack<>();
        Queue<String>printMoves1=new LinkedList<>();
        Queue<Node>NodesNum=new LinkedList<>();

        //Start searching

        while (!Nodeq.isEmpty())
        {
            Node nodeToExplore=Nodeq.poll().getKey();
            int Depth=0;
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
                System.out.println("Execution time of Euclidean distance: " + execution/100000000 + " seconds");
                break;
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
                    int currentH1=GetHManhattan(Work_Space.toBoardArray(nodeToExplore.node1.value));
                    Nodeq.add(new AbstractMap.SimpleEntry<>(nodeToExplore.node1,currentH1));
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
                String boardString = Work_Space.toBoardString(board);

                //Adding the move in the tree

                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,2,nodeToExplore,"Up");
                    int currentH2=GetHManhattan(Work_Space.toBoardArray(nodeToExplore.node2.value));
                    Nodeq.add(new AbstractMap.SimpleEntry<>(nodeToExplore.node2,currentH2));
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
                String boardString = Work_Space.toBoardString(board);

                // Adding the move in the tree

                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,3,nodeToExplore,"Down");
                    int currentH3=GetHManhattan(Work_Space.toBoardArray(nodeToExplore.node3.value));
                    Nodeq.add(new AbstractMap.SimpleEntry<>(nodeToExplore.node3,currentH3));
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
                String boardString = Work_Space.toBoardString(board);

                // Adding the move in the tree

                if (!Explored.contains(boardString))
                {
                    Tree.add(nodeToExplore,boardString,4,nodeToExplore,"Right");
                    int currentH4=GetHManhattan(Work_Space.toBoardArray(nodeToExplore.node4.value));
                    Nodeq.add(new AbstractMap.SimpleEntry<>(nodeToExplore.node4,currentH4));
                }

                    // Undo the last move to reuse it

                    board[zeroY][zeroX + 1] = board[zeroY][zeroX];
                    board[zeroY][zeroX] = '0';
            }
                for (int i=0;i< Nodeq.size();i++)
                {
                    currentH=GetHManhattan(Work_Space.toBoardArray(nodeToExplore.value));
                    assert Nodeq.peek() != null;
                    int newH=GetHManhattan(Work_Space.toBoardArray(Nodeq.peek().getKey().value));
                    if (newH<currentH)
                    {
                        currentH=newH;
                    }
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
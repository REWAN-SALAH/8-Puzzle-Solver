public class Tree
{
    static Node root;

    //tree constructor that return the root with null
    Tree() {
        root = null;
    }

    //"add" method to add the Node with its data in the tree
    public static void add(Node root,String board,int number, Node parent,String move)
    {
        if (number==1)
        {
            root.node1=new Node(board,parent,move);
        }
        else if (number==2)
        {
            root.node2=new Node(board,parent,move);
        }
        else if (number==3)
        {
            root.node3=new Node(board,parent,move);
        }
        else if (number==4)
        {
            root.node4=new Node(board,parent,move);
        }
    }
}
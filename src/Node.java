public class Node
{
    //Declaring all the nodes in the tree and the puzzle that will enter the tree

    String value;
    Node node1;
    Node node2;
    Node node3;
    Node node4;
    Node parent;
    String Move;

    //Making Node constructor to receive all the data about the node

    public Node(String value,Node parent,String Move)
    {
        this.value = value;
        this.node1 = null;
        this.node2 = null;
        this.node3 = null;
        this.node4 = null;
        this.parent=parent;
        this.Move=Move;
    }

}
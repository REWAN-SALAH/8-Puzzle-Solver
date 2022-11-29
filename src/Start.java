import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

// Frame To Take Initial State
public class Start extends JFrame implements ActionListener
{
    // Add Text filed and Button
    JFrame frame= new JFrame("8 - PUZZLES");
    JLabel l1 ;
    static JTextField t1;
    JButton bb;
    Color light_Grey = new Color(188,176,161);
    Color c1 = new Color(152,88,54);
    Color c2 = new Color(72,35,23);
    Start()
    {
        // Set Locations
        frame.setSize(300,200);
        frame.setLayout(null);
        l1 = new JLabel("Initial State");
        l1.setForeground(c2);
        l1.setBounds(100,10,200,20);
        l1.setFont(new Font("r",Font.BOLD,20));
        frame.add(l1);
        t1= new JTextField();
        t1.setBounds(80,50,150,30);
        t1.addKeyListener(new KeyAdapter()
        {
            // Only 9 Numbers
            @Override
            public void keyTyped(KeyEvent e)
            {
                super.keyTyped(e);
                if (t1.getText().length()>=9)
                {
                    e.consume();
                }
            }
        });
        frame.add(t1);
        bb=new JButton("Start");
        bb.setBackground(c1);
        bb.setForeground(light_Grey);
        bb.addActionListener(new ActionListener()
        {
            // Open Another Frame
            @Override
            public void actionPerformed(ActionEvent e)
            {
                puzzle.setInit(Work_Space.toBoardArray(t1.getText()));
                new puzzle();
                dispose();
            }
        });
        bb.setBounds(120,90,70,30);
        bb.setFont(new Font("r",Font.BOLD,15));
        frame.add(bb);
        frame.getContentPane().setBackground(light_Grey);
        // Set frame on the Center
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x,y);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
    // Start Program
    public static void main(String[] args)
    {
        new Start();
    }
    // Call Algorithms
    public static void callBFS() throws IOException
    {
        BFS.bfs(t1.getText());
        puzzle.pathList = BFS.pathList;
        puzzle.moveList = BFS.moveList;
    }
    public static void callDFS() throws IOException
    {
        DFS.dfs(t1.getText());
        puzzle.pathList = DFS.pathList;
        puzzle.moveList = DFS.moveList;
    }
    public static void callManhattan() throws IOException
    {
        ManhattanAStar.AStarManhattan(t1.getText());
        puzzle.pathList = ManhattanAStar.pathList;
        puzzle.moveList = ManhattanAStar.moveList;
    }
    public static void callEuc() throws IOException
    {
        EuclideanAStar.AStarEuclidean(t1.getText());
        puzzle.pathList = EuclideanAStar.pathList;
        puzzle.moveList = EuclideanAStar.moveList;
    }
}
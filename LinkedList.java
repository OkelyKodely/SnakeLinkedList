import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author d
 */
public class LinkedList
{

    Bag bag = new Bag();

    JFrame j = new JFrame();
    JPanel p = new JPanel();
    Graphics g = null;

    Node node;
    Node readOnlyCursor = new Node();
    Node cur = new Node();
    Node cursor = new Node();

    int i = 1;

    String direction = "RIGHT";

    int x = 6;
    int y = 5;        

    boolean thKeyPressed = false;

    public class Ball {
        int x;
        int y;
    }

    public class Bag {
    
        ArrayList<Ball> balls = new ArrayList<Ball>();
        
        public Bag() {
            Random r = new Random();
            for(int i=0; i<10; i++) {
    
                Ball ball = new Ball();
                ball.x = r.nextInt(32);
                ball.y = r.nextInt(26);
    
                balls.add(ball);
            }
        }
    }

    class Node
    {
        Node node;
        Object obj;
    }


    public  LinkedList () 
    {

        j.setLayout(null);
        p.setLayout(null);
        j.setBounds(0, 0, 1000, 800);
        p.setBounds(j.getBounds());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

        g = p.getGraphics();

        node = new Node();
        node.obj = "" + 5 + ",5";
        cur = node;
        recurConstruct(1, node, 5);

        move();

    }

    public void move() {
        j.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

                thKeyPressed = true;

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    direction = "UP";
                    y--;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    direction = "DOWN";
                    y++;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    direction = "LEFT";
                    x--;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    direction = "RIGHT";
                    x++;
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

        Thread t = new Thread() {
            public void run() {
                while(true) {
                    //Clear Screen
                    g.setColor(Color.red);

                    g.fillRect(0, 0, 1000, 800);;
                    //Operateion .
                    i=1;deleteNode(5);
                    i=1;addNode(0, "" + (x) + "," + (y) + "");
                    traverseLinkedList(node);
                    drawBag();
                    //Put a space inBetween traversals
                    System.out.println("");
                    try {
                        Thread.sleep(1000);
                        if(thKeyPressed==false)
                        {
                            if(direction.equals("UP"))
                            {
                                y--;
                            }
                            if(direction.equals("DOWN"))
                            {
                                y++;
                            }
                            if(direction.equals("LEFT"))
                            {
                                x--;
                            }
                            if(direction.equals("RIGHT"))
                            {
                                x++;
                            }
                        } else
                        {
                            thKeyPressed = false;
                        }
                    } catch(Exception e) {}
                }
            }
        };

        t.start();
    }

    public void recurConstruct(int i, Node node, int a) {
        if(i < a+1) {
            node.node = new Node();
            node.node.obj = "" + (6 - i) + ",5";
            recurConstruct(i + 1,node.node, a);
        }
    }

    public Node update(int iii, int ii, Node node) {
        if(iii < ii)
            return update(iii+1, ii, node.node);
        i = iii;
        return node;
    }

    public Node updateDil(int iii, int ii, Node node) {
        if(iii < ii-1)
            return updateDil(iii+1, ii, node.node);
        i = iii;
        return node;
    }

    public void deleteNode(int ii) {

        Node nBefore = node;
        cursor.node = updateDil(i, ii, cur.node);
        if(i-1 >= ii-1) {
            cursor.node = cursor.node.node;
            nBefore.node = null;
        } else if(i-1 == ii - 2) {
            if(cursor.node.node.node != null) {
                cursor.node.node = cursor.node.node.node;
                cursor.node = null;
            } else {
                cursor.node.node = null;
            }
        }
    }

    public void addNode(int ii, String g) {

        Node nod = new Node();
        nod.obj = g;
        Node nBefore = node;
        if(ii == 0) {
            nod.node = cur.node;
            node.node = nod;
        }
        if(ii != 0) {
            cursor.node = update(i, ii, cur.node);
            if(i-1 >= ii) {
                nod.node = cursor.node;
                cursor = nod;
            } else if(i-1 == ii - 1) {
                if(cursor.node.node != null) {
                    nod.node = cursor.node.node;
                    cursor.node.node = nod;
                } else {
                    nod.node = null;
                    cursor.node.node = nod;
                }
            }
        }
    }

    public void drawBag()

    {

        for(int i=0; i<bag.balls.size(); i++) {

            if(bag.balls.get(i).x == Integer.parseInt(node.node.obj.toString().substring(0,node.node.obj.toString().indexOf(","))) &&
                bag.balls.get(i).y == Integer.parseInt(node.node.obj.toString().substring(node.node.obj.toString().indexOf(",")+1,node.node.obj.toString().length()))) {

                ///i=1;addNode(0, "" + (x) + "," + (y) + "");
                bag.balls.remove(bag.balls.get(i));
            }
            else {

                g.setColor(new Color(255, 220, 255));
                g.fillRect(bag.balls.get(i).x*30, bag.balls.get(i).y*30, 30, 30);
            }

        }

    }

    public Node traverseLinkedList(Node n)
    {

        if(n.node != null) {
            try
            {
                StringTokenizer st = new StringTokenizer(n.node.obj.toString(), ",");
                String a = st.nextToken();
                String b = st.nextToken();
                if(Integer.parseInt(a) >= 33)
                    System.exit(0);
                else if(Integer.parseInt(a) <= 0)
                    System.exit(0);
                else if(Integer.parseInt(b) >= 27)
                    System.exit(0);
                else if(Integer.parseInt(b) <= 0)
                    System.exit(0);

                g.setColor(new Color(255, 255, 55));
                g.drawOval(Integer.parseInt(a)*30, Integer.parseInt(b)*30, 30, 30);

                println(n.node);
            } catch(Exception e)
            {

            }

            Node ne = traverseLinkedList(n.node);

            return null;
        }

        return n;
    }

    public void println(Node cursor1) {

        System.out.println("Node (obj): " + 
            cursor1.obj.toString());
    }

    public static void main(String[] args) {

        LinkedList ll = new LinkedList();
    }
}
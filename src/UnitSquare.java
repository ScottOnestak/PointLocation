import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class UnitSquare extends JPanel{
	
	//global variables
    private ArrayList<LineSegment> lines;
    public Point p0;
    public Point p1;
    private BinaryTree tree;
    private boolean firstPoint;
    final int xStart = 20;
    final int yStart = 20;
    final int width = 300;
    double drawLineX1, drawLineX2, drawLineY1, drawLineY2;
    LineSegment drawLine;
    private Color[] c1 ={Color.red, Color.blue, Color.CYAN, Color.yellow, new Color(50,225,50)};

    //constructor
    public UnitSquare()
    {
        lines = new ArrayList<LineSegment>();
        p0 = null;
        p1 = null;
        firstPoint = false;  
    }

    //addLines method...contrary to the drawing on the project, the origin for GUI is the upper left corner, not the lower left
    //therefore, need to flip the y-values for the regions to be drawn correctly
    public void addLine(LineSegment l) {

    	//flip the y-values and create the line segment to draw
    	drawLineX1 = l.p1.x;
    	drawLineY1 = 1-l.p1.y;
    	drawLineX2 = l.p2.x;
    	drawLineY2 = 1-l.p2.y;
    	
    	drawLine = new LineSegment(drawLineX1, drawLineY1, drawLineX2, drawLineY2);
    	
        lines.add(drawLine);
        repaint();
    }

    //set the tree
    public void setTree(BinaryTree tree) {
        this.tree = tree;
    }

    //check the points and print them out
    public void checkPoints(){
        if(p0 != null && p1 != null){
            String s;
            LineSegment res1 = tree.search(tree.getNode(), p0, p1);
            if(res1 == null)
                s = ("Points "+ p0+" and "+ p1 +" are in the same region");
            else
                s = ("Points "+p0+" and "+p1+" are separated by line " + res1.p1.x + " " + res1.p1.y + " " + res1.p2.x + " " + res1.p2.y);
            System.out.println(s);
        }
    }

    //add the points based on the mouse listener coordinates
    public void addPoint(MouseEvent e) {
        if(e.getX() > width+xStart || e.getY() > width+yStart || e.getX() < xStart || e.getY() < yStart)
            return;  // not a valid point

        // If p0 is selected, then select p1. Otherwise select the opposite of the last one selected. 
        if(p0 != null && p1 == null)
            firstPoint = false;
        else
            firstPoint = !firstPoint;

        if(firstPoint){
            p0 = new Point(((double)e.getX()-xStart)/width, ((double)e.getY()-yStart)/width);
            p1=null;
        } else
            p1 = new Point(((double)e.getX()-xStart)/width, ((double)e.getY()-yStart)/width);

        repaint();
    }

    //the actual method that paints
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //initialize count to zero
        int count = 0;
        
        //set background to black
        this.setBackground(new Color(0,0,0));
        
        //set color to white to write
        g.setColor(Color.WHITE);
        
        g.drawString("Please select two points to compare and see Output window.",10,350);
        g.drawRect(xStart,yStart,width,width);
        count++;
        // Draw any lines needed
        for(LineSegment l : lines){	
        	g.setColor(c1[count]);
            g.drawLine((int)(width*l.p1.x)+xStart, (int)(width*(1-l.p1.y))+yStart, (int)(width*l.p2.x)+xStart, (int)(width*(1-l.p2.y))+yStart);
            //change color
            count = (count + 1) % 5;  
        }

        // Draw points if they are selected and a bunch of other cool stuff
        if(p0!=null){
        	g.setColor(Color.WHITE);
            g.fillOval((int)(p0.x), (int)(p0.y),10,10);
            g.drawString("First point selected is:" + p0.x + " " + p0.y,10,380);
        }
        if(p1!=null){
        	g.setColor(Color.BLACK);
            g.fillOval((int)(p1.x), (int)(p1.y),10,10);
            g.setColor(Color.WHITE);
            g.drawString("Second point selected is:" + p1.x + " " + p1.y,10,395);
            String s;
            LineSegment res1 = tree.search(tree.getNode(), p0, p1);
            if(res1 == null)
                s = ("Points "+ p0+" and "+ p1 +" are in the same region");
            else
                s = ("Points "+p0+" and "+p1+" are separated by line " + res1.p1.x + " " + res1.p1.y + " " + res1.p2.x + " " + res1.p2.y);
            g.drawString(s, 10, 415);
        }
    }

}

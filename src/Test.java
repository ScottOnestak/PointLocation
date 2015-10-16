import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		
		//initialize global variables
		int numLines;
		BinaryTree BST = new BinaryTree();
		String[] input = new String[4];
		String container, infile;
		LineSegment line;
		Point one = new Point(0,0);
		Point two = new Point(0,0);
		
		//create scanner to take in input file
		System.out.print("Please enter your file name: ");
		Scanner scan = new Scanner(System.in);
		infile = scan.nextLine();
		
		
		//create try...catch for buffered reader
		try{
			
			//create buffered reader and read first line in
			BufferedReader read = new BufferedReader(new FileReader(infile));
			numLines = Integer.parseInt(read.readLine());
			double x1, y1, x2, y2;
		
			//create the GUI
			JFrame frame = new JFrame();
			MyMouseListener mml = new MyMouseListener();
			frame.addMouseListener(mml);
			frame.setPreferredSize(new Dimension(700,500));
			UnitSquare u = new UnitSquare();
			u.addMouseListener(mml);
			frame.add(u);
			frame.pack();
			frame.setVisible(true);
		
			//go through the lines
			for(int i = 0; i < numLines; i++){
				container = read.readLine();
				input = container.split(" ");
			
				//parse the variables
				x1 = Double.parseDouble(input[0]);
				y1 = Double.parseDouble(input[1]);
				x2 = Double.parseDouble(input[2]);
				y2 = Double.parseDouble(input[3]);
			
				//create line segments
				line = new LineSegment(x1, y1, x2, y2);
				
				//check for degenerate case of a line equal to a point before inserting and adding
				if(x1 != x2 && y1 != y2){
					BST.insert(line);
					u.addLine(line);
				}
			}
		
			//get external nodes, average path length and print those out with the tree in order
			int count = TreeNode.externalNodes(BST.getNode());
			double length = TreeNode.averagePathLength(BST.getNode());
			
			BST.printInOrder();
			System.out.println("This is the count: " + count);
			System.out.println("Average path length: " + length);
		
			//begin reading in points til the end of the file
			container = read.readLine();
			while(container != null){
				input = container.split(" ");
			
				//parse the points
				x1 = Double.parseDouble(input[0]);
				y1 = Double.parseDouble(input[1]);
				x2 = Double.parseDouble(input[2]);
				y2 = Double.parseDouble(input[3]);
			
				//make the points
				one.x = x1;
				one.y = y1;
			
				two.x = x2;
				two.y = y2;
			
			
				//if null, in same region... if not, return the line that separates them
				if(BST.search(BST.getNode(), one, two) == null){
					System.out.println("Points " + one + " and " + two + " are in the same region.");
				} else {
					System.out.println("Points " + one + " and " + two + " are separated by line "
						+ TreeNode.printNode(BST.search(BST.getNode(), one, two)));
				}

				//read in the next line
				container = read.readLine();
			}
			u.setTree(BST);
			read.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

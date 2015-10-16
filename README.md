# PointLocation
I build a binary tree that took in points of line segments and build a tree with those in order to determine whether the test cases of points lie in the same region or are separated by  a line, and if so return that line.

TEST.JAVA: 

To do this, I began by creating a buffered reader that read line by line.  The first told us how many of the lines are line segments and the rest are points.  I then used the input from the first line of the file to  determine the size of the for loop that creates the line segments and inserts them into the tree.  From there, I was able to read in the points until the end of the file.  I also created the JFrame and set up the GUI in this class.  

POINT.JAVA:  

Exactly what it sounds like.  Point takes in two values (x and y) and creates a point with them.  

LINESEGMENT.JAVA:  

Takes in 4 values x1,y1,x2,and y2 and makes them into a line segment.  It also contains the method to check whether the lines intersect (citation for helping code in the code).  Additionally, it has a boolean method to say true or false if they intersect and a toString method.  

TREENODE.JAVA:  TreeNode creates the tree node.  It also contains many important methods I call, such as the printInOrder method that prints the tree out in order, which I use in the main method.  Additionally, it also contains a method to print out a node, which is a lineSegment.  I also count the external nodes, the length of the path of the external nodes, and the average path length of the tree.  The average path length of the tree should grow at a rate fairly log n.  This is because to go down another level, there is an additional two nodes for the node before.  Therefore, the tree is expanding at a rate 2n every level.  However, since this is not an AVL tree and does not balance, it will be less efficient and therefore could grow at rate n if all the nodes were inserted to the left or right of each other, making a linked list.  

BINARYTREE.JAVA:  

This is the meat of the program.  To insert, I have a public and private insert.  The public only inserts into the tree if the root is null.  Besides that, it goes through all the test cases, using the ccw given to us to insert the line segments into the proper nodes.  I won't go though all the cases, because that would make a long-winded explanation even longer.  After that, I have my search method, which takes in the root of the tree and the two test points.  We call the ccw on the test points and traverse them through the tree based on whether they are above or below the current node (whether they are clockwise, counter-clockwise, or collinear (which they shouldn't be).  We know that if they return different values for the same node, then that is the line that splits them, so I return that line and print it out.  Otherwise, we check to see if the next node is null.  If it is, then we have reached the bottom of the tree and they are on the same side of the bottom node, meaning they are in the same region, so I return null.  If the next node is not null, however, we call the search method with the next node as the head.  

MYMOUSELISTENER.JAVA:  Very simple.  If the person clicks, for the first one it adds the point, and for the second it checks them. Same as the binary tree, just with points determined by the mouse click.  

UNITSQUARE.JAVA:  

In this method, I draw the line segments first.  It is important to note that the project drawing are wrong in this case because the origin for GUI is not the bottom left hand corner, it is the upper left hand corner. Therefore, I had to flip my x-values so the lines would match up correctly to the regions.  After drawing the lines and displaying the message to select the points, when the person clicks the first time I display the coordinate of that point.  Then, when they click the second point, I display the coordinate and the same message the user receives from the regular method which tells the person whether the points are in the same region or if they are in a different region and what line splits them.  This can be repeated as long as the user likes.

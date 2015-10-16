public class BinaryTree {
	
	public TreeNode root;
	String thePoint;
	Point intersection = new Point(0,0);
	LineSegment segment;
	LineSegment n;
	
	private final static int COUNTERCLOCKWISE = 1;
	private final static int CLOCKWISE = -1;
	private final static int COLLINEAR = 0;
	
	public BinaryTree() {}
	
	//call print in order
	public void printInOrder() { root.printInOrder(); }
	
	//print out the node
	public String printNode(TreeNode n) { return printNode(n); }
	
	//return the root
	public TreeNode getNode() { return root; }

	//public insert method
	public void insert(LineSegment line) {
		if (root == null) {		
			root = new TreeNode(line);
		} else {
			insert(line, root);
		}
	}
	
	//private insert method...goes through all the cases
	private TreeNode insert(LineSegment line, TreeNode n) {
		if (LineSegment.intersectbool(line, n.line)) {
			if (n.leftchild != null)
				insert(line, n.leftchild);
			else
				n.leftchild = new TreeNode(line);
			if (n.rightchild != null)
				insert(line, n.rightchild);
			else
				n.rightchild = new TreeNode(line);
		} else if (ccw(line.p1, n.line.p1, n.line.p2) == COUNTERCLOCKWISE) {
			if (n.rightchild != null)
				insert(line, n.rightchild);
			else
					n.rightchild = new TreeNode(line);
		}else if (ccw(line.p1, n.line.p1, n.line.p2) == CLOCKWISE) {
			if (n.leftchild != null)
				insert(line, n.leftchild);
			else
				n.leftchild = new TreeNode(line);
		}
		
		else if(ccw(line.p2, n.line.p1, n.line.p2) == COUNTERCLOCKWISE) {
			if (n.rightchild != null)
				insert(line, n.rightchild); 
			else
					n.rightchild = new TreeNode(line);
		}
		
		else if (ccw(line.p2, n.line.p1, n.line.p2) == CLOCKWISE) {
			if (n.leftchild != null)
				insert(line, n.leftchild);
			else
				n.leftchild = new TreeNode(line);
		}
		
		else if(ccw(line.p1, n.line.p1, n.line.p2) == COLLINEAR || ccw(line.p2, n.line.p1, n.line.p2) == COLLINEAR) {
			System.out.println("Degenerate case for line: " + line.p1.x + " " + line.p1.y + " " + line.p2.x + " " + line.p2.y);
		}

		return n;
	}

	//search method
	public LineSegment search(TreeNode node, Point p1, Point p2) {
		//calculate which side of the node the point is on
		int a1 = ccw(p1, node.line.p1, node.line.p2); 
		int a2 = ccw(p2, node.line.p1, node.line.p2);
		
		//if both on the left, go left unless there is no child... then they must be in the same region
		if (a1 == -1  && a2 == -1) {
			if (node.leftchild == null){
				return null;
			}else{
				return search(node.leftchild, p1, p2);
			}

		} 
		//if both on the right, go right unless there is no child... then they must be in the same region
		else if (a1 == 1 && a2 == 1) {
			if (node.rightchild==null)
				return null;
			else
				return search(node.rightchild, p1, p2);
		} 
		//if one is collinear,which shouldn't be the case.  print out a statment.
		else if( a1 == 0 || a2 == 0){
			System.out.println("Collinear problem at:" + node.line.p1.x + " " + node.line.p1.y + " " + node.line.p2.x + " " + node.line.p2.y);
			return null;
		} else {
			return node.line;
		}
	}
	
	//ccw, given
	int ccw(Point p0, Point p1, Point p2) {
		 double dx1 = p1.x - p0.x;
		 double dy1 = p1.y - p0.y;
		 double dx2 = p2.x - p0.x;
		 double dy2 = p2.y - p0.y;
		 if(p1.x == p2.x && p1.y == p2.y) return COLLINEAR;
		 else if (dx1*dy2 > dy1*dx2) return COUNTERCLOCKWISE;
		 else if (dx1*dy2 < dy1*dx2) return CLOCKWISE;
		 else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return CLOCKWISE;
		 else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return COUNTERCLOCKWISE;
		 else return COLLINEAR;
	}
}

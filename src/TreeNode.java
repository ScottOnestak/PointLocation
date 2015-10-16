public class TreeNode {

	//global variables
	public LineSegment line, segment;
	public TreeNode leftchild;
	public TreeNode rightchild;
	public TreeNode parent;
	public TreeNode root;
	
	//constructor
	public TreeNode(LineSegment line){
		this.line = line;
		this.leftchild = null;
		this.rightchild = null;
		this.parent = null;
	}
	
	//print the tree in order
	public void printInOrder() {
		if (leftchild != null)
			leftchild.printInOrder();
		
		System.out.println(line.p1.x + " " + line.p1.y + " " + line.p2.x + " " + line.p2.y);
		
		if (rightchild != null)
			rightchild.printInOrder();
	}
	
	//print out the line segment at the node
	public static String printNode(LineSegment n){
		return n.p1.x + " " + n.p1.y + " " + n.p2.x + " " + n.p2.y;
	}

	//count the number of external nodes
	public static int externalNodes(TreeNode n){
		if(n.leftchild == null || n.rightchild == null)
			return 1;
		else
			return externalNodes(n.leftchild) + externalNodes(n.rightchild);
	}
	
	//count the external pathlength
	private static int externalPathLength(TreeNode n, int m){
		if(n == null)
			return 0;
		if(n.leftchild == null && n.rightchild == null){
			m++;
			return m;
		}
		else{
			return externalPathLength(n.leftchild, m++) + externalPathLength(n.rightchild, m++);
		}
	}
	
	//return average path length
	public static double averagePathLength(TreeNode n){
		return (double) externalPathLength(n, 0) / (double) externalNodes(n);
	}
}

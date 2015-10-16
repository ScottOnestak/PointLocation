public class LineSegment {
	Point p1;
	Point p2;

	/**Constructor to build a line segment out of four coordinates
	 * @param four double coordinates
	 * */
	public LineSegment(double x1, double y1, double x2, double y2) {
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
	}
	
	public LineSegment(){}

	/**POI = Point of Intersection. This method calculates the point of intersection of two lines
	 * @param lines
	 * @return point of intersection
	 * */
	/*Help from http://www.ahristov.com/tutorial/geometry-games/intersection-segments.html*/
	
	public static Point POI(LineSegment line1, LineSegment line2){
		
		double x1 = line1.p1.x;
		double y1 = line1.p1.y;
		double x2 = line1.p2.x;
		double y2 = line1.p2.y;
		
		double x3 = line2.p1.x;
		double y3 = line2.p1.y;
		double x4 = line2.p2.x;
		double y4 = line2.p2.y;
		
		double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4); 
		if (d == 0) return null; 
		
		double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
		double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
		
		if (x3==x4) { 
			if (yi < Math.min(y1,y2) || yi > Math.max(y1,y2))
				return null; 
		} 
		Point p = new Point(xi,yi);
		
		if (xi < Math.min(x1,x2) || xi > Math.max(x1,x2)) 
			return null; 
		
		if (xi < Math.min(x3,x4) || xi > Math.max(x3,x4)) 
			return null;
		return p;
	}

	public static boolean intersectbool(LineSegment line1, LineSegment line2){
		if (POI(line1, line2) == null) {  //dont intersect		
			return false;
		}
		else
			return true; //do intersect
	}
	
	public String toString(){
		return "" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y + " ";
	}
}

import java.text.DecimalFormat;


public class Point{
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");

        return ""+df.format(x)+" "+df.format(y);
    }

}

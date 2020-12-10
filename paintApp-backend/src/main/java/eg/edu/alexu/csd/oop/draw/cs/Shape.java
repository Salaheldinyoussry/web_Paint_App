package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public abstract class Shape implements Cloneable{
	public int id=-1;
	public String type;
	public String lineColor;
    public String fillColor;
    public double boundingBox_top =0;
    public double boundingBox_left=0;
    public double width=0;
	public double height=0;
	public double f_x1=0;
	public double f_y1=0;
	public double f_x2=0;
	public double f_y2=0;

	  public Object clone() throws
      CloneNotSupportedException 
{ 
return super.clone(); 
} 
	

public abstract boolean checkIfShapeIntersectAPoint(double x,double y);

}

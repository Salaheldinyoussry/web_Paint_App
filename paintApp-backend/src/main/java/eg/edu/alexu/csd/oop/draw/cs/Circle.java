package eg.edu.alexu.csd.oop.draw.cs;

public class Circle extends Shape {



@Override
public boolean checkIfShapeIntersectAPoint(double x, double y) {
	System.out.println("I am acircle");
	
	double c_x=boundingBox_left +(width/2);
	double c_y=boundingBox_top +(height/2);
	//double minR=Math.min(width, height)
	double radius=(width)/2;
	
	if((Math.pow((x-c_x), 2)+ Math.pow((y-c_y), 2)) <=Math.pow(radius,2)) {
		return true;
	}
	
	
	return false;
}

}

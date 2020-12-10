package eg.edu.alexu.csd.oop.draw.cs;

public class Ellipse extends Shape {
	public double c_x;
	public double c_y;
	public double radius_x;
	public double radius_y;


	@Override
	public boolean checkIfShapeIntersectAPoint(double x, double y) {
		double c_x=boundingBox_left +(width/2);
		double c_y=boundingBox_top +(height/2);
        double radiusX =width/2;
        double radiusY =height/2;
	
		double tmp = (Math.pow((x-c_x), 2))/(Math.pow(radiusX, 2))  +  (Math.pow((y-c_y), 2))/(Math.pow(radiusY, 2));
		if(tmp<=1) {
			return true;
		}
		
		
		return false;
	}
}

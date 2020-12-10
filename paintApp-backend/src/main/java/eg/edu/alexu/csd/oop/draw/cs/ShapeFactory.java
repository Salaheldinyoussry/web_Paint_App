package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Service;


public class ShapeFactory {
	public Shape getShape(String x) {
	if(x.equalsIgnoreCase("rectangle")) {
		return new Rectangle();
	}
	else if(x.equalsIgnoreCase("circle")) {
		return new Circle();
	} 
	else if(x.equalsIgnoreCase("line")) {
		return new LineSegment();
	} 
	else if(x.equalsIgnoreCase("ellipse")) {
		return new Ellipse();
	} 
	else if(x.equalsIgnoreCase("triangle")) {
		return new Triangle();
	} 
	else if(x.equalsIgnoreCase("square")) {
		return new Square();
	} 
		
		return null;		
	}
	

}

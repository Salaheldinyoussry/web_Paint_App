package eg.edu.alexu.csd.oop.draw.cs;

public class LineSegment  extends Shape{

@Override
public boolean checkIfShapeIntersectAPoint(double x,double y) {
	System.out.println("I am line");

	if((x>=f_x1 && x<=f_x2)  || (x>=f_x2 && x<=f_x1) ) {
		double slope=(f_y2-f_y1)/(f_x2-f_x1);
		double interc =f_y1-(slope*f_x1);		
		double tmp=(slope*x)+interc;		
		if(Math.abs(y-tmp)<=10) {
           return true;
		}
				}
	return false;
}

}


//if((x>=f_x1 && x<=f_x2)  || (x>=f_x2 && x<=f_x1) ) {
//double slope=(f_y2-f_y1)/(f_x2-f_x1);
//double interc =f_y1-(slope*f_x1);
//double tmp1=x;
//double tmp2=(slope*x)+interc;
//double vec1_x=f_x2-tmp1;
//double vec1_y=f_y2-tmp2;
//double vec2_x=x-tmp1;
//double vec2_y=y-tmp2;
//double dot_product=vec1_x*vec2_x+vec1_y*vec2_y;
//double vec1Len =Math.sqrt( Math.pow(vec1_x, 2) + Math.pow(vec1_y, 2) );
//double vec2Len =Math.sqrt( Math.pow(vec2_x, 2) + Math.pow(vec2_y, 2) );
//double theta =dot_product /(vec1Len +vec2Len);
//double perDis =vec2Len* Math.sin(Math.toDegrees(theta)); 
//if(perDis<0.5) {
//return true;
//}
//}
//return false;

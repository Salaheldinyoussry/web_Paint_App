package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Component;

@Component
public class Triangle extends Shape{
	
//	public boolean checkIfShapeIntersectAPoint(int x,int y) {
//	
//		
//	}
//
	@Override
	public boolean checkIfShapeIntersectAPoint(double x, double y) {
		System.out.println("I am triangle");

		double x2=boundingBox_left;
		double y2=boundingBox_top+height;
		double x3=boundingBox_left+width;
		double y3=boundingBox_top+height;
		double x4=boundingBox_left+width/2;
		double y4=boundingBox_top;
		
		
		 double A = area (x2, y2, x3, y3, x4, y4); 
	       
	       /* Calculate area of triangle PBC */  
	        double A1 = area (x, y, x3, y3, x4, y4); 
	       
	       /* Calculate area of triangle PAC */  
	        double A2 = area (x2, y2, x, y, x4, y4); 
	       
	       /* Calculate area of triangle PAB */   
	        double A3 = area (x2, y2, x3, y3, x, y); 
	         
	       /* Check if sum of A1, A2 and A3 is same as A */
	        return (A == A1 + A2 + A3); 
		
//		double tmp1 =Math.sqrt((Math.pow(x4-x2, 2))+ (Math.pow(y4-y2, 2)) );
//		double tmp3 =Math.sqrt((Math.pow(x-x2, 2))+ (Math.pow(y-y2, 2)) );
//		double tmp4 =Math.sqrt((Math.pow(x-x3, 2))+ (Math.pow(y-y3, 2)) );
//				
//		if(x>=boundingBox_left && x<= boundingBox_left +width && y>=boundingBox_top && y<=boundingBox_top+height) {
//	        if(Math.asin(tmp1/width)>= Math.asin(tmp3/width) && Math.asin(tmp1/width)>= Math.asin(tmp4/width))   
//			  return true;		
//		 
//		}	
//		
		
	}

    private double area(double x1, double y1, double x2, double y2, double x3, double y3) {

    	return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ 
                x3*(y1-y2))/2.0); 
    	}
	
	
	
//  public int P1_x;
//  public  int P1_y;
//  public  int P2_x ;
//  public int P2_y;
//  public int P3_x;
//  public int P3_y;
// 
// 
// 
// public void draw() {
//	 this.type="triangle";
////	 
////	 if(f_x1>f_x2) {
////	
////		 
////	 }
////	 else if(f_x2>=f_x1) {
////			this.drawP1_x =(x2-x1)/2 +x1;
////	 }
//////	 if(f_y1>f_y2) {
//////			this.drawP1_y =y2;
//////				 
//////			 }
//////			 else if(y2>=y1) {
//////					this.drawP1_y =y1;
//////			 }
//// 
////		P1_x=f_x1;
////		P1_y=f_y1;
////		P2_x=f_x2;
////		P2_y=f_y1;
////	if(f_x2>=f_x1 && f_y2>=f_y1) {
////	
////		P3_x=f_x1+(f_x2 - f_x1)/2;
////		P3_y=f_y2;
////	
////			
////	}
////	else if(f_x2<f_x1 && f_y2>f_y1) {
////		P3_x=f_x2+(f_x1 - f_x2)/2;
////		P3_y=f_y2;
////	
////	}	
////	else if(f_x2>f_x1 && f_y2<f_y1) {
////		P3_x=f_x1+(f_x2 - f_x1)/2;
////		P3_y=f_y2;
////		
////	}	
////	
////	else if(f_x2<f_x1 && f_y2>f_y1) {
////		P3_x=f_x2+(f_x1 - f_x2)/2;
////		P3_y=f_y2;
////			
////	}	
//	 
//	 
// 
//	 
// }
 
 

 
	
	
}

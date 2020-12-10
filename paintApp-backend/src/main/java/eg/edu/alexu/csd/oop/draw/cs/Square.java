package eg.edu.alexu.csd.oop.draw.cs;

public class Square extends Shape {
	
	
	
	


	@Override
	public boolean checkIfShapeIntersectAPoint(double x, double y) {
		double minn=Math.min(width, height);
		if(x>=boundingBox_left && x<= boundingBox_left +minn && y>=boundingBox_top && y<=boundingBox_top+minn)
           return true;		
	    return false;
	}	
	
	
	
	
	
	
	
	
//	 public int P1_x;
//	  public  int P1_y;
//	  public  int P2_x ;
//	  public int P2_y;
//	  public int P3_x;
//	  public int P3_y;
//	  public int P4_x;
//	  public int P4_y;	
//	
//public void draw() {
//	
////	P1_x=f_x1;
////	P1_y=f_y1;
////	if(Math.abs(this.f_x2-this.f_x1) > Math.abs(this.f_y2 -this.f_y1)) {
////		if(f_x1<f_x2) {
////		P2_x=f_x1+ Math.abs(this.f_y2 -this.f_y1);
////		}
////		else {
////			P2_x=f_x1- Math.abs(this.f_y2 -this.f_y1);
////			}
////		P2_y=f_y1;
////		P3_x =f_x1;
////		
////		if(f_y2>f_y1) {
////		P3_y=f_y1+Math.abs(this.f_y2 -this.f_y1);
////		}
////		else {
////			P3_y=f_y1-Math.abs(this.f_y2 -this.f_y1);	
////		}
////		if(f_y2>f_y1) {
////		P4_x=f_x1+Math.abs(this.f_y2 -this.f_y1);
////		}
////		else {
////			P4_x=f_x1-Math.abs(this.f_y2 -this.f_y1);
////				}
////		if(f_y2>f_y1) {
////		
////		P4_y=f_y1+Math.abs(this.f_y2 -this.f_y1);
////		}
////		else {
////			P4_y=f_y1-Math.abs(this.f_y2 -this.f_y1);
////			
////		}
////	}
////	else {
////		
////		if(f_x1<f_x2) {
////		P2_x=f_x1+ Math.abs(this.f_x2 -this.f_x1);
////		}
////		else {
////			P2_x=f_x1- Math.abs(this.f_x2 -this.f_x1);
////			}
////		P2_y=f_y1;
////		P3_x =f_x1;
////		
////		if(f_y2>f_y1) {
////		P3_y=f_y1+Math.abs(this.f_x2 -this.f_x1);
////		}
////		else {
////			P3_y=f_y1-Math.abs(this.f_x2 -this.f_x1);	
////		}
////		if(f_y2>f_y1) {
////		P4_x=f_x1+Math.abs(this.f_x2 -this.f_x1);
////		}
////		else {
////			P4_x=f_x1-Math.abs(this.f_x2 -this.f_x1);
////				}
////		if(f_y2>f_y1) {
////		
////		P4_y=f_y1+Math.abs(this.f_x2 -this.f_x1);
////		}
////		else {
////			P4_y=f_y1-Math.abs(this.f_x2 -this.f_x1);
////			
////		}
////		
////	}
////	
//	
//}
}

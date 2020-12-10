package eg.edu.alexu.csd.oop.draw.cs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class uploadImage {
	public	List<FakeShape> allShapes = new ArrayList<>();
	public	List<UserAction2> UserActions = new ArrayList<>() ;  
	public int actionIndex;	
//	@Component
//	public class UserAction2{
//    	public String action;
//    public	FakeShape old;
//    public	FakeShape updated;
//    
//    }
	
	public List<Shape> convert(){
		List<Shape> l = new ArrayList<>(); 

		for(int i=0; i<allShapes.size(); i++) {
		ShapeFactory f=new ShapeFactory();
			Shape s=f.getShape(allShapes.get(i).type);
		    s.type =allShapes.get(i).type;
			   s.boundingBox_left=allShapes.get(i).boundingBox_left;
             s.boundingBox_top=allShapes.get(i).boundingBox_top;
			   s.width=allShapes.get(i).width;
			   s.height=allShapes.get(i).height;
			   s.id=allShapes.get(i).id;
			   s.type=allShapes.get(i).type;
			   s.lineColor=allShapes.get(i).lineColor;
			   s.fillColor=allShapes.get(i).fillColor;
			   s.f_x1=allShapes.get(i).f_x1;
			   s.f_y1=allShapes.get(i).f_y1;
			   s.f_x2=allShapes.get(i).f_x2;
			   s.f_y2=allShapes.get(i).f_y2;
			   l.add(s);
		
	}
		
		
		return l;
		
		
	}
	public List<UserAction> convert2(){
		List<UserAction> l = new ArrayList<>(); 

		for(int i=0; i<UserActions.size(); i++) {
		ShapeFactory f=new ShapeFactory();
			Shape s=f.getShape(UserActions.get(i).old.type);
		    s.type =UserActions.get(i).old.type;
			   s.boundingBox_left=UserActions.get(i).old.boundingBox_left;
             s.boundingBox_top=UserActions.get(i).old.boundingBox_top;
			   s.width=UserActions.get(i).old.width;
			   s.height=UserActions.get(i).old.height;
			   s.id=UserActions.get(i).old.id;
			   s.type=UserActions.get(i).old.type;
			   s.lineColor=UserActions.get(i).old.lineColor;
			   s.fillColor=UserActions.get(i).old.fillColor;
			   s.f_x1=UserActions.get(i).old.f_x1;
			   s.f_y1=UserActions.get(i).old.f_y1;
			   s.f_x2=UserActions.get(i).old.f_x2;
			   s.f_y2=UserActions.get(i).old.f_y2;
               
			   Shape ss=null;
			   if(UserActions.get(i).updated!=null ) {
				 ss=f.getShape(UserActions.get(i).updated.type);
			    ss.type =UserActions.get(i).old.type;
				   ss.boundingBox_left=UserActions.get(i).updated.boundingBox_left;
	             ss.boundingBox_top=UserActions.get(i).updated.boundingBox_top;
				   ss.width=UserActions.get(i).updated.width;
				   ss.height=UserActions.get(i).updated.height;
				   ss.id=UserActions.get(i).updated.id;
				   ss.type=UserActions.get(i).updated.type;
				   ss.lineColor=UserActions.get(i).updated.lineColor;
				   ss.fillColor=UserActions.get(i).updated.fillColor;
				   ss.f_x1=UserActions.get(i).updated.f_x1;
				   ss.f_y1=UserActions.get(i).updated.f_y1;
				   ss.f_x2=UserActions.get(i).updated.f_x2;
				   ss.f_y2=UserActions.get(i).updated.f_y2;
			   }
				   UserAction a =new UserAction();
				   a.old=s;
				   a.updated=ss;
				   a.action=this.UserActions.get(i).action;
				   l.add(a);
				   
			   
	}
		
		
		return l;
		
		
	}
	


}

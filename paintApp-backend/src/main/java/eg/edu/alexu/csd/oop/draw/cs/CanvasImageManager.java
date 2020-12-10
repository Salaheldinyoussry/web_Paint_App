package eg.edu.alexu.csd.oop.draw.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

public class CanvasImageManager {
   private static CanvasImageManager c;
	private CanvasImageManager() {
		
	}
	
	public static  CanvasImageManager getInstance() {
			if(c==null) {
			   c=new CanvasImageManager();
			}
		
		return c;
		
	}

	@Component
	public class imageData {
		public	List<Shape> allShapes ;
		public	List<UserAction> UserActions;  
		public int actionIndex;
		public Object stream() {
			// TODO Auto-generated method stub
			return null;
		}	}
//	@Component
//	public class UserAction{
//    	public String action;
//    public	Shape old;
//    public	Shape updated;
//    public UserAction(){}
//    	UserAction(String action,Shape old,Shape updated){
//    		this.action=action;
//    		this.old=old;
//    		this.updated=updated;
//    	}
//    }
	
public	List<Shape> allShapes = new ArrayList<>(); 
public	List<UserAction> UserActions = new ArrayList<>(); 
int actionIndex=-1;
uploadImage i=new uploadImage();
	
	 public Shape getShapeById(int id) {
		for(int i=0; i<allShapes.size(); i++) {
			if(allShapes.get(i).id==id) {
				return allShapes.get(i);
			}
		}
		  return null;
		  }
	 
	 
	 public int checkIfUserSelectedAShape(double x,double y) {
	    for(int i=allShapes.size()-1; i>=0; i--) {
	    	Shape s=allShapes.get(i);
	    	if(s.checkIfShapeIntersectAPoint(x, y)) {
	    		return s.id;
	    	}
	    }
		  return -1;
		 
	 }

	 
   public void AddShape(Shape s) {
	   if(this.actionIndex<this.UserActions.size()-1) {
	    	deleteMemory();
	    }
		 
	   allShapes.add(s);
		UserAction ua= new UserAction("add",s,null);
		UserActions.add(ua);
		   actionIndex++;
			
	}
   
   public void deleteShape(int id) {
	   if(this.actionIndex<this.UserActions.size()-1) {
	    	deleteMemory();
	    }
		 
	   for(int i=0; i<allShapes.size(); i++) {
			if(allShapes.get(i).id==id) {
			    Shape x=allShapes.get(i);
				UserAction ua= new UserAction("delete",x,null);
				UserActions.add(ua);
				   actionIndex++;				
				allShapes.remove(i);
			}
		}	   
 			
 	}
   
   public void changeLocation(int id ,double x,double y,double width ,double height ,double f_x1, double f_y1,double f_x2,double f_y2) {
	   if(this.actionIndex<this.UserActions.size()-1) {
	    	deleteMemory();
	    }
		 
	   //  if(id>=0 && id<this.allShapes.size()) {
    	    Shape sh=getShapeById(id);
 		   ShapeFactory sf =new  ShapeFactory();
 		   Shape oldShape =sf.getShape(sh.type);
 		   try {
 			oldShape= (Shape) sh.clone();
 		} catch (CloneNotSupportedException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
//    	  
//	    oldShape =sf.getShape(sh.type);
//	   oldShape.boundingBox_left=sh.boundingBox_left;
//	   oldShape.boundingBox_top=sh.boundingBox_top;
//	   oldShape.width=sh.width;
//	   oldShape.height=sh.height;
//	   oldShape.id=sh.id;
//	   oldShape.type=sh.type;
//	   oldShape.lineColor=sh.lineColor;
//	   oldShape.fillColor=sh.fillColor;
//	   oldShape.f_x1=sh.f_x1;
//	   oldShape.f_y1=sh.f_y1;
//	   oldShape.f_x2=sh.f_x2;
//	   oldShape.f_y2=sh.f_y2;
	   
	   sh.boundingBox_left=x;
	   sh.boundingBox_top=y;
	   sh.width=width;
	   sh.height=height;
	   sh.f_x1=f_x1;
	   sh.f_x2=f_x2;
	   sh.f_y1=f_y1;
	   sh.f_y2=f_y2;
	   Shape newShape =sf.getShape(sh.type);

	    try {
			 newShape=(Shape) sh.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   UserAction ua= new UserAction("changeLocation",oldShape,newShape);
		UserActions.add(ua);
		   actionIndex++;
			
	}
   
   public void changeFillColor(int id ,String color) {
	   //   if(id>=0 && id<this.allShapes.size()) {
    if(this.actionIndex<this.UserActions.size()-1) {
    	deleteMemory();
    }
	   Shape sh=getShapeById(id);
		   ShapeFactory sf =new  ShapeFactory();
		   Shape oldShape =sf.getShape(sh.type);
		   try {
			oldShape= (Shape) sh.clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		   oldShape.boundingBox_left=sh.boundingBox_left;
//		   oldShape.boundingBox_top=sh.boundingBox_top;
//		   oldShape.width=sh.width;
//		   oldShape.height=sh.height;
//		   oldShape.id=sh.id;
//		   oldShape.type=sh.type;
//		   oldShape.lineColor=sh.lineColor;
//		   oldShape.fillColor=sh.fillColor;
//		   oldShape.f_x1=sh.f_x1;
//		   oldShape.f_y1=sh.f_y1;
//		   oldShape.f_x2=sh.f_x2;
//		   oldShape.f_y2=sh.f_y2;
	    sh.fillColor=color;
		   Shape newShape =sf.getShape(sh.type);

	    try {
			 newShape=(Shape) sh.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   UserAction ua= new UserAction("color",oldShape,newShape);
			UserActions.add(ua);
			   actionIndex++;
				
		}
   
   
   private void deleteMemory() {
	   for(int i=UserActions.size()-1; i>=0 ; i-- ) {
		   if(i==this.actionIndex) {
			   break;
		   }
		   else {
			   UserActions.remove(i);
		   }
	   }
   }
   
   public List<Shape> undo() {
	   
	 if(actionIndex>-1) {
	   if(UserActions.get(actionIndex).action.equalsIgnoreCase("delete")) {
		  allShapes.add(UserActions.get(actionIndex).old);
		   
	   }
	   else if (UserActions.get(actionIndex).action.equalsIgnoreCase("changeLocation")) {
		   Shape sh=getShapeById(UserActions.get(actionIndex).old.id);
		   sh.boundingBox_left=UserActions.get(actionIndex).old.boundingBox_left;
		   sh.boundingBox_top=UserActions.get(actionIndex).old.boundingBox_top;
		   sh.width=UserActions.get(actionIndex).old.width;
		   sh.height=UserActions.get(actionIndex).old.height;
		   sh.f_x1=UserActions.get(actionIndex).old.f_x1;
		   sh.f_y1=UserActions.get(actionIndex).old.f_y1;
		   sh.f_x2=UserActions.get(actionIndex).old.f_x2;
		   sh.f_y2=UserActions.get(actionIndex).old.f_y2;
		   
	   }
      
	   else if (UserActions.get(actionIndex).action.equalsIgnoreCase("color")) {
		   Shape sh=getShapeById(UserActions.get(actionIndex).old.id);
		   sh.fillColor=UserActions.get(actionIndex).old.fillColor;
		   
	   }
	   else if (UserActions.get(actionIndex).action.equalsIgnoreCase("add")) {
			System.out.println("what the jj");

		   int id=UserActions.get(actionIndex).old.id;
		   for(int i=0; i<allShapes.size(); i++) {
				if(allShapes.get(i).id==id) {
					System.out.println("what the jj");
					allShapes.remove(i);
					break;
				}
			}	
		   
	   }
	   
		  actionIndex--;
	   
	 }
	return allShapes;

   
   }
   
   public List<Shape> redo() {
		if(	  actionIndex <UserActions.size()-1) {
	   actionIndex++; 
	   if(actionIndex!=-1) {
		   if(UserActions.get(actionIndex).action.equalsIgnoreCase("delete")) {
			   int id=UserActions.get(actionIndex).old.id;
			   for(int i=0; i<allShapes.size(); i++) {
					if(allShapes.get(i).id==id) {
						allShapes.remove(i);
						break;
					}
				}			   
		   }
		   else if (UserActions.get(actionIndex).action.equalsIgnoreCase("changeLocation")) {
			   Shape sh=getShapeById(UserActions.get(actionIndex).old.id);
			   sh.boundingBox_left=UserActions.get(actionIndex).updated.boundingBox_left;
			   sh.boundingBox_top=UserActions.get(actionIndex).updated.boundingBox_top;
			   sh.width=UserActions.get(actionIndex).updated.width;
			   sh.height=UserActions.get(actionIndex).updated.height;
			   sh.f_x1=UserActions.get(actionIndex).updated.f_x1;
			   sh.f_y1=UserActions.get(actionIndex).updated.f_y1;
			   sh.f_x2=UserActions.get(actionIndex).updated.f_x2;
			   sh.f_y2=UserActions.get(actionIndex).updated.f_y2;
			   
		   }
	      
		   else if (UserActions.get(actionIndex).action.equalsIgnoreCase("color")) {
			   Shape sh=getShapeById(UserActions.get(actionIndex).old.id);
			   sh.fillColor=UserActions.get(actionIndex).updated.fillColor;
			   
		   }
		   else if(UserActions.get(actionIndex).action.equalsIgnoreCase("add")) {
				  allShapes.add(UserActions.get(actionIndex).old);
				   
			   }
		   
		 }}
		return allShapes;

	   
	   }
   
   
   public imageData save() {

	   imageData i=new imageData();
	   i.UserActions=this.UserActions;
	   i.allShapes=this.allShapes;
	   i.actionIndex=this.actionIndex;
	   System.out.println(UserActions.toString());
	   
	   return i;
	   
	   
   }
   
   public void load(uploadImage i) {
	  
	   this.allShapes=i.convert();
	   this.UserActions=i.convert2();
	   this.actionIndex=i.actionIndex;
	   
   }
   
   
//   public void save(String path) {
//       
//       JSONArray arr = new JSONArray();
//
//	   for(int i=0; i<UserActions.size(); i++) {
//
//		   JSONObject shape = new JSONObject();
//	       shape.put("id", UserActions.get(i).updated.id);
//	       shape.put("type", UserActions.get(i).updated.type);
//	       shape.put("lineColor",UserActions.get(i).updated.lineColor );
//	       shape.put("fillColor", UserActions.get(i).updated.fillColor);
//	       shape.put("boundingBox_top", UserActions.get(i).updated.boundingBox_top);
//	       shape.put("boundingBox_left", UserActions.get(i).updated.boundingBox_left);
//	       shape.put("width", UserActions.get(i).updated.height);
//	       shape.put("height", UserActions.get(i).updated.height);
//	       shape.put("f_x1", UserActions.get(i).updated.f_x1);
//	       shape.put("f_y1",UserActions.get(i).updated.f_y1 );
//	       shape.put("f_x2", UserActions.get(i).updated.f_x2);
//	       shape.put("f_y2",UserActions.get(i).updated.f_y2);
//	       
//		   JSONObject shape1 = new JSONObject();
//	       shape1.put("id", UserActions.get(i).old.id);
//	       shape1.put("type", UserActions.get(i).old.type);
//	       shape1.put("lineColor",UserActions.get(i).old.lineColor );
//	       shape1.put("fillColor", UserActions.get(i).old.fillColor);
//	       shape1.put("boundingBox_top", UserActions.get(i).old.boundingBox_top);
//	       shape1.put("boundingBox_left", UserActions.get(i).old.boundingBox_left);
//	       shape1.put("width", UserActions.get(i).old.height);
//	       shape1.put("height", UserActions.get(i).old.height);
//	       shape1.put("f_x1", UserActions.get(i).old.f_x1);
//	       shape1.put("f_y1",UserActions.get(i).old.f_y1 );
//	       shape1.put("f_x2", UserActions.get(i).old.f_x2);
//	       shape1.put("f_y2",UserActions.get(i).old.f_y2);
//	       
//		   JSONObject object = new JSONObject();
//           object.put("action", UserActions.get(i).action);
//
//		   object.put("old", shape1);
//           object.put("updated", shape);
//           arr.add(object);
//	   }
//
//        
//       JSONObject employeeObject = new JSONObject(); 
//       employeeObject.put("shape", shape);
//        
//       //Second Employee
//       JSONObject employeeDetails2 = new JSONObject();
//       employeeDetails2.put("firstName", "Brian");
//       employeeDetails2.put("lastName", "Schultz");
//       employeeDetails2.put("website", "example.com");
//        
//       JSONObject employeeObject2 = new JSONObject(); 
//       employeeObject2.put("employee", employeeDetails2);
//        
//       //Add employees to list
//       JSONArray employeeList = new JSONArray();
//       employeeList.add(employeeObject);
//       employeeList.add(employeeObject2);
//        
//       //Write JSON file
//       try (FileWriter file = new FileWriter("employees.json")) {
//
//           file.write(employeeList.toJSONString());
//           file.flush();
//
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//	   
//   }
   
	
}

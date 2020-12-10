package eg.edu.alexu.csd.oop.draw.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import eg.edu.alexu.csd.oop.draw.cs.CanvasImageManager.imageData;
import io.netty.handler.codec.http.HttpHeaders;

@RestController
@CrossOrigin
//@RequestMapping("/paintapp")
public class controller {





@PostMapping("/add")
public void addShape( @RequestBody FakeShape s) {
   ShapeFactory f =new ShapeFactory();
   Shape x=f.getShape(s.type);	
   System.out.println(s.boundingBox_left+"  :s.b");
   x.boundingBox_left=s.boundingBox_left;
   x.boundingBox_top=s.boundingBox_top;
   x.width=s.width;
   x.height=s.height;
   x.id=s.id;
   x.type=s.type;
   x.lineColor=s.lineColor;
   x.fillColor=s.fillColor;
   x.f_x1=s.f_x1;
   x.f_y1=s.f_y1;
   x.f_x2=s.f_x2;
   x.f_y2=s.f_y2;
    CanvasImageManager cm= CanvasImageManager.getInstance();
    cm.AddShape(x);
	 System.out.println(s.type);
   }
  /**
   * 
   * @param x  mouse location x
   * @param y  mouse location y
   * @return
   */
  @GetMapping("/getId/{x}/{y}")
  public int getIdOfShape(@PathVariable double x , @PathVariable double y  ) {
    CanvasImageManager cm= CanvasImageManager.getInstance();
     int id=cm.checkIfUserSelectedAShape(x, y);
     System.out.println(id);
    return id;
   }
  
  
  ///  it shoud be delete Request but it makes  error in communicating between two localhost ports
  @GetMapping("/delete/{id}")
  public void deleteShape(@PathVariable int id ) {
    CanvasImageManager cm= CanvasImageManager.getInstance();
    cm.deleteShape(id);
  
   }
  
  
  @GetMapping("/colorfill/{id}/{newColor}")
  
    public void colorShapeById(@PathVariable int id , @PathVariable String newColor  ) {
    CanvasImageManager cm= CanvasImageManager.getInstance();
  cm.changeFillColor(id, newColor);
   }
  
  
  @GetMapping("/resize/{id}/{x}/{y}/{width}/{height}/{f_x1}/{f_y1}/{f_x2}/{f_y2}")
  public void resizeShapeByID(@PathVariable int id , @PathVariable double x , @PathVariable double y, 
		  @PathVariable double width , @PathVariable double height , @PathVariable double f_x1 ,@PathVariable double f_y1, 
		  @PathVariable double f_x2, @PathVariable double f_y2) {
if(id!=-1) {
	  CanvasImageManager cm= CanvasImageManager.getInstance();
  cm.changeLocation(id, x, y, width, height, f_x1, f_y1, f_x2, f_y2);
}
 }
  
//  @PutMapping("/move/{id}/{x}/{y}/{width}/{height}/{f_x1}/{f_y1}/{f_x2}/{f_y2}")
//  public void moveShapeByID(@PathVariable int id , @PathVariable int x , @PathVariable int y,  @PathVariable int width , @PathVariable int height  
//		   , @PathVariable double f_x1 ,@PathVariable double f_y1, 
//		  @PathVariable double f_x2, @PathVariable double f_y2) {
//		  
//		
//  CanvasImageManager cm= CanvasImageManager.getInstance();
//
// }
//  
  @GetMapping("/undo")
  public List<Shape> makeUndo() { 
   CanvasImageManager cm= CanvasImageManager.getInstance();    
   return cm.undo();
   }
  
  @GetMapping("/redo")
  public List<Shape> makeRedo( ) {
    CanvasImageManager cm= CanvasImageManager.getInstance();
  
    return cm.redo();
   }
  
  @GetMapping("/get")
  public List<Shape> get( ) {
    CanvasImageManager cm= CanvasImageManager.getInstance();
  
    return cm.allShapes;
   }
  

  
//  @GetMapping("/download/json")
//  public ResponseEntity<Object> downloadFile() throws IOException  {
//		FileWriter filewriter =  null;
//		try {
//
//			///////////
//			///// write to file json
//	////////////////////////////////////////////
//			
//		StringBuilder filecontent = new StringBuilder();
//			filecontent.append("trying my best").append("\n");
//		
//		String filename = "try.json";
//		
//	 filewriter = new FileWriter(filename);
//		filewriter.write(filecontent.toString());
//		filewriter.flush();
//		
//		File file = new File(filename);
//////////////////////////////////////////		
//		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//		org.springframework.http.HttpHeaders headers=new org.springframework.http.HttpHeaders() ;
//		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
//		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");
//		
//		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
//		return responseEntity;
//		} catch (Exception e ) {
//			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
//		} finally {
//			if(filewriter!=null)
//				filewriter.close();
//		}
//	}
  
  
  
  //@PostMapping("/upload")
  //public void fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
  @PostMapping(value="/upload", consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})

  public void fileUpload(@RequestBody uploadImage i) {
	    CanvasImageManager cm= CanvasImageManager.getInstance();
                  cm.load(i);
	  
//		try {
//			// read and write the file to the selected location-
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get("" + file.getOriginalFilename());
//			/// making a file like the one that was uploaded 
//			Files.write(path, bytes);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	  System.out.println(i.allShapes);

	}
  @GetMapping(value="/save", produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
  public imageData sv() {
    CanvasImageManager cm= CanvasImageManager.getInstance();
    return cm.save();
   }
  
  
  
  
  
  
  
  
  
  
  

}

package eg.edu.alexu.csd.oop.draw.cs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
//		
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//
//        //read json file and convert to customer object
//        try {
//			Circle c = objectMapper.readValue(new File("cc.json"), Circle.class);
//			System.out.println(c.id);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	
//		Circle c=new Circle();
//		JSONObject circle = new JSONObject();
//        circle.put("id", "1");
//        circle.put("type", "Gupta");
//        circle.put("f_x1", "howtodoinjava.com");
//         
//        
//        
//        JSONObject ar1 = new JSONObject(); 
//        ar1.put("employee", circle);
//         
//        //Second Employee
//        JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
//        employeeDetails2.put("lastName", "Schultz");
//        employeeDetails2.put("website", "example.com");
//         
//        JSONObject ar2 = new JSONObject(); 
//        ar2.put("employee", employeeDetails2);
//         
//        //Add employees to list
//        JSONArray employeeList = new JSONArray();
//        employeeList.add(ar1);
//        employeeList.add(ar2);
//         
//        //Write JSON file
//        try (FileWriter file = new FileWriter("mygson.json")) {
// 
//            file.write(employeeList.toJSONString());
//            file.write(",");
//            file.write(employeeList.toJSONString());
//
//            file.flush();
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
	

}

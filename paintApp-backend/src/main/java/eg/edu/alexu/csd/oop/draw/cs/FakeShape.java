package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Service
@JacksonXmlRootElement
public class FakeShape extends Shape{

	@Override
	public boolean checkIfShapeIntersectAPoint(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

}

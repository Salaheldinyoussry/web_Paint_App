package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Component
@JacksonXmlRootElement
public class UserAction2 {
	public String action;
public	FakeShape old;
public	FakeShape updated;

}

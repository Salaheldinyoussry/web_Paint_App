package eg.edu.alexu.csd.oop.draw.cs;

import org.springframework.stereotype.Component;

@Component
public class UserAction {
   	public String action;
    public	Shape old;
    public	Shape updated;
    public UserAction(){}
    	UserAction(String action,Shape old,Shape updated){
    		this.action=action;
    		this.old=old;
    		this.updated=updated;
    	}
}

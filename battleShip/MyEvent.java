package battleShip;

import java.util.EventObject;

public class MyEvent extends EventObject {
	private String message;

    public MyEvent(Object source, String message) {
    	super(source);
    	this.message = message;
    }

   public MyEvent(Object source) {
    	this(source, "");
   }

   public MyEvent(String s) {
     	this(null, s);
   }

   public String getMessage() {
   		return message;
   }
 }

package net.java.hotelbooking.springboot.exception;

public class NoHotelFound extends RuntimeException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NoHotelFound() {
     super("No Hotel Found in this city");
    }
    public NoHotelFound(String s) {
        super(s);
    }
}

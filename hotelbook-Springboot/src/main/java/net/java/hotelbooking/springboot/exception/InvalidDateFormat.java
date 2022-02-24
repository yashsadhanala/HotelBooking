package net.java.hotelbooking.springboot.exception;

public class InvalidDateFormat extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDateFormat() {
        super("Enter valid date");
    }

    public InvalidDateFormat(String message) {
        super(message);
    }
}

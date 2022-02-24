package net.java.hotelbooking.springboot.exception;

public class NoRoomsAvailable extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRoomsAvailable() {
        super("No Rooms Available in this hotel ");
    }
}

package exception.email;

public class AutomaticEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public AutomaticEmailException(String msg, Throwable thrwbl) {
		super(msg, thrwbl);
	}
}

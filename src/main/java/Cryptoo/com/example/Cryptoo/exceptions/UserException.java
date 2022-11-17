package Cryptoo.com.example.Cryptoo.exceptions;

public class UserException extends RuntimeException{

	private static final long serialVersionUID = -4623269192905313851L;
	
	public UserException(String message){
		super(message);
	}

}

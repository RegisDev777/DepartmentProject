package db;

public class DbExeception extends  RuntimeException {
    
	private static final long serialversionUID = 1L;
	
	public DbExeception(String msg) {
		super(msg);
	}
	
	
}

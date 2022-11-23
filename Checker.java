
public interface Checker {
	
	public boolean process (char c);
	
	public boolean finished ();
	
	public Checker cloneHere(int pos);

}

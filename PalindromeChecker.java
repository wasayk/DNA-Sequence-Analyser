
public class PalindromeChecker implements Checker{
	private final int seqLength;
	private final int position;
	private boolean fail;
	private String patternSoFar;
	private StackADT<Character> previous;

	public PalindromeChecker(int seqLength) {
		this.seqLength = seqLength;
		position = 0;
		fail = false;
		patternSoFar = "";
		previous = new ArrayStack<>();
	}
	
	private PalindromeChecker (int seqLength, int position) {
		this.seqLength = seqLength;
		this.position = position;
		fail = false;
		patternSoFar = "";
		previous = new ArrayStack<>();
	}
	
	@Override
	public boolean process(char c) {
		patternSoFar += c;
		if (seqLength % 2 == 0) {
			if (patternSoFar.length() <= seqLength / 2) { 
				previous.push(c);
			} else {
				char temp = previous.pop();
				if (!previous.isEmpty() && temp != c) {
					fail = true;
				}
			}
		} else if (seqLength % 2 == 1 && patternSoFar.length() != (seqLength / 2) + 1) {
			if (patternSoFar.length() <= (seqLength + 1) / 2) {
		            previous.push(c);
		    } else {
		    	char temp = previous.pop();
		    	if (!previous.isEmpty() &&  temp != c) {
		    		fail = true;
		        }
		    }
		}
		
		if (patternSoFar.length() == seqLength && fail == false) {
		       return true;
		} else {
		       return false;
		}
	}


	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return fail;
	}

	@Override
	public Checker cloneHere(int pos) {
		// TODO Auto-generated method stub
		return new PalindromeChecker(seqLength, pos);
	}

	public String toString () {
		return "Palindrome(" + seqLength + ") - " + position + "{" + patternSoFar + "}";
		
	}
}

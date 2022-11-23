
public class RepeatChecker implements Checker {
	private final int seqLength;
	private final int position;
	private final int repeatNumber;
	private boolean fail;
	private String patternSoFar;
	private QueueADT<Character> previous;
	

	public RepeatChecker(int seqLength, int repeatNumber) {
		this.seqLength = seqLength;
		this.repeatNumber = repeatNumber;
		position = 0;
		fail = false;
		patternSoFar = "";
		previous = new LinkedQueue<>();
	}
	
	private RepeatChecker(int seqLength, int repeatNumber, int position) {
		this.seqLength = seqLength;
		this.repeatNumber = repeatNumber;
		this.position = position;
		fail = false;
		patternSoFar = "";
		previous = new LinkedQueue<>();
		
	}

	@Override
	public boolean process(char c) {
		patternSoFar += c;
		if (patternSoFar.length() <= seqLength) {
			previous.enqueue(c);
		
		// check to see if they match 
		} else {
			char temp = previous.dequeue();
			if (!previous.isEmpty() && temp != c) {
				fail = true;
			} else {
				previous.enqueue(c);
			}
			
		}
		if((patternSoFar.length()==seqLength*repeatNumber) && (fail==false)) {
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
		return new RepeatChecker(seqLength, repeatNumber, pos);
	}
	
	@Override
	public String toString () {
		return "Repeat(" + seqLength + "," + repeatNumber + ") - " + position + "{" + patternSoFar + "}";
	}
	
	

}

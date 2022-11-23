import java.util.Iterator;
public class DnaAnalyser {
	private LinkedQueue<Checker> checkers;
	private LinkedQueue<String> results;
	
	public DnaAnalyser(LinkedQueue<Checker> checkers) {
		this.checkers = checkers;
		results = new LinkedQueue<String>();
		// TODO Auto-generated constructor stub
	}
	
	public String search(Iterator<Character> dnaSequence) {
		// initializing a queue for active checkers
		QueueADT<Checker> activeCheckers = new LinkedQueue<>();
		//emptying past results
		while (!results.isEmpty()) {
			results.dequeue();
		}
		
		// variable to keep track of position
		int pos = 1;
		// string to concatenate
		String str = "";
		//looping through each char of dnaSequence
		while (dnaSequence.hasNext()) {
			char dnaTracker = dnaSequence.next();
			//keeping track of its position
			pos = pos + 1; 
			//concatenating char into full sequence of string
			str += dnaTracker;
			//adding a new checker to active checkers
			for (int i = 0; i < checkers.size(); i++){
				Checker temp = checkers.dequeue();
				activeCheckers.enqueue(temp.cloneHere(pos));
				checkers.enqueue(temp);
			}
				
				//having all checkers process (c)
			int sz = activeCheckers.size();
			for (int i = 0; i < sz; i++) {
				Checker temp2 = activeCheckers.dequeue();
				boolean status = temp2.process(dnaTracker);
				
				//if process (c) is successful, add its toString to results
				if (status == true) {
					results.enqueue(temp2.toString());
				//otherwise, leave as a active checker
				} else if (temp2.finished() != true) {
					activeCheckers.enqueue(temp2);
				}
				
				
				
			}
		}
		return str;

		
	}
	
	public String displayAnalysis(String dnaSequence) {
		String str = "";
		for (int i = 0; i < results.size(); i++) {
			results.dequeue();
			results.enqueue(str);
			
		}
		return str + DNA.display(dnaSequence);
	}

}

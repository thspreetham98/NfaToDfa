import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NFA {
	
	//HashMap<String, String> transition;
	ArrayList<String>symbols;
	ArrayList<String> states;
	private String startState;
	private ArrayList<String> finalStates;
	
	HashMap<String, HashMap<String, ArrayList<String>>> graph;
	
	public NFA(String[] symbols, String[] states){
		this.symbols = new ArrayList<>(Arrays.asList(symbols));
		this.symbols.add(" ");
		this.states = new ArrayList<>(Arrays.asList(states));
		//initialise graph
		initGraph();
	}
	
	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append("Symbols: " + this.symbols.toString() + "\n");
		temp.append("States: " + this.states.toString() + "\n");
		temp.append("Start state " + this.startState.toString() + "\n");
		temp.append("Final state" + this.finalStates.toString() + "\n");
		temp.append(this.graph.toString());
		return temp.toString();
	}
	
	private void initGraph() {
		this.graph = new HashMap<>();
		for(String st: this.states) {
			this.graph.put(st, new HashMap<>());
			for(String sym: this.symbols) {
				this.graph.get(st).put(sym, new ArrayList<>());
			}
			this.graph.get(st).get(" ").add(st);
		}
	}
	
	public boolean symbolsHas(String s) {
		return symbols.contains(s);
	}
	
	public boolean statesHas(String s) {
		return states.contains(s);
	}

	public String getStartState() {
		return startState;
	}

	public void setStartState(String startState) {
		this.startState = startState;
	}

	public ArrayList<String> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(String[] finalStates) {
		this.finalStates = new ArrayList<>(Arrays.asList(finalStates));
	}

	public void insertTransition(String[] temp) {
		// TODO Auto-generated method stub
		if(temp.length==3) {
			//state1->temp[0], symbol->temp[1], state2->temp[2]
			if(this.statesHas(temp[0]) && this.statesHas(temp[2]) && (this.symbolsHas(temp[1]) || temp[1].equals("L"))) {
				if(!temp[1].equals("L")) {
					this.graph.get(temp[0]).get(temp[1]).add(temp[2]);
					System.out.println("Entry Successful");
				}else {
					this.graph.get(temp[0]).get(" ").add(temp[2]);
				}
				
			}
			else {
				System.out.println("Invalid Entry");
			}
		}
		else {
			System.out.println("Invalid entry");
		}
	}

	public ArrayList<String> find(String st, String sym) {
		ArrayList<String> reached = new ArrayList<>();
		ArrayList<String> rLambda = reachedWithLambda(st);
		for(String i: rLambda) {
			for(String j: this.graph.get(i).get(sym)) {
				if(!reached.contains(j)) {
					reached.add(j);
				}
			}
		}
		return reached;
	}

	private ArrayList<String> reachedWithLambda(String st) {
		ArrayList<String> reachedWithLambda = new ArrayList<>();
		ArrayList<String> queue = new ArrayList<>();
		queue.add(st);
		while(queue.size()>0) {
			for(String nextState: this.graph.get(st).get(" ")) {
				if(!reachedWithLambda.contains(nextState)) {
					reachedWithLambda.add(nextState);
					queue.add(nextState);
				}
			}
			queue.remove(0);
			reachedWithLambda.sort(null);
		}
		return reachedWithLambda;
	}

}

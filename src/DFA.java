import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DFA {
	
	//HashMap<String, String> transition;
	ArrayList<String>symbols = new ArrayList<>();
	ArrayList<String> states = new ArrayList<>();
	private ArrayList<String> startState = new ArrayList<>();
	private ArrayList<ArrayList<String>> finalStates = new ArrayList<>();
	
	HashMap<ArrayList<String>, HashMap<String, ArrayList<String>>> graph = new HashMap<>();
	
	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append("Symbols: " + this.symbols.toString() + "\n");
		temp.append("States: " + this.states.toString() + "\n");
		temp.append("Start state " + this.startState.toString() + "\n");
		temp.append("Final state" + this.finalStates.toString() + "\n");
		temp.append(this.graph.toString());
		return temp.toString();
	}
	
	public boolean symbolsHas(String s) {
		return symbols.contains(s);
	}
	
	public boolean statesHas(String s) {
		return states.contains(s);
	}

	public ArrayList<String> getStartState() {
		return startState;
	}

	public void setStartState(ArrayList<String> startState) {
		this.startState = startState;
	}

	public ArrayList<ArrayList<String>> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(ArrayList<String> finalStates) {
		this.finalStates = new ArrayList<>(Arrays.asList(finalStates));
	}
	
	public void convertNFA(NFA nfa) {
		for(String sym: nfa.symbols) {
			if(!sym.equals(" ")) {
				this.symbols.add(sym);
			}
		}
		ArrayList<ArrayList<String>> newEl = new ArrayList<ArrayList<String>>();
		newEl.add(new ArrayList<>());
		newEl.get(0).add(nfa.getStartState());
		this.startState = newEl.get(0);
		while(newEl.size()>0) {
			this.graph.put(newEl.get(0), new HashMap<>());
			for(String st: newEl.get(0)) {
				if(nfa.getFinalStates().contains(st)) {
					this.finalStates.add(newEl.get(0));
					break;
				}
			}
			for(String sym: this.symbols) {
				this.graph.get(newEl.get(0)).put(sym, new ArrayList<>());
				for(String st: newEl.get(0)) {
					for(String k: nfa.find(st, sym)) {
						if(!this.graph.get(newEl.get(0)).get(sym).contains(k)) {
							this.graph.get(newEl.get(0)).get(sym).add(k);
						}
					}
				}
				this.graph.get(newEl.get(0)).get(sym).sort(null);
				if((!this.graph.containsKey(this.graph.get(newEl.get(0)).get(sym))) && this.graph.get(newEl.get(0)).get(sym).size()>0) {
					newEl.add(this.graph.get(newEl.get(0)).get(sym));
				}
			}
			newEl.remove(0);
		}
	}

}

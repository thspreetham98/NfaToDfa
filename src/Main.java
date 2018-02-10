
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter symbols of language used. L is reserved for lambda");
		String[] symbols = sc.nextLine().split(" ");
		System.out.println("Enter names of states");
		String[] states = sc.nextLine().split(" ");
		String[] temp;
		NFA nfa = new NFA(symbols, states);
		while(true) {
			System.out.println("enter transitions -> initial state symbol_consumed next state\n-1 to end");
			temp = sc.nextLine().split(" ");
			if(temp.length==1 && temp[0].equals("-1")) {
				break;
			}
			nfa.insertTransition(temp);
		}
		System.out.println("Enter start state");
		nfa.setStartState(sc.nextLine());
		System.out.println("Enter final state");
		nfa.setFinalStates(sc.nextLine().split(" "));
		System.out.println(nfa);
		
		//convert to dfa
		DFA dfa = new DFA();
		dfa.convertNFA(nfa);
		System.out.println(dfa);
		sc.close();
	}

}

package Diablo;

import Diablo.Items.Item;

public class Dialogue {
	/*
	 * an object representing dialogue
	 * used to construct different types of dialogue
	 * and together combine to build dialogue logic
	 * Types of dialogue:
	 * dialogue
	 * dialogue w/ transitions
	 * dialogue w/ responses
	 * dialogue w/ transition & objective
	 */
	private String dialogue;
	private Dialogue [] responses=null;
	private Objective objective=null;
	private Dialogue transition=null;
	private Item reward;
	Dialogue(String dialogue){
	/*
	 * standard dialogue constructor
	 */
		this.dialogue=dialogue;
	}
	Dialogue(String dialogue, Dialogue [] responses){
	/*
	 * dialogue with response(s) constructor
	 */
		this.dialogue=dialogue;
		this.responses=responses;
	}
	Dialogue(String dialogue, Dialogue transition){
	/*
	 * dialogue with transition constructor
	 */
		this.dialogue=dialogue;
		this.transition=transition;
	}
	Dialogue(String dialogue, Objective objective){
	/*
	 * dialogue with objective constructor
	 */
		this.dialogue=dialogue;
		this.objective=objective;
		
	}
	
	/*
	 * getters and setters
	 */
	public void addResponse(Dialogue responses) {
		
	}	
	public void addResponses(Dialogue[] responses) {
		this.responses=responses;
	}	
	public Dialogue[] getResponses() {
		return responses;
	}	
	
	public void setTransition(Dialogue transition) {
		this.transition=transition;
	}
	public Dialogue getTransition() {
		return transition;
	}
	
	public void setObjective(Objective objective) {
		this.objective=objective;
	}
	public Objective getObjective() {
		return objective;
	}
	
	public void setDialogue(Objective objective) {
		this.objective=objective;
	}
	public String getDialogue() {
		return dialogue;
	}
	
}

package Diablo;

public class DialogueManager {
	
	//stage current Entity
	//d=entity.getdialogue()
	//call UI manager
	//print (dialogue d)
	//wait for user to finish
	//check(d.getobjective) if true => d=d.gettransition
	//if false => show(check(d.responses)) if false=>d=d.getTransition
	//wait for player to choose
	//check(responses(i).getObjective)) if true=> d=r.getTransiton
	//if false or null => d=d
	//entity.setDialogue(d)
	private Entity entity;
	private DialogueUI ui;
	private Dialogue d;
	
	DialogueManager(Entity entity,DialogueUI ui){
		this.entity=entity;
		d=entity.getDialogue();
		this.ui=ui;
	}
	
	public void manageDialogue(Dialogue d) {
		if(checkObjective()==true) {
			return;
		}
		if(checkResponses()==true) {
			return;
		}
		if(checkTransition()==true) {
			return;
		}	
		//entity.setDialogue(d);
	}

	private boolean checkObjective() {
		if(d.getObjective()==null)
		return false;
		
	((Objective) d.getObjective()).doObjective();
		return true;
	}
	
	
	private boolean checkResponses() {
		if( d.getResponses()==null)
			return false;
		
		Dialogue [] responses = d.getResponses();
		String [] dialogues= new String[responses.length];
		ui.stageResponses(d);
		return true;
	}
	
	
	private boolean checkTransition() {
		if(d.getTransition()==null) {
			return false;
		}
		System.out.print("here");
		entity.setDialogue(d.getTransition());
		return true;
	}
	
	public void chooseRespone(int i) {
		i--;
			Dialogue [] responses= d.getResponses();
		if(responses[i].getObjective()!=null) {
			((Objective) responses[i].getObjective()).doObjective();
			return;
		}
		if(responses[i].getTransition()!=null) {
			ui.game.dialogue=false;
			entity.setDialogue(responses[i].getTransition());
			return;
		}
		ui.game.dialogue=false;
		
	}
	
}

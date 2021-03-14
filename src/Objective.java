package Diablo;

import Diablo.items.Item;

public class Objective {
	//private Shop shop;
	//private Item item;
	private Dialogue transition=null;
	private Item reward=null;
	 Objective(){
	}
	public void doObjective() {
	 }
	public Dialogue getTransition() {
		return transition;
	}
}

class DialogueObjective extends Objective{
	private Dialogue d;
	private Game game;
	private Dialogue transition=null;
	DialogueObjective(Dialogue d,Game game){
		this.d=d;
		transition=d.getTransition();
		this.game=game;
	}
	
	@Override
	public void doObjective(){
		game.dialogueObj.NPC=true;
		game.dialogueObj.setDialogue(d);
		game.getEntityList().get(0).addObjective(this);
		
	}
	@Override
	public Dialogue getTransition() {
		return transition;
	}
}

class ShopObjective extends Objective{
	private Item reward;
	private Dialogue transition=null;
	@Override
	public void doObjective(){
		//open shop
	}
	@Override
	public Dialogue getTransition() {
		return transition;
	}
}

class ItemObjective extends Objective{
	private Item reward=null;
	private Dialogue transition=null;
	@Override
	public void doObjective(){
		//check entity for item
		//return reward to player
	}
	@Override
	public Dialogue getTransition() {
		return transition;
	}
}
class QuestObjective extends Objective{
	private Dialogue transition=null;
	private Objective checkQuest;
	private DialogueObjective questDialogue;
	private Game game;
	QuestObjective(Game game,Objective checkQuest, Objective questDialogue){
		this.game=game;
	}
	public void doObjective() {
		//check entity for an Objective
		if(game.getEntityList().get(0).getQuestlog().contains(checkQuest)) {
			questDialogue.doObjective();
		}
		//return reward to player
	}
}
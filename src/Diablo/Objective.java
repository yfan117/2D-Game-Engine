package Diablo;

import Diablo.Items.Item;

public class Objective {
	//private Shop shop;
	//private Item item;
	private Dialogue transition=null;
	private Item reward=null;
	public String name;
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
	public String name;
	
	DialogueObjective(Dialogue d,String name,Game game){
		
		this.d=d;
		this.game=game;
		this.name=name;
		
	}

	@Override
	public void doObjective(){
		game.getEntityList().get(0).addQuest(name);
		game.dialogueObj.NPC=true;
		game.dialogueObj.setDialogue(d);
		
		
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
	private String questName;
	private DialogueObjective questDialogue;
	private Game game;
	private Item reward;
	public String name;
	QuestObjective(Game game,String questName, Objective questDialogue,Item reward){
		this.game=game;
		this.questName=questName;
		this.questDialogue=(DialogueObjective) questDialogue;
		this.reward=reward;
	}
	public void doObjective() {
		//check entity for an Objective
		if(game.getEntityList().get(0).getQuestlog()==null) {
			return;
		}
		for(int i =0; i < game.getEntityList().get(0).getQuestlog().size();i++) {
			System.out.println(game.getEntityList().get(0).getQuestlog().get(i));
			System.out.println(questName);
			if(game.getEntityList().get(0).getQuestlog().get(i).equals(questName)) {
			System.out.println("quest found");
			questDialogue.doObjective();//open the dialogue related to the quest
			//return reward to player
			System.out.println("you receieved "+ reward.getName());
			game.getEntityList().get(0).addItem(1, reward);
			return;
		}
			
		}
	}
}
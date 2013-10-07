
public class Vorace extends Neuneu {
	public Vorace(Loft l, int x, int y) {
		super(l, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public boolean action(){
		if(!this.getActionRealisee()){
			Nourriture miam = this.canEat(this.getLoft().getNourritures());
			//Par définition le Vorace cherche uniquement à se nourrire.
			if(miam != null){
				this.manger(miam);
				return true;
			}
			else{
				this.seDeplacer();
				return false;
			}
		}
		else{
			return true;
		}
	}
	
	public void seDeplacer(){
		//Le vorace se déplace vers la nourriture la plus proche.
		Nourriture nourr = this.findClosestFood(this.getLoft().getNourritures());
		if(nourr != null){
			int positionOfNourr[] = nourr.getPosition();
			int dX = (positionOfNourr[0]-position[0])/Math.max(1, positionOfNourr[0]-position[0]);
			int dY = (positionOfNourr[1]-position[1])/Math.max(1, positionOfNourr[1]-position[1]);
			int newPosition[] = {position[0]+dX,position[1]+dY};
			this.setPosition(newPosition);
			this.perdreEnergieDeplacement();
		}
		else
		{
			//cas où il ne reste plus de nourriture
			super.seDeplacer();
		}
	}
}

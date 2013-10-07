
public class Erratique extends Neuneu {
	public Erratique(Loft l, int x, int y) {
		super(l, x, y);
	}
	
	public boolean action(){
		if(this.getActionRealisee()==false){
			Neuneu partenaire = this.canReproduce();
			Nourriture miam = this.canEat(this.getLoft().getNourritures());
			if(partenaire != null){		//Dès qu'il peut se reproduire, il le fait
				this.seReproduire(partenaire);	//Test de changement pour GIT
				this.setActionRealisee(true);
				partenaire.setActionRealisee(true);
				return true;
			}
			else if(miam != null){
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

}

import java.util.List;


public abstract class Neuneu extends Nourriture {
	
	protected static int energiePourReproduction=60;
	private static int totalPopulation = 0;
	protected static int energieInitiale = 100;
	protected boolean actionRealisee;
	protected int id;
	
	public Neuneu(Loft l, int x, int y){
		super(l, energieInitiale, x, y);
		id=++Neuneu.totalPopulation;
	}
	
	/**
	 * @return
	 * Cette méthode est appelée par CycleDeVie de Loft.
	 * Elle détermine une action à réaliser parmi
	 */
	
	public void seReproduire(Neuneu n){
		setEnergie(getEnergie()-Neuneu.energiePourReproduction);
		this.actionRealisee = true;
		n.setEnergie(n.getEnergie()-Neuneu.energiePourReproduction);
		n.setActionRealisee(true);
		//Ci-dessous, comment décider du type de neuneu engendré?
		Neuneu bebeNeuneu = new Erratique(this.loft, n.getPosition()[0], n.getPosition()[1]);
		//Le bébé neuneu naît dans une case adjacente
		int newPos[] = new int[2];
		do{
			newPos[0]=position[0] - 1 + (int)(Math.random()*3);
			newPos[1]= position[1] - 1 + (int)(Math.random()*3);
		}while(!this.loft.checkPosition(newPos[0], newPos[1]));
		bebeNeuneu.setPosition(newPos);
		this.loft.ajouterNeuneu(bebeNeuneu);
		this.seDeplacer();	//Les neuneus se déplacent après reproduction.
		n.seDeplacer();
	}
	
	public void manger(Nourriture n){
		this.augmenterEnergie(n.getEnergie());
		n.diminuerEnergie(n.getEnergie());
	}
	
	public Neuneu canReproduce(){
		List<Neuneu> neuneus = this.getLoft().getNeuneus();
		int position[] = this.getPosition();
		//On vérifie que le neuneu a assez d'énergie.
		if(this.getEnergie()>=Neuneu.energiePourReproduction){
			for(Neuneu n:neuneus){
				int positionOfN[] = n.getPosition();
				if(position[0]==positionOfN[0] && position[1]==positionOfN[1] && n!=this){
					//On vérifie que le neuneu partenaire a assez d'énergie
					if(n.getEnergie()>=Neuneu.energiePourReproduction){
						return n;
					}
				}
			}
			return null;
		}
		else{
			return null;	//Si aucun autre neuneu à cette case, pas de reproduction possible. On renvoit null.			
		}
	}
	
	public Nourriture canEat(List<Nourriture> nourritures){
		//On doit envoyer en paramètres les aliments possibles du neuneus
		int position[] = this.getPosition();
		for(Nourriture nourr:nourritures){
			int positionOfNourr[] = nourr.getPosition();
			if(position[0]==positionOfNourr[0] && position[1]==positionOfNourr[1] && nourr!=this){
				return nourr;
			}
		}
		return null;
	}
	
	public Nourriture findClosestFood(List<Nourriture> nourritures){
		Loft loft = this.getLoft();
		int distanceMin = loft.getH()+loft.getW()+1;
		Nourriture closest = null;
		for(Nourriture nourr:nourritures){
			int distanceAvecNourr = this.distanceAvecNourriture(nourr);
			if (distanceAvecNourr<distanceMin){
				distanceMin = distanceAvecNourr;
				closest = nourr;
			}
		}
		return closest;
	}
	
	public Neuneu findClosestNeuneu(List<Neuneu> neuneus){
		Loft loft = this.getLoft();
		int distanceMin = loft.getH()+loft.getW()+1;
		Neuneu closest = null;
		for(Neuneu n:neuneus){
			if(n!=this){
				int distanceAvecNourr = this.distanceAvecNourriture(n);
				if (distanceAvecNourr<distanceMin){
					distanceMin = distanceAvecNourr;
					closest = n;
				}
			}
		}
		return closest;
	}

	public boolean getActionRealisee() {
		return actionRealisee;
	}

	public void setActionRealisee(boolean actionRealisee) {
		this.actionRealisee = actionRealisee;
	}
	
	public void seDeplacer(){
		//Les Erratiques se déplacent de façon totalement aléatoire.
		int position[] = this.getPosition();
		int x=position[0];
		int y=position[1];
		int newX,newY;
		do{
			newX=x;
			newY=y;
			newX += -1+(int)(Math.random()*3);
			newY += -1+(int)(Math.random()*3);
		}while(!this.getLoft().checkPosition(newX, newY));
		int newPosition[] = {newX, newY};
		this.setPosition(newPosition);
		this.perdreEnergieDeplacement();
	}
	
	protected void perdreEnergieDeplacement(){
		this.diminuerEnergie(5);
	}
	
	public boolean checkAlive(){
		return !(energie<=0);
	}
	
	public void dessiner(){
		System.out.println("Neuneu " + id + " est à la position " + position[0] +"," + position[1] + " avec l'énergie "+ energie);
	}
	
	public abstract boolean action();
}

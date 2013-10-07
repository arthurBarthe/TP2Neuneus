
public class Lapin extends Neuneu {

	public Lapin(Loft l, int x, int y) {
		super(l, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public boolean action(){
		if(!this.getActionRealisee()){
			Neuneu partenaire = this.canReproduce();
			if(partenaire != null){		//Dès qu'il peut se reproduire, il le fait
				this.seReproduire(partenaire);
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
		//Le lapin se déplace vers le neuneu le plus proche.
		Neuneu neuneu = this.findClosestNeuneu(this.getLoft().getNeuneus());
		int positionOfNeuneu[] = neuneu.getPosition();
		int dX = (positionOfNeuneu[0]-position[0])/Math.max(1, positionOfNeuneu[0]-position[0]);
		int dY = (positionOfNeuneu[1]-position[1])/Math.max(1, positionOfNeuneu[1]-position[1]);
		int newPosition[] = {position[0]+dX,position[1]+dY};
		this.setPosition(newPosition);
		this.perdreEnergieDeplacement();
	}
}

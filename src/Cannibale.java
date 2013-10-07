import java.util.LinkedList;
import java.util.List;


public class Cannibale extends Vorace {
	public Cannibale(Loft l, int x, int y) {
		super(l, x, y);
		// TODO Auto-generated constructor stub
	}

	public boolean action(){
		if(!this.getActionRealisee()){
			if(!super.action()){
				List<Nourriture> neuneus;
				neuneus = new LinkedList<Nourriture>();
				for(Neuneu n:this.getLoft().getNeuneus()){
					neuneus.add((Nourriture)n);
				}
				Nourriture miam = this.canEat(neuneus);
				if(miam != null){
					this.manger(miam);
					this.setActionRealisee(true);
					System.out.println("Neuneu " + this.id + " a mangé " + ((Neuneu)miam).id);
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
		else{
			return true;
		}
	}
}

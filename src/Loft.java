import java.util.LinkedList;
import java.util.List;


public class Loft {

	/**
	 * @param args
	 */
	protected int w,h;	//width, height:
	protected int numCycleDeVie = 0;
	protected static int numCyclesDeVieMax=50;
	protected List<Nourriture> nourritures;
	protected List<Nourriture> nourrituresMangees;
	protected List<Neuneu> neuneus;
	protected List<Neuneu> neuneusNes;
	protected List<Neuneu> neuneusMorts;
	
	public static void main(String[] args) {
		Loft loft = new Loft(100,100);
		loft.Initialisation();
		while(loft.getNumCycleDeVie()<Loft.numCyclesDeVieMax){
			loft.cycleDeVie();
		}
	}
	
	public int getNumCycleDeVie() {
		return numCycleDeVie;
	}

	public void setNumCycleDeVie(int numCycleDeVie) {
		this.numCycleDeVie = numCycleDeVie;
	}

	public Loft(int w, int h){
		this.w = w;
		this.h = h;
		nourritures = new LinkedList<Nourriture>();
		nourrituresMangees = new LinkedList<Nourriture>();
		neuneus = new LinkedList<Neuneu>();
		neuneusNes = new LinkedList<Neuneu>();
		neuneusMorts = new LinkedList<Neuneu>();
	}
	
	public void Initialisation(){

		//Je vais générer un nombre de neuneus entre min et max
		
		int min=5;
		int max=10;
		
		int nbre_erratiques=min + (int)(Math.random() * ((max - min) + 1));
		int nbre_voraces= min + (int)(Math.random() * ((max - min) + 1));
		int nbre_cannibales=min + (int)(Math.random() * ((max - min) + 1));
		int nbre_lapins=min + (int)(Math.random() * ((max - min) + 1));;
		
		//Je crée un tableau de w * h qui me servira à connaître les cases inoccupées, je l'initialise à 0.
		int[][] tab_position= new int[w][h];
		for (int i=0;i<w;i++){
			for (int j=0;j<h;j++) {
					tab_position[i][j]=0;
			}
		}
		
		//Initialisation des erratiques
		for (int i=0;i<nbre_erratiques; i++){
			//Je vérifie qu'il n'y a pas de neuneu sur la case où je le crée
			int a=(int)(Math.random() * w);
			int b=(int)(Math.random() * h);
			
			while (tab_position[a][b]==1){
				a=(int)(Math.random() * w);
				b=(int)(Math.random() * h);
			}
			
			neuneus.add(new Erratique(this, a,b));
			tab_position[a][b]=1;
			
		}
		
		//Initialisation des voraces
				for (int i=0;i<nbre_voraces; i++){
					//Je vérifie qu'il n'y a pas de neuneu sur la case où je le crée
					int a=(int)(Math.random() * w);
					int b=(int)(Math.random() * h);
					
					while (tab_position[a][b]==1){
						a=(int)(Math.random() * w);
						b=(int)(Math.random() * h);
					}
					
					neuneus.add(new Vorace(this, a,b));
					tab_position[a][b]=1;
					
				}
				
			//Initialisation des cannibales
			for (int i=0;i<nbre_cannibales; i++){
				//Je vérifie qu'il n'y a pas de neuneu sur la case où je le crée
				int a=(int)(Math.random() * w);
				int b=(int)(Math.random() * h);
					
				while (tab_position[a][b]==1){
					a=(int)(Math.random() * w);
					b=(int)(Math.random() * h);
					}
					
				neuneus.add(new Cannibale(this, a,b));
				tab_position[a][b]=1;
					
				}
			
			//Initialisation des lapins
			for (int i=0;i<nbre_lapins; i++){
				//Je vérifie qu'il n'y a pas de neuneu sur la case où je le crée
				int a=(int)(Math.random() * w);
				int b=(int)(Math.random() * h);
				
				while (tab_position[a][b]==1){
					a=(int)(Math.random() * w);
					b=(int)(Math.random() * h);
				}
				
				neuneus.add(new Lapin(this, a,b));
				tab_position[a][b]=1;
				
			}
			
			//Initialisation des neuneus terminée, on passe à celle de la nourriture.
			//Je donne des bornes min et max parmi lesquelles la quantite de nourriture
			//sera comprise (de manière aléatoire).
			
			min=10;
			max=20;
			int quantite=(int) (min + Math.random() * (max - min));
			int energie_max=33;  //La quantite d'énergie fournie par nourriture, modifiable. 
			
			for (int i=0;i<quantite;i++){
				
				int a=(int)(Math.random() * w  );
				int b=(int)(Math.random() * h  );
				
				while (tab_position[a][b]==1){
					a=(int)(Math.random() * w  );
					b=(int)(Math.random() * h );
				}
				
				nourritures.add(new Nourriture(this,energie_max, a , b));
				tab_position[a][b]=1;
				
			}			
	}
	
	public void cycleDeVie(){
		System.out.println(this.numCycleDeVie);
		for(Neuneu n:neuneus){
			n.setActionRealisee(false);
		}
		//Les neuneus vivent leur vie
		for(Neuneu n:neuneus){
			n.action();
		}
		System.out.println(this.numCycleDeVie);
		
		neuneus.addAll(neuneusNes);
		
		for(Neuneu n:neuneus){
			if (!n.checkAlive()){
				neuneusMorts.add(n);
			}
		}
		
		for(Nourriture nourr:nourritures){
			if(!nourr.checkAlive()){
				nourrituresMangees.add(nourr);
			}
		}
		
		neuneus.removeAll(neuneusMorts);
		neuneusMorts.removeAll(neuneusMorts);
		nourritures.removeAll(nourrituresMangees);
		nourrituresMangees.removeAll(nourrituresMangees);

		//On affiche la carte du loft
		for(Dessinable d:neuneus){
			d.dessiner();		// TODO supprimer l'interface dessinable, la remplacer par une classe abstraite objet du loft
		}
		
		this.numCycleDeVie++;
	}
	
	public void ajouterNeuneu(Neuneu n){
		this.neuneusNes.add(n);
	}
	
	public void supprimerNeuneu(Neuneu n){
		this.neuneusMorts.add(n);
	}
	
	public boolean checkPosition(int x, int y){
		if (x<0 || x>=w || y<0 || y>=h){
			return false;
		}
		else{
			return true;
		}
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public List<Nourriture> getNourritures() {
		return nourritures;
	}

	public void setNourritures(List<Nourriture> nourritures) {
		this.nourritures = nourritures;
	}
	
	public List<Neuneu> getNeuneus() {
		return neuneus;
	}

	public void setNeuneus(List<Neuneu> neuneus) {
		this.neuneus = neuneus;
	}

}

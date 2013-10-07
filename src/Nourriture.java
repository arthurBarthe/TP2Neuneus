
public class Nourriture implements Dessinable {
	
	protected int energie;
	protected int[] position;
	protected Loft loft;
	protected String name;
	
	public Nourriture(Loft l, int energie, int x, int y){
		this.energie = energie;
		this.position = new int[2];
		this.position[0] = x;
		this.position[1] = y;
		this.loft = l;
		this.name="neuneu";
	}
	
	public void augmenterEnergie(int quantite){
		this.energie += quantite;
	}
	
	public void diminuerEnergie(int quantite){
		this.energie -= quantite;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}
	
	public Loft getLoft(){
		return loft;
	}
	
	public void setLoft(Loft loft){
		this.loft = loft;
	}
	
	public int distanceAvecNourriture(Nourriture nourr){
		int posOfNourr[] = nourr.getPosition();
		return Math.abs(position[0]-posOfNourr[0]) + Math.abs(position[1]-posOfNourr[1]);
	}

	public void dessiner() {
		System.out.println("Nourriture" + name + " est a la position " + position[0] + "," + position[1] + " avec énergie " + this.energie);
	}
	
	public boolean checkAlive(){
		return !(energie<=0);
	}
}

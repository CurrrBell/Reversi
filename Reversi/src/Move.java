
public class Move {
	int X;
	int Y;
	boolean tested;
	
	public Move(){
		
	}
	
	public Move(int x, int y){
		this.X = x;
		this.Y = y;
		this.tested = false;
	}
	
	public void nowTested(){
		this.tested = true;
	}
	
	public boolean isNew(){
		return this.tested;
	}
	
	public int getX(){
		return this.X;
	}
	
	public int getY(){
		return this.Y;
	}
	
	public String toString(){
		return "X: " + this.X + "\tY: " + this.Y;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Move){
			if(((Move) o).getX() == this.getX() && ((Move) o).getY() == this.getY()){
				return true;
			}
		}
		
		return false;
	}
}

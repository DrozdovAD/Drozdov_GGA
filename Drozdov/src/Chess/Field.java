package Chess;

public class Field {
	
	
	public char [][] field={{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},};
	
	
	
public void show(){
	for (int i=0;i<8;i++){
		for(int j=0;j<8;j++){
			switch (field[i][j]) {
			case 0:
				System.out.print("_ ");
				break;
			case 1:
				System.out.print("* ");
				break;

			default:
				System.out.print(field[i][j]+" ");
				break;
			}
		}
		System.out.println();
	}
	System.out.println("xxxxxxxxxxxx");
}
}

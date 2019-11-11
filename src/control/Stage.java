package control;

import java.util.ArrayList;

import elements.Element;
import elements.PieceL;
import utils.Consts;

public class Stage {
	protected int matrix[][] = new int[Consts.NUM_ROW+4][Consts.NUM_COL];
	private final ArrayList<Element> blocks = new ArrayList<Element>(); 
	private final GameScreen gs;
	private int atual;
	
	public Stage(GameScreen s) {
		this.gs = s;			
	}
	
	public void init() {
		PieceL aux = new PieceL(gs, 0, "red.png");
		this.addElement(aux);
	}
	
	public ArrayList<Element> getBlocks() {
		return blocks;
	}
	
	public void addElement(Element elem) {
		System.out.println(elem);
		blocks.add(elem);
	}
	
	public void removeElement(Element elem) {
		blocks.remove(elem);
	}
	
	public Element getControl() {
		return this.blocks.get(atual);
	}
	
	private void updateMatrix(int x, int y, int val) {
		this.matrix[x][y] = val;
	}
	
	public void createNewBlock() {
		for(int i = 0; i < this.getControl().getSubElemen().size(); i++) {
			int X = this.getControl().getSubElemen().get(i).getX();
			int Y = this.getControl().getSubElemen().get(i).getY();
			this.updateMatrix(X, Y, 1);
			System.out.println();			
			
			for(int j = 0; j < Consts.NUM_ROW; j++) {
				for(int k = 0; k < Consts.NUM_COL; k++) {
					System.out.print(matrix[j][k] + " ");
				}
				System.out.print("\r\n");
			}
		}
		atual++;
		int fator = 0;
				
		if(matrix[6][1] == 1)
			fator = 2;
		else if(matrix[5][1] == 1)
			fator = 3;
		else if(matrix[4][1] == 1)
			fator = 4;
		PieceL aux = new PieceL(gs, fator, "red.png");
		this.addElement(aux);		
		System.out.println(this.blocks.size());
	}
	
	public int[][] getActualMatrix() {
		return this.matrix;
	}
	
	public void verifyAndKill() {
		for(int i = 0; i < Consts.NUM_ROW; i++) {
			boolean limpaLinha = true;
			int j = 0;
			
			while(j < Consts.NUM_COL) {
				if(this.getActualMatrix()[i][j] == 0) {
					limpaLinha = false;
					break;
				}
				j++;
			}
			
			if(limpaLinha == true) {
				deslocaAbaixo(i);
			}
		}
	}
	
	private void deslocaAbaixo(int row) {
		for(int i = row; i > 0; i--) {
			for(int j = 0; j < Consts.NUM_COL; j++) {
				int val = this.getActualMatrix()[i-1][j];
				this.updateMatrix(i, j, val);
			}
		}
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).corrigePeca(row);
		}
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).moveDown(this.getActualMatrix());
		}
	}
}

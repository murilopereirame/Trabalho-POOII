package elements;

import java.awt.Graphics;
import java.util.ArrayList;

import control.*;
import utils.Consts;

public class PieceL extends Element{
    protected int elems[][] = new int[4][4];
    protected int cX;
    private int fator = 0;
    
    protected int position = 0;
    
    public PieceL(GameScreen s, int fator, String imageName) {
    	super(imageName);
    	//Pe�a central 1X1
    	
    	elems[0][1] = 1;
    	elems[1][1] = 1;
    	elems[2][1] = 1;
    	elems[3][1] = 1;    
    	
    	this.fator = fator;
    	
    	create(s);
    	
    }
    
    public void create(GameScreen s) {    	    
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			if(elems[i][j] == 1) {
    				if(i == 1 && j == 1)
    					cX = pieces.size() - 1;
    				
    				Lolo l1;
    				l1 = new Lolo("red.png");
    		    	l1.setPosition(i-fator, j);
    		    	pieces.add(l1);
    			}
    		}
    	}
    	
    	s.addElement(pieces.get(cX));
    	for(int i = 0; i < pieces.size(); i++) {
    		if(i != cX)
    			s.addElement(pieces.get(i));
    	}
    }   
    
    public String getPos() {
    	String pos = "";
    	for(int i = 0; i <pieces.size(); i++) {
    		pos = pos + "X: " + pieces.get(i).getX() + " Y: " + pieces.get(i).getY() + "\n";
    	}
    	return pos;
    }   

	@Override
	public void autoDraw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}


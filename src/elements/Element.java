package elements;

import utils.Consts;
import utils.Position;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public abstract class Element implements Serializable{
    protected ArrayList<Lolo> pieces = new ArrayList<Lolo>();
	protected int position = 0;
    protected ImageIcon imageIcon;
    protected Position pos;
    protected boolean isTransposable; // Pode passar por cima?
    protected boolean isMortal;       // Se encostar, morre?   
    private boolean gameOver = false;
    
    public Element(String imageName) {
        this.pos = new Position(1, 1);
        this.isTransposable = true;
        this.isMortal = false;
        
        try {
            imageIcon = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.IMG_PATH + imageName);
            Image img = imageIcon.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
            imageIcon = new ImageIcon(bi);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean overlap(Element elem) {
        double xDist = Math.abs(elem.pos.getX() - this.pos.getX());
        double yDist = Math.abs(elem.pos.getY() - this.pos.getY());
        
        if (xDist < 1.0 && yDist < 1.0)
            return true;
        else
            return false;
    }

    public String getStringPosition() {
        return ("(" + pos.getX() + ", " + pos.getY() + ")");
    }
    
    public int getX() {
    	return pos.getX();
    }
    
    public int getY() {
    	return pos.getY();
    }
    
    public boolean setPosition(int x, int y) {
        return pos.setPosition(x, y);
    }

    public boolean isTransposable() {
        return isTransposable;
    }

    public void setTransposable(boolean isTransposable) {
        this.isTransposable = isTransposable;
    }
    
    public boolean isMortal() {
        return isMortal;
    }

    abstract public void autoDraw(Graphics g);

    public boolean moveDown(int matrix[][]) {
    	if(position == 0) {
    		int downX = pieces.get(pieces.size() - 1).getX();
    		int downY = pieces.get(pieces.size() - 1).getY();
    		System.out.println(downY);
    		
    		if((downX + 1 == Consts.NUM_ROW) || (downY + 1 == Consts.NUM_COL+1))
    			return false;
    		
    		if(matrix[downX+1][downY] == 1 && matrix[Consts.NUM_ROW-1][downY] == 1 && downX < 4) {
    			this.setGameOver(true);
    			System.out.println("TOPO");
    			return false;
    			
    		}
    		
    		if(matrix[downX+1][downY] == 1 && matrix[Consts.NUM_ROW-1][downY] == 1 && downX < 4) { 
    			System.out.println("TOPO"); 
    			return false;    
    			
    		}
    		
    		if(matrix[downX+1][downY] == 1) {
    			return false;
    		}
    	}
        
    	
    	for(int i = 0; i < pieces.size(); i++) {
    		
    		if(!pieces.get(i).pos.moveDown()) {
    			for(int j = 0; j < i; j++) {
    				pieces.get(j).pos.moveUp();
    			}
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean moveLeft(int matrix[][]) {
    	if(position == 0) {
    		int downX = pieces.get(pieces.size() - 1).getX();
    		int downY = pieces.get(pieces.size() - 1).getY();
    		
    		if((downY - 1 == -1) || (downX - 1 == Consts.NUM_ROW))
    			return false;
    		
    		System.out.println("D-1: " + (downY - 1));
    		
    		if(matrix[downY][Consts.NUM_COL - 1] == 1 && downY < 1) {    			
    			if(downY > 0) {
	    			if(matrix[downX][downY-1] == 1) {
	        			System.out.println("Esquerda");
	        			return false;
	        			
	        		}
    			}
    		}
    		
    		if(downY > 0) {
	    		if(matrix[downX][downY-1] == 1) {
	    			return false;
	    		}
    		}
    	}
    	
    	for(int i = 0; i < pieces.size(); i++) {
    		
    		if(!pieces.get(i).pos.moveLeft()) {
    			for(int j = 0; j < i; j++) {
    				pieces.get(j).pos.moveRight();
    			}
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean moveRight(int matrix[][]) {
    	if(position == 0) {
    		int downX = pieces.get(pieces.size() - 1).getX();
    		int downY = pieces.get(pieces.size() - 1).getY();
    		
    		if((downY + 1 == Consts.NUM_COL+1) || (downX - 1 == Consts.NUM_ROW))
    			return false;
    		
    		
    		if(matrix[downY][Consts.NUM_COL- 1] == 1 && downY < 1) {    			
    			if(downY < Consts.NUM_COL) {
	    			if(matrix[downX][downY+1] == 1) {
	        			System.out.println("Esquerda");
	        			return false;
	        		}
    			}
    		}
    		
    		if(downY < Consts.NUM_COL - 1) {
	    		if(matrix[downX][downY+1] == 1) {
	    			return false;
	    		}
    		}
    	}
    	
    	for(int i = 0; i < pieces.size(); i++) {
    		
			if (!pieces.get(i).pos.moveRight()) {
    			for(int j = 0; j < i; j++) {
    				pieces.get(j).pos.moveLeft();
    			}
    			return false;
    		}
    	}
    	return true;
    }
    
    public void corrigePeca(int row) {
    	for(int i = 0; i < pieces.size(); i++) {
    		System.out.println("X: " + pieces.get(i).getX() + " ROW: " + row);
    		if(pieces.get(i).getX() == row) {
    			System.out.println("X: " + pieces.get(i).getX() + " ROW: " + row);
    			pieces.remove(i);
    		}
    		int x = pieces.get(i).getX();
    		int y = pieces.get(i).getY();
    		pieces.get(i).setPosition(x, y);
    	}
    }
    
    
    private void setGameOver(boolean status) {
    	this.gameOver = status;
    }
    
    public boolean isOver() {
    	return this.gameOver;
    } 
    
    public ArrayList<Lolo> getSubElemen(){
    	return this.pieces;
    }   
}

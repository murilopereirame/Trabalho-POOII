package control;

import elements.Element;
import elements.Lolo;
import utils.Consts;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameController {
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        
        /*for(int j = 0; j < e.size(); j++) {
        	if(e.get(j) instanceof Lolo) {
		        Lolo lLolo = (Lolo)e.get(j);
		        if (!isValidPosition(e, lLolo)) {
		            lLolo.backToLastPosition();
		            
		            return;
		        }
		        
		        Element eTemp;
		        for(int i = 1; i < e.size(); i++){
		            eTemp = e.get(i);
		            if(lLolo.overlap(eTemp))
		                if(eTemp.isMortal())
		                    e.remove(eTemp);
		        }
        	}
        }*/
    }
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}

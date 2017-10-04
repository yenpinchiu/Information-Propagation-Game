package IPG;

import java.util.Iterator;

import javax.swing.JTextField;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class AI {
	
	//variable
	int i_ai_pick_num;
	int i_ai_pick_num2;
	
	//index
	int strategy_index;
	int strategy_index2;
	
	//history
	history historyy;
	
	AI(){
		
	}
	
	
	//degree based strategy
	void degree_based(Graph<node, Integer> g,int AI_id,JTextField ai_pick_num,VisualizationViewer<node,Integer> vv,history history){
		
		
		historyy = history;
		
		//get the number of node AI picked from ai_pick_num
    	i_ai_pick_num = Integer.parseInt(ai_pick_num.getText());

    	//no node picked at first
    	node picknode = null;  
    	
    	int count = 0;	            	
    	while(count < i_ai_pick_num){//do i_ai_pick_num time
    		
    		Iterator<node> ap = g.getVertices().iterator();
    		while(ap.hasNext()){ 	
    			node temp = ap.next();
    			if(picknode == null && temp.country == 0)picknode = temp; // if no node picked now , pick the first no country node
    			else if(picknode != null && temp.country == 0){// encounter a no country node , compare them with specific strategy
    				if(g.degree(temp)> g.degree(picknode)) picknode = temp; // high degree based
    			}
    		}
    		if(picknode != null){picknode.country = AI_id; historyy.write_select("AI"+AI_id,"0",picknode); }//set picked node country , if there are any node picked
    		picknode = null;//reset to null for next time pick
    		count++;
    	}
    	
    	vv.repaint();//repaint
		
	}
	
	//blocking strategy
	void blocking (Graph<node, Integer> g,int AI_id,JTextField ai_pick_num,VisualizationViewer<node,Integer> vv,history history){
		
		historyy = history;
		
		//get the number of node AI picked from ai_pick_num
    	i_ai_pick_num = Integer.parseInt(ai_pick_num.getText());

    	//no node picked at first
    	node picknode = null;
    	
    	int count = 0;	            	
    	while(count < i_ai_pick_num){//do i_ai_pick_num time
    		
    		Iterator<node> ap = g.getVertices().iterator();
    		while(ap.hasNext()){ 	
    			node temp = ap.next();
    			if(picknode == null && temp.country == 0  && tool.occupied_neighbor(g,temp,AI_id))picknode = temp; // if no node picked now , pick the first no country node
    			else if(picknode != null && temp.country == 0 && tool.occupied_neighbor(g,temp,AI_id)){// encounter a no country node , compare them with specific strategy
    				if(g.degree(temp)> g.degree(picknode) ) picknode = temp;// blocking
    				
    			}
    			
    		}
    		if(picknode != null){picknode.country = AI_id; historyy.write_select("AI"+AI_id,"0",picknode); }//set picked node country , if there are any node picked
    		if(picknode == null){degree_based(g,AI_id,ai_pick_num,vv,history); return;}
    		picknode = null;//reset to null for next time pick
    		count++;
    		
    	}
    	
    	vv.repaint();//repaint
  
	}
	
	
	
}

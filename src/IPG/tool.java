package IPG;

import java.util.Iterator;

import javax.swing.JButton;

import edu.uci.ics.jung.graph.Graph;

public class tool {
	
	tool(){
		
	}
	
	
	//return there is any blank node or not
	static boolean is_any_blanknode(Graph<node, Integer> g){ 
		
		Iterator<node> sc = g.getVertices().iterator();
		
		while(sc.hasNext()){ 
			node temp = sc.next();
			if(temp.country == 0) return true;
		}
		
		return false;
	}
	
	//TimerTask for pressing AI1 AI2 propagation in sequence cycle///////////////
	 static class autopress extends java.util.TimerTask{
        
		 JButton AI1;
		 JButton AI2;
		 JButton propagation;
		 Graph<node, Integer> g;
		 int count = 0;
		 
        autopress(JButton AI1,JButton AI2,JButton propagation,Graph<node, Integer> g){
        	
        	this.AI1 = AI1;
        	this.AI2 = AI2;
        	this.propagation = propagation;
        	this.g = g;
        }
        
        @Override
        public void run() {        	
    		if(count %3 == 0)this.AI1.doClick();
    		if(count %3 == 1)this.AI2.doClick();
    		if(count %3 == 2)this.propagation.doClick();
    		count ++;
    		if(!is_any_blanknode(g)) cancel();
       }
     }///////////////////////
	 
	 
	//return node from graph g by node id , if no such node ,return a new node with that id
	static node return_node_by_id(Graph<node, Integer> g,int nid){ 
				
			Iterator<node> cn = g.getVertices().iterator();
				
			while(cn.hasNext()){ 
				node temp = cn.next();
				if(nid == temp.nid) return temp;
			}
				
			node new_node = new node(nid,0);
			return new_node;
	}
	
	//return is any occupied neighbor there around node n1 , but those neighbor cant be n2s country
	static boolean occupied_neighbor(Graph<node, Integer> g,node n,int AI_id){ 
		
		Iterator<node> on = g.getNeighbors(n).iterator();
	
		while(on.hasNext()){ 
			node temp = on.next();
			if( temp.country != 0 && temp.country != AI_id) return true;
		}
			
		return false;
	}

	

}

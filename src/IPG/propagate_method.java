package IPG;

import java.util.Iterator;

import edu.uci.ics.jung.graph.Graph;

public class propagate_method {
	
	//index
	int propagation_index = 0;
	
	//variable
	double iip = 0.2;
	double ltt = 0.1;
	
	//history
	static history historyy;
	
	propagate_method(){
		
	}
	
	//IC propagation model
	static void IC(Graph<node, Integer> g,double p,int players,history history){ 
		
				
				historyy = history;
				
				int[] count = new int[players];
				double[] pp = new double[players];
				node temp = null;
				node temp2 = null;
				
				Iterator<node> pi = g.getVertices().iterator();
	        	while(pi.hasNext()){ 	
	            	temp = pi.next();
	            	
	            	if(!temp.scar2){
	            	
	            	Iterator<node> pin = g.getNeighbors(temp).iterator();
	            	while(pin.hasNext()){
	            		temp2 = pin.next();
	            		if(temp.country == 0 && temp2.country!= 0 && !temp2.scar){
	            			
	            			if(!temp.scar2)temp.scar2 = true;
	            			
	            			for(int i= 0;i<players;i++){
	            				if(temp2.country == i+1){
	            					if(Math.random() <= p) count[i]++;
	            				}
	            			}
	            		}
	            	}

	            	
	            	int sum = 0;
	            	for(int i =0;i<players;i++){
	            		sum = sum + count[i];
	            	}

	            	if(sum !=0 ){
	            		
	            		for(int i =0;i<players;i++){
	            			pp[i] = (double)count[i]/sum;
	                	}
	            		
	            		double rand = Math.random();
	            		double higher = 0;
	            		for(int i =0;i<players;i++){
	            			higher = higher + pp[i];
	            			if(rand<=higher){
	            				temp.country = i+1;
	            				history.write_propagation("player"+(i+1), temp);
	            				break;
	            			}
	            		}
	            		temp.scar = true;
	            	}
	            	for(int i = 0;i<players;i++){
	            		count[i] = 0;
	            		pp[i] = 0;
	            	}
	            	
	            	}
	        	}
	        	//clear the scar
	        	Iterator<node> pi2 = g.getVertices().iterator();
	        	while(pi2.hasNext()){
	        		node temp3 = pi2.next();
	        		temp3.scar = false;
	        	}//clear end
	        	
			}//IC propagation model end
	
	
	//LT propagation model
	static void LT(Graph<node, Integer> g,double threshold,int players,history history){
		
		historyy = history;
		
		node temp = null;
		node temp2 = null;
		double[] influ = new double[players];
		double[] pp = new double[players];
		
		Iterator<node> pi = g.getVertices().iterator();
    	while(pi.hasNext()){ 	
        	temp = pi.next();
        	Iterator<node> pin = g.getNeighbors(temp).iterator();
        		while(pin.hasNext()){
        			temp2 = pin.next();
        			if( temp.country == 0 && temp2.country!= 0 && !temp2.scar){
        				influ[temp2.country-1] = influ[temp2.country-1] + ((double)1/g.getNeighborCount(temp));
        			}
        		}
        		
        		double sum = 0;
            	for(int i =0;i<players;i++){
            		if(influ[i] >= threshold)sum = sum + influ[i];           		
            	}
            	
            	if(sum !=0 ){
            		
            		for(int i =0;i<players;i++){
            			if(influ[i] >= threshold) pp[i] = (double)influ[i]/sum;
                	}
            		
            		double rand = Math.random();
            		double higher = 0;
            		for(int i =0;i<players;i++){
            			higher = higher + pp[i];
            			if(rand<=higher){
            				temp.country = i+1;
            				history.write_propagation("player"+(i+1), temp);
            				break;
            			}
            		}
            		temp.scar = true;
            	}
            	for(int i = 0;i<players;i++){
            		influ[i] = 0;
            		pp[i] = 0;
            	}
    	}	
        //clear the scar
        Iterator<node> pi2 = g.getVertices().iterator();
        while(pi2.hasNext()){
        	node temp3 = pi2.next();
        	temp3.scar = false;
        }//clear end
    	
	}//LT propagation model end
	
	
	
}





























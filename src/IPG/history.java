package IPG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import edu.uci.ics.jung.graph.Graph;

public class history {
	
	String[] data;
	String[] lines;
	int line_count = 1;
	int max_line;
	
	int iter = 0;
	
	public history(){
		
		
	}
	
	
	void write_graph_data(Graph<node, Integer> g){
			
			data[0] = "Graph data:";
		    
			int count = 1;
			Iterator<node> n = g.getVertices().iterator();
			while(n.hasNext()){ 	
            	node temp_node = n.next();
            	
            	Iterator<node> nn = g.getNeighbors(temp_node).iterator();
            	while(nn.hasNext()){ 		
            		node temp_node2 = nn.next();
            		if(temp_node2.scar == false){
            			data[count] = temp_node.toString() + " " + temp_node2.toString();
            			count++;
            		}
            	}
            	temp_node.scar = true;
			}
			
			Iterator<node> c = g.getVertices().iterator();
        	while(c.hasNext()){
        		node temp_node3 = c.next();
        		temp_node3.scar = false;
        	}
	}
	
	void write_select(String player,String oplayer,node pnode){
		
		if(line_count<max_line){
			lines[line_count] = "player "+ player + " select node " + pnode.toString() + " from " + oplayer;
			line_count++;
		}
	}
	
	
	void write_iter(){
		
		if(line_count<max_line){		
			lines[line_count] = "propagate";
			line_count++;
		}
	}
	
	void write_propagation(String player,node to){
		
		if(line_count<max_line){		
			lines[line_count] = player + " occupy " + to.toString();
			line_count++;
		}
	}
	
	void undo(Graph<node, Integer> g){
		
		if((line_count-1)!=0)line_count --;
		else return;
		
		
		String[] scan = lines[line_count].split(" ");
		
		if( scan[0].compareTo("propagate") == 0 ){
			
			lines[line_count] = "";
			return;
		}
		
		if(scan[1].compareTo("occupy") == 0){
			
			while(true){
				
				tool.return_node_by_id(g,Integer.parseInt(scan[2])).country = 0;
				lines[line_count] = "";
				line_count --;
				scan = lines[line_count].split(" ");
							
				if(scan[0] == "propagate"){
					lines[line_count] = "";
					break;
				}
			}
			
			return;
		}
		
		if(scan[2].compareTo("select") == 0){
			
			tool.return_node_by_id(g,Integer.parseInt(scan[4])).country = Integer.parseInt(scan[6]);
			lines[line_count] = "";
			return;
		}
		
		return;
	}
	
	void write(File f){
		
		try{

				FileWriter fw = new FileWriter(f);
				BufferedWriter bfw=new BufferedWriter(fw);
			    
			    
			    for(int i =0;i<data.length;i++){
			    	
			    	bfw.write(data[i]);
					bfw.newLine();
			    }
			    bfw.newLine();
			    bfw.newLine();
			    
			    lines[0] = "propagation process:";
			       
			    for(int i =0;i<lines.length;i++){
			    	
			    	if(lines[i] == null)break;
			    	bfw.write(lines[i]);
					bfw.newLine();
			    }
			
			bfw.flush();
			fw.close();
		
		}catch(IOException e){}	
	}
	
	void reset(Graph<node, Integer> g){
		
		data = new String[g.getEdgeCount()+1];
		max_line = g.getVertexCount()*50 + 1;
		lines = new String[max_line];
		line_count = 1;
	}
	
	void write_clean(){
		
		if(line_count<max_line){
			lines[line_count] = "clean all";
			line_count++;
		}
	}
	
	
	
}

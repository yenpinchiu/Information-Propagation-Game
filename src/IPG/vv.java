package IPG;


import java.awt.Color;
import java.awt.Paint;
import java.awt.event.MouseEvent;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;

import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class vv {
	
	//jung visual panel vv
	VisualizationViewer<node, Integer> vv;
	//color set
	Color[] colorset;
	//graph mouse
	EditingModalGraphMouse<node,Integer> graphMouse;
	
	//history
	history historyy;
	
	//score
	score scoree;
	
	//information 
	information informationn;
	
	//index
	int now_picked_country = 0;
	
	Graph<node, Integer> gg;
	
	
	vv(Graph<node, Integer> g,Factory<node> vertexFactory,Factory<Integer> edgeFactory,Color[] colors,history history,score score,information information){
		
		historyy = history;
		scoree = score;
		gg = g;
		informationn = information;
		
		vv =  new VisualizationViewer<node, Integer>(new FRLayout<node, Integer>(gg));
		//set node label
        vv.getRenderContext().setVertexLabelTransformer(  new ToStringLabeller<node>()); 
        //set to white
        vv.setBackground(Color.white);
        
		colorset = colors;
		
        //set node color
        Transformer<node, Paint> vertex_color = new Transformer<node, Paint>() {
        	public Color transform(node n) { 
        		Set<node> pickedVertices = vv.getPickedVertexState().getPicked(); 
        		if(pickedVertices.contains(n)) { 
        			return Color.pink; 
        		}
        		if(n.country == 1) { 
        			return colorset[1]; 
        		}
        		else if(n.country == 2) { 
        			return colorset[2];
        		}
        		else if(n.country == 3) { 
        			return colorset[3];
        		}
        		else if(n.country == 4) { 
        			return colorset[4];
        		}
        		else if(n.country == 5) { 
        			return colorset[5];
        		}
        		else if(n.country == 6) { 
        			return colorset[6];
        		}
        		
        		return colorset[0]; 
        		
        	}
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertex_color);
	      //set node color end
	        
	        //set vv mouse action
	        graphMouse = 
	            	new EditingModalGraphMouse<node,Integer>(vv.getRenderContext(), vertexFactory, edgeFactory);
	        vv.setGraphMouse(graphMouse);
	        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);//default
	        
	      //direct pick node and change country , so "dont" need pick mode
	        vv.addGraphMouseListener( new GraphMouseListener<node>(){
				@Override
				public void graphClicked(node n, MouseEvent e) {		
				}
				@Override
				public void graphPressed(node n, MouseEvent e) {
					
					vv.getPickedEdgeState().clear();
					
					if(now_picked_country!=7){
						int tmp = n.country;
						n.country = now_picked_country;	
						historyy.write_select("" + now_picked_country,"" + tmp,n);
					}
					
					Iterator<Integer> ne = gg.getInEdges(n).iterator();
					while(ne.hasNext()){
						int pe = ne.next();
						vv.getPickedEdgeState().pick(pe,true);
					}
					
					informationn.count_and_set_to_panel(gg,n);
					
				}
				@Override
				public void graphReleased(node n, MouseEvent e) {
						
					if(now_picked_country!=7){
						vv.getPickedVertexState().clear();
						vv.repaint();
	                
	                	scoree.count_score();
	                	scoree.set_to_panel();
	                }
	                
				}
	        });//////////

	}
	
	
	
	VisualizationViewer<node, Integer> return_vv(){
		
		return vv;
	}
	
	void repaint(){
		
		vv.repaint();
		
	}		
	
}

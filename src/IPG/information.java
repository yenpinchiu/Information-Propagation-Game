package IPG;

import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.uci.ics.jung.graph.Graph;

public class information {
	
	JLabel[] i_label = new JLabel[5];
	JPanel[] i_panel = new JPanel[5];
	Graph<node, Integer> gg;
	
	information(){
		
		for(int i = 0;i<i_label.length;i++){		
			i_label[i] = new JLabel();
		}
	}
	
	void set_panel(JPanel[] panel){
		
		 i_panel = panel;
	}
	
	void count_and_set_to_panel(Graph<node, Integer> g,node n){
		
		gg = g;
		
		int afected_n = 0;
		int unafected_n = 0;
		int degree_of_neighbors = 0;
		node temp;
		
		i_label[0].setText("Node_id: " + n.toString());
		i_label[1].setText("Degree:  " + gg.getNeighborCount(n) );
		
		Iterator<node> pnn = gg.getNeighbors(n).iterator();
		while(pnn.hasNext()){
			
			temp = pnn.next();
			if(temp.country == 0){
				unafected_n++;
				degree_of_neighbors = degree_of_neighbors + gg.getNeighborCount(temp);
			}
			else {
				afected_n++;
			}
		}
		
		i_label[2].setText("Blank Neighbors: " + unafected_n);
		i_label[3].setText("Occupied Neighbors: " +  afected_n);
		i_label[4].setText("Neighbor Degree: " + degree_of_neighbors);
		
		i_panel[0].removeAll();
		i_panel[1].removeAll();
		i_panel[2].removeAll();
		i_panel[3].removeAll();
		i_panel[4].removeAll();
		
		i_panel[0].add(i_label[0]);
		i_panel[1].add(i_label[1]);
		i_panel[2].add(i_label[2]);
		i_panel[3].add(i_label[3]);
		i_panel[4].add(i_label[4]);
	}
	
	void intit_set_to_panel(){
		
		
		i_label[0].setText("Node_id: ");
		i_label[1].setText("Degree:  " );	
		i_label[2].setText("Blank Neighbors: ");
		i_label[3].setText("Occupied Neighbors: ");
		i_label[4].setText("Blank Nei Degree: ");
		
		i_panel[0].removeAll();
		i_panel[1].removeAll();
		i_panel[2].removeAll();
		i_panel[3].removeAll();
		i_panel[4].removeAll();
		
		i_panel[0].add(i_label[0]);
		i_panel[1].add(i_label[1]);
		i_panel[2].add(i_label[2]);
		i_panel[3].add(i_label[3]);
		i_panel[4].add(i_label[4]);
	}
	
}

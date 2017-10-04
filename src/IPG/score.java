package IPG;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.uci.ics.jung.graph.Graph;

public class score {	
	
	Graph<node, Integer> gg;
	JPanel[] panell = new JPanel[7];
	int score[] = new int[7];
	JLabel[] score_label = new JLabel[7];
	Color[] colorr;
	
	public score(Color[] color){
		
		colorr = color;
		
		for(int i = 0;i<score_label.length;i++){		
			score_label[i] = new JLabel();
			score_label[i].setForeground(color[i]);
		}
	}
	
	void set_panel(JPanel[] panel){
		
		panell = panel;
		
	}
	
	
	void renew_graph(Graph<node, Integer> g){
		
		gg = g;
	}
	
	void count_score(){
		
		Iterator<node> cnn = gg.getVertices().iterator();
		
		for(int i = 0;i<score.length;i++){
			score[i] = 0;
		}
		
		while(cnn.hasNext()){ 
			node temp = cnn.next();
			score[temp.country]++; 
		}
	}
	
	void set_to_panel(){
		
		for(int i = 0;i<score_label.length;i++){		
			score_label[i].setText(Integer.toString(score[i]));
			score_label[i].setFont(new Font("Arial",1,16));
			panell[i].removeAll();
			panell[i].add(score_label[i]);
		}
	}

	
}

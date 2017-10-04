package IPG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/*
 *  Yan-Bin Ciou all copyrights reserved. 
 *  Information Propagation Game Version 1.0(beta) released in April 2012
 */

public class ipg extends JApplet{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	////////////////////////////////////////////
	
	//window size
	private int screenw = 1200;
	private int screenh = 800;
	
	//panels
	private JPanel table;//main panel
	static JPanel[] control_panels;//control panels which put on main panel
	static JPanel[] little_control_panels;//split control panel into small panel
	static JPanel[] score_panel;
	static JPanel[] i_panel;
	//dealers
	static components components_generater;//components generater
	static graph_generator g_generator;//graph generater	
	static vv v;//vv geneartor
	static propagate_method propa;
	static AI donita;
	static history history;
	static score score;
	static information information;
	
	//graph
	static Graph<node, Integer> g  = new SparseMultigraph<node, Integer>();//graph
	static Color[] node_colors = new Color[]{Color.white,Color.orange,new Color(149,213,234),new Color(155,219,168),new Color(207,169,182),new Color(93,96,15),new Color(108,26,37)};
	
	//box list
	static String[] graph_names = {"ER model","BA model","WS model"};// graph list
	static String[] propagation_names = {"IC model","LT model"};//propagation list
	static String[] strategy_names = {"Centrality Based","Blocking"};//{"Centrality Based","Centrality Based(n)","Blocking","Blocking(n)"};//strategy list
	
	
	//////////////////////////////////////////////

		//start
			public static void main(String[] args){
				
				JFrame frame = new JFrame();
				ipg gui = new  ipg();		
				frame.setTitle("Information Propagation Game Ver.1.0(beta)");
				frame.getContentPane().add(gui);
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frame.pack();
				frame.setResizable(true);
				frame.setVisible(true);
			}
		
		//main
			ipg(){
				
				try {
					
					BorderLayout thisLayout = new BorderLayout();
					this.setLayout(thisLayout);
					this.setPreferredSize(new java.awt.Dimension(screenw, screenh));
					
					table = getGraphPanel();
					this.add(table, BorderLayout.CENTER);
					
				}catch (Exception e) {e.printStackTrace();}
				
				
			}
		
			
		//generate main panel
			private static JPanel getGraphPanel() {
				
				//build dealers				
				g_generator = new graph_generator();
				propa = new propagate_method();		
				history = new history();
				score = new score(node_colors);
				information = new information();
				components_generater = new components(history,score);
				v = new vv(g,g_generator.vertexFactory,g_generator.edgeFactory,node_colors,history,score,information);
				donita = new AI();
				
				
				history.reset(g);
				history.write_graph_data(g);
				
				//gui assign//////////////////////////////////////////////
				//creat main panel
				JPanel jp = new JPanel();
		        jp.setBackground(Color.WHITE);
		        jp.setLayout(new BorderLayout());
		        
		        
		        //set control panels
		        control_panels = new JPanel[3];
		        //top panel
		        control_panels[0] = new JPanel(new GridLayout(1,2));
		        //bot panel
	        	control_panels[1] = new JPanel(new GridLayout(2,2));
	        	//right panel
	        	control_panels[2] = new JPanel(new GridLayout(14,1));
	        	
	        	//set small panels
	        	little_control_panels = new JPanel[20];
	        	for(int i=0 ;i < little_control_panels.length ;i++){
	        		little_control_panels[i] = new JPanel();
	        	}
	        	
	        	//add panel to main panel
	        	jp.add(control_panels[0], BorderLayout.NORTH);
	        	jp.add(control_panels[1], BorderLayout.SOUTH);
	        	jp.add(control_panels[2], BorderLayout.EAST);
	        	
	        	//add little panel to panel
	        	for(int i=0 ;i < little_control_panels.length ;i++){
	        		
	        		//top panel split into 2 panel
	        		if(i<2)control_panels[0].add(little_control_panels[i]);
	        		//bot panel split into 4 panel
	        		else if(i<6)control_panels[1].add(little_control_panels[i]);
	        		//other 14 little panel add to right panel
	        		else if(i< little_control_panels.length)control_panels[2].add(little_control_panels[i]);
	        	}
	        	
	        	
	        	//set score panel
	        	score_panel = new JPanel[7];
	        	for(int i =0;i<score_panel.length;i++){
	        	
	        		score_panel[i] = little_control_panels[i+7];
	        	}
	        	score.set_panel(score_panel);
	        	
	        	//set information panel
	        	i_panel = new JPanel[5];
	        	for(int i =0;i<i_panel.length;i++){
		        	
	        		i_panel[i] = little_control_panels[i+15];
	        	}
	        	information.set_panel(i_panel);
	        	information.intit_set_to_panel();
	        	
	        	//add
	        	jp.add(v.return_vv(), BorderLayout.CENTER);//jung visual panel add to mid
	        	//top
	        	little_control_panels[0].add(components_generater.new_buttom(g,g_generator,v));
	        	little_control_panels[0].add(components_generater.graph_chooser(graph_names,g_generator,little_control_panels[0]));
	        	little_control_panels[0].add(components_generater.ern_l());
	        	little_control_panels[0].add(components_generater.ern());
	        	little_control_panels[0].add(components_generater.erp_l());
	        	little_control_panels[0].add(components_generater.erp());
	        	little_control_panels[0].add(components_generater.load(g, v,g_generator,graph_names));
	        	little_control_panels[0].add(components_generater.save(g, v.return_vv()));
	        	components_generater.ban_l();
	        	components_generater.ban();
	        	components_generater.bal_l();
	        	components_generater.bal();
	        	components_generater.bas_l();
	        	components_generater.bas();
	        	components_generater.wsn_l();
	        	components_generater.wsn();
	        	components_generater.wsk_l();
	        	components_generater.wsk();
	        	components_generater.wsp_l();
	        	components_generater.wsp();
	        	
	        	
	        	little_control_panels[1].add(components_generater.layout_chooser(v.return_vv(),g));
	        	little_control_panels[1].add(components_generater.trans_buttom(v));
	        	little_control_panels[1].add(components_generater.edit_buttom(v));
	        	little_control_panels[1].add(components_generater.help());
	        	
	        	//bot
	        	little_control_panels[2].add(components_generater.propagate(v.return_vv(),g,propa));
	        	little_control_panels[2].add(components_generater.propagation_chooser(propagation_names, propa,little_control_panels[2]));
	        	little_control_panels[2].add(components_generater.icp_l());
	        	little_control_panels[2].add(components_generater.icp());
	        	components_generater.ltt_l();
	        	components_generater.ltt();
	        	little_control_panels[2].add(components_generater.undo(g,v.return_vv()));
	        	little_control_panels[3].add(components_generater.auto(g));
	        	little_control_panels[4].add(components_generater.notchange(v));        	
	        	little_control_panels[4].add(components_generater.erase(node_colors,v));
	        	little_control_panels[4].add(components_generater.p1(node_colors,v));
	        	little_control_panels[4].add(components_generater.p2(node_colors,v));
	        	little_control_panels[4].add(components_generater.p3(node_colors,v));
	        	little_control_panels[4].add(components_generater.p4(node_colors,v));
	        	little_control_panels[4].add(components_generater.clean(g,v));
	        	
	        	little_control_panels[5].add(components_generater.AI1(v.return_vv(),g,donita));
	        	little_control_panels[5].add(components_generater.ai_pick_num());
	        	little_control_panels[5].add(components_generater.strategy_chooser(strategy_names,donita));
	        	little_control_panels[5].add(components_generater.AI2(v.return_vv(),g,donita));
	        	little_control_panels[5].add(components_generater.ai_pick_num2());
	        	little_control_panels[5].add(components_generater.strategy_chooser2(strategy_names,donita));
	        	
	        	little_control_panels[6].add(components_generater.iscore());        	
	        	little_control_panels[6].setBackground(new Color(168,168,168));
	        	
	        	little_control_panels[14].add(components_generater.imformation());        	
	        	little_control_panels[14].setBackground(new Color(168,168,168));

	        	
	        	
	        	score.renew_graph(g);
	        	score.count_score();
	        	score.set_to_panel();
	        	
	        	
	        	//////////////////////////////////////////////////////////////
	        	
	        	
	        	
		        
		        return jp;
			}
	
	
}

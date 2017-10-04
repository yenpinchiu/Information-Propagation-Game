package IPG;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class components {
	
	
	
	Graph<node, Integer> gg;
	graph_generator g_generatorr;
	vv the_v;
	VisualizationViewer<node,Integer> vvv;
	propagate_method pp;
	AI shirley;
	Timer timer;
	history historyy;
	JPanel panell;
	JPanel panell2;
	score scoree;
	
	Color[] colors;
	String[] graph_namess;
	
	JButton newbuttom;
	JComboBox graph_chooser;
	JButton load;
	JButton save;
	
	JComboBox layout_chooser;
	JButton trans;
	JButton edit;
	
	JButton p1;
	JButton p2;
	JButton p3;
	JButton p4;
	JButton erase;
	JButton propagation;
	JComboBox propagation_chooser;
	
	JButton auto;
	
	JButton AI1;
	JButton AI2;
	JTextField ai_pick_num;
	JTextField ai_pick_num2;
	JComboBox strategy_chooser;
	JComboBox strategy_chooser2;
	
	JTextField ern;
	JTextField erp;
	JTextField ban;
	JTextField bal;
	JTextField bas;
	JTextField wsn;
	JTextField wsk;
	JTextField wsp;
	JLabel ern_l;
	JLabel erp_l;
	JLabel ban_l;
	JLabel bal_l;
	JLabel bas_l;
	JLabel wsn_l;
	JLabel wsk_l;
	JLabel wsp_l;
	
	JTextField icp;
	JLabel icp_l;
	
	JTextField ltt;
	JLabel ltt_l;
	
	JLabel iscore;
	JLabel imformation;
	
	JButton help;
	JButton undo;
	JButton clean;
	JButton notchange;
	
	
	String instructions =
	        "<html>"+
	        "<p>  New Graph : generate random graph with choosed model and parameters  "+
	        "<p>"+
	        "<p>  Load Graph : load graph from data , simple data format example followed"+
	        "<p>1 2"+
	        "<p>3 4"+
	        "<p>(build a graph which has 4 nodes (1) (2) (3) (4) , 2 edges (1,2) (3,4))"+
	        "<p>"+
	        "<p>  Save History: "+
	        "<p>  save graph data and playing progress of current graph"+
	        "<p>"+
	        "<p>  Layout chooser: change layout"+
	        "<p>"+
	        "<p>  Transform mode: "+
	        "<p>  mouse wheel for scaling , drag for shifting , shift + drag for rotating...etc"+
	        "<p>"+
	        "<p>  Edit mode:"+
	        "<p>   graph edit ,add vertex(click) of edge (drag form one node to another)"+
	        "<p>"+
	        "<p>  Help: you already know"+
	        "<p>"+
	        "<p>  Pick mode: "+
	        "<p>  split into some mode , all can move the choosed node and dislay the choosed node outedges"+
	        "<p>  choose : pick node but not change its nation"+
	        "<p>  erase : pick node and set it to non-nation"+
	        "<p>  p1 p2 p3 p4 : pick node and set to specific nation"+
	        "<p>  clean all: set all node to non-nation"+
	        "<p>"+
	        "<p>  Propagate: propagate one iteration with choosed model and parameter"+
	        "<p>"+
	        "<p>  Undo: undo one select or one propagate , will washout the related history"+
	        "<p>"+
	        "<p>  AI1 AI2: AI auto pick node with choosed number of node and strategy"+
	        "<p>       Centrality Based: pick high centrality nodes"+
	        //"<p>       Centrality Based(n): pick nodes which has high centrality neighbors"+
	        "<p>       Blocking: pick high centrality neighbor of opponents seed"+
	        //"<p>       Blocking(n): pick node which has the highest neighbor overlapping rate with opponents seeds neighbor"+
	        "<p>"+
	        "<p>  Autoplay: AI1 pick -> AI2 pick -> propagate -> AI1 pick .... to end "+
	        "<p>"+
	        "<p> Score: diaplay score"+
	        "<p>"+
	        "<p> Information: display some information of choosed node"+
	        "<p>"+
	        "<p></html>";
	
	components(history history,score score){
		
		historyy = history;
		scoree = score;
	}
	
	//new graph/////////////////////////////////////
	JButton new_buttom(Graph<node, Integer> g,graph_generator g_generator, vv vv){
		
		newbuttom = new JButton("New Graph");
		
		gg = g;
		g_generatorr = g_generator;
		the_v = vv;
		vvv = the_v.return_vv();
		
		newbuttom.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				
				g_generatorr.ien = Integer.parseInt(ern.getText());
				g_generatorr.iep = Double.parseDouble(erp.getText());
				g_generatorr.ibn = Integer.parseInt(ban.getText());
				g_generatorr.ibe = Integer.parseInt(bal.getText());
				g_generatorr.ibs = Integer.parseInt(bas.getText());
				g_generatorr.iwn = Integer.parseInt(wsn.getText());
				g_generatorr.iwk = Integer.parseInt(wsk.getText());
				g_generatorr.iwp = Double.parseDouble(wsp.getText());
				
				//some rule
				if(g_generatorr.iep>1 || g_generatorr.iep<0)return;
				if(g_generatorr.ibn<g_generatorr.ibe)return;
				if(g_generatorr.iwn<=g_generatorr.iwk)return;
				if(g_generatorr.iwp>1 || g_generatorr.iwp<0 )return;
				
				//rebuild all graph
				//ER
				if(g_generatorr.graph_index == 0)gg = graph_generator.generateER(g_generatorr.graphFactory, g_generatorr.vertexFactory, g_generatorr.edgeFactory, g_generatorr.ien,g_generatorr.iep);
				//BA
				if(g_generatorr.graph_index == 1)gg = graph_generator.generateBA(g_generatorr.graphFactory2, g_generatorr.vertexFactory, g_generatorr.edgeFactory, g_generatorr.ibn,g_generatorr.ibe,g_generatorr.ibs);
				//WS
				if(g_generatorr.graph_index == 2)gg = graph_generator.generateWS(g_generatorr.iwn,g_generatorr.iwk,g_generatorr.iwp);
				
				Class<? extends Layout<node,Integer>> layoutC = 
		                (Class<? extends Layout<node,Integer>>) layout_chooser.getSelectedItem();
		            try
		            {
		                Constructor<? extends Layout<node,Integer>> constructor = layoutC
		                        .getConstructor(new Class[] {Graph.class});
		                Object o = constructor.newInstance(gg);
		                Layout<node,Integer> l = (Layout<node,Integer>) o;
		                l.setInitializer(vvv.getGraphLayout());
		                l.setSize(vvv.getSize());
		                
						LayoutTransition<node,Integer> lt =
							new LayoutTransition<node,Integer>(vvv, vvv.getGraphLayout(), l);
						Animator animator = new Animator(lt);
						animator.start();
						vvv.getRenderContext().getMultiLayerTransformer().setToIdentity();
						vvv.repaint();
						the_v.gg = gg;
		                
		            }
		            catch (Exception ee) {ee.printStackTrace();}
		            
		            historyy.reset(gg);
		            historyy.write_graph_data(gg);
		            
		            scoree.renew_graph(gg);
		            scoree.count_score();
		            scoree.set_to_panel();
				
			}});
		
		
		return newbuttom;
	}
	
	//graph chooser//////////////////////////////
	JComboBox graph_chooser(String[] graph_names,graph_generator g_generator ,JPanel panel){
		
		g_generatorr = g_generator;
		panell = panel;
		
		graph_chooser = new JComboBox(graph_names);
		
		
		graph_chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JComboBox cb = (JComboBox)e.getSource();
				g_generatorr.graph_index = cb.getSelectedIndex();
				
				if(g_generatorr.graph_index == 0){
					
					panell.removeAll();
					panell.add(newbuttom);
					panell.add(graph_chooser);
					panell.add(ern_l);
					panell.add(ern);
					panell.add(erp_l);
					panell.add(erp);
					panell.add(load);
					panell.add(save);
					panell.validate();
					panell.repaint();
				}
				if(g_generatorr.graph_index == 1){
					
					panell.removeAll();
					panell.add(newbuttom);
					panell.add(graph_chooser);
					panell.add(ban_l);
					panell.add(ban);
					panell.add(bal_l);
					panell.add(bal);
					panell.add(bas_l);
					panell.add(bas);
					panell.add(load);
					panell.add(save);
					panell.validate();
					panell.repaint();
				}
				if(g_generatorr.graph_index == 2){
					
					panell.removeAll();
					panell.add(newbuttom);
					panell.add(graph_chooser);
					panell.add(wsn_l);
					panell.add(wsn);
					panell.add(wsk_l);
					panell.add(wsk);
					panell.add(wsp_l);
					panell.add(wsp);
					panell.add(load);
					panell.add(save);
					panell.validate();
					panell.repaint();
				}
				
			}});
		
		return graph_chooser;
	}
	
	//layout chooser/////////////////////////////////
	private static Class[] getCombos()
    {
        ArrayList layouts = new ArrayList<Class<? extends Layout>>();
        layouts.add(KKLayout.class);
        layouts.add(FRLayout.class);
        layouts.add(CircleLayout.class);
        layouts.add(SpringLayout.class);
        layouts.add(ISOMLayout.class);
        
        return (Class[]) layouts.toArray(new Class[0]);
    }	
	
	JComboBox layout_chooser( VisualizationViewer<node,Integer> vv,Graph<node, Integer> g){
		
		 Class[] combos = getCombos();
		 layout_chooser = new JComboBox(combos); 
		 
	     vvv = vv;
	     gg = g;
		 
		 layout_chooser.addActionListener(new ActionListener() {
			 	 
				public void actionPerformed(ActionEvent e) {
					
					Class<? extends Layout<node,Integer>> layoutC = 
			                (Class<? extends Layout<node,Integer>>) layout_chooser.getSelectedItem();
			            try
			            {
			                Constructor<? extends Layout<node,Integer>> constructor = layoutC
			                        .getConstructor(new Class[] {Graph.class});
			                Object o = constructor.newInstance(gg);
			                Layout<node,Integer> l = (Layout<node,Integer>) o;
			                l.setInitializer(vvv.getGraphLayout());
			                l.setSize(vvv.getSize());
			                
							LayoutTransition<node,Integer> lt =
								new LayoutTransition<node,Integer>(vvv, vvv.getGraphLayout(), l);
							Animator animator = new Animator(lt);
							animator.start();
							vvv.getRenderContext().getMultiLayerTransformer().setToIdentity();
							vvv.repaint();
							
			                
			            }
			            catch (Exception ee) {ee.printStackTrace();}
			
				}});
		 
		 layout_chooser.setSelectedItem(FRLayout.class);
		 layout_chooser.setRenderer(new DefaultListCellRenderer() {
	            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                String valueString = value.toString();
	                valueString = valueString.substring(valueString.lastIndexOf('.')+1);
	                return super.getListCellRendererComponent(list, valueString, index, isSelected,
	                        cellHasFocus);
	            }
	        });
		 
		 return layout_chooser;
	}
	
	
	//transform mode buttom////////////////////
	JButton trans_buttom(vv v){
		
		the_v = v;
		
		trans = new JButton("Transform Mode");
		
		trans.addActionListener(new ActionListener() {	        	
			public void actionPerformed(ActionEvent e) {	            	
				the_v.graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);            	
				the_v.vv.repaint();
			}
		});
		
		return trans;
	}
	
	//editing mode buttom////////////////////
	JButton edit_buttom(vv v){
			
			the_v = v;
			
			edit = new JButton("Edit Mode");
			
			edit.addActionListener(new ActionListener() {	        	
				public void actionPerformed(ActionEvent e) {	            	
					the_v.graphMouse.setMode(ModalGraphMouse.Mode.EDITING);            	
					the_v.vv.repaint();
				}
			});
			
			return edit;
		}
	
	
	//p1 select bottom
	JButton p1(Color[] c,vv v){
		
		colors = c;
		the_v = v;
		
		p1 = new JButton("    P1    ");
		p1.setBackground(colors[1]);
		p1.addActionListener(new ActionListener() {
    	
			public void actionPerformed(ActionEvent e) {
							
				the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
				the_v.now_picked_country = 1; 	

         }
		});
		return p1;
	}
	
	//p2 select bottom
		JButton p2(Color[] c,vv v){
			
			colors = c;
			the_v = v;
			
			p2 = new JButton("    P2    ");
			p2.setBackground(colors[2]);
			p2.addActionListener(new ActionListener() {
	    	
				public void actionPerformed(ActionEvent e) {
								
					the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
					the_v.now_picked_country = 2; 	

	         }
			});
			return p2;
		}
		
		//p3 select bottom
		JButton p3(Color[] c,vv v){
			
			colors = c;
			the_v = v;
			
			p3 = new JButton("    P3    ");
			p3.setBackground(colors[3]);
			p3.addActionListener(new ActionListener() {
	    	
				public void actionPerformed(ActionEvent e) {
								
					the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
					the_v.now_picked_country = 3; 	

	         }
			});
			return p3;
		}
	
		//p4 select bottom
		JButton p4(Color[] c,vv v){
			
			colors = c;
			the_v = v;
			
			p4 = new JButton("    P4    ");
			p4.setBackground(colors[4]);
			p4.addActionListener(new ActionListener() {
	    	
				public void actionPerformed(ActionEvent e) {
								
					the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
					the_v.now_picked_country = 4; 	

	         }
			});
			return p4;
		}
		
		//erase select bottom
		JButton erase(Color[] c,vv v){
					
					colors = c;
					the_v = v;
					
					erase = new JButton("  Clean  ");
					erase.setBackground(colors[0]);
					erase.addActionListener(new ActionListener() {
			    	
						public void actionPerformed(ActionEvent e) {
										
							the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
							the_v.now_picked_country = 0; 	

			         }
					});
				return erase;
		}
		
		//not change select bottom
		JButton notchange(vv v){

				the_v = v;
							
				notchange = new JButton(" Choose ");
				notchange.setBackground(Color.WHITE);
				notchange.addActionListener(new ActionListener() {
					    	
					public void actionPerformed(ActionEvent e) {
												
						the_v.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
						the_v.now_picked_country = 7; 	

					}
				});
				return notchange;
		}
		
		//clean bottom
		JButton clean(Graph<node, Integer> g,vv v){
			
			gg = g;
			the_v = v;
			
			clean = new JButton(" Clean All ");
			clean.setBackground(Color.WHITE);
			clean.addActionListener(new ActionListener() {
					    	
				public void actionPerformed(ActionEvent e) {
												
					Iterator<node> cla = gg.getVertices().iterator();
					
					while(cla.hasNext()){ 
						node temp = cla.next();
						temp.country = 0;
					}
					
					the_v.return_vv().repaint();
					
					scoree.count_score();
					scoree.set_to_panel();
					
					historyy.write_clean();
				}
			});
			return clean;
		}
	
		//propagation buttom
		JButton propagate(VisualizationViewer<node,Integer> vv,Graph<node, Integer> g,propagate_method p){
			
			vvv = vv;
			gg = g;
			pp = p;
					
			propagation = new JButton("propagate");
			propagation.addActionListener(new ActionListener() {
        	
				public void actionPerformed(ActionEvent e) {
					
					pp.iip = Double.parseDouble(icp.getText());
					pp.ltt = Double.parseDouble(ltt.getText());
					
					//rules
					if(pp.iip>1)return;
					
					//IC propagate
					if(pp.propagation_index == 0){historyy.write_iter();	propagate_method.IC(gg,pp.iip,6,historyy);}
					//LT propagate
					if(pp.propagation_index == 1){historyy.write_iter();	propagate_method.LT(gg,pp.ltt,6,historyy);}
					
					//repaint
					vvv.repaint();
					
					scoree.count_score();
		        	scoree.set_to_panel();
				}
			});
			
			
			
			return propagation;
		}
		
		//set propagation change box
		JComboBox propagation_chooser(String[] propagation_names,propagate_method p,JPanel panel){
			
			pp = p;
			panell2 = panel;
			
			propagation_chooser = new JComboBox(propagation_names);
			propagation_chooser.addActionListener(new ActionListener() {
	        	
					public void actionPerformed(ActionEvent e) {
	            	
						JComboBox cb = (JComboBox)e.getSource();
						pp.propagation_index = cb.getSelectedIndex();
						
						if(pp.propagation_index == 0){
							
							panell2.removeAll();
							panell2.add(propagation);
							panell2.add(propagation_chooser);
							panell2.add(icp_l);
							panell2.add(icp);
							panell2.add(undo);
							panell2.validate();
							panell2.repaint();
						}
						if(pp.propagation_index == 1){
							
							panell2.removeAll();
							panell2.add(propagation);
							panell2.add(propagation_chooser);
							panell2.add(ltt_l);
							panell2.add(ltt);
							panell2.add(undo);
							panell2.validate();
							panell2.repaint();
						}
	            	
					}
				});
			
			return propagation_chooser;
		}
	
		//AI1 select bottom
			JButton AI1(VisualizationViewer<node,Integer> vv,Graph<node, Integer> g,AI donita){
				
				vvv = vv;
				gg = g;
				shirley = donita;
					
				AI1 = new JButton("    AI1   ");
				AI1.setBackground(colors[5]);
				AI1.setForeground(new Color(255,255,255));
				AI1.addActionListener(new ActionListener() {
			    	
					public void actionPerformed(ActionEvent e) {
										
						the_v.now_picked_country = 5; 	
						if(shirley.strategy_index == 0){ shirley.degree_based(gg,5,ai_pick_num , vvv,historyy); }
						if(shirley.strategy_index == 1){ shirley.blocking(gg,5,ai_pick_num , vvv,historyy); }
						scoree.count_score();
			        	scoree.set_to_panel();
					}
				});
				
				
				
					return AI1;
			}
			
		//AI2 select bottom
			JButton AI2(VisualizationViewer<node,Integer> vv,Graph<node, Integer> g,AI donita){
				
				vvv = vv;
				gg = g;
				shirley = donita;
				
				AI2 = new JButton("    AI2   ");
				AI2.setBackground(colors[6]);
				AI2.setForeground(new Color(255,255,255));
				AI2.addActionListener(new ActionListener() {
			    	
					public void actionPerformed(ActionEvent e) {
										
						the_v.now_picked_country = 6; 	
						if(shirley.strategy_index2 == 0){ shirley.degree_based(gg,6,ai_pick_num2 , vvv,historyy);}
						if(shirley.strategy_index2 == 1){ shirley.blocking(gg,6,ai_pick_num , vvv,historyy); }
						
						scoree.count_score();
			        	scoree.set_to_panel();
						
					}
				});
				
				
	        	
					return AI2;
			}
			
			JTextField ai_pick_num(){
				ai_pick_num = new JTextField();
				ai_pick_num.setColumns(3);//size = 3
				ai_pick_num.setText("1"); // initial at one node
				
				return ai_pick_num;
			}
			JTextField ai_pick_num2(){
				ai_pick_num2 = new JTextField();
				ai_pick_num2.setColumns(3);//size = 3
				ai_pick_num2.setText("1"); // initial at one node
				
				return ai_pick_num2;
			}
			
			JComboBox strategy_chooser(String[] strategy_names,AI donita){
				
				shirley = donita;
				
				strategy_chooser = new JComboBox(strategy_names);
				strategy_chooser.addActionListener(new ActionListener() {
			    	
					public void actionPerformed(ActionEvent e) {
						
						JComboBox cb = (JComboBox)e.getSource();
						shirley.strategy_index = cb.getSelectedIndex();
					}
				});
				
				return strategy_chooser;
			}
			JComboBox strategy_chooser2(String[] strategy_names,AI donita){
				
				shirley = donita;
				
				strategy_chooser2 = new JComboBox(strategy_names);
				strategy_chooser2.addActionListener(new ActionListener() {
			    	
					public void actionPerformed(ActionEvent e) {
											
						JComboBox cb = (JComboBox)e.getSource();
						shirley.strategy_index2 = cb.getSelectedIndex();
					}
				});
				
				return strategy_chooser2;
			}
			
			
			//autoplay buttom
			JButton auto(Graph<node, Integer> g){
				
				gg = g;
				
				auto = new JButton("  AUTOPLAY  ");
				auto.addActionListener(new ActionListener() {
	        	
					public void actionPerformed(ActionEvent e) {
	            	
						timer = new Timer();
						timer.schedule(new tool.autopress(AI1,AI2,propagation,gg),0, 1000);
					}
				});
				
				return auto;
			}
			
			//load buttom
			JButton load(Graph<node, Integer> g , vv vv,graph_generator g_generator,String[] graph_names){
				
				 gg = g;
				 the_v = vv;
				 vvv = the_v.return_vv();
				 g_generatorr = g_generator;
				 graph_namess = graph_names;
				
				 load = new JButton("Load Graph");
				 load.addActionListener(new ActionListener() {
			        	
			            public void actionPerformed(ActionEvent e) {
			            	
			            	JFileChooser chooser = new JFileChooser();
			            	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			            	chooser.setApproveButtonText("½T©w");
			            	chooser.showOpenDialog(null);
			            	
			            	File f = chooser.getSelectedFile(); 
			            	
			            	try{
			            		String buffer;
			            		String buffer_split[];
			            		FileReader fr=new FileReader(f);
			            		BufferedReader bfr =new BufferedReader(fr);
			            		
			            		SparseMultigraph<node, Integer> load_g = new SparseMultigraph<node, Integer>();
			            		int edge_id = 0;
			            		while((buffer = bfr.readLine())!=null){
			            			
			            			buffer_split = buffer.split(" ");
			            			
			            			node new_node = tool.return_node_by_id(load_g,Integer.parseInt(buffer_split[0]));
			            			load_g.addVertex(new_node);
			            			node new_node2 = tool.return_node_by_id(load_g,Integer.parseInt(buffer_split[1]));
			            			load_g.addVertex(new_node2);
			            			if(!load_g.isNeighbor(new_node, new_node2))load_g.addEdge(edge_id,new_node ,new_node2);
			            			edge_id++;
			            		}
			            		
			            		fr.close();
			            		
			            		gg = load_g;			            
								
			            		Class<? extends Layout<node,Integer>> layoutC = 
			    		                (Class<? extends Layout<node,Integer>>) layout_chooser.getSelectedItem();
			    		            try
			    		            {
			    		                Constructor<? extends Layout<node,Integer>> constructor = layoutC
			    		                        .getConstructor(new Class[] {Graph.class});
			    		                Object o = constructor.newInstance(gg);
			    		                Layout<node,Integer> l = (Layout<node,Integer>) o;
			    		                l.setInitializer(vvv.getGraphLayout());
			    		                l.setSize(vvv.getSize());
			    		                
			    						LayoutTransition<node,Integer> lt =
			    							new LayoutTransition<node,Integer>(vvv, vvv.getGraphLayout(), l);
			    						Animator animator = new Animator(lt);
			    						animator.start();
			    						vvv.getRenderContext().getMultiLayerTransformer().setToIdentity();
			    						vvv.repaint();
			    						
			    		                the_v.gg = gg; 
			    		            }
			    		            catch (Exception ee) {ee.printStackTrace();}
						                
						            
			            		
			            	}catch(IOException fe){}
			            	
			            	historyy.reset(gg);
				            historyy.write_graph_data(gg);
				            
				            scoree.renew_graph(gg);
				            scoree.count_score();
				            scoree.set_to_panel();
			            
				 }});
				
				return load;
				
			}
			
			//save history
			JButton save(Graph<node, Integer> g , VisualizationViewer<node,Integer> vv){
					
				 gg = g;
				 vvv = vv;
				 
				 save = new JButton("Save History");
				 
				 save.addActionListener(new ActionListener() {
			        	
			            public void actionPerformed(ActionEvent e) {  
			            	
			            	JFileChooser chooser = new JFileChooser();
			            	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			            	chooser.showSaveDialog(null);
			            	
			            	File f = chooser.getSelectedFile(); 
			           
			            	historyy.write(f);
			            }
			        });
				 
				 
				 
				 return save;
			}
			
			
			//parameters
			
			JLabel ern_l(){
			
				ern_l= new JLabel();
				ern_l.setText("n  ");
				
				return ern_l;
			}
			
			JLabel erp_l(){
				
				erp_l= new JLabel();
				erp_l.setText("p  ");
				
				return erp_l;
			}
			
			JLabel ban_l(){
				
				ban_l= new JLabel();
				ban_l.setText("n  ");
				
				return ban_l;
			}
			
			JLabel bal_l(){
				
				bal_l= new JLabel();
				bal_l.setText("l  ");
				
				return bal_l;
			}
			
			JLabel bas_l(){
				
				bas_l= new JLabel();
				bas_l.setText("s  ");
				
				return bas_l;
			}
			
			JLabel wsn_l(){
				
				wsn_l= new JLabel();
				wsn_l.setText("n  ");
				
				return wsn_l;
			}
			
			JLabel wsk_l(){
				
				wsk_l= new JLabel();
				wsk_l.setText("k  ");
				
				return wsk_l;
			}
			
			JLabel wsp_l(){
				
				wsp_l= new JLabel();
				wsp_l.setText("p  ");
				
				return wsp_l;
			}
			
			JTextField ern(){
				
				ern = new JTextField();
				ern.setColumns(2);
				ern.setText("30");
				
				return ern;
			}
			
			JTextField erp(){
				
				erp = new JTextField();
				erp.setColumns(2);
				erp.setText("0.2");
				
				return erp;
			}
			
			JTextField ban(){
				
				ban = new JTextField();
				ban.setColumns(2);
				ban.setText("10");
				
				return ban;
			}


			JTextField bal(){
	
				bal = new JTextField();
				bal.setColumns(2);
				bal.setText("4");
	
				return bal;
			}


			JTextField bas(){
	
				bas = new JTextField();
				bas.setColumns(2);
				bas.setText("20");
	
				return bas;
			}
			
			JTextField wsn(){
				
				wsn = new JTextField();
				wsn.setColumns(2);
				wsn.setText("10");
				
				return wsn;
			}


			JTextField wsk(){
	
				wsk = new JTextField();
				wsk.setColumns(2);
				wsk.setText("4");
	
				return wsk;
			}


			JTextField wsp(){
	
				wsp = new JTextField();
				wsp.setColumns(2);
				wsp.setText("0.1");
	
				return wsp;
			}
			
			JLabel icp_l(){
				
				icp_l= new JLabel();
				icp_l.setText("p  ");
				
				return icp_l;
			}
			
			JTextField icp(){
				
				icp = new JTextField();
				icp.setColumns(2);
				icp.setText("0.2");
	
				return icp;
			}
			
			JTextField ltt(){
				
				ltt = new JTextField();
				ltt.setColumns(2);
				ltt.setText("0.1");
	
				return ltt;
			}
			
			JLabel ltt_l(){
				
				ltt_l= new JLabel();
				ltt_l.setText("treshold ");
				
				return ltt_l;
			}
			
			//score label
			JLabel iscore(){
				iscore = new JLabel();
				iscore.setText("            Score              ");
				return iscore;
			}
			
			//imformation label
			JLabel imformation(){
				imformation = new JLabel();
				imformation.setText("          Imformation           ");
				return imformation;
			}
			
			
			//undo buttom
			JButton undo(Graph<node, Integer> g, VisualizationViewer<node,Integer> vv){
			
				gg = g;
				vvv = vv;
				
				undo = new JButton("  Undo  ");
				undo.addActionListener(new ActionListener() {
	        	
					public void actionPerformed(ActionEvent e) {
	            	
						historyy.undo(gg);
						vvv.repaint();
						
						scoree.count_score();
			            scoree.set_to_panel();
					}
				});
				
				return undo;
			}
			
			//help buttom
			JButton help(){
			
				
				help = new JButton("  Help  ");
				help.addActionListener(new ActionListener() {
	        	
					public void actionPerformed(ActionEvent e) {
	            	
						JOptionPane.showMessageDialog((JComponent)e.getSource(), instructions, "Help", JOptionPane.PLAIN_MESSAGE);
					}
				});
				
				return help;
			}
			
			
			
		
			
			
			
}

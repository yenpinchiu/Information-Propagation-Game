package IPG;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;
import edu.uci.ics.jung.algorithms.generators.random.ErdosRenyiGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;


public class graph_generator {
	
	//index
	int graph_index;
	
	//factory
	final Factory<UndirectedGraph<node, Integer>> graphFactory;
	final Factory<Graph<node, Integer>> graphFactory2;
	final Factory<node> vertexFactory;
	final Factory<Integer> edgeFactory;
	
	
	//variable
	int ien = 30;
	double iep = 0.2;
	int ibn = 10;
	int ibe = 4;
	int ibs = 20;
	int iwn = 30;
	int iwk = 4;
	double iwp = 0.1;

	 
	graph_generator(){
		
		
		//graph factory
		graphFactory =
	    		new Factory<UndirectedGraph<node, Integer>>() {
	    		public UndirectedGraph<node, Integer> create() {
	    		return new UndirectedSparseGraph<node, Integer>();
	    		}
	    	};
	
	    graphFactory2 =
		    	new Factory<Graph<node, Integer>>() {
		    	public Graph<node, Integer> create() {
		    	return new SparseGraph<node, Integer>();
		    	}
		    };
	    //vertex factory
	   vertexFactory = new Factory<node>() {
    		int count;
			public node create() {		
			node n = new node(count++,0);
			return n;
		}};
		
		//edge factory
		edgeFactory = new Factory<Integer>() {
			int count;
			public Integer create() {
			return count++;
		}};
		///////////factory end
				
	}
	
	
	//ER graph generator
	public static<V,E> Graph<V,E> generateER(Factory<UndirectedGraph<V, E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int n,double p){
	      
		ErdosRenyiGenerator<V,E> e = new ErdosRenyiGenerator<V,E>(graphFactory, vertexFactory, edgeFactory,n,p);
		return e.create();		
	}
	
	//BA graph generator
	public static<V,E> Graph<V,E> generateBA(Factory<Graph<V,E>> graphFactory, Factory<V> vertexFactory, Factory<E> edgeFactory, int n,int e,int steps){
		        
		Set<V> vset=new HashSet<V>();
		for(int i=0;i<n;i++)vset.add(vertexFactory.create());
				      
			BarabasiAlbertGenerator<V,E> BAgen=new BarabasiAlbertGenerator<V,E>(graphFactory, vertexFactory, edgeFactory, n, e, vset);
			BAgen.evolveGraph(steps);
			return BAgen.create();		
	}
	
	//WS graph generator
	public static<V,E> Graph<V,E> generateWS(int n,int k,double p){
		
		int num_neighbor = k/2;
		int num_nodes = n;
		node temp;
		int edge_count = 0;
		
		SparseGraph <node,Integer> g = new SparseGraph<node, Integer>();
		//n nodes		
		for(int i=0;i<num_nodes;i++){
			temp = new node(i,0);
			g.addVertex(temp);
		}
		//ring
		for(int i=0;i<num_nodes;i++){		
			for(int j=0;j<num_neighbor;j++){
				
				int right;
				int left;
				if(i+j>=num_nodes-1) right = i+j-num_nodes+1;else right = i+j+1;
				if(i-j<=0) left = i-j+num_nodes-1;else left = i-j-1;
				
				g.addEdge(edge_count, tool.return_node_by_id(g,i),  tool.return_node_by_id(g,right));
				edge_count++;
				g.addEdge(edge_count, tool.return_node_by_id(g,i),  tool.return_node_by_id(g,left));
				edge_count++;
			}
		}
		//rewrite
		for(int i=0;i<num_nodes;i++){
			for(int j=i;j<num_nodes;j++){
				
				node temp_node1 = tool.return_node_by_id(g,i);
				node temp_node2 = tool.return_node_by_id(g,j);
				
				if( g.isNeighbor(temp_node1,temp_node2)){
					
					int temp_edge = g.findEdge(temp_node1,temp_node2);
					
					if(Math.random() <= p){
						g.removeEdge(temp_edge);
						g.addEdge(temp_edge, temp_node1, tool.return_node_by_id(g, (int)(Math.random() * num_nodes)));
					}
				}
			}
		}
		
		
		return (Graph<V, E>) g;
	}

	
	
}

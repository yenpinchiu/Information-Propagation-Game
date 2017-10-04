package IPG;

public class node {
	
	int nid;
	int country;
	boolean scar = false;//useful at sometime need record
	boolean scar2 = false;
	
	public node(int nid,int country){
		
		this.nid = nid;
		this.country = country;
	}
	
	//use nid as label
	public String toString(){
		return "" + nid + "";
	}

}
//package vn.edu.tdt.it.dsa;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;  
import java.util.*;

public class WarehouseBook {
	
	protected class WarehouseNode {
		private ProductRecord record;
		private WarehouseNode left, right;
		private int balance; 
		
		public WarehouseNode (ProductRecord pr)
		{
			record = pr;
			left = null;
			right = null;
			balance = 0;
		}
		public ProductRecord getRecord() {
			return record;
		}
		public void setRecord(ProductRecord record) {
			this.record = record;
		}
		public WarehouseNode getLeft() {
			return left;
		}
		public void setLeft(WarehouseNode left) {
			this.left = left;
		}
		public WarehouseNode getRight() {
			return right;
		}
		public void setRight(WarehouseNode right) {
			this.right = right;
		}
		public int getBalance() {
			return balance;
		}
		public void setBalance(int balance) {
			this.balance = balance;
		}
		
		//Dinh nghia ham so sanh hai node
		public int compareTo(WarehouseNode wn){
			if (record.getProductID()==wn.getRecord().getProductID())
				return 1;
			else if (record.getProductID()!=wn.getRecord().getProductID())
				return 0;
			else
				return -1;
		}
	}
	//End of WarehouseNode
	
	private WarehouseNode root;
	private int size;
	String tam;
	
	public int getSize(){
		return size;
	}
	
	public WarehouseBook(){
		root = null;
		size = 0;
	}
	//phuong thuc insert vao BST
	public void insert(ProductRecord data) {
		root = insert(root, data);
	}
	//Viet ham so sanh
	private int compare(ProductRecord x, ProductRecord y) {
		if (x.getProductID()!=y.getProductID())
				return 1;
			else if (x.getProductID()==y.getProductID())
				return 0;
			else
				return -1;
	}
	
	//Viet ham them
	private WarehouseNode insert(WarehouseNode p, ProductRecord toInsert) {
		if (p == null)
			return new WarehouseNode(toInsert);

		if (compare(toInsert, p.record) == 0)
		{
			p.record.setQuantity(p.record.getQuantity() + toInsert.getQuantity());
			return p;
		}
		if (compare(toInsert, p.record) < 0)
			p.left = insert(p.left, toInsert);
		else
			p.right = insert(p.right, toInsert);

		return p;
	}

	
	public WarehouseBook(File file) throws IOException{
		//sinh vien viet ma tai day
		FileReader fr= new FileReader(file);
        BufferedReader br= new BufferedReader(fr);
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line,"(");  
		StringTokenizer st1;
		ProductRecord pr;
		while (st.hasMoreTokens()) {  
			String t = st.nextToken();
			st1 = new StringTokenizer(t,")");
			while(st1.hasMoreTokens()){
				t = st1.nextToken().replace("N","").trim();
				int t1 = Integer.parseInt(t.substring(0,3));
				int t2 = Integer.parseInt(t.substring(3));
				pr = new ProductRecord(t1,t2);
				
			}
		}  
	}
	
	//In ra 
	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(WarehouseNode r) {
		if (r != null) {
			System.out.print(r.record.toString()+ "\n");
			preOrderHelper(r.left);
			preOrderHelper(r.right);
		}
	}
	
	//Luu file
	public void save(File file){
		//sinh vien viet ma tai day
	}
	
	//Doc file Events
	public void process(File file) throws IOException{
		//sinh vien viet ma tai day
		FileReader fr= new FileReader(file);
        BufferedReader br= new BufferedReader(fr);
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line," ");  
		Vector<String> v = new Vector<String>();
		while (st.hasMoreTokens()) { 
			v.add(st.nextToken());
		}
		process(v);
	}
	
	public void process(List<String> events){
		//sinh vien viet ma tai day
		//Xu ly su kien la 1
		String d;
		int g, c;
		for (String v: events){
			d = v.substring(0,1);
			
			if(d.equals("1")){
				g = Integer.parseInt(v.substring(1,4));
				c = Integer.parseInt(v.substring(4));
				ProductRecord pr = new ProductRecord(g, c);
				insert(pr);
			}
		}
		
		//Xu ly su kien ............
	}
	
	@Override
	public String toString(){
		String res = "";
		//sinh vien viet ma tai day
		return res;
	}
	
	public static void main(String[] args){
		//vi du ham main de chay
		try{
			WarehouseBook wb = new WarehouseBook(new File("warehouse.txt"));
			wb.process(new File("events.txt"));
			wb.preOrderTraversal();
			System.out.printf("\nCheck\n");
			
			wb.save(new File("warehouse_new.txt"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}

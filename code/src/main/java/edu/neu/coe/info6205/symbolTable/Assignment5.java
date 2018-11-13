package edu.neu.coe.info6205.symbolTable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.neu.coe.info6205.symbolTable.BSTSimple.Node;
import edu.neu.coe.info6205.util.FileWriter;

public class Assignment5 {
	private int initsize;
	private int range;
	
	private Assignment5(int is, int r) {
		initsize = is;
		range = r;
	}
	
	private Map<Integer, Integer> nodeGenerator() {
		Random rd = new Random();
		Map<Integer, Integer> map = new HashMap<>();
		while(map.size() < initsize) {
			int i = rd.nextInt(range);
			map.put(i, i);
		}
		return map;
	}
	
	private BSTSimple bstGenerator() {
		BSTSimple bst = new BSTSimple(nodeGenerator());
		return bst;
	}
	
	private BSTSimple randomInsertOrDelete(BSTSimple bst,int n) {
		int delN = 0;
		int insN = 0;
		while(delN+insN < 2*n) {
			Random rd = new Random();
			int choice = rd.nextInt(2);
			if(choice == 0 && delN < n) {
				int key = rd.nextInt(range);
				bst.delete(key);
				delN++;
			}
			if(choice == 1 && insN < n) {
				int key = rd.nextInt(range);
				bst.put(key, key);
				insN ++;
			}
		}
		return bst;
	}
	
	public static String gateWay(int initRange, int range, int times) {
		Assignment5 tester = new Assignment5(initRange,range);
		BSTSimple bst = tester.bstGenerator();
		//System.out.println(bst);
		int preheight = bst.height();
		int precount = bst.size();
		double avgpre = bst.avgDepth();
		bst = tester.randomInsertOrDelete(bst, times);
		//System.out.println(bst);
		int postheight = bst.height();
		double avgpost = bst.avgDepth();
		int postcount = bst.size();
//		System.out.println(String.format("test sepecificatio: init size of BST: %d, insert&delete range: %d, exp times: %d", initRange, range, times));
//		System.out.println(String.format("-------size before is %d, size after is %d", precount, postcount));
//		System.out.println(String.format("-------avg depth before is %f, avg depth after is %f", avgpre, avgpost));
//		System.out.println(String.format("-------max depth before is %d, max depth after is %d", preheight, postheight));
		String toprint = String.format("%d,%d,%d,%f,%f", initRange, range, times, avgpre, avgpost);
		return toprint;
	}
	
	public static void main(String[] args) {
		FileWriter fw = new FileWriter();
		fw.setPath("./data2.csv");
		try {
			fw.openFile();
			int times = 1000000;
			int maxinitsize = 20000;
			int maxrange = 20000;
			for(int range = 200; range<maxrange; range*=2)
				for(int init = 100; init<maxinitsize&&init<range ; init+=100) {
					String towrite = gateWay(init, range, times);
					fw.writeLine(towrite);
				}
			fw.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

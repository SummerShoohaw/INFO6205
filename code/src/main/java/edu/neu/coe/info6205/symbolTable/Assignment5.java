package edu.neu.coe.info6205.symbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.neu.coe.info6205.symbolTable.BSTSimple.Node;

public class Assignment5 {
	private int initRange;
	private int range;
	
	private Assignment5(int ir, int r) {
		initRange = ir;
		range = r;
	}
	
	private Map<Integer, Integer> nodeGenerator() {
		Random rd = new Random();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i<initRange;i++) {
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
	
	public static void gateWay(int initRange, int range, int times) {
		Assignment5 tester = new Assignment5(initRange,range);
		BSTSimple bst = tester.bstGenerator();
		System.out.println(bst);
		int preheight = bst.height();
		bst = tester.randomInsertOrDelete(bst, times);
		int postheight = bst.height();
		System.out.println(String.format("test sepecificatio: initRange of BST: %d, insert&delete range: %d, exp times: %d", initRange, range, times));
		System.out.println(String.format("-------depth before is %d, depth after is %d", preheight, postheight));
	}
	
	public static void main(String[] args) {
		gateWay(100,255,255);
	}
	
	
}

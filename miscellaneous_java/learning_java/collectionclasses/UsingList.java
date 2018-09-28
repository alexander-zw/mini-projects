package collectionclasses;

import java.util.*;

public class UsingList {
	public static void main(String[] args){
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= 100; i++){
			list.add(i);
		}
		
		list.remove(10);
		list.remove(39);
		
		for(int x: list){
			System.out.printf("%4s", x);
			if(x % 20 == 0){
				System.out.println();
			}
		}
//		ListIterator<Integer> iter = list.listIterator();
//		while(iter.hasNext()){
//			System.out.print(iter.next() + " ");
//			
//		}
//		System.out.println("\n" + list);
	
	}

}

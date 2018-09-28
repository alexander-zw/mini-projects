package tools;

public class ArrayMethods {
	
	private ArrayMethods(){};
	
	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param ch the character to search for
	 * @return the first index of {@code ch} or {@code -1} if not found
	 */
	static public int search(char[] arr, char ch){
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == ch)
				return i;
		}
		return -1;
	}

	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param num the integer to search for
	 * @return the first index of {@code num} or {@code -1} if not found
	 */
	static public int search(int[] arr, int num){
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == num)
				return i;
		}
		return -1;
	}

	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param obj the object to search for
	 * @return the first index of {@code obj} or {@code -1} if not found
	 */
	static public int search(Object[] arr, Object obj){
		for(int i = 0; i < arr.length; i++){
			if(arr[i].equals(obj))
				return i;
		}
		return -1;
	}

	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param ch the character to search for
	 * @return the first indexes of {@code ch} or {@code null} if not found
	 */
	static public int[] search2D(char[][] arr, char ch){
		for(int i = 0; i < arr.length; i++){
			for(int j= 0; j < arr.length; j++){
				if(arr[i][j] == ch){
					int[] a = {i, j};
					return a;
				}
			}
		}
		return null;
	}

	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param num the integer to search for
	 * @return the first indexes of {@code num} or {@code null} if not found
	 */
	static public int[] search2D(int[][] arr, int num){
		for(int i = 0; i < arr.length; i++){
			for(int j= 0; j < arr.length; j++){
				if(arr[i][j] == num){
					int[] a = {i, j};
					return a;
				}
			}
		}
		return null;
	}

	/**
	 * Linear search.
	 * @param arr the array to search
	 * @param obj the object to search for
	 * @return the first indexes of {@code obj} or {@code null} if not found
	 */
	static public int[] search2D(Object[][] arr, Object obj){
		for(int i = 0; i < arr.length; i++){
			for(int j= 0; j < arr.length; j++){
				if(arr[i][j].equals(obj)){
					int[] a = {i, j};
					return a;
				}
			}
		}
		return null;
	}
	
}

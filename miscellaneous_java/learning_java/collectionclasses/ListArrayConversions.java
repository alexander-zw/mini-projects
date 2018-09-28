package collectionclasses;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class ListArrayConversions {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		List<int[]> list = Arrays.asList(arr);
		for (int[] nums : list) {
			System.out.println(nums.length + ", " + nums[0]);
		}
		
		IntStream stream = Arrays.stream(arr);
		Iterator<Integer> iter = stream.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}

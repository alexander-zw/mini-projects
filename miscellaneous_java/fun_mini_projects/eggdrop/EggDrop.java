package eggdrop;

public class EggDrop {

	/**
	 * Solves the egg drop problem. The passed-in parameter is expected to be empty
	 * and will not be read. It will be filled in with the optimal strategies of
	 * which floor the next egg should be dropped on.
	 * 
	 * @param expected a two-dimensional array; {@code expected.length - 1} is the
	 *                 number of eggs, and {@code expected[0].length - 1} is the
	 *                 number of floors
	 * @return the expected number of drops
	 */
	public static double maximumDrops(int[][] optimal) {
		int eggs = optimal.length - 1;
		int floors = optimal[0].length - 1;
		double[][] maximum = new double[eggs + 1][floors + 1];

		// If there are no eggs but some floors, it is impossible
		for (int f = 1; f <= floors; f++) {
			maximum[0][f] = Double.POSITIVE_INFINITY;
			optimal[0][f] = -1; // failed to find answer
		}

		// If there are no floors we are already done
		for (int e = 0; e <= eggs; e++) {
			maximum[e][0] = 0;
			optimal[e][0] = 0; // we are done
			maximum[e][1] = 0;
			optimal[e][1] = 0; // we are done
		}
		
		// recurrence
		for (int e = 1; e <= eggs; e++) {
			for (int f = 2; f <= floors; f++) {
				double min = Double.POSITIVE_INFINITY;
				int minDrop = -1;
				for (int drop = 2; drop <= f; drop++) {
					// either egg broke or did not break
					double curr = Math.max(maximum[e - 1][drop - 1], maximum[e][f - drop + 1]) + 1;
					if (curr < min) {
						min = curr;
						minDrop = drop;
					}
				}
				maximum[e][f] = min;
				optimal[e][f] = minDrop;
			}
		}

		return maximum[eggs][floors];
	}

	public static void main(String[] args) {
		int eggs = 2;
		int floors = 100;
		
		int[][] optimal = new int[eggs + 1][floors + 1];
		double expected = maximumDrops(optimal);
		System.out.println(expected);
		
		for (int i = 0; i < optimal.length; i++) {
			for (int j = 0; j < optimal[i].length; j++) {
				System.out.print(optimal[i][j] + "\t");
			}
			System.out.println();
		}
	}

}

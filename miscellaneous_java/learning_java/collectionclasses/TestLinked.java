package collectionclasses;

public class TestLinked extends UsingList {

	public static void main(String[] args) {
		Linked<Integer> empty = new Linked<Integer>();
		Linked<Integer> list1 = new Linked<Integer>(0);
		Linked<Integer> list2 = new Linked<Integer>(0, new Linked<Integer>(1));
		Linked<Integer> list3 = new Linked<Integer>(2, new Linked<Integer>(3, list2));
		int one = 1;
		try {
			System.out.println(empty.contains(one));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(list1.contains(one));
			System.out.println(list2.contains(one));
			System.out.println(list3.contains(one));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

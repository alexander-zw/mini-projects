package collectionclasses;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list implemented as a linked list
 * 
 * @author AlexanderWu
 *
 * @param <E> The element type
 */
public class Linked<E> implements List<E> {
	
	private E first; // first element of linked list
	private Linked<E> rest; // rest of linked list
	
	/**
	 * Creates an empty linked list
	 */
	public Linked() {
		this(null, null);
	}
	
	/**
	 * Creates linked list with one element; rest is empty list
	 * 
	 * @param elem the element
	 */
	public Linked(E elem) {
		this(elem, new Linked<E>()); // rest is empty list
	}

	/**
	 * Creates linked list with {@code firstElem} as the first element, and {@code restOfList}
	 * as the other elements
	 * 
	 * @param firstElem the first element
	 * @param restOfList a linked list containing the remaining elements
	 */
	public Linked(E firstElem, Linked<E> restOfList) {
		first = firstElem;
		rest = restOfList;
	}
	
	public E getFirst() throws Exception {
		if (isEmpty())
			throw new Exception("Empty list does not have a first element");
		return first;
	}
	
	public Linked<E> getRest() throws Exception {
		if (isEmpty())
			throw new Exception("Empty list does not link to any sublist");
		return rest;
	}
	
	@Override
	public String toString() {
		String representation = "Linked List [";
		Linked<E> remaining = this;
		while (!remaining.isEmpty()) {
			representation += remaining.first + ", ";
			remaining = remaining.rest;
		}
		if (!this.isEmpty()) // take away extra comma and space
			representation = representation.substring(0, representation.length() - 2);
		return representation + "]";
	}

	@Override
	public int size() {
		int index = 0;
		Linked<E> remaining = this;
		while(!remaining.isEmpty()) {
			remaining = remaining.rest;
			index++;
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) throws ClassCastException {
		Linked<E> remaining = this;
		while (!remaining.isEmpty()) {
			if (remaining.first.getClass().isInstance(o) && remaining.first.equals((E)o)) return true;
			remaining = remaining.rest;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}

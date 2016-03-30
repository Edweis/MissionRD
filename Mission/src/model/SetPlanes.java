package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author piou Represent a set of planes
 */
public class SetPlanes implements Iterable<Plane> {
	ArrayList<Plane> set;

	public SetPlanes() {
		set = new ArrayList<Plane>();
	}

	public void add(Plane p) {
		set.add(p);
	}

	public void set(int index, Plane element) {
		set.set(index, element);
	}

	public Plane get(int index) {
		return set.get(index);
	}

	@Override
	public Iterator<Plane> iterator() {
		return set.iterator();
	}

	public String toString() {
		String s = "This set of planes contains : ";
		for (Plane p : set) {
			s = s + "\n" + p.toString();
		}
		return s;
	}

}

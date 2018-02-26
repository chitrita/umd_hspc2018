package hspc2018.flipping;

// Helper class for the solution -- should not be included in the live contest!

public class Egg {
	int element;
	Egg next;
	Egg prev;

	public Egg (int element, Egg next, Egg prev) {
		this.element = element;
		this.next = next;
		this.prev = prev;
	}
	public Egg() {}
}

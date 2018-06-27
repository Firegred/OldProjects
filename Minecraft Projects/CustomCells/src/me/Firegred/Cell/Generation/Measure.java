package me.Firegred.Cell.Generation;

public enum Measure {

	LENGTH(0),
	WIDTH(1),
	HEIGHT(2);
	private int value;
	private Measure(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
};

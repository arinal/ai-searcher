package org.stei.ai.sample.eightpuzzle;

public enum Direction {
	Up(-3),
	Left(-1),
	Right(1),
	Down(3);
	
	private int value;

	Direction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

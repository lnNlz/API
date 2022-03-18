package com.javaEngine.math;

/**
 * 
 */
public class Vec2 {
	protected int x;
	protected int y;
	
	/**
	 * @param x
	 * - {@code X} value
	 * 
	 * @param y
	 * - {@code Y} value
	 */
	public Vec2(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param value
	 * - Value for both {@code x} and {@code y}
	 * 
	 * @see #Vec2(int, int)
	 */
	public Vec2(final int value) {
		this(value, value);
	}
	
	/**
	 * Sets {@code x and y} to {@code 0}
	 * 
	 * @see #Vec2(int)
	 * @see #Vec2(int, int)
	 */
	public Vec2() {
		this(0);
	}
	
	/**
	 * @return
	 * the {@code length} or {@code magnitude} of this vector,
	 * this is achieved through Pythagoras theorem
	 */
	public final int length() {
		return (int)Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Normalizes this {@code vector 2D}
	 * 
	 * @return
	 * Normalized vector
	 */
	public final Vec2 normalize() {
		// Hypoteneuse_
		final int length = length();
		
		// Vector 2D to return
		final Vec2 newVec2 = new Vec2();
		
		// Prevents division by zero
		if(length == 0) return newVec2;
		
		// Normalize
		newVec2.x = x / length;
		newVec2.y = y / length;
		
		return newVec2;
	}
	
	/**
	 * @return
	 * {@code slope} of this vector
	 * <br>
	 * calculation:
	 * <br>
	 * <br>
	 * {@code y / x}
	 */
	public final int slope() {
		return y / x;
	}
	
	/**
	 * Sets this' {@code x} and {@code y} value
	 * 
	 * @param newValue
	 * - {@code Vector 2D} that contains the new value
	 * 
	 * @return
	 * {@code this} vector
	 */
	public Vec2 set(final Vec2 newValue) {
		x = newValue.x;
		y = newValue.y;
		
		return this;
	}
	
	@Override
	public Vec2 clone() {
		return new Vec2(x, y);
	}
	
	
	@Override
	public String toString() {
		return "[x  : " + x + ", y : " + y + "]";
	}
	
	/**
	 * @return
	 * {@code Vector 2D} with this' {@code x}, {@code y} and {@code z} value
	 */
	public Vec3 toVector3D() {
		return new Vec3(x, y, 0);
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 2D} has the same {@code x} and {@code y} value
	 * 
	 * @param anotherVec2
	 * - Another Vector 2D to examine
	 * 
	 * @return
	 * {@code true} if values are equal; {@code false} otherwise
	 */
	public boolean equals(final Vec2 anotherVec2) {
		return x == anotherVec2.x && y == anotherVec2.y;
	}
	
	/**
	 * @return
	 * {@code true} if values are zero; {@code false} otherwise
	 */
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	
	/**
	 * @return
	 * {@code x} value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets new {@code x} value
	 * 
	 * @param x
	 * - new Value for {@code x}
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return
	 * {@code y} value
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets new {@code y} value
	 * 
	 * @param y
	 * - new Value for {@code y}
	 */
	public void setY(int y) {
		this.y = y;
	}
}
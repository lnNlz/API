package com.javaEngine.math;

/**
 * 
 */
public class Vec2F {
	protected float x;
	protected float y;
	
	/**
	 * @param x
	 * - {@code X} value
	 * 
	 * @param y
	 * - {@code Y} value
	 */
	public Vec2F(final float x, final float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param value
	 * - Value for both {@code x} and {@code y}
	 * 
	 * @see #Vec2(float, float)
	 */
	public Vec2F(final float value) {
		this(value, value);
	}
	
	/**
	 * Sets {@code x and y} to {@code 0.0}
	 * 
	 * @see #Vec2(float)
	 * @see #Vec2(float, float)
	 */
	public Vec2F() {
		this(0);
	}
	
	/**
	 * @return
	 * the {@code length} or {@code magnitude} of this vector,
	 * this is achieved through Pythagoras theorem
	 */
	public final float length() {
		return (float)Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Normalizes this {@code vector 2D Float}
	 * 
	 * @return
	 * Normalized vector
	 */
	public final Vec2F normalize() {
		// Hypoteneuse_
		final float length = length();
		
		// Vector 2D to return
		final Vec2F newVec2F = new Vec2F();
		
		// Prevents division by zero
		if(length == 0) return newVec2F;
		
		// Normalize
		newVec2F.x = x / length;
		newVec2F.y = y / length;
		
		return newVec2F;
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
	public final float slope() {
		return y / x;
	}
	
	/**
	 * Sets this' {@code x} and {@code y} value
	 * 
	 * @param newValue
	 * - {@code Vector 2D Float} that contains the new value
	 * 
	 * @return
	 * {@code this} vector
	 */
	public Vec2F set(final Vec2F newValue) {
		x = newValue.x;
		y = newValue.y;
		
		return this;
	}
	
	@Override
	public Vec2F clone() {
		return new Vec2F(x, y);
	}
	
	
	@Override
	public String toString() {
		return "[x  : " + x + ", y : " + y + "]";
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 2D Float} has the same {@code x} and {@code y} value
	 * 
	 * @param anotherVec2
	 * - Another Vector 2D Float to examine
	 * 
	 * @return
	 * {@code true} if values are equal; {@code false} otherwise
	 */
	public boolean equals(final Vec2 anotherVec2F) {
		return x == anotherVec2F.x && y == anotherVec2F.y;
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 2D Float} has the same {@code x} and {@code y} value by rounding them
	 * 
	 * @param anotherVec2
	 * - Another Vector 2D Float to examine
	 * 
	 * @return
	 * {@code true} if rounded values are equal; {@code false} otherwise
	 */
	public boolean equalsRounded(final Vec2 anotherVec2F) {
		return (int)x == (int)anotherVec2F.x && (int)y == (int)anotherVec2F.y;
	}
	
	/**
	 * @return
	 * {@code true} if values are zero; {@code false} otherwise
	 */
	public boolean isZero() {
		return x == 0.0 && y == 0.0;
	}
	
	/**
	 * @return
	 * {@code x} value
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets new {@code x} value
	 * 
	 * @param x
	 * - new Value for {@code x}
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * @return
	 * {@code y} value
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Sets new {@code y} value
	 * 
	 * @param y
	 * - new Value for {@code y}
	 */
	public void setY(float y) {
		this.y = y;
	}
}
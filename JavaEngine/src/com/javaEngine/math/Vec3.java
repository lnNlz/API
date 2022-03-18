package com.javaEngine.math;

/**
 * 
 */
public class Vec3 {
	protected int x;
	protected int y;
	protected int z;
	
	/**
	 * @param x
	 * - {@code X} value
	 * 
	 * @param y
	 * - {@code Y} value
	 * 
	 * @param z
	 * - {@code Z} value
	 */
	public Vec3(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @param value
	 * - Value for {@code x}, {@code y} and {@code z}
	 * 
	 * @see #Vec3(int, int, int)
	 */
	public Vec3(final int value) {
		this(value, value, value);
	}
	
	/**
	 * Sets {@code x, y and z} to {@code 0}
	 * 
	 * @see #Vec3(int)
	 * @see #Vec3(int, int, int)
	 */
	public Vec3() {
		this(0);
	}
	
	/**
	 * @return
	 * the {@code length} or {@code magnitude} of this 3D vector,
	 * this is achieved through Pythagoras theorem
	 */
	public final int length() {
		return (int)Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Normalizes this {@code vector 3D}
	 * 
	 * @return
	 * Normalized vector
	 */
	public final Vec3 normalize() {
		// Hypoteneuse_
		final int length = length();
		
		// Vector 2D to return
		final Vec3 newVec3 = new Vec3();
		
		// Prevents division by zero
		if(length == 0) return newVec3;
		
		// Normalize
		newVec3.x = x / length;
		newVec3.y = y / length;
		newVec3.z = z / length;
		
		return newVec3;
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
	 * Sets this' {@code x}, {@code y} and {@code z} value
	 * 
	 * @param newValue
	 * - {@code Vector 3D} that contains the new value
	 * 
	 * @return
	 * {@code this} vector
	 */
	public Vec3 set(final Vec3 newValue) {
		x = newValue.x;
		y = newValue.y;
		z = newValue.z;
		
		return this;
	}
	
	@Override
	public Vec3 clone() {
		return new Vec3(x, y, z);
	}
	
	@Override
	public String toString() {
		return "[x  : " + x + ", y : " + y + ", z  : " + z + "]";
	}
	
	/**
	 * @return
	 * - {@code 3D matrix} representation of this {@code vector}
	 */
	public Matrix toMatrix3D() {
		// 3D matrix
		final Matrix matrix = new Matrix(3);
		
		matrix.set(0, 0, x);
		matrix.set(1, 1, y);
		matrix.set(2, 2, z);
		
		return matrix;
	}
	
	/**
	 * @return
	 * - {@code 4D matrix} representation of this {@code vector}
	 */
	public Matrix toMatrix4D() {
		// 4D matrix
		final Matrix matrix = new Matrix(4);
		
		matrix.set(0, 0, x);
		matrix.set(1, 1, y);
		matrix.set(2, 2, z);
		matrix.set(3, 3, 1); // w
		
		return matrix;
	}
	
	/**
	 * @return
	 * {@code Vector 3D Float} with this' {@code x}, {@code y} and {@code z} value
	 */
	public Vec3F toVector3DF() {
		return new Vec3F(x, y, z);
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 3D} has the same {@code x}, {@code y} and {@code z} value
	 * 
	 * @param anotherVec3
	 * - Another Vector 3D to examine
	 * 
	 * @return
	 * {@code true} if values are equal; {@code false} otherwise
	 */
	public boolean equals(final Vec3 anotherVec3) {
		return x == anotherVec3.x && y == anotherVec3.y && z == anotherVec3.z;
	}
	
	/**
	 * @return
	 * {@code true} if values are zero; {@code false} otherwise
	 */
	public boolean isZero() {
		return x == 0 && y == 0 && z == 0;
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
	
	/**
	 * @return
	 * {@code z} value
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Sets new {@code z} value
	 * 
	 * @param z
	 * - new Value for {@code z}
	 */
	public void setZ(int z) {
		this.z = z;
	}
}
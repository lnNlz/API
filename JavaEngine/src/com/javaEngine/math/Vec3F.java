package com.javaEngine.math;

/**
 * 
 */
public class Vec3F {
	protected float x;
	protected float y;
	protected float z;
	
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
	public Vec3F(final float x, final float y, final float z) {
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
	public Vec3F(final float value) {
		this(value, value, value);
	}
	
	/**
	 * Sets {@code x, y and z} to {@code 0}
	 * 
	 * @see #Vec3(float)
	 * @see #Vec3(float, float, float)
	 */
	public Vec3F() {
		this(0);
	}
	
	/**
	 * @return
	 * the {@code length} or {@code magnitude} of this 3D vector,
	 * this is achieved through Pythagoras theorem
	 */
	public final float length() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Normalizes this {@code vector 3D Float}
	 * 
	 * @return
	 * Normalized vector
	 */
	public final Vec3F normalize() {
		// Hypoteneuse_
		final float length = length();
		
		// Vector 2D to return
		final Vec3F newVec3F = new Vec3F();
		
		// Prevents division by zero
		if(length == 0.0) return newVec3F;
		
		// Normalize
		newVec3F.x = x / length;
		newVec3F.y = y / length;
		newVec3F.z = z / length;
		
		return newVec3F;
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
	 * Sets this' {@code x}, {@code y} and {@code z} value
	 * 
	 * @param newValue
	 * - {@code Vector 3D Float} that contains the new value
	 * 
	 * @return
	 * {@code this} vector
	 */
	public Vec3F set(final Vec3F newValue) {
		x = newValue.x;
		y = newValue.y;
		z = newValue.z;
		
		return this;
	}
	
	@Override
	public Vec3F clone() {
		return new Vec3F(x, y, z);
	}
	
	
	@Override
	public String toString() {
		return "[x  : " + x + ", y : " + y + ", z  : " + z + "]";
	}
	
	/**
	 * @return
	 * - {@code 3D matrix float} representation of this {@code vector}
	 */
	public MatrixF toMatrixF3D() {
		// 3D matrix
		final MatrixF matrix = new MatrixF(3);
		
		matrix.set(0, 0, x);
		matrix.set(1, 1, y);
		matrix.set(2, 2, z);
		
		return matrix;
	}
	
	/**
	 * @return
	 * - {@code 4D matrix float} representation of this {@code vector}
	 */
	public MatrixF toMatrixF4D() {
		// 4D matrix
		final MatrixF matrix = new MatrixF(4);
		
		matrix.set(0, 0, x);
		matrix.set(1, 1, y);
		matrix.set(2, 2, z);
		matrix.set(3, 3, 1); // w
		
		return matrix;
	}
	
	/**
	 * @return
	 * {@code Vector 3D} with this' {@code x}, {@code y} and {@code z} value
	 */
	public Vec3 toVector3D() {
		return new Vec3((int)x, (int)y, (int)z);
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 3D Float} has the same {@code x}, {@code y} and {@code z} value
	 * 
	 * @param anotherVec3F
	 * - Another Vector 3D to examine
	 * 
	 * @return
	 * {@code true} if values are equal; {@code false} otherwise
	 */
	public boolean equals(final Vec3 anotherVec3F) {
		return x == anotherVec3F.x && y == anotherVec3F.y && z == anotherVec3F.z;
	}
	
	/**
	 * @return
	 * {@code true} if values are zero; {@code false} otherwise
	 */
	public boolean isZero() {
		return x == 0.0 && y == 0.0 && z == 0.0;
	}
	
	/**
	 * Sets all value to {@code zero}
	 */
	public void toZero() {
		x = 0.0F; y = 0.0F; z = 0.0F;
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
	
	/**
	 * @return
	 * {@code z} value
	 */
	public float getZ() {
		return z;
	}
	
	/**
	 * Sets new {@code z} value
	 * 
	 * @param z
	 * - new Value for {@code z}
	 */
	public void setZ(float z) {
		this.z = z;
	}
}
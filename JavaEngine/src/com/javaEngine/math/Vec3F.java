package com.javaEngine.math;

/**
 * 
 */
public class Vec3F {
	protected float x;
	protected float y;
	protected float z;
	
	// TODO: Remove this w component
	public float w = 1.0F;
	
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
	 * Sets {@code all the value} of this vector to {@code all the value} of the given vector
	 * 
	 * @param vector
	 * - {@code Vector} values
	 */
	public Vec3F(final Vec3F vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
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
	 * Performs {@code cross product} multiplication between this vector
	 * and {@code another vector specified}
	 * 
	 * @param anotherVec3F
	 * - {@code Another vector} to multiply
	 * 
	 * @return
	 * {@code result or output} of the operation
	 */
	public Vec3F crossProduct(final Vec3F anotherVec3F) {
		// Vector to return
		final Vec3F outputVector = new Vec3F();
		
		// Perform cross product multiplication
		outputVector.x = y * anotherVec3F.z - z * anotherVec3F.y;
		outputVector.y = z * anotherVec3F.x - x * anotherVec3F.z;
		outputVector.z = x * anotherVec3F.y - y * anotherVec3F.x;
		
		return outputVector;
	}
	
	/**
	 * Perform {@code dot product} multiplication between this vector
	 * and {@code another vector specified}
	 * 
	 * @param anotherVec3F
	 * - {@code Another vector} to multiply
	 * 
	 * @return
	 * {@code result of output} of the operation
	 */
	public float dotProduct(final Vec3F anotherVec3F) {
		// Value to return
		float outputValue = 0.0F;
		
		// Perform dot product multiplication
		outputValue = x * anotherVec3F.x;
		outputValue += y * anotherVec3F.y;
		outputValue += z * anotherVec3F.z;
		
		return outputValue;
	}
	
	/**
	 * Adds {@code every value} of this vector to all the value of the {@code given vector}
	 *  
	 * @param anotherVec3F
	 * - {@code vector} to add 
	 * 
	 * @return
	 * {@code Added} Vec3F
	 * 
	 * @see #add(float)
	 */
	public Vec3F add(final Vec3F anotherVec3F) {
		return new Vec3F(anotherVec3F.x + x, anotherVec3F.y + y, anotherVec3F.z + z);
	}
	
	/**
	 * Adds {@code every value} of this vector to the value given
	 *  
	 * @param value
	 * - {@code value} to add
	 * 
	 * @return
	 * {@code Added} value
	 * 
	 * @see #add(Vec3F)
	 */
	public Vec3F add(final float value) {
		return add(new Vec3F(value));
	}
	
	/**
	 * Subtracts {@code every value} of this vector to all the value of the {@code given vector}
	 *  
	 * @param anotherVec3F
	 * - {@code vector} to subtract 
	 * 
	 * @return
	 * {@code Subtracted} Vec3F
	 */
	public Vec3F subtract(final Vec3F anotherVec3F) {
		return new Vec3F(x - anotherVec3F.x, y - anotherVec3F.y, z - anotherVec3F.z);
	}
	
	/**
	 * Multiplies {@code every value} of this vector to the scalar given
	 * 
	 * @param scalar
	 * - {@code Scalar} value to multiply
	 * 
	 * @return
	 * {@code Multiplied} Vector3F
	 * 
	 * @see #multiply(Vec3F)
	 */
	public Vec3F multiply(final float scalar) {
		return new Vec3F(x * scalar, y * scalar, z * scalar);
	}
	
	/**
	 * Multiplies {@code every value} of this vector to every value of the {@code vector} given
	 * 
	 * @param anotherVec3
	 * - {@code Vector3F} value to multiply
	 * 
	 * @return
	 * {@code Multiplied} Vector3F
	 * 
	 * @see #multiply(float)
	 */
	public Vec3F multiply(final Vec3F anotherVec3) {
		return new Vec3F(x * anotherVec3.x, y * anotherVec3.y, z * anotherVec3.z);
	}
	
	/**
	 * Divides {@code every value} of this vector to every value of the {@code vector} given
	 * 
	 * @param anotherVec3F
	 * - Another {@code vector} to divide
	 * 
	 * @return
	 * {@code Output} vector
	 * 
	 * @see #divide(float)
	 */
	public Vec3F divide(final Vec3F anotherVec3F) {
		return new Vec3F(x / anotherVec3F.x,
						 y / anotherVec3F.y,
						 z / anotherVec3F.z);
	}
	
	/**
	 * Divides {@code every value} of this vector to the {@code value} given
	 * 
	 * @param value
	 * - {@code value} to divide
	 * 
	 * @return
	 * {@code Output} vector
	 */
	public Vec3F divide(final float value) {
		return divide(new Vec3F(value));
	}
	
	/**
	 * @return
	 * {@code Half} of every value of this {@code vector}
	 */
	public Vec3F half() {
		return new Vec3F( (int)x >> 1, (int)y >> 1, (int)z >> 1 );
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
		matrix.set(0, 1, y);
		matrix.set(0, 2, z);
		matrix.set(0, 3, 1); // w
		
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
	 * @return
	 * {@code Vector 2D float} that only contains {@code x} and {@code y} value, removing {@code z}
	 */
	public Vec2F toVector2DFloat() {
		return new Vec2F(x, y);
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
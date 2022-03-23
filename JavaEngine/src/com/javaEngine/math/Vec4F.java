package com.javaEngine.math;

public class Vec4F {
	protected float x;
	protected float y;
	protected float z;
	protected float w;
	
	/**
	 * @param x
	 * - {@code X} value
	 * 
	 * @param y
	 * - {@code Y} value
	 * 
	 * @param z
	 * - {@code Z} value
	 * 
	 * @param w
	 * - {@code W} value
	 */
	public Vec4F(final float x, final float y, final float z, final float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * Sets {@code all} axis to the given {@code value}
	 * 
	 * @param value
	 * - {@code Value} for all the axis
	 * 
	 * @see #Vec4F(float, float, float, float)
	 */
	public Vec4F(final float value) {
		this(value, value, value, value);
	}
	
	/**
	 * Sets {@code all} axis to {@code zero}
	 * 
	 * @see #Vec4F(float)
	 * @see #Vec4F(float, float, float, float)
	 */
	public Vec4F() {
		this(0.0F);
	}
	
	/**
	 * Sets values from {@code anotherVec4F}
	 * 
	 * @param anotherVec4F
	 * - {@code Vector} that contains values
	 */
	public Vec4F(final Vec4F anotherVec4F) {
		x = anotherVec4F.x;
		y = anotherVec4F.y;
		z = anotherVec4F.z;
		w = anotherVec4F.w;
	}
	
	/**
	 * @return
	 * the {@code length} or {@code magnitude} of this 4D vector,
	 * this is achieved through Pythagoras theorem
	 */
	public final float length() {
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	/**
	 * Normalizes this {@code vector 4D Float}
	 * 
	 * @return
	 * Normalized vector
	 */
	public final Vec4F normalize() {
		// Hypoteneuse_
		final float length = length();
		
		// Vector 2D to return
		final Vec4F newVec4F = new Vec4F();
		
		// Prevents division by zero
		if(length == 0.0) return newVec4F;
		
		// Normalize
		newVec4F.x = x / length;
		newVec4F.y = y / length;
		newVec4F.z = z / length;
		newVec4F.w = w / length;
		
		return newVec4F;
	}
	
	/**
	 * Perform {@code dot product} multiplication between this vector
	 * and {@code another vector specified}
	 * 
	 * @param anotherVec4F
	 * - {@code Another vector} to multiply
	 * 
	 * @return
	 * {@code result of output} of the operation
	 */
	public float dotProduct(final Vec4F anotherVec4F) {
		// Value to return
		float outputValue = 0.0F;
		
		// Perform dot product multiplication
		outputValue =  x * anotherVec4F.x;
		outputValue += y * anotherVec4F.y;
		outputValue += z * anotherVec4F.z;
		outputValue += w * anotherVec4F.w;
		
		return outputValue;
	}
	
	/**
	 * Adds {@code every value} of this vector to all the value of the {@code given vector}
	 *  
	 * @param anotherVec4F
	 * - {@code vector} to add 
	 * 
	 * @return
	 * {@code Added} Vec4F
	 * 
	 * @see #add(float)
	 */
	public Vec4F add(final Vec4F anotherVec4F) {
		return new Vec4F(anotherVec4F.x + x, anotherVec4F.y + y, anotherVec4F.z + z, anotherVec4F.w + w);
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
	public Vec4F add(final float value) {
		return add(new Vec4F(value));
	}
	
	/**
	 * Subtracts {@code every value} of this vector to all the value of the {@code given vector}
	 *  
	 * @param anotherVec4F
	 * - {@code vector} to subtract 
	 * 
	 * @return
	 * {@code Subtracted} Vec4F
	 */
	public Vec4F subtract(final Vec4F anotherVec4F) {
		return new Vec4F(x - anotherVec4F.x, y - anotherVec4F.y, z - anotherVec4F.z, w - anotherVec4F.w);
	}
	
	/**
	 * Multiplies {@code every value} of this vector to the scalar given
	 * 
	 * @param scalar
	 * - {@code Scalar} value to multiply
	 * 
	 * @return
	 * {@code Multiplied} Vector4F
	 * 
	 * @see #multiply(Vec4F)
	 */
	public Vec4F multiply(final float scalar) {
		return new Vec4F(x * scalar, y * scalar, z * scalar, w * scalar);
	}
	
	/**
	 * Multiplies {@code every value} of this vector to every value of the {@code vector} given
	 * 
	 * @param anotherVec4
	 * - {@code Vector3F} value to multiply
	 * 
	 * @return
	 * {@code Multiplied} Vector4F
	 * 
	 * @see #multiply(float)
	 */
	public Vec4F multiply(final Vec4F anotherVec4) {
		return new Vec4F(x * anotherVec4.x, y * anotherVec4.y, z * anotherVec4.z, w * anotherVec4.w);
	}
	
	/**
	 * Divides {@code every value} of this vector to every value of the {@code vector} given
	 * 
	 * @param anotherVec4F
	 * - Another {@code vector} to divide
	 * 
	 * @return
	 * {@code Output} vector
	 * 
	 * @see #divide(float)
	 */
	public Vec4F divide(final Vec4F anotherVec4F) {
		return new Vec4F(x / anotherVec4F.x,
						 y / anotherVec4F.y,
						 z / anotherVec4F.z,
						 w / anotherVec4F.w);
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
	public Vec4F divide(final float value) {
		return divide(new Vec4F(value));
	}
	
	/**
	 * @return
	 * {@code Half} of every value of this {@code vector}
	 */
	public Vec4F half() {
		return new Vec4F( (int)x >> 1, (int)y >> 1, (int)z >> 1, (int)w >> 1 );
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
	 * Sets this' {@code x}, {@code y}, {@code z} and {@code w} value
	 * 
	 * @param newValue
	 * - {@code Vector 4D Float} that contains the new value
	 * 
	 * @return
	 * {@code this} vector
	 */
	public Vec4F set(final Vec4F newValue) {
		x = newValue.x;
		y = newValue.y;
		z = newValue.z;
		w = newValue.w;
		
		return this;
	}
	
	@Override
	public Vec4F clone() {
		return new Vec4F(x, y, z, w);
	}
	
	
	@Override
	public String toString() {
		return "[x  : " + x + ", y : " + y + ", z  : " + z + ", w : " + w + "]";
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
		matrix.set(3, 3, w);
		
		return matrix;
	}
	
	/**
	 * @return
	 * {@code Vector 3D Float} with this' {@code x}, {@code y} and {@code z} value divided by {@code w}
	 */
	public Vec3F toVector3DFloatDivided() {
		return new Vec3F(x / w, y / w, z / w);
	}
	
	/**
	 * @return
	 * {@code Vector 3D} with this' {@code x}, {@code y} and {@code z} value divided by {@code w}
	 */
	public Vec3 toVector3DDivided() {
		return new Vec3((int)(x / w), (int)(y / w), (int)(z / w));
	}
	
	/**
	 * @return
	 * {@code Vector 3D Float} with this' {@code x}, {@code y} and {@code z} value... removing the {@code w} component
	 */
	public Vec3F toVector3DFloat() {
		return new Vec3F(x, y, z);
	}
	
	/**
	 * @return
	 * {@code Vector 3D} with this' {@code x}, {@code y} and {@code z} value
	 */
	public Vec3 toVector3DRaw() {
		return new Vec3((int)x, (int)y, (int)z);
	}
	
	/**
	 * Returns whether {@code this} and {@code another Vector 4D Float} has the same {@code x}, {@code y}, {@code z} and {@code w} value
	 * 
	 * @param anotherVec4F
	 * - Another Vector 4D to examine
	 * 
	 * @return
	 * {@code true} if values are equal; {@code false} otherwise
	 */
	public boolean equals(final Vec4F anotherVec4F) {
		return x == anotherVec4F.x && y == anotherVec4F.y && z == anotherVec4F.z && w == anotherVec4F.w;
	}
	
	/**
	 * @return
	 * {@code true} if values are zero; {@code false} otherwise
	 */
	public boolean isZero() {
		return x == 0.0F && y == 0.0F && z == 0.0F && w == 0.0F;
	}
	
	/**
	 * Sets all value to {@code zero}
	 */
	public void toZero() {
		x = 0.0F; y = 0.0F; z = 0.0F; w = 0.0F;
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
	
	/**
	 * @return
	 * {@code w} value
	 */
	public float getW() {
		return w;
	}
	
	/**
	 * Sets new {@code w} value
	 * 
	 * @param w
	 * - new Value for {@code w}
	 */
	public void setW(float w) {
		this.w = w;
	}
}
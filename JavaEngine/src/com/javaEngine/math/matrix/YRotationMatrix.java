package com.javaEngine.math.matrix;

import com.javaEngine.math.MatrixF;

/**
 * Rotates {@code 3D points} - y {@code axis}
 */
public final class YRotationMatrix extends MatrixF {
	private float theta;
	
	/**
	 * @param theta
	 * - Angle of {@code y - rotation}
	 */
	public YRotationMatrix(final float theta) {
		// 4D matrix
		super(4);
		
		this.theta = theta;
		setTheta(theta);
	}
	
	/**
	 * @see #XRotationMatrix(float)
	 */
	public YRotationMatrix() {
		this(0.0F);
	}
	
	// ***********************************************************
	// Getters
	// ***********************************************************
	
	/**
	 * @return
	 * Angle of {@code y - rotation}
	 */
	public float getTheta() {
		return theta;
	}
	
	// ***********************************************************
	// Getters
	// ***********************************************************
	
	/**
	 * @param newTheta
	 * - new {@code theta} value
	 */
	public void setTheta(final float newTheta) {
		theta = newTheta;
		values[0][0] = (float) Math.cos(theta);
		values[0][2] = (float) Math.sin(theta);
		
		values[1][1] = 1.0F;
		
		values[2][0] = -(float) Math.sin(theta);
		values[2][2] = (float) Math.cos(theta);
		
		values[3][3] = 1.0F;
	}
}
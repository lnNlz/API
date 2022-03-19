package com.javaEngine.math.matrix;

import com.javaEngine.math.MatrixF;

/**
 * Rotates {@code 3D points} - z {@code axis}
 */
public final class ZRotationMatrix extends MatrixF {
	private float theta;
	
	/**
	 * @param theta
	 * - Angle of {@code z - rotation}
	 */
	public ZRotationMatrix(final float theta) {
		// 4D matrix
		super(4);
		
		this.theta = theta;
		setTheta(theta);
	}
	
	/**
	 * @see #XRotationMatrix(float)
	 */
	public ZRotationMatrix() {
		this(0.0F);
	}
	
	// ***********************************************************
	// Getters
	// ***********************************************************
	
	/**
	 * @return
	 * Angle of {@code z - rotation}
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
		values[0][1] = (float) Math.sin(theta);
		
		values[1][0] = -(float) Math.sin(theta);
		values[1][1] = (float) Math.cos(theta);
		
		values[2][2] = 1.0F;
		
		values[3][3] = 1.0F;
	}
}
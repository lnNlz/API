package com.javaEngine.math.matrix;

import com.javaEngine.math.MatrixF;

/**
 * Rotates {@code 3D points} - x {@code axis}
 */
public final class XRotationMatrix extends MatrixF {
	private float theta;
	
	/**
	 * @param theta
	 * - Angle of {@code x - rotation}
	 */
	public XRotationMatrix(final float theta) {
		// 4D matrix
		super(4);
		
		this.theta = theta;
		setTheta(theta);
	}
	
	/**
	 * @see #XRotationMatrix(float)
	 */
	public XRotationMatrix() {
		this(0.0F);
	}
	
	// ***********************************************************
	// Getters
	// ***********************************************************
	
	/**
	 * @return
	 * Angle of {@code x - rotation}
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
		values[0][0] = 1.0F;
		
		values[1][1] = (float) Math.cos(theta);
		values[1][2] = (float) Math.sin(newTheta);
		
		values[2][1] = -(float) Math.sin(newTheta);
		values[2][2] = (float) Math.cos(newTheta);
		
		values[3][3] = 1.0F;
	}
}
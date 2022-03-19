package com.javaEngine.math.matrix;

import com.javaEngine.JavaEngine;
import com.javaEngine.math.MatrixF;

/**
 * {@code A Matrix 4D} that's main purpose is to transform {@code 3D coordinates} to {@code 2D screen coordinates}
 * 
 * @since 1.0
 * @version 1.0
 */
public final class ProjectionMatrix extends MatrixF {
	// Constants
	public static final float DEFAULT_FIELD_OF_VIEW = 90.0F;
	public static final float DEFAULT_NEAR_PLANE = 0.1F;
	public static final float DEFAULT_FAR_PLANE = 1000.0F;
	
	public final float FOV;
	public final float NEAR_PLANE;
	public final float FAR_PLANE;
	
	/**
	 * @param FOV
	 * - User {@code field of view}
	 * 
	 * @param nearPlane
	 * - Location of the {@code near plane}
	 * 
	 * @param farPlane
	 * - Location of the {@code far plane}
	 */
	public ProjectionMatrix(final float FOV, final float nearPlane, final float farPlane) {
		// 4D matrix
		super(4);
		
		this.FOV = FOV;
		this.NEAR_PLANE = nearPlane;
		this.FAR_PLANE = farPlane;
		
		setFieldOfView(FOV);
		setPlaneLocations(nearPlane, farPlane);
		
		values[2][3] = 1.0F;
	}
	
	/**
	 * @param FOV
	 * - User {@code field of view}
	 * 
	 * @see #ProjectionMatrix(float, float, float)
	 */
	public ProjectionMatrix(final float FOV) {
		this(FOV, DEFAULT_NEAR_PLANE, DEFAULT_FAR_PLANE);
	}
	
	/**
	 * @see #ProjectionMatrix(float)
	 * @see #ProjectionMatrix(float, float, float)
	 */
	public ProjectionMatrix() {
		this(DEFAULT_FIELD_OF_VIEW);
	}
	
	// ******************************************************
	// Getters
	// ******************************************************
	
	/**
	 * @return
	 * Matrix's {@code field of view}
	 */
	public float getFieldOfView() {
		return FOV;
	}
	
	/**
	 * @return
	 * Matrix's {@code near plane}
	 */
	public float getNearPlane() {
		return NEAR_PLANE;
	}
	
	/**
	 * @return
	 * Matrix's {@code far plane}
	 */
	public float getFarPlane() {
		return FAR_PLANE;
	}
	
	// ******************************************************
	// Setters
	// ******************************************************
	
	/**
	 * @param newFOV
	 * - New {@code field of view} value
	 */
	public void setFieldOfView(final float newFOV) {
		final float screenAspectRatio = (float)JavaEngine.get().getHeight() / (float)JavaEngine.get().getWidth();
		final float inverseTangent = 1 / (float)Math.tan(FOV * 0.5F / 180 * 3.14159F);
		
		values[0][0] = screenAspectRatio * inverseTangent;
		values[1][1] = inverseTangent;
	}
	
	/**
	 * @param nearPlane
	 * - New {@code near plane} location
	 * 
	 * @param farPlane
	 * - New {@code far plane} location
	 */
	public void setPlaneLocations(final float nearPlane, final float farPlane) {
		values[2][2] = FAR_PLANE / (FAR_PLANE - NEAR_PLANE);
		values[3][2] = (FAR_PLANE * NEAR_PLANE) / (FAR_PLANE - NEAR_PLANE);
	}
}
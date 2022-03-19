package com.javaEngine;

import com.javaEngine.math.matrix.ProjectionMatrix;

public final class Camera {
	private static final Camera camera = new Camera();
	
	public static float FOV = ProjectionMatrix.DEFAULT_FIELD_OF_VIEW;
	
	/**
	 * @return
	 * - A camera class {@code instance}
	 */
	public static Camera get() {
		return camera;
	}
}
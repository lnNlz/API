package com.javaEngine.math.matrix;

import com.javaEngine.math.MatrixF;
import com.javaEngine.math.Vec3F;

public final class PointAtMatrix3D extends MatrixF {
	public PointAtMatrix3D(final Vec3F originalPosition, final Vec3F destination, final Vec3F upDirection) {
		// 4x4 matrix
		super(4);
		
		// New Forward vector calculation
		final Vec3F newForwardVector = destination.subtract(originalPosition).normalize();
		
		// New Up vector calculation
		// TODO: Clean this again if this thing don't works
		final Vec3F newUpVector = upDirection.subtract(newForwardVector.multiply(upDirection.dotProduct(newForwardVector))).normalize();
		
		// New Right vector calculation
		final Vec3F newRightVector = newUpVector.crossProduct(newForwardVector);
		
		// Set all the value
		values[0][0] = newRightVector.getX();
		values[0][1] = newRightVector.getY();
		values[0][2] = newRightVector.getZ();
		values[0][3] = 0.0F;
		
		values[1][0] = newUpVector.getX();
		values[1][1] = newUpVector.getY();
		values[1][2] = newUpVector.getZ();
		values[1][3] = 0.0F;
		
		values[2][0] = newForwardVector.getX();
		values[2][1] = newForwardVector.getY();
		values[2][2] = newForwardVector.getZ();
		values[2][3] = 0.0F;
		
		values[3][0] = originalPosition.getX();
		values[3][1] = originalPosition.getY();
		values[3][2] = originalPosition.getZ();
		values[3][3] = 1.0F;
	}
	
	/**
	 * @return
	 * {@code Inverse} of this matrix
	 */
	public MatrixF inverse() {
		return inverse(this);
	}
}
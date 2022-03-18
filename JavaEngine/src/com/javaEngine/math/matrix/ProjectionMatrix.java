package com.javaEngine.math.matrix;

import com.javaEngine.JavaEngine;
import com.javaEngine.math.MatrixF;
import com.javaEngine.math.Vec3F;

// TODO: Document this
public final class ProjectionMatrix extends MatrixF {
	public final float FOV;
	public final float NEAR_PLANE;
	public final float FAR_PLANE;
	
	public ProjectionMatrix(final float FOV, final float nearPlane, final float farPlane) {
		// 4D matrix
		super(4);
		
		this.FOV = FOV;
		this.NEAR_PLANE = nearPlane;
		this.FAR_PLANE = farPlane;
		
		set();
	}
	
	public void set() {
		final float screenAspectRatio = (float)JavaEngine.get().getHeight() / (float)JavaEngine.get().getWidth();
		final float inverseTangent = 1 / (float)Math.tan(FOV * 0.5F / 180 * 3.14159F);
		
		values[0][0] = screenAspectRatio * inverseTangent;
		values[1][1] = inverseTangent;
		values[2][2] = FAR_PLANE / (FAR_PLANE - NEAR_PLANE);
		values[2][3] = 1.0F;
		values[3][2] = (FAR_PLANE * NEAR_PLANE) / (FAR_PLANE - NEAR_PLANE);
	}
	
	public ProjectionMatrix(final float FOV) {
		this(FOV, 0.1F, 1000.0F);
	}
	
	public ProjectionMatrix() {
		this(90.0F);
	}
	
	public Vec3F multiply(final Vec3F pos) {
		final Vec3F output = new Vec3F();
		
		output.setX(
					pos.getX() * values[0][0] +
					pos.getY() * values[1][0] +
					pos.getZ() * values[2][0] +
					values[3][0]
				);
		
		output.setY(
					pos.getX() * values[0][1] +
					pos.getY() * values[1][1] +
					pos.getZ() * values[2][1] +
					values[3][1]
				);
		
		output.setZ(
					pos.getX() * values[0][2] +
					pos.getY() * values[1][2] +
					pos.getZ() * values[2][2] +
					values[3][2]
				);
		
		final float w = 
				pos.getX() * values[0][3] +
				pos.getY() * values[1][3] +
				pos.getZ() * values[2][3] +
				values[3][3];
		
		// Normalize
		if(w != 0.0F) {
			output.setX(output.getX() / w);
			output.setY(output.getY() / w);
			output.setZ(output.getZ() / w);
		}
		
		return output;
	}
}
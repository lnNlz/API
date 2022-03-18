package com.javaEngine.math;

/**
 *
 */
public final class Mathf {
	private Mathf() {}
	
	/**
	 * Checks whether {@code value} is {@code greater than} maximum value or
	 * {@code less than} minimum value
	 * 
	 * @param minimumValue
	 * - Minimum value for {@code value} given
	 * 
	 * @param maximumValue
	 * - Maximum value for {@code value} given
	 * 
	 * @param value
	 * - Value to {@code examine}
	 * 
	 * @return
	 * {@code value} if it is somewhere in the middle
	 */
	public static int lock(final int minimumValue, final int maximumValue, final int value) {
		return value > maximumValue ? maximumValue : (value < minimumValue ? minimumValue : value);
	}
	
	/**
	 * Checks whether {@code value} is {@code greater than} maximum value or
	 * {@code less than} minimum value
	 * 
	 * @param minimumValue
	 * - Minimum value for {@code value} given
	 * 
	 * @param maximumValue
	 * - Maximum value for {@code value} given
	 * 
	 * @param value
	 * - Value to {@code examine}
	 * 
	 * @return
	 * {@code value} if it is somewhere in the middle
	 */
	public static float lock(final float minimumValue, final float maximumValue, final float value) {
		return value > maximumValue ? maximumValue : (value < minimumValue ? minimumValue : value);
	}
	
	/**
	 * Checks whether {@code value} is {@code greater than} maximum value or
	 * {@code less than} minimum value
	 * 
	 * @param minimumValue
	 * - Minimum value for {@code value} given
	 * 
	 * @param maximumValue
	 * - Maximum value for {@code value} given
	 * 
	 * @param value
	 * - Value to {@code examine}
	 * 
	 * @return
	 * {@code value} if it is somewhere in the middle
	 */
	public static double lock(final double minimumValue, final double maximumValue, final double value) {
		return value > maximumValue ? maximumValue : (value < minimumValue ? minimumValue : value);
	}
	
	// TEST
	// TODO: Remove this
	public static Vec3F multiply(final Vec3F input, final MatrixF matrix) {
		if(matrix.size() != 4) return null;
		
		final Vec3F output = new Vec3F();
		
		output.setX(
					input.getX() * matrix.get(0, 0) +
					input.getY() * matrix.get(1, 0) +
					input.getZ() * matrix.get(2, 0) +
					matrix.get(3, 0)
				);
		
		output.setY(
				input.getX() * matrix.get(0, 1) +
				input.getY() * matrix.get(1, 1) +
				input.getZ() * matrix.get(2, 1) +
				matrix.get(3, 1)
				);
		
		output.setZ(
				input.getX() * matrix.get(0, 2) +
				input.getY() * matrix.get(1, 2) +
				input.getZ() * matrix.get(2, 2) +
				matrix.get(3, 2)
				);
		
		final float w = 
				input.getX() * matrix.get(0, 3) +
				input.getY() * matrix.get(1, 3) +
				input.getZ() * matrix.get(2, 3) +
				matrix.get(3, 3);
		
		// Normalize
		if(w != 0.0F) {
			output.setX(output.getX() / w);
			output.setY(output.getY() / w);
			output.setZ(output.getZ() / w);
		}
		
		return output;
	}
}
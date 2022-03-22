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
	
	/**
	 * Perform {@code matrix multiplication} between two matrices
	 * 
	 * @param matrix1
	 * - {@code Matrix} to multiply
	 * 
	 * @param matrix2
	 * - {@code Matrix} to multiply
	 * 
	 * @return
	 * {@code Output} if operation is successful; {@code null} if not
	 */
	public static Matrix multiplyMatrix(final Matrix matrix1, final Matrix matrix2) {
		// Invalid size
		if(matrix1.size() != matrix2.size()) return null;
		
		// Size of the matrix
		final int size = matrix1.size();
		
		// Matrix to return
		final Matrix outputMatrix = new Matrix(size);
		
		// Perform Matrix multiplication
		for(int i = 0; i < size; i++)   
			for(int j = 0; j < size; j++)
				for(int k = 0; k < size; k++)  
					outputMatrix.set(i, j, outputMatrix.get(i, j) + matrix1.get(i, k) * matrix2.get(k, j));
		
		return outputMatrix;
	}
}
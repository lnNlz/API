package com.javaEngine.math;

/**
 * An {@code N by N} matrix with Row - Column order
 * 
 * <br>
 * This {@code Matrix} holds {@code N^2} floats
 */
public class MatrixF {
	protected final float[][] values;
	
	/**
	 * Creates an {@code N by N} matrix	 
	 * 
	 * @param size
	 * - Size of the {@code matrix}
	 */
	public MatrixF(final int size) {
		values = new float[size][size];
	}
	
	/**
	 * Creates a {@code 1 by 1} matrix
	 * 
	 * @see #MatrixF(int)
	 */
	public MatrixF() {
		this(1);
	}
	
	/**
	 * @return
	 * {@code size} of this matrix
	 */
	public final int size() {
		return values.length;
	}
	
	/**
	 * @param row
	 * - Row of the {@code value}
	 * 
	 * @param column
	 * - Column of the {@code value}
	 * 
	 * @return
	 *  - {@code value} at the specified location
	 */
	public float get(final int row, final int column) {
		return values[row][column];
	}
	
	/**
	 * Checks whether {@code row} or {@code column} is a valid index
	 * 
	 * @param row
	 * - Row of the {@code value}
	 * 
	 * @param column
	 * - Column of the {@code value}
	 * 
	 * @return
	 * - {@code value} at the specified location
	 */
	public float checkedGet(final int row, final int column) {
		return values[Mathf.lock(0, values.length, row)][Mathf.lock(0, values.length, column)];
	}
	
	/**
	 * Sets a {@code new value} at a specific location
	 * 
	 * @param row
	 * - Row of the {@code value}
	 * 
	 * @param column
	 * - Column of the {@code value}
	 * 
	 * @param value
	 * - {@code new value}
	 */
	public void set(final int row, final int column, final float value) {
		values[row][column] = value;
	}
	
	/**
	 * Sets a {@code new value} at a specific location;
	 * Checks whether {@code row} or {@code column} is a valid index
	 * 
	 * @param row
	 * - Row of the {@code value}
	 * 
	 * @param column
	 * - Column of the {@code value}
	 * 
	 * @param value
	 * - {@code new value}
	 */
	public void checkedSet(final int row, final int column, final float value) {
		values[Mathf.lock(0, values.length, row)][Mathf.lock(0, values.length, column)] = value;
	}
	
	/**
	 * Adds two {@code matrices} together
	 * 
	 * @param mat
	 * - {@code Matrix} to add
	 * 
	 * @return
	 * - {@code Output Matrix} if operation is successful and both {@code matrices} has the same dimension; {@code null} otherwise
	 */
	public MatrixF add(final MatrixF mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final MatrixF outputMatrix = new MatrixF(size);
		
		for(int row = 0; row < size; row++)
			for(int column = 0; column < size; column++)
				outputMatrix.values[row][column] = mat.values[row][column] + values[row][column];
		
		return outputMatrix;
	}
	
	/**
	 * Subtracts two {@code matrices} together
	 * 
	 * @param mat
	 * - {@code Matrix} to subtract
	 * 
	 * @return
	 * - {@code Output Matrix} if operation is successful and both {@code matrices} has the same dimension; {@code null} otherwise
	 */
	public MatrixF subtract(final MatrixF mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final MatrixF outputMatrix = new MatrixF(size);
		
		for(int row = 0; row < size; row++)
			for(int column = 0; column < size; column++)
				outputMatrix.values[row][column] = mat.values[row][column] - values[row][column];
		
		return outputMatrix;
	}
	
	/**
	 * Multiplies two {@code matrices} together
	 * 
	 * @param mat
	 * - {@code Matrix} to multiply
	 * 
	 * @return
	 * - {@code Output Matrix} if operation is successful and both {@code matrices} has the same dimension; {@code null} otherwise
	 */
	@Deprecated
	public MatrixF multiply(final MatrixF mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final MatrixF outputMatrix = new MatrixF(size);
		
		for(int row = 0; row < size; row++)
			for(int column = 0; column < size; column++)
				outputMatrix.values[row][column] += mat.values[row][column] * values[row][column];
		
		return outputMatrix;
	}
}
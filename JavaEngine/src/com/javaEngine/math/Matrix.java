package com.javaEngine.math;

/**
 * An {@code N by N} matrix with Row - Column order
 * 
 * <br>
 * This {@code Matrix} holds {@code N^2} integers
 */
public class Matrix {
	protected final int[][] values;
	
	/**
	 * Creates an {@code N by N} matrix	 
	 * 
	 * @param size
	 * - Size of the {@code matrix}
	 */
	public Matrix(final int size) {
		values = new int[size][size];
	}
	
	/**
	 * Creates a {@code 1 by 1} matrix
	 * 
	 * @see #Matrix(int)
	 */
	public Matrix() {
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
	public int get(final int row, final int column) {
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
	public int checkedGet(final int row, final int column) {
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
	public void set(final int row, final int column, final int value) {
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
	public void checkedSet(final int row, final int column, final int value) {
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
	public Matrix add(final Matrix mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final Matrix outputMatrix = new Matrix(size);
		
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
	public Matrix subtract(final Matrix mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final Matrix outputMatrix = new Matrix(size);
		
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
	public Matrix multiply(final Matrix mat) {
		final int size = size();
		
		// Invalid size
		if(mat.size() != size) return null;
		
		final Matrix outputMatrix = new Matrix(size);
		
		for(int row = 0; row < size; row++)
			for(int column = 0; column < size; column++)
				outputMatrix.values[row][column] += mat.values[row][column] * values[row][column];
		
		return outputMatrix;
	}
}
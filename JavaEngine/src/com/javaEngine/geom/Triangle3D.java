package com.javaEngine.geom;

import java.awt.Graphics2D;
import java.awt.Polygon;

import com.javaEngine.math.Vec3F;

public class Triangle3D {
	public final Vec3F pointA;
	public final Vec3F pointB;
	public final Vec3F pointC;
	
	/**
	 * @param pointA
	 * - {@code Point A}
	 * 
	 * @param pointB
	 * - {@code Point B}
	 * 
	 * @param pointC
	 * - {@code Point C}
	 */
	public Triangle3D(final Vec3F pointA, final Vec3F pointB, final Vec3F pointC) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}
	
	/**
	 * Draws the {@code triangle} by drawing its {@code 3 sides}
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void drawLine(final Graphics2D g) {
		g.drawLine((int)pointA.getX(), (int)pointA.getY(), 
				   (int)pointB.getX(), (int)pointB.getY());
		
		g.drawLine((int)pointB.getX(), (int)pointB.getY(), 
				   (int)pointC.getX(), (int)pointC.getY());
		
		g.drawLine((int)pointC.getX(), (int)pointC.getY(), 
				   (int)pointA.getX(), (int)pointA.getY());
	}
	
	/**
	 * Draws the {@code triangle} by drawing its {@code 3 sides}
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void fill(final Graphics2D g) {
		g.fill(new Polygon(
					new int[] { (int)pointA.getX(), (int)pointB.getX(), (int)pointC.getX() },
					new int[] { (int)pointA.getY(), (int)pointB.getY(), (int)pointC.getY() },
					3
				));
	}
	
	/**
	 * Adds the vector given to all the points
	 * 
	 * @param toAdd
	 * - {@code Vector 3D float} to add
	 */
	public void add(final Vec3F toAdd) {
		pointA.set(pointA.add(toAdd));
		pointB.set(pointB.add(toAdd));
		pointC.set(pointC.add(toAdd));
	}
	
	/**
	 * Multiplies the vector given to all the points
	 * 
	 * @param toAdd
	 * - {@code Vector 3D float} to multiply
	 */
	public void multiply(final Vec3F toMultiply) {
		pointA.set(pointA.multiply(toMultiply));
		pointB.set(pointB.multiply(toMultiply));
		pointC.set(pointC.multiply(toMultiply));
	}
	
	@Override
	public Triangle3D clone() {
		return new Triangle3D(pointA, pointB, pointC);
	}
}
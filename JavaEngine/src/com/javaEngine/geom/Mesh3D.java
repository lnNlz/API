package com.javaEngine.geom;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.javaEngine.Camera;
import com.javaEngine.JavaEngine;
import com.javaEngine.math.Mathf;
import com.javaEngine.math.Vec3F;
import com.javaEngine.math.matrix.ProjectionMatrix;

public class Mesh3D {
	protected final Vec3F rotation;
	
	protected final ArrayList<Triangle3D> triangles;
	
	/**
	 * @param triangles
	 * - Array of Mesh's {@code triangles}
	 * 
	 * @param rotation
	 * - Mesh's {@code rotation}
	 */
	public Mesh3D(final Triangle3D[] triangles, final Vec3F rotation) {
		if(triangles.length == 0)
			throw new IllegalArgumentException("A Mesh must have at least 1 triangle!");
			
		this.triangles = new ArrayList<Triangle3D> ();
		
		// Add all the triangles
		for(final Triangle3D tri : triangles)
			this.triangles.add(tri);
		
		this.rotation = rotation;
	}
	
	// TODO: Optimize this code
	public void transformToScreen() {
		final ProjectionMatrix projectionMatrix = new ProjectionMatrix(Camera.FOV);
		
		for(final Triangle3D triangle : triangles) {
			// Transform to screen space
			final Triangle3D triangleToProject = new Triangle3D(
						Mathf.multiplyVecToMat(triangle.pointA, projectionMatrix),
						Mathf.multiplyVecToMat(triangle.pointB, projectionMatrix),
						Mathf.multiplyVecToMat(triangle.pointC, projectionMatrix)
					);
			
			// Scale triangle to aspect view
			final Vec3F scale = new Vec3F(1.0F, 1.0F, 0.0F);
			triangleToProject.add(scale);
			
			// Scale triangle to screen view
			final Vec3F offset = new Vec3F((float)(JavaEngine.get().getWidth() << 1), (float)(JavaEngine.get().getHeight() << 1), 0.0F);
			triangleToProject.multiply(offset);
		}
	}
	
	/**
	 * @param triangles
	 * - Array of Mesh's {@code triangles}
	 * 
	 * @see #Mesh3D(Triangle3D[], Vec3F)
	 */
	public Mesh3D(final Triangle3D[] triangles) {
		this(triangles, new Vec3F(0.0F, 0.0F, 0.0F));
	}
	
	/**
	 * Draws an outline of all the {@code triangles}
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void draw(final Graphics2D g) {
		triangles.forEach(tri -> tri.drawLine(g));
	}
	
	/**
	 * Fills {@code all the triangles} with a solid color
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void fill(final Graphics2D g) {
		triangles.forEach(tri -> tri.fill(g));
	}
	
	/**
	 * Creates a {@code 3 dimensional} array
	 * 
	 * @return
	 * - 3 Dimensional array
	 */
	public final float[][][] toArray() {
		// [] -> Triangles
		// [] -> Points
		// [] -> Coordinates
		final float[][][] array = new float[triangles.size()][3][3];
		
		// Set the values
		for(int i = 0; i < array.length; i++) {
			final Triangle3D tri = triangles.get(i);
			
			// Point A
			array[i][0][0] = tri.pointA.getX();
			array[i][0][1] = tri.pointA.getY();
			array[i][0][2] = tri.pointA.getZ();
			
			// Point B
			array[i][1][0] = tri.pointB.getX();
			array[i][1][1] = tri.pointB.getY();
			array[i][1][2] = tri.pointB.getZ();
			
			// Point C
			array[i][2][0] = tri.pointC.getX();
			array[i][2][1] = tri.pointC.getY();
			array[i][2][2] = tri.pointC.getZ();
		}
		
		return array;
	}
}
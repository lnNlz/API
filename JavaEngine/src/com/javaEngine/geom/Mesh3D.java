package com.javaEngine.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.javaEngine.Camera;
import com.javaEngine.JavaEngine;
import com.javaEngine.math.MatrixF;
import com.javaEngine.math.Vec3F;
import com.javaEngine.math.matrix.PointAtMatrix3D;
import com.javaEngine.math.matrix.ProjectionMatrix;
import com.javaEngine.math.matrix.XRotationMatrix;
import com.javaEngine.math.matrix.YRotationMatrix;
import com.javaEngine.math.matrix.ZRotationMatrix;

/**
 * A 3D Polygon that stores {@code multiple triangles} and handles them,
 * each Triangle contains 3 {@code Vector 3 Float} for points
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see Triangle3D
 * @see Cube
 * @see Rectangle3D
 */
public class Mesh3D {
	// Mesh position offset
	protected final Vec3F position;
	
	// Mesh rotation
	protected final Vec3F rotation;
	
	// Mesh scale
	protected final Vec3F scale;
	
	// Real triangle coordinates
	protected final ArrayList<Triangle3D> triangles;
	protected final ArrayList<Triangle3D> trianglesToProject;
	
	// Settings
	protected boolean applyLighting = true;
	
	// Lighting
	protected Color color;
	protected float brightness;
	
	/**
	 * @param triangles
	 * - Array of Mesh's {@code triangles}
	 * 
	 * @param rotation
	 * - Mesh's {@code rotation}
	 */
	public Mesh3D(final Triangle3D[] triangles, final Vec3F rotation) {
		this.triangles = new ArrayList<Triangle3D> ();
		this.trianglesToProject = new ArrayList<Triangle3D> ();
		
		if(triangles != null) {
			// Illegal length
			if(triangles.length == 0)
				throw new IllegalArgumentException("A Mesh must have at least 1 triangle!");
			
			// Add all the triangles
			for(final Triangle3D tri : triangles)
				this.triangles.add(tri);
		}
		
		this.rotation = rotation;
		
		// Default position offset
		this.position = new Vec3F(0.0F, 0.0F, 0.0F);
		
		// Default scale
		this.scale = new Vec3F(1.0F, 1.0F, 1.0F);
		assert(scale.length() > 0.0F) : "Invalid scale!";
		
		// Default color
		this.color = Color.WHITE;
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
	
	// TODO: Optimize this code
	public void toScreenCoordinates() {
		// Matrix initialization
		final ProjectionMatrix projectionMatrix = new ProjectionMatrix(Camera.FOV);
		final XRotationMatrix rotationMatrixX = new XRotationMatrix(Camera.get().rotation.getX());
		final YRotationMatrix rotationMatrixY = new YRotationMatrix(Camera.get().rotation.getY());
		final ZRotationMatrix rotationMatrixZ = new ZRotationMatrix(Camera.get().rotation.getZ());
		
//		final XRotationMatrix rotationMatrixX = new XRotationMatrix(rotation.getX());
//		final YRotationMatrix rotationMatrixY = new YRotationMatrix(rotation.getY());
//		final ZRotationMatrix rotationMatrixZ = new ZRotationMatrix(rotation.getZ());
		
		// Camera handling
		final Vec3F lookingDirection = multiplyVecToMatRaw(Camera.get().view, rotationMatrixY);
		final Vec3F targetView = Camera.get().position.add(lookingDirection);
		
		// View matrix
		final MatrixF viewMatrix = new PointAtMatrix3D(Camera.get().position, targetView, Camera.get().up).inverse();
		
		for(final Triangle3D triangle : triangles) {
			// Rotate
			final Triangle3D triangleRotateZ = new Triangle3D(
						multiplyVecToMatRaw(triangle.pointA, rotationMatrixZ),
						multiplyVecToMatRaw(triangle.pointB, rotationMatrixZ),
						multiplyVecToMatRaw(triangle.pointC, rotationMatrixZ)
					);
			
			final Triangle3D triangleRotateY = new Triangle3D(
					multiplyVecToMatRaw(triangleRotateZ.pointA, rotationMatrixY),
					multiplyVecToMatRaw(triangleRotateZ.pointB, rotationMatrixY),
					multiplyVecToMatRaw(triangleRotateZ.pointC, rotationMatrixY)
				);
			
			final Triangle3D triangleRotateX = new Triangle3D(
					multiplyVecToMatRaw(triangleRotateY.pointA, rotationMatrixX),
					multiplyVecToMatRaw(triangleRotateY.pointB, rotationMatrixX),
					multiplyVecToMatRaw(triangleRotateY.pointC, rotationMatrixX)
				);
			
			// Translate
			final Triangle3D translatedTriangle = triangleRotateX.clone();
			translatedTriangle.pointA.set( triangleRotateX.pointA.add(position) );
			translatedTriangle.pointB.set( triangleRotateX.pointB.add(position) );
			translatedTriangle.pointC.set( triangleRotateX.pointC.add(position) );
			
			// Getting the surface's normal
			final Vec3F delta1 = new Vec3F(
						translatedTriangle.pointB.getX() - translatedTriangle.pointA.getX(),
						translatedTriangle.pointB.getY() - translatedTriangle.pointA.getY(),
						translatedTriangle.pointB.getZ() - translatedTriangle.pointA.getZ()
					);
			
			final Vec3F delta2 = new Vec3F(
					translatedTriangle.pointC.getX() - translatedTriangle.pointA.getX(),
					translatedTriangle.pointC.getY() - translatedTriangle.pointA.getY(),
					translatedTriangle.pointC.getZ() - translatedTriangle.pointA.getZ()
				);
			
			// Normalize
			final Vec3F normalVector = new Vec3F( delta1.crossProduct(delta2) );
			normalVector.set( normalVector.normalize() );
				
			// Checks if triangle is visible
			if(normalVector.dotProduct(new Vec3F(translatedTriangle.pointA.subtract(Camera.get().position))) > 0.0F) continue;
			
			// Triangle to view
			final Triangle3D viewTriangle = new Triangle3D(
					multiplyVecToMatRaw(translatedTriangle.pointA, viewMatrix),
					multiplyVecToMatRaw(translatedTriangle.pointB, viewMatrix),
					multiplyVecToMatRaw(translatedTriangle.pointC, viewMatrix)
				);
			
			// Transform to screen space
			final Triangle3D triangleToProject = new Triangle3D(
						multiplyVecToMat(viewTriangle.pointA, projectionMatrix),
						multiplyVecToMat(viewTriangle.pointB, projectionMatrix),
						multiplyVecToMat(viewTriangle.pointC, projectionMatrix)
					);
			
			// Handle lighting
			if(applyLighting) {
				final Vec3F normalizedLight = Camera.get().direction.normalize();
				brightness = normalVector.dotProduct(normalizedLight);
				brightness = brightness < 0 ? 0 : brightness > 255 ? 255 : brightness;
				
				// TODO: Add custom color support
				triangleToProject.color = new Color(
						brightness,
						brightness,
						brightness
				);
				
			}
			
			// Scale triangle to aspect view
			triangleToProject.add(scale);
			
			// Scale triangle to screen view
			final Vec3F offset = new Vec3F((float)(JavaEngine.get().getWidth() >> 1), (float)(JavaEngine.get().getHeight() >> 1), 0.0F);
			triangleToProject.multiply(offset);
			
			// Set all the changes
			trianglesToProject.add(triangleToProject);
		}
	}
	
	/**
	 * Multiplies {@code Vector 3D float} to {@code Matrix 4x4 Float}
	 * 
	 * @param input
	 * - {@code Vector 3D float} to multiply
	 * 
	 * @param matrix
	 * - {@code Matrix 4x4 Float} to multiply
	 * 
	 * @return
	 * - {@code output vector} if operation is successful; {@code null} otherwise
	 */
	protected final Vec3F multiplyVecToMat(final Vec3F input, final MatrixF matrix) {
		// Invalid size
		if(matrix.size() != 4) return null;
		
		// Vector 3D float to return
		final Vec3F output = new Vec3F();
		
		// First element
		output.setX(
					input.getX() * matrix.get(0, 0) +
					input.getY() * matrix.get(1, 0) +
					input.getZ() * matrix.get(2, 0) +
					matrix.get(3, 0)
				);
		
		// Second element
		output.setY(
				input.getX() * matrix.get(0, 1) +
				input.getY() * matrix.get(1, 1) +
				input.getZ() * matrix.get(2, 1) +
				matrix.get(3, 1)
				);
		
		// Third element
		output.setZ(
				input.getX() * matrix.get(0, 2) +
				input.getY() * matrix.get(1, 2) +
				input.getZ() * matrix.get(2, 2) +
				matrix.get(3, 2)
				);
		
		// Fourth element
		final float w = 
				input.getX() * matrix.get(0, 3) +
				input.getY() * matrix.get(1, 3) +
				input.getZ() * matrix.get(2, 3) +
				matrix.get(3, 3);
		
		// Transform 4D back to 3D
		if(w != 0.0F) {
			output.setX(output.getX() / w);
			output.setY(output.getY() / w);
			output.setZ(output.getZ() / w);
		}
		
		return output;
	}
	
	/**
	 * Multiplies {@code Vector 3D float} to {@code Matrix 4x4 Float} without normalizing it
	 * 
	 * @param input
	 * - {@code Vector 3D float} to multiply
	 * 
	 * @param matrix
	 * - {@code Matrix 4x4 Float} to multiply
	 * 
	 * @return
	 * - {@code output vector} if operation is successful; {@code null} otherwise
	 */
	private final Vec3F multiplyVecToMatRaw(final Vec3F input, final MatrixF matrix) {
		// Invalid size
		if(matrix.size() != 4) return null;
		
		// Vector 3D float to return
		final Vec3F output = new Vec3F();
		
		// First element
		output.setX(
					input.getX() * matrix.get(0, 0) +
					input.getY() * matrix.get(1, 0) +
					input.getZ() * matrix.get(2, 0) +
					input.w      * matrix.get(3, 0)
				);
		
		// Second element
		output.setY(
				input.getX() * matrix.get(0, 1) +
				input.getY() * matrix.get(1, 1) +
				input.getZ() * matrix.get(2, 1) +
				input.w      * matrix.get(3, 1)
				);
		
		// Third element
		output.setZ(
				input.getX() * matrix.get(0, 2) +
				input.getY() * matrix.get(1, 2) +
				input.getZ() * matrix.get(2, 2) +
				input.w      * matrix.get(3, 2)
				);
		
		return output;
	}
	
 	/**
	 * Clears all the {@code triangles} from the list to project
	 */
	public void clean() {
		// Clear all the elements
		trianglesToProject.clear();
	}
	
	/**
	 * Draws an outline of all the {@code triangles}
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void draw(final Graphics2D g) {
		trianglesToProject.forEach(tri -> tri.drawLine(g));
	}
	
	/**
	 * Fills {@code all the triangles} with a solid color
	 * 
	 * @param g
	 * - {@code Graphics2D} to draw
	 */
	public void fill(final Graphics2D g) {
		trianglesToProject.forEach(tri -> tri.fill(g));
	}
	
	/**
	 * Applies {@code lighting} to this mesh
	 * 
	 * @param bool
	 * {@code true} whether lighting should be applied; {@code false} otherwise
	 */
	public void applyLigting(final boolean bool) {
		applyLighting = bool;
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
	
	// TODO: Document all of this
	
	/**
	 * @return
	 * Position offset of this {@code Mesh 3D}
	 */
	public Vec3F getPosition() {
		return position;
	}
	
	/**
	 * @return
	 * Rotation of this {@code Mesh 3D}
	 */
	public Vec3F getRotation() {
		return rotation;
	}

	/**
	 * @return
	 * Scale of this {@code Mesh 3D}
	 */
	public Vec3F getScale() {
		return scale;
	}
	
	/**
	 * @return
	 * {@code Color} of this mesh
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets {@code new color} to this mesh
	 * 
	 * @param newColor
	 * - {@code new} color
	 */
	public void setColor(final Color newColor) {
		color = newColor;
	}
}
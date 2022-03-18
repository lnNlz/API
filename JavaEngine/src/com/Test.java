package com;

import java.awt.Graphics2D;

import com.javaEngine.JavaEngine;
import com.javaEngine.Obj;
import com.javaEngine.geom.Triangle3D;
import com.javaEngine.math.Mathf;
import com.javaEngine.math.MatrixF;
import com.javaEngine.math.Vec3F;
import com.javaEngine.math.matrix.ProjectionMatrix;

public final class Test {
	public static void main(String[] args) {
		// Initialize Java engine
		if(!JavaEngine.init()) System.out.println("Unable to initialize Java Engine!");
		
		// Java Engine object
		JavaEngine engine = JavaEngine.get();
		
		// Create window
		// 1000 pixel width, 700 pixel height
		engine.display(1000, 700, "Testing program");
		
		// Start the engine
		engine.start();
		
		// Add the object
		engine.add(new Testing());
	}		
}

class Testing implements Obj {
	private final Triangle3D[] cube = new Triangle3D[12];
	private final ProjectionMatrix mat = new ProjectionMatrix();
	private final MatrixF rotZ = new MatrixF(4);
	private final MatrixF rotX = new MatrixF(4);
	
	@Override
	public void onStart() {
		// Front
		cube[0] = new Triangle3D(
					new Vec3F(0.0F, 0.0F, 0.0F),
					new Vec3F(1.0F, 1.0F, 0.0F),
					new Vec3F(1.0F, 0.0F, 0.0F)
				);
		
		cube[1] = new Triangle3D(
				new Vec3F(0.0F, 0.0F, 0.0F),
				new Vec3F(1.0F, 0.0F, 0.0F),
				new Vec3F(1.0F, 1.0F, 0.0F)
			);
		
		// Right
		cube[2] = new Triangle3D(
					new Vec3F(1.0F, 0.0F, 0.0F),
					new Vec3F(1.0F, 1.0F, 0.0F),
					new Vec3F(1.0F, 1.0F, 1.0F)
				);
		
		cube[3] = new Triangle3D(
				new Vec3F(1.0F, 0.0F, 0.0F),
				new Vec3F(1.0F, 1.0F, 1.0F),
				new Vec3F(1.0F, 0.0F, 1.0F)
			);
		
		// Back
		cube[4] = new Triangle3D(
					new Vec3F(1.0F, 0.0F, 1.0F),
					new Vec3F(1.0F, 1.0F, 1.0F),
					new Vec3F(0.0F, 1.0F, 1.0F)
				);
		
		cube[5] = new Triangle3D(
				new Vec3F(1.0F, 0.0F, 1.0F),
				new Vec3F(0.0F, 1.0f, 1.0F),
				new Vec3F(0.0F, 0.0F, 1.0F)
			);
		
		// Left
		cube[6] = new Triangle3D(
					new Vec3F(0.0F, 0.0F, 1.0F),
					new Vec3F(0.0F, 1.0F, 1.0F),
					new Vec3F(0.0F, 1.0F, 0.0F)
				);
		
		cube[7] = new Triangle3D(
				new Vec3F(0.0F, 0.0F, 1.0F),
				new Vec3F(0.0F, 1.0f, 0.0F),
				new Vec3F(0.0F, 0.0F, 0.0F)
			);
		
		// Top
		cube[8] = new Triangle3D(
					new Vec3F(0.0F, 1.0F, 0.0F),
					new Vec3F(0.0F, 1.0F, 1.0F),
					new Vec3F(1.0F, 1.0F, 1.0F)
				);
		
		cube[9] = new Triangle3D(
				new Vec3F(0.0F, 1.0F, 0.0F),
				new Vec3F(1.0F, 1.0f, 1.0F),
				new Vec3F(1.0F, 1.0F, 0.0F)
			);
		
		// Bottom
		cube[10] = new Triangle3D(
					new Vec3F(1.0F, 0.0F, 0.0F),
					new Vec3F(0.0F, 0.0F, 1.0F),
					new Vec3F(0.0F, 0.0F, 0.0F)
				);
		
		cube[11] = new Triangle3D(
				new Vec3F(1.0F, 0.0F, 0.0F),
				new Vec3F(0.0F, 1.0f, 0.0F),
				new Vec3F(1.0F, 0.0F, 0.0F)
			);
	}
	
	private float theta;
	@Override
	public void onUpdate(long elapsedTime) {
		theta += 0.05F;
		
		rotZ.set(0, 0, (float)Math.cos(theta));
		rotZ.set(0, 1, (float)Math.sin(theta));
		rotZ.set(1, 0, -(float)Math.sin(theta));
		rotZ.set(1, 1, (float)Math.cos(theta));
		rotZ.set(2, 2, 1.0F);
		rotZ.set(3, 3, 1.0F);
		
		rotX.set(0, 0, 1.0F);
		rotX.set(1, 1, (float)Math.cos(theta * 0.5F));
		rotX.set(1, 2, (float)Math.sin(theta * 0.5F));
		rotX.set(2, 1, -(float)Math.sin(theta * 0.5F));
		rotX.set(2, 2, (float)Math.cos(theta * 0.5F));
		rotX.set(3, 3, 1.0F);
	}
	
	@Override
	public void onRender(Graphics2D g) {
		for(final Triangle3D tri : cube) {
			final Triangle3D triZ = new Triangle3D(
						Mathf.multiply(tri.pointA, rotZ),
						Mathf.multiply(tri.pointB, rotZ),
						Mathf.multiply(tri.pointC, rotZ)
					);
			
			final Triangle3D triX = new Triangle3D(
					Mathf.multiply(triZ.pointA, rotX),
					Mathf.multiply(triZ.pointB, rotX),
					Mathf.multiply(triZ.pointC, rotX)
				);
			
			final Triangle3D trans = triX.clone();
			trans.pointA.setZ(triX.pointA.getZ() + 3.0F);
			trans.pointB.setZ(triX.pointB.getZ() + 3.0F);
			trans.pointC.setZ(triX.pointC.getZ() + 3.0F);
			
			final Triangle3D projTri = new Triangle3D(
						mat.multiply(trans.pointA),
						mat.multiply(trans.pointB),
						mat.multiply(trans.pointC)
					);
			
			// Scale
			projTri.pointA.setX(projTri.pointA.getX() + 1.0F);
			projTri.pointA.setY(projTri.pointA.getY() + 1.0F);
			
			projTri.pointB.setX(projTri.pointB.getX() + 1.0F);
			projTri.pointB.setY(projTri.pointB.getY() + 1.0F);
			
			projTri.pointC.setX(projTri.pointC.getX() + 1.0F);
			projTri.pointC.setY(projTri.pointC.getY() + 1.0F);
			
			final float w = JavaEngine.get().getWidth() >> 1;
			final float h = JavaEngine.get().getHeight() >> 1;
			
			projTri.pointA.setX(projTri.pointA.getX() * w);
			projTri.pointA.setY(projTri.pointA.getY() * h);
			
			projTri.pointB.setX(projTri.pointB.getX() * w);
			projTri.pointB.setY(projTri.pointB.getY() * h);
			
			projTri.pointC.setX(projTri.pointC.getX() * w);
			projTri.pointC.setY(projTri.pointC.getY() * h);
			
			// Draw
			g.drawLine(projTri.pointA.toVector3D().getX(), projTri.pointA.toVector3D().getY(),
					projTri.pointB.toVector3D().getX(), projTri.pointB.toVector3D().getY());
			g.drawLine(projTri.pointB.toVector3D().getX(), projTri.pointB.toVector3D().getY(),
					projTri.pointC.toVector3D().getX(), projTri.pointC.toVector3D().getY());
			g.drawLine(projTri.pointC.toVector3D().getX(), projTri.pointC.toVector3D().getY(),
					projTri.pointA.toVector3D().getX(), projTri.pointA.toVector3D().getY());
		}
	}
}
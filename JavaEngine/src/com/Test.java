package com;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.javaEngine.Camera;
import com.javaEngine.JavaEngine;
import com.javaEngine.Key;
import com.javaEngine.Mouse;
import com.javaEngine.Obj;
import com.javaEngine.geom.Cube;

public final class Test {
	public static void main(String[] args) {
		// Initialize Java engine
		if(!JavaEngine.init()) System.out.println("Unable to initialize Java Engine!");
		
		// Java Engine object
		JavaEngine engine = JavaEngine.get();
		
		// Create window
		// 1000 pixel width, 700 pixel height
		engine.display(1000, 700, "Testing program");
		JavaEngine.setBackgroundColor(Color.BLACK);
		
		// Start the engine
		engine.start();
		
		// Add the object
		engine.add(new Testing());
	}		
}

class Testing implements Obj {
	// Create the cube
	private final Cube cube = new Cube(1.0F);
	
	private Robot robot;
	public Testing() {
		// Offset the cube
		cube.getPosition().setZ(1.0F);
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	private float theta = 0.0F;
	
	@Override
	public void onUpdate(double deltaTime) {
		cube.clean();
		// TODO: Allow independent rotation
		final float speed = 0.1F * (float)deltaTime;
		final Camera camera = Camera.get();
		
		// Move on x and y coordinates
		if(Key.keyPressed(KeyEvent.VK_D))
			camera.position.setX( camera.position.getX() + speed );
			
		if(Key.keyPressed(KeyEvent.VK_A))
			camera.position.setX( camera.position.getX() - speed );
		
		if(Key.keyPressed(KeyEvent.VK_DOWN))
			camera.position.setY( camera.position.getY() - speed );
			
		if(Key.keyPressed(KeyEvent.VK_UP))
			camera.position.setY( camera.position.getY() + speed );
		
		// Move on z coordinate
		if(Key.keyPressed(KeyEvent.VK_W))
			camera.position.setZ( camera.position.getZ() + speed );
		
		if(Key.keyPressed(KeyEvent.VK_S))
			camera.position.setZ( camera.position.getZ() - speed );
		
		final int w = JavaEngine.get().getWidth();
		theta = (float)Mouse.getX() / (float)w * 5;
		
		if(Key.keyPressed(KeyEvent.VK_ESCAPE))
			System.exit(0);
		
		camera.rotation.setY( theta );
			
		// Do some magic
		cube.toScreenCoordinates();
	}
	
	@Override
	public void onRender(final Graphics2D g) {
		// Render the cube
		cube.fill(g);
		
		g.setFont(new Font("Arial", 0, 20));
		g.setColor(Color.WHITE);
		g.drawString(Camera.get().position.toString(), 0, 20);
		g.drawString(Camera.get().rotation.toString(), 0, 40);
	}
}

//class Testing implements Obj {
////	private final Triangle3D[] cube = new Triangle3D[2];
////	private final ProjectionMatrix mat = new ProjectionMatrix();
////	private final XRotationMatrix rotX = new XRotationMatrix();
////	private final ZRotationMatrix rotZ = new ZRotationMatrix();
////	private final YRotationMatrix rotY = new YRotationMatrix();
//	
//	@Override
//	public void onStart() {
//		// Front
////		cube[0] = new Triangle3D(
////					new Vec3F(0.0F, 0.0F, 0.0F),
////					new Vec3F(0.0F, 1.0F, 0.0F),
////					new Vec3F(1.0F, 1.0F, 0.0F)
////				);
////		
////		cube[1] = new Triangle3D(
////				new Vec3F(0.0F, 0.0F, 0.0F),
////				new Vec3F(1.0F, 0.0F, 0.0F),
////				new Vec3F(1.0F, 1.0F, 0.0F)
////			);
//////		
//////		// Right
////		cube[2] = new Triangle3D(
////					new Vec3F(1.0F, 0.0F, 0.0F),
////					new Vec3F(1.0F, 1.0F, 0.0F),
////					new Vec3F(1.0F, 1.0F, 1.0F)
////				);
////		
////		cube[3] = new Triangle3D(
////				new Vec3F(1.0F, 0.0F, 0.0F),
////				new Vec3F(1.0F, 1.0F, 1.0F),
////				new Vec3F(1.0F, 0.0F, 1.0F)
////			);
////		
////		// Back
////		cube[0] = new Triangle3D(
////					new Vec3F(1.0F, 0.0F, 1.0F),
////					new Vec3F(1.0F, 1.0F, 1.0F),
////					new Vec3F(0.0F, 1.0F, 1.0F)
////				);
////		
////		cube[1] = new Triangle3D(
////				new Vec3F(1.0F, 0.0F, 1.0F),
////				new Vec3F(0.0F, 1.0f, 1.0F),
////				new Vec3F(0.0F, 0.0F, 1.0F)
////			);
////		
////		// Left
////		cube[0] = new Triangle3D(
////					new Vec3F(0.0F, 0.0F, 1.0F),
////					new Vec3F(0.0F, 1.0F, 1.0F),
////					new Vec3F(0.0F, 1.0F, 0.0F)
////				);
////		
////		cube[1] = new Triangle3D(
////				new Vec3F(0.0F, 0.0F, 1.0F),
////				new Vec3F(0.0F, 1.0f, 0.0F),
////				new Vec3F(0.0F, 0.0F, 0.0F)
////			);
////		
////		// Top
////		cube[0] = new Triangle3D(
////					new Vec3F(0.0F, 1.0F, 0.0F),
////					new Vec3F(0.0F, 1.0F, 1.0F),
////					new Vec3F(1.0F, 1.0F, 1.0F)
////				);
////		
////		cube[1] = new Triangle3D(
////				new Vec3F(0.0F, 1.0F, 0.0F),
////				new Vec3F(1.0F, 1.0f, 1.0F),
////				new Vec3F(1.0F, 1.0F, 0.0F)
////			);
////		
////		// Bottom
//////		cube[0] = new Triangle3D(
////					new Vec3F(1.0F, 0.0F, 0.0F),
////					new Vec3F(0.0F, 0.0F, 1.0F),
////					new Vec3F(0.0F, 0.0F, 0.0F)
////				);
////		
////		cube[1] = new Triangle3D(
////				new Vec3F(1.0F, 0.0F, 0.0F),
////				new Vec3F(0.0F, 1.0f, 0.0F),
////				new Vec3F(1.0F, 0.0F, 0.0F)
////			);
//	}
//	
//	private float theta;
//	private float theta2;
//	private float theta3;
//	@Override
//	public void onUpdate(long elapsedTime) {
//		final float speed = 0.005F;
//		
//		theta += speed;
//		theta2 += speed * 0.5F;
////		theta3 += speed * 0.1F;
//		
////		if(Key.keyPressed(KeyEvent.VK_W)) {
////			theta += speed;
////		}
////		
////		if(Key.keyPressed(KeyEvent.VK_S)) {
////			theta -= speed;
////		}
////		
////		if(Key.keyPressed(KeyEvent.VK_A)) {
////			theta2 += speed;
////		}
////		
////		if(Key.keyPressed(KeyEvent.VK_D)) {
////			theta2 -= speed;
////		}
////		
////		if(Key.keyPressed(KeyEvent.VK_LEFT)) {
////			theta3 += speed;
////		}
////		
////		if(Key.keyPressed(KeyEvent.VK_RIGHT)) {
////			theta3 -= speed;
////		}
//		
//		// Rotate z
////		rotZ.setTheta(theta2);
//		
//		// Rotate x
//		rotX.setTheta(theta);
//		
//		// Rotate y
//		rotY.setTheta(theta3);
//	}
//	
//	@Override
//	public void onRender(Graphics2D g) {
////		for(final Triangle3D tri : cube) {
////			final Triangle3D triZ = new Triangle3D(
////						Mathf.multiplyVecToMat(tri.pointA, rotZ),
////						Mathf.multiplyVecToMat(tri.pointB, rotZ),
////						Mathf.multiplyVecToMat(tri.pointC, rotZ)
////					);
////			
////			final Triangle3D triX = new Triangle3D(
////					Mathf.multiplyVecToMat(triZ.pointA, rotX),
////					Mathf.multiplyVecToMat(triZ.pointB, rotX),
////					Mathf.multiplyVecToMat(triZ.pointC, rotX)
////				);
////			
////			final Triangle3D triY = new Triangle3D(
////					Mathf.multiplyVecToMat(triX.pointA, rotY),
////					Mathf.multiplyVecToMat(triX.pointB, rotY),
////					Mathf.multiplyVecToMat(triX.pointC, rotY)
////				);
////			
////			final Triangle3D trans = triY.clone();
////			trans.pointA.setZ(triY.pointA.getZ() + 3.0F);
////			trans.pointB.setZ(triY.pointB.getZ() + 3.0F);
////			trans.pointC.setZ(triY.pointC.getZ() + 3.0F);
////			
////			final Triangle3D projTri = new Triangle3D(
////						Mathf.multiplyVecToMat(trans.pointA, mat),
////						Mathf.multiplyVecToMat(trans.pointB, mat),
////						Mathf.multiplyVecToMat(trans.pointC, mat)
////					);
////			
////			// Scale
////			projTri.pointA.setX(projTri.pointA.getX() + 1.0F);
////			projTri.pointA.setY(projTri.pointA.getY() + 1.0F);
////			
////			projTri.pointB.setX(projTri.pointB.getX() + 1.0F);
////			projTri.pointB.setY(projTri.pointB.getY() + 1.0F);
////			
////			projTri.pointC.setX(projTri.pointC.getX() + 1.0F);
////			projTri.pointC.setY(projTri.pointC.getY() + 1.0F);
////			
////			// Mid
////			final float w = JavaEngine.get().getWidth() >> 1;
////			final float h = JavaEngine.get().getHeight() >> 1;
////			
////			projTri.pointA.setX(projTri.pointA.getX() * w);
////			projTri.pointA.setY(projTri.pointA.getY() * h);
////			
////			projTri.pointB.setX(projTri.pointB.getX() * w);
////			projTri.pointB.setY(projTri.pointB.getY() * h);
////			
////			projTri.pointC.setX(projTri.pointC.getX() * w);
////			projTri.pointC.setY(projTri.pointC.getY() * h);
////			
////			// Draw
//////			g.drawLine(projTri.pointA.toVector3D().getX(), projTri.pointA.toVector3D().getY(),
//////					projTri.pointB.toVector3D().getX(), projTri.pointB.toVector3D().getY());
//////			g.drawLine(projTri.pointB.toVector3D().getX(), projTri.pointB.toVector3D().getY(),
//////					projTri.pointC.toVector3D().getX(), projTri.pointC.toVector3D().getY());
//////			g.drawLine(projTri.pointC.toVector3D().getX(), projTri.pointC.toVector3D().getY(),
//////					projTri.pointA.toVector3D().getX(), projTri.pointA.toVector3D().getY());
////			
//////			projTri.fill(g);
////			
////			g.setColor(new Color(0, 100, 0));
////			projTri.fill(g);
////		}
//	}
//}
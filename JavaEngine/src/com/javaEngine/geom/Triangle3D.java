package com.javaEngine.geom;

import java.awt.Graphics2D;

import com.javaEngine.math.Vec3F;

public class Triangle3D {
	public final Vec3F pointA;
	public final Vec3F pointB;
	public final Vec3F pointC;
	
	public Triangle3D(final Vec3F a, final Vec3F b, final Vec3F c) {
		pointA = a;
		pointB = b;
		pointC = c;
	}
	
	public void render(final Graphics2D g) {
		g.drawLine((int)pointA.getX(), (int)pointA.getY(), 
				   (int)pointB.getX(), (int)pointB.getY());
		
		g.drawLine((int)pointB.getX(), (int)pointB.getY(), 
				   (int)pointC.getX(), (int)pointC.getY());
		
		g.drawLine((int)pointC.getX(), (int)pointC.getY(), 
				   (int)pointA.getX(), (int)pointA.getY());
	}
	
	@Override
	public Triangle3D clone() {
		return new Triangle3D(pointA, pointB, pointC);
	}
}
package net.decitrig.galaxy;

import java.awt.geom.Point2D;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** Represents a single galaxy. */
public class Galaxy {
	private final int mass;

	private final ReadWriteLock positionLock = new ReentrantReadWriteLock();
	private double x;
	private double y;

	public Galaxy(int mass, double x, double y) {
		this.mass = mass;
		this.x = x;
		this.y = y;
	}

	Point2D location() {
		positionLock.readLock().lock();
		try {
			return new Point2D.Double(x, y);
		} finally {
			positionLock.readLock().unlock();
		}
	}

	public int getMass() {
	  return mass;
  }
}

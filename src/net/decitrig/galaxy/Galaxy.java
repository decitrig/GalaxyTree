package net.decitrig.galaxy;

import java.awt.geom.Point2D;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.base.Objects;

/** Represents a single galaxy. */
public class Galaxy {
  private static final AtomicInteger nextId = new AtomicInteger(0);

  private final int id;
  private final int mass;

  private final ReadWriteLock positionLock = new ReentrantReadWriteLock();
  private double x;
  private double y;

  public Galaxy(int mass, double x, double y) {
    this.id = nextId.getAndIncrement();
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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Galaxy)) {
      return false;
    }
    return ((Galaxy) obj).id == this.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return com.google.common.base.Objects.toStringHelper(this)
        .add("x", String.format("%.2f", x))
        .add("y", String.format("%.2f", y))
        .toString();
  }
}

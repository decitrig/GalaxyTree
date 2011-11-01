package net.decitrig.universe.octree;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.base.Objects;
import com.google.common.primitives.Doubles;

/** Represents a 3-dimensional box in space, where all sides are equal in length. */
public class Box {
  public enum Octant {
    TOP_NE(new Vector3D(1, 1, 1)),
    TOP_SE(new Vector3D(1, 0, 1)),
    TOP_NW(new Vector3D(0, 1, 1)),
    TOP_SW(new Vector3D(0, 0, 1)),
    BOTTOM_NE(new Vector3D(1, 1, 0)),
    BOTTOM_SE(new Vector3D(1, 0, 0)),
    BOTTOM_NW(new Vector3D(0, 1, 0)),
    BOTTOM_SW(new Vector3D(0, 0, 0)),
    ;

    private final Vector3D unit;

    private Octant(Vector3D unit) {
      this.unit = unit;
    }

    public Vector3D unit() {
      return this.unit;
    }
  }

  /** Corner of box closest to global origin. */
  private final Vector3D origin;
  /** Length of one side. */
  private final double size;

  public Box(Vector3D origin, double size) {
    this.origin = origin;
    this.size = size;
  }

  public Box getOctantBox(Box.Octant oct) {
    return new Box(origin.add(size / 2, oct.unit()), size / 2);
  }

  public Octant octantOf(Vector3D vector) {
    Vector3D center = origin.add(size / 2, new Vector3D(1, 1, 1));
    int xResult = Doubles.compare(vector.getX(), center.getX());
    int yResult = Doubles.compare(vector.getY(), center.getY());
    int zResult = Doubles.compare(vector.getZ(), center.getZ());
    if (zResult < 0) {
      // bottom
      if (xResult < 0)  {
        // bottom west
        if (yResult < 0) {
          return Octant.BOTTOM_SW;
        }
        return Octant.BOTTOM_NW;
      } else {
        // bottom east
        if (yResult < 0) {
          return Octant.BOTTOM_SE;
        }
        return Octant.BOTTOM_NE;
      }
    } else {
      // top
      if (xResult < 0)  {
        // top west
        if (yResult < 0) {
          return Octant.TOP_SW;
        }
        return Octant.TOP_NW;
      } else {
        // top east
        if (yResult < 0) {
          return Octant.TOP_SE;
        }
        return Octant.TOP_NE;
      }
    }
  }

  public Vector3D getOrigin() {
    return origin;
  }

  public double getSize() {
    return size;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Box)) {
      return false;
    }
    Box that = (Box) obj;
    return Objects.equal(this.origin, that.origin)
        && Objects.equal(this.size, that.size);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.origin, this.size);
  }
}

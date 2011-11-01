package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.base.Objects;

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

package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

public class TestUtil {
  private TestUtil() {}

  public static Vector3D vector(double i, double j, double k) {
    return new Vector3D(i, j, k);
  }
}

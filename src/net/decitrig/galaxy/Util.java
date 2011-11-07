package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

public class Util {
  private Util() {}

  public static Vector3D vector(double i, double j, double k) {
    return new Vector3D(i, j, k);
  }
}

package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.annotations.VisibleForTesting;

public class OctTreeUniverse implements Universe {

  @VisibleForTesting
  interface Node {
    Node insert(Particle p);
    Vector3D barycenter();
    Vector3D mass();
  }

  @Override
  public Iterable<Particle> particles() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(double timeDelta) {
    throw new UnsupportedOperationException();
  }
}

package net.decitrig.galaxy;

public interface Universe {
  Iterable<Particle> particles();
  void update(double timeDelta);
}

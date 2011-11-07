package net.decitrig.galaxy;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

/** Reads universe information from a stream and turns it into a list of {@code Particle}s. */
public class UniverseParser {
  private Scanner scanner;

  public UniverseParser(InputStream stream) {
    scanner = new Scanner(stream);
  }

  public List<Particle> parseParticles() {
    int size = scanner.nextInt();
    List<Particle> particles = Lists.newArrayListWithCapacity(size);
    for (int i = 0; i < size; i++) {
      double x = scanner.nextDouble();
      double y = scanner.nextDouble();
      double z = scanner.nextDouble();
      double mass = scanner.nextDouble();
      double vx = scanner.nextDouble();
      double vy = scanner.nextDouble();
      double vz = scanner.nextDouble();
      particles.add(new Particle(mass, Util.vector(x, y, z), Util.vector(vx, vy, vz)));
    }
    return particles;
  }
}

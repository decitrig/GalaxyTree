package net.decitrig.galaxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UniverseParserTest {
  private static final InputStream fiveGoodParticles = UniverseParserTest.class.getResourceAsStream("5-good-particles.txt");

  @Before
  public void checkResources() {
    assertNotNull(fiveGoodParticles);
  }

  @Test
  public void testParticlesFromString() {
    UniverseParser parser = new UniverseParser(fiveGoodParticles);
    List<Particle> particles = parser.parseParticles();
    assertEquals(5, particles.size());
  }
}

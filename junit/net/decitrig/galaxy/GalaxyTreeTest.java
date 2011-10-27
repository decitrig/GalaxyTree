package net.decitrig.galaxy;

import static org.junit.Assert.*;

import org.junit.Test;

public class GalaxyTreeTest {

  private static final Galaxy GALAXY2 = new Galaxy(1, 3.0, 3.0);
  private static final Galaxy GALAXY1 = new Galaxy(1, 2.0, 2.0);

  @Test
  public void testAddIncrementsSize() {
    GalaxyTree tree = new GalaxyTree();
    assertEquals(0, tree.size());
    assertTrue(tree.isEmpty());
    assertTrue(tree.add(GALAXY1));
    assertEquals(1, tree.size());
    assertFalse(tree.isEmpty());

    assertTrue(tree.add(GALAXY2));
    assertEquals(2, tree.size());
    assertFalse(tree.isEmpty());
  }

  @Test
  public void testInsertDuplicate() {
    GalaxyTree tree = new GalaxyTree();
    assertTrue(tree.add(GALAXY1));
    assertFalse(tree.add(new Galaxy(1, 2.0, 2.0)));
    assertEquals(1, tree.size());
  }
}

package net.decitrig.galaxy;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import net.decitrig.util.Pair;

import com.google.common.primitives.Doubles;

public class GalaxyTree extends AbstractCollection<Galaxy> {
  private Node root = EmptyNode.instance;

  @Override
  public Iterator<Galaxy> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int size() {
    return root.size();
  }

  @Override
  public boolean isEmpty() {
    return root.isEmpty();
  }

  @Override
  public boolean add(Galaxy galaxy) {
    Pair<Node, Boolean> pair = root.insert(galaxy, 0);
    root = pair.head();
    return pair.tail();
  }

  private static class LongitudeComparator implements Comparator<Galaxy> {
    static final LongitudeComparator instance = new LongitudeComparator();
    @Override
    public int compare(Galaxy a, Galaxy b) {
      int result = Doubles.compare(a.location().getX(), b.location().getX());
      if (result != 0) {
        return result;
      }
      return Doubles.compare(a.location().getY(), b.location().getY());
    }
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  private static class LatitudeComparator implements Comparator<Galaxy> {
    static final LatitudeComparator instance = new LatitudeComparator();
    @Override
    public int compare(Galaxy a, Galaxy b) {
      int result = Doubles.compare(a.location().getY(), b.location().getY());
      if (result != 0) {
        return result;
      }
      return Doubles.compare(a.location().getX(), b.location().getX());
    }
  }

  private interface Node {
    int size();
    boolean isEmpty();
    Pair<Node, Boolean> insert(Galaxy g, int level);
  }

  private static class EmptyNode implements Node {
    static final EmptyNode instance = new EmptyNode();

    @Override
    public int size() {
      return 0;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public Pair<Node, Boolean> insert(Galaxy g, int level) {
      Node n = new GalaxyNode(g);
      return Pair.of(n, true);
    }
  }

  private static class GalaxyNode implements Node {
    private final Galaxy galaxy;

    private Node left = EmptyNode.instance;
    private Node right = EmptyNode.instance;

    GalaxyNode(Galaxy galaxy) {
      this.galaxy = galaxy;
    }

    public int size() {
      return 1 + left.size() + right.size();
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    public Pair<Node, Boolean> insert(Galaxy galaxy, int level) {
      int result = compare(galaxy, level);
      if (result < 0) {
        Pair<Node, Boolean> pair = left.insert(galaxy, level + 1);
        left = pair.head();
        return Pair.of((Node) this, pair.tail());
      } else if (result > 0) {
        Pair<Node, Boolean> pair = right.insert(galaxy, level + 1);
        right = pair.head();
        return Pair.of((Node) this, pair.tail());
      } else {
        return Pair.of((Node) this, false);
      }
    }

    private int compare(Galaxy galaxy, int level) {
      int result;
      if (level % 2 == 0) {
        result = LongitudeComparator.instance.compare(galaxy, this.galaxy);
      } else {
        result = LatitudeComparator.instance.compare(galaxy, this.galaxy);
      }
      return result;
    }
  }
}

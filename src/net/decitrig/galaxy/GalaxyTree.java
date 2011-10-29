package net.decitrig.galaxy;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;

public class GalaxyTree {
	private static Comparator<Galaxy> xAxisComparator = new Comparator<Galaxy>() {
		@Override
		public int compare(Galaxy a, Galaxy b) {
			return Doubles.compare(a.location().getX(), b.location().getX());
		}
	};
	private static Comparator<Galaxy> yAxisComparator = new Comparator<Galaxy>() {
		@Override
		public int compare(Galaxy a, Galaxy b) {
			return Doubles.compare(a.location().getY(), b.location().getY());
		}
	};

	private static abstract class Node {
		private static Point2D findMedian(List<Galaxy> sorted) {
			Preconditions.checkArgument(sorted.size() > 0, "Sorted list must not be empty");
			if (sorted.size() == 1) {
				return sorted.get(0).location();
			}
			int mid = sorted.size() / 2;
			if (sorted.size() % 2 == 0) {
				Point2D high = sorted.get(mid).location();
				Point2D low = sorted.get(mid - 1).location();
				double x = (high.getX() + low.getX()) / 2;
				double y = (high.getY() + low.getY()) / 2;
				return new Point2D.Double(x, y);
			} else {
				return sorted.get(mid).location();
			}
		}

		private static Collection<Galaxy>
		    leftOf(final Point2D median, List<Galaxy> galaxies, int level) {
			Predicate<Galaxy> predicate;
			if (level % 2 == 0) {
				predicate = new Predicate<Galaxy>() {
					@Override
					public boolean apply(Galaxy input) {
					  return input.location().getX() < median.getX();
					}
				};
			} else {
				predicate = new Predicate<Galaxy>() {
					@Override
					public boolean apply(Galaxy input) {
						return input.location().getY() < median.getY();
					}
				};
			}
			return Collections2.filter(galaxies, predicate);
		}

		private static Collection<Galaxy>
		    rightOf(final Point2D median, List<Galaxy> galaxies, int level) {
			Predicate<Galaxy> predicate;
			if (level % 2 == 0) {
				predicate = new Predicate<Galaxy>() {
					@Override
					public boolean apply(Galaxy input) {
						return input.location().getX() >= median.getX();
					}
				};
			} else {
				predicate = new Predicate<Galaxy>() {
					@Override
					public boolean apply(Galaxy input) {
						return input.location().getY() >= median.getY();
					}
				};
			}
			return Collections2.filter(galaxies, predicate);
		}

		static Node create(Collection<Galaxy> galaxies, int level) {
			if (galaxies.isEmpty()) {
				return EmptyClump.instance;
			}
			if (galaxies.size() == 1) {
				return new LeafGalaxy(galaxies.iterator().next());
			}
			List<Galaxy> sorted = new ArrayList<Galaxy>(galaxies);
			if (level % 2 == 0) {
				Collections.sort(sorted, xAxisComparator);
			} else {
				Collections.sort(sorted, yAxisComparator);
			}
			Point2D median = findMedian(sorted);
			double radius = Double.NEGATIVE_INFINITY;
			for (Galaxy g : sorted) {
				if (radius < g.location().distance(median)) {
					radius = g.location().distance(median);
				}
			}
			int mid = sorted.size() / 2;
			Node left = Node.create(leftOf(median, sorted, level), level + 1);
			Node right = Node.create(rightOf(median, sorted, level), level + 1);
			return new Clump(median, radius, left, right);
		}

		private Clump parent;

		void setParent(Clump parent) {
			this.parent = parent;
		}

		public Clump getParent() {
	    return parent;
    }

		public abstract String debugString(int level);
	}

	private static class EmptyClump extends Node {
		private static final EmptyClump instance = new EmptyClump();

		@Override
		public String toString() {
		  return "EmptyClump";
		}

		@Override
    public String debugString(int level) {
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < level; i++) {
	    	builder.append("|--");
	    }
	    builder.append(toString() + "\n");
	    return builder.toString();
    }

	}

	private static class LeafGalaxy extends Node {
		Galaxy galaxy;

		LeafGalaxy(Galaxy galaxy) {
			this.galaxy = galaxy;
		}

		@Override
		public String toString() {
			return Objects.toStringHelper(this)
			              .add("x", String.format("%.2f", galaxy.location().getX()))
			              .add("y", String.format("%.2f", galaxy.location().getY()))
			              .toString();
		}

		@Override
		public String debugString(int level) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < level; i++) {
				builder.append("|--");
			}
			builder.append(toString() + "\n");
			return builder.toString();
		}
	}

	private static class Clump extends Node {
		Point2D center;
		double radius;
		Node left;
		Node right;

		Clump(Point2D center, double radius, Node left, Node right) {
			this.center = center;
			this.radius = radius;
			this.left = left;
			this.right = right;
			if (left != null) {
				left.setParent(this);
			}
			if (right != null) {
				right.setParent(this);
			}
		}

		@Override
		public String toString() {
			return Objects.toStringHelper(this)
			              .add("x", String.format("%.2f", center.getX()))
			              .add("y", String.format("%.2f", center.getY()))
			              .add("radius", String.format("%.2f", radius))
			              .toString();
		}

	  public String debugString(int level) {
	  	StringBuilder builder = new StringBuilder();
	  	for (int i = 0; i < level; i++) {
	  		builder.append("|--");
	  	}
	  	builder.append(toString() + "\n");
	  	builder.append(left == null ? "null\n" : left.debugString(level + 1));
	  	builder.append(right == null ? "null\n" : right.debugString(level + 1));
	  	return builder.toString();
	  }
	}

  private Node root;

  public GalaxyTree(List<Galaxy> galaxies) {
  	root = Node.create(galaxies, 0);
  }

  public int size() {
  	return -1;
  }

  public boolean isEmpty() {
  	return false;
  }

  public String debugString() {
  	return root.debugString(0);
  }
}

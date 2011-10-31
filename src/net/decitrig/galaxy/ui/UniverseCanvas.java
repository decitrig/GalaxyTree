package net.decitrig.galaxy.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.decitrig.galaxy.Particle;
import net.decitrig.galaxy.Universe;

import org.apache.commons.math.geometry.Vector3D;

public class UniverseCanvas {

  private static Shape dot(Point2D center, int radius) {
    double minX = center.getX() - radius;
    double minY = center.getY() - radius;
    return new Ellipse2D.Double(minX, minY, radius*2, radius*2);
  }

  private class CanvasPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      g2.setColor(Color.BLACK);
      g2.setBackground(Color.WHITE);
      g2.clearRect(0, 0, getWidth(), getHeight());

      g2.setBackground(Color.WHITE);
      g2.setColor(Color.BLACK);

      for (Particle p : universe.particles()) {
        Vector3D position = p.position();
        Point2D center = new Point2D.Double(position.getX(), position.getY());
        g2.draw(dot(center, 10));
      }
    }
  }

  private final Universe universe;
  private final CanvasPanel canvas = new CanvasPanel();

  public UniverseCanvas(Universe universe) {
    this.universe = universe;
  }

  public JComponent getComponent() {
    return canvas;
  }
}

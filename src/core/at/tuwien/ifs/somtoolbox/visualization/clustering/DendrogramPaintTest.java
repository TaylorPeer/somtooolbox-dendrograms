/*
 * Copyright 2004-2010 Institute of Software Technology and Interactive Systems, Vienna University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.ifs.tuwien.ac.at/dm/somtoolbox/license.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.tuwien.ifs.somtoolbox.visualization.clustering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author taylorpeer
 */
public class DendrogramPaintTest {

    public static void createAndShowGUI(ClusterNode topNode) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DendrogramPaintPanel panel = new DendrogramPaintPanel(topNode);
        f.getContentPane().add(panel);

        f.setSize(1000, 800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class DendrogramPaintPanel extends JPanel {

    private static final long serialVersionUID = 1142616885007793370L;

    private ClusterNode root;

    private int leaves;

    private int levels;

    private int heightPerLeaf;

    private int widthPerLevel;

    private int currentY;

    DendrogramPaintPanel(ClusterNode topNode) {
        this.root = topNode;
    }

    private static int getNumChildren(ClusterNode node) {

        int count = 0;
        if (node == null) {
            return count;
        }

        ClusterNode child1 = node.getChild1();
        ClusterNode child2 = node.getChild2();
        if (child1 != null) {
            count++;
        }
        if (child2 != null) {
            count++;
        }
        return count + getNumChildren(child1) + getNumChildren(child1);
    }

    private static <T> int countLeaves(ClusterNode node) {
        if (getNumChildren(node) == 0) {
            return 1;
        }
        ClusterNode child1 = node.getChild1();
        ClusterNode child2 = node.getChild2();
        return countLeaves(child1) + countLeaves(child2);
    }

    private static <T> int countLevels(ClusterNode node) {
        if (getNumChildren(node) == 0) {
            return 1;
        }
        ClusterNode child1 = node.getChild1();
        ClusterNode child2 = node.getChild2();
        return 1 + Math.max(countLevels(child1), countLevels(child2));
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;

        int margin = 25;
        leaves = countLeaves(root);
        levels = countLevels(root);
        heightPerLeaf = (getHeight() - margin - margin) / leaves;
        widthPerLevel = (getWidth() - margin - margin) / levels;
        currentY = 0;

        g.translate(margin, margin);
        draw(g, root, 0);
    }

    private <T> Point draw(Graphics g, ClusterNode node, int y) {
        if (getNumChildren(node) == 0) {
            int x = getWidth() - widthPerLevel;

            double centroidX = node.getCentroid().getX();
            double centroidY = node.getCentroid().getY();
            String label = centroidX + "x" + centroidY;

            g.drawString(label, x + 8, currentY + 8);
            int resultX = x;
            int resultY = currentY;
            currentY += heightPerLeaf;
            return new Point(resultX, resultY);
        }
        if (getNumChildren(node) >= 2) {
            ClusterNode child1 = node.getChild1();
            ClusterNode child2 = node.getChild2();
            Point p0 = draw(g, child1, y);
            Point p1 = draw(g, child2, y + heightPerLeaf);

            g.fillRect(p0.x - 2, p0.y - 2, 4, 4);
            g.fillRect(p1.x - 2, p1.y - 2, 4, 4);
            int dx = widthPerLevel;
            int vx = Math.min(p0.x - dx, p1.x - dx);
            g.drawLine(vx, p0.y, p0.x, p0.y);
            g.drawLine(vx, p1.y, p1.x, p1.y);
            g.drawLine(vx, p0.y, vx, p1.y);
            Point p = new Point(vx, p0.y + (p1.y - p0.y) / 2);
            return p;
        }
        // Should never happen
        return new Point();
    }
}
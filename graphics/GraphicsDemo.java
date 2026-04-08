package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * CMSC 12 Master Graphics Demo
 * Bundles all lecture concepts into one interactive file.
 */
public class GraphicsDemo extends JFrame {

    private String currentDemo = "Coordinates"; // Default starting demo

    private Timer animationTimer;

     // Animation Variables
    private double ballX = 50, ballY = 50;
    private double ballDX = 10, ballDY = 10; // Speed/Direction
    private double angle = 0.05;

    public GraphicsDemo() {
        setTitle("CMSC 12: Java Graphics Master Demo");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Layout Setup ---
        setLayout(new BorderLayout());
        
        // Sidebar for navigation
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(10, 1, 5, 5));
        sidebar.setBackground(Color.LIGHT_GRAY);

        // Buttons for each slide concept
        String[] buttons = {
            "Coordinates", "Lines", "Rectangles", "Ovals", 
            "Arcs", "Polygons", "Graphics2D", "Paths", "Animation", "Clear"
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.addActionListener(e -> {
                currentDemo = label;
                handleTimer(label); // Start or stop timer based on mode
                repaint(); // Triggers paintComponent in the DrawingPanel
            });
            sidebar.add(btn);
        }

        // Main Drawing Area
        DrawingPanel canvas = new DrawingPanel();

        canvas.add(new JButton("Buttton!"));
        
        add(sidebar, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);

        // Initialize Timer (roughly 60 frames per second)
        animationTimer = new Timer(16, e -> {
            updateAnimation();
            repaint();
        });

        setVisible(true);
    }

    private void handleTimer(String label) {
        if (label.equals("Animation") || label.equals("Paths")) {
            animationTimer.start();
        } else {
            animationTimer.stop();
        }
    }

    private void updateAnimation() {
        // Move the ball
        ballX += ballDX;
        ballY += ballDY;

        //Bounce off walls (accounting for sidebar width and ball size)
        if (ballX < 0 || ballX > 650) ballDX *= -1;
        if (ballY < 0 || ballY > 530) ballDY *= -1;

        angle += 0.05f;
    }


    // Inner class for the custom drawing logic
    class DrawingPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            // Rule 1: Always call super to clear the screen
            super.paintComponent(g);
            
            // Rule 2: Cast to Graphics2D for better features
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            g2d.setColor(Color.BLACK);
            g2d.drawString("Current Demo: " + currentDemo, 20, 30);

            // Switch based on button clicked
            switch (currentDemo) {
                case "Coordinates":
                    drawCoordinates(g2d);
                    break;
                case "Lines":
                    drawLinesDemo(g2d);
                    break;
                case "Rectangles":
                    drawRectsDemo(g2d);
                    break;
                case "Ovals":
                    drawOvalsDemo(g2d);
                    break;
                case "Arcs":
                    drawArcsDemo(g2d);
                    break;
                case "Polygons":
                    drawPolygonsDemo(g2d);
                    break;
                case "Graphics2D":
                    drawG2DDemo(g2d);
                    break;
                case "Paths":
                    drawPathsDemo(g2d);
                    break;
                case "Animation": 
                    drawAnimationDemo(g2d); 
                    break;
                default:
                    break;
            }
        }

        private void drawCoordinates(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.fillOval(100, 100, 10, 10); // Point at (100,100)
            g2d.drawString("Origin (0,0) is Top-Left", 10, 60);
            g2d.drawString("Point (100, 100)", 115, 110);
            
            // Visualize Y-axis going DOWN
            g2d.setColor(Color.BLUE);
            g2d.drawLine(20, 80, 20, 200);
            g2d.drawString("Y increases DOWN", 30, 150);

            g2d.fillOval(400, 400, 50, 50); // Point at (100,100)
            g2d.drawString("Point (400, 400)", 415, 410);
        }

        private void drawLinesDemo(Graphics2D g2d) {
            g2d.setColor(Color.BLUE);
            // drawLine(startX, startY, endX, endY)
            g2d.drawLine(50, 100, 300, 300);
            g2d.drawString("Line from (50,100) to (300,300)", 50, 90);
        }

        private void drawRectsDemo(Graphics2D g2d) {
            // Draw vs Fill
            g2d.setColor(Color.BLACK);
            //g2d.drawRect(50, 50, 150, 100); 

            Rectangle2D rect = new Rectangle2D.Double(50, 50, 150, 100);
            g2d.draw(rect);
            
            g2d.setColor(Color.GREEN);
            g2d.fillRect(250, 50, 150, 100);

            g2d.setColor(Color.RED);
            g2d.fillRoundRect(50, 200, 150, 100, 40, 40);
            g2d.drawString("RoundRect with 40px Arcs", 50, 320);
        }

        private void drawOvalsDemo(Graphics2D g2d) {
            g2d.setColor(Color.MAGENTA);
            // drawOval(x, y, width, height)
            g2d.drawOval(50, 50, 200, 100); 
            
            g2d.setColor(Color.ORANGE);
            g2d.fillOval(300, 50, 150, 150); // width=height makes a Circle
            g2d.drawString("Circle (W=H)", 340, 220);
        }

        private void drawArcsDemo(Graphics2D g2d) {
            
            // fillArc(x, y, w, h, startAngle, arcAngle)
            
            
            g2d.setColor(Color.BLACK);  
            g2d.drawArc(100, 100, 200, 200, 45, 270);
            g2d.drawString("Start: 45°, Arc: 270°", 120, 330);

            g2d.setColor(Color.ORANGE);
            g2d.fillArc(100, 100, 200, 200, 45, 270); // Pac-Man
        }

        private void drawPolygonsDemo(Graphics2D g2d) {
            int[] x = {100, 200, 300, 200, 100};
            int[] y = {200, 100, 200, 300, 300};
            // drawPolygon(xPoints, yPoints, count)
            g2d.setColor(Color.CYAN);
            g2d.fillPolygon(x, y, 5);
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(x, y, 5);
        }

        private void drawG2DDemo(Graphics2D g2d) {
            // Using G2D specific features: Stroke and Gradient
            g2d.setStroke(new BasicStroke(8));
            // GradientPaint paint = new GradientPaint(50, 50, Color.RED, 100, 50, Color.YELLOW);
            // g2d.setPaint(paint);

            float[] fractions = {0.0f, 0.2f, 1.0f};
            Color[] colors = {Color.WHITE, new Color(0, 10, 200, 200), new Color(0, 255, 255, 0)};

            RadialGradientPaint rpaint = new RadialGradientPaint(
                350/2, 250/2, 100, fractions, colors);
            g2d.setPaint(rpaint);
            
            Rectangle2D rect = new Rectangle2D.Double(50, 100, 300, 150);
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
            g2d.drawString("G2D: Gradient + 8px Stroke", 50, 280);
        }

        private void drawPathsDemo(Graphics2D g2d) {
            // Path2D for custom shapes
            Path2D star = new Path2D.Double();
            star.moveTo(150, 50);
            star.lineTo(180, 150);
            star.lineTo(280, 150);
            star.lineTo(200, 210);
            star.lineTo(230, 310);
            star.lineTo(150, 250);
            star.lineTo(70, 310);
            star.lineTo(100, 210);
            star.lineTo(20, 150);
            star.lineTo(120, 150);
            star.closePath();

            // AffineTransform oldTransform = g2d.getTransform();

            Rectangle2D starBounds = star.getBounds2D();
            double centerX = starBounds.getCenterX();
            double centerY = starBounds.getCenterY();

            g2d.rotate(angle, centerX, centerY);

            g2d.setPaint(Color.YELLOW);
            g2d.fill(star);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(star);

            // g2d.setTransform(oldTransform);
        }

        private void drawAnimationDemo(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.fill(new Ellipse2D.Double(ballX, ballY, 30, 30));
            g2d.setColor(Color.BLACK);
            g2d.drawString("Bouncing Ball: Repainting at 60 FPS", 20, 60);
            g2d.drawString("Position: (" + (int)ballX + ", " + (int)ballY + ")", 20, 80);
        }
    }

    public static void main(String[] args) {
        // Run on the Event Dispatch Thread for Swing safety
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new GraphicsDemo();
            }
        });
    }
}

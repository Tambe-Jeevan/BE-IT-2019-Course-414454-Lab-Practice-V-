import java.awt.*;

public class Missile {
    double x, y;
    double vx, vy;
    final double gravity = 0.5;

    public Missile(int startX, int startY, int angleDeg, int power, boolean isLeftToRight) {
        this.x = startX;
        this.y = startY;

        double angleRad = Math.toRadians(angleDeg);
        int direction = isLeftToRight ? 1 : -1;
        this.vx = direction * power * Math.cos(angleRad) / 2;
        this.vy = -power * Math.sin(angleRad) / 2;
    }

    public void update() {
        x += vx;
        y += vy;
        vy += gravity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)x, (int)y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 10, 10);
    }
}

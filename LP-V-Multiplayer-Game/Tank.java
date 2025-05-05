import java.awt.*;

public class Tank {
    int x, y;
    final Color color;

    public Tank(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Move the tank left
    public void moveLeft() {
        if (x > 10) {
            x -= 10;
        }
    }

    // Move the tank right
    public void moveRight() {
        if (x < 1150) {
            x += 10;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 50, 30);
        g.fillOval(x + 15, y - 10, 20, 20); // cannon
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 30);
    }
}

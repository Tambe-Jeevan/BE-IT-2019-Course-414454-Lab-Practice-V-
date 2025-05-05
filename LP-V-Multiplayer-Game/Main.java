public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Tank Battle 🚀");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setResizable(false);
            frame.add(new GamePanel());
            frame.setVisible(true);
        });
    }
}

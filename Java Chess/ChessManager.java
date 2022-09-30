import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChessManager extends JFrame {
    
   public ChessManager() {
      init(); 
   }

   private void init() {

      add(new ChessBoard());

      pack();

      setTitle("Java Chess");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }    
    
   public static void main(String[] args) {     
      EventQueue.invokeLater(() -> {
         ChessManager ex = new ChessManager();
         ex.setVisible(true);
      });
   }
}
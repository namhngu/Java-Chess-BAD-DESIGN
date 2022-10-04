// Nam Nguyen
// Manages the chess application.
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChessManager extends JFrame {

   // Post:
   // Initializes the chess application and board.
   public ChessManager() {
      init(); 
   }

   // Post:
   // Initializes the chess application and board.
   private void init() {

      add(new ChessBoard());

      pack();

      setTitle("Java Chess");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }    

   // Post:
   // Starts the application.
   public static void main(String[] args) {     
      EventQueue.invokeLater(() -> {
         ChessManager ex = new ChessManager();
         ex.setVisible(true);
      });
   }
}
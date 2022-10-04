// Nam Nguyen
// SelectionTool represents the selection tool used to pick up pieces on the board.
import java.awt.event.KeyEvent;
public class SelectionTool extends Sprite {

   // time for the selection tool to blink
   private int blinkTimer = 20;

   // whether the tool is visible or not
   private boolean visible = true;

   // whether a piece has been selected or not
   private boolean selected = false;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //
   // Post:
   // Constructs a selection tool at a specific x,y coordinate
   public SelectionTool(int x, int y) {
      super(x, y, "selectionFrame.png");
   }

   // Pre:
   //    key: the integer representing the key press
   //
   // Post:
   // Changes the display coordinates and appearance of the tool depending on the key press.
   public void keyPressed(int key, boolean pieceUnder) {
      
      if (key == KeyEvent.VK_SPACE && pieceUnder) {
         selected = !selected;
      }
      
      if (!selected) {
         if (key == KeyEvent.VK_LEFT) {
            selected = false;
            addXY(-1,0);
            blinkTimer = 20;
            visible = true;
         }
         
         if (key == KeyEvent.VK_RIGHT) {
            selected = false;
            addXY(1,0);
            blinkTimer = 20;
            visible = true;
         }
         
         if (key == KeyEvent.VK_UP) {
            selected = false;
            addXY(0,-1);
            blinkTimer = 20;
            visible = true;
         }
         
         if (key == KeyEvent.VK_DOWN) {
            selected = false;
            addXY(0,1);
            blinkTimer = 20;
            visible = true;
         }
      }
   }

   // Post:
   // Returns true/false depending on the cycle of blinking
   public boolean cycleBlink() {
      if (!selected) {
         if (blinkTimer == 0) {
            visible = !(visible);
            blinkTimer = 20;
         } else {
            blinkTimer -= 1;
         }
      } else {
         visible = true;
      }
      return visible;
   }
}
import java.awt.event.KeyEvent;
import java.awt.Graphics;
public class SelectionTool extends Sprite {

   private int blinkTimer = 20;
   private boolean visible = true;
   private boolean selected = false;

   public SelectionTool(int x, int y) {
      super(x, y, "selectionFrame.png");
   }
   
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
   
   // Returns true/false depending on the cycle of blinking
   public boolean cycleBlink() {
      if (!selected) {
         if (blinkTimer == 0) {
            visible = !(visible);
            blinkTimer = 20;
         } else {
            blinkTimer += -1;
         }
      } else {
         visible = true;
      }
      return visible;
   }
}
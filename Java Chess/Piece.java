import java.util.*;
import java.awt.event.KeyEvent;
public abstract class Piece extends Sprite {

   private Point origin;
   private char color;
   private String type;
   
   public Piece(int x, int y, String type, char color) {
      super(x, y, type + color + ".png");
      this.origin = new Point(x,y);
      this.color = color;
      this.type = type;
   }
   
   public char getColor() {
      return color;
   }
   
   public String getType() {
      return type;
   }
   
   public void keyPressed(int key) {
      
      if (key == KeyEvent.VK_LEFT) {
         addXY(-1,0);
      }
      
      if (key == KeyEvent.VK_RIGHT) {
         addXY(1,0);
      }
      
      if (key == KeyEvent.VK_UP) {
         addXY(0,-1);
      }
      
      if (key == KeyEvent.VK_DOWN) {
         addXY(0,1);
      }
   }
   
   protected boolean isEmpty(Point coord, Piece[][] pieces) {
      return (inBounds(coord) && (pieces[coord.getY()][coord.getX()] == null));
   }
    
   protected boolean isEnemy(Point coord, Piece[][] pieces) {
      if (inBounds(coord) && !isEmpty(coord, pieces)) {
         return getColor() != pieces[coord.getY()][coord.getX()].getColor();
      }
      return false;
   }

   public void returnToOrigin() {
      coord = new Point(origin.getX(), origin.getY());
   }
   
   public void updateOrigin() {
      if (inBounds(coord)) {
         origin = new Point(coord.getX(), coord.getY());
      }
   }
   
   public Point getOrigin() {
      return origin;
   }
   
   public void flipPoint() {
      origin = origin.update(7 - origin.getX(), 7 - origin.getY());
      coord = coord.update(7 - coord.getX(), 7 - coord.getY());
   }
   
   public abstract ArrayList<Point> getPossibleMoves(Piece[][] pieces);
}
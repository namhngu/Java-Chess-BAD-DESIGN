// Nam Nguyen
// Piece represents a chess piece.
import java.util.*;
import java.awt.event.KeyEvent;
public abstract class Piece extends Sprite {

   // The point where the piece is placed on the board.
   private Point origin;

   // The char representing the color of the piece.
   private char color;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    type: the type of piece
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a piece of a specific color and type at coordinate x and y.
   public Piece(int x, int y, String type, char color) {
      super(x, y, type + color + ".png");
      this.origin = new Point(x,y);
      this.color = color;
   }

   // Post:
   // Returns the representing the color of the piece.
   public char getColor() {
      return color;
   }

   // Pre:
   //    key: the integer representing the key press
   //
   // Post:
   // Changes the display coordinates of the chess piece depending on the key press.
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

   // Pre:
   //    coord: the coordinate you want checked
   //    pieces: the array representing the chess board
   //
   // Post:
   // Returns whether the board space the coordinate specifies is empty of not.
   protected boolean isEmpty(Point coord, Piece[][] pieces) {
      return (inBounds(coord) && (pieces[coord.getY()][coord.getX()] == null));
   }

   // Pre:
   //    coord: the coordinate you want checked
   //    pieces: the array representing the chess board
   //
   // Post:
   // Returns whether the board space the coordinate contains an empty or not.
   protected boolean isEnemy(Point coord, Piece[][] pieces) {
      if (inBounds(coord) && !isEmpty(coord, pieces)) {
         return getColor() != pieces[coord.getY()][coord.getX()].getColor();
      }
      return false;
   }

   // Post:
   // Updates the display coordinate of the piece back to the actual position of the piece.
   public void returnToOrigin() {
      coord = new Point(origin.getX(), origin.getY());
   }

   // Post:
   // Updates the origin of the piece to the display coord.
   public void updateOrigin() {
      origin = new Point(coord.getX(), coord.getY());
   }

   // Post:
   // Returns the origin of the piece.
   public Point getOrigin() {
      return origin;
   }

   // Post:
   // Mirrors the point of the piece to the otherside of the board.
   public void flipPoint() {
      origin = new Point(7 - origin.getX(), 7 - origin.getY());
      coord = new Point(7 - coord.getX(), 7 - coord.getY());
   }

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the "normal" moves that the piece can do, including the ones that may endanger the
   // piece's king.
   public abstract ArrayList<Point> getPossibleMoves(Piece[][] pieces);
}
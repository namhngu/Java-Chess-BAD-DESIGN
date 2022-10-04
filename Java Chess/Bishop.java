// Nam Nguyen
// Bishop represents a bishop piece.
import java.util.*;

public class Bishop extends DirectionalPiece {

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a bishop piece of a specific color at coordinate x and y.
   public Bishop(int x, int y, char color) {
      super(x, y, "bishop", color);
   }

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the moves that the piece can do, including the ones that may endanger the
   // bishop's king.
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] directions = {new Point(-1,-1), new Point(1,-1), new Point(-1,1), new Point(1,1)};

      for (Point direction : directions) {
         ArrayList<Point> diag = new ArrayList<Point>();
         diag = addDirection(direction, pieces, getOrigin(), diag);
         moves.addAll(diag);
      }
      
      return moves;
   }
}
// Nam Nguyen
// Rook represents a rook piece.
import java.util.*;

public class Rook extends DirectionalPiece {

   // Whether the king has moved or not
   private boolean firstMove = true;

   public Rook(int x, int y, char color) {
      super(x, y, "rook", color);
   }

   // Update the origin of the piece to the display coordinate
   @Override
   public void updateOrigin() {
      firstMove = false;
      super.updateOrigin();
   }

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the "normal" moves that the piece can do, including the ones that may endanger the
   // rook's king.
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] directions = {new Point(0,-1), new Point(1,0), new Point(-1,0), new Point(1,0)};

      for (Point direction : directions) {
         ArrayList<Point> diag = new ArrayList<Point>();
         diag = addDirection(direction, pieces, getOrigin(), diag);
         moves.addAll(diag);
      }
      
      return moves;
   }

   // Post:
   // Returns whether the rook has done its first move or not
   public boolean getFirstMove() {
      return firstMove;
   }
}
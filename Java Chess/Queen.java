import java.util.*;
public class Queen extends DirectionalPiece{

   private Piece king;

   public Queen(int x, int y, char color) {
      super(x, y, "queen", color);
      this.king = king;
   }
   
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] directions = {new Point(-1,-1), new Point(1,-1), new Point(-1,1), new Point(1,1), new Point(0,1), new Point(1,0), new Point(0,-1), new Point(-1,0)};
      
      for (int i = 0; i < directions.length; i++) {
         ArrayList<Point> diag = new ArrayList<Point>();
         diag = addDirection(directions[i], pieces, getOrigin(), diag);
         moves.addAll(diag);
      }
      
      return moves;
   }
}
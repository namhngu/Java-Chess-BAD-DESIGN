import java.util.*;

public class Rook extends DirectionalPiece {

   private boolean firstMove = true;
   private Piece king;

   public Rook(int x, int y, char color) {
      super(x, y, "rook", color);
      this.king = king;
   }
   
   public void updateOrigin() {
      firstMove = false;
      super.updateOrigin();
   }

   
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] directions = {new Point(0,-1), new Point(1,0), new Point(-1,0), new Point(1,0)};
      
      for (int i = 0; i < directions.length; i++) {
         ArrayList<Point> diag = new ArrayList<Point>();
         diag = addDirection(directions[i], pieces, getOrigin(), diag);
         moves.addAll(diag);
      }
      
      return moves;
   }
   
   public boolean getFirstMove() {
      return firstMove;
   }
}
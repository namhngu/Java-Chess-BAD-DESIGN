import java.util.*;

public class Pawn extends Piece {

   private boolean firstMove = true;
   private boolean vulnerable = false;
   private Piece king;
   
   public Pawn(int x, int y, char color) {
      super(x, y, "pawn", color);      
      this.king = king;
   }
   
   // need to fix this
   @Override
   public void updateOrigin() {
      if (firstMove && getCoord().difference(getOrigin()).equals(new Point(0, -2))) {
         vulnerable = true;
      }      
      firstMove = false;
      super.updateOrigin();
   }
   
   
   
   // implement en passante
   // make this list based off of array location instead of display location 
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();    
      
      if (isEmpty(coord.sum(0,-1), pieces)) {
         moves.add(coord.sum(0,-1));
         if (firstMove && isEmpty(coord.sum(0,-2), pieces)) {
            moves.add(coord.sum(0,-2));
         }
      }
      
      if (isEnemy(coord.sum(1,-1), pieces)) {
         moves.add(coord.sum(1,-1));
      }
      
      if (isEnemy(coord.sum(-1,-1), pieces)) {
         moves.add(coord.sum(-1,-1));
      }
      return moves;
   }
   
   public ArrayList<Point> getPassantMoves(Piece[][] pieces) {
      ArrayList<Point> specialMoves = new ArrayList<Point>();
      Point l = coord.sum(-1,0);
      Point r = coord.sum(1,0);
      if (isEnemy(l, pieces) && pieces[l.getY()][l.getX()] instanceof Pawn && ((Pawn)pieces[l.getY()][l.getX()]).vulnerable) {
         specialMoves.add(coord.sum(-1,-1));
      }
      
      if (isEnemy(r, pieces) && pieces[r.getY()][r.getX()] instanceof Pawn && ((Pawn)pieces[r.getY()][r.getX()]).vulnerable) {      
         specialMoves.add(coord.sum(1,-1));
      }
      return specialMoves;
   }
   
   public void notVulnerable() {
      vulnerable = false;
   }
}
import java.util.*;
public class King extends Piece {
   
   private boolean firstMove = true;

   public King(int x, int y, char color) {
      super(x, y, "king", color);
   }
   
   public void updateOrigin() {
      firstMove = false;
      super.updateOrigin();
   }
   
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] moveCheckList = {new Point(0,1), new Point(-1,1), new Point(1,1), new Point(0,-1), new Point(-1,-1), new Point(1,-1), new Point(1,0), new Point(-1,0)};
      for (int i = 0; i < moveCheckList.length; i++) {
         moves = checkThenAdd(coord.sum(moveCheckList[i]), pieces, moves);
      }
      return moves;
   }

   private ArrayList<Point> checkThenAdd(Point coord, Piece[][] pieces, ArrayList<Point> moves) {
      if (isEmpty(coord, pieces) || isEnemy(coord, pieces)) {
         moves.add(coord);
      }
      return moves;
   }
   
   public ArrayList<Point> getCastleMoves(Piece[][] pieces) {
      ArrayList<Point> specialMoves = new ArrayList<Point>();     
      if (firstMove) {
         Piece leftRook;
         Piece rightRook;
         if (getColor() == 'W') {
            rightRook = pieces[7][7];
            leftRook = pieces[7][0];
         } else {
            leftRook = pieces[7][0];
            rightRook = pieces[7][7];
         }
         if (checkRookStatus(leftRook) && checkForCastle(new Point(-1, 0), getCoord(), pieces, leftRook)) {
            specialMoves.add(coord.sum(-2,0));            
         }
         if (checkRookStatus(rightRook) && checkForCastle(new Point(1, 0), getCoord(), pieces, rightRook)) {
            specialMoves.add(coord.sum(2,0));
         }
      }
      return specialMoves;
   }
   
   private boolean checkForCastle(Point direction, Point origin, Piece[][] pieces, Piece rook) {
      origin = origin.sum(direction);
      if (pieces[origin.getY()][origin.getX()] == rook) {     
         return true;
      } else if (pieces[origin.getY()][origin.getX()] != null) {
         return false;
      } else {
         return checkForCastle(direction, origin, pieces, rook);
      }
   
   }
   
   private boolean checkRookStatus(Object ob) {
      return (ob instanceof Rook && ((Rook)ob).getColor() == getColor() && ((Rook)ob).getFirstMove());
   } 
}
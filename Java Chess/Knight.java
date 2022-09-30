import java.util.*;

public class Knight extends Piece {

   private Piece king;
   
   public Knight(int x, int y, char color) {
      super(x, y, "knight", color);
      this.king = king;
   }
   
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] moveCheckList = {new Point(-1,2), new Point(1,2), new Point(2,-1), new Point(2,1), new Point(-1,-2), new Point(1,-2), new Point(-2,1), new Point(-2,-1)};
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
}
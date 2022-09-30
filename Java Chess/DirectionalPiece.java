import java.util.*;
public abstract class DirectionalPiece extends Piece {

   public DirectionalPiece(int x, int y, String type, char color) {
      super(x, y, type, color);
   }
   
   protected ArrayList<Point> addDirection(Point dir, Piece[][] pieces, Point step, ArrayList<Point> arrList) {
      Point nxtStep = step.sum(dir);
      if(!isEmpty(nxtStep, pieces)) {
         if(isEnemy(nxtStep, pieces)) {
            arrList.add(nxtStep);
         }
         return arrList;
      } else {
         arrList.add(nxtStep);
         return addDirection(dir, pieces, nxtStep, arrList);
      }
   }
   
   public abstract ArrayList<Point> getPossibleMoves(Piece[][] pieces);
}
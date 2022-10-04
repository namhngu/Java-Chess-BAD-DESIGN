// Nam Nguyen
// DirectionalPiece represents a special group of pieces that can move in lines: diagonal, vertical, horizontal.
import java.util.*;
public abstract class DirectionalPiece extends Piece {

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a DirectionalPiece of a specific color at coordinate x and y.
   public DirectionalPiece(int x, int y, String type, char color) {
      super(x, y, type, color);
   }

   // Pre:
   //    dir: The step to be added in the direction you want
   //    pieces: an array of pieces representing the chess board
   //    step: The current coord you are at
   //    arrList: The ArrayList<Point> of moves
   //
   // Post:
   // Returns an ArrayList<Point> of moves that can be done in specified directions given the board
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

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the moves that the piece can do, including the ones that may endanger the
   // piece's king.
   public abstract ArrayList<Point> getPossibleMoves(Piece[][] pieces);
}
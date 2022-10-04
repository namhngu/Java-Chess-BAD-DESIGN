// Nam Nguyen
// Knight represents a knight piece.
import java.util.*;

public class Knight extends Piece {

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a knight piece of a specific color at coordinate x and y.
   public Knight(int x, int y, char color) {
      super(x, y, "knight", color);
   }

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the moves that the piece can do, including the ones that may endanger the
   // knight's king.
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] moveCheckList = {new Point(-1,2), new Point(1,2), new Point(2,-1), new Point(2,1), new Point(-1,-2), new Point(1,-2), new Point(-2,1), new Point(-2,-1)};
      for (Point point : moveCheckList) {
         moves = checkThenAdd(coord.sum(point), pieces, moves);
      }
      return moves;
   }

   // Pre:
   //    coord: the point in question
   //    pieces:  the array representing the chess board
   //    moves: the list of moves that the piece can do
   //
   // Post:
   // Returns the ArrayList<Point> of moves added with coord if the piece can move to the coordinate
   private ArrayList<Point> checkThenAdd(Point coord, Piece[][] pieces, ArrayList<Point> moves) {
      if (isEmpty(coord, pieces) || isEnemy(coord, pieces)) {
         moves.add(coord);
      }
      return moves;
   }
}
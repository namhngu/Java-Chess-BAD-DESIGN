// Nam Nguyen
// King represents a king piece.
import java.util.*;
public class King extends Piece {

   // Whether the king has moved or not
   private boolean firstMove = true;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a king piece of a specific color at coordinate x and y.
   public King(int x, int y, char color) {
      super(x, y, "king", color);
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
   // king.
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();
      Point[] moveCheckList = {new Point(0,1), new Point(-1,1), new Point(1,1), new Point(0,-1), new Point(-1,-1), new Point(1,-1), new Point(1,0), new Point(-1,0)};
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

   // Pre:
   //    pieces:
   //
   // Post:
   // Returns an ArrayList<Point> of moves possible with the castling rule.
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

   // Pre:
   //    direction: vector of the direction we want to test for castling
   //    origin: the origin of the king
   //    pieces: the array representing the board and its pieces
   //    rook: the rook we are checking for castle
   //
   // Post:
   //
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

   // Pre:
   //    ob: the object we are checking
   //
   // Post:
   // Returns whether the object in question is a rook and is suitable for castling
   private boolean checkRookStatus(Object ob) {
      return (ob instanceof Rook && ((Rook)ob).getColor() == getColor() && ((Rook)ob).getFirstMove());
   } 
}
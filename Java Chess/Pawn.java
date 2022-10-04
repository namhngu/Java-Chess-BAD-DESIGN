// Nam Nguyen
// Pawn represents a pawn piece.
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Pawn extends Piece {

   // Boolean representing whether the pawn has done their first move or not
   private boolean firstMove = true;

   // Boolean representing whether the pawn is a queen or not
   private boolean isQueen = false;

   // Boolean representing whether the pawn is susceptible to en passant or not
   private boolean vulnerable = false;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    color: capitalized character of the first letter of the color (black or white)
   //
   // Post:
   // Creates a pawn piece of a specific color at coordinate x and y.
   public Pawn(int x, int y, char color) {
      super(x, y, "pawn", color);
   }
   
   // Post:
   // Updates the origin of the pawn and checks whether the pawn can be turned into a queen or
   // is susceptible to en passant or not.
   @Override
   public void updateOrigin() {
      if (!isQueen) {
         if (firstMove && getCoord().difference(getOrigin()).equals(new Point(0, -2))) {
            vulnerable = true;
         }
         if (getCoord().getY() == 0) {
            isQueen = true;
            ImageIcon icon = new ImageIcon("Sprites\\queen" + getColor() + ".png");
            image = icon.getImage();
            image = image.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
         }
      }
      firstMove = false;
      super.updateOrigin();
   }



   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of all the "normal" moves that the piece can do, including the ones that may endanger the
   // pawn's king.
   public ArrayList<Point> getPossibleMoves(Piece[][] pieces) {
      ArrayList<Point> moves = new ArrayList<Point>();    
      if (!isQueen) {
         if (isEmpty(coord.sum(0, -1), pieces)) {
            moves.add(coord.sum(0, -1));
            if (firstMove && isEmpty(coord.sum(0, -2), pieces)) {
               moves.add(coord.sum(0, -2));
            }
         }

         if (isEnemy(coord.sum(1, -1), pieces)) {
            moves.add(coord.sum(1, -1));
         }

         if (isEnemy(coord.sum(-1, -1), pieces)) {
            moves.add(coord.sum(-1, -1));
         }
      } else {
         Point[] directions = {new Point(-1,-1), new Point(1,-1), new Point(-1,1), new Point(1,1), new Point(0,1), new Point(1,0), new Point(0,-1), new Point(-1,0)};
         for (Point direction : directions) {
            ArrayList<Point> diag = new ArrayList<Point>();
            diag = addDirection(direction, pieces, getOrigin(), diag);
            moves.addAll(diag);
         }
      }
      return moves;
   }

   // Pre:
   //    pieces: an array of pieces representing the chess board
   //
   // Post:
   // Returns an ArrayList<Point> of moves possible with the en passant rule.
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

   // Pre:
   //    dir: The step to be added in the direction you want
   //    pieces: an array of pieces representing the chess board
   //    step: The current coord you are at
   //    arrList: The ArrayList<Point> of moves
   //
   // Post:
   // Returns an ArrayList<Point> of moves that can be done in specified directions given the board
   private ArrayList<Point> addDirection(Point dir, Piece[][] pieces, Point step, ArrayList<Point> arrList) {
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

   // Post:
   // Makes the pawn not vulnerable to en passant
   public void notVulnerable() {
      vulnerable = false;
   }
}
//Conventions//
//First element of the vector is the value of that square; if 0 value then the
//      Sudoku value has not been determined yet.
//When a Sudoku value is determined there will be 0 and the value in the vector.  The 
//     zero will be updated and the value left in the list
//variable naming convention:
//         classes - first letter is capitalized
//         variables and methods - first letter is not capitalized, every important
//                                   word after that is caplitalized.
//End Conventions//

//Assumptions//
//
//
//End Assumptions//


#ifndef H_Sudoku
#define H_Sudoku

#include "Board.h"
#include "oneCell.h"
#include <fstream>



class Sudoku{
      
      public:
             bool applyRules();
             bool applyRule1();
             bool applyRule2();
             bool applyRule3();
             bool applyRule4();
             bool applyRule5();
             bool applyRule6();
             bool applyRule7();
             bool isValidateSudokuBoard();
             bool solve();
             bool isSolved();
             bool isUnSolveable();
             void generateBoard();             
             oneCell Guess();
             void makeMove(oneCell);
             bool isSoln(int, int);
             bool remove(int, vector<int> &);
             void initCell(int, int);
             void initBoard();
             bool toManyFilled();
             bool notDone();
             void removeElem(oneCell);
             void printCurrentBoard();
             void printCurrentBoardWithCandiates();
             void updateCandiateListWrapper(oneCell);
             void updateCandiateListRow(int, int);
             void updateCandiateListCol(int, int);
             void updateCandiateListQuadrand(int, int);
             int getQuad(int, int);
             ofstream fout;             
             Sudoku(){
                      initCounts();
             }
             void initCounts();
      private:
             Board prevBoard;
             Board currentBoard;
             int wrapperCount;
             int candColCount;
             int candRowCount;
             int candQuadCount;              
              
};



#endif

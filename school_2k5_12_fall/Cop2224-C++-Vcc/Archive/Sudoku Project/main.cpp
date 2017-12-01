#include <cstdlib>
#include <iostream>
#include "Sudoku.h"
#include "Board.h"
#include "oneCell.h"
#include <vector>
#include <fstream>

using namespace std;

bool readInBoard(Sudoku&);
     
ifstream fin;

int main(int argc, char *argv[]){
    
    Sudoku tmpSud;
    
    //try{
        fin.open(argv[1]);
        tmpSud.fout.open("out.txt");
    //}
    //catch(e){
                
    //}
    
    tmpSud.initBoard();
    //tmpSud.printCurrentBoard();
    tmpSud.fout << "After initBoard()" << endl;
    tmpSud.printCurrentBoardWithCandiates();
    
    readInBoard(tmpSud);
    tmpSud.fout << "After readInBoard()" << endl;
    //tmpSud.printCurrentBoard();
    tmpSud.printCurrentBoardWithCandiates();
    tmpSud.solve();
    
    //tmpSud.printCurrentBoard();
    //tmpSud.printCurrentBoardWithCandiates();
    system("PAUSE");
    return EXIT_SUCCESS;
}

bool readInBoard(Sudoku& tmpSud){
     
     int row, col, value, prevI = -1;
     oneCell tmpCell;
     
     while(fin >> row >> col >> value){
               if(prevI != row){
                     //tmpSud.printCurrentBoardWithCandiates();  
               }
               tmpCell.row = row;
               tmpCell.col = col;
               tmpCell.value = value;
               tmpSud.makeMove(tmpCell);    
               
               prevI = row;               
     }
     
     return true;
}

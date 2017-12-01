#include <iostream>
#include <fstream>
#include <string>
using namespace std;

#include "Grocery.h"
#include "Produce.h"

int main() {

	ofstream fout;
	fout.open("grocery.out");
	ifstream fin;
	fin.open("grocery.dat");

	Grocery g1( fin ),g2,g3("G3", 1.25, 25);
	Produce p1( fin ),p2,p3("P3", 2.0, 150, 4.5);
    g2.Extract( fin );
	p2.Extract( fin );

	g3.Insert( fout );
	g2.Insert( fout );
	g1.Insert( fout );

	p3.Insert( fout );
	p2.Insert( fout );
	p1.Insert( fout );

	fin.close();
	fout.close();
    return 0;
}//main
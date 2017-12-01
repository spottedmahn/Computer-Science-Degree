#include "testio.h"

using namespace std;

namespace ioNS{

	TestIO::TestIO(){
	
		//fIn = NULL;
		//fOut = NULL;
	}

	TestIO::TestIO(char * in, char * out){
	
		fIn.open(in, fstream::in);
		
		if(!fIn.is_open()){
		
			cout << "Unable to open Input file" << endl;
		}

		fOut.open(out, fstream::out);

		if(!fOut.is_open()){
		
			cout << "Unable to open Output file" << endl;
		}

	}

	void TestIO::Copy(){
		
		if((fIn.is_open()) && (fOut.is_open())){
			
			char line[400];
			
			while(fIn.good()){
				
				fIn.getline(line, 400);
				fOut << line << endl;
			}
			cout << "Copy complete" << endl;
		}
				
	}
	void TestIO::Print(){
		
		if((fIn.is_open()) && (fOut.is_open())){
			
			char line[400];
			
			fIn.clear(fstream::goodbit);

			fIn.seekg(0, fstream::beg);
			
			string s;

			while(fIn.good()){
				
				fIn >> s;
				cout << s;
			}
			cout << "Print Complete 1" << endl;
			
			fOut.seekg(fstream::beg);

			while(fOut.good()){
				
				fOut.getline(line, 400);
				cout << line << endl;
			}
			cout << "Print Complete 2" << endl;
		}
	}

	void TestIO::FilePointer(){
		
		for(int i=0;i < 2;i++){
			
			for(int j=0;j < 2;j++){

				cout << "Before ABC, fout.tellp() == " << fOut.tellp() << endl;
							
				fOut << "ABC";

				cout << "After ABC, fout.tellp() == " << fOut.tellp() << endl;

				if(j%2 == 1){
					
					cout << "Before endl, fout.tellp() == " << fOut.tellp() << endl;
					fOut << endl;
					cout << "After endl, fout.tellp() == " << fOut.tellp() << endl;
					
				}

				cout << "Before DEF, fout.tellp() == " << fOut.tellp() << endl;
							
				fOut << "DEF";

				cout << "After DEF, fout.tellp() == " << fOut.tellp() << endl;
			}

			cout << "Before GHI, fout.tellp() == " << fOut.tellp() << endl;
						
			fOut << "GHI";

			cout << "After GHI, fout.tellp() == " << fOut.tellp() << endl;
		}
	}
}

int main(int argc, char* argv[]){
	
	using namespace ioNS;

	//Copy Test
	/*
	cout << "Please enter input file name: ";
	char fInC[20];
	cin >> fInC;

	cout << "Please enter output file name: ";
	char fOutC[20];
	cin >> fOutC;

	TestIO test(fInC, fOutC);
*/
	

	TestIO test("in.txt", "pointerTest.txt");
	
	test.FilePointer();
	//test.Copy();
	test.Print();
	return 0;
}


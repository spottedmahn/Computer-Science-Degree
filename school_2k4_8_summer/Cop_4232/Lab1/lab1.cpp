
#include "INCLUDES.h"

#include "agent.h"
#include "instructor.h"
#include "student.h"

using namespace std;
using namespace iomgmt;

void main(int argc, char* argv[]) {

	try{
		ifstream fin;
		ofstream fout;

		theIOMgr.getFileName("Please enter input file name: ");
		theIOMgr.openFile(fin);
		//theIOMgr.openFile(argv[0]);

		theIOMgr.getFileName("Please enter output file name: ");
		theIOMgr.openFile(fout);
		//theIOMgr.openFile(argv[1]);

		string token;

		for (int i=0;i < 3; i++){
			
			fin >> token;
			
			theIOMgr.deltaMargin(fin,-token.length());
			
			if (strcmp(token.c_str(), "Student{") == 0){
				
				Student agt;
				
				fin >> agt;
				fout << agt;
				fout.flush();

			}
			else if (strcmp(token.c_str(), "Instructor{") == 0){
				
				Instructor agt;
				
				fin >> agt;
				fout << agt;
				fout.flush();

			}
			else if (strcmp(token.c_str(), "Agent{") == 0){
				
				Agent agt;
				
				fin >> agt;
				fout << agt;
				fout.flush();

			}
		}
	}
	catch (AppError e){	
		
		cout << "IOError Detected." << endl;

		cout << "Description: " << e.getMsg() << endl;
		
		cout << "Origin: " << e.getOrigin() << endl;
	}
}
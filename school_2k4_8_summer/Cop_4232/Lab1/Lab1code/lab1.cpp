#include "agent.h"
#include "student.h"
#include "instructor.h"


void main() {


	// Construct new conversation
	try
	{
	    ifstream  fin;
		ofstream fout;

		//Open input file
		theIOMgr.getFileName("Enter input file name: ");
		theIOMgr.openFile(fin);
		
		//Create simulation log file
		theIOMgr.getFileName("Enter the output file name : ");
		theIOMgr.openFile(fout);

		string token;
		for (int i=0; i<3; i++)
		{
			// Parse opening token
			fin >> token;
			theIOMgr.deltaMargin(fin,-token.length());
			if (token=="Student{")
			{
				Student agnt;
				fin>>agnt;
				fout<<agnt;
			}
			else if (token=="Instructor{")
			{
				Instructor agnt;
				fin>>agnt;
				fout<<agnt;
			}
			else if (token=="Agent{")
			{
				Agent agnt;
				fin>>agnt;
				fout<<agnt;
			}
		}//end for loop

	}
	catch ( AppError e )
	{		
		cout << "\nIOError Detected!\n------------\n";
		cout << "Description : " << e.getMsg()    << "\n";
		cout << "Origin      : " << e.getOrigin() << "\n\n";
	}
	
	system("PAUSE");
}//main
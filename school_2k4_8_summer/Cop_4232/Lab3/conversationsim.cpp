
#include "simmodels.h"
using namespace simmodels;


void main() {


	// Construct new conversation
	try
	{
		Conversation classroom;
			
		// Output state of the conversation
		classroom.PutState();
        theIOMgr.newLine(simlog);
		// Initialize conversation
		classroom.Initialize(); 
	    theIOMgr.newLine(simlog);
		// Output state of the conversation
		classroom.PutState();
	    theIOMgr.newLine(simlog);
		// begin convesation
		classroom.Simulate();

		// Output state of the conversation
		classroom.PutState();


		// Output conversation postsimulation report
		classroom.WrapUp(); 
	}
	catch ( AppError e )
	{
		
		cout << "\nSimulation Error Detected!\n------------\n";
		cout << "Description : " << e.getMsg()    << "\n";
		cout << "Origin      : " << e.getOrigin() << "\n\n";
	}
	


}//main
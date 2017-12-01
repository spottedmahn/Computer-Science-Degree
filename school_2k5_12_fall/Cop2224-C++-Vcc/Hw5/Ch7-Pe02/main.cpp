#include <cstdlib>
#include <iostream>

using namespace std;

char calculateGrade(int);

int main(int argc, char *argv[])
{
    int tmpI;
    //char tmpC;
    
    cout << "Please enter score: ";
    cin >> tmpI;
    cout << "Your grade is " << calculateGrade(tmpI) << endl;
    system("PAUSE");
    return EXIT_SUCCESS;
}

char calculateGrade(int cScore){
     
     if(cScore >= 90)
               return 'A';           
     else if(cScore >= 80)
          return 'B';     
     else if(cScore >= 70)
          return 'C';
     else if(cScore >= 60)
          return 'D';
     else
         return 'F';
}

#include <iostream>
#include <iomanip>
#include <cctype>

using namespace std; 	//introduces namespace std
int main( void )
{
	int x;
	x = 15 + static_cast<int>(10.5)/2;
	
	cout<<x<<endl;
	cout<<fixed<<showpoint<<setprecision(2);
	cout<<setw(5)<<365.677<<setw(5)<<98.876<<setw(7)<<984.576;
	char ch1,ch2,ch3;
	cin.get(ch1);
	cin.get(ch2);
	cin.get(ch3);
	cout<<ch1<<"  "<<ch2<<" "<<ch3;
	cout<<tolower('$');
		return 0;
}
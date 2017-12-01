#include <iostream>

using namespace std; 	//introduces namespace std
int main( void )
{
	char array[51];
	char discard;
	
	cin >>  array;
	cout << "array: " << array << endl;
	
	cin.get(array,51);
	cin.get(discard);
	cout << "array2: " << array << endl;
	cout << "discard: " << discard << endl;
	discard = discard;
	char temp[8] = "william";
	cout << "This is a test" ;
	return 0;
}

class studentType 
   {
    public:  void setData(string, double, int);
    private:  string name;
   };
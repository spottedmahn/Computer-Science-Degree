/*#include <iostream>

using namespace std; 	//introduces namespace std
int main( void )
{
string s="10001000100010001000100010001000";
 
union {float f;char byte[4];} bit;
bit.byte[0]=bit.byte[1]=bit.byte[2]=bit.byte[3]=1<<3; //1<<3 ="1000"
bit.f = 1<<3;
int s2 = 2<<3;
printf("%f",bit.f);
cout << "s == " << s2;
	cout << "This is a test" ;

	return 0;
}*/

#include <string>
#include <sstream>
#include <iostream>

template <class T>

bool from_string(T& t, const std::string& s, std::ios_base& (*f)(std::ios_base&)) { 
	std::istringstream iss(s);
	return !(iss >> f >> t).fail();
}

/* output: 255 123.456 */
int main() {

	int i;
	float f;

	// the third parameter of from_string() should be  // one of std::hex, std::dec or std::oct 

	if(from_string<int>(i, std::string("ff"), std::hex)) {
		std::cout << i << std::endl; 
	}
	else  {
		std::cout << "from_string failed" << std::endl;
	} 

	if(from_string<float>(f, std::string("123.456"), std::dec)) {
		std::cout << f << std::endl;
	}
	else {
		std::cout << "from_string failed" << std::endl;
	}

	return 0;
} 

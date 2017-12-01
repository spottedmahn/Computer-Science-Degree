#ifndef _testio
#define _testio

#include <stdio.h>
#include <fstream>
#include <iostream>
#include <string>

using namespace std;

namespace ioNS{

	class TestIO{

		private:
			fstream fIn;
			fstream fOut;

		public:
			TestIO::TestIO();
			TestIO::TestIO(char * in, char * out);
			TestIO::main(int argc, char* argv[]);
			void Copy();
			void Print();
			void FilePointer();
	};

}
#endif
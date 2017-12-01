#ifndef _tokenerror
#define _tokenerror

#include "INCLUDES.h"

#include "apperrors.h"

namespace iomgr{


	class TokenError: public AppError{
		
		private:
			static const string TOKENERROR;

		public:
			TokenError();
			TokenError(string msg);
			TokenError(string msg, string origin);
	};
}

#endif
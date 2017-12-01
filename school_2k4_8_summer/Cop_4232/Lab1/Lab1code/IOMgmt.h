#ifndef _IOMGR
#define _IOMGR

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;

#include "apperrors.h"
#define   MARGINSIZE   20

namespace iomgmt {

	class IOError: public AppError 
	{
		public:
		  IOError();               //default constructor
		  IOError(string Msg);             //constructor
		  IOError(string Msg, string Org); //constructor
          //INHERITED: 
            //string getMsg();
            //string getOrigin();
            //void   appendMsg(string Msg);
            //void   appendOrg(string Org);
		
		private:
			static const string IOERROR;
	}; //IOError

	
	class TokenError: public AppError 
	{
		public:
		  TokenError();               //default constructor
		  TokenError(string Msg);             //constructor
		  TokenError(string Msg, string Org); //constructor
          //INHERITED: 
            //string getMsg();
            //string getOrigin();
            //void   appendMsg(string Msg);
            //void   appendOrg(string Org);
		
		private:
			static const string TOKENERROR;
	}; //TokenError

	class IOMgr  
	{
		public:
		   IOMgr();
           virtual void getFileName(string Prompt);
	       virtual void openFile(ifstream& fin) throw( IOError );
		   virtual void openFile(ofstream& fout) throw( IOError );
		   string  getName() const;
		   void    deltaMargin(ofstream& fout, int delta);
		   void    deltaMargin(ifstream& fin, int delta);
		   void    newLine(ofstream& fout);
		   void    pushMargin(ofstream& fout) throw( IOError ); //push cur
		   void    pushToMargin(ofstream& fout, int delta) throw( IOError );
		   void    popMargin() throw( IOError );
		   void    popToMargin(ofstream& fout) throw( IOError );
		   void    toMargin(ofstream& fout);
		   void    clearMargins();
		   void	   advanceMargin(ofstream& fout);
		   friend  ofstream& operator<<( ofstream& fout, IOMgr& mgr);
		protected:
		   long    lineorg;
		   long    curpos;
		   long    margin[MARGINSIZE];
		   int     idx;
		   string  prompt;
		   string  fileName;
	};//IOMgr

	class Tokenizer 
	{
		public:
		Tokenizer();// throw (IOError);      //Constructor (uses theIOMgr (see below))
		bool   More();                    //"false" if EOF
		string Scan();                    //delivers next token:
                                           //!found => returns null string
                                           // found => next token
		void   SetDelims(string symbols) 
			   throw (TokenError);        //sets new delimiters (not null)
		string GetDelims();               //returns current delimiters
		string GetDiscard();              //returns discarded delimiters
                                           //before  token returned by Scan()
		void   Reset();                   //Revert to DELIMS
		void   Putback(string token);     //"undo" token
		
		private:
		ifstream fin;                     //encapsulated input stream
		static   const string DELIMS;     //Blank,Tab,Newline
		string   delims;                  //active delimiters
		string   backbuff;                //pushback buffer
		string   discard;                 //string discarded before next token
		char     NextChar();              //returns next character
		bool     IsDelim(char x);         //tests for a delimiter
	};//Tokenizer

	
	class StringTokenizer 
	{
		public:
		StringTokenizer();
		StringTokenizer(string astr);
		bool   More();                    //"false" if no more tokens
		string Scan();                    //delivers next token:
                                           //!found => returns null string
                                           // found => next token
		void   SetDelims(string symbols)
			   throw (TokenError);        //sets new delimiters (not null)
		string GetDelims();               //returns current delimiters
		string GetDiscard();              //returns discarded delimiters
                                          //before  token returned by Scan()
		void   Reset();                   //Revert to DELIMS
		void   Putback(string token);     //"undo" token
		
		private:
		string   thestr;                  //encapsulated input string
		static   const string DELIMS;     //Blank,Tab
		string   delims;                  //active delimiters
		string   backbuff;                //pushback buffer
		string   discard;                 //string discarded before next token
		char     NextChar();              //returns next character
		bool     IsDelim(char x);         //tests for a delimiter
	};//StringTokenizer

	extern IOMgr theIOMgr;                //available for general use

}//namespace

#endif
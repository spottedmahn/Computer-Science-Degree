

#include <iomanip>
#include <cstdlib>
#include <cctype>
using namespace std;

#include "IOMgmt.h"
using namespace iomgmt;


namespace iomgmt {
    const string IOError::IOERROR = "IOError{}";
	const string TokenError::TOKENERROR = "TokenError{}";
    const string Tokenizer::DELIMS = " \t\n";      //Blank,Tab,Newline
	const string StringTokenizer::DELIMS = " \t";  //Blank,Tab

	IOMgr theIOMgr;                            //iomgmt object

    IOError::IOError()
	{
     //default constructor for AppError is automatically called
    }//default constructor
     
    IOError::IOError(string Msg)
	{
		appendMsg(Msg);
        appendOrg(IOERROR);
    }//constructor
            
    IOError::IOError(string Msg, string Org) : AppError(Msg,IOERROR)
	{
	    appendOrg(Org);
    }//constructor

	TokenError::TokenError()
	{
     //default constructor for AppError is automatically called
    }//default constructor
     
    TokenError::TokenError(string Msg)
	{
		appendMsg(Msg);
        appendOrg(TOKENERROR);
    }//constructor
            
    TokenError::TokenError(string Msg, string Org) : AppError(Msg,TOKENERROR)
	{
	    appendOrg(Org);
    }//constructor



	/////////////////////////////////////////////////////////// IOMgr


		   
	/////////////////////////////////////////////////////////// Tokenizer

	//pass the tokenizer a inputmgr derived class
	Tokenizer::Tokenizer() throw (IOError) 
	{
		IOMgr   FinMgr;
		delims  = DELIMS;
		discard = "";
	
       
		//get the file name from the input mgr
		theIOMgr.getFileName("Enter input file name: ");
		try{
		     theIOMgr.openFile(fin);
		}catch( IOError& e ){
		     e.appendOrg(string(":Tokenizer()"));
		     throw;
		}//catch

	}//Constructor

	bool Tokenizer::More()
	{ 
		//"false" if EOF and putback buffer is empty
		return !fin.eof() || backbuff.length();
	}//More 

	//return the next character if there is on in the backbuffer use it first
	char Tokenizer::NextChar()
	{
		char sym = 0;
   
		if( backbuff.length() )
		{
			sym = backbuff[0];
			backbuff = backbuff.substr(1,backbuff.length()-1);
			return sym;
		}

		if( fin.good() && !sym ) fin.get(sym);
			return sym;
	}//NextChar

	bool Tokenizer::IsDelim(char x)
	{
		for(int i=0; i < delims.length(); i++)
		{
			if( x == delims[i] ) return true;
		}//for
	
		return false;
	}//IsDelim

	string Tokenizer::Scan() 
	{//delivers next token       
		string token = "";
		char  sym;
		
		if( fin.eof() ) 
			return token;
       
		discard = "";      
		sym     = NextChar();
       
		//read in delimiters until first non delimiter
		while( !fin.eof() && IsDelim(sym) ) 
		{
			discard = discard + sym;
			sym     = NextChar();
		}//while 
       
		if( fin.eof() ) 
			return token;

		token = token + sym;
		sym = NextChar();
		//now read in all non delimiters until the first delimiter
		while( !fin.eof() && !IsDelim(sym) )
		{
			token = token + sym;
            sym = NextChar();
		}//while
		
		//put the delimiter back since its part of the next token sequence
		if(!fin.eof()) 
			fin.putback(sym);
       
		return token;
	}//Scan

	void  Tokenizer::SetDelims(string symbols) throw(TokenError)
	{
		if(!symbols.length())
			throw IOError(string("Null delimiter string"),
			              string("Tokenizer.SetDelims"));
		
		delims = symbols;
	}//SetDelims

	string Tokenizer::GetDelims()
	{
		return delims;
	}//GetDelims

	string Tokenizer::GetDiscard()
	{
		return discard;
	}//GetDiscard

	void   Tokenizer::Reset()
	{
		delims = DELIMS;
	}//Reset

	void   Tokenizer::Putback(string token)
	{ //"undo" token
			backbuff = token + backbuff;
	}//Putback  

/////////////////////////////////////////////////////////// StringTokenizer

}//namespace



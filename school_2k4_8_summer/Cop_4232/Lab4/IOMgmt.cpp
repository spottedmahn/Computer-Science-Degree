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
	//const string StringTokenizer::DELIMS = " \t";  //Blank,Tab

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



	///////////////////////////////////////////////////////// IOMgr

    //Default Constructor
	IOMgr::IOMgr ()
	{
		fileName = "";
		prompt   = "";
		lineorg  = 0;
		curpos   = 0;
		idx      = -1;
		for(int i=0; i < MARGINSIZE; i++) margin[i] = 0;
	}

	//get the file name from the input mgr
	void IOMgr::getFileName(string Prompt)
	{   prompt = Prompt;
		for(;;){
		     cout <<  prompt;
		     cin  >>  fileName;
			 if( fileName.length() > 0 ) break;
			 cout << endl << "Null file name! Please re-enter." << endl;
		}//for

	}//end

	void IOMgr::openFile(ifstream& fin) throw( IOError )
	{   
		if( !fileName.length() ) 
			throw IOError(string("File name not defined!"),
			              string("IOMgr::openFile(input)"));

		fin.open(fileName.c_str(), ios::in );
		if( fin.fail() )
			throw IOError(string("Input File Not Found!"),
			              string("IOMgr:openFile(input)"));
	
	}//end

	
	void IOMgr::openFile(ofstream& fout) throw( IOError )
	{   
		if( !fileName.length() ) 
			throw IOError(string("File name not defined!"),
			              string("IOMgr::openFile(output)"));
		fout.open(fileName.c_str(), ios::out);
		if( fout.fail() )
			throw IOError(string("Unable to Create Output file!"),
				          string("IOMgr:openFile(output)"));
	}//end

	string IOMgr::getName() const
	{  
		return fileName;
	}//end


	void    IOMgr::deltaMargin(ofstream& fout, int delta){
		    long diff = curpos - lineorg + delta;
		    if( delta == 0 ) return;
			if( delta < 0  ) {
				  newLine(fout);
				  if( diff < 0 ) diff = 0;
				  fout.seekp(lineorg + diff ,ios::cur);
			}
			else  fout.seekp(delta,ios::cur);
			curpos = fout.tellp();
	}//deltaMargin

	void    IOMgr::deltaMargin(ifstream& fin, int delta){
		    fin.seekg(delta,ios::cur);
			curpos = fin.tellg();
	}//deltaMargin

	void    IOMgr::newLine(ofstream& fout){
		    fout << endl;
			curpos = lineorg = fout.tellp();
	
	}//newLine

	void    IOMgr::toMargin(ofstream& fout){
			curpos = lineorg + margin[idx];
			fout.seekp(curpos);
	}//newLine


	void    IOMgr::pushMargin(ofstream& fout) throw( IOError ){
		    if( ++idx >= MARGINSIZE ) throw IOError(string("Margin Stack Overflow!"),
				                                    string("IOMgr::pushMargin()"));
			curpos = fout.tellp();
			margin[idx] = curpos - lineorg;
	}//pushMargin

	void    IOMgr::pushToMargin(ofstream& fout, int delta) throw( IOError ){
		    if( ++idx >= MARGINSIZE ) throw IOError(string("Margin Stack Overflow!"),
				                                    string("IOMgr::pushToMargin()"));
			curpos = fout.tellp();
			margin[idx] = curpos - lineorg;
			deltaMargin(fout,delta);
	}//pushToMargin

	void    IOMgr::popMargin() throw( IOError ){
		    if( idx < 0 ) throw IOError(string("Margin Stack Underflow!"),
				                         string("IOMgr::popMargin()"));
            curpos = lineorg + margin[idx--];
	}//popMargin

	void    IOMgr::popToMargin(ofstream& fout) throw( IOError ){
		    if( idx < 0 ) throw IOError(string("Margin Stack Underflow!"),
				                         string("IOMgr::popMargin()"));
            curpos = lineorg + margin[idx--];
			fout.seekp(curpos);
	}//popToMargin

	void    IOMgr::clearMargins(){
		    idx = -1;
			for(int i=0; i < MARGINSIZE; i++) margin[i] = 0;
	}//clearMargins

	void	IOMgr::advanceMargin(ofstream& fout) {
			newLine(fout);
			toMargin(fout);
	}

	ofstream&  operator<<( ofstream& fout, IOMgr& mgr){
		    mgr.curpos = fout.tellp();
		    return fout;
	}//operator<<
		   
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

}//namespace



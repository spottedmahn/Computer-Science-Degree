
//orkun
#include "INCLUDES.h"

#include "IOMgmt.h"

using namespace std;

using namespace iomgmt;


namespace iomgmt {

	const string IOError::IOERROR = "IOError{}";
	
	const string TokenError::TOKENERROR = "TokenError{}";
    	
	const string Tokenizer::DELIMS = " \t\n";      //Blank,Tab,Newline
	
	//const string StringTokenizer::DELIMS = " \t";  //Blank,Tab

	IOMgr theIOMgr;                            //iomgmt object

	IOError::IOError(){
	
	//default constructor for AppError is automatically called
	
	}//default constructor
     
	IOError::IOError(string Msg){

		appendMsg(Msg);
		appendOrg(IOERROR);
	}//constructor
            
	IOError::IOError(string Msg, string Org) : AppError(Msg,IOERROR){

	    appendOrg(Org);
	}//constructor

	TokenError::TokenError(){
	
		//default constructor for AppError is automatically called
	
	}//default constructor
     
	TokenError::TokenError(string Msg){

		appendMsg(Msg);
		appendOrg(TOKENERROR);
	
	}//constructor
            
   	TokenError::TokenError(string Msg, string Org) : AppError(Msg,TOKENERROR){
	
		appendOrg(Org);
	
	}//constructor



	/////////////////////////////////////////////////////////// IOMgr

	IOMgr::IOMgr(){
		
		fileName = "";
		prompt = "";
		
		lineorg = 0;
		curpos = 0;

		for(int i=0;i < MARGINSIZE;i++){
			
			margin[i] = 0;
		}

		idx = -1;
	}

	void IOMgr::getFileName(string promptIn) throw (IOError){
		
		prompt = promptIn;

		cout << prompt << endl;

		cin >> fileName;
		
		if(fileName.length() == 0){
			
			cout << "Filename was invalid.  1 to Repeat or 2 to Terminate" << endl;
			int option;
			cin >> option;
			if(option == 1){
				getFileName(promptIn);
			}
			else if(option == 2){
				throw IOError(string("Invalid fileName, user choose to quit"), string("IOMgr::getFileName()"));
			}
		}
		
	}

	void IOMgr::openFile(ofstream& fout) throw (IOError){
		
		fout.open(fileName.c_str());

		if(!fout.is_open()){
			
			throw IOError(string("Unable to open file."), string("IOMgr::openFile"));
		}
	}
	
	void IOMgr::openFile(ifstream& fIn) throw (IOError){
		
		fIn.open(fileName.c_str());

		if(!fIn.is_open()){
			
			throw IOError(string("Unable to open file."), string("IOMgr::openFile"));
		}
	}

	string IOMgr::getName() const{
		
		return fileName;
	}

	void IOMgr::pushMargin(ofstream& fOut) throw(IOError){
	
		curpos = fOut.tellp();

		idx++;
		
		if(idx >= MARGINSIZE){
			
			throw IOError(string("Margin stack overflow"), string("IOMgr::pushMargin()"));
		}

		margin[idx] = curpos - lineorg;
		
	}

	void IOMgr::popMargin() throw(IOError){

		curpos = lineorg + margin[idx];
		idx--;

		if(idx < -1){
			
			throw IOError(string("Margin stack underflow"), string("IOMgr::popMargin()"));
		}
	}

	void IOMgr::newLine(ofstream& fout){
		
		curpos = fout.tellp();
		lineorg = fout.tellp();

		fout << endl;
		fout.flush();

	}
	//Dr. Workman's Code
	/*void IOMgr::deltaMargin(ofstream& fout, int delta){
		
		long diff = curpos - lineorg + delta;
		
		if( delta == 0 ) return;
			
			if( delta < 0  ) {
			
				newLine(fout);
				
				if( diff < 0 ) diff = 0;
				
				fout.seekp(lineorg + diff ,ios::cur);
			}

			else  fout.seekp(delta,ios::cur);
			
			curpos = fout.tellp();
	}//deltaMargin*/
	
	void IOMgr::deltaMargin(ofstream& fout, int delta) throw(IOError){
	
		if(delta == 0){
		
			curpos = fout.tellp();
		}

		else if(delta > 0){
			
			curpos += delta;
			fout.seekp(delta, ios::cur);
		}
		else {
			
			int diff = curpos - delta;

			if(diff < 0){
				
				throw IOError(string("Negative offset"), string("IOMgr::deltaMargin()"));
			}

			newLine(fout);

			fout.seekp(diff, ios::cur);

			curpos = fout.tellp();
		}

	}

	void IOMgr::deltaMargin(ifstream& fin, int delta) throw(IOError){
		
		if((curpos - delta) < 0){
			
			throw IOError(string("BOF potential error"), string("IOMgr::deltaMargin(ifstream)"));
		}

		fin.seekg(delta, ios::cur);
		
		curpos = fin.tellg();
	}

	void IOMgr::advToMargin(ofstream& fout){
		
		newLine(fout);
		
		toMargin(fout);
}

	void IOMgr::toMargin(ofstream &fout){
		
		curpos = lineorg + margin[idx];

		fout.seekp(curpos);
	}

	void IOMgr::pushToMargin(ofstream& fout, int delta) throw (IOError){
		
		curpos = fout.tellp();

		idx++;

		if(idx > MARGINSIZE){
			
			throw IOError(string("Margin stack overflow"), string("IOMgr::pushMargin()"));
		}

		margin[idx] = lineorg;
	}

	void IOMgr::popToMargin(ofstream& fout) throw (IOError){
		
		popMargin();

		fout.seekp(curpos, ios::cur);
	}

	void IOMgr::pushAdvMargin(ofstream& fout) throw (IOError){
		
		pushMargin(fout);
		advToMargin(fout);
	}

	void IOMgr::popAdvMargin(ofstream& fout) throw (IOError){
		
		popMargin();
		advToMargin(fout);
	}

	void IOMgr::clearMargins(){
		
		for(int i=0;i < MARGINSIZE;i++){
			
			margin[i] = 0;
		}

		idx = -1;
	}
	/////////////////////////////////////////////////////////// Tokenizer

	//pass the tokenizer a inputmgr derived class
	Tokenizer::Tokenizer() throw (IOError) {

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

	bool Tokenizer::More(){ 

		//"false" if EOF and putback buffer is empty
		return !fin.eof() || backbuff.length();
	
	}//More 

	//return the next character if there is on in the backbuffer use it first
	char Tokenizer::NextChar(){

		char sym = 0;
   
		if( backbuff.length() ){

			sym = backbuff[0];
			backbuff = backbuff.substr(1,backbuff.length()-1);
			return sym;
		}

		if( fin.good() && !sym ){

			fin.get(sym);
		}
			return sym;
	}//NextChar

	bool Tokenizer::IsDelim(char x){

		for(int i=0; i < delims.length(); i++){

			if( x == delims[i] ){
				
				return true;
			}

		}//for
	
		return false;

	}//IsDelim

	//delivers next token
	string Tokenizer::Scan(){

		string token = "";
		char  sym;
		
		if( fin.eof() ){ 

			return token;
		}
       
		discard = "";      
		sym     = NextChar();
       
		//read in delimiters until first non delimiter
		while( !fin.eof() && IsDelim(sym) ){

			discard = discard + sym;
			sym     = NextChar();
		}//while 
       
		if( fin.eof() ){ 

			return token;
		}

		token = token + sym;
		sym = NextChar();
		
		//now read in all non delimiters until the first delimiter
		while( !fin.eof() && !IsDelim(sym) ){
	
			token = token + sym;
      	      sym = NextChar();
		}//while
		
		//put the delimiter back since its part of the next token sequence
		if(!fin.eof()) 
			fin.putback(sym);
       
		return token;
	}//Scan

	void  Tokenizer::SetDelims(string symbols) throw(TokenError){

		if(!symbols.length()){

			throw IOError(string("Null delimiter string"), string("Tokenizer.SetDelims"));
		}
		
		delims = symbols;

	}//SetDelims

	string Tokenizer::GetDelims(){

		return delims;
	}

	string Tokenizer::GetDiscard(){

		return discard;
	}

	void   Tokenizer::Reset(){

		delims = DELIMS;
	}
	
	//"undo" token
	void   Tokenizer::Putback(string token){
	
		backbuff = token + backbuff;
	}

/////////////////////////////////////////////////////////// StringTokenizer

}//namespace



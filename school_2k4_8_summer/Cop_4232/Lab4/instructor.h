

class Instructor : public Agent{
	
	Instructor();
	Instructor(ifstream& fin) throw(AppError);
	~Instructor();
    void Initialize(MSGPTR );
    void Dispatch  (MSGPTR );
	virtual void Extract(ifstream& fin) throw(AppError);
	virtual void Insert(ofstream &fout);

     protected:
		 virtual void Get(ifstream& fin)     throw(AppError);
         virtual void Put(ofstream &fout);

	private:
		int Prepdelay;
		int gradingDelay;
		Lecture lec;
};

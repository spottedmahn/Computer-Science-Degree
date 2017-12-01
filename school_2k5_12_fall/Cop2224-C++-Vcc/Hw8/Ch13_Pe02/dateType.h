#include <fstream>
#include <iostream>

using namespace std;

class dateType{
      
      public:
             bool setDate(int, int, int);
             int getDay() const;
             int getMonth() const;
             int getYear() const;
             void setDay(int);
             void setMonth(int);
             void setYear(int);                    
             void printDate() const;
             void printDateTwo(ofstream&) const;
             int daysInMonth() const;
             int daysInMonth(int) const;      
             int daysPassed() const;
             int daysRemaining() const;
             void addDays(int);
             dateType(int month = 1, int day = 1, int year = 1900);
             bool isValidDate(int, int, int);
             bool isLeapYear() const;
      private:
              int dMonth;
              int dDay;
              int dYear;
};



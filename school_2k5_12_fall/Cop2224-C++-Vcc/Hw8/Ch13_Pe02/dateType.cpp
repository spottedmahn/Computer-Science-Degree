
#include "dateType.h"

//set date will not allow an invalid date to be set
bool dateType::setDate(int month, int day, int year){
     
     bool isValid = isValidDate(month, day, year);
     
     if(isValid){
         dMonth = month;
         dDay = day;
         dYear = year;
         
         return true;
     }
     else{
          return false;     
     }
}

int dateType::getDay() const{
    return dDay;   
}

int dateType::getMonth() const{
    return dMonth;   
}

int dateType::getYear() const{
    return dYear;   
}
//prints to cout
void dateType::printDate() const{
     
     cout << dMonth << "-" << dDay << "-" << dYear << endl;     
}
//prints to fostream
void dateType::printDateTwo(ofstream& fout) const{
     
     fout << dMonth << "-" << dDay << "-" << dYear << endl;    
}

bool dateType::isValidDate(int monthIn, int dayIn, int yearIn){
     
     if((monthIn < 1) || (monthIn > 12)){
                 return false;           
     }
     
     if((dayIn < 1) || (dayIn > 31)){
               return false;
     }
     
     if(yearIn < 1){
               return false;          
     }
     
     switch(monthIn){
                     //if april, june, september, november
                     case 4:
                     case 6:
                     case 9:
                     case 11:
                          if(dayIn > 30){
                                   return false;
                          }        
                          break;
                     //if feburary
                     case 2:
                          if(dayIn > 28){
                                   return false;         
                          }
                          break;
                     default:
                             break;
     }
     return true;     
}

bool dateType::isLeapYear() const{
     
     if(((dYear % 100) == 0) && ((dYear % 400) != 0)){
                return false;           
     }
     if((dYear % 4) != 0){
              return false;          
     }
     return true;     
}

dateType::dateType(int month, int day, int year){
                       
     bool isValid = isValidDate(month, day, year);
     
     if(isValid){
         dMonth = month;
         dDay = day;
         dYear = year;

     }
}


int dateType::daysInMonth() const{

     switch(dMonth){
         //if april, june, september, november
         case 4:
         case 6:
         case 9:
         case 11:
              return 30;       
         //if feburary
         case 2:
              return 28;
         default:
              return 31;
     }
     return -1;         
}

int dateType::daysInMonth(int tmpI) const{

     switch(tmpI){
         //if april, june, september, november
         case 4:
         case 6:
         case 9:
         case 11:
              return 30;       
         //if feburary
         case 2:
              return 28;
         default:
              return 31;
     }
     return -1;         
}

int dateType::daysPassed() const{
    
    int numOfDays = 0; 
     
     for(int i=1;i <= dMonth;i++){
             if(dMonth == i){
                 numOfDays += dDay;         
             }
             else{
                  numOfDays += daysInMonth(i);
             }
     }
     return numOfDays;
}
int dateType::daysRemaining() const{
     
     return (365 - daysPassed());
}
//add daysIn to current date
void dateType::addDays(int daysIn){
 
     if(daysIn < 1){
               return;
     }
     if((daysInMonth(dMonth) - dDay) > daysIn){
           dDay += daysIn;  
     }
     else{
          int tmpD = dDay - 1, tmpM = dMonth;
          //set day of month to the first of next month
          dDay = 1;
          //if december wrap around
          if(dMonth == 12){
                   dYear++;
                   dMonth = 1;         
          }
          //otherwise increment to next month
          else{
               dMonth++;
          }
          //recursively call addDays with # of days remaingingsmaller # of days to add
          
          int b = daysIn - (daysInMonth(tmpM) - tmpD);
          addDays(daysIn - (daysInMonth(tmpM) - tmpD));         
     }
}

void dateType::setDay(int tmpI){
     dDay = tmpI;
}
void dateType::setMonth(int tmpI){
     dMonth = tmpI;
}
void dateType::setYear(int tmpI){
     dYear = tmpI;    
}             


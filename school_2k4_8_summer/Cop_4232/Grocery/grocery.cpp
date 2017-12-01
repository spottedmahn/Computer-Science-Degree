#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstdio>

using namespace std;

#include "Grocery.h"
#include "Produce.h"

//Grocery methods
    Grocery::Grocery() { 
             name = "undefined"; 
             markup = 1.0; 
             cost = 0; 
    }

    Grocery::Grocery( ifstream& fin ){
             Extract( fin );
    }

    Grocery::Grocery( string Name, double Markup, int Cost ){
             name = Name;
             markup = Markup;
             cost  = Cost;
    }

    string Grocery::getName() { 
           return name; 
    }

	double Grocery::getMarkup() { 
           return markup; 
    }


    int    Grocery::getCost() { 
           return cost;   
    }
    
    int    Grocery::getPrice(){ 
           return (int)(cost * markup + 0.5); 
    }

    void   Grocery::Extract(ifstream& fin ) {
           string opentkn, closetkn;
           fin >> opentkn;
           //error check: opentkn == "Grocery{"
           Get( fin ); 
           fin >> closetkn;
           //error check: closetkn == "}"
    }

     void  Grocery::Insert( ofstream& fout ){
           fout << endl;
           fout << " Grocery{ ";
           Put( fout );
           fout << " } ";
     }

     void  Grocery::Get( ifstream& fin) {
           string label1, label2, label3;
           fin  >> label1 >> name 
                >> label2 >> markup 
                >> label3 >> cost;
           //error checks: label1 == "name:" && 
           //              label2 == "markup:" && 
           //              label3 == "cost:"
     }

     

     void  Grocery::Put ( ofstream& fout){
           fout << " name: " << name 
                << " markup: " << markup 
                << " cost: "  <<  cost;
     }

//Produce methods

     Produce::Produce(){ weight = 0.0; }
     Produce::Produce( ifstream& fin ) {
              Extract( fin );
     }
              
     Produce::Produce( string Name, double Markup, int Cost, double Wt )
                     :Grocery(Name,Markup,Cost), weight(Wt) {
                            
     }
              
     double Produce::getWeight() { 
            return weight; 
     }
              
     int    Produce::getPrice() {
            return (int)( weight * Grocery::getCost() * Grocery::getMarkup() + 0.5);
     }

     void   Produce::Extract(ifstream& fin ){
            string opentkn, closetkn;
            fin >> opentkn;
            //error check: opentkn == "Produce{"
            Get( fin ); 
            fin >> closetkn;
            //error check: closetkn == “}”
     }
              
     void   Produce::Insert( ofstream& fout ){
            fout << endl << "Produce{ ";
            Put( fout );
            fout  <<  " } ";
     }

     void   Produce::Get( ifstream& fin){
            string label1;
            Grocery::Get( fin ); //parse name, markup, cost
            fin  >> label1 >> weight;
            //error checks: label1 == “weight:”
     }

     void   Produce::Put ( ofstream& fout){
            Grocery::Put( fout );
            fout << " weight: " << weight;
     }

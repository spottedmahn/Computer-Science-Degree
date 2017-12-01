

bool compareBoard(board b1, borad b2,int size){
       for(int i =0; i<size;i++){
         for(int j=0; j<size;j++){
                if(b1[i[[j].size() != b2[i[[j].size()){
                    return 1;
                }
                for(int k=0; k<b1[i][j].size();k++){
                    if(b1[i][j][k] != b2[i][j][k]{
                        return 1;
                    }
                }
            }   
        }
        return 0;
}


sturct MakeGuess(board b1){
    
    struct retrnval;
     for(int lstNum = 3; lstNum<12; lstNum ++){  
        for(int i =0; i<size;i++){
             for(int j=0; j<size;j++){
                if(b1[i][j].size() == lstNum){
                    //setstruct   x,y,val and return
                }
             }
         }
    }
}


board MakeBoard(board in){
 
  do while (notdone()){
     int rand1 = rand()%10;
     int rand2 = rand()%10;
     int rand3 = rand()%10;
     
     
     in[rand1][rand2][0] = rand3; //make move
     
     //check board
     
     if(checkboard "bad"){
            
            reset cell
            
        }else if(toManyFilled){
            
            reset cell   
        }
        
    
     
     
        
        
    }
    
    
}




notdoe(board in){

int count = 0;


//caculates rows that have more than 2
for(int i=0; i<size; i++){
  for(int j=0; j<size; j++){
      if(in[i][j][0] != 0){
         count++;   
        }
        if(count >=2){
            break;   
        }   
    
    }   
    
    if(count < 2 ){
        return false;
    }
    count = 0;
}


//caculates cols that have more than 2
count = 0;
for(int i=0; i<size; i++){
  for(int j=0; j<size; j++){
      if(in[j][i][0] != 0){
         count++;   
        }
        if(count >=2){
            break;   
        }   
    
    }   
    
    if(count < 2 ){
        return false;
    }
    count = 0;
}
 

    
    


return true;
}


toManyfilled(){
    
    
}

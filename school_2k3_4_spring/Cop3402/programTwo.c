/********************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

FILE  *ot_fptr,  /* Operation Table File Pointer; points to the file
                    containing the input operation table */
      *in_fptr,  /* Input File Pointer; points to the file containing
                    the input assembly program */
      *out_fptr, /* Output File Pointer; points to the file to contain
                    all the output of the program */
      *fopen();

#define  TRUE          1
#define  FALSE         0
#define  NAME_LENGTH  10  /* maximum number of characters in name  */
#define  BKT_SIZE     26  /* number of entries in bucket directory */

/* an entry in Symbol Table */
struct st_entry {
     char    st_name[NAME_LENGTH + 1];
     char    st_type;  /* R (Relocatable) or A (Absolute) */
     int     st_value;
     struct  st_entry  *st_next;
}*st_new, *st_current, *st_temp, *symtbl[BKT_SIZE];

// set up (char-to-(int)-to-char) conversion table
char *alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

#define  OP_LENGTH  6    /* maximum number of characters in operation */
/* an entry in Operation Table */
struct ot_entry {
     char    ot_mnemonic[OP_LENGTH + 1];
     int     ot_format;  /* 0 (assembler directive), 1 (format 1),
                            2 (format 2), 3 (format 3 or 4) */
     int     ot_priority; /* operation priority */
     struct  ot_entry  *ot_next;
     struct  ot_entry *ot_prev;
}*optbl, *ot_current, *ot_new, *ot_shadow;
 
// address format 4 flag
int add4=0;
/* location counter*/
int locctr;
/* line number */
int lineno;

#define  OPND_LENGTH  50  /* maximum number of characters in operand */
#define  INP_LENGTH   70  /* maximum number of characters in an input
                             assembly line */

// function prototyping
void dir_processing();
void machine_processing();
void init();
void pass1();
void prntinfo();
void ot_insert();
int hash();

/********************************************************************/
main(){

   init();
   pass1();
   prntinfo();

   printf("\n\nPress <ENTER> to continue...");
   getchar();
}

/********************************************************************/
void init(){
	
	char *oper;
    char string[18];
    int address, priority;
    int x=0,y=0;
    
    lineno=0;
    locctr=0;
    
	in_fptr=fopen("input.txt","r");
	if(in_fptr==NULL){
		printf("Unable to locate file <input.txt> for processing.\n");
		printf("Please make sure above file is in this directory.\n\n");
		printf("Press <ENTER> to continue...");
		getchar();
		exit(1);
	}
	else
		printf("[input.txt] file found.\n\n");
	
	out_fptr=fopen("output.txt","w");
	if(out_fptr==NULL)
		printf("File could not be opened.\n");
	else
		printf("[output.txt] file opened.\n\n");
	
	ot_fptr=fopen("optbl.txt","r");
	if(ot_fptr==NULL){
		printf("Unable to locate file <optbl.txt> for processing.\n");
		printf("Please make sure above file is in this directory.\n\n");
		printf("Press <ENTER> to continue...");
		getchar();
		exit(1);
	}
	else{
 		printf("[optbl.txt] file found.   ** loading into [optbl] **\n");
		do{
		   // check for end of input file; break if EOF
  		   if(feof(ot_fptr)) break;
  		   // retrieve first line of input file
  		   fgets(string,18,ot_fptr);
  		   if(feof(ot_fptr)) break;
  		   // if line starts with NEWLINE, there are empty lines at end so break
  		   if(string[0]=='\n') break;
           oper=strtok(string," ");
           address=atoi(strtok(NULL," \n"));
           priority=atoi(strtok(NULL," \n"));
           //printf("**%-7s%2i%5i\n",oper,address,priority);
           
           ot_insert(oper,address,priority);
           y++;
  		}
  		while(!feof(ot_fptr));
        printf("                          ** finished building [optbl] **\n\n");
	}
		
	//initialize symtbl indexes
	for(x=0;x<26;x++){
		symtbl[x]=(struct st_entry *)malloc(sizeof(struct st_entry));
		strcpy(symtbl[x]->st_name,"");	// st_name set to ""
		symtbl[x]->st_type='X';         // st_type set to 'X'
  		symtbl[x]->st_value=0;			// st_value set to 0
		symtbl[x]->st_next=NULL;			// next pointers set to NULL
	}
	return;
}
/********************************************************************/
void ot_insert(char *mnemonic, int address, int priority){
    
    ot_new=(struct ot_entry *)malloc(sizeof(struct ot_entry));
    strcpy(ot_new->ot_mnemonic,mnemonic);
    ot_new->ot_format=address;
    ot_new->ot_priority=priority;
    ot_new->ot_prev=NULL;
    ot_new->ot_next=NULL;
    // optbl is empty, first node in list
    if(optbl==NULL){
        optbl=ot_new;
        return;
    }
    ot_current=optbl;
    // while current priority is larger, go down the list
    while((ot_current!=NULL)&&(ot_current->ot_priority>priority)){
        ot_shadow=ot_current;
        ot_current=ot_current->ot_next;
    }
    // if there is a priority tie, break it with strcmp()
    while((ot_current!=NULL)&&(ot_current->ot_priority==priority)&&(strcmp(mnemonic,ot_current->ot_mnemonic)>0)){
        ot_shadow=ot_current;
        ot_current=ot_current->ot_next;
    }
    if(ot_current==NULL){
        ot_shadow->ot_next=ot_new;
        ot_new->ot_prev=ot_shadow;
        return;
    }
    // this should be the right place
    ot_new->ot_next=ot_current;
    ot_new->ot_prev=ot_current->ot_prev;
    if(ot_current->ot_prev!=NULL) ot_current->ot_prev->ot_next=ot_new;
    ot_current->ot_prev=ot_new;
    if(ot_current==optbl){
        ot_new->ot_next=ot_current;
        ot_current->ot_prev=ot_new;
        optbl=ot_new;        
    }
    return;
}
/********************************************************************/
// hash routine
// hashes NAME to alphabet index of most used CAPITAL character [if 2 --> the lesser]
int hash(char * current){
    char *string=current;
    int *array[26];
    int count,x,y,size,winner;		   // general int counters
    char first,                        // first of top 2 most occurring letter
        second;                        // second of top 2 most occurring letter
    // zero the references array
    for(x=0;x<26;x++)array[x]=0;
    // count capital characters in string
	for(count=0;count<(int)strlen(string);count++){
        switch(string[count]){
                case 'A':array[0]++;break;case 'B':array[1]++;break;case 'C':array[2]++;break;
                case 'D':array[3]++;break;case 'E':array[4]++;break;case 'F':array[5]++;break;
                case 'G':array[6]++;break;case 'H':array[7]++;break;case 'I':array[8]++;break;
                case 'J':array[9]++;break;case 'K':array[10]++;break;case 'L':array[11]++;break;
                case 'M':array[12]++;break;case 'N':array[13]++;break;case 'O':array[14]++;break;
                case 'P':array[15]++;break;case 'Q':array[16]++;break;case 'R':array[17]++;break;
                case 'S':array[18]++;break;case 'T':array[19]++;break;case 'U':array[20]++;break;
                case 'V':array[21]++;break;case 'W':array[22]++;break;case 'X':array[23]++;break;
                case 'Y':array[24]++;break;case 'Z':array[25]++;break;default:break;
        }
    }
    // variables used for finding 1st and 2nd most occurring letters
    first=0;
    size=0;
    // find the two most occurring letters
    for(x=0;x<26;x++){
        y=(int)array[x];y=y/4;
        if(y>=size){
            size=y;
            second=first;
            first=x;
        }
    }
    // decide which of 2 most occuring is the dominant & return "winner"
    if(array[first]>array[second])winner=first;
    else if(array[first]<array[second])
		winner=second;
    else if(array[first]==array[second]){
        if(alphabet[first]>alphabet[second])
			winner=first;
        else
			winner=second;
    }
    return winner;
}
/********************************************************************/
void pass1(){
   int x=0;
   char inp_line[71], *label, *oper, *opnd;

   do{
        if(feof(in_fptr)) break;        
        memset(inp_line,' ',70);
        fgets(inp_line,70,in_fptr);
        if(inp_line[0]=='\n') break;
        /* increment the line number */
        lineno++;
        add4=0;
        if(inp_line[0]!=' ')
           label=strtok(&inp_line[0]," \n");
        else label="";
        if(inp_line[11]!=' ')
           oper=strtok(&inp_line[11]," \n");
        else oper="";
        if(inp_line[18]!=' ')
           opnd=strtok(&inp_line[18]," \n");
        else opnd="";
        // set the format_4 flag if needed
        if(oper[0]=='+'){
           add4=1;
           oper=strtok(&oper[1]," \n");
        }
        ot_current=optbl;
        // find the <oper> to increase priority
        while(strcmp(ot_current->ot_mnemonic,oper)!=0){
           ot_current=ot_current->ot_next;
        }
        // delete the <oper> from linked-list
        if(ot_current==optbl){
           ot_current->ot_next->ot_prev=NULL;
           optbl=ot_current->ot_next;
        }
        else if(ot_current->ot_next==NULL){
           ot_current->ot_prev->ot_next=NULL;
        }
        else{
           ot_current->ot_next->ot_prev=ot_current->ot_prev;
           ot_current->ot_prev->ot_next=ot_current->ot_next;
        }
        // insert <oper> into optbl with increased priority
        ot_insert(ot_current->ot_mnemonic,ot_current->ot_format,ot_current->ot_priority+1);
        // scan for dir_processing <oper>'s
        if((strstr(oper,"START")!=0)||(strstr(oper,"END")!=0)||(strstr(oper,"BYTE")!=0)||
           (strstr(oper,"RESW")!=0)||(strstr(oper,"RESB")!=0)||(strstr(oper,"EQU")!=0)){   
              dir_processing(label,oper,opnd);
        }
        // must be machine_processing <oper>
        else
           machine_processing(label,oper,opnd);
   }
   while(1);
}
/********************************************************************/
void st_insert(char *name, char type, int value){
	
	printf(" --> [%s] goes into symtbl at [%i]\n",name,hash(name));
    // allocate memory for new st_entry
	st_new=(struct st_entry *)malloc(sizeof(struct st_entry));
	strcpy(st_new->st_name,name);
	st_new->st_type=type;
	st_new->st_value=value;
	st_new->st_next=NULL;
    st_current=symtbl[hash(name)];
    while(st_current->st_next!=NULL){
		// new st_entry length is shorter than current
		if(strlen(name)<strlen(st_current->st_next->st_name)){
			// insert into linked-list
			st_new->st_next=st_current->st_next;
			st_current->st_next=st_new;
			return;
		}
		// new ht_entry length is same as current
		// perform second hash; use strcmp to find lessser of 2 and insert as above
		else if(strlen(name)==strlen(st_current->st_next->st_name)){
			if(strcmp(name,st_current->st_next->st_name)<0){
				st_new->st_next=st_current->st_next;
				st_current->st_next=st_new;
				return;
			}
			else{
				st_new->st_next=st_current->st_next->st_next;
				st_current->st_next->st_next=st_new;
				return;
			}
		}
		else st_current=st_current->st_next;
	}
	// new ht_entry belongs at end of linked-list
	st_current->st_next=st_new;
	return;
}
/********************************************************************/
/* This routine is used by pass1 to process assembler directives. */
void dir_processing(char *label, char *oper, char *opnd){
    
    fprintf(out_fptr,"%3i    %04X   %-11s%-7s%-8s\n",lineno,locctr,label,oper,opnd);
   /*The assembler directives, to be handled in this lab, are START, END,
   BYTE, RESB, RESW, and EQU.
   if there is a label at the beginning of the input line {
        put the label in symtbl;
        set different fields for the label (st_value is locctr and
          st_type is R; the only exception is the assembler 
          directive EQU where st_value and st_type for the label
          depend on the operand in the input line; see below)
   */
      if(add4==1) printf("[%-11s+%-6s%-8s] ",label,oper,opnd); else printf("[%-11s%-7s%-8s] ",label,oper,opnd);
      if(strncmp(oper,"START",5)==0){
            st_insert(label,'R',0);
      }
      /*
        Assume that there is a label and that the value in the operand field is zero (0).
        Put the label in symtbl and set different fields for the label.
      */
      else if(strncmp(oper,"END",3)==0){
            if(strcmp(label,"")!=0){
                st_insert(label,'R',locctr);
            }
      }
      /*
        If there is a label at the beginning of the input line, put the label in symtbl and
        set different fields for it.
      */
      else if(strncmp(oper,"BYTE",4)==0){
                   if(strncmp(opnd,"C",1)==0){
                      insert(label,'R',locctr);
                      locctr+=(strlen(opnd)-3);
                   }
                   else{
                      insert(label,'R',locctr);
                      locctr+=((strlen(opnd)-3)/2);
                   }
      }
      /*
        This can be one of two forms:  [LABEL  BYTE  C'...']  or  [LABEL  BYTE  X'...']
        In the first case, each character represent one byte. In the second case, every two hex
        digits represent one byte. In either case, determine number of bytes needed and increment
        locctr accordingly. If the input has X, assume the value will be a multiple of 2.
      */
      else if(strncmp(oper,"RESB",4)==0){
                   st_insert(label,'R',atoi(opnd));
                   locctr+=3;
      }
      /*
        Increment locctr accordingly (assume the operand is an integer).
      */
      else if(strncmp(oper,"RESW",4)==0){
                   st_insert(label,'R',locctr);
                   locctr+=3;
      }
      /*
        Increment locctr accordingly (assume the operand is an integer).
      */
      else if(strncmp(oper,"EQU",3)==0){
                   if(strncmp(opnd,"*",1)==0){
                      st_insert(label,'R',locctr);
                   }
                   else if(strpbrk(opnd,"0123456789")!=NULL){
                       st_insert(label,'A',atoi(opnd));
                   }
                   else{
                       st_current=symtbl[hash(opnd)];
                       while(strcmp(st_current->st_name,opnd)!=0){
                           st_current=st_current->st_next;
                       }                       
                       st_insert(label,st_current->st_type,st_current->st_value);
                   }
      }
      /*
        This will be only one form:    [LABEL  EQU  lblvalue]
        lblvalue can be an integer (such as 1024), another label (a relocatable or an absolute address),
        or * (refers to the current value of location counter; * represents a relocatable address);
        You have to determine the value and the type for the label and put it in symtbl.
      */
      /* In all cases, write a line to output file. */
      if(add4==1){
         oper=strcat("+",oper);
      }
      return;
}

/********************************************************************/

void machine_processing(char *label,char *oper,char *opnd){
   
   if(add4==1) printf("[%-11s+%-6s%-8s] ",label,oper,opnd); else printf("[%-11s%-7s%-8s] ",label,oper,opnd);
   if(add4==1){
      fprintf(out_fptr,"%3i    %04X   %-11s+%-6s%-8s\n",lineno,locctr,label,oper,opnd);
   }
   else fprintf(out_fptr,"%3i    %04X   %-11s%-7s%-8s\n",lineno,locctr,label,oper,opnd);
   
   if(strcmp(label,"")!=0){
      st_insert(label,'R',locctr);
   }
   else printf(" --\n");
   
   ot_current=optbl;
   if(add4==1) locctr+=4;
   else{
      while(strcmp(ot_current->ot_mnemonic,oper)!=0){
         ot_current=ot_current->ot_next;
      }
      locctr+=ot_current->ot_format;
   }
   return;
}
/********************************************************************/
void prntinfo(){

    int i=0;
   /* This routine is used by the main routine after all the input  */
   /* assembly statements have been processed.  This routine writes */
   /* symtbl and optbl to the output file.                          */

   /*Note that when this routine is invoked, the output for the input
   assembly lines has already been written to the output file.
   Write the symtbl to the output file.
   Write the optbl to the output file.
   */
   printf("\n--------------------------------------------------\n");
   printf("%30s%21c\n","Symbol Table",'|');
   printf("--------------------------------------------------\n");
   printf("%-8s%-11s%-13s%-8s%-10s%c\n","Hash","Dominant","Symbol","Symbol","Symbol",'|');
   printf("%-8s%-11s%-13s%-8s%-10s%c\n","Index","Letter","Name","Value","Type",'|');
   printf("--------------------------------------------------\n");
   while(i<(BKT_SIZE))
   {
		st_current=symtbl[i];
		if(st_current->st_next!=NULL)
		{
			printf("%3d%8c        %-10s   %04X%7c\n",i, alphabet[hash(st_current->st_next->st_name)],st_current->st_next->st_name,st_current->st_next->st_value,st_current->st_next->st_type);  
			st_current=st_current->st_next;
		}
		while(st_current->st_next != NULL)
		{
			printf("%19s%-10s   %04X%7c\n","",st_current->st_next->st_name,st_current->st_next->st_value,st_current->st_next->st_type);  				
		st_current=st_current->st_next;
		}
	i++;
	}
	printf("--------------------------------------------------\n\n");
    
    printf("------------------------------\n");
    printf("%22s%9c\n","Operation Table",'|');
    printf("------------------------------\n");
    printf("%9s%9s%11s%2c\n","Operation","Format","Priority",'|');
	printf("------------------------------\n");
	ot_current=optbl;
	while(ot_current->ot_next!=NULL){
	    printf(" %-12s%-8i%-2i\n",ot_current->ot_mnemonic,ot_current->ot_format,ot_current->ot_priority);
	    ot_current=ot_current->ot_next;
	}
}


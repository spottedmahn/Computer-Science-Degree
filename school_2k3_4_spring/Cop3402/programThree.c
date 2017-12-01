/********************************************************************/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>

typedef  char  byte;  /* this means 'byte' is equivalent to 'char' */

FILE  *ot_fptr,   /* Operation Table File Pointer; points to the file
                     containing the input operation table */
      *st_fptr,   /* Symbol Table File Pointer; points to the file
                     containing the input symbol table */
      *in_fptr,   /* Input File Pointer; points to the file containing
                     the input for pass-2 */
      *out_fptr,  /* Output File Pointer; points to the file to contain
                     all the output of the program */
      *temp_fptr, /* Temporary File Pointer; points to the file to
                     temporarily contain part (section exceeding page
                     width) of the output of the program */
      *fopen();

// set up (char-to-(int)-to-char) conversion table
char alphabet[27]="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
int add4=0;

char  HEXDIG[16] = {'0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

byte  RMHBHEX[16] = 
        {'\000', '\001', '\002', '\003', '\004', '\005', '\006', '\007',
         '\010', '\011', '\012', '\013', '\014', '\015', '\016', '\017' };
        /* different values for a byte when a hex digit is placed in
           its right-most half-byte (the four bits in the left-most
           half-byte are set to 0), e.g., 015 = 00001101 = D */
           
byte  BITON[8] = {'\001', '\002', '\004',
                  '\010', '\020', '\040',
                  '\100', '\200' }; /* different values a byte
        can have when only one bit in it is on, i.e., only one bit
        is 1 and the other seven bits are 0 */

byte  BITOFF[8] = {'\376', '\375', '\373',
                   '\367', '\357', '\337',
                   '\277', '\177' }; /* different values a byte
        can have when only one bit in it is off, i.e., only one bit
        is 0 and the other seven bits are 1 */

#define  TRUE          1
#define  FALSE         0

#define  NAME_LENGTH  10  /* maximum number of characters in name  */
#define  BKT_SIZE     26  /* number of entries in bucket directory */
/* an entry in Symbol Table */
struct st_entry {
     char    st_name[NAME_LENGTH + 1];
     char    st_type;  /* R (Relocatable), A (Absolute), 
                          E (Exported - external definition),
                          I (Imported - external reference),
                          C (Control section), N (Not applicable) */
     int     st_value;
     struct  st_entry  *st_next;
};
struct  st_entry  *symtbl[BKT_SIZE], *st_new, *st_current, *st_temp;

#define  OP_LENGTH  6    /* maximum number of characters in operation */
/* an entry in Operation Table */
struct ot_entry {
     char    ot_mnemonic[OP_LENGTH + 1];
     int     ot_format;    /* 0 (assembler directive), 1 (format 1),
                              2 (format 2), 3 (format 3 or 4) */
     int    ot_opcode;    /* operation code */
     int     ot_priority;  /* operation priority */
     struct  ot_entry  *ot_next, *ot_prev;
};
struct  ot_entry  *optbl, *ot_new, *ot_current, *ot_shadow;

#define  MT_SIZE  100  /* maximum number of entries in Modification Table */
/* an entry in Modification Table */
struct mt_entry {
     int     mt_addr;    /* starting address of the field to be modified */
     int     mt_length;  /* length of the field to be modified; number of half-bytes in the field */
     char    mt_sign;    /* + or - : a value is to be added to or subtracted from the indicated field */
     char    mt_name[NAME_LENGTH + 1];  /* symbol whose value will be added or subtracted from indicated field */
};
struct  mt_entry  *modtbl[MT_SIZE], *mt_new;  /* modification table; all the
              modification records are maintained in an array;
              when pass-2 needs to generate a modification record for
              the loader, a new entry is put in the array */
int     mt_count;  /* number of entries currently in modtbl;
              this count is incremented every time you add a new
              entry to modtbl (note that MT_SIZE indicates the maximum
              number of entries and not the actual number of entries,
              i.e., mt_count will most likely be less than MT_SIZE) */

int  locctr;  /* location counter */
int program_counter;
int  lineno;  /* line number */
char sign;

#define  OPND_LENGTH  50  /* maximum number of characters in operand */
#define  INP_LENGTH   70  /* maximum number of characters in an input line */
#define  HEX_LENGTH    5

byte  objcode[4];  /* object code */

int   basereg;  /* base register; this variable is initialized to zero;
        when there is a BASE directive, this variable gets the value;
        when there is a NOBASE directive, this variable is set back to
        zero */

/********************************************************************/

int main()
{
   void  init(), pass2(), copy_temp_file(), prnt_optbl(), prntsymtbl(), prntmodtbl();

   init();
   pass2();
   copy_temp_file();

   prntmodtbl();
   prnt_optbl();
   
   return 0;
}

/********************************************************************/

void init()
{
   // function prototyping
   void st_insert(), prntsymtbl(), ot_insert();
   
   // line of optbl file input
   char inp_line[INP_LENGTH];
   // optbl input arguments
   char mnemonic[7];
   int format;
   int opcode;
   int priority;
   //symtbl input arguments
   char name[11];
   char value_hex[5];
   int value;
   char type[2];
   // general counter
   int x=0;
   
   
   // It initializes the global variables such as lineno.
   lineno=0;
   locctr=0;
   program_counter=0;
   basereg=0;
   sign='+';
   
   // It opens the five files (sets ot_fptr, st_fptr, in_fptr, out_fptr, temp_fptr).
   // file containing the input operation table
   ot_fptr=fopen("optbl.txt","r");
      if(ot_fptr==NULL) printf("File could not be opened.\n");
      //else printf("File found.\n");
   // file containing the input symbol table
   st_fptr=fopen("symtbl.txt","r");
      if(st_fptr==NULL) printf("File could not be opened.\n");
      //else printf("File found.\n");
   // input file
   in_fptr=fopen("input.txt","r");
      if(in_fptr==NULL) printf("File could not be opened.\n");
      //else printf("File found.\n");
   // output file
   out_fptr=fopen("output.txt","w");
      if(out_fptr==NULL) printf("File could not be opened.\n");
      //else printf("File found.\n");
   // temp file
   temp_fptr=fopen("temp.txt","w");
      if(out_fptr==NULL) printf("File could not be opened.\n");
      //else printf("File found.\n");
      
   // It loads optbl (using ot_fptr).  Operation code (value for
   //     ot_opcode) will be typed in the input as it appears on
   //     page 496.  Note that using %x to read a value for ot_opcode
   //     won't work.  So, you have to read the value (into a temporary
   //     variable) and then put this value into the one byte of
   //     ot_opcode.
   
   while(fgets(inp_line,INP_LENGTH+1,ot_fptr)!=NULL){
      sscanf(inp_line,"%s %d %x %d",mnemonic,&format,&opcode,&priority);
      printf("[%-6s] [%-1i] [%-3i] [%-2i]\n",mnemonic,format,opcode,priority);
      ot_insert(mnemonic,format,opcode,priority);
   }
   printf("\n");
   
   // It puts the registers and their values (shown below) in symtbl; set
   //     st_type to N for registers.  Insert the registers in the order
   //     given below:
   //                 register  value
   //                    A        0
   //                    X        1
   //                    L        2
   //                    PC       8
   //                    SW       9
   //                    B        3
   //                    S        4
   //                    T        5
   //                    F        6
   //initialize symtbl
   for(x=0;x<BKT_SIZE+1;x++){
      symtbl[x]=(struct st_entry *)malloc(sizeof(struct st_entry));
      strcpy(symtbl[x]->st_name,"");	// st_name set to ""
      symtbl[x]->st_type='X';           // st_type set to 'X'
      symtbl[x]->st_value=0;			// st_value set to 0
      symtbl[x]->st_next=NULL;			// next pointers set to NULL
   }
   // insert registers and values into symtbl
   st_insert("A",'N',0);
   st_insert("X",'N',1);
   st_insert("L",'N',2);
   st_insert("PC",'N',8);
   st_insert("SW",'N',9);
   st_insert("B",'N',3);
   st_insert("S",'N',4);
   st_insert("T",'N',5);
   st_insert("F",'N',6);
   printf("\n");
   
   // It loads symtbl (using st_fptr).  Each entry in the input will
   //     contain a name, a value and a type.  The hash value for a name
   //     is not in the input, so you need to apply the hash function
   //     again.  The values (read for st_value) will be typed in the
   //     input in hex; use %x to read a value for st_value.
   while(fgets(inp_line,INP_LENGTH+1,st_fptr)!=NULL){
      sscanf(inp_line,"%s %*x %s",name,type);
      strtok(inp_line," ");
      strcpy(value_hex,(char *)strtok(NULL," "));
      sscanf(value_hex,"%x",&value);
      //printf("[%s] [%i] [%s]\n",name,value,type);
      st_insert(name,type[0],value);
   }
   printf("\n");
   
   // It writes symtbl to output file (out_fptr).
   prntsymtbl();
   // It initializes modtbl.
   for(x=0;x<MT_SIZE+1;x++){
      modtbl[x]=(struct mt_entry *)malloc(sizeof(struct mt_entry));
      modtbl[x]->mt_addr=0;
      modtbl[x]->mt_length=0;
      modtbl[x]->mt_sign='X';
      strcpy(modtbl[x]->mt_name,"");
   }
   
}/* end of init */
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
void st_insert(char *name, char type, int value){
	
	printf("symtbl insert of [%s] with type [%c] and value [%i]\n",name,type,value);
    // allocate memory for new st_entry
	st_new=(struct st_entry *)malloc(sizeof(struct st_entry));
	strcpy(st_new->st_name,name);
	st_new->st_type=type;
	st_new->st_value=value;
	st_new->st_next=NULL;
    st_current=symtbl[hash(name)];
    printf("insert hashes to location [%i] of symtbl array\n",hash(name));
    while(st_current->st_next!=NULL){
		// new st_entry length is shorter than current
		if(strlen(name)<strlen(st_current->st_next->st_name)){
			// insert into linked-list
			st_new->st_next=st_current->st_next;
			st_current->st_next=st_new;
			return;
		}
		// new st_entry length is same as current
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
	// new st_entry belongs at end of linked-list
	st_current->st_next=st_new;
	return;
}
/********************************************************************/
void st_find(char *name){
    st_current=symtbl[hash(name)];
    while(strcmp(name,st_current->st_name)!=0)
        st_current=st_current->st_next;
}
/********************************************************************/
void ot_insert(char *mnemonic, int format, int opcode, int priority){
    
    printf(" optbl [re]insert of [%s] with format [%i] and opcode [%i] and priority [%i]\n",mnemonic,format,opcode,priority);
    // create new ot_entry and initialize values
    ot_new=(struct ot_entry *)malloc(sizeof(struct ot_entry));
    strcpy(ot_new->ot_mnemonic,mnemonic);
    ot_new->ot_format=format;
    ot_new->ot_opcode=opcode;
    ot_new->ot_priority=priority;
    ot_new->ot_prev=NULL;
    ot_new->ot_next=NULL;
    
    // get pointer ready
    ot_current=optbl;
    // if optbl is empty, set head pointer to new ot_entry
    if(ot_current==NULL){
        optbl=ot_new;
        return;
    }
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
    // this should be the right place for insertion
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
void ot_find(char *value){
   ot_current=optbl;
   while(strcmp(value,ot_current->ot_mnemonic)!=0)
      ot_current=ot_current->ot_next;
}
/********************************************************************/
void mt_insert(int location, int length, char sign, char *name){
	
	mt_new=(struct mt_entry *)malloc(sizeof(struct mt_entry *));
	mt_new->mt_addr=location;
	mt_new->mt_length=length;
	mt_new->mt_sign=sign;
	strcpy(mt_new->mt_name,name);

	modtbl[mt_count]=mt_new;
	mt_count++;

}
/********************************************************************/
void pass2()
   /* This routine is used by the main routine.  It performs Pass-2 */
   /* of the assembler.                                             */
{
   int location=0,format=0;

   char  location_hex[HEX_LENGTH+1],    /*  6  */
         inp_line[INP_LENGTH + 1],  /* input line              70  */
         temp[INP_LENGTH + 1],      /* input line              70  */
         label[NAME_LENGTH + 1],    /* label in the input      10  */
         oper[OP_LENGTH + 1],       /* operation in the input  6   */
         opnd[OPND_LENGTH + 1];     /* operand in the input    50  */
   void  fmt0(), fmt1(), fmt2(), fmt3(), fmt4();
   
   program_counter=0;
   
   // put heading for output of input data
   fprintf(out_fptr,"%-7s%-6s%-33s%s\n","Line","Loc","Source Statement","Length");
   fprintf(out_fptr,"-----------------------------------------------------\n");
   /* read each input line and perform pass-2 */
   while(fgets(&inp_line[0], (INP_LENGTH+HEX_LENGTH), in_fptr)!=NULL){
        
        /* increment the line number */
        lineno++;
        add4=0;
		
		/* get different parts of the input  */
        strcpy(temp,inp_line);
        inp_line[strlen(inp_line)-1]='\0';
        printf("------------------------------------\n");

		printf("\n\nProgram Counter is [%i]\n\n",program_counter);

        printf("*** parsing [%s]\n",inp_line);
        //printf("             1234567890123456789012345678901234567890\n");
        strcpy(location_hex,(char *)strtok(&inp_line[0]," \n"));
        sscanf(location_hex,"%x",&location);
		
		locctr=location;
        printf("[%04X] ",locctr);
        

        strcpy(inp_line,temp);
        inp_line[strlen(inp_line)-1]='\0';
        if(inp_line[HEX_LENGTH+1]!=' ')
           strcpy(label,(char *)strtok(&inp_line[HEX_LENGTH+1]," \n"));
        else strcpy(label,"");
        printf("[%s] ",label);
        
        strcpy(inp_line,temp);
        inp_line[strlen(inp_line)-1]='\0';
        if(inp_line[HEX_LENGTH+NAME_LENGTH+2]!=' '){
           strcpy(oper,(char *)strtok(&inp_line[HEX_LENGTH+NAME_LENGTH+2]," \n"));
           if(oper[0]=='+'){
              add4=1;
              strcpy(oper,(char *)strtok(&oper[1]," \n"));
           }
        }
        else strcpy(oper,"");
        printf("[%s] ",oper);
        
        strcpy(inp_line,temp);
        inp_line[strlen(inp_line)-1]='\0';;
        if((inp_line[HEX_LENGTH+NAME_LENGTH+OP_LENGTH+3]!=' ')&&(strlen(inp_line)>23))
           strcpy(opnd,(char *)strtok(&inp_line[HEX_LENGTH+NAME_LENGTH+OP_LENGTH+3]," \n"));
        else strcpy(opnd,"");
        printf("[%s]\n",opnd);
        
        //printf("[%i] [%s] [%s] [%s]\n",locctr,label,oper,opnd);
             /* Note: get_tokens will also assign a value to the global
                variable locctr.  The value in the input is in hex 
                characters (base 16) and get_tokens must convert it to
                decimal (base 10) */
        
        // increment the priority of the operation as you did in Lab #2;
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
        //printf(" optbl delete of [%s] [%i] [%i] [%i]\n",ot_current->ot_mnemonic,ot_current->ot_format,ot_current->ot_opcode,ot_current->ot_priority);
        // insert <oper> into optbl with increased priority
        ot_insert(ot_current->ot_mnemonic,ot_current->ot_format,ot_current->ot_opcode,ot_current->ot_priority+1);
        
        // get format of oper
        ot_find(oper);
		format=ot_current->ot_format;
		
		if((format==3)&&(add4==1)) format++;
		if((format==1)||(format==2)||(format==4)) program_counter+=ot_current->ot_format;
		
		
		// write a line to output file (out_fptr);
        if((format!=0)){
			if(add4!=1)
				fprintf(out_fptr,"%4i   %-6s%-11s%-10s%-15s%i\n",lineno,location_hex,label,oper,opnd,format);
			else
				fprintf(out_fptr,"%4i   %-6s%-11s+%-9s%-15s%i\n",lineno,location_hex,label,oper,opnd,format);
		}
		else if(format==0){
			if(add4!=1)
				fprintf(out_fptr,"%4i   %-6s%-11s%-10s%-15s",lineno,location_hex,label,oper,opnd);
			else
				fprintf(out_fptr,"%4i   %-6s%-11s+%-9s%-15s",lineno,location_hex,label,oper,opnd);
		}
		
		ot_find(oper);
        //printf("switching on format [%i]\n",ot_current->ot_format);
        switch(ot_current->ot_format){
             case 0:
                  fmt0(label,oper,opnd);
                  break;
             case 1:
                  fmt1(label,oper,opnd);
                  break;
             case 2:
                  fmt2(label,oper,opnd);
                  break;
             case 3:
                  if (add4!=1)
                       fmt3(label,oper,opnd);
                  else
                       fmt4(label,oper,opnd);
                  break;
             default:
                  break;
        }
        // clear the arguments
        memset(inp_line,' ',strlen(inp_line));

		

        printf("\ncompleted one line of PASS-2 input\n\n");

		
   }

   return;
}

/********************************************************************/

void fmt0(char *label, char *oper, char *opnd)
   /* This routine is used by pass2 to process format 0 (assembler */
   /* directives).                                                 */
{	
   int x=0, temp_int=0, format=0;
   char temp[2];
   char *lhs_opnd, *rhs_opnd;
   void prnt_bits(), prnt_bits_to_file(), prnt_temp(), num_to_20();
   
   printf("execution of format [0]\n");
   objcode[3]=objcode[2]=objcode[1]=objcode[0]=0;
   // The assembler directives are START, END, BYTE, WORD, RESB, RESW,
   // EQU, BASE, NOBASE, CSECT, EXTDEF, and EXTREF.  The directives that
   // pass2 needs to process (i.e., pass1 does not take care of them
   // completely) are:
   // BYTE:  We may have two forms as shown by the following examples:
   //            LABEL  BYTE  C'ALI'
   //            LABEL  BYTE  X'09AF'
   //        In the first case, every character must be put in one byte of
   //        objcode, i.e., objcode[0] = 'A', objcode[1] = 'L', ...
   //        In the second case, every two hex must be put in one byte
   //        of objcode (note that the input is in character form).  so,
   //        for the above example, objcode[0] will contain bits 00001001,
   //        and objcode[1] will contain bits 10101111.  Assume there will
   //        be an even number of hex digits.
   //        In both cases, assume that we won't need more than the four
   //        bytes in objcode.
   //
   
   if(strcmp(oper,"BYTE")==0){
      if(opnd[0]=='C'){
         printf("\n[%s]\n",opnd);
         for(x=0;x<(int)(strlen(opnd)-3);x++){
            objcode[x]=opnd[x+2];
            printf("CHAR[%c]--> BIT STRING[ ",objcode[x]);prnt_bits(objcode[x]);printf(" ]\n");
         }
         printf("\n\n");
		 locctr+=(int)(strlen(opnd)-3);
		 format=(int)(strlen(opnd)-3);

      }
      else{
         printf("\n[%s]\n",opnd);
         for(x=0;x<((int)(strlen(opnd)-3)/2);x++){
            strncpy(temp,&opnd[(2*x+2)],2);
            printf("STRING[%s]-->",temp);
            sscanf(temp,"%x",&temp_int);
            printf(" INT[%i]-->",temp_int);
            objcode[x]=temp_int;
            printf(" CHAR[%c]-->",objcode[x]);
            printf(" BIT STRING[ ");prnt_bits(objcode[x]);printf(" ]\n");
            memset(temp,' ',strlen(temp));
         }
         printf("\n");
		 locctr+=(int)((strlen(opnd)-3)/2);
		 format=(int)((strlen(opnd)-3)/2);

      }
   }
   // WORD:  We may have three forms:
   //            LABEL  WORD  53
   //            LABEL  WORD  extref
   //            LABEL  WORD  extref-extref
   //        In the first case, put the value in the objcode.  In the
   //        second case, add one entry to modtbl.  In the third case, add
   //        two entries to modtbl.
   else if(strcmp(oper,"WORD")==0){

      // first case is a integer
	   if(isdigit(opnd[0])!=0){
		   /*
		   for(x=0;x<(int)strlen(oper);x++){
			  objcode[x]=oper[x];
		   }
		   */
		   num_to_20(atoi(opnd));
		   objcode[0]=objcode[1];
		   objcode[1]=objcode[2];
		   objcode[2]=objcode[3];
	   }
	   else if(strstr(opnd,"-")==NULL){
		  mt_insert(locctr,6,'+',opnd);
	   }
	   else{
		  lhs_opnd=strtok(opnd,"-");
		  rhs_opnd=strtok(NULL,"\n");
		  
		  mt_insert(locctr,6,'+',lhs_opnd);
		  mt_insert(locctr,6,'-',rhs_opnd);
	   }
	format=3;
   }

   // NOBASE:  basereg = 0.
   else if(strcmp(oper,"NOBASE")==0){
      basereg=0;
	  format=0;
   }
   
   // BASE:  basereg = the value of the label.
   else if(strcmp(oper,"BASE")==0){
      st_find(opnd);
      basereg=st_current->st_value;
      printf("Setting basreg to %i\n",st_current->st_value);
	  format=0;
   }
   else
	   format=0;

   //increase PC for fomat 0 instructions
   program_counter+=format;

	// write a line to output file (out_fptr);
	

   // In all cases of format 0, write one line to temporary output
   // file (temp_fptr).  The line number is written to temporary output 
   // file.  Also objcode (if there is objcode for the input line) in
   // hex form and bit form is written to temporary output file.
   prnt_temp(format);
	fprintf(out_fptr,"%i\n",format);
   
   return;
}/* end of fmt0 */

/********************************************************************/

void fmt1(char *label, char *oper, char *opnd)
   /* This routine is used by pass2 to process format 1. */
{
   void prnt_bits(), prnt_bits_to_file();
   printf("execution of format [1]\n");
   
   ot_find(oper);
   
   // Put operation code in objcode[0].
   objcode[0]=ot_current->ot_opcode;
   
   // Write one line to temporary output file (temp_fptr).  The line
   // number and objcode (in hex form and bit form) are written to
   // temporary output file.
   fprintf(temp_fptr,"%4i   %02X             ",lineno,ot_current->ot_opcode);
   prnt_bits_to_file(objcode[0]);
   fprintf(temp_fptr,"\n");
   
   return;
}/* end of fmt1 */

/********************************************************************/

void fmt2(char *label, char *oper, char *opnd)
   /* This routine is used by pass2 to process format 2. */
{
   void prnt_bits(), prnt_bits_to_file(), two_hex_to_byte(), prnt_temp();
   
   char lhs_str[2]="  ", rhs_str[2]="  ";
   int lhs_int, rhs_int;

   printf("execution of format [2]\n");
   ot_find(oper);
   // Put operation code in objcode[0].
   objcode[0]=ot_current->ot_opcode;
   
   // Put the two registers (their numbers) in objcode[1].  Note that
   // if the registers are, for example, B and S, symtbl shows B is 3
   // and S is 4.  So, for this example, objcode[1] = 00110100.
   if(strlen(opnd)==1){
      printf("opnd is [%s]",opnd);
      printf("\nregister as a string is [%s]\n", opnd);
      st_find(opnd);
      lhs_int=st_current->st_value;
      rhs_int=0;
      two_hex_to_byte(HEXDIG[lhs_int],'0',&objcode[1]);
   }
   else if(strlen(opnd)==3){
      printf("opnd is [%s]\n",opnd);
      strncpy(lhs_str,opnd,1);
      lhs_str[1]='\0';
      printf("first register as a string is [%s]\n", &lhs_str);
      strncpy(rhs_str,&opnd[2],1);
      rhs_str[1]='\0';
      printf("second register as a string is [%s]\n", &rhs_str);
      // get lhs value
      printf("[%s] hashes to [%i]\n",lhs_str,hash(lhs_str));
	  st_find(lhs_str);
      printf("name of matching record is [%s]\n",st_current->st_name);
      lhs_int=st_current->st_value;
      // get rhs value
      printf("[%s] hashes to [%i]\n",rhs_str,hash(rhs_str));
	  st_find(rhs_str);
      printf("name of matching record is [%s]\n",st_current->st_name);
      rhs_int=st_current->st_value;
      two_hex_to_byte(HEXDIG[lhs_int],HEXDIG[rhs_int],&objcode[1]);
   }
   // Write one line to temporary output file (temp_fptr).  The line
   // number and objcode (in hex form and bit form) are written to
   // temporary output file.
   prnt_temp(2);

   return;
}/* end of fmt2 */

/********************************************************************/

   // For formats 3 and 4, you need to set the flag bits.  These were
   // explained in class (e.g., see your notes for Section 2.2.1) and are
   // repeated below:
   //   -------------------------------------------------------------
   //                                  || n | i || x || b | p || e ||
   //   -------------------------------++---+---++---++---+---++---++
   //   immediate addressing (#)       || 0 | 1 ||
   //   indirect addressing (@)        || 1 | 0 ||
   //   otherwise (simple addressing)  || 1 | 1 ||
   //   -------------------------------++---+---++---++---+---++---++
   //   indexed addressing (...,X)     ||       || 1 ||
   //   otherwise                      ||       || 0 ||
   //   -------------------------------++---+---++---++---+---++---++
   //   base relative addressing       ||            || 1 | 0 ||
   //   PC relative addressing         ||            || 0 | 1 ||
   //   otherwise (direct addressing)  ||            || 0 | 0 ||
   //   -------------------------------++---+---++---++---+---++---++
   //   format 3                       ||                     || 0 ||
   //   format 4                       ||                     || 1 ||
   //   -------------------------------------------------------------

   //   As you may remember, the SIC/XE implementation puts the flag
   //   bits "n" and "i" in the first byte of the object code, i.e.,
   //   these two flag bits replace the last two bits of opcode.  We 
   //   will not implement indirect addressing and immediate addressing,
   //   and will leave the opcode byte unchanged, i.e., we won't change
   //   the last two bits of opcode.

/********************************************************************/

void fmt3(char *label, char *oper, char *opnd)
   /* This routine is used by pass2 to process format 3. */
{
   void prnt_bits(), prnt_bits_to_file(), turnon_bit(), turnoff_bit(), num_to_12(), prnt_temp();
   
   int indexed;
   int displacement,target_address;
   //char temp[5];
   
   printf("execution of format [3]\n");
   ot_find(oper);
   
   // Put operation code in objcode[0].
   objcode[0]=ot_current->ot_opcode;
   
   // (Remember that we attempt PC relative first and then base 
   //  relative if needed.)
   // Assume that all the displacements for format 3 are positive.
   // Assume displacement cannot be greater than 1023, i.e., after
   //      computing the displacement for PC relative, if it is greater
   //      than 1023, attempt base relative.
   // In all the following cases, you need to set the flag bits in
   //      objcode and put the appropriate values in objcode.
   
   // determine the addressing mode
   indexed=0;
   if(strcmp(opnd,"")==0){
      objcode[1]=0;
      objcode[2]=0;
      fprintf(temp_fptr,"%4i   %02X ",lineno,ot_current->ot_opcode);
      fprintf(temp_fptr,"%02X ",objcode[1]);
      fprintf(temp_fptr,"%02X       ",objcode[2]);
      prnt_bits_to_file(objcode[0]);
      prnt_bits_to_file(objcode[1]);
      prnt_bits_to_file(objcode[2]);
      fprintf(temp_fptr,"\n");
      return;
   }
   if(strstr(opnd,",X")!=NULL){
      opnd[strlen(opnd)-2]='\0';
      indexed=1;
   }
      
   st_find(opnd);
   target_address=st_current->st_value;
   displacement=(target_address-program_counter);
   printf("\nTarget Address[%i] - Program Counter[%i] = Displacement[%i]\n",target_address,program_counter,displacement);
   objcode[1]=objcode[2]=0;
   
   if(displacement<1024){
   // program-counter relative addressing
      if(indexed==1){
         st_find("X");
         // PROBLEMS HERE
		 //printf("\n\n***[%i]***\n\n",st_current->st_value);
		 num_to_12(displacement+st_current->st_value);
		 turnon_bit(&objcode[1],8);
		 turnon_bit(&objcode[1],6);
      }
      else{
         num_to_12(displacement);
		 turnon_bit(&objcode[1],6);
      }
	program_counter+=3;
   }
   
   else if(displacement>1023){
   // base relative addressing
      displacement=target_address-basereg;
	  printf("\n\ndisplacement [%i]=TA[%i]-B[%i]\n\n",displacement,target_address,basereg);
      if(indexed==1){
         st_find("X");
         // PROBLEMS HERE
         num_to_12(displacement+st_current->st_value);
		 turnon_bit(&objcode[1],8);

		 printf("\n\nobjcode[1]=[%i or %X]\n\n",objcode[1],objcode[1]);
      }
      else{
         // PROBLEMS HERE
         num_to_12(displacement);
		 turnon_bit(&objcode[1],7);;
      }
	  program_counter+=4;
   }

   // Write one line to temporary output file (temp_fptr).  The line
   // number and objcode (in hex form and bit form) are written to
   // temporary output file.
   prnt_temp(3);
   /*
   fprintf(temp_fptr,"%4i   %02X ",lineno,objcode[0]);
   strncpy(temp,&objcode[1],4);
   if((int)objcode[1]>=0) fprintf(temp_fptr,"%02X ",objcode[1]);
   else fprintf(temp_fptr,"%02X ",objcode[1]-4294967040);
   if((int)objcodefprintf(temp_fptr,"%02X       ",objcode[2]);
   
   prnt_bits_to_file(objcode[0]);
   prnt_bits_to_file(objcode[1]);
   prnt_bits_to_file(objcode[2]);
   fprintf(temp_fptr,"\n");
   */
   return;
}/* end of fmt3 */
/********************************************************************/
void num_to_12(int value){
	
	void turnon_bit();

	sign='+';

	if(value<0){
		value=abs(value);
		sign='-';
		turnon_bit(&objcode[1],4);
	}
	if(value>=1024){
		if(value==1024) value=0;
		else value=value%1024;
		turnon_bit(&objcode[1],3);
	}
	if(value>=512){
		if(value==512) value=0;
		else value=value%512;
		turnon_bit(&objcode[1],2);
	}
	if(value>=256){
		if(value==256) value=0;
		else value=value%256;
		turnon_bit(&objcode[1],1);
	}
	objcode[2]=value;
}

/********************************************************************/

void fmt4(char *label, char *oper, char *opnd)
   /* This routine is used by pass2 to process format 4. */
{
   int indexed=0,displacement=0;

   void prnt_bits(), prnt_bits_to_file(), turnon_bit(), turnoff_bit(), num_to_20(), prnt_temp();
   
   printf("execution of format [4]\n");
   // Put operation code in objcode[0].
   objcode[0]= objcode[1]= objcode[2]= objcode[3]=0;
   ot_find(oper);
   objcode[0]=ot_current->ot_opcode;
   
   // For format 4, in general, we add an entry to modtbl if the operand
   //      in the instruction is relocatable or it is external reference.
   
   // In all the following cases, you need to set the flag bits in
   //      objcode and put the appropriate values in objcode. 
   
    // set indexing flag
    if(strstr(opnd,",X")!=NULL){
      opnd[strlen(opnd)-2]='\0';
      indexed=1;
	}
	// If indexed addressing: (LBL,X)
	if(indexed == 1){
		st_find("X");		
		st_temp=st_current;
	
		st_find(opnd);
		
		if((st_current->st_type == 'R' ) || (st_current->st_type == 'I')){
			mt_insert(locctr+1, 5, sign, opnd);
		}
		else
			num_to_20(st_current->st_value + st_temp->st_value);
		turnon_bit(&objcode[1],8);
		turnon_bit(&objcode[1],5);
	}
	// If not indexed addressing: (LBL)
	else{
		st_find(opnd);
		
		if((st_current->st_type == 'R' ) || (st_current->st_type == 'I')){
			mt_insert(locctr+1, 5, sign, opnd);
		}
		else num_to_20(st_current->st_value);
		turnon_bit(&objcode[1],5);
	}
	
	
   
   // Write one line to temporary output file (temp_fptr).  The line
   // number and objcode (in hex form and bit form) are written to
   // temporary output file.
   prnt_temp(4);

   return;
}/* end of fmt4 */

/********************************************************************/
void num_to_20(int value){
	
	void turnon_bit();
	
	sign='+';

	if(value<0){	//negative
		sign='-';
		value=abs(value);
		turnon_bit(&objcode[1],4);
	}
	if(value>=262144){
		value=value%262144;
		turnon_bit(&objcode[1],3);
	}
	if(value>=131072){
		value=value%131072;
		turnon_bit(&objcode[1],2);
	}
	if(value>=65536){
		 value=value%65536;
		turnon_bit(&objcode[1],1);
	}
	if(value>=32768){
		value=value%32768;
		turnon_bit(&objcode[2],8);
	}
	if(value>=16384){
		value=value%16384;
		turnon_bit(&objcode[2],7);
	}
	if(value>=8192){
		value=value%8192;
		turnon_bit(&objcode[2],6);
	}
	if(value>=4096){
		 value=value%4096;
		turnon_bit(&objcode[2],5);
	}
	if(value>=2048){
		value=value%2048;
		turnon_bit(&objcode[2],4);
	}
	if(value>=1024){
		 value=value%1024;
		turnon_bit(&objcode[2],3);
	}
	if(value>=512){
		value=value%512;
		turnon_bit(&objcode[2],2);
	}
	if(value>=256){
		 value=value%256;
		turnon_bit(&objcode[2],1);
	}
	objcode[3]=value;
}
/********************************************************************/
void copy_temp_file()
   /* This routine is used by the main routine after all the input   */
   /* have been processed.  This routine copies the temporary output */
   /* file (temp_fptr) into the main output file (out_fptr).         */
{
   char inp_line[INP_LENGTH];
   // Close the temporary output file (it was opened for "w").
   fclose(temp_fptr);
   // Now open this file for "r".
   temp_fptr=fopen("temp.txt","r");
   // Copy this file (temp_fptr) to the main output file (out_fptr).
   fseek(out_fptr,0,SEEK_END);

   fprintf(out_fptr,"\n\nLine   Objcode in Hex   Objcode in Binary\n");
   fprintf(out_fptr,"------------------------------------------\n");
   while(fgets(&inp_line[0],INP_LENGTH+1,temp_fptr)!=NULL){
      fprintf(out_fptr,"%s",inp_line);
   }
   return;
}/* end of copy_temp_file */
/********************************************************************/
void prnt_temp(int length){

	int x, needed_bit_lines;
	
	fprintf(temp_fptr,"%4i   ",lineno);
	needed_bit_lines=0;
	if(((length==1)||(length==2))&&(objcode[0]==0)){
		fprintf(temp_fptr,"\n");
		return; 
	}
	for(x=0;x<length;x++){
		if(objcode[x]<0) fprintf(temp_fptr,"%02X ",objcode[x]-4294967040);
		else fprintf(temp_fptr,"%02X ",objcode[x]);
		needed_bit_lines++;
	}
	for(x=0;x<(((4-length)*3)+3);x++){
		fprintf(temp_fptr," ");
	}
	for(x=0;x<needed_bit_lines;x++){
		prnt_bits_to_file(objcode[x]);
	}
	fprintf(temp_fptr,"\n");
	return;
}/********************************************************************/
void prnt_optbl(){
   fprintf(out_fptr,"\n\n         Operation Table\n");
   fprintf(out_fptr,"Operation   Format   Opcode   Priority\n");
   fprintf(out_fptr,"---------------------------------------\n");
   ot_current=optbl;
   do{
	  fprintf(out_fptr,"%-14s%-9i%02X       %3i\n",ot_current->ot_mnemonic, ot_current->ot_format, ot_current->ot_opcode, ot_current->ot_priority);
	  ot_current=ot_current->ot_next;
   }
   while(ot_current->ot_next!=NULL);
   return;

}
/********************************************************************/
void prntsymtbl(){

    int i=0;
   /* This routine is used by the main routine after all the input  */
   /* assembly statements have been processed.  This routine writes */
   /* symtbl and optbl to the output file.                          */

   fprintf(out_fptr,"%30s\n","Symbol Table");
   fprintf(out_fptr,"-----------------------------------------------\n");
   fprintf(out_fptr,"%-8s%-11s%-13s%-8s%-10s\n","Hash","Dominant","Symbol","Symbol","Symbol");
   fprintf(out_fptr,"%-8s%-11s%-13s%-8s%-10s\n","Index","Letter","Name","Value","Type");
   fprintf(out_fptr,"-----------------------------------------------\n");
   while(i<(BKT_SIZE))
   {
		st_current=symtbl[i];
		if(st_current->st_next!=NULL)
		{
			fprintf(out_fptr,"%3d%8c        %-10s   %04X%7c\n",i, alphabet[hash(st_current->st_next->st_name)],st_current->st_next->st_name,st_current->st_next->st_value,st_current->st_next->st_type);  
			st_current=st_current->st_next;
		}
		while(st_current->st_next != NULL)
		{
			fprintf(out_fptr,"%19s%-10s   %04X%7c\n","",st_current->st_next->st_name,st_current->st_next->st_value,st_current->st_next->st_type);  				
		st_current=st_current->st_next;
		}
	i++;
	}
	fprintf(out_fptr,"\n");
	return;
}
/********************************************************************/
void prntmodtbl(){
	int x;
	
	fprintf(out_fptr,"\n\n    Modification Table\n");
	fprintf(out_fptr,"Address  Length  Sign  Name\n");
	fprintf(out_fptr,"----------------------------\n");
	for(x=0;x<mt_count;x++){
		fprintf(out_fptr,"  %04X%6i%7c%10s\n",modtbl[x]->mt_addr, modtbl[x]->mt_length, modtbl[x]->mt_sign, modtbl[x]->mt_name);
	}

}
/********************************************************************/
void prnt_bits(onebyte)
   /* This routine prints all the bits in a byte. */
   byte  onebyte;
{
   int   i,
         is_bit_on();

   for ( i = 8;  i > 0;  i-- )
        if ( is_bit_on(onebyte, i) ){
             printf("1");
             if(i==1);
             else printf("-");
        }
        else{
             printf("0");
             if(i==1);
             else printf("-");
        }
}/* end of prnt_bits */
/*******************************************************************/
void prnt_bits_to_file(onebyte)
   /* This routine prints all the bits in a byte. */
   byte  onebyte;
{
   int   i,
         is_bit_on();

   for ( i = 8;  i > 0;  i-- )
        if ( is_bit_on(onebyte, i) ){
             fprintf(temp_fptr,"1");
             if(i==1) fprintf(temp_fptr,"  ");
             else fprintf(temp_fptr,"");
        }
        else{
             fprintf(temp_fptr,"0");
             if(i==1) fprintf(temp_fptr,"  ");
             else fprintf(temp_fptr,"");
        }
}/* end of prnt_bits */
/*******************************************************************/
int is_bit_on(onebyte, bitpos)
   /* This routine returns whether or not the bit at position */
   /* bitpos in the byte onebyte is 1.                        */
   byte  onebyte;
   int   bitpos;
{
   byte  b;
   int   i;

   /* get the bit at bitpos all the way to the right of the byte */
   b = onebyte >> (bitpos - 1);
   /* make sure the remaining bits are zero (note that '\001' is */
   /* '00000001')                                                */
   b = b & '\001';
   i = b; /* change b to integer (this is not really needed) */
   return(i); /* note that we could have returned b */

}/* end of is_bit_on */

/*******************************************************************/

void two_hex_to_byte(lmhb, rmhb, byte_ptr)
   /* This routine takes two hex characters and puts them in a     */
   /* byte.  For example, it takes the hex characters 'D' and '7'  */
   /* and puts them in a byte to have 'D7' in the byte.            */

   char  lmhb, /* left-most half-byte  */
         rmhb; /* right-most half-byte */
   byte  *byte_ptr;
{
   int   k;

   /* find which hex lmhb is */
   for ( k = 0;   HEXDIG[k] != lmhb;   ++k )
        ;
   /* put the lmhb in the byte */
   *byte_ptr = RMHBHEX[k];
   *byte_ptr = *byte_ptr << 4;

   /* find which hex rmhb is */
   for ( k = 0;   HEXDIG[k] != rmhb;   ++k )
        ;
   /* put the rmhb in the byte */
   *byte_ptr = *byte_ptr | RMHBHEX[k];

}/* end of two_hex_to_byte */
/*******************************************************************/

void turnon_bit(byte_ptr, bitpos)
   /* This routine sets the bit at position bitpos in a byte to 1. */
   byte  *byte_ptr;
   int   bitpos;
{

   *byte_ptr = *byte_ptr | BITON[bitpos - 1];

}/* end of turnon_bit */

/*******************************************************************/

void turnoff_bit(byte_ptr, bitpos)
   /* This routine sets the bit at position bitpos in a byte to 0. */
   byte  *byte_ptr;
   int   bitpos;
{
   *byte_ptr  = *byte_ptr & BITOFF[bitpos - 1];

}/* end of turnoff_bit */

/*******************************************************************/
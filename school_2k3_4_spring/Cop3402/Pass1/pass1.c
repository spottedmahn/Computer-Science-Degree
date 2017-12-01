//Lasheree McFarlane
//Michael DePouw
//Pass 1 

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h> 

#define MNEMONIC_OPCODE_LENGTH 10
#define HEX_OPCODE_LENGTH 3
#define SIZE 100
#define INP_LENGTH 70
#define DIG_FACTOR 2
#define LET_FACTOR 3 

FILE *input, //Input file pointer
     *output, *outInterFile, //Output file pointer
  *fopen(); 

/********************************************************************/ 

struct optabEntry{
 char mnemonicOpcode[MNEMONIC_OPCODE_LENGTH +1];
 int instFormat;
 char hexOpcode[HEX_OPCODE_LENGTH +1 ];
}*otTemp; 

struct symtabEntry {
 char name[MNEMONIC_OPCODE_LENGTH];
    int  value;
 char type; /* R (Relocatable) or A (Absolute)*/
         /* E (Export EXTREF) or I (Import EXTDEF)*/
    int  length;  /* For RESB, RESW, BYTE, WORD*/
 struct symtabEntry *next;
}*st_tmp; 

/********************************************************************/ 

struct optabEntry opTab[SIZE];
struct symtabEntry *syTab[SIZE];
char *inputfile, *outputfile; 

int locctr; //location counter, incremented for by the appropriate
            // value for each instuction.
int lineno; //line number that is incremented by 1 for each input
            // assembly language line.
int otCnt;  //number of actual entries in operation table 

int NUM_OF_OPCODES;

/********************************************************************/ 

//Declarations
void oTable(); //construction of operation table
void syTable(); //construction of the symbol table
void pass1();
int check(char *name);
int hash(char *key);
void init();
void insert(int index, char *key, char, int);
void dirProcessing(char *lab, char *op, char *opd);
void macProcessing(char *lab, char *op, char *opd);
int isSpaceBetween(char *, int start, int end);
struct optabEntry *searchOT(char *name); //search operation table for opcodes
struct symtabEntry *searchST(char *name, int *index); //search the symbol table 

/********************************************************************/ 

int main()
{
 oTable();
 init();
 pass1();
 syTable(); 

 printf("\nThe operation table is found in OpTable.txt\n");
 printf("The intermediate file is found in InterFile.txt\n");
 printf("The symbol table is found in SyTable.txt\n\n"); 


 return 0;
} 

/********************************************************************/
//construct the operation code table
void oTable()
{ 

 char mCode[MNEMONIC_OPCODE_LENGTH +1], hCode[HEX_OPCODE_LENGTH +1 ];
 int j, info; 

 inputfile = "opcodes.txt";
 input=fopen(inputfile, "r"); 

    //read from input file into the opCode table.
  NUM_OF_OPCODES = 0;
  while(fscanf(input, "%s%d%s", &mCode, &info, &hCode)!=EOF){
		strcpy(opTab[NUM_OF_OPCODES].mnemonicOpcode, mCode);
		opTab[NUM_OF_OPCODES].instFormat = info;
		strcpy(opTab[NUM_OF_OPCODES].hexOpcode, hCode); 

  NUM_OF_OPCODES++;
 }
 fclose(input);
 otCnt = NUM_OF_OPCODES; 

 //Write to output file containing the operation table
 outputfile="OpTable.txt";
 output=fopen(outputfile, "w");
 fprintf(output, "\n\tOPERATION TABLE\n\n");
 fprintf(output, "Index\tOperation\tFormat\tHexDec.\n");
 for(j=0; j<NUM_OF_OPCODES;j++)
   fprintf(output,"%d\t%s\t\t%d\t%s\n", j, opTab[j].mnemonicOpcode,opTab[j].instFormat,opTab[j].hexOpcode);
 fclose(output); 

} 


/********************************************************************/
void init()
{
//Performs the basic memory allocation needed to start a symbol hashtable
//with the given  size
 int i; 

 lineno = 0;
 locctr = 0000; 

 *syTab=calloc(SIZE, sizeof(struct symtabEntry *));
        for (i=0; i<SIZE; i++){
  syTab[i]=NULL;
 }
} 

/********************************************************************/
//Reads line from the assembly code and divide them into tokens
//and writes to the intermediate file
void pass1()
{
 struct optabEntry *current; 

 char inpLine[INP_LENGTH + 1]; //input assembly line
 char label[MNEMONIC_OPCODE_LENGTH]; //label in the input
 char mCode[MNEMONIC_OPCODE_LENGTH]; //operation in the input
 //operand in the input, this will contain everything after
 //the operation in the input
 char operand[MNEMONIC_OPCODE_LENGTH + 8];
 char infile[20];
 char c;
 int status; //1 = true and 0 = false
 //int spaceflag; //1=true and 0 = false
 int i; 

 //Gets the source file from the user
 printf("\nPlease enter the name of your source file.\n");
 printf("If it is a text file, add '.txt' as the extention.\n");
 scanf("%s", &infile);
        input=fopen(infile, "r"); 

 outputfile ="InterFile.txt";
 outInterFile=fopen(outputfile, "w"); 

 fprintf(outInterFile,"\n\tTHE INTERMEDIATE FILE\n\n");
 fprintf(outInterFile, "Line\tLoc\tSource Statement\n");
 fprintf(outInterFile, "----\t---\t-------------\n"); 

 //read each input assembly line and perform pass-1
 while(fgets(inpLine, INP_LENGTH, input) != NULL){
  printf("%s\n",inpLine);
  ++lineno; //increment the line number 

  //If input is a comment
  if(inpLine[0] == '.')
   fprintf(outInterFile, "%d\t%s", lineno, inpLine);
  else{
   if(!isspace(inpLine[0])){
    //get different parts of the input and print to file with label
    sscanf(inpLine, "%s%s%s", &label, &mCode, &operand);
    //printing line to intermediate file
    fprintf(outInterFile, "%d\t%d\t%s", lineno,locctr, inpLine);
    //checking for + before operator
    if(mCode[0] == '+'){
     status = check(mCode + 1);
     //printf("mCode[0] == +, status = %d\n", status);
     //if assembler directive
     if(status ==  1)
      dirProcessing(label, mCode, operand);
     else
      macProcessing(label, mCode, operand);
    }
    else{
     //printf("mCode[0] != +, status = %d\n", status);
     status = check(mCode);
     //if assembler directive
     if(status ==  1)
      dirProcessing(label, mCode, operand);
     else
      macProcessing(label, mCode, operand);
    }
   }
   else{
    //get different parts of the input and print to file
    //without label
    sscanf(inpLine, "%s%s", &mCode, &operand);
    label[0] = '\0';
	status = check(mCode);
    //if assembler directive
     if(status ==  1)
      dirProcessing(label, mCode, operand);
     else
      macProcessing(label, mCode, operand);
    fprintf(outInterFile, "%d\t%d\t%s", lineno, locctr,inpLine); 

   }
   //check for invalid operation code
   //checking for + before operator
   if(mCode[0] == '+' || mCode[0] == '%'){
    current = searchOT(mCode + 1);
	//if(current!=NULL)
	//	printf("top: current -> instFormat == %d\n",current->instFormat);
    status = check(mCode);
    //printf("mCode[0] == +, status = %d\n", status);
    //if assembler directive
    if((current == NULL) && (status == 0))
     fprintf(outInterFile,"  ****ERROR: Invalid Opcode %s\n", mCode);
   }
   else{
    current = searchOT(mCode);
    status = check(mCode);
    //printf("mCode[0] == +, status = %d\n", status);
    //if assembler directive
    if((current == NULL) && (status == 0))
     fprintf(outInterFile,"  ****ERROR: Invalid Opcode %s\n", mCode);
   }
   //check for invalid operation code modifier
   c = inpLine[8];
   if(c != '+' && c != ' ')
    fprintf(outInterFile,"  ****ERROR: Invalid Opcode Modifier %c\n", c); 

   //check for invalid operand modifier
   c = inpLine[16];
   if(c != '#' && c != '@' && c != ' ')
    fprintf(outInterFile,"  ****ERROR: Invalid Operand Modifier %c\n", c); 

   //check for invalid label
   i=0;
   if(!isupper(label[i]))
    if(label[0] != '\0')
     fprintf(outInterFile,"  ****ERROR: Invalid Label %s\n", label);
   for(i=1; i< (int) strlen(label); i++){
    if(!isupper(label[i]) && !isdigit(label[i]))
     fprintf(outInterFile,"  ****ERROR: Invalid Label %s\n", label);
    break;
   } 

/*
   //Other error check
 //  int len = strlen(operand) + 17;
   for(i=len; i<35; i++){
       if(isalnum(inpLine[i]))
                   fprintf(outInterFile,"  ****ERROR: Undetermined\n");
       break;
       
   }
*/
  }
 } 

 fclose(input);
 fclose(outInterFile);
} 

/********************************************************************/ 

//This routine is used by pass1 to process assembler directives
void dirProcessing(char *lab, char *op, char *opd)
{
  int hashIndex = hash(lab); 

  if(searchST(lab, &hashIndex) != NULL)
   fprintf(outInterFile, "  ****ERROR: Duplicate Label %s\n", lab); 

 //start directive
 if(strncmp(op,"START",5) == 0){
  insert(hashIndex, lab, 'R', atoi(opd));
    }
 //end directive
    else if(strncmp(op,"END",3) == 0){
  if(lab[0] != '\0'){
   insert(hashIndex, lab, 'R', locctr);
        }
    }
 //byte directive
    else if(strncmp(op,"BYTE",4) == 0){
  if(strncmp(opd,"C",1) == 0){
   insert(hashIndex, lab, 'R', locctr);
            locctr += (strlen(opd)-3);
        }
        else{
   insert(hashIndex, lab, 'R', locctr);
            locctr+=((strlen(opd)-3)/2);
        }
    }
    //reserve byte directive
 else if(strncmp(op,"RESB",4) == 0){
  insert(hashIndex, lab, 'R', atoi(opd));
        locctr+=3;
    }
 //resereve word directive
    else if(strncmp(op,"RESW",4) == 0){
  insert(hashIndex, lab, 'R', locctr);
        locctr+=3;
    }
 //equ directive
    else if(strncmp(op,"EQU",3) == 0){
  //if * in operand insert value with locctr
  if(strncmp(opd,"*",1) == 0){
   insert(hashIndex, lab, 'R', locctr);
        }
        //if number in operand insert that number
  else if(strpbrk(opd,"0123456789")!=NULL){
   printf("enter an equ with numbers, label == %s\n", lab);
   insert(hashIndex, lab, 'A', atoi(opd));
  }
  //else find entry in symtbl for opd and insert label
        else{
   int isPlus;
   int isMinus;
   int i;
   //deteremining if opd is an expression or not
   for(i=0;i<(int)strlen(opd);i++){
    if(opd[i] == '+'){
     isPlus = 1;
     //break
     i=strlen(opd);
    }
    else if(opd[i] == '-'){
     isMinus = 1;
     //break
     i=strlen(opd);
    }
    else{
     isPlus = 0;
     isMinus = 0;
    }
   }
   //expression
   if(isPlus){
    char plus[] = "+";
    char *s1 = strtok(opd, plus),*s2 = strtok(NULL, plus);
    int hashIndex_s1 = hash(s1), hashIndex_s2 = hash(s2);
    struct symtabEntry *tmpST1 = searchST(s1, &hashIndex_s1);
    struct symtabEntry *tmpST2 = searchST(s2, &hashIndex_s2);
    if((tmpST1 -> type == 'R') && (tmpST2 -> type == 'R')){
     fprintf(outInterFile,"******** ERROR:  Illegal Expression in Operand Field\n");
    }
    else{
     int tmpI = tmpST1 -> value + tmpST2 -> value;
     hashIndex = hash(lab);
     insert(hashIndex, lab, 'A', tmpI);
    }
   }
   else if(isMinus){
    char minus[] = "-";
    char *s1 = strtok(opd, minus),*s2 = strtok(NULL, minus);
    int hashIndex_s1 = hash(s1), hashIndex_s2 = hash(s2);
    struct symtabEntry *tmpST1 = searchST(s1, &hashIndex_s1);
    struct symtabEntry *tmpST2 = searchST(s2, &hashIndex_s2);
    //printf("tmpST1 -> name == %s, s1 == %s\n",tmpST1->name, s1);
    if((tmpST1 -> type == 'A') && (tmpST2  -> type == 'R')){
     fprintf(outInterFile,"******** ERROR:  Illegal Expression in Operand Field\n");
    }
    else{
     int tmpI = tmpST1 -> value - tmpST2 -> value;
     hashIndex = hash(lab);
     insert(hashIndex, lab, 'A', tmpI);
     //printf("inserted, label == %s, hashIndex == %d\n",lab, hashIndex);
    }
   }
   //only operand
   else{
    int tmpHashIndex = hash(opd);
    printf("opd == %s, tmpHashIndex == %d \n",opd, tmpHashIndex);
    st_tmp = searchST(opd, &tmpHashIndex);
    insert(hashIndex, lab, st_tmp -> type, st_tmp -> value);
   }
        }
 } 


} 


/********************************************************************/ 

//This routine is used by pass1 to process machine operations
void macProcessing(char *lab, char *op, char *opd)
{
 int i; 
 if(lab[0] != '\0'){
	i=hash(lab); 
	 //search for the label in symbol table, if found write error to file
	if(searchST(lab, &i) != NULL)
		fprintf(output, "  ****ERROR: Duplicate Label %s\n", lab);
	//else insert it into the symbol table
	else{
		insert(i, lab, 'R', locctr);
	}
 }
    //finding number of bytes needed by the instruction and increment locctr 
    //accordingly.
    otTemp = searchOT(op);
	//printf("called searchOT");
	if(otTemp != NULL){
		locctr += otTemp -> instFormat; 
		printf("otTemp->instFormat == %d\n",otTemp->instFormat);
	}

} 

/********************************************************************************/ 

//COMPUTES THE HASH VALUE
int hash(char *name_ptr){
 //HASH Function: sum ascii values and mod by BKT_SIZE
 int hashIndex = 0;
 int i;
 int sum = 0;
 int n = 0;
 while(*(name_ptr + n) != '\0')
  n++;
 for(i=0;i<n;i++){
  sum += *(name_ptr + i);
 }
 return (sum % SIZE);
} 

/********************************************************************************/ 

void insert(int index, char *key, char rORa, int value)
{
//Adds a new entry by computing its hash value, then traversing to the 
//end of the chain at that index.
 int i;
 struct symtabEntry *current; 

 //if null inserting into front of linked list
 if ((syTab[index])==NULL){
  syTab[index]=malloc(sizeof(struct symtabEntry));
  current=syTab[index];
 }
 else{
        current=syTab[index];
  while (current->next!=NULL)
   current=current->next;
  //creating new symtab entry
  current->next=malloc(sizeof(struct symtabEntry));
        current=current->next;
 } 

 current->next=NULL; 

 //coping string into node
 for (i=0; i<MNEMONIC_OPCODE_LENGTH +1; i++)
  current->name[i]=key[i]; 

 //setting value and type
  current->value = value;
  current->type = rORa; 

} 

/********************************************************************************/ 

//search to see if the name exists in the symbol table
struct symtabEntry *searchST(char *name, int *index)
{ 

 struct symtabEntry *current; 

 *index=hash(name);
 current=syTab[*index];
 //printf("searching for %s\n",name);
        while (current!=NULL){
  //printf("current -> name == %s\n",current -> name);
  if (strcmp(current->name, name) == 0){
   //printf("found %s\n",name);
   return current;
  }
  current=current->next;
 }
 return current;
} 


/********************************************************************/ 

//searches the operation table for mnemonic opcodes
struct optabEntry *searchOT( char *opCodeIn)
{
	int i; 
	for(i=0;i<NUM_OF_OPCODES;i++){
		//printf("opTab[i].mnemonicOpcode == %s, opCodeIn == %s\n",opTab[i].mnemonicOpcode, opCodeIn);
		if(strcmp(opTab[i].mnemonicOpcode, opCodeIn) == 0){
			//printf("opTab[i].mnemonicOpcode == %s, opCodeIn == %s, opTab[i].instFormat == %d\n",opTab[i].mnemonicOpcode, opCodeIn, opTab[i].instFormat);
			return (&opTab[i]);
		}	
	}
 return NULL;
} 

/********************************************************************/
//Checks to see if the mnemonic opcode or directive matches the
//given assembler directives. If it does it return a 1(true),
//otherwise it returns a 0(false)
int check(char *name)
{
 if((strcmp(name, "START") == 0) || (strcmp(name, "END") == 0) ||
  (strcmp(name, "BYTE") == 0) || (strcmp(name, "WORD") == 0) ||
  (strcmp(name, "RESB") == 0) || (strcmp(name, "RESW") == 0) ||
  (strcmp(name, "EQU") == 0) || (strcmp(name, "EXTDEF") == 0) ||
  (strcmp(name, "NOBASE") == 0) || (strcmp(name, "BASE") == 0) ||
  (strcmp(name, "EXTREF") == 0))
  return 1; 

 else
  return 0;
} 

/********************************************************************/
//construction of the symbol table
void syTable()
{
 int i, j;
 struct symtabEntry *current; 

 outputfile="SyTable.txt";
 output=fopen(outputfile, "w"); 

 fprintf(output,"\n\tSYMBOL TABLE\n\n");
 fprintf(output,"Hash Index\tName\tValue\tType\n");
 j=0; 

 for (i=0; i<SIZE; i++){
   //travesal of the table
  current=syTab[i];
  while (current!=NULL){
    if (j==0){
     fprintf(output, "   %d\t\t%s\t%X\t%c",i,current->name,current->value,current->type);
     j=1;
    }
    else
     fprintf(output, "    \t\t%s\t%X\t%c",current->name,current->value,current->type);
    fprintf(output, "\n");
    current=current->next;
  } 

  j=0;
 }
 fclose(output); 

} 

/********************************************************************/ 

int isSpaceBetween(char *in, int start, int end){
 int i;
 for(i=start;i<end;i++){
  if(isspace(in[i])){
   return 0;
  }
 }
 return 1;
} 

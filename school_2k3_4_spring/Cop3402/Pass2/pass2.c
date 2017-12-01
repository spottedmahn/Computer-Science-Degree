//Michael DePouw & William Ford
//Pass 2

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h> 

#define MNEMONIC_OPCODE_LENGTH 10  //length of op code
#define HEX_OPCODE_LENGTH 3			//length of hex for op tab
#define SIZE 100				//size of arrays
#define INP_LENGTH 79			//length of fgets
#define HEX_LENGTH 5			//len of hex
#define OPND_LENGTH 17			//operand length
#define NAME_LENGTH 10			//len of label
#define OP_LENGTH 6				// op len
#define COMMENT_LENGTH 30		// len of comments
#define LABEL_START 10			// where the label starts for stcpy
#define OP_START 19				// where the opcode starts for stcpy
#define OPND_START 27			// where the operand starts for stcpy
#define QUOTE_START 29			// quote starts for "X" and "C" of byte
#define COM_START1 45			// where the comment starts for stcpy
#define COM_START2 48			// where the indented comment starts for stcpy
#define ONETHR 32768			//value for 8000 hex converted to dec.

FILE *input, //Input file pointer
     *output, *outInterFile, //Output file pointer
  *fopen(); 

//op table entry
struct optabEntry{
	char mnemonicOpcode[MNEMONIC_OPCODE_LENGTH +1];
	int instFormat;
	char hexOpcode[HEX_OPCODE_LENGTH +1 ];
}*otTemp; 
//symbol table entry
struct symtabEntry {
	char name[MNEMONIC_OPCODE_LENGTH];
    int  value;
	/* R (Relocatable), A (Absolute), E (Export EXTREF), or I (Import EXTDEF)*/
	char type; 
    /* For RESB, RESW, BYTE, WORD*/
	int  length;  
	struct symtabEntry *next;
}*st_tmp; 

struct optabEntry opTab[SIZE];
struct symtabEntry *syTab[SIZE];
char *inputfile, *outputfile; 

int locctr; //location counter, incremented for by the appropriate
            // value for each instuction.
int lineno; //line number that is incremented by 1 for each input
            // assembly language line.
int otCnt;  //number of actual entries in operation table 

//function declarations
void readOpTable(); //construction of operation table
void readSymTable(); //construction of the symbol table
void syTable();		//prints the symtable
void pass2(); // do all calculations for pass 2
int hash(char *key); // hash values for index for op and sym
void init(); // initilize files
void insert(char *,int , int, char); // insert record
int isSpaceBetween(char *, int start, int end);  //check for spaces in string
struct optabEntry *searchOT(char *name); //search operation table for opcodes
struct symtabEntry *searchST(char *name, int *index); //search the symbol table 

int main(){
	readOpTable();
	init();
	readSymTable(); 
	syTable();
	pass2();

 /*printf("\nThe operation table is found in OpTable.txt\n");
 printf("The intermediate file is found in InterFile.txt\n");
 printf("The symbol table is found in SyTable.txt\n\n"); 
*/
	return 0;
} 
void pass2(){


	char inputLine[INP_LENGTH + 1], hexLocation[HEX_LENGTH + 1], label[NAME_LENGTH + 1]; // chars for input
	char operat[OP_LENGTH + 1], operand[OPND_LENGTH + 1], comments[COMMENT_LENGTH + 1]; //chars for input
	char tmpCH[OPND_LENGTH + 1];  //temp for gen input

	struct optabEntry *op;  //make pointer for return functions
	struct symtabEntry *sym;
	
	int index;			
	int tmpI;				//declare var's
	int noOperandFlag = 0;

	input = fopen("p2INTFILE.txt", "r");
	output = fopen("list.txt", "w");			//open input and output files

	while(fgets(inputLine, INP_LENGTH, input) != NULL){  //while not eof

		//printf("InputLine = %s\n", inputLine);
		strcpy(comments, "");
		//if comment line just print to output
		if(inputLine[0] == ' '){
			//printf("%s\n", inputLine);
			fprintf(output, "%s", inputLine);
		}
		//else process line
		else{
			//getting hex
			strcpy(hexLocation, (char *) strtok(inputLine, " \n"));
			
			//printing hex
			fprintf(output, "%s\t", hexLocation);
			
			//getting label
			if(inputLine[LABEL_START] == ' '){
				strcpy(label,"");
			}
			else{
				strcpy(label, (char *) strtok((inputLine + LABEL_START), " \n"));
			}
			
			//getting operator
			strcpy(operat, (char *) strtok((inputLine + OP_START), " \n"));
			
			//getting operand
			if(inputLine[OPND_START] != ' ' && (inputLine[OPND_START] != 10)){
				
				int i=OPND_START, isQuote = 0;

				//find if it is a quote
				while(inputLine[i] != '\0' && (i < 46)){
					if(inputLine[i] == '\''){
						isQuote = 1;
					}
					i++;
				}

				//check operand and get val into a temp
				//takes out the '  ' of the operand so it can be processed
				if(isQuote){
					char tmpC [OPND_LENGTH + 1];
					
					strcpy(operand, strtok((inputLine + OPND_START), "'\n"));
					
					strcat(operand, "'");
					
					strcpy(tmpC, strtok((inputLine + QUOTE_START), "'\n"));
					
					strcat(tmpC, "'");
					
					strcat(operand, tmpC);
				}
				else{
					//not quots so get operand
					strcpy(operand, (char *) strtok((inputLine + OPND_START), " \n"));
				}

				noOperandFlag = 0;
			}
			else{
				strcpy(operand, "");  //no operand set flag
				noOperandFlag = 1;
			}
			strcpy(tmpCH, operand);
			
			//getting comments
			if(((inputLine[COM_START1] != ' ') && (inputLine[COM_START1] != 10)) || (inputLine[COM_START2] != ' ')){

				strcpy(comments, (char *) strtok((inputLine + COM_START1), "\n"));
			}		
			else{
				strcpy(comments, "");
			}

			//printf("tmpCH == %s", tmpCH);
			strcpy(operand, tmpCH);
			//printf("Opereand == %s\t", operand);

			//get val from op table
			op = searchOT(operat);

			//if a valid op then proceed
			if(op != NULL){
				
				if(noOperandFlag){
					//print if has an operand
					fprintf(output, "%s0000 \t%s \t%s \t%s \t%s\n", op -> hexOpcode, label, operat, operand, comments);						
				}
				else{

					//check to see if operand has comma
					int i=0, isComma = 0;

					while(operand[i] != '\0'){
						if(operand[i] == ','){
							isComma = 1;
						}
						i++;
					}
					
					if(isComma){
						//if so there are two typs 'X' and Other
						if(operand[i-1] == 'X'){
							
							char tmpC[OPND_LENGTH + 1];
							
							strcpy(tmpC, (char *) strtok((inputLine + OPND_START), ","));
							
							index = hash(operand);

							sym = searchST(tmpC, &index);

							tmpI = sym -> value + ONETHR;

							//get val and print it out
							fprintf(output, "%s%x \t%s \t%s \t%s \t%s\n", op -> hexOpcode, tmpI, label, operat, operand, comments);

						}	
						else{
							
							//process other case and do pass 2 properities
							char r1[OPND_LENGTH + 1], r2[OPND_LENGTH + 1];
							int tmpValue;

							strcpy(r1, strtok((inputLine + OPND_START), ","));
							strcpy(r2, strtok(NULL, " \n"));

							index = hash(r1);

							sym = searchST(r1, &index);
							
							tmpValue = sym -> value;

							index = hash(r2);

							sym = searchST(r2, &index);

							fprintf(output,"%s%X%X \t%s \t%s \t%s \t%s\n", op -> hexOpcode, tmpValue, sym -> value, label, operat, operand, comments);

						}
					}
					else{
						
						index = hash(operand);
						
						sym = searchST(operand, &index);
						
						//print out rest of types with commas if type 2 add 0 else print normal
						if(op -> instFormat == 2){
							fprintf(output,"%s%x0\t%s\t%s\t%s\t%s\n",op -> hexOpcode, sym -> value, label, operat, operand, comments);
						}
						else{
							fprintf(output,"%s%x\t%s\t%s\t%s\t%s\n",op -> hexOpcode, sym -> value, label, operat, operand, comments);
						}
					}	
				}
			}
			else{

				// if byte then process
				if(operat[0] == 'B'){
					
					//process if it is 'X'
					if(operand[0] == 'X'){
						char tmpC[OPND_LENGTH + 1];

						strcpy(tmpC, (char *) strtok((inputLine +QUOTE_START), "'"));

						fprintf(output, "%s \t%s \t%s \t%s \t%s\n", tmpC, label, operat, operand, comments);
					}
					else{
						//type 'C' conver to hex and print
						//print 3 chars per line
						char tmpC[OPND_LENGTH + 1];
						
						int i = 0, flag = 1;
						
						strcpy(tmpC, (char *) strtok((inputLine + QUOTE_START), "'"));
						
						while((tmpC[i] != '\0') && (i < 3)){
							
							if(tmpC[i] == '\0'){
								flag = 0;
							}
						
							fprintf(output, "%X", tmpC[i]);
							i++;
						}
						//print out first line
						fprintf(output, "  %s \t%s \t%s \t%s", label, operat, operand, comments);
						
						if(flag){
							//print out rest of the lines after 1st
							// int hex
							i = 3;
							
							while(tmpC[i] != '\0'){
								
								if(i % 3 == 0){
									fprintf(output, "\n\t%X", tmpC[i]);
								}
								else{
									fprintf(output, "%X", tmpC[i]);
								}
								i++;
							}
							// at end print new line
							fprintf(output, "\n");
						}
					}
				}
				//else if
				// if "word"
				else if(operat[0] == 'W'){
					
					int tmp = atoi(operand);
					//print out values
					fprintf(output, "%06X \t%s \t%s \t%s \t%s \n", tmp, label, operat, operand, comments);

				}
				else{
					//else everything else not in cases above
					fprintf(output, "\t%s \t%s \t%s \t%s\n", label, operat, operand, comments);
				}
			}
		}
		inputLine[COM_START1] = ' ';//make input line 45 null
		inputLine[COM_START2] = ' ';//make input line 48 null
		strcpy(label, "");				
		strcpy(operat, "");
		strcpy(operand, "");		//make all var null for next pass
		strcpy(comments, "");
		strcpy(hexLocation, "");
		
	}//end while(fgets())
	printf("Pass2 complete\n");
}
//construct the operation code table
void readOpTable(){ 

	char mnemonicOpCode[MNEMONIC_OPCODE_LENGTH +1], hexCode[HEX_OPCODE_LENGTH +1 ];
 
	int instFormat, j; 
	
	input=fopen("p2opcodes.txt", "r"); 

	//read from input file into the opCode table.
	otCnt = 0;
	while(fscanf(input, "%s%d%s", mnemonicOpCode, &instFormat, hexCode)!=EOF){
		strcpy(opTab[otCnt].mnemonicOpcode, mnemonicOpCode);
		opTab[otCnt].instFormat = instFormat;
		strcpy(opTab[otCnt].hexOpcode, hexCode); 
		otCnt++;
	}
	fclose(input);


 //Write to output file containing the operation table
 outputfile="OpTable.txt";
 output=fopen(outputfile, "w");
 fprintf(output, "\n\tOPERATION TABLE\n\n");
 fprintf(output, "Index\tOperation\tFormat\tHexDec.\n");
 for(j=0; j<otCnt;j++)
   fprintf(output,"%d\t%s\t\t%d\t%s\n", j, opTab[j].mnemonicOpcode,opTab[j].instFormat,opTab[j].hexOpcode);
 fclose(output); 

} 
//searches the operation table for mnemonic opcodes
struct optabEntry *searchOT( char *opCodeIn){
	
	int i; 
	for(i=0;i<otCnt;i++){
		//printf("opTab[i].mnemonicOpcode == %s, opCodeIn == %s\n",opTab[i].mnemonicOpcode, opCodeIn);
		if(strcmp(opTab[i].mnemonicOpcode, opCodeIn) == 0){
			//printf("opTab[i].mnemonicOpcode == %s, opCodeIn == %s, opTab[i].instFormat == %d\n",opTab[i].mnemonicOpcode, opCodeIn, opTab[i].instFormat);
			return (&opTab[i]);
		}	
	}
	return NULL;
} 
void init(){
	//Performs the basic memory allocation needed to start a symbol hashtable with the given  size
	int i; 
	
	lineno = 0;
	locctr = 0000; 

	*syTab=calloc(SIZE, sizeof(struct symtabEntry *));
	
	for (i=0; i<SIZE; i++){
		syTab[i]=NULL;
	}
} 
//read symbol table
void readSymTable(){
	
	char name[MNEMONIC_OPCODE_LENGTH];
	int value;
	int length;
	char type;
	
	input = fopen("p2symtable.txt", "r");

	while(fscanf(input, "%s %d %d %c", name, &value, &length, &type) != EOF){
		insert(name, value, length, type);
	}
}
//insert into symbol table
void insert(char *name, int value, int length, char type){

	int index = hash(name);
	
	struct symtabEntry *tmpSTE = syTab[index];
	struct symtabEntry *newNode;	
	//if null inserting into front of linked list
	if ((syTab[index])==NULL){
		syTab[index]=malloc(sizeof(struct symtabEntry));
		tmpSTE = syTab[index];

	}
	//else traversing to end of linked list
	else{
		while (tmpSTE->next!=NULL){
			tmpSTE=tmpSTE->next;
		}
		newNode = malloc(sizeof(struct symtabEntry));
		tmpSTE->next = newNode;
		tmpSTE = tmpSTE->next;
	} 
	
	tmpSTE->next=NULL; 
	tmpSTE->value = value;
	tmpSTE->type = type; 
	strcpy(tmpSTE->name, name);
} 
//search to see if the name exists in the symbol table
struct symtabEntry *searchST(char *name, int *index){ 

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
     fprintf(output, "   %d\t\t%s\t%d\t%c",i,current->name,current->value,current->type);
     j=1;
    }
    else
     fprintf(output, "    \t\t%s\t%d\t%c",current->name,current->value,current->type);
    fprintf(output, "\n");
    current=current->next;
  } 

  j=0;
 }
 fclose(output); 

} 
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

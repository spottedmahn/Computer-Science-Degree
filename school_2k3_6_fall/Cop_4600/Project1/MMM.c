/***************************************************************

** Michael DePouw

** COP4600

** Assignment Number: 1

** Date:9-12-03

***************************************************************/

/****************Program Description***************************

**
** Please see Project1.doc
**

**************************************************************/
#include <stdio.h>
#include <memory.h>

 
#define MAX_NUM_SEGMENTS 10
#define MAX_NUM_PROCESSES 6
#define MEMSIZE 1024

int searchFreeMem(int);
int Alloc_Seg(int);
int isalpha(int);
int strcmp(char *, char *);
int atoi(char *);
int findLastUsedBase();
char *strtok(char *, char *);
void testMem();
void insertFreeMem(int, int);
void changeBaseUsedMem(int, int);
void removeUsedMem(int);
void removeFreeMem(int);
void insertUsedMem(int, int);
void compactMem();
void *calloc();
void deallocMem(struct pcbType *);
void intialize();
void print();
void mergeSeg();
struct segList *findLastFree();
struct segType *findLastUsed();

int DSA = -1;
int availMem = 1024;
int validProcesses[MAX_NUM_PROCESSES];

FILE *fOut, *fIn;

struct segList{
	int segPtr;
	int segSize;
	struct segList *next;
}*freeMem, *usedMem;

struct segType{
	int memBase;
	int segLen;
};

struct pcbType{
	struct segType *segTable;
	int segTableLen;
}**PCB_Table;

int main(void){
	
	intialize();

	testMem();
	fclose(fOut);

	return 0;
}

void intialize(){
	
	int i;

	freeMem = calloc(1, sizeof(struct segList));
	freeMem -> segPtr = 0;
	freeMem -> segSize = MEMSIZE;
	freeMem -> next = NULL;

	PCB_Table = calloc(MAX_NUM_PROCESSES, sizeof(struct pcbType *));

	for(i=0;i < MAX_NUM_PROCESSES;i++){
		
		PCB_Table[i] = calloc(1, sizeof(struct pcbType));
		PCB_Table[i] -> segTable = calloc(MAX_NUM_SEGMENTS, sizeof(struct segType));
	}

	fIn = fopen("Allocate_deallocate1.txt", "r");
	fOut = fopen("out.txt", "w");

	for(i=0;i < MAX_NUM_PROCESSES;i++){
		 
		validProcesses[i] = 0;
	}
}
void testMem(){
	
	int i;

	char tmpS[80];
	char line[80];
	
	memset(line, '\0', 80);
	fgets(line, 80, fIn);
	
	i=0;
	while( isalpha(line[i] ) || line[i] == '-' ) {
		i++;
	}

	line[i] = '\0';
	
	fprintf(fOut, "%s\n", line);
	
	if(strcmp(line, "First-Fit") == 0){
		
		DSA = 0;
	}
	else if(strcmp(line, "Best-Fit") == 0){
		
		DSA = 1;
	}
	else if(strcmp(line, "Worst-Fit") == 0){
		
		DSA = 2;
	}

	while(fgets(tmpS, 80, fIn) != NULL){
		
		int memBase;
		int num;
		int size;
		
		char *aORd;
		char *tokens = " ";

		aORd = strtok(tmpS, tokens);
		strtok(NULL, tokens);
		num = atoi(strtok(NULL, tokens));
		

		if(aORd[0] == 'A' || aORd[0] == 'a'){
			
			size = atoi(strtok(NULL, tokens));

			if(MAX_NUM_SEGMENTS > PCB_Table[num] -> segTableLen){
				
				memBase = Alloc_Seg(size);

				if(memBase != -1){
					

					if(validProcesses[num - 1] == 0){
						
						validProcesses[num - 1] = 1;
					}
					
					PCB_Table[num - 1] ->segTable[PCB_Table[num - 1] -> segTableLen].memBase = memBase;
					PCB_Table[num - 1] ->segTable[PCB_Table[num - 1] -> segTableLen].segLen = size;
					PCB_Table[num - 1] ->segTableLen ++;
					insertUsedMem(memBase, size);

					availMem-=size;

					fprintf(fOut, "Allocate Process %d\n", num);
					fprintf(fOut, "Free Memory = %d\n", availMem);
					print();
				}
				else{
					
					fprintf(fOut, "Allocate Process Failed, Not enough available memory %d\n", num);
					fprintf(fOut, "Free Memory = %d\n", availMem);
					print();
				}
			}
		}
		else if(aORd[0] == 'D' || aORd[0] == 'd'){
			
			deallocMem(PCB_Table[num - 1]);
			
			validProcesses[num - 1] = 0;
			
			fprintf(fOut, "Deallocate Process %d\n", num);
			fprintf(fOut, "Free Memory = %d\n", availMem);
			fflush(fOut);
			print();
		}
	}	
}	

int Alloc_Seg(int len){
	
	int baseAddr = -1;

	if(len > availMem){
		
		return -1;
	}
	while(1){
		
		baseAddr = searchFreeMem(len);
		
		if(baseAddr >= 0){
			
			return baseAddr;
		}
		compactMem();
		mergeSeg();
	}
	return -1;
}

void deallocMem(struct pcbType *in){
	
	int i;

	for(i=0;i < in -> segTableLen;i++){
			
		insertFreeMem(in -> segTable[i].memBase, in -> segTable[i].segLen);

		removeUsedMem(in -> segTable[i].memBase);

		availMem += in -> segTable[i].segLen;

		in -> segTable[i].memBase = 0;
		in -> segTable[i].segLen = 0;

	}
	
	in -> segTableLen = 0;

	mergeSeg();
}

void removeFreeMem(int base){
	
	struct segList *delNode = freeMem;

	if(freeMem == NULL){
		
		return;
	}
	
	if(freeMem -> segPtr == base){
		
		if(freeMem -> next == NULL){
			
			freeMem = NULL;
			return;
		}
		else{
			
			freeMem = freeMem -> next;
			return;
		}	
	}
	if((freeMem -> next == NULL) && (delNode -> segPtr == base)){
		
		freeMem = NULL;
		return;
	}

	while(delNode -> next -> segPtr != base){
		
		delNode = delNode -> next;
	}
	
	if(delNode -> next -> next == NULL){
		
		delNode -> next = NULL;
		return;
	}
	else{
		
		delNode -> next = delNode -> next -> next;
		return;
	}

}
void insertFreeMem(int base, int size){
	
	struct segList *helper = freeMem;
	struct segList *newNode = calloc(1, sizeof(struct segList));
	
	newNode -> segPtr = base;
	newNode -> segSize = size;

	if(freeMem == NULL){
		
		freeMem = newNode;
		newNode -> next = NULL;
		return;
	}
	else if(freeMem -> next == NULL){
		
		if(freeMem -> segPtr < base){
			
			freeMem -> next = newNode;
			newNode -> next = NULL;
			return;
		}
		else{
			
			freeMem = newNode;
			freeMem -> next = helper;
			return;
		}
	}
	else if(freeMem -> segPtr > base){
			
		newNode -> next = freeMem;
		freeMem = newNode;
		return;
	}
	else{
		
		while((helper -> next != NULL) && (helper -> next -> segPtr < base)){
		
			helper = helper -> next;
		}		

		if(helper -> next == NULL){
			
			helper -> next = newNode;
			newNode -> next = NULL;
			return;
		}
		else{
			
			newNode -> next = helper -> next;
			helper -> next = newNode;
			return;
		}
	}
}
int searchFreeMem(int len){
	
	struct segList *helper = freeMem;
	//First-Fit
	if(DSA == 0){
		
		int firstFitAddr = -1;

		while(helper != NULL){
			
			if(helper -> segSize == len){				
				
				firstFitAddr = helper -> segPtr;
				
				removeFreeMem(helper -> segPtr);
			
				return firstFitAddr;
			}
			else if(helper -> segSize > len){
					
				firstFitAddr = helper -> segPtr;
				
				helper -> segPtr += len;
				helper -> segSize -= len;

				return firstFitAddr;
			}
			helper = helper -> next;	
		}
		return -1;
	}
	//Best-Fit
	else if(DSA == 1){

		struct segList *bestFit = NULL;
		
		int bestFitAddr = -1;
		//determining best fit
		while(helper != NULL){
			
			if(helper -> segSize >= len){
				
				if( bestFit == NULL ) {
				
					bestFit = helper;
				}
				else if(helper -> segSize < bestFit->segSize){
					
					bestFit = helper;
				}
			}
			
			helper = helper -> next;
		}

		if(bestFit != NULL) {

			bestFitAddr = bestFit->segPtr;

			//if == delete node
			if(bestFit -> segSize == len){
				
				removeFreeMem(bestFit -> segPtr);				
			}
			//else reduce size and increase ptr
			else{
			
				bestFit->segSize -= len;
				bestFit->segPtr += len;
			}	
		}
		return bestFitAddr;
	}
	//Worst-Fit
	else if(DSA == 2){
		
		struct segList *worstFit = NULL;
		
		int worstFitAddr = -1;

		while(helper != NULL){
			
			if(helper -> segSize >= len){
				
				if(worstFit == NULL){
					
					worstFit = helper;
				}
				else if(helper -> segSize > worstFit -> segSize){
					
					worstFit = helper;
				}
			}
			helper = helper -> next;
		}
		if(worstFit != NULL){
			
			worstFitAddr = worstFit -> segPtr;
			
			//if == delete node
			if(worstFit -> segSize == len){
				
				removeFreeMem(worstFit -> segPtr);
			}
			//else decrease size and increase ptr
			else{
				
				worstFit -> segSize -= len;
				worstFit -> segPtr += len;				
			}
		}
		return worstFitAddr;
	}
	return -1;
}
void compactMem(){
	
	struct segList *lastF = findLastFree();
	struct segType *lastU = findLastUsed();

	int i, j;

	for(i=0;i < MAX_NUM_PROCESSES;i++){
		
		if(validProcesses[i]){

			for(j=0;j < PCB_Table[i] -> segTableLen;j++){
				
				if(PCB_Table[i] -> segTable[j].memBase == lastU -> memBase){
					
					changeBaseUsedMem(lastU -> memBase, (lastU -> memBase + lastF -> segSize));

					lastF -> segPtr = PCB_Table[i] -> segTable[j].memBase;
					PCB_Table[i] -> segTable[j].memBase = lastU -> memBase + lastF -> segSize;

					return;
				}
			}
		}
	}
}

struct segType *findLastUsed(){
	
	int i,j;
	int lastUsedBase;

	lastUsedBase = findLastUsedBase();

	for(i=0;i < MAX_NUM_PROCESSES;i++){
		
		if(validProcesses[i]){

			for(j=0;j < PCB_Table[i] -> segTableLen;j++){
				
				if(PCB_Table[i] -> segTable[j].memBase == lastUsedBase){

					return &PCB_Table[i] -> segTable[j];
				}
			}
		}
	}
	return NULL;
}

int findLastUsedBase(){
	
	int usedMemAtEnd = MEMSIZE;
	
	struct segList *helper = usedMem;

	if((helper -> segPtr + helper -> segSize) != MEMSIZE){
		
		return usedMem -> segPtr;
	}
	else if((helper -> segPtr + helper -> segSize) == usedMemAtEnd){
		
		usedMemAtEnd -= helper -> segSize;
	}
	
	while(helper -> next -> segPtr + helper -> next -> segSize == usedMemAtEnd){
			
		usedMemAtEnd -= helper -> segSize;
		
		helper = helper -> next;
	}
	
	return helper -> next -> segPtr;

}
void mergeSeg(){
	
	int done = 0;
	int dontMoveHelper = 0;

	struct segList *helper = freeMem;

	while((!done) && (helper -> next != NULL)){
		
		if((helper -> segPtr + helper -> segSize) == helper -> next -> segPtr){
			
			helper -> segSize += helper -> next -> segSize;

			if(helper -> next -> next != NULL){
				
				helper -> next = helper -> next -> next;
				dontMoveHelper = 1;
			}
			else{
				
				helper -> next = NULL;
				done = 1;
			}
		}
		if(!dontMoveHelper){
			helper = helper -> next;
		}
		dontMoveHelper = 0;
	}
}

void insertUsedMem(int memBase, int size){
	
	struct segList *helper = usedMem;
	struct segList *newNode = calloc(1, sizeof(struct segList));
	
	newNode -> segPtr = memBase;
	newNode -> segSize = size;

	if(usedMem == NULL){
		
		usedMem = newNode;
		newNode -> next = NULL;

		return;
	}

	else if(usedMem -> segPtr < memBase){
		
		usedMem = newNode;
		usedMem -> next = helper;
		return;
	}
	
	while((helper -> next != NULL) && (helper -> next -> segPtr > memBase)){
		
		helper = helper -> next;
	}

	if(helper -> next == NULL){
		
		helper -> next = newNode;
		newNode -> next = NULL;

		return;
	}
	else if(helper -> next != NULL){
		
		newNode -> next = helper -> next;
		helper -> next = newNode;

		return;
	}
}

void removeUsedMem(int base){
	
	struct segList *helper = usedMem;
	
	if(usedMem -> segPtr == base){
		
		if(usedMem -> next == NULL){
			
			usedMem = NULL;
		}
		else{

			usedMem = usedMem -> next;
		}
		return;
	}

	while(helper -> next -> segPtr != base){
		
		helper = helper -> next;
	}
	
	if(helper -> next -> next == NULL){
		
		helper -> next = NULL;
		return;
	}
	else {
		
		helper -> next = helper -> next -> next;
		return;
	}

}

void changeBaseUsedMem(int oldBase, int newBase){
	
	struct segList *helper = usedMem -> next;

	if(usedMem -> segPtr == oldBase){
		
		usedMem -> segPtr = newBase;
		return;
	}

	while(helper -> segPtr != oldBase){
		
		helper = helper -> next;
	}

	helper -> segPtr = newBase;
}
struct segList *findLastFree(){
		
	struct segList *helper = freeMem;
	struct segList *last = freeMem;

	while(helper != NULL){
		
		if(last -> segPtr < helper -> segPtr){
			last = helper;
		}
		helper = helper -> next;
	}
	
	return last;
}


void print(){
	
	int x=0;
	int searchPCB = 1;
	int i,j;

	struct segList *helper = freeMem;
	
	while(x < MEMSIZE){
		
		while(helper != NULL){
			
			if(helper -> segPtr == x){
				
				fprintf(fOut, "%d free\n", helper -> segSize);
				fflush(fOut);
				searchPCB = 0;
				x += helper -> segSize;
			}

			helper = helper -> next;
		}

		if(searchPCB){
			
			for(i=0;i < MAX_NUM_PROCESSES;i++){
				
				if(validProcesses[i]){

					for(j=0;j < PCB_Table[i] -> segTableLen;j++){
						if(PCB_Table[i] -> segTable[j].memBase == x){
							
							fprintf(fOut, "%d used\n", PCB_Table[i] -> segTable[j].segLen);
							fflush(fOut);
							x+= PCB_Table[i] -> segTable[j].segLen;
						}
					}
				}
			}
		}
		searchPCB = 1;
		helper = freeMem;
	}
}
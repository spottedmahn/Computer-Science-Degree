/***************************************************************

** Michael DePouw

** COP4600

** Assignment Number: 2

** Date:9-12-03

***************************************************************/

/****************Program Description***************************

**
** Please see Project2_1.doc
**

**************************************************************/
#include "CIS.h"

short unsigned int FAT[NUM_OF_BLOCKS];

struct FCB root[NUM_OF_FILES];

char *FATp = (char *) FAT;
char *rootP = (char *) root;

int main(void){
	
	initializeFat();
	initializeRoot();
	initializeDisk();
	
	readFat();
	readRoot();
	
	commandInterpreterSystem();

	return 0;
}

void initializeDisk(){
	
	int i;
	char buffer[SIZE_OF_SECTOR];

	if(fopen("DISK1.BIN", "rb") == NULL){
		
		binaryFile = fopen("DISK1.BIN", "wb+");
		
		fillBuffer('0',buffer);

		for(i=0;i < NUM_OF_SIDES * NUM_OF_TRACKS * NUM_OF_SECTORS_PER_TRACK;i++){
			
			fseek(binaryFile, i * SIZE_OF_SECTOR, 0);

			fwrite(buffer, sizeof(char), SIZE_OF_SECTOR, binaryFile);
		}
		
		logFile = fopen("DISK1_log.txt", "w");

		writeFat();
		writeRoot();
	}
	else{
		
		binaryFile = fopen("DISK1.BIN", "rb+");
		logFile = fopen("DISK1_log.txt", "w");
	}

}

void initializeFat(){
	
	int i;

	for(i=0;i < NUM_OF_BLOCKS;i++){
	
		FAT[i] = 0;
	}

	FAT[NUM_OF_BLOCKS - 1] = 65535;

}

void initializeRoot(){
	
	int i;

	for(i=0;i < NUM_OF_FILES;i++){
		
		root[i].size = -1;
	}
}
void commandInterpreterSystem(){
	
	char tmpS[50], *command, *fillChar, buffer[SIZE_OF_SECTOR], *tokens = " ", *inFile, *outFile, *fileName, *att;
	
	int blckNum, success;
	
	helpToScreen();

	while(1){
		
		putchar('>');
		//reading a line
		gets(tmpS);

		command = strtok(tmpS, tokens);

		if(command == NULL){
			continue;
		}

		if(strcmp(command, "exit") == 0){
			
			fclose(binaryFile);
			fclose(logFile);

			return 0;
		}
		else if(strcmp(command, "fillblock") == 0){
			
			blckNum = atoi(strtok(NULL, tokens));

			fillChar = strtok(NULL, tokens);
			
			fillBuffer(fillChar[0], buffer);
			//writing a block to blckNum
			success = writeBlock(blckNum, buffer);

			if(success >= 1){

				printf("block %d filled\n", blckNum);

			}
			else{
				
				printf("block %d not filled\n", blckNum);
			}
		}
		else if(strcmp(command, "readblock") == 0){
			
			blckNum = atoi(strtok(NULL, tokens));
			
			success = readBlock(blckNum, buffer);

			if(success >= 1){

				printBufferToScreen(buffer);

			}	
			else{
				
				printf("readblock %d failed\n", blckNum);
			}
		}
		else if(strcmp(command, "batch") == 0){
			
			inFile = strtok(NULL, tokens);
			outFile = strtok(NULL, tokens);

			success = batchFileProcess(inFile, outFile);
			
			if(!success){
				
				return;
			}	
		}
		else if(strcmp(command, "help") == 0){
			
			helpToScreen();
		}
		else if(strcmp(command, "create") == 0){
			
			fileName = strtok(NULL,tokens);

			success = createFile(fileName);

			if(success >= 1){
				
				printf("%s created successfully\n",fileName);
			}
			else{
				
				printf("failed to create file\n");
			}

		}
		else if(strcmp(command, "del") == 0){
			
			fileName = strtok(NULL, tokens);

			success = delFile(fileName);
			
			if(success >= 1){
				
				printf("%s deleted successfully\n", fileName);
			}
			else{
				
				printf("deleted failed\n");
			}
		}
		else if(strcmp(command, "format") == 0){
			
			format();

			printf("Format Successfull\n");
		}
		else if(strcmp(command, "mkdir") == 0){
			
			fileName = strtok(NULL, tokens);

			success = makeDir(fileName);

			if(success >= 1){
				
				printf("%s created successfully\n", fileName);
			}
			else{
				
				printf("make dir failed\n");
			}
		}
		else if(strcmp(command, "rmdir") == 0){
			
			fileName = strtok(NULL, tokens);

			success = delDir(fileName);

			if(success >= 1){
				
				printf("%s deleted successfully\n", fileName);
			}	
			else{
				
				printf("deleted failed\n");
			}
		}
		else if(strcmp(command, "cd") == 0){
			

		}
		else if(strcmp(command, "dir") == 0){
			
			dirToScreen();
		}
		else if(strcmp(command, "attrib") == 0){
			
			att = strtok(NULL, tokens);

			fileName = strtok(NULL, tokens);

			success = setAtt(att, fileName);
			
			if(success >= 1){
				
				printf("attribute set successfully\n");
			}
			else{
				
				printf("attribute set failed\n");
			}
		}
	}
}

int batchFileProcess(char *inFile, char *outFile){
	
	char  *command, *fillChar, *tokens = " ", buffer[SIZE_OF_SECTOR], tmpS[40], *fileName, *att;
	
	int blckNum, success, i;
	
	FILE *fIn, *fOut;

	fIn = fopen(inFile, "r");
	fOut = fopen(outFile, "w");

	for(i=0;i < 40;i++){
		
		tmpS[i] = '\0';
	}

	while(fgets(tmpS, 40, fIn) != NULL){		
		
		for(i=0;i < 40;i++){
			
			if(tmpS[i] == '\n'){
				
				tmpS[i] = '\0';
				i=40;
				break;
			}
		}

		command = strtok(tmpS, tokens);
		
		if(command == NULL){
			break;
		}

		if(strcmp(command, "exit") == 0){
			
			fclose(fIn);
			fclose(fOut);

			return 0;
		}
		else if(strcmp(command, "fillblock") == 0){
			
			blckNum = atoi(strtok(NULL, tokens));

			fillChar = strtok(NULL, tokens);

			fillBuffer(fillChar[0], buffer);

			success = writeBlock(blckNum, buffer);

			if(success >= 1){

				fprintf(fOut, "block %d filled\n", blckNum);

			}
			else{
				
				fprintf(fOut, "block %d not filled\n", blckNum);
			}
		}
		else if(strcmp(command, "readblock") == 0){
			
			blckNum = atoi(strtok(NULL, tokens));

			success = readBlock(blckNum, buffer);

			if(success >= 1){

				printBufferToFile(buffer, fOut);

			}
			else{
				
				fprintf(fOut, "readblock %d failed\n", blckNum);
			}
		}
		else if(strcmp(command, "help") == 0){
			
			helpToFile(fOut);
		}
		else if(strcmp(command, "create") == 0){
			
			fileName = strtok(NULL,tokens);

			success = createFile(fileName);

			if(success >= 1){
				
				fprintf(fOut, "%s created successfully\n",fileName);
			}
			else{
				
				fprintf(fOut, "failed to create file\n");
			}

		}
		else if(strcmp(command, "del") == 0){
			
			fileName = strtok(NULL, tokens);

			success = delFile(fileName);
			
			if(success >= 1){
				
				fprintf(fOut, "%s deleted successfully\n", fileName);
			}
			else{
				
				fprintf(fOut, "deleted failed\n");
			}
		}
		else if(strcmp(command, "format") == 0){
			
			format();

			fprintf(fOut, "Format successfull\n");
		}
		else if(strcmp(command, "mkdir") == 0){
			
			fileName = strtok(NULL, tokens);

			success = makeDir(fileName);

			if(success){
				
				fprintf(fOut, "%s created successfully\n", fileName);
			}
			else{
				
				fprintf(fOut, "make dir failed\n");
			}
		}
		else if(strcmp(command, "rmdir") == 0){
			
			fileName = strtok(NULL, tokens);

			success = delDir(fileName);

			if(success){
				
				fprintf(fOut, "%s deleted successfully\n", fileName);
			}	
			else{
				
				fprintf(fOut, "deleted failed\n");
			}
		}
		else if(strcmp(command, "cd") == 0){
			

		}
		else if(strcmp(command, "dir") == 0){
			
			dirToFile(fOut);
		}
		else if(strcmp(command, "attrib") == 0){
			
			att = strtok(NULL, tokens);

			fileName = strtok(NULL, tokens);

			success = setAtt(att, fileName);

			if(success){
				
				fprintf(fOut, "attribute set successfully\n");
			}
			else{
				
				fprintf(fOut, "attribute set failed\n");
			}
		}
	}

	fclose(fIn);
	fclose(fOut);

	return 1;
}

int format(){
	
	int i;

	for(i=0; i < NUM_OF_FILES;i++){
		
		root[i].size = -1;
	}

	initializeFat();

	return 1;
}

int createFile(char *fileName){
	
	int loc, exists;

	char *name, *ext, *tokens = ".", fileNameCopy[9];

	strcpy(fileNameCopy, fileName);

	exists = fileExists(fileName);

	if(exists < 0){
		
		loc = findFirstFree();

		name = strtok(fileNameCopy, tokens);

		strcpy(root[loc].name, name);

		ext = strtok(NULL, tokens);

		strcpy(root[loc].extension, ext);

		root[loc].att = ARCHIVE;
		root[loc].lcdt = time(NULL);
		root[loc].size = 0;
		root[loc].start_block = 0;

		writeFat();
		writeRoot();
		
		return 1;
	}
	else{
		
		return -1;
	}


}

int delFile(char *fileName){
	
	int loc;

	loc = fileExists(fileName);

	if(loc >= 0){
		
		//deallocateDiskSpace(root[location].size, root[loc].startBlock);

		root[loc].size = -1;

		writeFat();
		writeRoot();

		return 1;
	}
	else{
		
		return -1;
	}
}

int makeDir(char *dirName){
	
	int loc, exists;
	
	char *name, *tokens = " ";
	
	exists = dirExists(dirName);

	if(exists < 0){
		
		loc = findFirstFree();

		name = strtok(dirName, tokens);

		strcpy(root[loc].name, name);

		root[loc].att = (ARCHIVE | DIRECTORY);
		root[loc].lcdt = time(NULL);
		root[loc].size = 0;
		root[loc].start_block = 0;
		

		writeFat();
		writeRoot();

		return 1;
	}
	else{
		
		return -1;
	}
}

int delDir(char *dirName){
	
	int loc;

	loc = dirExists(dirName);

	if(loc >= 0){
		
		root[loc].size = -1;

		writeFat();
		writeRoot();

		return 1;
	}
	else{
		
		return -1;
	}

}	

int setAtt(char *att, char *fileName){
	
	int loc, attribute;

	loc = fileExists(fileName);

	if(loc >= 0){
		
		if(att[0] == '+'){
			
			attribute = determineAtt(att);

			root[loc].att = root[loc].att | attribute;
		}
		else{
			
			attribute = determineAtt(att);

			root[loc].att = root[loc].att ^ attribute;
		}
		
		writeFat();
		writeRoot();

		return 1;
	}
	else{
		
		return -1;
	}
}

int findFirstFree(){
	
	int i;

	i = 0;

	while(root[i].size != -1){
		
		i++;
	}

	return i;
}

int fileExists(char *fileName){
	
	int i;

	char *name, *ext, *tokens = ".", fileNameCopy[9];

	strcpy(fileNameCopy,fileName);

	name = strtok(fileNameCopy, tokens);
	ext = strtok(NULL, tokens);

	for(i=0;i < NUM_OF_FILES;i++){
		
		if(root[i].size != -1){
			
			if(strcmp(root[i].name, name) == 0){
				
				if(strcmp(root[i].extension, ext) == 0){
					
					return i;
				}
			}
		}
	}

	return -1;
}

int dirExists(char *dirName){
	
	int i;

	for(i=0;i < NUM_OF_FILES;i++){
		
		if(root[i].size != -1){
			
			if(strcmp(root[i].name, dirName) == 0){
				
				return i;
			}
		}
	}

	return -1;
}

int determineAtt(char *att){
	
	if(att[1] == 'r'){
		
		return READ_ONLY;
	}
	else if(att[1] == 'a'){
		
		return ARCHIVE;
	}
	else if(att[1] == 's'){
		
		return SYSTEM;
	}
	else if(att[1] == 'h'){
		
		return HIDDEN;
	}
	else{
		
		return -1;
	}
}

void dirToScreen(){
	
	int i, att;
	
	for(i=0;i < NUM_OF_FILES;i++){
	
		if(root[i].size != -1){
			
			att = root[i].att & HIDDEN;

			if(att != HIDDEN){
				
				att = root[i].att & DIRECTORY;

				if(att == DIRECTORY){
				
					printf("<DIR> \t %s \t %s", root[i].name, ctime(&root[i].lcdt));
				}
				else{
					
					printf("%d \t %s.%s \t %s", root[i].size, root[i].name, root[i].extension, ctime(&root[i].lcdt));
				}
			}
		}
	}
}

void dirToFile(FILE *fOut){
	
	int i, att;
	
	for(i=0;i < NUM_OF_FILES;i++){
	
		if(root[i].size != -1){
			
			att = root[i].att & HIDDEN;

			if(att != HIDDEN){
				
				att = root[i].att & DIRECTORY;

				if(att == DIRECTORY){
				
					fprintf(fOut, "<DIR> \t %s \t %s\n", root[i].name, ctime(&root[i].lcdt));
				}
				else{
					
					fprintf(fOut, "%d \t %s.%s \t %s\n", root[i].size, root[i].name, root[i].extension, ctime(&root[i].lcdt));
				}
			}
		}
	}
}
int writeBlock(int blckNum, char *buffer){
		
	fseek(binaryFile, (long) blckNum * SIZE_OF_SECTOR, 0);
	fwrite(buffer, sizeof(char), SIZE_OF_SECTOR, binaryFile);
	fflush(binaryFile);

	disk1Log('w', blckNum);

	return 1;
}

int readBlock(int blckNum, char *buffer){
		
	fseek(binaryFile, (long) blckNum * SIZE_OF_SECTOR, 0);
	fread(buffer, sizeof(char), SIZE_OF_SECTOR, binaryFile);
	fflush(binaryFile);

	disk1Log('r', blckNum);

	return 1;
}

void disk1Log(char rORw, int blckNum){
	
	location *result = determineLocation(blckNum);

	fprintf(logFile, "Side #%d Track #%d Sector #%d %c\n", result -> side, result -> track, result -> sector, rORw);
	fflush(logFile);
}

location *determineLocation(int blckNum){
	
	location *position = calloc(1, sizeof(location));

	if(((blckNum -1)/(NUM_OF_TRACKS * NUM_OF_SECTORS_PER_TRACK)) == 0){
		
		position -> side = 0;

		position -> track = blckNum / NUM_OF_SECTORS_PER_TRACK;
	}
	else{
		
		position -> side = 1;

		position -> track = (blckNum - 1439) / NUM_OF_SECTORS_PER_TRACK;
	}

	position -> sector = blckNum % NUM_OF_SECTORS_PER_TRACK;

	return position;
}

void fillBuffer(char inChar, char *inBuffer){
	
	int i;

	for(i=0; i < SIZE_OF_SECTOR;i++){
		
		inBuffer[i] = inChar;
	}
}

void readFat(){
	
	int i;

	char buffer[512];

	for(i = 1;i < 13;i++){
		
		readBlock(i, buffer);
		fillFat(buffer, (i-1)*512);
	}
	for( ;i < 25;i++){
		
		readBlock(i, buffer);
		fillFat(buffer, (i-13)*512);
	}	
}

void fillFat(char *inBuffer, int index){
	
	int i;

	if(index > 5631){
		
		for(i=0;i < 127;i++){
			
			FATp[i + index] = inBuffer[i];
		}
	}
	else{
		
		for(i=0;i < 512;i++){
			
			FATp[i + index] = inBuffer[i];
		}
	}
}

void writeFat(){
	
	char buffer[512];

	int i;

	for(i = 1;i < 13;i++){
		
		fillBufferFat(buffer, (i-1)*512);
		writeBlock(i, buffer);
	}
	for( ;i < 26;i++){
		
		fillBufferFat(buffer, (i-13)*512);
		writeBlock(i, buffer);
	}
}

void fillBufferFat(char *inBuffer, int index){
	
	int i;

	if(index > 5631){
		
		for(i=0;i < 127;i++){
		
			inBuffer[i] = FATp[i + index];
		}

		for( ;i < 512;i++){
			
			inBuffer[i] = '0';
		}
	}
	else{
		
		for(i=0;i < 512;i++){
			
			inBuffer[i] = FATp[i + index];
		}
	}
}

void writeRoot(){
	
	int i;

	char buffer[512];

	for(i = 26;i < 37;i++){
		
		fillRootBuffer(buffer, (i-26)*512);
		writeBlock(i, buffer);
	}
}

void readRoot(){
	
	int i;

	char buffer[512];

	for(i = 26;i < 37;i++){
		
		readBlock(i, buffer);
		fillRoot(buffer, (i-26)*512);
	}
}

void fillRootBuffer(char *inBuffer, int index){
	
	int i;

	if(index > 5119){
		
		for(i=0;i < 256;i++){
			
			inBuffer[i] = rootP[i + index];
		}

		for( ;i < 512;i++){
			
			inBuffer[i] = '9';
		}
	}
	else{
		
		for(i=0;i < 512;i++){
			
			inBuffer[i] = rootP[i + index];
		}
	}
}

void fillRoot(char *inBuffer, int index){

	int i;

	if(index > 5119){
		
		for(i=0;i < 256;i++){
			
			rootP[i + index] = inBuffer[i];
		}

	}
	else{
		
		for(i=0;i < 512;i++){
			
			rootP[i + index] = inBuffer[i];
		}
	}
}

void printBufferToScreen(char *inBuffer){
	
	int i;

	for(i=0; i < SIZE_OF_SECTOR;i++){
		
		printf("%c", inBuffer[i]);
	}
	printf("\n");
}

void printBufferToFile(char *inBuffer, FILE *fOut){
	
	int i;

	for(i=0; i < SIZE_OF_SECTOR;i++){
		
		fprintf(fOut, "%c", inBuffer[i]);
	}
	fprintf(fOut, "\n");
}

void helpToScreen(){
	
	printf("exit\n");
	printf("fillblock BlockNum char\n");
	printf("readblcok BlockNum\n");
	printf("batch InputFile OutputFile\n");
	printf("help\n");
	printf("create FileName\n");
	printf("del FileName\n");
	printf("format\n");
	printf("mkdir DirectoryName\n");
	printf("rmdir DirectoryName\n");
	printf("cd\n");
	printf("dir\n");
	printf("attrib [+r|-r] [+a|-a] [+s|-s] [+h|-h] FileName\n");
}

void helpToFile(FILE *fIn){
	
	fprintf(fIn, "exit\n");
	fprintf(fIn, "fillblock BlockNum char\n");
	fprintf(fIn, "readblcok BlockNum\n");
	fprintf(fIn, "batch InputFile OutputFile\n");
	fprintf(fIn, "help\n");
	fprintf(fIn, "create FileName\n");
	fprintf(fIn, "del FileName\n");
	fprintf(fIn, "format\n");
	fprintf(fIn, "mkdir DirectoryName\n");
	fprintf(fIn, "rmdir DirectoryName\n");
	fprintf(fIn, "cd\n");
	fprintf(fIn, "dir\n");
	fprintf(fIn, "attrib [+r|-r] [+a|-a] [+s|-s] [+h|-h] FileName\n");
}	
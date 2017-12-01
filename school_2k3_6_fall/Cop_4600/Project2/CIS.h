#include <stdio.h>
#include <time.h>

#define NUM_OF_SIDES 2
#define NUM_OF_TRACKS 80
#define NUM_OF_SECTORS_PER_TRACK 18
#define SIZE_OF_SECTOR 512
#define NUM_OF_BLOCKS (NUM_OF_TRACKS * NUM_OF_SECTORS_PER_TRACK * NUM_OF_SIDES)
#define NUM_OF_FILES 224
#define READ_ONLY 1
#define HIDDEN 2
#define SYSTEM 4
#define VOLUME_LABEL 16
#define DIRECTORY 32
#define ARCHIVE 64


void disk1Log(char, int);
void commandInterpreterSystem();
void fillBuffer(char, char *);
void printBufferToScreen(char *);
void printBufferToFile(char *, FILE *);
void initializeDisk();
void initializeRoot();
void helpToScreen();
void helpToFile(FILE *);
void *calloc(size_t, size_t);
void initializeFat();
void dirToScreen();
void dirToFile(FILE *);
void readFat();
void fillFat(char *, int);
void writeFat();
void fillBufferFat(char *, int);
void readRoot();
void writeRoot();
void fillRootBuffer(char *, int);
void fillRoot(char *, int);

int writeBlock(int, char *);
int readBlock(int, char *);
int fseek(FILE *, long, int);
int strcmp(const char *, const char *);
int atoi(const char *);
int batchFileProcess(char *, char *);
int format();
int createFile(char *);
int delFile(char *);
int makeDir(char *);
int delDir(char *);
int setAtt(char *, char *);
int findFirstFree();
int fileExists(char *);
int dirExists(char *);
int determineAtt(char *);

char *strtok(char *, const char *);
char *strcpy(char *, const char *);

size_t fread(void *, size_t, size_t, FILE *);
size_t fwrite(const void *, size_t, size_t, FILE *);

time_t time(time_t *);

struct locationIs *determineLocation(int);

FILE *fopen(const char *,const char *);

FILE *binaryFile, *logFile;

struct locationIs{

	int side;
	int track;
	int sector;
};

typedef struct locationIs location;

struct FCB{

	char name[9];
	char extension[4];
	char att;
	long lcdt;
	int size;
	short unsigned int start_block;
};


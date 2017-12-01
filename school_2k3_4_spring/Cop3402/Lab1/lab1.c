/***************************************************************

** Michael DePouw

** COP3402

** Assignment Number: 1

** Date:2-2-02

***************************************************************/

/****************Program Description***************************

**  requirements: input in a file call input.txt
**                2 strings per-line
**				  Either DEF or USE for the first string
**
**  The program reads in definitions and uses of these definitions.
**	The definitions are stored in a hash table using linked
**  listed for collision. Whenever a use is seen the ref_count
**	is increment.
**

**************************************************************/

#include <stdio.h>

FILE *ifp, *ofp;

#define NAME_LENGTH 10
#define COM_LENGTH 3	//command length
#define BKT_SIZE 19

struct ht_entry{
	char ht_name[NAME_LENGTH + 1]; //string used to store NAMES
	int ref_count; //reference count
	struct ht_entry *next;
};

typedef struct ht_entry NODE;

NODE hash_tbl[BKT_SIZE];
//initializes the hash_tbl and opens the input and output
void init();
//inserts nodes into the hash_tbl
void insert(char *, int);
//searches the hash and for a string and returns null if not found
NODE *search(char *, int *);
//calculates the hash index of a string
int hash(char *);
//prints out the hash_tbl
void tbl_flush();

int main(){

	char command[COM_LENGTH + 1];
	char inp_name[NAME_LENGTH + 1];

	init();

	int index;
	int *ind_ptr;
	ind_ptr = &index;

	while((fscanf(ifp,"%s%s", command, inp_name)) != EOF){
		fprintf(ofp, "%s \t %s\n", command, inp_name);
		//if DEF either insert of print error
		if(command[0] == 'D'){
			NODE *helper;
			helper = search(inp_name, ind_ptr);
			if(helper == NULL){
				insert(inp_name, *ind_ptr);
			}
			else{
				fprintf(ofp, "%s is all ready defined\n",inp_name);
			}
		}
		//else it's a USE command
		else{
			NODE *helper = search(inp_name, ind_ptr);

			if(helper == NULL){
				fprintf(ofp, "%s was used before it was defined \n", inp_name);
			}
			else{
				helper -> ref_count += 1;
			}
		}
	}
	tbl_flush();
	fclose(ifp);
	fclose(ofp);
	return 0;
}

void init(){
	int i;
	for(i=0;i<BKT_SIZE;i++){
		hash_tbl[i].ref_count = 0;
		hash_tbl[i].next = NULL;
		hash_tbl[i].ht_name[0] = '\0';

	}
	ifp = fopen("input.txt", "r");
	ofp = fopen("output.txt", "w");
}

void insert(char *inp_name, int hashIndex){
	//if array position is empty
	if(hash_tbl[hashIndex].ht_name[0] == '\0'){
		//copying string into the node
		int j=0;
		while(*(inp_name + j) != '\0'){
			hash_tbl[hashIndex].ht_name[j] = *(inp_name + j);
			j++;
		}
		hash_tbl[hashIndex].ht_name[j] = '\0';

	}
	else{
		NODE *helper = hash_tbl + hashIndex;
		//creating a new node
		NODE *newNode = (NODE *) malloc(sizeof(struct ht_entry));
		newNode -> next = NULL;
		newNode -> ref_count = 0;
		strcpy(newNode -> ht_name, inp_name);
		//finding end of the linked list
		while((helper -> next) != NULL){
			helper = helper -> next;
		}
		helper -> next = newNode;
	}
}

NODE *search(char *name_ptr, int *ind_ptr){
	*ind_ptr = hash(name_ptr);
	NODE *helper = hash_tbl + *ind_ptr;
	//if ht_name is not \0 then check array position
	if(hash_tbl[*ind_ptr].ht_name[0] != '\0'){
		int result;
		//comparing the two strings
		result = strcmp(name_ptr, helper -> ht_name);
		//if array position is not it search linked list
		if(result == 0)
			return helper;
		else{
			//searching linked list
			while(helper -> next != NULL){
				helper = helper -> next;
				result = strcmp(name_ptr, helper -> ht_name);
				if(result == 0)
					return helper;
			}
		}
	}
	//not found
	return NULL;
}

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
	return (sum % BKT_SIZE);
}

void tbl_flush(){
	int i;
	fprintf(ofp, "Hash Index \t Name \t Ref_Count\n");
	for(i=0;i<BKT_SIZE;i++){
		if(hash_tbl[i].ht_name[0] != '\0'){
			NODE *helper = hash_tbl + i;
			fprintf(ofp, "%d \t\t %s \t\t %d\n",i,helper -> ht_name, helper -> ref_count);
			while(helper -> next != NULL){
				helper = helper ->next;
				fprintf(ofp, "\t\t %s \t\t %d\n", helper -> ht_name, helper -> ref_count);
			}
		}
	}
}

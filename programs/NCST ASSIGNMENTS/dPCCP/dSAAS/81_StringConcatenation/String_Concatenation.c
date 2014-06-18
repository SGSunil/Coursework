#include<stdio.h>
int loc=0;
char str1[50],str2[50],result[100];

void strcopy(char *target,char *source);
void getstring(char *target);
void strcat(char *original,char *tobeconcat);

int main(int argc,char* argv[]){
	getstring(str1);
	getstring(str2);
	strcopy(result,str1);
	printf("location of loc %d ",loc);

	strcat(str1,str2);
	printf("your string is after concat %s from %s and %s \n",result,str1,str2);
}


//strcat func to concat one string to another avoiding duplicates.
void strcat(char *original,char *tobeconcat){
	printf("called strcat");
	int i=0,j=0,match=0;
	while(tobeconcat[i]!='\0'){
		while(original[j]!='\0'){
			if(tobeconcat[i]!=original[j])
				match=0;
			else {
				match=1; 
				break;
			}
			j++;
		}
		if(match==0){
			result[loc]=tobeconcat[i];
			loc++;
		}
		j=0;
		i++;	
	}
	result[loc]='\0';
}


//strcopy func to copy one string except '\0'.
void strcopy(char *target,char *source) {
	printf("inside strcopy\n");
	while(*source!='\0'){
		*target=*source;
		target++;
		source++;
		loc++;
	}
	*target='\0';
}

// getstring func to get space terminated string from std input.
void getstring(char *target){
	char ch;
	while(((ch=getchar())!=' ')){//&&(ch!='\n')){
		*target=ch;
		target++;
	}
	*target='\0';
	printf("%s inside getstring\n",target);
}

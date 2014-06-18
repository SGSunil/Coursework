#include<stdio.h>
#define _ERR_H
#undef _ERR_H

//subString: find whether str2 is a part of str1.
int subString(char *str1, char *str2){
	printf("called \"subString\"\n");
	char firstCh=str2[0];
	int i,len=strlen(str2);
	char res[len];
	char *cp;
	char *stro=str1;
	for(i=1;i<=len;i++){
		if((cp=strchr(stro,firstCh))){
			#ifdef _ERR_H
			printf("%d***%s\n",len,cp);
			#endif
			strncpy(res,cp,len);
			res[len]='\0';
			#ifdef _ERR_H
			printf("&&&%s&&&%s\n",cp,res);
			#endif
			if(strcmp(res,str2)==0){
				#ifdef _ERR_H
				printf("strcmp\n");
				#endif
				printf("returned \"subString\"\n");
				return 1;
			}
			stro=cp+1;
		}
	}
	printf("returned \"subString\"\n");
	return 0;
}

//shortestString: find the shortest string after concatenation.
int shortestString(char *str1, char *str2){
	printf("Called \"shortestString\"\n");
	char *first=str1+strlen(str1)-1,*second=str2;
	int len=strlen(str2),flag=0,i=0;
	printf("length of second string is %d and value of first is %s\n",len,first);

	if(!strcmp(str1,str2)){
		return 1;
		printf("equals and the result is %s\n",str1);
	}
	else{
		if(subString(str1,str2)){
			return 1;
			printf("%s is subset of %s\n",str2,str1);
		}
		else{
			for(i=1;i<len;i++){
				printf("inside for loop for %d time\n",i);
				printf("strcmp value %d \n",strncmp(str2,first,i));
				if(strncmp(str2,first,i)==0){
					flag=1;
					--first;
					printf("after %d pass first value is %s\n",i,first);
					continue;
				}
				else{
					--first;

				}
				if(flag==1){
					first=str1+strlen(str1)-(i);
					*++first='\0';
					strcat(first,str2);
					printf("first value after concat %s\n",first);
					return 1;
					//break;

				}	
				//printf("out for loop for %d time\n",i);
			}
			//printf("concatenated string is %s\n",str1);
				
		
		}

	}
	printf("return from  \"shortestString\"\n");
			return 0;
}



int main( ){
	char str1[150];
	char str2[150];
	char *f=str1;
	char *s=str2;
	fgets(str1,50,stdin);	
	fgets(str2,50,stdin);

	while(*f){
		if(*f=='\n'){
			*f='\0';
			break;
		}
		f++;
	}	

	while(*s){
		if(*s=='\n'){
			*s='\0';
			break;
		}
		s++;
	}	
	
	if(shortestString(str1,str2)){
		printf("result is %s\n",str1);
	}
	else{
		if(shortestString(str2,str1))	
		printf("result is %s\n",str2);
		else{
		strcat(str1,str2);
		printf("result is %s\n",str1);
		}

	}

	#ifdef _ERR_H
	printf("str1 is %s\nstr2 is %s\n",str1,str2);
	#endif

}

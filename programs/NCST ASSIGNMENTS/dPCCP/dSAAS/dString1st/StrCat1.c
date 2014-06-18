#include<stdio.h>
#define _ERR_H
#undef _ERR_H
int subString(char *str1, char *str2){
	char firstCh=str2[0];
	int i,len=strlen(str2);
	char res[len];
	char *cp;
	char *stro=str1;
	for(i=1;i<=len;i++){
		if((cp=(char *)strchr(stro,firstCh))){
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
				return 1;
			}
			stro=cp+1;
		}
	}
	return 0;
}
int shortestString(char *str1, char *str2,char *result){
	char *first=str1+strlen(str1)-1,*second=str2;
	char *resu=result;
	int len=strlen(str2),flag=0,i=0;
	if(!strcmp(str1,str2)){
		return 1;
	}
	else{
		if(subString(str1,str2)){
			return 1;
		}
		else{
			for(i=1;i<len;i++){
				if(strncmp(str2,first,i)==0){
					flag=1;
					--first;
					continue;
				}
				else{
					--first;

				}
				if(flag==1){
					first=str1+strlen(str1)-(i);
					*++first='\0';
					//resu=str1+strlen(str1)-(i);
					//*++resu='\0';
					strcat(first,str2);
			//printf("before cat value is %s\n",resu);
					//strcat(resu,str2);
			//printf("after value is %s\n",resu);
					//strcpy(result,first);
					return 1;
				}	
			}
		}

	}
			return 0;
}
int main( ){
char result1[150],res1[150],res2[150];
char result2[150];
	char str1[150]; char str2[150],c='i'; char *first=str1; char *second=str2; int a,b,len1,len2;
	while((c=getchar())!=' '){
		*first++=c;
	}
	*first='\0';
	while(((c=getchar())!=' ')&&(c!='\n')){
		*second++=c;
	}
	*second='\0';
					strcpy(result1,str1);
					strcpy(result2,str2);
/*	if(shortestString(str1,str2)){
		printf("%s\n",str1);
	}
	else{
		if(shortestString(str2,str1))	
		printf("%s\n",str2);
		else{
		strcat(str1,str2);
		printf("%s\n",str1);
		}
*/

	a=shortestString(str1,str2,result1);
	strcpy(res1,str1);
	strcpy(str1,result1);
	b=shortestString(str2,str1,result2);
	strcpy(res2,str2);
	if(a==1&&b==1){
		len1=strlen(res1);
		len2=strlen(res2);
		if(len1>len2){
			printf("hello\n");
			printf("%s\n",res2);
		}
		else{
			printf("bye\n%s\n",res1);
		}
	}
	else {
		if(a==1)
			printf("%s\n",res1);
		else
		if(b==1)
			printf("%s\n",res2);
		else
			printf("%s\n",strcat(str1,str2));
				

	}
}

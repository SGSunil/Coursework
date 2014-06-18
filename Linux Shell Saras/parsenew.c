#include "saras.h"
void rem_spaces(char *s)
{ char *p, temp [500];
   p=s;
  while(*p==' ' || *p=='\t') p++; 
  strcpy(temp,p);
  p= temp+ strlen(temp)-1;
  while(*p==' ' || *p=='\t') p--;
   *(p+1)='\0';
   strcpy(s, temp);
} 
void mygets(char *s) 
{  int i=-1; char c,prev=' ';
   while(1) {
   c=getchar();
   if(c=='\n' && prev!='\\') 
	   { s[++i]='\0'; return ; }
    prev= c;
   if(c=='\n') printf(">");
   if(c=='\\' || c=='\n') continue;
   if(++i >=100) { perror ("String too large"); exit(1);
	 }
   s[i]=c;
           }
}
int joblist(char *s)  {
  char *p; 
  jdx=-1;
  p= strtok(s, ";");
    while(p!=NULL) 
    {  
        rem_spaces(p);
      if(strlen(p)==0) continue;
        jdx++;
   if(jdx >=10) {// perror ("too many semicolons"); exit(1);
	return 5; }
        jpr[jdx]= (char *) malloc (100);
       strcpy(jpr[jdx],p);
       p= strtok(NULL, ";");
    }
}
void  parse(char *s){
  char *pr; 
  int i=0;
  pr= strtok(s, "|");
    while(pr!=NULL) 
    {  
        rem_spaces(pr);
        apr[i]= (char *) malloc (100);
       strcpy(apr[i++], pr);
       pr= strtok(NULL, "|");
    }
     idx=i-1;
     pr=strpbrk(apr[idx],"&");
      if( pr== apr[idx]+strlen(apr[idx])-1) 
      {bgflag=1; *pr='\0';}
       else bgflag=0;
      dissect ();
}

void dissect() {
  	int i=0,j=0;
        char fs[90], *pfi, *pfo;
  	char *pr, temp[90];
     for(i=0; i<=idx; i++)
      {
   	strcpy(jb[i].in, "");
   	strcpy(jb[i].out, "");
   	strcpy(fs, "");
        jb[i].flag=0;
  	pr=strpbrk(apr[i], "<>");
        if(pr)
        {            if(pr==apr[i])
                     {perror("No command \nSARAS:"); 
                     exit(1); }
          strcpy(fs, pr);
           *pr='\0';
        pfi=strpbrk(fs, "<");
        if(pfi) {  pfi++;
                pfi+=strspn(pfi," \t");
        	pfo=strpbrk(pfi, " \t<>");
                if(pfo==NULL)
                 strcpy(jb[i].in, pfi);
                 else 
                   strncpy (jb[i].in, pfi, pfo-pfi);
                 }
        pfi=strpbrk(fs, ">");
        if(pfi) { pfi++;
               if(*pfi!='>')
                { 
                 jb[i].flag=1;
                pfi+=strspn(pfi," \t");
        	pfo=strpbrk(pfi, " \t<>");
                if(pfo==NULL)
                 strcpy(jb[i].out, pfi);
                 else 
                   strncpy (jb[i].out, pfi, pfo-pfi);
                 }
               else
                 { pfi++;
                 jb[i].flag=2;
                pfi+=strspn(pfi," \t");
        	pfo=strpbrk(pfi, " <>");
                if(pfo==NULL)
                 strcpy(jb[i].out, pfi);
                 else 
                   strncpy (jb[i].out, pfi, pfo-pfi);
                 }
                 }

                 rem_spaces(jb[i].in);
                 rem_spaces(jb[i].out);
        }

	 j=0;
       	pr= strtok(apr[i], " \t");
       	while(pr!=NULL) {
		 jb[i].com[j]=(char*) malloc(90);
          	strcpy(jb[i].com[j], pr);  j++;
           	pr= strtok(NULL, " \t");
        }
       jb[i].com[j]=NULL;
     }
}
void logic() {
 int j,k;
   for(j=0; j<=idx; j++)
      { 
	printf("%d command\n",j+1);
	for(k=0; jb[j].com[k]!=NULL; k++)
          printf("%dth string %s \n",k, jb[j].com[k]);
          printf("infile=%s outfile=%s  flag=%d\n", jb[j].in, jb[j].out, jb[j].flag);
	}
          printf("bgflag=%d\n",bgflag);
}
/*
int main()
{ 
  char s[500];
   int j;
  while (1){
    printf("SARAS:>");
    mygets(s);
    rem_spaces(s);
   joblist(s);
   for(j=0; j<=jdx; j++)
   {
   if(strcmp(jpr[j], "exit")==0 ) goto  outer;
   if(strlen(jpr[j])==0) continue;
    printf("Job No. %d begins\n", j);
   parse(jpr[j]);
   logic();
   } // of for loop
  } // of while loop
  outer:;
}*/

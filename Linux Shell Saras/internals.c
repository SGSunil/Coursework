#include "saras.h"
//******************-fg():
void Kill(char *i)
{
	if(i==NULL)
		printf("bye, no process id to give a killing dose\n");
	else
	kill(-atoi(i),SIGKILL);
}

//******************-bg():
void bg(char *i){
	printf("%d ************\n",atoi(i));
	kill(-atoi(i),SIGTSTP);
}


//******************-bg1():
void bg1(int i)
{
	int x;
	x=kill(-i,SIGTSTP);
	printf("%d in integer and status is= %d************\n",i,x);
/*
        signal (SIGINT,  SIG_DFL);
        signal (SIGQUIT, SIG_IGN);
        signal (SIGTSTP, SIG_DFL);
        signal (SIGTTIN, SIG_DFL);
        signal (SIGTTOU, SIG_IGN);
        signal (SIGCHLD, SIG_DFL);
*/	
	waitpid(-1,NULL,0);
	x=kill(-shellpgid,SIGCONT);
	printf("kill status is= %d************\n",x);

	signal (SIGINT, SIG_DFL);
        signal (SIGQUIT, SIG_DFL);
        //signal (SIGTSTP, SIG_IGN);
        //signal (SIGTTIN, SIG_IGN);
        signal (SIGTTOU, SIG_IGN);
	
	int tty=open("/dev/tty",O_RDWR,0700);	
	write(tty,"ada",3);
	x=tcsetpgrp(tty,shellpgid);//i in place of getppid().
		
	printf("terminal given to process id= %d and status= %d\n",shellpgid,x);
}

//******************-fg():
void fg(char *i){
	if(i==NULL)
		printf("bye, no process id to bring to foreground\n");
	else{
	printf("%d ************shellpgid= %d\n",atoi(i),shellpgid);
	kill(-atoi(i),SIGCONT);

//**** code below resets the signals for our foreground job, it should accept all.
        signal (SIGINT, SIG_DFL);
        signal (SIGQUIT, SIG_DFL);
        signal (SIGTSTP, SIG_DFL);
        signal (SIGTTIN, SIG_IGN);
        signal (SIGTTOU, SIG_IGN);
        signal (SIGCHLD, SIG_DFL);

	int k=tcsetpgrp(STDIN_FILENO,atoi(i));
	printf("PARENT WAITING and k=%d***\n",k);
	waitpid(-atoi(i),NULL,0);
	printf("PARENT WAITED FOR ENOUGH TIME\n");

//**** code below resets the signals for our main shell, or they will be modified.
	signal (SIGINT, SIG_IGN);
        signal (SIGQUIT, SIG_IGN);
        signal (SIGTSTP, SIG_IGN);
        signal (SIGTTIN, SIG_IGN);
        signal (SIGTTOU, SIG_IGN); 
	tcsetpgrp(STDIN_FILENO,shellpgid);
	}
}


//*******************-get(): function for getting the value of any env. variable.
int get(char *s)
{
	char* res = (char*)calloc(100, sizeof(char));//allocate max buffer len for path
	if(getenv(s+1)!=NULL)
		strcpy(res,getenv(s+1));
	else
		res=NULL;
	if(res==NULL)
		printf("not found");
	else
		printf("%s",res);
	printf("\n");
}

//*******************-set(): function for setting the value of any env. variable.
int set(char *s)
{
	putenv(s);
}

//*******************-pwd(): function for getting the present working directory.
void pwd()
{
	printf("%s\n",getenv("PWD"));
}

//*******************-clear(): function for clear the screen.
void clear()
{
        //This function uses the vt1010
        printf("\033[2J");//clear the screen
        printf("\033[0;0f");//place the cursor on top left corner of screen

}

//*******************-echo(): function to show the inputs on the screen.
int echo(char **s)
{
	int i=0;
	if(*s==NULL)
	{
		printf("\n");
	}
	else
	{
		while(*s!=NULL)
		{
			if(**s=='$')
			{ // check for env. variable.
				get(*s);
				*s++;
			}
			else
			{
				if(i==0)		
					printf("%s",*s++);
				else
					printf(" %s",*s++);
				i++;
			}
		}
	}
}


//*******************-cd(): function to to change the user directory given as input.
int cd(char *dir)
{
		int flag;//check wether the directory is present or not
		char* buffer = (char*)calloc(1024, sizeof(char));//allocate max buffer len for path
		if(dir==NULL)
		{
			chdir(getenv("HOME"));//only cd command is given
		}
		else
		{
			flag=chdir(dir);//else change for given path
		}
		if(flag==-1)
		{
			perror("SARAS");//flag error if fault found
		}
		else
		{
			setenv("PWD",getcwd(buffer,1024),1);//set the present working directory
		}
		return 0;}


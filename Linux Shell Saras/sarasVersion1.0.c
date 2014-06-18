//*****************************************Headerfiles
#include"saras.h"

//***************************************************************
//int shellpgid;
int shellt=STDIN_FILENO; //shell terminal mode.

//***************************************************************
void logout(int status)
{
//	signal(SIGTSTP, logout);
	printf("ctrl+Z pressed for %d\n",getpid());
	bg1(getpid());

//	sleep(1);
	
}

//******************************************Pipeline- function for executing commands.

void pipeline(int i, int des)
{
	int fd[2], k=0,pid,j=0,index;
//****************variable for passing the reading end of prev cmd to nxt cmd.
	int red=0; 
	int in=0, out=1;
	int flagout=0, flagin=0;
	for(j=0; j< i; j++)
	{
				//printf(" in for  id= %d \n",getpid());
//**** close the write end of the pipe in the parent, which will  be used in child also
			close(fd[1]); 

			if(flagout==1)
			{
				red=0;
				close(fd[0]);
			}
			else
			{
//**** Assigns the read end to red to give to next command.
				red=fd[0]; 
			}

			if(j+1<i)
			{
				pipe(fd);	
				out=fd[1];
			}
			else
			{
//*****- for last comm. we need not to create a pipe, o/p redirect can only be to a file or screen.
				out=1;
			}

//*****- for the first command we have no previous command so, no redirection from pipe.
			if(j==0) 
				red=0;

			if(strcmp(jb[j].out,"")!=0)
			{
				close(fd[1]); // closes the write end of the pipe.
				int x;
				if(jb[j].flag==1)
					 x=open(jb[j].out,O_WRONLY | O_CREAT,0777);
				else
					 x=open(jb[j].out,O_RDWR|O_APPEND,0777);
				if(x==-1)
				{
					 perror("saras: ");
					//printf("%s doesn't exist in output, create it\n",jb[j].out);
				}
				out=x;
				flagout=1;
			}
			else 
				flagout=0;

			if(strcmp(jb[j].in,"")!=0)
			{
				
				//printf("%s* in input\n",jb[j].in);
				int x=open(jb[j].in,O_RDONLY,0777);
				if(x==-1) 
				{
					perror("saras");
					//printf("%s doesn't exist, create it\n",jb[j].in);
				}
				red=x;
			}

//*****- check for whether to send output of current command to pipe or not.
//****************because cd prints nothing so no output redirection and its reflection should be in the main shell we are working in.

//*****- if the current command is pwd(ours, not of a shell)/internals.
			if(!strcmp(jb[j].com[0],"pwd"))
			{
				pwd();
			}
//*****- if the current command is get(ours, not of a shell)/internals.
			else if(!strcmp(jb[j].com[0],"get"))
			{
				get(jb[j].com[1]);
			}
//*****- if the current command is cd/internals.
			else if(!strcmp(jb[j].com[0],"cd"))
			{
				cd(jb[j].com[1]);
			}
//*****- if the current command is set/internals.
			else if(!strcmp(jb[j].com[0],"set"))
			{
				set(jb[j].com[1]);	
			}
//*****- if the command can print or get input from the console or any command.		
			else if((pid=fork())==0)
			{
//printf("in forking CHILD= %d  pgid= %d and tcpgrp= %d \n",getpid(),getpgid(pid), tcgetpgrp(1));
	                       	 signal (SIGINT, SIG_DFL); 
        	       		 signal (SIGQUIT, SIG_DFL);
               			 //signal (SIGTSTP, logout);
               			 signal (SIGTTIN, SIG_DFL);
              	 		 signal (SIGTTOU, SIG_DFL);
               			 signal (SIGCHLD, SIG_DFL); 
				
				//printf(" in for fork id= %d \n",getpid());
//*****- check for whether to get input of current command from pipe or not.
				
				if(red!=0)
				{
					dup2(red,0);
					close(red);
				}
//*****- check for whether to send output of current command to pipe or not.
				if(out!=1)
				{
					close(1);
					dup2(out,1);
					close(out);
				}		
//*****- if the current command is echo/internals.
				if(!strcmp(jb[j].com[0],"echo"))
				{
					echo(jb[j].com+1 );
					printf("\n");
					exit(0);
				}
//*****- if the current command is clear/internals.
				else if(!strcmp(jb[j].com[0],"clear"))
				{
					clear( );
					exit(0);
				}
//*****- if the current command is from "EXTERNALS"..
				else
				{
					execvp(jb[j].com[0],jb[j].com);
					perror("saras");
					exit(1);
				}

	    		}
			else  waitpid(pid,NULL,0);
			
		}
		exit(0);
}
		




int main()
{
		signal (SIGINT, SIG_IGN); 
		signal (SIGQUIT, SIG_IGN); 
		signal (SIGTSTP, SIG_IGN); 
		signal (SIGTTIN, SIG_IGN); 
		signal (SIGTTOU, SIG_IGN); 
		//signal (SIGCHLD, SIG_IGN); 

	shellt=STDIN_FILENO;

//**** to check whether terminal is assigned to foreground job- our shell from the calling shell.
	while (tcgetpgrp (shellt) != (shellpgid = getpgrp())) 
	{
		kill (-shellpgid, SIGTTIN); 

	}

	//printf("shell pgid**** =%d\n",shellpgid);

	shellpgid=getpid();

	if(setpgid(shellpgid,shellpgid)<0){   // if making a separate group is success or fail.
		perror("SARAS: ");
	}

	tcsetpgrp(STDIN_FILENO,shellpgid); // 0 for terminal: our shell will control term from now.


	char ip[100]="omnamahshivay";

	putenv("PS1=>");

	while(1)
	{
		bgflag=0;
		int index=0, pid;

		char* pmpt=1+strrchr(getenv("PWD"),'/');
		printf("<SARAS: %s in %s%s ",getenv("USER"),strlen(pmpt)?pmpt:"/", getenv("PS1"));
		mygets(ip);
		rem_spaces(ip);
		joblist(ip);
		int j;
	for(j=0;j<=jdx;j++)
	{

		if((strcmp("exit", jpr[j])==0)||(strcmp("logout",jpr[j]))==0)
		{
			exit(0);
		}
		else
		{
			parse(jpr[j]);
		//	logic();
			for(index=0;index<idx+1;index++)
                        {
				if(!strcmp(jb[index].com[0],"Kill"))
				{
					Kill(jb[index].com[1]);
				}else
				if(!strcmp(jb[index].com[0],"bg"))
				{
					bg(jb[index].com[1]);
				}else
				if(!strcmp(jb[index].com[0],"fg"))
				{
					fg(jb[index].com[1]);
				}else
                                if(!strcmp(jb[index].com[0],"cd"))
                                {
                                        cd(jb[index].com[1]);
                                }else
                                if(!strcmp(jb[index].com[0],"set"))
                                {
                                        set(jb[index].com[1]);
				}else
                                if(!strcmp(jb[index].com[0],"get"))
                                {
                                        get(jb[index].com[1]);
				}
                        }

			if((pid=fork())==0){

				setpgid(0,0);  //***set group for child separate frm main.

				if(bgflag==1)
				{
					tcsetpgrp(STDIN_FILENO,getppid());

				}else
				{
          			 tcsetpgrp(STDIN_FILENO,pid);
	                       	 signal (SIGINT, SIG_DFL); 
        	       		 signal (SIGQUIT, SIG_DFL);
               			 signal (SIGTSTP, logout);
               			 signal (SIGTTIN, SIG_DFL);
              	 		 signal (SIGTTOU, SIG_DFL);
               			 signal (SIGCHLD, SIG_DFL); 
               			 signal (SIGHUP, SIG_DFL); // to prevent orphaned group. 
				}

				//sleep(1);

	//printf("IN PID= %d ,GRP ID(CHILD)= %d and TERM ID= %d\n",getpid(),getpgid(pid),tcgetpgrp(STDIN_FILENO));

				pipeline(idx+1,1);

				exit(0);
			}
			else
			{
				int x=0;
				x=setpgid(pid,pid);  //*** set grp f chld sep from main.

//printf("MAIN GRP ID(CHILD)= %d TERM ID= %d \n",getpgid(pid), tcgetpgrp(STDIN_FILENO));

				if(bgflag==1) // condition for background.
				{
					tcsetpgrp(STDIN_FILENO,getpid());
				//printf("IN BG=1,TERM ID= %d\n",tcgetpgrp(STDIN_FILENO));
				}
				else 
				{
					kill(-pid,SIGCONT);
					tcsetpgrp(STDIN_FILENO,pid);
				//printf("IN BG=0,TERM ID= %d\n",tcgetpgrp(STDIN_FILENO));
					waitpid(pid,NULL,0); 
				}
				bgflag=0;
			}	
		tcsetpgrp(STDIN_FILENO,getpid());
	//printf("IN MAIN AT LAST, TERM ID= %d\n",tcgetpgrp(STDIN_FILENO));
		}
	}
	}
}


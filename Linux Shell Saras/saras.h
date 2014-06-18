#ifndef SARAS_H
#define SARAS_H
#include <stdio.h>
#include<signal.h>
#include<fcntl.h>
#include<term.h>
#include<string.h>
#include <unistd.h>
#include <stdlib.h>
#include<sys/types.h>
#include<sys/wait.h>	

struct { 
	char *com[90]; 
	char in[90], out[90]; 
        int flag;
} jb[10];

int shellpgid;
int idx, bgflag;
char *apr[10];
char *jpr[10];
int jdx;
void Kill(char *i);
void bg1(int i);
void fg(char *i);
int get(char *s);
int set(char *s);
void pwd();
void clear();
int echo(char **s);
int cd(char *dir);
void dissect();
void rem_spaces(char *s);
void parse(char *s);
void mygets(char *s);
int joblist(char *s);
void logic() ;
void logout(int status);
void pipeline(int i, int des);
#endif

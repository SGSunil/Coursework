#include<stdio.h>
#include<string.h>
int R,C,i,j,x,y,moves;  char var=0;
const int r=100,c=100;
int main( ){
	scanf("%d%d",&R,&C); //printf("R is %d and C is %d\n",R,C);
	char Maze[r][c];
	int EMaze(char Maze[r][c],int x,int y, int a); //prototype of EMaze function.
	for(i=0;i<R;i++)
		for(j=0;j<2*C;  )
			if(((var=getchar())!=' ')||(var!='\n')){
				if(var=='#'){
					x=i;y=j/2;
				}
				Maze[i][j++/2]=var;
			}
			else j++/2;
		
//	printf("x is %d and y is %d\n",x,y);
/*	for(i=0;i<R;i++){
		for(j=0;j<C;j++)
			printf("%c ",Maze[i][j]);
		printf("\n");
	}
*/	
	EMaze(Maze,x,y,0);
	printf("%d\n",moves);
	
/*
	for(i=0;i<R;i++){
		for(j=0;j<C;j++)
			printf("%c ",Maze[i][j]);
		printf("\n");
	}
*/

}

int EMaze(char Maze[r][c],int x,int y,int a){
	if((x>=R)||(x<0)||(y>=C)||(y<0))
		return 0;
	else
	if(Maze[x][y]=='0')
		return 0;
	else
	if((Maze[x][y+1]=='@')||(Maze[x+1][y]=='@')||(Maze[x][y-1]=='@')||(Maze[x-1][y]=='@')){
		Maze[x][y]='0';
		moves=++a;
		return 0;
	}
	else{
	Maze[x][y]='0';
	EMaze(Maze,x,y+1,a+1);
	EMaze(Maze,x+1,y,a+1);
	EMaze(Maze,x-1,y,a+1);
	EMaze(Maze,x,y-1,a+1);
	}
	return 0;
}
	

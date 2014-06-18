import ncst.pgdst.*;
 class EMaze {
        static int count=0; static char maze[][];
        static int maxcol=0,maxrow=0;

        public static void main(String str[]) throws IOException {
        SimpleInput sin=new SimpleInput();
        maxrow=sin.readInt(); maxcol=sin.readInt();
        maxrow+=2; maxcol+=2;
        maze=new char[maxrow][maxcol];

        int hx=0,hy=0; int a=0;
        for(int i=0; i<maxrow; i++)
                for(int j=0;j<maxcol; j++)
                        maze[i][j]='0';

        for(int i=1; i<maxrow-1; i++)
                for(int j=1;j<maxcol-1; j++){
                        sin.skipWhite();
                        maze[i][j]=sin.readChar();

                }
        for(int i=1; i<maxrow-1; i++)
                for(int j=1;j<maxcol-1; j++){
                        if(maze[i][j]=='#'){
                                hx=i; hy=j; break;
                        }
                }
        for(int i=0; i<maxrow; i++){
                for(int j=0;j<maxcol; j++){
		System.out.print(maze[i][j]+" ");
			
		}
		System.out.println();
	}


        eMaze(hx,hy,0); //calling EMaze function.
		System.out.println();
		System.out.println();

        for(int i=0; i<maxrow; i++){
                for(int j=0;j<maxcol; j++){
		System.out.print(maze[i][j]+" ");
			
		}
		System.out.println();
	}

        System.out.println(EMaze.count); //printing result;
}

public static int eMaze(int i, int j, int a){

        if((i==0)|(i==maxrow)|(j==0)|(j==maxcol))
                return (0);
        else
        if((maze[i][j]=='0'))
                return (0);
        else
        if((maze[i][j+1]=='@')||(maze[i+1][j]=='@')||(maze[i-1][j]=='@')||(maze[i][j-1]=='@')){
                EMaze.count=++a;
        maze[i][j+1]='0';
        maze[i+1][j]='0';
        maze[i][j-1]='0';
        maze[i-1][j]='0';
        maze[i][j]='0';
                return (0);
        }
        else{
        maze[i][j]='0';
	if(maze[i][j+1]=='1')
        eMaze(i,j+1,a+1);
	if(maze[i+1][j]=='1')
        eMaze(i+1,j,a+1);
	if(maze[i][j-1]=='1')
        eMaze(i,j-1,a+1);
	if(maze[i-1][j]=='1')
        eMaze(i-1,j,a+1);
        return(0);
        }
        }
}


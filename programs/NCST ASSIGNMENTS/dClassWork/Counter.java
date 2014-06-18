import ncst.pgdst.*;

public class Queue {
        static Object queue[];
        static int first,last, size,count;

        public static void main(String str[]) throws IOException{

                SimpleInput sin=new SimpleInput();

                sin.skipWhite();
                System.out.println("enter the simulation time...");
                int s=sin.readInt();
                int c=0,store[]=new int[];
                Queue qu=new Queue(s);
                Character c,c1;
                int i=0,a=0;

                while(a=sin.readInt()!=-1){
                        store[i]=a; i++;
                }

                for(int j=0;j<i;j++)
                        System.out.print(" "+store[j]+" ");


/*
                while(i<s){
                sin.skipWhite();
                ch=sin.readChar();
                 c1=new Character(ch);
                qu.enQueue(c1);
                ++i;
                }

                i=0;

                while(i<s){
                c=(Character)qu.deQueue();
                System.out.println(c+"***");
                i++;
                }
*/

        }//end of main function.


        Queue(int size) {
        count=0;
        first=0;
        last=-1;
        this.size=size;
        queue=new Object[size];
        }

        static boolean isFull( ) {
                return (count>=queue.length);
        }

        static boolean isEmpty( ) {
                return (count==0);
        }

        static void  enQueue(Object obj) {
                if(!isFull()){
                        last=(last+1)%size;
                        queue[last]=obj;
                        count++;
                }
                else
                System.out.println("Queue is full, can't insert");
        }

        static Object deQueue( ) {
                if(!isEmpty()){
                        Object temp=new Object();
                        temp=queue[first];
                        first=(first+1)%size;
                        count--;
                        return (temp);
                }
        return (null);
        }


}


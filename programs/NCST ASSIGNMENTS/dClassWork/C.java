mport ncst.pgdst.*;

public class Queue {
        static Object queue[];
        static int first,last, size,count;

        public static void main(String str[]) throws IOException{

                SimpleInput sin=new SimpleInput();
                System.out.println("enter the simulation time...");
                int s=sin.readInt();
                int c=0;
                Integer c1;
                int store[]=new int[s*2];

                //Character c,c1;
                int i=0,a=0;

                while((a=sin.readInt())!=-1){
                        store[i]=a; i++;
                }

                Queue qu=new Queue(i);
                for(int j=0;j<i;j++)
                        System.out.print(" "+store[j]+" ");

                int j=0;

                while(j<i){
                 c1=new Integer(store[j]);
                qu.enQueue(c1);
                ++j;
                }

                j=0;

                while(j<=s){
                c1=(Integer)qu.deQueue();
                System.out.println(c1+"***");
                j++;
                }


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



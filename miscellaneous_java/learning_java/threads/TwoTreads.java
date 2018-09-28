package threads;

public class TwoTreads {
	public static void printNum(int x){	//the xth thread
		int i = 0;	//counting number
		while(true){
			System.out.println("Thread " + x + ": " + i++);	//task: print consecutive integers
			try{
				Thread.sleep(100);	//sleep for 0.1s
			}catch(Exception e){
				e.printStackTrace();
			}
			if(i >= 20){	//stop after 20 prints
				break;
			}
		}
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){	//implement Runnable
			public void run(){
				printNum(1);	//perform task
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				printNum(2);	//perform same task
			}
		});
		t1.start();
		t2.start();

	}

}

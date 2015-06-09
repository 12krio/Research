package mem;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;


public class MemState {

	public static void main(String[] args) {
	}

	public static void getInfo(){
		// TODO Auto-generated method stub
		System.out.println("------------------------------");

		Runtime runtime = Runtime.getRuntime();
//		Runtime#totalMemory()	JavaVMのメモリの総容量
//		Runtime#maxMemory()	JavaVMが使用しようとする最大メモリ容量
//		Runtime#freeMemory()	JavaVM内の空きメモリ容量		
		System.out.printf("総容量　　：%10d%n" , runtime.totalMemory()/1000000);
		System.out.printf("最大メモリ：%10d%n" , runtime.maxMemory()/1000000);
		System.out.printf("空きメモリ：%10d%n" , runtime.freeMemory()/1000000);
//		ただし、JNIでnativeメソッド（WindowsならDLL内の関数）を呼び出している場合、メモリは別管理される。[2007-03-26]
//				すなわち、上記のメソッドをJNI呼び出し元で使用した場合、呼出元のメモリしか取得できない。
//
//				それらも含めた全体を知りたい場合、Windowsならtasklistでプロセス全体の使用量を見ることが出来る。
		MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
		MemoryUsage usage = mbean.getHeapMemoryUsage();

		System.out.printf("初期サイズ：%10d%n", usage.getInit()/1000000);
		System.out.printf("使用サイズ：%10d%n", usage.getUsed()/1000000);
		System.out.printf("保証サイズ：%10d%n", usage.getCommitted()/1000000);	//JavaVMが使用できる最大サイズ
		System.out.printf("最大サイズ：%10d%n", usage.getMax()/1000000);	//理論上の最大限使用可能なサイズ	
		
	}
}

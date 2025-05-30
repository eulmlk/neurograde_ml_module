import java.util.Scanner;
public class Main {
	static int N;
	static int Ma;
	static int Mb;
	static int[]a;
	static int[]b;
	static int[]c;
    static int ca;
	static int cb;
	static final int INF=10000;
	static int ans;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		Ma=sc.nextInt();
		Mb=sc.nextInt();
		a=new int[N];
		b=new int[N];
		c=new int[N];
		for(int i=0;i<N;i++){
			a[i]=sc.nextInt();
			b[i]=sc.nextInt();
			c[i]=sc.nextInt();
		}
		int[][][]dp=new int[N+1][400][400];
		
		for(int i=0;i<N+1;i++){
			for(int j=0;j<400;j++){
				for(int k=0;k<400;k++){
					dp[i][j][k]=INF;
				}
			}
		}
		dp[0][0][0]=0;
		
		for(int i=0;i<N;i++){
			for(ca=0;ca<400;ca++){
				for(cb=0;cb<400;cb++){
					if(dp[i][ca][cb]!=INF){
						dp[i+1][ca][cb]=Math.min(dp[i+1][ca][cb], dp[i][ca][cb]);
						dp[i+1][ca+a[i]][cb+b[i]]=Math.min(dp[i+1][ca+a[i]][cb+b[i]],(dp[i][ca][cb]+c[i]));
					}
				}
			}
		}
		
		ans=INF;
		for(ca=1;ca<400;ca++){
			for(cb=1;cb<400;cb++){
				if(ca*Mb==Ma*cb)ans=Math.min(ans, dp[N][ca][cb]);
			}
		}
		
		if(ans!=INF)System.out.println(ans);
		else System.out.println(-1);
	}
	
}

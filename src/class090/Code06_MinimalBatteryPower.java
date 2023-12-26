package class090;

import java.util.Arrays;

// 执行所有任务的最少初始电量
// 每一个任务有两个参数，需要耗费的电量、至少多少电量才能开始这个任务
// 返回手机至少需要多少的初始电量，才能执行完所有的任务
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code06_MinimalBatteryPower {

	// 为了验证
	// 暴力递归
	// 时间复杂度O(n!)
	// 得到所有排列
	// 其中一定有所需电量最小的排列
	public static int power1(int[][] jobs) {
		return f1(jobs, jobs.length, 0);
	}

	public static int f1(int[][] jobs, int n, int i) {
		if (i == n) {
			int ans = 0;
			for (int[] job : jobs) {
				ans = Math.max(job[1], ans + job[0]);
			}
			return ans;
		} else {
			int ans = Integer.MAX_VALUE;
			for (int j = i; j < n; j++) {
				swap(jobs, i, j);
				ans = Math.min(ans, f1(jobs, n, i + 1));
				swap(jobs, i, j);
			}
			return ans;
		}
	}

	public static void swap(int[][] jobs, int i, int j) {
		int[] tmp = jobs[i];
		jobs[i] = jobs[j];
		jobs[j] = tmp;
	}

	// 正式方法
	// 贪心
	// 时间复杂度O(n * logn)
	public static int power2(int[][] jobs) {
		Arrays.sort(jobs, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
		int ans = 0;
		for (int[] job : jobs) {
			ans = Math.max(job[1], ans + job[0]);
		}
		return ans;
	}

	// 为了验证
	public static int[][] randomJobs(int n, int v) {
		int[][] jobs = new int[n][2];
		for (int i = 0; i < n; i++) {
			jobs[i][0] = (int) (Math.random() * v) + 1;
			jobs[i][1] = (int) (Math.random() * v) + 1;
		}
		return jobs;
	}

	// 为了验证
	public static void main(String[] args) {
		int N = 10;
		int V = 20;
		int testTimes = 2000;
		System.out.println("测试开始");
		for (int i = 1; i <= testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[][] jobs = randomJobs(n, V);
			int ans1 = power1(jobs);
			int ans2 = power2(jobs);
			if (ans1 != ans2) {
				System.out.println("出错了！");
				for (int[] job : jobs) {
					System.out.println(job[0] + " , " + job[1]);
				}
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
			if (i % 100 == 0) {
				System.out.println("测试到第" + i + "组");
			}
		}
		System.out.println("测试结束");
	}

}

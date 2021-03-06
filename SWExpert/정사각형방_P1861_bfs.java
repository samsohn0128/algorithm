package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 정사각형방_P1861_bfs {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	// 상하좌우

	static int T;
	static int N;
	static int[][] grid;
	static int cnt;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = -1;
			Node ansNode = new Node(-1, -1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 1;
					bfs(new Node(i, j));
					if (cnt > ans) {
						ans = cnt;
						ansNode.y = i;
						ansNode.x = j;
					} else if (cnt == ans) {
						if (grid[i][j] < grid[ansNode.y][ansNode.x]) {
							ansNode.y = i;
							ansNode.x = j;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + grid[ansNode.y][ansNode.x] + " " + ans);
		}

		br.close();
	}

	static void bfs(Node n) {
		q.offer(n);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (grid[ny][nx] == grid[temp.y][temp.x] + 1) {
						cnt++;
						q.offer(new Node(ny, nx));
					}
				}
			}
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
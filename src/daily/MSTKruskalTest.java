package daily;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTKruskalTest {
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}	
	}
	
static int[] parents;
	
	// 모든 원소의 부모를 자기자신으로
	private static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		
		parents[b] = a;
		return true;
	}
	
	static int V,E;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine(), " ");
		V = Integer.parseInt(token.nextToken());
		E = Integer.parseInt(token.nextToken());

		edgeList = new Edge[E];
		
		int start, weight, end;
		for (int i = 0; i < E; i++) {
			token = new StringTokenizer(in.readLine()," ");
			start = Integer.parseInt(token.nextToken());
			end = Integer.parseInt(token.nextToken());
			weight = Integer.parseInt(token.nextToken());
			edgeList[i] = new Edge(start, end, weight);
		}
		
		Arrays.sort(edgeList); // 오름차순 정렬
		
		make();
		
		// 간선을 하나씩 시도하면서 트리 만들기
		int cnt = 0, res=0;
		for (Edge edge : edgeList) {
			if(union(edge.start, edge.end)) {
				res += edge.weight;
				if(++cnt == V-1) break;	// 신장트리 완성
			}
		}
		
		System.out.println(res);
	}

}

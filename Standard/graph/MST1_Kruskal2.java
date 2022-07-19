package com.ssafy.graph;

import java.util.*;
/**
 * 시간 복잡도  E logE   => edig가 적은 경우 유리 
 * 완전 그래프의 E 수  v(v-1)/2  
..........[6]
.....13.....73..12
[3].28.............[0]
..24............67.17
....[1].62.........[4]
...................20
...............45....
..........[5]..37..[2]      
*/
public class MST1_Kruskal2 {
	public static int N=7;
	public static List<int[]> edge;
	public static int[] p;
	
	public static int findSet(int x) {
		if(p[x]==x) return x;
		else return p[x]=findSet(p[x]);
	}
	public static boolean union(int a,int b) {
		a=findSet(a);
		b=findSet(b);
		
		if (a ==b) return false;
		p[b]=a;
		return true;
	}
	public static int kruskal() {
		
//		1. Weight을 기준으로 Edig 정렬
		Collections.sort(edge,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
		});
		//for(int[] e:v) System.out.println(Arrays.toString(e));

//		2. makeset
		p=new int[N+1];
		for(int i=0; i<N+1; i++) p[i]=i;

//		3. Edge 연결
		int sum=0;
		int count = 0;
		for(int[] e : edge) {
			if(union(e[0],e[1])) {
				System.out.println("->"+Arrays.toString(e));
				sum=sum+e[2];
				if(++count == N-1) {
					break;
				}
			}
		}
		return sum;		
	}
	public static void main(String[] args) {
		edge=new ArrayList<int[]>();
		edge.add(new int[]{1,7,12});
		edge.add(new int[]{1,4,28});
		edge.add(new int[]{1,2,67});
		edge.add(new int[]{1,5,17});
		edge.add(new int[]{2,4,24});
		edge.add(new int[]{2,5,62});
		edge.add(new int[]{3,5,20});
		edge.add(new int[]{3,6,37});
		edge.add(new int[]{4,7,13});
		edge.add(new int[]{5,6,45});
		edge.add(new int[]{5,7,73});
		System.out.println(kruskal());
	}
}























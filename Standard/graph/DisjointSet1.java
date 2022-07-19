package com.ssafy.graph;

import java.util.Arrays;

public class DisjointSet1 {
	static int[] parent;		//root값을 저장하는 배열 
	
	/** 해당 노드의 root를 지정하는 함수   => 자기 자신이 root 노드  */
	public static void makeSet(int v) {
		parent[v] = v;
	}
	
	/** 해당 노드의 root를 찾는 함수 */
	public static int findSet(int v) {
//		종료 조건 : root를 찾으면 종료   => 배열 값이 자신이면 root
		if(v == parent[v]) {
			return v;			//root를 리턴
		}
		return findSet(parent[v]);			// 문제점: path가 길면 root를 찾는데 시간이 오래 걸림
	}
	
	public static void unionSet(int u, int v) {
//		int r1 = findSet(u);
//		int r2 = findSet(v);
//		parent[r1] = r2;
		
		parent[findSet(u)] = findSet(v);
	}
	public static void main(String[] args) {
		int N=6;
		parent = new int [N+1];
		for (int i = 1; i <=N; i++) {
			makeSet(i);
		}
		
		unionSet(4,  3);
		unionSet(6,  5);
		unionSet(1,  4);

		System.out.println(Arrays.toString(parent));
//		System.out.println(findSet(4));
//		System.out.println(findSet(3));
//		System.out.println(findSet(5));
//		System.out.println(findSet(6));
	}
}




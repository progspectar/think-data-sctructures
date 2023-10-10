package com.allendowney.thinkdast;

import java.util.Iterator;
import java.util.LinkedList;

class Graph {

    // No. of vertices
    private int V;

    // Array of lists for Adjacency
// List Representation
    private LinkedList<Integer> adj[];
   // private LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked")
    Graph(int v) {
        V = v;
        adj = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList();
        }
    }

    // Adds a relation as a two way edge of
// undirected graph.
    public void addRelation(int v, int w) {

        // Since indexing is 0 based, reducing
        // edge numbers by 1.
        v--;
        w--;
        adj[v].add(w);
        adj[w].add(v);
    }

    // Returns count of not visited nodes
// reachable from v using DFS.
    int countUtil(int v, boolean visited[]) {
        int count = 1;
        visited[v] = true;

        // Recur for all the vertices adjacent
        // to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                count = count + countUtil(n, visited);
        }
        return count;
    }

    // A DFS based function to Count number of
// existing groups and number of new groups
// that can be formed using a member of
// every group.
    void countGroups() {

        // Mark all the vertices as not
        // visited(set as false by default
        // in java)
        boolean visited[] = new boolean[V];
        int existing_groups = 0, new_groups = 1;

        for (int i = 0; i < V; i++) {

            // If not in any group.
            if (visited[i] == false) {
                existing_groups++;

                // Number of new groups that
                // can be formed.
                new_groups = new_groups *
                        countUtil(i, visited);
            }
        }

        if (existing_groups == 1)
            new_groups = 0;

        System.out.println("No. of existing groups are " +
                existing_groups);
        System.out.println("No. of new groups that " +
                "can be formed are " +
                new_groups);
    }

    // Driver code
    public static void main(String[] args) {
        int n = 4;

        // Create a graph given in
        // the above diagram
        Graph g = new Graph(n); // total 8 people
        g.addRelation(1, 2);
        g.addRelation(1, 3);
        g.addRelation(2, 3);
        g.countGroups();

//        g.addRelation(1, 4);
//        g.addRelation(1, 5);
//        g.addRelation(1, 6);
//
//        g.addRelation(2, 3);
//        g.addRelation(2, 7);
//        g.addRelation(2, 8);
//
//        g.addRelation(3, 7);
//        g.addRelation(3, 8);
//
//        g.addRelation(4, 5);
//        g.addRelation(4, 6);
//
//        g.addRelation(5, 6);
//        g.addRelation(7, 8);

//        // 1 and 2 are friends
//        g.addRelation(3, 4); // 3 and 4 are friends
//        g.addRelation(5, 6); // 5 and 6 are friends
//        •	1 and  (4) , 1 and  (5) , 1and  (6) ;
//•	2 and 3, 2 and 7, 2 and 8;
//•	3 and 7, 3 and 8;
//•	 (4)  and  (5) ,  (4)  and  (6) ;
//•	 (5)  and  (6) ;
//•	7 and 8.


    }
}
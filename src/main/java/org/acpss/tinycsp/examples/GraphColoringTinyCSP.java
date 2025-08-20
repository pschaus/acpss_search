/*
 * mini-cp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License  v3
 * as published by the Free Software Foundation.
 *
 * mini-cp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU Lesser General Public License  for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with mini-cp. If not, see http://www.gnu.org/licenses/lgpl-3.0.en.html
 *
 * Copyright (c)  2018. by Laurent Michel, Pierre Schaus, Pascal Van Hentenryck
 */

package org.acpss.tinycsp.examples;


import org.acpss.tinycsp.TinyCSP;
import org.acpss.tinycsp.Variable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example that illustrates how TinyCSP can be used to model
 * and solve the graph-coloring problem.
 */
public class GraphColoringTinyCSP {

    public static class GraphColoringInstance {

        public final int n;
        public final List<int []> edges;
        public final int maxColor;

        /**
         *
         * @param n the number of nodes with indices on {0,...,n-1}
         * @param edges a list of edges, an edge (a,b) encoded in a size two array [a,b]
         * @param maxColor, the maximum number of colors allowed in the solution, the allowed colors are {0...maxColor-1}
         */
        public GraphColoringInstance(int n, List<int []> edges, int maxColor) {
            this.n = n;
            this.edges = edges;
            this.maxColor = maxColor;
        }
    }


    public static void main(String[] args) {
        int n = 15;
        int maxColor = 4;
        int[][] edgePairs = {
                {14, 11}, {2, 1}, {0, 7}, {7, 12}, {0, 9},
                {7, 14}, {6, 3}, {6, 14}, {9, 13}, {12, 6},
                {6, 13}, {6, 0}, {5, 14}, {6, 10}, {9, 1},
                {3, 0}, {13, 11}, {9, 5}, {8, 10}, {0, 14},
                {13, 5}, {1, 3}, {8, 4}, {12, 13}, {11, 10},
                {3, 14}, {3, 13}, {10, 5}, {3, 4}, {9, 2}
        };
        List<int[]> edges = new ArrayList<>();
        for (int[] pair : edgePairs) {
            edges.add(pair);
        }
        GraphColoringInstance instance = new GraphColoringInstance(n, edges, maxColor);
        int [] solution= solve(instance);
    }

    /**
     * Useful if you want to visualize your solution
     * @param file where you want to store the solution
     * @param sol the color of each vertex
     * @param nCol the number of colors used
     */
    public static void writeSol(String file, int [] sol, int nCol) {
        try {
            FileWriter fw = new FileWriter(file+".sol");
            fw.write(nCol+" "+1+"\n");

            for (int i = 0; i < sol.length; i++) {
                fw.write(sol[i]+" ");
            }

            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Solve the graph coloring problem
     * @param instance a graph coloring instance
     * @return the color of each node such that no two adjacent nodes receive a same color,
     *         or null if the problem is unfeasible
     */
    public static int[] solve(GraphColoringInstance instance) {
        // TODO: solve the graph coloring problem using TinyCSP and return a solution
        // Hint: you can stop the search on first solution throwing and catching an exception
        //       in the onSolution closure or you can modify the dfs search
        // STUDENT throw new NotImplementedException("GraphColoringTinyCSP");
        // BEGIN STRIP

        int n = instance.n;

        TinyCSP csp = new TinyCSP();

        Variable[] color = new Variable[n];
        for (int i = 0; i < n; i++) {
            color[i] = csp.makeVariable(instance.maxColor);
        }

        for (int [] edge: instance.edges) {
            int i = edge[0];
            int j = edge[1];
            csp.notEqual(color[i],color[j],0); // not the same color for adjacent nodes
        }

        ArrayList<int []> solutions = new ArrayList<>();

        // find the first solution
        try {
            csp.dfsNary(solution -> {
                solutions.add(solution);
                throw new RuntimeException("stop"); // stop the search at first solution
            });
        } catch (RuntimeException stop) {
            return solutions.get(0);
        }
        return  null;

        // END STRIP
    }


}

package com.mounacheikhna.codejam.cj2008;

import java.io.*;
import java.util.*;

public class Milshakes {
    private MyScanner in;
    private PrintStream out;
    private HashMap<Integer, HashSet<Integer>> unmaltedPreferences;
    private int[] maltedPreferences;
    private HashSet<Integer> batchMalted;
    private HashSet<Integer> batchUnmalted;

    private void solve() throws IOException {
        int C = in.nextInt();
        for (int i = 0; i < C; i++) {
            out.print("Case #" + (i + 1) + ": ");
            solveCase();
        }
    }

    private void solveCase() throws IOException {
        unmaltedPreferences = new HashMap<>();
        batchMalted = new HashSet<>();
        batchUnmalted = new HashSet<>();

        int n = in.nextInt();
        int m = in.nextInt();
        maltedPreferences = new int[m];
        Arrays.fill(maltedPreferences, -1);

        for (int i = 0; i < m; i++) {
            int T = in.nextInt();
            HashSet<Integer> preferences = new HashSet<>();
            for (int j = 0; j < T; j++) {
                int flavor = in.nextInt();
                int malted = in.nextInt();
                if (malted == 1) {
                    maltedPreferences[i] = flavor;
                } else {
                    preferences.add(flavor);
                }
            }
            unmaltedPreferences.put(i, preferences);
        }

        batchMalted = new HashSet<>();
        batchUnmalted = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            batchUnmalted.add(i);
        }

        boolean recheck = true;

        while (recheck) {
            recheck = false;
            for (int i = 0; i < m; i++) {
                if (!checkCustomerSatisfied(i)) {
                    int maltedPref = maltedPreferences[i];
                    if (maltedPref > 0) {
                        if (!batchMalted.contains(maltedPref)) {
                            batchMalted.add(maltedPref);
                            batchUnmalted.remove(maltedPref);
                            recheck = true;
                        }
                    }
                }
            }
        }

        boolean allSatisfied;
        allSatisfied = true;
        for (int i = 0; i < m; i++) {
            if (!checkCustomerSatisfied(i)) {
                allSatisfied = false;
                break;
            }
        }

        if (allSatisfied) {
            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (batchMalted.contains(i))
                    ans.append("1 ");
                else
                    ans.append("0 ");
            }
            out.println(ans);
        } else
            out.println("IMPOSSIBLE");
    }

    private boolean checkCustomerSatisfied(int customerIndex) {
        HashSet<Integer> preferences = unmaltedPreferences.get(customerIndex);
        for (Integer p : preferences) {
            if (batchUnmalted.contains(p)) {
                return true;
            }
        }
        int maltedPref = maltedPreferences[customerIndex];
        return maltedPref > 0 && batchMalted.contains(maltedPref);
    }

    public static void main(String[] args) throws IOException {
        Milshakes milshakes = new Milshakes();
        milshakes.in = new MyScanner();
        milshakes.out = System.out;
        milshakes.solve();
    }

    static class MyScanner {
        Scanner inp = null;

        MyScanner() throws IOException {
            inp = new Scanner(System.in);
        }

        int nextInt() throws IOException {
            return inp.nextInt();
        }

    }

}
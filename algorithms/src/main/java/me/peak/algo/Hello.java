package me.peak.algo;

public class Hello {

    public static void main(String[] args) {
        int n = 9;
        boolean[] used = new boolean[n];
        StringBuffer str = new StringBuffer();
        find(used, str);
    }


    static void find(boolean[] used, StringBuffer str) {
        if (str.length() == used.length) {
            System.out.println(str);
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                str.append((i+1));
                used[i] = true;
                find(used, str);
                used[i] = false;
                str.deleteCharAt(str.length() - 1);
            }

        }
    }
}

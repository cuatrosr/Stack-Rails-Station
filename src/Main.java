

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        while (n != 0) {
            String arr = br.readLine();
            int[] arrNum = Stream.of(arr.split(" ")).mapToInt(Integer::parseInt).toArray();
            Stack<Integer> a = fillStack(n);
            Stack<Integer> s = new Stack<Integer>();
            n = checkRails(n, arrNum, a, s);
            n = Integer.parseInt(br.readLine());
            bw.write("\n");
        }
        bw.flush();
    }

    public static Stack<Integer> fillStack(int n) {
        Stack<Integer> a = new Stack<Integer>();
        for (int i = 1; i <= n; i++) {
            a.insertElementAt(i, 0);
        }
        return a;
    }

    public static int checkRails(int n, int[] b, Stack<Integer> a, Stack<Integer> s) throws IOException {
        boolean possible = true;
        int i = 0;
        while (i != n && possible) {
            if (!s.isEmpty() && s.peek() == b[i]) {
                s.pop();
                i++;
            } else if (!a.isEmpty()) {
                s.push(a.pop());
            } else {
                try {
                    boolean skip = false;
                    while (s.peek() != b[i] && !skip) {
                        if (!a.isEmpty()) {
                            s.push(a.pop());
                        } else {
                            skip = true;
                            possible = false;
                        }
                    }
                    s.pop();
                    i++;
                } catch (Exception e) {
                    possible = false;
                }
            }
        }
        String msg = (possible) ? "Yes\n" : "No\n";

        bw.write(msg);
        String newArr = br.readLine();

        try {
            int temp = Integer.parseInt(newArr);
            return temp;
        } catch (NumberFormatException e) {
            b = Stream.of(newArr.split(" ")).mapToInt(Integer::parseInt).toArray();
            return checkRails(n, b, fillStack(n), new Stack<>());
        }
    }
}

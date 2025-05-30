import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.NoSuchElementException;

public class Main {

  private int N;

  private void shot(BitSet S) {
    int cur = 0;
    boolean right = true;
    while (0 <= cur && cur < N) {
      if (S.get(cur)) {
        S.set(cur, false);
        if (right) {
          cur--;
        } else {
          cur++;
        }
        right = !right;
      } else {
        S.set(cur, true);
        if (right) {
          cur++;
        } else {
          cur--;
        }
      }
    }
  }

  private boolean isStripe(BitSet S) {
    boolean C = S.get(0);
    for (int i = 0; i < N; i++) {
      if ((i & 1) == 0 && S.get(i) != C) {
        return false;
      } else if ((i & 1) == 1 && S.get(i) == C) {
        return false;
      }
    }
    return true;
  }

  private void solve(FastScanner in, PrintWriter out) {
    N = in.nextInt();
    int K = in.nextInt();
    String s = in.next();
    BitSet S = new BitSet(N);
    for (int i = 0; i < N; i++) {
      S.set(i, s.charAt(i) == 'A');
    }

    int prev = -1;
    for (int k = 1; k <= K; k++) {
      shot(S);

      if (isStripe(S)) {
        if (prev < 0) {
          prev = k;
        } else {
          int cycle = k - prev;
          K -= k;
          K %= cycle;
          for (int i = 0; i < K; i++) {
            shot(S);
          }
          char[] o = new char[N];
          for (int i = 0; i < N; i++) {
            if (S.get(i)) {
              o[i] = 'A';
            } else {
              o[i] = 'B';
            }
          }
          out.println(new String(o));
          return;
        }
      }
    }
    char[] o = new char[N];
    for (int i = 0; i < N; i++) {
      if (S.get(i)) {
        o[i] = 'A';
      } else {
        o[i] = 'B';
      }
    }
    out.println(new String(o));
  }

  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out);
    new Main().solve(new FastScanner(), out);
    out.close();
  }

  private static class FastScanner {

    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int bufferLength = 0;

    private boolean hasNextByte() {
      if (ptr < bufferLength) {
        return true;
      } else {
        ptr = 0;
        try {
          bufferLength = in.read(buffer);
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (bufferLength <= 0) {
          return false;
        }
      }
      return true;
    }

    private int readByte() {
      if (hasNextByte()) {
        return buffer[ptr++];
      } else {
        return -1;
      }
    }

    private static boolean isPrintableChar(int c) {
      return 33 <= c && c <= 126;
    }

    private void skipUnprintable() {
      while (hasNextByte() && !isPrintableChar(buffer[ptr])) {
        ptr++;
      }
    }

    boolean hasNext() {
      skipUnprintable();
      return hasNextByte();
    }

    public String next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      StringBuilder sb = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        sb.appendCodePoint(b);
        b = readByte();
      }
      return sb.toString();
    }

    long nextLong() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      long n = 0;
      boolean minus = false;
      int b = readByte();
      if (b == '-') {
        minus = true;
        b = readByte();
      }
      if (b < '0' || '9' < b) {
        throw new NumberFormatException();
      }
      while (true) {
        if ('0' <= b && b <= '9') {
          n *= 10;
          n += b - '0';
        } else if (b == -1 || !isPrintableChar(b)) {
          return minus ? -n : n;
        } else {
          throw new NumberFormatException();
        }
        b = readByte();
      }
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    double[] nextDoubleArray(int n) {
      double[] array = new double[n];
      for (int i = 0; i < n; i++) {
        array[i] = nextDouble();
      }
      return array;
    }

    double[][] nextDoubleMap(int n, int m) {
      double[][] map = new double[n][];
      for (int i = 0; i < n; i++) {
        map[i] = nextDoubleArray(m);
      }
      return map;
    }

    public int nextInt() {
      return (int) nextLong();
    }

    public int[] nextIntArray(int n) {
      int[] array = new int[n];
      for (int i = 0; i < n; i++) {
        array[i] = nextInt();
      }
      return array;
    }

    public long[] nextLongArray(int n) {
      long[] array = new long[n];
      for (int i = 0; i < n; i++) {
        array[i] = nextLong();
      }
      return array;
    }

    public String[] nextStringArray(int n) {
      String[] array = new String[n];
      for (int i = 0; i < n; i++) {
        array[i] = next();
      }
      return array;
    }

    public char[][] nextCharMap(int n) {
      char[][] array = new char[n][];
      for (int i = 0; i < n; i++) {
        array[i] = next().toCharArray();
      }
      return array;
    }

    public int[][] nextIntMap(int n, int m) {
      int[][] map = new int[n][];
      for (int i = 0; i < n; i++) {
        map[i] = nextIntArray(m);
      }
      return map;
    }
  }
}

import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int h = in.nextInt();
		int w = in.nextInt();
		char[][] t = new char[h][w];
		for (int i=0; i<h; i++) {
			t[i] = in.next().toCharArray();
		}

		char[][] s = new char[h][w];
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				s[i][j] = 'x';
			}
		}

		int[] dx = { 0, 1, 1, 1, 0,-1,-1,-1};
		int[] dy = { 1, 1, 0,-1,-1,-1, 0, 1};
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {

				if (t[i][j] == '#')
					continue;

				for (int k=0; k<8; k++) {
					int ni = i + dy[k];
					int nj = j + dx[k];
					if (ni < 0 || nj < 0 || h <= ni || w <= nj) continue;
					s[ni][nj] = '.';
				}
			}
		}

		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				if (s[i][j] == 'x') {
					s[i][j] = t[i][j];
				}
			}
		}

		boolean f = true;

		outer:
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {

				if (s[i][j] == '.' && t[i][j] == '#') {

					boolean ff = false;
					for (int k=0; k<8; k++) {
						int ni = i + dy[k];
						int nj = j + dx[k];
						if (ni < 0 || nj < 0 || h <= ni || w <= nj) continue;
						if (s[ni][nj] == '#') {
							ff = true;
						}
					}

					if (!ff) {
						f = false;
						break outer;
					}
				}
			}
		}

		dump(s);

		if (!f) {
			out.println("impossible");
			return;
		}

		out.println("possible");
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				out.print(s[i][j]);
			}
			out.println();
		}
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.nanoTime();

		solve();
		out.flush();

		long end = System.nanoTime();
		dump((end - start) / 1000000 + " ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}
#include<iostream>
#include<stack>

using namespace std;

static const int N = 100;
static const int WHITE = 0;
static const int GRAY = 1;
static const int BLACK = 2;

int n, M[N][N];
int color[N], d[N], f[N], tt;
int nt[N];

int next(int u) {
  for (int v = nt[u]; v < n; v++) {
    nt[u] = v + 1;
    if (M[u][v]) {
      return v;
    }
  }
  return -1;
}

void dfs_visit(int r) {
  for (int i = 0; i < n; i++) {
    nt[i] = 0;
  }

  stack<int> S;
  S.push(r);
  color[r] = GRAY;
  d[r] = ++tt;

  while(!S.empty()) {
    int u = S.top();
    int v = next(u);
    if (v != -1) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        d[v] = ++tt;
        S.push(v);
      }
    } else {
      S.pop();
      color[u] = BLACK;
      f[u] = ++tt;
    }
  }
}

void dfs() {
  for (int i = 0; i < n; i++) {
    color[i] = WHITE;
    nt[i] = 0;
  }
  tt = 0;

  for (int u = 0; u < n; u++) {
    if (color[u] == WHITE) {
      dfs_visit(u);
    }
  }

  for (int i = 0; i < n; i++) {
    cout << i + 1 << " " << d[i] << " " << f[i] << endl;
  }
}

int main() {

  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      M[i][j] = 0;
    }
  }

  int u, k, v;
  for (int i = 0; i < n; i++) {
    cin >> u >> k;
    u--; // to 0 origin
    for (int j = 0; j < n; j++) {
      cin >> v;
      v--; // to 0 origin
      M[u][v] = 1;
    }
  }

  dfs();

  return 0;
}

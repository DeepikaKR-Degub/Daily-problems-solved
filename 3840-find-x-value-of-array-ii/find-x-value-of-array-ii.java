import java.util.*;

class Solution {

    int n;
    int k;

    // Segment tree
    Node[] tree;

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Query from start to n-1
            Node ans = query(1, 0, n - 1, start, n - 1);

            result[q] = ans.cnt[x];
        }

        return result;
    }

    // Build segment tree
    void build(int node, int left, int right, int[] nums) {

        tree[node] = new Node(k);

        if (left == right) {

            int rem = nums[left] % k;

            tree[node].prod = rem;

            // The only prefix is the element itself
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two consecutive segments
    Node merge(Node A, Node B) {

        Node C = new Node(k);

        // Product of complete segment
        C.prod = (int) ((long) A.prod * B.prod % k);

        // Prefixes entirely inside A
        for (int r = 0; r < k; r++) {
            C.cnt[r] += A.cnt[r];
        }

        // Prefixes that include all of A and then part of B
        for (int r = 0; r < k; r++) {

            if (B.cnt[r] == 0)
                continue;

            int newRem = (int) ((long) A.prod * r % k);

            C.cnt[newRem] += B.cnt[r];
        }

        return C;
    }

    // Point update
    void update(int node, int left, int right, int index, int value) {

        if (left == right) {

            int rem = value % k;

            tree[node].prod = rem;

            Arrays.fill(tree[node].cnt, 0);
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    Node query(int node, int left, int right, int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node A = query(node * 2, left, mid, ql, qr);
        Node B = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(A, B);
    }
}
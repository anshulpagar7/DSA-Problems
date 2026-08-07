class Solution {
    const int f2[10] = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    const int f3[10] = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
    const int f5[10] = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    const int f7[10] = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

    long long minDigits(long long c2, long long c3, long long c5, long long c7) {
        c2 = max(0LL, c2);
        c3 = max(0LL, c3);
        c5 = max(0LL, c5);
        c7 = max(0LL, c7);

        long long cnt = c5 + c7;
        long long n9 = c3 / 2;
        long long r3 = c3 % 2;
        long long n8 = c2 / 3;
        long long r2 = c2 % 3;

        cnt += n9 + n8;
        if (r3 == 1 && r2 == 1) cnt += 1;
        else if (r3 == 1 && r2 == 2) cnt += 2;
        else if (r3 == 1 && r2 == 0) cnt += 1;
        else if (r3 == 0 && r2 == 2) cnt += 1;
        else if (r3 == 0 && r2 == 1) cnt += 1;

        return cnt;
    }

public:
    string smallestNumber(string num, long long t) {
        long long need2 = 0, need3 = 0, need5 = 0, need7 = 0;
        long long temp_t = t;
        while (temp_t % 2 == 0) { need2++; temp_t /= 2; }
        while (temp_t % 3 == 0) { need3++; temp_t /= 3; }
        while (temp_t % 5 == 0) { need5++; temp_t /= 5; }
        while (temp_t % 7 == 0) { need7++; temp_t /= 7; }

        if (temp_t > 1) return "-1";

        int n = num.length();
        int z = n;
        for (int i = 0; i < n; ++i) {
            if (num[i] == '0') {
                z = i;
                break;
            }
        }

        if (z == n) {
            long long c2 = 0, c3 = 0, c5 = 0, c7 = 0;
            for (char ch : num) {
                int d = ch - '0';
                c2 += f2[d]; c3 += f3[d]; c5 += f5[d]; c7 += f7[d];
            }
            if (c2 >= need2 && c3 >= need3 && c5 >= need5 && c7 >= need7) {
                return num;
            }
        }

        vector<long long> p2(n + 1, 0), p3(n + 1, 0), p5(n + 1, 0), p7(n + 1, 0);
        for (int i = 0; i < z; ++i) {
            int d = num[i] - '0';
            p2[i + 1] = p2[i] + f2[d];
            p3[i + 1] = p3[i] + f3[d];
            p5[i + 1] = p5[i] + f5[d];
            p7[i + 1] = p7[i] + f7[d];
        }

        int max_i = min(n - 1, z);
        for (int i = max_i; i >= 0; --i) {
            int start_d = (i == z) ? 1 : (num[i] - '0' + 1);
            long long cur2 = p2[i], cur3 = p3[i], cur5 = p5[i], cur7 = p7[i];

            for (int d = start_d; d <= 9; ++d) {
                long long rem2 = need2 - cur2 - f2[d];
                long long rem3 = need3 - cur3 - f3[d];
                long long rem5 = need5 - cur5 - f5[d];
                long long rem7 = need7 - cur7 - f7[d];

                int rem_len = n - 1 - i;
                if (minDigits(rem2, rem3, rem5, rem7) <= rem_len) {
                    string res = num.substr(0, i) + to_string(d);
                    long long r2 = rem2, r3 = rem3, r5 = rem5, r7 = rem7;

                    for (int pos = 0; pos < rem_len; ++pos) {
                        int req_len = rem_len - 1 - pos;
                        for (int cd = 1; cd <= 9; ++cd) {
                            if (minDigits(r2 - f2[cd], r3 - f3[cd], r5 - f5[cd], r7 - f7[cd]) <= req_len) {
                                res += to_string(cd);
                                r2 -= f2[cd]; r3 -= f3[cd]; r5 -= f5[cd]; r7 -= f7[cd];
                                break;
                            }
                        }
                    }
                    return res;
                }
            }
        }

        long long min_d = minDigits(need2, need3, need5, need7);
        long long L = max((long long)n + 1, min_d);

        string res = "";
        long long r2 = need2, r3 = need3, r5 = need5, r7 = need7;
        for (int pos = 0; pos < L; ++pos) {
            long long req_len = L - 1 - pos;
            for (int cd = 1; cd <= 9; ++cd) {
                if (minDigits(r2 - f2[cd], r3 - f3[cd], r5 - f5[cd], r7 - f7[cd]) <= req_len) {
                    res += to_string(cd);
                    r2 -= f2[cd]; r3 -= f3[cd]; r5 -= f5[cd]; r7 -= f7[cd];
                    break;
                }
            }
        }

        return res;
    }
};
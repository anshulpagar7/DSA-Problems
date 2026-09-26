#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        unordered_map<string, string> dict;
        for (const auto& entry : knowledge) {
            dict[entry[0]] = entry[1];
        }

        string result = "";
        string currentKey = "";
        bool inBracket = false;

        for (char c : s) {
            if (c == '(') {
                inBracket = true;
                currentKey.clear();
            } else if (c == ')') {
                inBracket = false;
                auto it = dict.find(currentKey);
                if (it != dict.end()) {
                    result += it->second;
                } else {
                    result += '?';
                }
            } else {
                if (inBracket) {
                    currentKey += c;
                } else {
                    result += c;
                }
            }
        }

        return result;
    }
};
/* REGULAR EXPRESSION PATTERN MATCHING  */


#include <iostream>
#include <regex>

using namespace std;

bool matchPattern(const string& pattern, const string& str) {
    regex regexPattern(pattern);
    return regex_match(str, regexPattern);
}

int main() {
    string pattern, str;

    cout << "Enter your regular expression pattern: ";
    getline(cin, pattern);

    cout << "Enter the string to match: ";
    getline(cin, str);

    if (matchPattern(pattern, str)) {
        cout << "Match found!" << endl;
    } else {
        cout << "No match found." << endl;
    }

    return 0;
}

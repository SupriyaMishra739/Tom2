#include <iostream>
#include <string>
using namespace std;

string process(string text, int shift) {
    for (char& c : text) {
        if (isalpha(c)) {
            char base = islower(c) ? 'a' : 'A';
            c = (c - base + shift) % 26 + base;
        }
    }
    return text;
}

int main() {
    string text;
    int shift;

    cout << "Enter a string: ";
    getline(cin, text);
    cout << "Enter shift value: ";
    cin >> shift;

    string encrypted = process(text, shift);
    cout << "Encrypted text: " << encrypted << endl;

    cout << "Decrypted text: " << process(encrypted, 26 - shift) << endl;

    return 0;
}

#include <iostream>
#include <string>
using namespace std;

int main()
{
    int key;
    string text;

    cout << "Please enter the encryption key: ";
    cin >> key;
    cin.ignore(); // Ignore the newline character left by cin

    cout << "Please enter a sentence: ";
    getline(cin, text);

    for (int i = 0; i < text.length(); i++)
    {
        text[i] = text[i] ^ key; // XOR encryption
    }

    cout << "The encrypted message is: " << text << endl;

    for (int i = 0; i < text.length(); i++)
    {
        text[i] = text[i] ^ key; // XOR decryption
    }

    cout << "The decrypted message is: " << text << endl;

    return 0;
}

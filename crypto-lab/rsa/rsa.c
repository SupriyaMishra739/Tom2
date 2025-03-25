

#include <stdio.h>

long long gcd(long long a, long long b)
{
    return b ? gcd(b, a % b) : a;
}

long long modExp(long long base, long long exp, long long mod)
{
    long long result = 1;
    while (exp)
    {
        if (exp % 2)
            result = (result * base) % mod;
        base = (base * base) % mod;
        exp /= 2;
    }
    return result;
}

long long modInverse(long long e, long long phi)
{
    for (long long d = 1; d < phi; d++)
        if ((e * d) % phi == 1)
            return d; // Finds d where (e*d) % phi = 1
    return -1;
}

int isPrime(long long num)
{
    for (long long i = 2; i * i <= num; i++)
    {
        if (num % i == 0)
            return 0;
    }
    return num > 1;
}

long long nextPrime(long long start)
{
    while (!isPrime(start))
        start++;
    return start;
}

int main()
{
    long long p = nextPrime(53);
    long long q = nextPrime(61);
    long long n = p * q;
    long long phi = (p - 1) * (q - 1); // (Euler's totient function).

    // Choose Public Key (e)
    long long e = 3; // gcd(e, φ(n)) = 1
    while (gcd(e, phi) != 1)
        e++;

    // d= private key, such that (e * d) % phi = 1.
    long long d = modInverse(e, phi);

    long long message, ciphertext;
    // Displays public and private keys.
    printf("Public Key: (%lld, %lld)\n", e, n);
    printf("Private Key: (%lld, %lld)\n", d, n);
    printf("Enter message: ");

    scanf("%lld", &message);

    // Ensures message is smaller than n (RSA requires this).
    if (message >= n)
    {
        printf("Message must be smaller than n!\n");
        return 1;
    }
    // Encrypts the message using modular exponentiation:
    ciphertext = modExp(message, e, n);
    printf("Encrypted: %lld\n", ciphertext);
    // Decrypts using the private key d:
    printf("Decrypted: %lld\n", modExp(ciphertext, d, n));

    return 0;
}
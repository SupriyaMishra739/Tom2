import java.math.BigInteger;
import java.util.Scanner;

public class rsa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for prime numbers p and q
        System.out.print("Enter prime number p: ");
        BigInteger p = scanner.nextBigInteger();
        System.out.print("Enter prime number q: ");
        BigInteger q = scanner.nextBigInteger();

        // Key Generation
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("2");
        while (e.gcd(phi).compareTo(BigInteger.ONE) != 0) e = e.add(BigInteger.ONE);
        BigInteger d = e.modInverse(phi);

        System.out.println("Public Key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private Key: (d = " + d + ", n = " + n + ")");

        // Encrypt/Decrypt
        System.out.print("Enter a message (integer form): ");
        BigInteger M = scanner.nextBigInteger();
        BigInteger C = M.modPow(e, n);  // Encryption
        System.out.println("Encrypted Message: " + C);
        System.out.println("Decrypted Message: " + C.modPow(d, n));  // Decryption

        scanner.close();
    }
    }

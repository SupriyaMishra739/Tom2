import java.math.BigInteger;
import java.util.Scanner;

public class dh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime p: ");
        BigInteger p = new BigInteger(sc.nextLine());
        System.out.print("Enter generator g: ");
        BigInteger g = new BigInteger(sc.nextLine());

        System.out.print("Enter private key for Alice : ");
        BigInteger a = new BigInteger(sc.nextLine());
        System.out.print("Enter private key for Bob: ");
        BigInteger b = new BigInteger(sc.nextLine());

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);
        System.out.println("Public Key of ABC is :"+A);
        System.out.println("Public Key of XYZ is :"+B);

        BigInteger secretA = B.modPow(a, p);
        BigInteger secretB = A.modPow(b, p);

        System.out.println("Message computed by Alice: " + secretA);
        System.out.println("Message computed by Bob: " + secretB);
        System.out.println(secretA.equals(secretB) ? "secrete Messages Match!" : "Keys do not match.");

        sc.close();
    }
}

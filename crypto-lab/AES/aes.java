import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class aes {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the message to encrypt: ");
            String message = scanner.nextLine();

            System.out.print("Enter a 16-character key: ");
            String keyString = scanner.nextLine();
            if (keyString.length() != 16) {
                System.out.println("Key must be exactly 16 characters!");
                return;
            }

            // Create key
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "AES");

            // Encrypt
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
            System.out.println("Encrypted Message: " + encrypted);

            // Decrypt
            cipher.init(Cipher.DECRYPT_MODE, key);
            String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
            System.out.println("Decrypted Message: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
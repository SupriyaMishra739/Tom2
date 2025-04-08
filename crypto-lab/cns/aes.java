import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class aes {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // User inputs for data and key
        System.out.print("Enter data: ");
        String data = sc.nextLine();
        System.out.print("Enter 16-character key: ");
        String keyInput = sc.nextLine();
        
        if (keyInput.length() != 16) {
            System.out.println("Key must be 16 characters.");
            return;
        }
       
        // AES encryption and decryption
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(keyInput.getBytes(), "AES");
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
        
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        sc.close();
    }
}


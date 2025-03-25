public class SimpleAES {
    private static final int[] S_BOX = {
        0x9, 0x4, 0xA, 0xB, 0xD, 0x1, 0x8, 0x5,
        0x6, 0x2, 0x0, 0x3, 0xC, 0xE, 0xF, 0x7
    };
    
    private static final int[] INV_S_BOX = {
        0xA, 0x5, 0x9, 0xB, 0x1, 0x7, 0x8, 0xF,
        0x6, 0x0, 0x2, 0x3, 0xC, 0x4, 0xE, 0xD
    };
    
    private static final int[] ROUND_KEY = {0x2, 0x7, 0xC, 0xA};
    
    private static int subByte(int byteVal) {
        return S_BOX[byteVal & 0xF];
    }
    
    private static int invSubByte(int byteVal) {
        return INV_S_BOX[byteVal & 0xF];
    }
    
    private static void shiftRows(int[] state) {
        int temp = state[1];
        state[1] = state[3];
        state[3] = temp;
    }
    
    private static void invShiftRows(int[] state) {
        int temp = state[1];
        state[1] = state[3];
        state[3] = temp;
    }
    
    private static void mixColumns(int[] state) {
        state[0] ^= state[1];
        state[2] ^= state[3];
    }
    
    private static void addRoundKey(int[] state) {
        for (int i = 0; i < state.length; i++) {
            state[i] ^= ROUND_KEY[i];
        }
    }
    
    public static int[] encrypt(int[] plaintext) {
        int[] state = plaintext.clone();
        
        for (int i = 0; i < state.length; i++) {
            state[i] = subByte(state[i]);
        }
        
        shiftRows(state);
        mixColumns(state);
        addRoundKey(state);
        
        return state;
    }
    
    public static int[] decrypt(int[] ciphertext) {
        int[] state = ciphertext.clone();
        
        addRoundKey(state);
        mixColumns(state);
        invShiftRows(state);
        
        for (int i = 0; i < state.length; i++) {
            state[i] = invSubByte(state[i]);
        }
        
        return state;
    }
    
    public static void main(String[] args) {
        int[] plaintext = {0x3, 0xA, 0xC, 0x4};
        System.out.println("Plaintext: ");
        printArray(plaintext);
        
        int[] encrypted = encrypt(plaintext);
        System.out.println("Encrypted: ");
        printArray(encrypted);
        
        int[] decrypted = decrypt(encrypted);
        System.out.println("Decrypted: ");
        printArray(decrypted);
    }
    
    private static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(Integer.toHexString(val) + " ");
        }
        System.out.println();
    }
}
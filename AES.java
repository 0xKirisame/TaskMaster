package taskMaster;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class AES {
    private String key;
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    
    public AES(String key) {
        this.key = key;
    }

    /**
     * Derives a 256-bit AES key from the password using SHA-256
     */
    private SecretKey deriveKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(password.getBytes("UTF-8"));
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
    }

    /**
     * Encrypts plaintext string using AES-256-CBC
     */
    public String encrypt(String plainText) {
        if (key.isEmpty() || plainText == null) return plainText;
        
        try {
            SecretKey secretKey = deriveKey(key);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            // Generate a fixed IV from the key for deterministic encryption
            // In production, use a random IV and prepend it to the ciphertext
            byte[] ivBytes = Arrays.copyOf(deriveKey(key).getEncoded(), 16);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            
            // Convert to Base64-like representation using hex for readability
            return bytesToHex(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return plainText;
        }
    }

    /**
     * Decrypts ciphertext string using AES-256-CBC
     */
    public String decrypt(String cipherText) {
        if (key.isEmpty() || cipherText == null) return cipherText;
        
        try {
            SecretKey secretKey = deriveKey(key);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            // Use the same fixed IV as encryption
            byte[] ivBytes = Arrays.copyOf(deriveKey(key).getEncoded(), 16);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = hexToBytes(cipherText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return cipherText;
        }
    }

    /**
     * Static method to encrypt/decrypt a file in place using AES
     * This method reads the file, encrypts/decrypts it, and writes it back
     */
    public static void encrypt(String fileName, String password) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        // Read all bytes from file
        byte[] fileContent = Files.readAllBytes(Paths.get(fileName));
        
        // Derive key from password
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(password.getBytes("UTF-8"));
        SecretKey secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        
        // Generate IV from key (fixed IV for simplicity)
        byte[] ivBytes = Arrays.copyOf(keyBytes, 16);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        
        // Detect if file is encrypted by checking a marker
        // We'll use a simple marker: if decryption succeeds, it was encrypted
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        
        try {
            // Try to decrypt first
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decrypted = cipher.doFinal(fileContent);
            
            // If successful, write decrypted content back
            Files.write(Paths.get(fileName), decrypted);
        } catch (Exception e) {
            // If decryption fails, the file is not encrypted, so encrypt it
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encrypted = cipher.doFinal(fileContent);
            
            // Write encrypted content back
            Files.write(Paths.get(fileName), encrypted);
        }
    }

    /**
     * Convert byte array to hex string
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Convert hex string to byte array
     */
    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                 + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

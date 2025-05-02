package taskMaster;

public class XOR {
    private String key;
    
    public XOR(String key) {
        this.key = key;
    }

    public String encrypt(String plainText) {
        if (key.isEmpty() || plainText == null) return plainText;
        
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char encryptedChar = (char)(plainText.charAt(i) ^ key.charAt(i % key.length()));
            outputString.append(encryptedChar);
        }
        
        return outputString.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
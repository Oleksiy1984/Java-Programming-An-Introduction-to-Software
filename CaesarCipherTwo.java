public class CaesarCipherTwo {

    private int key1 = -1;
    private int key2 = -1;
    private CaesarCipher cc1;
    private CaesarCipher cc2;

    /**
     * Constructor - Takes in the two keys used for encryption. Use this constructor when you want to use this object for encryption (& then subsequent
     * decryption of same string)
     *
     * @param key1
     * 				Key1 used in encryption process
     * @param key2
     * 				Key2 used in encryption process
     */

    public CaesarCipherTwo(int key1, int key2){
        this.key1 = key1;
        this.key2 = key2;
        cc1 = new CaesarCipher();
        cc1.set_key(key1);
        cc2 = new CaesarCipher();
        cc2.set_key(key2);

    }

    /**
     * Constructor - To be used when you want to use this class for decryption & the method needs to figure out the decryption keys
     */
    public CaesarCipherTwo(){
        cc1 = new CaesarCipher();
        cc2 = new CaesarCipher();
    }

    /**
     * Takes a String as input and encrypts it using 2 keys
     * @param s
     * 			Input String
     * @return
     * 			Encrypted String
     */

    public String encrypt(String s){
        cc1.set_string_under_action(s);
        cc2.set_string_under_action(s);
        String encrypted1 = cc1.encryptString();
        String encrypted2 = cc2.encryptString();
        String encrypted = CaesarCipher.interleave(encrypted1, encrypted2);
        return encrypted;

    }

    /**
     * Takes a String and decrypts it using 2 keys
     * @param s
     * 			String input for decryption
     * @param mode
     * 			Accepts either 1 or 2 as input
     * 			Mode 1 - Use the complement of key used for encryption while doing decryption. Its critical that this mode be used with the same
     * 					 key settings as used while encrypting the String
     * 			Mode 2 - The method figures out the right decryption key to be used
     * @return
     * 			Decrypted String
     */

    public String decrypt(String s, int mode){

        String [] decrypt = CaesarCipherTwo.halfOfString(s);
        cc1.set_string_under_action(decrypt[0]);
        cc2.set_string_under_action(decrypt[1]);
        String d1 = cc1.decryptString(mode);
        String d2 = cc2.decryptString(mode);
        String d = CaesarCipherTwo.weaveTwoStrings(d1, d2);
        return d;
    }

    /**
     * Break a string into 2 (alternate chars in each of the 2 resultant strings)
     * @param s
     * 			Input String
     * @return
     * 			Returns an array of 2 strings. These are the resultant strings from breaking the input string
     */

    public static String[] halfOfString(String s){
        StringBuilder sb1 = new StringBuilder(10000);
        StringBuilder sb2 = new StringBuilder(10000);
        String [] decrypt = new String[2];

        for (int i = 0; i<s.length();i++){
            if (i%2 == 0){
                sb1.append(s.charAt(i));
            }else {
                sb2.append(s.charAt(i));
            }
        }

        decrypt[0] = sb1.toString();
        decrypt[1] = sb2.toString();
        return decrypt;
    }

    /**
     * This method weaves 2 strings together into a single string. Letters from each of the input strings form alternate letters in final string
     * @param s1
     * 			First input String
     * @param s2
     * 			Second input String
     * @return
     * 			Final resultant String
     */

    public static String weaveTwoStrings(String s1,String s2){
        int len = Math.max(s1.length(), s2.length());
        StringBuilder sb = new StringBuilder(10000);

        for (int i=0; i< len; i++){
            if (i < s1.length() && i < s2.length()){
                sb.append(s1.charAt(i));
                sb.append(s2.charAt(i));
            }else if (i < s1.length() && i >= s2.length()){
                sb.append(s1.charAt(i));
            }else if (i >= s1.length() && i < s2.length()){
                sb.append(s2.charAt(i));
            }
        }
        return sb.toString();

    }
}

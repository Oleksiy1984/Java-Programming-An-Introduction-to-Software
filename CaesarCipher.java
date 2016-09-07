

    /**
     * This class is used for encrypting Strings using the Caesar Cipher method. A key is used to encrypt string under action. Both the string under action & key can be
     * set through public setter methods
     * The same class can also be used to decrypt strings that have been encrypted using the Caesar Cipher method. Decryption can be done in 2 modes (thru the decrypt method)
     * Mode 1 - If a key has already been set & used to encrypt the string to be decrypted, then use mode 1
     * Mode 2 - If the key is unknown then the method will figure out the key
     * @author risshah
     *
     */

    public class CaesarCipher {

        private int encrypt_key = 0; //Key used to encrypt
        private String alphabet = ""; //Alphabets of the english language
        private String e_alphabet = ""; //Shifted character map used for encryption
        private String string_under_action = ""; //String that has to be either encrypted or decrypted
        private int [] char_freq = new int[26]; //freq of all characters in string under action

//    The same class is to be used both for encryption & decryption, hence removing the default constructors. Keep data elements of class (key & string under action)
//	  are now set using setter functions
//    /**
//     * Main constructor
//     * @param key
//     * 				Key used for Caesar Cipher encryption
//     */
//
   // public CaesarCipher(int key){
      //  this.encrypt_key = key;
       // this.generateEncryptionLookup();
   // }
//
//    /**
//     * Default constructor - Uses a key of 5 for encryption
//     */
    public CaesarCipher(){
        this.encrypt_key = 5;
        this.generateEncryptionLookup();
    }
//

        /**
         * Getter function
         * @return
         * 			Returns the shifted character map used for encryption
         */

        public String get_encryption_lookup(){
            return e_alphabet;
        }
        /**
         * Getter function
         * @return
         * 			Returns the key used for encryption
         */

        public int get_key(){
            return encrypt_key;
        }

        /**
         * Getter function for string under action
         * @return
         */

        public String get_string_under_action(){
            return string_under_action;
        }

        /**
         * Setter function for key used by cipher
         * @param key
         *			  input key
         **/

        public void set_key(int key){
            this.encrypt_key = key;
            this.generateEncryptionLookup();

        }

        /**
         * Setter function for string under action
         * @param s
         */

        public void set_string_under_action(String s){
            this.string_under_action = s;
        }

        /**
         *
         * Private function - generates the shifted character map based on the key passed in the object constructor
         */

        private void generateEncryptionLookup(){
            String alphabet_uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String alphabet_lowercase = "abcdefghijklmnopqrstuvwxyz";

            String e_alphabet_uppercase = alphabet_uppercase.substring(encrypt_key) + alphabet_uppercase.substring(0,encrypt_key);
            String e_alphabet_lowercase = alphabet_lowercase.substring(encrypt_key) + alphabet_lowercase.substring(0,encrypt_key);


            alphabet = alphabet_uppercase + alphabet_lowercase;
            e_alphabet = e_alphabet_uppercase + e_alphabet_lowercase;
        }

        /**
         * This is a helper function. It takes in a char and looks up the corresponding character on the shifted character map
         * @param in
         * 			Input character to be encrypted
         * @return
         *			Encrypted character
         */

        private char returnEncryptedChar(char in){
            char out;
            int index = alphabet.indexOf(in);

            if (index != -1){
                out = e_alphabet.charAt(index);
            }else {
                out = in;
            }

            return out;
        }
        /**
         * This function takes a string and returns the encrypted string
         *
         * 			Input string to be encrypted
         * @return
         * 			Returns the encrypted String
         */

        public String encryptString(){
            StringBuilder str = new StringBuilder(10000);
            for (int i=0;i<string_under_action.length(); i++){

                char in = string_under_action.charAt(i);
                str.append(returnEncryptedChar(in));
            }
            return str.toString();
        }

        /**
         * This function takes a string and returns the original text through decryption
         *
         * 			Input string to be Decrypted
         * @param mode
         * 		    If mode = 1, key already set, use its complement to decrypt
         * 			If mode = 2, figure out the key
         * @return
         * 			Returns the original decrypted text
         */

        public String decryptString(int mode){

            int key = -1;
            int current_key = this.get_key();

            if (mode == 1){
                key = 26 - encrypt_key;

            } else if (mode == 2){
                this.get_char_freq();
                key = this.compute_shift(this.get_max_char_freq());

            }

            this.set_key(key); //this statement internally mutates the key which is not a good idea

            String decryptedString = this.encryptString();
            this.set_key(current_key); //this statement reverts the state of the Caesar Cipher
            return decryptedString;

        }
        /**
         * Static method take two strings as inputs and interleaves them
         * @param a
         * 			String 1 for interleaving
         * @param b
         * 			String 2 for interleaving
         * @return
         * 			Interleaved String
         */

        public static String interleave(String a, String b){
            StringBuilder str = new StringBuilder(10000);
            int len = Math.max(a.length(),b.length());

            for (int i=0; i< len; i++){
                if (i < a.length() && i < b.length()){
                    if ((i%2)== 0){
                        str.append(a.charAt(i));
                    } else {
                        str.append(b.charAt(i));
                    }
                }else if (i < a.length() && i >= b.length()){
                    str.append(a.charAt(i));
                }else if (i >= a.length() && i < b.length()){
                    str.append(b.charAt(i));
                }
            }

            return str.toString();
        }

        /**
         * Getter method for character frequency of the supplied string
         * @return
         */

        public int [] get_char_freq(){
            char_frequency();
            return char_freq;
        }

        /**
         * Evalutes the String to be acted upon and populates an array with the character frequencies in the String
         */
        private void char_frequency(){
            String char_map = "abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i<string_under_action.length(); i++){
                char c = (string_under_action.toLowerCase()).charAt(i); //gives the character at i'th position in string
                int n = char_map.indexOf(c); //gives the position of the character on the character map
                if (n!= -1){
                    char_freq[n]++;
                }
            }
        }

        /**
         * Returns the index of character that occurs maximum number of times in the string
         * @return
         */

        public int get_max_char_freq(){
            return this.max_frequency();
        }

        /**
         * Returns the character index with highest frequency of characters in the input string
         * @return
         */

        private int max_frequency(){
            int max = 0;
            int max_index = 0;
            for (int i = 0; i<26; i++){
                if (char_freq[i] > max){
                    max = char_freq[i];
                    max_index = i;
                }
            }
            return max_index;
        }

        /**
         * This method computes the shift used for string encryption in Caesar Cipher.
         * @param n
         * 			Index of the letter that occurs maximum number of times in encrypted string
         * @return
         * 			complement (26 - x) of the original shift (x) that can be used for decryption
         */

        public int compute_shift(int n){
            int shift;
            int shift_complement;
            if (n >= 4){
                shift = n - 4;
            }else {
                shift = 26 + (n - 4);
            }

            shift_complement = 26 - shift;
            return shift_complement;
        }


    }

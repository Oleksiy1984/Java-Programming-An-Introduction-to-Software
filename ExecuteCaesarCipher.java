public class ExecuteCaesarCipher
{
    public static void main(String[] args) {
    
        CaesarCipherTwo f= new CaesarCipherTwo(8,21);
        System.out.println(f.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"));
/*
        CaesarCipher fg=new CaesarCipher(15);
        fg.set_string_under_action("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        System.out.println(fg.encryptString());
*/
       
    }

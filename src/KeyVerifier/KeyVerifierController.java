package KeyVerifier;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyVerifierController {
    private File message;
    private KeyPair keyPair;
    private static final String FILE_PATH = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\INPUT(SignedByBart).EXT";
    private static final String FILE_PATH_OLD = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\INPUT.EXT";
    private static final String PUBLIC_KEY_PATH = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\public.key";

    public KeyVerifierController(){
        message = new File(FILE_PATH);

        try {
            getKey();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            System.out.println("---getKey Failed--- \n");
            e.printStackTrace();
        }

        try {
            verifyMessage();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            System.out.println("---verifyMessage Failed--- \n");
            e.printStackTrace();
        }

    }

    public void getKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        FileInputStream fis;

        // Read Public Key.
        File filePublicKey = new File(PUBLIC_KEY_PATH);
        fis = new FileInputStream(PUBLIC_KEY_PATH);
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();


        // Generate Key
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
        System.out.println();
        PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(encodedPublicKey));

        keyPair = new KeyPair(publicKey, null);
    }

    public void verifyMessage() throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //Get the file
        RandomAccessFile raf = new RandomAccessFile(message.getPath(), "r");

        //Read length of signature
        int sigLength = raf.readInt();

        //Get signature
        byte[] signatureBytes = new byte[sigLength];
        raf.read(signatureBytes);

        //See length of the rest of the message
        int msgLength = (int) raf.length() - 4 - sigLength;

        //Read the message
        byte[] message = new byte[msgLength];
        raf.read(message);
        raf.close();

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(keyPair.getPublic());
        sig.update(message);

        if(sig.verify(signatureBytes)) {
            raf = new RandomAccessFile(FILE_PATH_OLD, "rw");
            raf.write(message);
            raf.close();
            System.out.println("------GREAT SUCCESS!!!------- \n");
        }
        else {
            System.out.println("------AN ERROR HAS OCCURRED------ \n");
        }
    }
}

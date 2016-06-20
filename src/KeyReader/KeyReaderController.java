package KeyReader;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class KeyReaderController {
    private File message;
    private KeyPair keyPair;
    private static final String FILE_PATH = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\INPUT.EXT";
    private static final String PRIVATE_KEY_FILE = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\private.key";

    public KeyReaderController(){
        message = new File(FILE_PATH);
    }

    public void sign(String signer) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException, InvalidKeySpecException {
        //Get the key
        getKey();

        //Create the signature
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(keyPair.getPrivate());

        //Get signature length
        byte[] signatureBytes = sig.sign();
        int signatureLength = signatureBytes.length;

        //Create path
        String messagePath = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\INPUT(SignedBy" + signer + ").EXT";

        //Write all the stuff in one file
        RandomAccessFile raf = new RandomAccessFile(messagePath, "rw");
        raf.writeInt(signatureLength);
        raf.write(signatureBytes);
        raf.write(readMessage());
        raf.close();
    }

    public byte[] readMessage() throws IOException {

        byte[] buffer = new byte[(int) message.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(message);
            if (ios.read(buffer) == -1) {
                throw new IOException(
                        "EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return buffer;
    }

    public void getKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        //get the key from
        FileInputStream fis;

        //Get the file for it's length
        File filePrivateKey = new File(PRIVATE_KEY_FILE);

        //Go and read the file
        fis = new FileInputStream(PRIVATE_KEY_FILE);

        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //Convert to PKCS8 else java can't read.
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        keyPair = new KeyPair(null, privateKey);
    }
}

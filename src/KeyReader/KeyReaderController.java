package KeyReader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
        sig.update(readMessage());

        //Get signature length
        byte[] signatureBytes = sig.sign();
        int signatureLength = signatureBytes.length;

        for (int j=0; j<signatureBytes.length; j++) {
            System.out.format("%02X ", signatureBytes[j]);
        }
        System.out.println();

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

        byte[] array = new byte[(int) message.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(message);
            if (ios.read(array) == -1) {
                throw new IOException(
                        "EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
        return array;
    }

    public void getKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        FileInputStream fis;
        // Read Private Key.
        File filePrivateKey = new File(PRIVATE_KEY_FILE);
        fis = new FileInputStream(PRIVATE_KEY_FILE);
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();


        // Generate Key
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
        System.out.println();
        PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));

        keyPair = new KeyPair(null, privateKey);
    }
}

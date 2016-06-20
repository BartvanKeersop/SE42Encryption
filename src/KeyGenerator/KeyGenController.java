package KeyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Bart van Keersop on 6/20/2016.
 */
public class KeyGenController {

    private final KeyPair keys;
    private KeyPairGenerator keyGen;
    private static final String ALGORITHM = "RSA";
    private static final String PUBLIC_KEY_FILE = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\public.key";
    private static final String PRIVATE_KEY_FILE = "C:\\Users\\Bart van Keersop\\Documents\\GitHub\\SE42Encryption\\files\\private.key";

    public KeyGenController() throws NoSuchProviderException, NoSuchAlgorithmException {
        keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);
        keys = keyGen.generateKeyPair();

        try {
            writePublicKey();
            writePrivateKey();
        } catch (IOException e) {
            System.out.println("------Error trying to write the keyfiles------ \n");
            e.printStackTrace();
        }
    }

    public void writePublicKey() throws IOException {
        /*
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        publicKeyFile.createNewFile();
        ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
        publicKeyOS.writeObject(keys.getPublic());
        publicKeyOS.close();

        //save public key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keys.getPublic().getEncoded());
        FileOutputStream fos = new FileOutputStream(PUBLIC_KEY_FILE);
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();
        */

        byte[] key = keys.getPublic().getEncoded();
        FileOutputStream fos = new FileOutputStream(PUBLIC_KEY_FILE);
        fos.write(key);
        fos.close();
    }

    public void writePrivateKey() throws IOException {
        /*
        File privateKeyFile = new File(PRIVATE_KEY_FILE);
        privateKeyFile.createNewFile();
        ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
        privateKeyOS.writeObject(keys.getPrivate());
        privateKeyOS.close();


        //save private key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keys.getPublic().getEncoded());
        FileOutputStream fos = new FileOutputStream(PRIVATE_KEY_FILE);
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();
        */

        byte[] key = keys.getPrivate().getEncoded();
        FileOutputStream fos = new FileOutputStream(PRIVATE_KEY_FILE);
        fos.write(key);
        fos.close();
    }
}

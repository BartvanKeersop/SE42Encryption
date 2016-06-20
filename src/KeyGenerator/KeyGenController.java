package KeyGenerator;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by Bart van Keersop on 6/20/2016.
 */
public class KeyGenController {

    KeyPairGenerator keyGen;
    public static final String ALGORITHM = "RSA";

    public KeyGenController() throws NoSuchProviderException, NoSuchAlgorithmException {
        keyGen = KeyPairGenerator.getInstance(ALGORITHM);
    }

    
}

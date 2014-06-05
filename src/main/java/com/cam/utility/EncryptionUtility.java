package com.cam.utility;

import javax.persistence.Transient;

import thep.paillier.PrivateKey;

public class EncryptionUtility {
	
	public static PrivateKey privkey = new PrivateKey(128);
}

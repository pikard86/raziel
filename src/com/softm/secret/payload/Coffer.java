package com.softm.secret.payload;

import com.softm.secret.crypt.CofferKey;

public class Coffer<T extends Treasure> {

	private T treasure;
	private byte[] encryptedBytes;

	public byte[] getEncryptedBytes() {
		return encryptedBytes;
	}

	public void setEncryptedBytes(byte[] encryptedBytes) {
		this.encryptedBytes = encryptedBytes;
	}

	public T getTreasure() {
		return treasure;
	}

	public void setTreasure(T treasure) {
		this.treasure = treasure;
	}

	public void open(CofferKey key) {
		key.openCoffer(this);
	};

	public void lock(CofferKey key) {
		key.lockCoffer(this);
	}

}

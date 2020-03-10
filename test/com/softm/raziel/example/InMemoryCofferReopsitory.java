package com.softm.raziel.example;

import java.util.HashMap;

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.repo.CofferRepository;

public class InMemoryCofferReopsitory implements CofferRepository {
	private final HashMap<Long, Coffer> coffers = new HashMap<Long, Coffer>();
	private Long cofferSequence = Long.valueOf(1);

	@Override
	public Coffer findById(final long id) {
		// TODO Auto-generated method stub
		return coffers.get(id);
	}

	@Override
	public long store(final Coffer coffer) {
		if (coffer.getId() == 0) {
			coffer.setId(cofferSequence);
			cofferSequence++;
		}
		coffers.put(coffer.getId(), coffer);
		return coffer.getId();
	}

}

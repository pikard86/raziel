package com.softm.raziel.example;

import com.softm.raziel.Owner;
import com.softm.raziel.repo.OwnerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryOwnerRepository implements OwnerRepository {

    final HashMap<String, Owner> memory = new HashMap<String, Owner>();

    @Override
    public Owner findOwnerById(final String ownerId) {
        return memory.get(ownerId);
    }

    @Override
    public List<Owner> findOwnersByIds(final List<String> ownerIds) {
        final List<Owner> owners = new ArrayList<Owner>();
        for (final String ownerId : ownerIds) {
            final Owner owner = memory.get(ownerId);
            if (owner != null) {
                owners.add(owner);
            }
        }
        return owners;
    }

    @Override
    public void store(final Owner owner) {
        memory.put(owner.getId(), owner);

    }

}

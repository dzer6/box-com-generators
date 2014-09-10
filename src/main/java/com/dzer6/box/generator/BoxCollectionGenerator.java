package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.*;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.ArrayList;

public class BoxCollectionGenerator extends BoxObjectGenerator<BoxCollection> {

    public BoxCollectionGenerator() {
        super(BoxCollection.class);
    }

    @Override
    protected Object generateValue(final SourceOfRandomness random,
                                   final GenerationStatus status,
                                   final Class<BoxCollection> type,
                                   final String key) {
        if (BoxCollection.FIELD_ENTRIES.equals(key)) {
            final ArrayList<BoxTypedObject> entries = new ArrayList<BoxTypedObject>();

            for (int i = 0; i < status.size(); i++) {
                if (random.nextLong() % 41 == 0) {
                    entries.add(generateEntry(random, status));
                }
            }

            return entries;
        }

        return super.generateValue(random, status, type, key);
    }

    protected BoxTypedObject generateEntry(final SourceOfRandomness random, final GenerationStatus status) {
        final Class<? extends BoxItem> type = random.nextBoolean() ? BoxFile.class : BoxFolder.class;
        return generateValue(random, status, type);
    }
}

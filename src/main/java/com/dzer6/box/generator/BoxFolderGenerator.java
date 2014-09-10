package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class BoxFolderGenerator extends BoxObjectGenerator<BoxFolder> {

    public BoxFolderGenerator() {
        super(BoxFolder.class);
    }

    @Override
    protected Object generateValue(final SourceOfRandomness random,
                                   final GenerationStatus status,
                                   final Class<BoxFolder> type,
                                   final String key) {
        if (BoxItem.FIELD_PARENT.equals(key) && random.nextBoolean()) {
            return new BoxFolder();
        }

        return super.generateValue(random, status, type, key);
    }
}

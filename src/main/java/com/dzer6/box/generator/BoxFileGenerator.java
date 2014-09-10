package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.utils.ISO8601DateParser;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.Date;
import java.util.HashMap;

public class BoxFileGenerator extends BoxObjectGenerator<BoxFile> {

    public BoxFileGenerator() {
        super(BoxFile.class);
    }

    @Override
    protected Object generateValue(final SourceOfRandomness random,
                                   final GenerationStatus status,
                                   final Class<BoxFile> type,
                                   final String key) {
        if (BoxFile.FIELD_CONTENT_CREATED_AT.equals(key)) {
            return ISO8601DateParser.toString(generateValue(random, status, Date.class));
        }

        if (BoxFile.FIELD_CONTENT_MODIFIED_AT.equals(key)) {
            return ISO8601DateParser.toString(generateValue(random, status, Date.class));
        }

        if (BoxFile.FIELD_CREATED_AT.equals(key)) {
            return ISO8601DateParser.toString(generateValue(random, status, Date.class));
        }

        if (BoxFile.FIELD_MODIFIED_AT.equals(key)) {
            return ISO8601DateParser.toString(generateValue(random, status, Date.class));
        }

        if (BoxFile.FIELD_PATH_COLLECTION.equals(key)) {

            final BoxCollection collection = generateValue(random, status, BoxCollection.class);

            for (int i = 0; i < status.size(); i++) {
                final BoxFolder folder = new BoxFolder(new HashMap<String, Object>() {{
                    put(BoxFolder.FIELD_NAME, generateValue(random, status, String.class));
                }});

                collection.getEntries().add(folder);
            }

            return collection;
        }

        return super.generateValue(random, status, type, key);
    }
}

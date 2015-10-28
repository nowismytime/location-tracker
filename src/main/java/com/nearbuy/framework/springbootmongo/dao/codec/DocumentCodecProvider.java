package com.nearbuy.framework.springbootmongo.dao.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.Map;

/**
 * Created by tushar on 27/08/15.
 */
public class DocumentCodecProvider implements CodecProvider {

    private Map<Class, Codec> map;

    public DocumentCodecProvider(Map<Class, Codec> map) {
        this.map = map;
    }

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return map.get(clazz);
    }
}

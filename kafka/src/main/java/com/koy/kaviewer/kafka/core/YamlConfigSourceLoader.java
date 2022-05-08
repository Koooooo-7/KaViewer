package com.koy.kaviewer.kafka.core;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class YamlConfigSourceLoader implements ConfigSourceLoader<Map<String, Object>, PropertiesResources<String>> {

    @Override
    public Map<String, Object> load(PropertiesResources<String> source) throws Exception {
        final Yaml yaml = new Yaml();
        return yaml.load(new FileInputStream(source.getResource()));
    }

    @Override
    public boolean support(PropertiesResources.ResourcesType type) {
        return PropertiesResources.ResourcesType.YAML.equals(type);
    }

    @Override
    public <V> Converter<Map<String, Object>, V> getConvertor() {
        return (Converter<Map<String, Object>, V>) yamlConvertor;
    }

    private static final KafkaProperties.KafkaPropertiesConverter<Map<String, Object>> yamlConvertor = configs -> {
        final KafkaProperties kafkaProperties = new KafkaProperties();
        final Map<String, Object> bootstrap = (LinkedHashMap<String, Object>) configs.get("bootstrap");
        kafkaProperties.setBootstrapServers(String.valueOf(bootstrap.getOrDefault("servers", "localhost:9092")));
        return kafkaProperties.buildProperties();
    };

}

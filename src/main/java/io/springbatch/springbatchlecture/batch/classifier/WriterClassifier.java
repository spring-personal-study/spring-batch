package io.springbatch.springbatchlecture.batch.classifier;

import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class WriterClassifier<C, T> implements Classifier<C, T> {

    private Map<String, ItemWriter<ApiRequestDTO>> writerMap = new HashMap<>();

    public void setWriterMap(Map<String, ItemWriter<ApiRequestDTO>> writerMap) {
        this.writerMap = writerMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T classify(C classifiable) {
        return (T) writerMap.get(((ApiRequestDTO) classifiable).getProductDTO().getType());
    }
}
